package com.lq.service.impl;

import com.lq.entity.SysLogWithBLOBs;
import com.lq.mapping.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import com.lq.entity.SysLog;
import com.lq.repository.SysLogRepository;
import com.lq.model.SysLogModel;
import com.lq.service.SysLogService;

import java.util.List;

@Service
public class SysLogServiceImpl implements SysLogService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private SysLogRepository sysLogRepo;

	@Transactional
	@Override
	public int create(SysLogModel sysLogModel) {
		return sysLogRepo.insert(beanMapper.map(sysLogModel, SysLogWithBLOBs.class));
	}

	@Transactional
	@Override
	public int createSelective(SysLogModel sysLogModel) {
		return sysLogRepo.insertSelective(beanMapper.map( sysLogModel, SysLogWithBLOBs.class));
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return sysLogRepo.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@Override
	public SysLogModel findByPrimaryKey(Integer id) {
		SysLog sysLog = sysLogRepo.selectByPrimaryKey(id);
		return beanMapper.map(sysLog, SysLogModel.class);
	}

	@Transactional(readOnly = true)
	@Override
	public long selectCount(SysLogModel sysLogModel) {
		return sysLogRepo.selectCount(beanMapper.map(sysLogModel, SysLog.class));
	}

	@Transactional(readOnly = true)
	@Override
	public List<SysLogModel> selectPage(SysLogModel sysLogModel,Pageable pageable) {
		SysLog sysLog = beanMapper.map(sysLogModel, SysLog.class);
		return beanMapper.mapAsList(sysLogRepo.selectPage(sysLog,pageable),SysLogModel.class);
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(SysLogModel sysLogModel) {
		return sysLogRepo.updateByPrimaryKey(beanMapper.map(sysLogModel, SysLog.class));
	}

	@Transactional
	@Override
	public int updateByPrimaryKeySelective(SysLogModel sysLogModel) {
		return sysLogRepo.updateByPrimaryKeySelective(beanMapper.map(sysLogModel, SysLogWithBLOBs.class));
	}

}
