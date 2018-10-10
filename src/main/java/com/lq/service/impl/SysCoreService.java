package com.lq.service.impl;

import com.google.common.collect.Lists;
import com.lq.entity.SysAcl;
import com.lq.entity.SysUser;
import com.lq.enums.DataUseful;
import com.lq.enums.RoleTypeEnum;
import com.lq.model.SysAclModel;
import com.lq.model.SysRoleModel;
import com.lq.repository.SysAclRepository;
import com.lq.repository.SysRoleAclRepository;
import com.lq.repository.SysRoleRepository;
import com.lq.repository.SysRoleUserRepository;
import com.lq.service.SysRoleUserService;
import com.lq.utils.LoginHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Auther: LQ
 * @Date: 2018/10/8 17:24
 * @Description:
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

    public List<SysAclModel> getCurrentUserAclList() {
        Integer userId = LoginHolder.getUserId();
        return getUserAclList(userId);
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
