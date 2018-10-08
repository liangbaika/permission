package com.lq.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.lq.enums.DataUseful;
import com.lq.mapping.BeanMapper;
import com.lq.utils.LoginHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import com.lq.entity.SysRoleAcl;
import com.lq.repository.SysRoleAclRepository;
import com.lq.model.SysRoleAclModel;
import com.lq.service.SysRoleAclService;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SysRoleAclServiceImpl implements SysRoleAclService {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private SysRoleAclRepository sysRoleAclRepo;

    @Transactional
    @Override
    public int create(SysRoleAclModel sysRoleAclModel) {
        return sysRoleAclRepo.insert(beanMapper.map(sysRoleAclModel, SysRoleAcl.class));
    }

    @Transactional
    @Override
    public int createSelective(SysRoleAclModel sysRoleAclModel) {
        return sysRoleAclRepo.insertSelective(beanMapper.map(sysRoleAclModel, SysRoleAcl.class));
    }

    @Transactional
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return sysRoleAclRepo.deleteByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    @Override
    public SysRoleAclModel findByPrimaryKey(Integer id) {
        SysRoleAcl sysRoleAcl = sysRoleAclRepo.selectByPrimaryKey(id);
        return beanMapper.map(sysRoleAcl, SysRoleAclModel.class);
    }

    @Transactional(readOnly = true)
    @Override
    public long selectCount(SysRoleAclModel sysRoleAclModel) {
        return sysRoleAclRepo.selectCount(beanMapper.map(sysRoleAclModel, SysRoleAcl.class));
    }

    @Transactional(readOnly = true)
    @Override
    public List<SysRoleAclModel> selectPage(SysRoleAclModel sysRoleAclModel, Pageable pageable) {
        SysRoleAcl sysRoleAcl = beanMapper.map(sysRoleAclModel, SysRoleAcl.class);
        return beanMapper.mapAsList(sysRoleAclRepo.selectPage(sysRoleAcl, pageable), SysRoleAclModel.class);
    }

    @Override
    public int update(int roleId, List<Integer> aclIdList) {
        List<Integer> originAclIdList = sysRoleAclRepo.getAclIdListByRoleIdList(Lists.newArrayList(roleId));
        if (originAclIdList.size() == aclIdList.size()) {
            Set<Integer> originAclIdSet = Sets.newHashSet(originAclIdList);
            Set<Integer> nowAclIdList = Sets.newHashSet(aclIdList);
            originAclIdSet.removeAll(nowAclIdList);
            if (CollectionUtils.isEmpty(originAclIdSet)) {
                return DataUseful.USEFUL.getCode();
            }
        }
        int res = doUpdate(roleId, aclIdList);
        return res;
    }

    @Transactional
    private int doUpdate(int roleId, List<Integer> aclIdList) {
        sysRoleAclRepo.deleteByRoleId(roleId);
        if (CollectionUtils.isEmpty(aclIdList)) {
            return 1;
        }
        List<SysRoleAcl> roleAclList = Lists.newArrayList();
        for (Integer aclId : aclIdList) {
            SysRoleAcl sysRoleAcl = new SysRoleAcl();
            sysRoleAcl.setRoleId(roleId);
            sysRoleAcl.setAclId(aclId);
            sysRoleAcl.setOperateIp("127.0.0.1");
            sysRoleAcl.setOperateTime(new Date());
            sysRoleAcl.setOperator(LoginHolder.getUser().getUsername());
            roleAclList.add(sysRoleAcl);
        }
        return sysRoleAclRepo.batchInsert(roleAclList);
    }

    @Transactional
    @Override
    public int updateByPrimaryKey(SysRoleAclModel sysRoleAclModel) {
        return sysRoleAclRepo.updateByPrimaryKey(beanMapper.map(sysRoleAclModel, SysRoleAcl.class));
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(SysRoleAclModel sysRoleAclModel) {
        return sysRoleAclRepo.updateByPrimaryKeySelective(beanMapper.map(sysRoleAclModel, SysRoleAcl.class));
    }

}
