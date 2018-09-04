package com.lq.model;

import com.wlw.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

public class SysRoleUserModel{
	
	private Integer id;
	private Integer roleId;
	private Integer userId;
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
		
	public void setUserId(Integer userId){
		this.userId = userId;
	}
	
	public Integer getUserId(){
		return this.userId;
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