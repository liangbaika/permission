package com.lq.model;

import com.lq.mapping.annotation.MapClass;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

@MapClass("com.lq.entity.SysRole")
public class SysRoleModel {

    private Integer id;
    @NotBlank(message = "角色名不能为空")
    private String name;
    @NotNull(message = "角色类型不能为空")
    //1 超级管理员 2 普通管理员 3 职工 4 老板 5 其他 （可拓展）
    private Integer type;
    private Integer status;
    private String remark;
    private String operater;
    private Date operateTime;
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

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return this.type;
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

    public void setOperater(String operater) {
        this.operater = operater;
    }

    public String getOperater() {
        return this.operater;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public Date getOperateTime() {
        return this.operateTime;
    }

    public void setOperateIp(String operateIp) {
        this.operateIp = operateIp;
    }

    public String getOperateIp() {
        return this.operateIp;
    }


}