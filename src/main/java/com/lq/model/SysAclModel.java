package com.lq.model;

import com.lq.mapping.annotation.MapClass;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.util.Date;

@MapClass("com.lq.entity.SysAcl")
@EqualsAndHashCode(of = {"id"})
public class SysAclModel {

    private Integer id;
    private String code;
    @NotBlank(message = "权限点不能为空")
    private String name;
    @NotNull(message = "必须指定对应的权限模块")
    private Integer aclModuleId;

    private String url;
    @NotNull(message = "必须指定权限点的类型")
    private Integer type;
    private Integer status;
    @NotNull(message = "必须指定权限点的展示顺序")
    private Integer seq;
    private String remark;
    private String operator;
    private Date operateTime;
    private String operateIp;

    //是否默认选中
    private boolean checked = false;
    //是否有权限操作
    private boolean hasAcl = false;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isHasAcl() {
        return hasAcl;
    }

    public void setHasAcl(boolean hasAcl) {
        this.hasAcl = hasAcl;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setAclModuleId(Integer aclModuleId) {
        this.aclModuleId = aclModuleId;
    }

    public Integer getAclModuleId() {
        return this.aclModuleId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
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