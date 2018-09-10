package com.lq.vo;

import lombok.ToString;

import java.util.Date;

/**
 * 系统部门表vo
 */
@ToString
public class SysDeptVO {
    //部门主键id
    private Integer id;
    //部门名字
    private String name;
    //父级部门id
    private Integer parentId;
    //层级
    private String level;
    //当前层级下的顺序 从小到大
    private Integer seq;
    //备注
    private String remark;
    //操作者
    private String operator;
    //操作时间
    private Date operteTime;
    //最后一次操作者ip
    private String operateIp;

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