package com.lq.service.impl;

import com.lq.entity.SysDept;
import com.lq.enums.ErrorCode;
import com.lq.exception.ParamException;
import com.lq.mapping.BeanMapper;
import com.lq.model.SysDeptModel;
import com.lq.repository.SysDeptRepository;
import com.lq.service.SysDeptService;
import com.lq.utils.LevelUtils;
import com.lq.utils.ParamValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;


import java.util.Date;
import java.util.List;

@Service
public class SysDeptServiceImpl implements SysDeptService {

    @Autowired
    private BeanMapper beanMapper;

    @Autowired
    private SysDeptRepository sysDeptRepo;

    @Transactional
    @Override
    public int create(SysDeptModel param) {
        ParamValidator.check(param);
        checkExist(param.getId(), param.getParentId(), param.getName());
        SysDeptModel deptModel = SysDeptModel.builder().name(param.getName())
                .operteTime(new Date())
                .parentId(param.getParentId())
                .remark(param.getRemark())
                .seq(param.getSeq())
                .build();
        deptModel.setLevel(LevelUtils.calculateLevel(getLevel(param.getParentId()), param.getParentId()));
        deptModel.setOperateIp("system");//TODO
        deptModel.setOperateIp("127.0.0.1");//TODO
        return sysDeptRepo.insertSelective(beanMapper.map(deptModel, SysDept.class));
    }

    private String getLevel(Integer parentId) {
        SysDeptModel parentDept = findByPrimaryKey(parentId);
        if (parentDept == null) {
            return null;
        }
        return parentDept.getLevel();
    }

    private void checkExist(Integer id, Integer parentId, String name) {
        //TODO 校验重复
        throw new ParamException(ErrorCode.PARAM_MISS.getMsg());
    }

    @Transactional
    @Override
    public int createSelective(SysDeptModel sysDeptModel) {
        return sysDeptRepo.insertSelective(beanMapper.map(sysDeptModel, SysDept.class));
    }

    @Transactional
    @Override
    public int deleteByPrimaryKey(Integer id) {
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

    @Transactional
    @Override
    public int updateByPrimaryKey(SysDeptModel sysDeptModel) {
        return sysDeptRepo.updateByPrimaryKey(beanMapper.map(sysDeptModel, SysDept.class));
    }

    @Transactional
    @Override
    public int updateByPrimaryKeySelective(SysDeptModel sysDeptModel) {
        return sysDeptRepo.updateByPrimaryKeySelective(beanMapper.map(sysDeptModel, SysDept.class));
    }

}
