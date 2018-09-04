package com.lq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import com.lq.entity.SysRoleUser;
import com.lq.repository.SysRoleUserRepository;
import com.lq.model.SysRoleUserModel;
import com.lq.service.SysRoleUserService;
import com.wlw.pylon.core.beans.mapping.BeanMapper;

import java.util.List;

@Service
public class SysRoleUserServiceImpl implements SysRoleUserService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private SysRoleUserRepository sysRoleUserRepo;

	@Transactional
	@Override
	public int create(SysRoleUserModel sysRoleUserModel) {
		return sysRoleUserRepo.insert(beanMapper.map(sysRoleUserModel, SysRoleUser.class));
	}

	@Transactional
	@Override
	public int createSelective(SysRoleUserModel sysRoleUserModel) {
		return sysRoleUserRepo.insertSelective(beanMapper.map(sysRoleUserModel, SysRoleUser.class));
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return sysRoleUserRepo.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@Override
	public SysRoleUserModel findByPrimaryKey(Integer id) {
		SysRoleUser sysRoleUser = sysRoleUserRepo.selectByPrimaryKey(id);
		return beanMapper.map(sysRoleUser, SysRoleUserModel.class);
	}

	@Transactional(readOnly = true)
	@Override
	public long selectCount(SysRoleUserModel sysRoleUserModel) {
		return sysRoleUserRepo.selectCount(beanMapper.map(sysRoleUserModel, SysRoleUser.class));
	}

	@Transactional(readOnly = true)
	@Override
	public List<SysRoleUserModel> selectPage(SysRoleUserModel sysRoleUserModel,Pageable pageable) {
		SysRoleUser sysRoleUser = beanMapper.map(sysRoleUserModel, SysRoleUser.class);
		return beanMapper.mapAsList(sysRoleUserRepo.selectPage(sysRoleUser,pageable),SysRoleUserModel.class);
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(SysRoleUserModel sysRoleUserModel) {
		return sysRoleUserRepo.updateByPrimaryKey(beanMapper.map(sysRoleUserModel, SysRoleUser.class));
	}
	
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(SysRoleUserModel sysRoleUserModel) {
		return sysRoleUserRepo.updateByPrimaryKeySelective(beanMapper.map(sysRoleUserModel, SysRoleUser.class));
	}

}
