package com.lq.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.lq.enums.DataUseful;
import com.lq.enums.LogType;
import com.lq.mapping.BeanMapper;
import com.lq.model.SysUserModel;
import com.lq.service.SysLogService;
import com.lq.service.SysUserService;
import com.lq.utils.LoginHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import com.lq.entity.SysRoleUser;
import com.lq.repository.SysRoleUserRepository;
import com.lq.model.SysRoleUserModel;
import com.lq.service.SysRoleUserService;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SysRoleUserServiceImpl implements SysRoleUserService {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private SysRoleUserRepository sysRoleUserRepo;

    @Autowired
    private SysLogService sysLogService;

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
    public List<SysRoleUserModel> selectPage(SysRoleUserModel sysRoleUserModel, Pageable pageable) {
        SysRoleUser sysRoleUser = beanMapper.map(sysRoleUserModel, SysRoleUser.class);
        return beanMapper.mapAsList(sysRoleUserRepo.selectPage(sysRoleUser, pageable), SysRoleUserModel.class);
    }

    @Autowired
    private SysUserService sysUserService;

    @Override
    public List<SysUserModel> getUsersByRoleId(int roleId) {
        SysRoleUserModel param = new SysRoleUserModel();
        param.setRoleId(roleId);
        List<SysRoleUserModel> sysRoleUserModels = selectPage(param, null);
        if (CollectionUtils.isEmpty(sysRoleUserModels)) {
            return Lists.newArrayList();
        }
        List<Integer> userIds = sysRoleUserModels.stream().map(s -> s.getUserId()).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(userIds)) {
            return Lists.newArrayList();
        }
        return sysUserService.getUsersByIds(userIds);
    }

    @Override
    public int update(int roleId, List<Integer> userIdList) {
        SysRoleUser param = new SysRoleUser();
        param.setRoleId(roleId);
        List<Integer> originUserIds = sysRoleUserRepo.selectPage(param, null).stream().map(e -> e.getUserId()).collect(Collectors.toList());
        //是否与原始数据一样
        if (originUserIds.size() == userIdList.size()) {
            Set<Integer> originUserIdSet = Sets.newHashSet(originUserIds);
            Set<Integer> nowUserIdList = Sets.newHashSet(userIdList);
            originUserIdSet.removeAll(nowUserIdList);
            if (CollectionUtils.isEmpty(originUserIdSet)) {
                return DataUseful.USEFUL.getCode();
            }
        }
        int res = doUpdate(roleId, userIdList);
        sysLogService.createSelectiveByCustomerOfLog(originUserIds, userIdList, LogType.ROLE_USER, roleId);
        return res;
    }

    @Transactional
    private int doUpdate(int roleId, List<Integer> userIdList) {
        sysRoleUserRepo.deleteByRoleId(roleId);
        if (CollectionUtils.isEmpty(userIdList)) {
            return DataUseful.USEFUL.getCode();
        }
        List<SysRoleUser> roleUserList = Lists.newArrayList();
        for (Integer userId : userIdList) {
            SysRoleUser sysRoleUser = new SysRoleUser();
            sysRoleUser.setRoleId(roleId);
            sysRoleUser.setRoleId(userId);
            sysRoleUser.setOperateIp("127.0.0.1");
            sysRoleUser.setOperateTime(new Date());
            sysRoleUser.setOperator(LoginHolder.getUser().getUsername());
            roleUserList.add(sysRoleUser);
        }
        return sysRoleUserRepo.batchInsert(roleUserList);
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
