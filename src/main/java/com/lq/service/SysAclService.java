
package com.lq.service;

import com.lq.model.SysAclModel;
import java.util.Date;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface SysAclService{

public int create(SysAclModel sysAclModel);

public int createSelective(SysAclModel sysAclModel);

public SysAclModel findByPrimaryKey(Integer id);

public int updateByPrimaryKey(SysAclModel sysAclModel);

public int updateByPrimaryKeySelective(SysAclModel sysAclModel);

public int deleteByPrimaryKey(Integer id);

public long selectCount(SysAclModel sysAclModel);

public List<SysAclModel> selectPage(SysAclModel sysAclModel, Pageable pageable);

}