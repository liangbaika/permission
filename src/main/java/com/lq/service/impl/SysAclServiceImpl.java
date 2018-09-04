package com.lq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import com.lq.entity.SysAcl;
import com.lq.repository.SysAclRepository;
import com.lq.model.SysAclModel;
import com.lq.service.SysAclService;

import java.util.List;

@Service
public class SysAclServiceImpl implements SysAclService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private SysAclRepository sysAclRepo;

	@Transactional
	@Override
	public int create(SysAclModel sysAclModel) {
		return sysAclRepo.insert(beanMapper.map(sysAclModel, SysAcl.class));
	}

	@Transactional
	@Override
	public int createSelective(SysAclModel sysAclModel) {
		return sysAclRepo.insertSelective(beanMapper.map(sysAclModel, SysAcl.class));
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return sysAclRepo.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@Override
	public SysAclModel findByPrimaryKey(Integer id) {
		SysAcl sysAcl = sysAclRepo.selectByPrimaryKey(id);
		return beanMapper.map(sysAcl, SysAclModel.class);
	}

	@Transactional(readOnly = true)
	@Override
	public long selectCount(SysAclModel sysAclModel) {
		return sysAclRepo.selectCount(beanMapper.map(sysAclModel, SysAcl.class));
	}

	@Transactional(readOnly = true)
	@Override
	public List<SysAclModel> selectPage(SysAclModel sysAclModel,Pageable pageable) {
		SysAcl sysAcl = beanMapper.map(sysAclModel, SysAcl.class);
		return beanMapper.mapAsList(sysAclRepo.selectPage(sysAcl,pageable),SysAclModel.class);
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(SysAclModel sysAclModel) {
		return sysAclRepo.updateByPrimaryKey(beanMapper.map(sysAclModel, SysAcl.class));
	}
	
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(SysAclModel sysAclModel) {
		return sysAclRepo.updateByPrimaryKeySelective(beanMapper.map(sysAclModel, SysAcl.class));
	}

}
