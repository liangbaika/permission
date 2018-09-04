
package com.lq.service;

import com.lq.model.SysAclModuleModel;
import java.util.Date;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface SysAclModuleService{

public int create(SysAclModuleModel sysAclModuleModel);

public int createSelective(SysAclModuleModel sysAclModuleModel);

public SysAclModuleModel findByPrimaryKey(Integer id);

public int updateByPrimaryKey(SysAclModuleModel sysAclModuleModel);

public int updateByPrimaryKeySelective(SysAclModuleModel sysAclModuleModel);

public int deleteByPrimaryKey(Integer id);

public long selectCount(SysAclModuleModel sysAclModuleModel);

public List<SysAclModuleModel> selectPage(SysAclModuleModel sysAclModuleModel, Pageable pageable);

}