package com.lq.vo;

import lombok.ToString;

import java.util.Date;
@ToString
public class SysAclVO{
	
	private Integer id;
	private String code;
	private String name;
	private Integer aclModuleId;
	private String url;
	private Integer type;
	private Integer status;
	private Integer seq;
	private String remark;
	private String operator;
	private Date operateTime;
	private String operateIp;
		
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return this.id;
	}
		
	public void setCode(String code){
		this.code = code;
	}
	
	public String getCode(){
		return this.code;
	}
		
	public void setName(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
		
	public void setAclModuleId(Integer aclModuleId){
		this.aclModuleId = aclModuleId;
	}
	
	public Integer getAclModuleId(){
		return this.aclModuleId;
	}
		
	public void setUrl(String url){
		this.url = url;
	}
	
	public String getUrl(){
		return this.url;
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
		
	public void setSeq(Integer seq){
		this.seq = seq;
	}
	
	public Integer getSeq(){
		return this.seq;
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