package com.lq.model;

import com.google.common.collect.Lists;
import com.lq.entity.SysAclModule;
import com.lq.mapping.annotation.MapClass;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@MapClass("com.lq.entity.SysAclModule")
public class SysAclModuleModel {

    private Integer id;
    @NotBlank(message = "权限模块名称不能为空")
    @Length(min = 2, max = 64, message = "长度需在2-64之间")
    private String name;

    private Integer parentId = 0;
    private String level;
    @NotNull(message = "展示顺序不能为空")
    private Integer seq;
    private Integer status = 1;
    private String remark;
    private String operator;
    private Date operteTime;
    private String operateIp;


    private List<SysAclModule> nodes;
    private List<SysAclModel> aclList;

    public List<SysAclModule> getNodes() {
        return nodes;
    }

    public void setNodes(List<SysAclModule> nodes) {
        this.nodes = nodes;
    }

    public List<SysAclModel> getAclList() {
        return aclList;
    }

    public void setAclList(List<SysAclModel> aclList) {
        this.aclList = aclList;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getParentId() {
        return this.parentId;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getLevel() {
        return this.level;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public Integer getSeq() {
        return this.seq;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getOperator() {
        return this.operator;
    }

    public void setOperteTime(Date operteTime) {
        this.operteTime = operteTime;
    }

    public Date getOperteTime() {
        return this.operteTime;
    }

    public void setOperateIp(String operateIp) {
        this.operateIp = operateIp;
    }

    public String getOperateIp() {
        return this.operateIp;
    }


}