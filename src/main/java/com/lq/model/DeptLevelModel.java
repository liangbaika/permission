package com.lq.model;

import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @Auther: LQ
 * @Date: 2018/9/11 20:05
 * @Description:部门树形
 */
public class DeptLevelModel extends SysDeptModel {

    private List<DeptLevelModel> deptList = Lists.newArrayList();

    public static DeptLevelModel adapt(SysDeptModel sysDeptModel) {
        DeptLevelModel deptLevelModel = new DeptLevelModel();
        BeanUtils.copyProperties(sysDeptModel, deptLevelModel);
        return deptLevelModel;
    }

}
