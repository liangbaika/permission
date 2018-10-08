package com.lq.service.impl;

import com.google.common.base.Preconditions;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.lq.entity.SysAclModule;
import com.lq.enums.DataUseful;
import com.lq.enums.ErrorCode;
import com.lq.exception.PermissionException;
import com.lq.mapping.BeanMapper;
import com.lq.model.SysAclModel;
import com.lq.model.SysAclModuleModel;
import com.lq.repository.SysAclRepository;
import com.lq.service.SysAclModuleService;
import com.lq.utils.LoginHolder;
import com.lq.utils.ParamValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

import com.lq.entity.SysRole;
import com.lq.repository.SysRoleRepository;
import com.lq.model.SysRoleModel;
import com.lq.service.SysRoleService;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.ValidationUtils;
import sun.rmi.log.LogHandler;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private SysRoleRepository sysRoleRepo;
    private Comparator<? super SysAclModel> comparter = new Comparator<SysAclModel>() {
        @Override
        public int compare(SysAclModel o1, SysAclModel o2) {
            return o1.getSeq() - o2.getSeq();
        }
    };

    @Transactional
    @Override
    public int create(SysRoleModel sysRoleModel) {
        ParamValidator.check(sysRoleModel);
        //to check  the name is  exist
        checkExist(sysRoleModel);
        sysRoleModel.setOperateIp("");
        sysRoleModel.setOperater(LoginHolder.getUser().getUsername());
        sysRoleModel.setOperateTime(new Date());
        sysRoleModel.setStatus(DataUseful.USEFUL.getCode());
        return sysRoleRepo.insertSelective(beanMapper.map(sysRoleModel, SysRole.class));
    }

    private void checkExist(SysRoleModel sysRoleModel) {
        SysRoleModel param = new SysRoleModel();
        param.setName(sysRoleModel.getName());
        long res = selectCount(param);
        if (res > 0) {
            throw new PermissionException(ErrorCode.ROLE_NAME_EXIST.getMsg());
        }
    }

    @Transactional
    @Override
    public int createSelective(SysRoleModel sysRoleModel) {
        return sysRoleRepo.insertSelective(beanMapper.map(sysRoleModel, SysRole.class));
    }

    @Transactional
    @Override
    public int deleteByPrimaryKey(Integer id) {
        //逻辑删除
       /* SysRoleModel before = findByPrimaryKey(id);
        Preconditions.checkNotNull(before, ErrorCode.ROLE_NOT_EXIST.getMsg());
        before.setStatus(DataUseful.NOUSEFUL.getCode());
        updateByPrimaryKeySelective(before);*/
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
    public List<SysRoleModel> selectPage(SysRoleModel sysRoleModel, Pageable pageable) {
        SysRole sysRole = beanMapper.map(sysRoleModel, SysRole.class);
        return beanMapper.mapAsList(sysRoleRepo.selectPage(sysRole, pageable), SysRoleModel.class);
    }

    @Transactional
    @Override
    public int updateByPrimaryKey(SysRoleModel sysRoleModel) {
        return sysRoleRepo.updateByPrimaryKey(beanMapper.map(sysRoleModel, SysRole.class));
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(SysRoleModel sysRoleModel) {
        SysRoleModel before = findByPrimaryKey(sysRoleModel.getId());
        Preconditions.checkNotNull(before, ErrorCode.ROLE_NOT_EXIST.getMsg());
        if (!before.getName().equals(sysRoleModel.getName())) {
            checkExist(sysRoleModel);
        }
        sysRoleModel.setOperateIp("");
        sysRoleModel.setOperater(LoginHolder.getUser().getUsername());
        sysRoleModel.setOperateTime(new Date());
        return sysRoleRepo.updateByPrimaryKeySelective(beanMapper.map(sysRoleModel, SysRole.class));
    }

    @Autowired
    private SysCoreService sysCoreService;

    @Autowired
    private SysAclRepository sysAclRepository;

    @Autowired
    private SysAclModuleService sysAclModuleService;

    public List<SysAclModuleModel> roleTree(int roleId) {
        //1 当前用户已分配的权限点
        List<SysAclModel> currentUserAclList = sysCoreService.getCurrentUserAclList();
        //2 当前角色分配的权限点
        List<SysAclModel> roleAclList = sysCoreService.getRoleAclList(roleId);
        //当前系统所有的权限点
        List<SysAclModel> allAclId = sysAclRepository.getAll();
        Set<Integer> userAclSet = currentUserAclList.stream().map(s -> s.getId()).collect(Collectors.toSet());
        Set<Integer> roleAclSet = roleAclList.stream().map(s -> s.getId()).collect(Collectors.toSet());
        List<SysAclModel> sysAclModels = Lists.newArrayList();
        for (SysAclModel acl : allAclId) {
            SysAclModel sysAclModel = new SysAclModel();
            if (userAclSet.contains(acl.getId())) {
                sysAclModel.setHasAcl(true);
            }
            if (roleAclSet.contains(acl.getId())) {
                sysAclModel.setChecked(true);
            }
            sysAclModels.add(sysAclModel);
        }
        return toTree(sysAclModels);
    }

    public List<SysAclModuleModel> toTree(List<SysAclModel> sysAclModels) {
        if (CollectionUtils.isEmpty(sysAclModels)) {
            return Lists.newArrayList();
        }
        List<SysAclModule> sysAclModuleTree = sysAclModuleService.getSysAclModuleTree();
        Multimap<Integer, SysAclModel> multimap = ArrayListMultimap.create();
        for (SysAclModel acl : sysAclModels) {
            if (acl.getStatus().equals(DataUseful.USEFUL.getCode())) {
                multimap.put(acl.getAclModuleId(), acl);
            }
        }
        bindAclAsTree(multimap, sysAclModuleTree);
        return beanMapper.mapAsList(sysAclModuleTree, SysAclModuleModel.class);
    }

    private void bindAclAsTree(Multimap<Integer, SysAclModel> multimap, List<SysAclModule> sysAclModuleTree) {

        if (CollectionUtils.isEmpty(sysAclModuleTree)) {
            return;
        }
        for (SysAclModule aclModule : sysAclModuleTree) {
            List<SysAclModel> sysAclModels = (List<SysAclModel>) multimap.get(aclModule.getId());
            if (!CollectionUtils.isEmpty(sysAclModels)) {
                Collections.sort(sysAclModels, comparter);
                aclModule.setAclList(sysAclModels);
            }
            bindAclAsTree(multimap, aclModule.getNodes());
        }
    }


}
