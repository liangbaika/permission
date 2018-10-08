
package com.lq.service;

import com.lq.model.SysRoleAclModel;

import java.util.Date;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SysRoleAclService {

    public int create(SysRoleAclModel sysRoleAclModel);

    public int createSelective(SysRoleAclModel sysRoleAclModel);

    public SysRoleAclModel findByPrimaryKey(Integer id);

    public int updateByPrimaryKey(SysRoleAclModel sysRoleAclModel);

    public int updateByPrimaryKeySelective(SysRoleAclModel sysRoleAclModel);

    public int deleteByPrimaryKey(Integer id);

    public long selectCount(SysRoleAclModel sysRoleAclModel);

    public List<SysRoleAclModel> selectPage(SysRoleAclModel sysRoleAclModel, Pageable pageable);

    int update(int roleId, List<Integer> aclIdList);
}