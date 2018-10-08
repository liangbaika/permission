package com.lq.service.impl;

import com.google.common.collect.Lists;
import com.lq.entity.SysAcl;
import com.lq.model.SysAclModel;
import com.lq.model.SysRoleModel;
import com.lq.repository.SysAclRepository;
import com.lq.repository.SysRoleAclRepository;
import com.lq.repository.SysRoleUserRepository;
import com.lq.utils.LoginHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

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
        return true;
    }
}
