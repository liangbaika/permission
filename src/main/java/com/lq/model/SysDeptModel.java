package com.lq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SysDeptModel {

    private Integer id;

    @NotBlank(message = "部门名称不能为空")
    @Length(max = 20, min = 2, message = "部门名长度需要在2到15之间")
    private String name;
    @NotNull(message = "展示顺序不能为空")
    private Integer seq;
    @Length(message = "备注长度不能超过150", max = 150)
    private String remark;

    private Integer parentId;
    private String level;
    private String operator;
    private Date operteTime;
    private String operateIp;

    private List<SysDeptModel> child;

    public List<SysDeptModel> getChild() {
        return child;
    }

    public void setChild(List<SysDeptModel> child) {
        this.child = child;
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