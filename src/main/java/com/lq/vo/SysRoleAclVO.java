package com.lq.vo;

import java.util.Date;

public class SysRoleAclVO{
	
	private Integer id;
	private Integer roleId;
	private Integer aclId;
	private String operator;
	private Date operateTime;
	private String operateIp;
		
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return this.id;
	}
		
	public void setRoleId(Integer roleId){
		this.roleId = roleId;
	}
	
	public Integer getRoleId(){
		return this.roleId;
	}
		
	public void setAclId(Integer aclId){
		this.aclId = aclId;
	}
	
	public Integer getAclId(){
		return this.aclId;
	}
		
	public void setOperator(String operator){
		this.operator = operator;
	}
	
	public String getOperator(){
		return this.operator;
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