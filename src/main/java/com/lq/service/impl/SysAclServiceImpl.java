package com.lq.service.impl;

import com.google.common.base.Preconditions;
import com.lq.enums.DataUseful;
import com.lq.enums.ErrorCode;
import com.lq.enums.LogType;
import com.lq.exception.PermissionException;
import com.lq.mapping.BeanMapper;
import com.lq.service.SysLogService;
import com.lq.utils.EncryptUtils;
import com.lq.utils.LoginHolder;
import com.lq.utils.ParamValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import com.lq.entity.SysAcl;
import com.lq.repository.SysAclRepository;
import com.lq.model.SysAclModel;
import com.lq.service.SysAclService;

import java.util.Date;
import java.util.List;

@Service
public class SysAclServiceImpl implements SysAclService {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private SysAclRepository sysAclRepo;

    @Autowired
    private SysLogService sysLogService;

    @Transactional
    @Override
    public int create(SysAclModel sysAclModel) {
        ParamValidator.check(sysAclModel);
        if (checkExist(sysAclModel.getAclModuleId(), sysAclModel.getName())) {
            throw new PermissionException(ErrorCode.ACL_CREATE_ERROR.getMsg());
        }
        //complete  the  param
        sysAclModel.setCode(EncryptUtils.getUniqueNumber());
        sysAclModel.setOperateIp("127.0.0.1");
        sysAclModel.setOperateTime(new Date());
        sysAclModel.setOperator(LoginHolder.getUser().getUsername());
        sysAclModel.setStatus(DataUseful.USEFUL.getCode());
        SysAcl after = beanMapper.map(sysAclModel, SysAcl.class);
        int res = sysAclRepo.insertSelective(after);
        sysLogService.createSelectiveByCustomerOfLog(null, after, LogType.ACL, after.getId());
        return res;
    }

    private boolean checkExist(Integer aclModuleId, String name) {
        return sysAclRepo.checkExist(aclModuleId, name) > 0 ? true : false;
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
    public List<SysAclModel> selectPage(SysAclModel param, Pageable pageable) {
        //the param  usually is  the  aclModuleId
        if (param == null) {
            throw new PermissionException(ErrorCode.PARAM_MISS.getMsg());
        }
        SysAcl sysAcl = beanMapper.map(param, SysAcl.class);
        return beanMapper.mapAsList(sysAclRepo.selectPage(sysAcl, pageable), SysAclModel.class);
    }

    @Transactional
    @Override
    public int updateByPrimaryKey(SysAclModel sysAclModel) {
        return sysAclRepo.updateByPrimaryKey(beanMapper.map(sysAclModel, SysAcl.class));
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(SysAclModel sysAclModel) {
        ParamValidator.check(sysAclModel);
        SysAcl before = sysAclRepo.selectByPrimaryKey(sysAclModel.getId());
        Preconditions.checkNotNull(before, ErrorCode.SYSACL_NOTEXIST.getMsg());
        if (!before.getName().equals(sysAclModel.getName()) && checkExist(sysAclModel.getAclModuleId(), sysAclModel.getName())) {
            throw new PermissionException(ErrorCode.ACL_CREATE_ERROR.getMsg());
        }
        sysAclModel.setOperateIp("127.0.0.1");
        sysAclModel.setOperateTime(new Date());
        sysAclModel.setOperator(LoginHolder.getUser().getUsername());
        sysLogService.createSelectiveByCustomerOfLog(before, sysAclModel, LogType.ACL, before.getId());
        return sysAclRepo.updateByPrimaryKeySelective(beanMapper.map(sysAclModel, SysAcl.class));
    }

}
