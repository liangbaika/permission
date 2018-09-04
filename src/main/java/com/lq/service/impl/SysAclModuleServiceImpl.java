package com.lq.service.impl;

import com.lq.mapping.BeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import com.lq.entity.SysAclModule;
import com.lq.repository.SysAclModuleRepository;
import com.lq.model.SysAclModuleModel;
import com.lq.service.SysAclModuleService;

import java.util.List;

@Service
public class SysAclModuleServiceImpl implements SysAclModuleService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private SysAclModuleRepository sysAclModuleRepo;

	@Transactional
	@Override
	public int create(SysAclModuleModel sysAclModuleModel) {
		return sysAclModuleRepo.insert(beanMapper.map(sysAclModuleModel, SysAclModule.class));
	}

	@Transactional
	@Override
	public int createSelective(SysAclModuleModel sysAclModuleModel) {
		return sysAclModuleRepo.insertSelective(beanMapper.map(sysAclModuleModel, SysAclModule.class));
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return sysAclModuleRepo.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@Override
	public SysAclModuleModel findByPrimaryKey(Integer id) {
		SysAclModule sysAclModule = sysAclModuleRepo.selectByPrimaryKey(id);
		return beanMapper.map(sysAclModule, SysAclModuleModel.class);
	}

	@Transactional(readOnly = true)
	@Override
	public long selectCount(SysAclModuleModel sysAclModuleModel) {
		return sysAclModuleRepo.selectCount(beanMapper.map(sysAclModuleModel, SysAclModule.class));
	}

	@Transactional(readOnly = true)
	@Override
	public List<SysAclModuleModel> selectPage(SysAclModuleModel sysAclModuleModel,Pageable pageable) {
		SysAclModule sysAclModule = beanMapper.map(sysAclModuleModel, SysAclModule.class);
		return beanMapper.mapAsList(sysAclModuleRepo.selectPage(sysAclModule,pageable),SysAclModuleModel.class);
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(SysAclModuleModel sysAclModuleModel) {
		return sysAclModuleRepo.updateByPrimaryKey(beanMapper.map(sysAclModuleModel, SysAclModule.class));
	}

	@Transactional
	@Override
	public int updateByPrimaryKeySelective(SysAclModuleModel sysAclModuleModel) {
		return sysAclModuleRepo.updateByPrimaryKeySelective(beanMapper.map(sysAclModuleModel, SysAclModule.class));
	}

}
