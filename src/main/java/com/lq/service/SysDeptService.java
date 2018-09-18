
package com.lq.service;

import java.util.Date;

import com.lq.model.SysDeptModel;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SysDeptService {

    public int create(SysDeptModel sysDeptModel);

    public int createSelective(SysDeptModel sysDeptModel);

    public SysDeptModel findByPrimaryKey(Integer id);

    public int updateByPrimaryKey(SysDeptModel sysDeptModel);

    public int updateByPrimaryKeySelective(SysDeptModel sysDeptModel);

    public int deleteByPrimaryKey(Integer id);

    public long selectCount(SysDeptModel sysDeptModel);

    public List<SysDeptModel> selectPage(SysDeptModel sysDeptModel, Pageable pageable);

    /**
     * 获取组织部门树
     *
     * @return
     */
    List<SysDeptModel> getSysDeptTree();

}