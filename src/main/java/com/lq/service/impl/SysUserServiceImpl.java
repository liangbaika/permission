package com.lq.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import com.lq.entity.SysUser;
import com.lq.repository.SysUserRepository;
import com.lq.model.SysUserModel;
import com.lq.service.SysUserService;

import java.util.List;

@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private BeanMapper beanMapper;

	@Autowired
	private SysUserRepository sysUserRepo;

	@Transactional
	@Override
	public int create(SysUserModel sysUserModel) {
		return sysUserRepo.insert(beanMapper.map(sysUserModel, SysUser.class));
	}

	@Transactional
	@Override
	public int createSelective(SysUserModel sysUserModel) {
		return sysUserRepo.insertSelective(beanMapper.map(sysUserModel, SysUser.class));
	}

	@Transactional
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return sysUserRepo.deleteByPrimaryKey(id);
	}

	@Transactional(readOnly = true)
	@Override
	public SysUserModel findByPrimaryKey(Integer id) {
		SysUser sysUser = sysUserRepo.selectByPrimaryKey(id);
		return beanMapper.map(sysUser, SysUserModel.class);
	}

	@Transactional(readOnly = true)
	@Override
	public long selectCount(SysUserModel sysUserModel) {
		return sysUserRepo.selectCount(beanMapper.map(sysUserModel, SysUser.class));
	}

	@Transactional(readOnly = true)
	@Override
	public List<SysUserModel> selectPage(SysUserModel sysUserModel,Pageable pageable) {
		SysUser sysUser = beanMapper.map(sysUserModel, SysUser.class);
		return beanMapper.mapAsList(sysUserRepo.selectPage(sysUser,pageable),SysUserModel.class);
	}

	@Transactional
	@Override
	public int updateByPrimaryKey(SysUserModel sysUserModel) {
		return sysUserRepo.updateByPrimaryKey(beanMapper.map(sysUserModel, SysUser.class));
	}
	
	@Transactional
	@Override
	public int updateByPrimaryKeySelective(SysUserModel sysUserModel) {
		return sysUserRepo.updateByPrimaryKeySelective(beanMapper.map(sysUserModel, SysUser.class));
	}

}
