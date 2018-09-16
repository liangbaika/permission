package com.lq.model;

import com.lq.mapping.annotation.MapClass;
import lombok.Builder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;
@MapClass("com.lq.entity.SysUser")
public class SysUserModel{
	
	private Integer id;
	@NotBlank(message = "用户名不能为为空")
	private String username;
	private String password;
	@NotBlank(message = "电话不能为空")
	@Length(min = 6,max =13,message = "电话长度在6到13位")
	private String telephone;
	@NotBlank(message = "邮箱不能为空")
	@Length(min = 6,max =30,message = "邮箱长度在6到30位")
	private String mail;
	private Integer deptId;
	private Integer status;
	@Length(min = 0,max =80,message = "备注长度在0到80位")
	private String remark;
	private String operater;
	private Date operateTime;
	private String operateIp;

	private String keyWord;

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return this.id;
	}
		
	public void setUsername(String username){
		this.username = username;
	}
	
	public String getUsername(){
		return this.username;
	}
		
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getPassword(){
		return this.password;
	}
		
	public void setTelephone(String telephone){
		this.telephone = telephone;
	}
	
	public String getTelephone(){
		return this.telephone;
	}
		
	public void setMail(String mail){
		this.mail = mail;
	}
	
	public String getMail(){
		return this.mail;
	}
		
	public void setDeptId(Integer deptId){
		this.deptId = deptId;
	}
	
	public Integer getDeptId(){
		return this.deptId;
	}
		
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return this.status;
	}
		
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getRemark(){
		return this.remark;
	}
		
	public void setOperater(String operater){
		this.operater = operater;
	}
	
	public String getOperater(){
		return this.operater;
	}
		
	public void setOperateTime(Date operateTime){
		this.operateTime = operateTime;
	}
	
	public Date getOperateTime(){
		return this.operateTime;
	}
		
	public void setOperateIp(String operateIp){
		this.operateIp = operateIp;
	}
	
	public String getOperateIp(){
		return this.operateIp;
	}
		
		
}