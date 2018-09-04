package com.lq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import com.lq.entity.SysRole;
import com.lq.repository.SysRoleRepository;
import com.lq.model.SysRoleModel;
import com.lq.service.SysRoleService;
import com.wlw.pylon.core.beans.mapping.BeanMapper;

import java.util.List;

@Service
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private SysRoleRepository sysRoleRepo;

	@Transactional
	@Override
	public int create(SysRoleModel sysRoleModel) {
		return sysRoleRepo.insert(beanMapper.map(sysRoleModel, SysRole.class));
	}

	@Transactional
	@Override
	public int createSelective(SysRoleModel sysRoleModel) {
		return sysRoleRepo.insertSelective(beanMapper.map(sysRoleModel, SysRole.class));
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return sysRoleRepo.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@Override
	public SysRoleModel findByPrimaryKey(Integer id) {
		SysRole sysRole = sysRoleRepo.selectByPrimaryKey(id);
		return beanMapper.map(sysRole, SysRoleModel.class);
	}

	@Transactional(readOnly = true)
	@Override
	public long selectCount(SysRoleModel sysRoleModel) {
		return sysRoleRepo.selectCount(beanMapper.map(sysRoleModel, SysRole.class));
	}

	@Transactional(readOnly = true)
	@Override
	public List<SysRoleModel> selectPage(SysRoleModel sysRoleModel,Pageable pageable) {
		SysRole sysRole = beanMapper.map(sysRoleModel, SysRole.class);
		return beanMapper.mapAsList(sysRoleRepo.selectPage(sysRole,pageable),SysRoleModel.class);
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(SysRoleModel sysRoleModel) {
		return sysRoleRepo.updateByPrimaryKey(beanMapper.map(sysRoleModel, SysRole.class));
	}
	
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(SysRoleModel sysRoleModel) {
		return sysRoleRepo.updateByPrimaryKeySelective(beanMapper.map(sysRoleModel, SysRole.class));
	}

}
