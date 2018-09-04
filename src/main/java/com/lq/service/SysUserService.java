
package com.lq.service;

import com.lq.model.SysUserModel;
import java.util.Date;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface SysUserService{

public int create(SysUserModel sysUserModel);

public int createSelective(SysUserModel sysUserModel);

public SysUserModel findByPrimaryKey(Integer id);

public int updateByPrimaryKey(SysUserModel sysUserModel);

public int updateByPrimaryKeySelective(SysUserModel sysUserModel);

public int deleteByPrimaryKey(Integer id);

public long selectCount(SysUserModel sysUserModel);

public List<SysUserModel> selectPage(SysUserModel sysUserModel, Pageable pageable);

}