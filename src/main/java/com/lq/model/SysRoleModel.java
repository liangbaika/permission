package com.lq.model;

import com.wlw.pylon.core.beans.mapping.annotation.MapClass;
import java.util.Date;

public class SysRoleModel{
	
	private Integer id;
	private String name;
	private Integer type;
	private Integer status;
	private String remark;
	private String operater;
	private Date operateTime;
	private String operateIp;
		
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return this.id;
	}
		
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
		
	public void setType(Integer type){
		this.type = type;
	}
	
	public Integer getType(){
		return this.type;
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