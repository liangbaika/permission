
package com.lq.service;

import com.lq.model.SysAclModuleModel;
import com.lq.model.SysRoleModel;

import java.util.Date;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SysRoleService {

    public int create(SysRoleModel sysRoleModel);

    public int createSelective(SysRoleModel sysRoleModel);

    public SysRoleModel findByPrimaryKey(Integer id);

    public int updateByPrimaryKey(SysRoleModel sysRoleModel);

    public int updateByPrimaryKeySelective(SysRoleModel sysRoleModel);

    public int deleteByPrimaryKey(Integer id);

    public long selectCount(SysRoleModel sysRoleModel);

    public List<SysRoleModel> selectPage(SysRoleModel sysRoleModel, Pageable pageable);

    public List<SysAclModuleModel> roleTree(int roleId);

}