
package com.lq.service;

import com.lq.model.SysLogModel;
import java.util.Date;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface SysLogService{

public int create(SysLogModel sysLogModel);

public int createSelective(SysLogModel sysLogModel);

public SysLogModel findByPrimaryKey(Integer id);

public int updateByPrimaryKey(SysLogModel sysLogModel);

public int updateByPrimaryKeySelective(SysLogModel sysLogModel);

public int deleteByPrimaryKey(Integer id);

public long selectCount(SysLogModel sysLogModel);

public List<SysLogModel> selectPage(SysLogModel sysLogModel, Pageable pageable);

}