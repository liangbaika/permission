
package com.lq.service;

import com.lq.model.SysRoleUserModel;
import java.util.Date;

import com.lq.model.SysUserModel;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface SysRoleUserService{

public int create(SysRoleUserModel sysRoleUserModel);

public int createSelective(SysRoleUserModel sysRoleUserModel);

public SysRoleUserModel findByPrimaryKey(Integer id);

public int updateByPrimaryKey(SysRoleUserModel sysRoleUserModel);

public int updateByPrimaryKeySelective(SysRoleUserModel sysRoleUserModel);

public int deleteByPrimaryKey(Integer id);

public long selectCount(SysRoleUserModel sysRoleUserModel);

public List<SysRoleUserModel> selectPage(SysRoleUserModel sysRoleUserModel, Pageable pageable);

    List<SysUserModel> getUsersByRoleId(int roleId);

    int update(int roleId, List<Integer> userIdList);
}