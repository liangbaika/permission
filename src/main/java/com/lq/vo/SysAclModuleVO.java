package com.lq.vo;


import lombok.ToString;

import java.util.Date;

@ToString
public class SysAclModuleVO{
	
	private Integer id;
	private String name;
	private Integer parentId;
	private String level;
	private Integer seq;
	private Integer status;
	private String remark;
	private String operator;
	private Date operteTime;
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
		
	public void setParentId(Integer parentId){
		this.parentId = parentId;
	}
	
	public Integer getParentId(){
		return this.parentId;
	}
		
	public void setLevel(String level){
		this.level = level;
	}
	
	public String getLevel(){
		return this.level;
	}
		
	public void setSeq(Integer seq){
		this.seq = seq;
	}
	
	public Integer getSeq(){
		return this.seq;
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
		
	public void setOperator(String operator){
		this.operator = operator;
	}
	
	public String getOperator(){
		return this.operator;
	}
		
	public void setOperteTime(Date operteTime){
		this.operteTime = operteTime;
	}
	
	public Date getOperteTime(){
		return this.operteTime;
	}
		
	public void setOperateIp(String operateIp){
		this.operateIp = operateIp;
	}
	
	public String getOperateIp(){
		return this.operateIp;
	}
		
		
}