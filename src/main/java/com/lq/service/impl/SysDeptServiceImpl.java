package com.lq.service.impl;

import com.google.common.collect.Lists;
import com.lq.entity.SysDept;
import com.lq.enums.ErrorCode;
import com.lq.exception.ParamException;
import com.lq.exception.PermissionException;
import com.lq.mapping.BeanMapper;
import com.lq.model.SysDeptModel;
import com.lq.repository.SysDeptRepository;
import com.lq.service.SysDeptService;
import com.lq.utils.Const;
import com.lq.utils.LevelUtils;
import com.lq.utils.LoginHolder;
import com.lq.utils.ParamValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.util.CollectionUtils;


import java.util.*;

@Service
public class SysDeptServiceImpl implements SysDeptService {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private SysDeptRepository sysDeptRepo;

    private Comparator<SysDeptModel> comparator = new Comparator<SysDeptModel>() {
        @Override
        public int compare(SysDeptModel s1, SysDeptModel s2) {
            return s2.getSeq() - s1.getSeq();
        }
    };

    @Transactional
    @Override
    public int create(SysDeptModel param) {
        ParamValidator.check(param);
        checkExist(param.getParentId(), param.getName());
        SysDeptModel deptModel = SysDeptModel.builder().name(param.getName())
                .operteTime(new Date())
                .parentId(param.getParentId())
                .remark(param.getRemark())
                .seq(param.getSeq())
                .build();
        deptModel.setOperateIp("system");//TODO
        deptModel.setOperateIp("127.0.0.1");//TODO
        return sysDeptRepo.insertSelective(beanMapper.map(deptModel, SysDept.class));
    }

    /**
     * 检查在父部门下是否已存在该部门
     *
     * @param parentId
     * @param name
     */
    private void checkExist(Integer parentId, String name) {
        if (StringUtils.isBlank(name)) {
            throw new ParamException(ErrorCode.PARAM_MISS.getMsg());
        }
        if (parentId == null) {
            parentId = Const.DEPT_ROOT;
        }
        int res = sysDeptRepo.selectDeptRepeat(parentId, name);
        if (res > 0) {
            throw new ParamException(ErrorCode.DEPT_REPEAT.getMsg());
        }
    }

    @Transactional
    @Override
    public int createSelective(SysDeptModel sysDeptModel) {
        return sysDeptRepo.insertSelective(beanMapper.map(sysDeptModel, SysDept.class));
    }

    @Transactional
    @Override
    public int deleteByPrimaryKey(Integer id) {
        if (id == null || id < 0) {
            throw new ParamException(ErrorCode.PARAM_ERROR.getMsg());
        }
        //查询此部门下有没有部门存在 若存在 不能删 只能先删掉下级存在的数据才能删(此处的删除有多种 这里我们选择最简单的一种)
        SysDept sysDept = new SysDept();
        sysDept.setParentId(id);
        List<SysDept> sysDepts = sysDeptRepo.selectPage(sysDept, null);
        if (!CollectionUtils.isEmpty(sysDepts)) {
            throw new PermissionException(ErrorCode.DELETE_ERROR1.getMsg());
        }
        return sysDeptRepo.deleteByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    @Override
    public SysDeptModel findByPrimaryKey(Integer id) {
        SysDept sysDept = sysDeptRepo.selectByPrimaryKey(id);
        return beanMapper.map(sysDept, SysDeptModel.class);
    }

    @Transactional(readOnly = true)
    @Override
    public long selectCount(SysDeptModel sysDeptModel) {
        return sysDeptRepo.selectCount(beanMapper.map(sysDeptModel, SysDept.class));
    }

    @Transactional(readOnly = true)
    @Override
    public List<SysDeptModel> selectPage(SysDeptModel sysDeptModel, Pageable pageable) {
        SysDept sysDept = beanMapper.map(sysDeptModel, SysDept.class);
        return beanMapper.mapAsList(sysDeptRepo.selectPage(sysDept, pageable), SysDeptModel.class);
    }

    @Override
    public List<SysDeptModel> getSysDeptTree() {
        //查询所有的组织结构
        List<SysDeptModel> treeModels = selectPage(new SysDeptModel(), null);
        Iterator<SysDeptModel> iterator = treeModels.iterator();
        //找出顶级组织机构（这里我们定义 顶级的pid为0 ）
        ArrayList<SysDeptModel> rootNodes = Lists.newArrayList();
        while (iterator.hasNext()) {
            SysDeptModel node = iterator.next();
            if (node.getParentId() == Const.DEPT_ROOT) {
                rootNodes.add(node);
                iterator.remove();
            }
        }
        //为当前的组织机构排序（根据seq值）
        if (!rootNodes.isEmpty()) {
            rootNodes.sort(comparator);
        }
        //每隔从顶级开始递归寻找他们的子节点
        if (!treeModels.isEmpty() && !rootNodes.isEmpty()) {
            rootNodes.forEach(rootNode -> constructTree(rootNode, treeModels));
        }
        return rootNodes;
    }

    /**
     * 构造树
     *
     * @param parentNode 父节点
     * @param treeModels 剩余节点
     */
    private void constructTree(SysDeptModel parentNode, List<SysDeptModel> treeModels) {
        Iterator<SysDeptModel> iterator = treeModels.iterator();
        //保存子节点
        List<SysDeptModel> childrens = new ArrayList<>();
        while (iterator.hasNext()) {
            SysDeptModel node = iterator.next();
            //找出下一级的节点
            if (parentNode.getId().equals(node.getParentId())) {
                childrens.add(node);
                iterator.remove();
            }
        }
        //为当前节点排序
        if (!childrens.isEmpty()) {
            childrens.sort(comparator);
        }
        //设置当前子节点为当前父节点的子集
        if (!CollectionUtils.isEmpty(childrens)) {
            parentNode.setChild(childrens);
        }
        //递归进行上述步骤  当我们的子节点没有时就一个顶级组织的递归就结束了
        //当treeModels 空时 我们所有的顶级节点都把递归执行完了 就结束了
        if (!CollectionUtils.isEmpty(treeModels) && !childrens.isEmpty()) {
            childrens.forEach(node -> constructTree(node, treeModels));
        }
    }

    @Transactional
    @Override
    public int updateByPrimaryKey(SysDeptModel sysDeptModel) {
        return sysDeptRepo.updateByPrimaryKey(beanMapper.map(sysDeptModel, SysDept.class));
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(SysDeptModel sysDeptModel) {
        if (sysDeptModel == null || sysDeptModel.getId() == null) {
            throw new ParamException(ErrorCode.PARAM_MISS.getMsg());
        }
        if (sysDeptModel.getName() != null) {
            checkExist(sysDeptModel.getParentId(), sysDeptModel.getName());
        }
        sysDeptModel.setOperateIp("127.0.0.1");
        sysDeptModel.setOperator(LoginHolder.getUser().getUsername());
        sysDeptModel.setOperteTime(new Date());
        //不可更改
        sysDeptModel.setId(null);
        sysDeptModel.setParentId(null);
        SysDept before = sysDeptRepo.selectByPrimaryKey(sysDeptModel.getId());
        //TODO before 可以记录日志
        return sysDeptRepo.updateByPrimaryKeySelective(beanMapper.map(sysDeptModel, SysDept.class));
    }

}
