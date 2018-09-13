package com.lq.model;

import java.util.List;

/**
 * @Auther: LQ
 * @Date: 2018/9/13 21:18
 * @Description: 组织部门树
 */
public class DeptTreeModel {

    private SysDeptModel dept;

    private List<SysDeptModel> nodes;


    public SysDeptModel getDept() {
        return dept;
    }

    public void setDept(SysDeptModel dept) {
        this.dept = dept;
    }

    public List<SysDeptModel> getNodes() {
        return nodes;
    }

    public void setNodes(List<SysDeptModel> nodes) {
        this.nodes = nodes;
    }
}
