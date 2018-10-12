package com.lq.service.impl;

import com.google.common.collect.Lists;
import com.lq.entity.SysAcl;
import com.lq.entity.SysUser;
import com.lq.enums.CahceKeyConst;
import com.lq.enums.DataUseful;
import com.lq.enums.RoleTypeEnum;
import com.lq.model.SysAclModel;
import com.lq.model.SysRoleModel;
import com.lq.redis.SysCacheServiceOfRedis;
import com.lq.repository.SysAclRepository;
import com.lq.repository.SysRoleAclRepository;
import com.lq.repository.SysRoleRepository;
import com.lq.repository.SysRoleUserRepository;
import com.lq.service.SysRoleUserService;
import com.lq.utils.JsonMapper;
import com.lq.utils.LoginHolder;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Auther: LQ
 * @Date: 2018/10/8 17:24
 * @Description:权限核心关系维护层
 */
@Service
public class SysCoreService {

    @Autowired
    private SysAclRepository sysAclRepository;
    @Autowired
    private SysRoleUserRepository sysRoleUserRepository;

    @Autowired
    private SysRoleRepository sysRoleRepository;

    @Autowired
    private SysRoleAclRepository sysRoleAclRepository;

    @Resource(name = "sysCacheServiceOfRedis")
    private SysCacheServiceOfRedis sysCacheServiceOfRedis;

    public List<SysAclModel> getCurrentUserAclList() {
        Integer userId = LoginHolder.getUserId();
        return getUserAclListFromCache(userId);
    }

    public List<SysAclModel> getRoleAclList(int roleId) {
        List<Integer> aclIdList = sysRoleAclRepository.getAclIdListByRoleIdList(new ArrayList<>(roleId));
        if (CollectionUtils.isEmpty(aclIdList)) {
            return Lists.newArrayList();
        }
        return sysAclRepository.getByIdList(aclIdList);

    }

    public List<SysAclModel> getUserAclList(int userId) {
        if (isSuperAdmin()) {
            return sysAclRepository.getAll();
        }
        List<Integer> userRoleIdList = sysRoleUserRepository.getRoleIDListByUserId(userId);
        if (CollectionUtils.isEmpty(userRoleIdList)) {
            return Lists.newArrayList();
        }
        List<Integer> userAclIdList = sysRoleAclRepository.getAclIdListByRoleIdList(userRoleIdList);
        if (CollectionUtils.isEmpty(userAclIdList)) {
            return Lists.newArrayList();
        }
        return sysAclRepository.getByIdList(userAclIdList);
    }


    public List<SysAclModel> getUserAclListFromCache(int userId) {
        String cacheValue = sysCacheServiceOfRedis.getFromCache(CahceKeyConst.USER_ACLS, String.valueOf(userId));
        if (cacheValue == null) {
            List<SysAclModel> userAclList = getUserAclList(userId);
            //timeOutSeconds  可以自由配置 建议写在配置文件中, 1800：半小时
            if (!CollectionUtils.isEmpty(userAclList)) {
                sysCacheServiceOfRedis.saveCache(JsonMapper.obj2String(userAclList), 1800, CahceKeyConst.USER_ACLS, String.valueOf(userId));
            }
            return CollectionUtils.isEmpty(userAclList) ? Lists.newArrayList() : userAclList;
        }
        return JsonMapper.string2Obj(cacheValue, new TypeReference<List<SysAclModel>>() {
        });
    }


    public boolean isSuperAdmin() {
        List<Integer> roleIDListByUserId = sysRoleUserRepository.getRoleIDListByUserId(LoginHolder.getUserId());
        if (CollectionUtils.isEmpty(roleIDListByUserId)) {
            return false;
        }
        List<SysRoleModel> roles = sysRoleRepository.getRoleByIdList(roleIDListByUserId);
        if (CollectionUtils.isEmpty(roles)) {
            return false;
        }
        for (SysRoleModel role : roles) {
            if (role == null || role.getStatus().equals(DataUseful.NOUSEFUL.getCode())) {
                continue;
            }
            if (role.getType().equals(RoleTypeEnum.SuperAdmin.getCode())) {
                return true;
            }
        }
        return false;
    }

    public Boolean hasAclOfThisUrl(String url) {
        if (isSuperAdmin()) {
            return true;
        }
        SysAcl param = new SysAcl();
        param.setUrl(url);
        List<SysAcl> sysAcls = sysAclRepository.selectPage(param, null);
        if (CollectionUtils.isEmpty(sysAcls)) {
            return true;
        }
        List<SysAclModel> currentUserAclList = getCurrentUserAclList();
        Set<Integer> userAclIdSet = currentUserAclList.stream().map(e -> e.getId()).collect(Collectors.toSet());
        boolean hasValidAcl = false;
        for (SysAcl sysAcl : sysAcls) {
            if (sysAcl == null || sysAcl.getStatus().equals(DataUseful.NOUSEFUL.getCode())) {
                continue;
            }
            hasValidAcl = true;
            if (userAclIdSet.contains(sysAcl.getId())) {
                return true;
            }
        }
        if (!hasValidAcl) {
            return true;
        }
        return false;
    }
}
