package com.lq.model;

import com.lq.mapping.annotation.MapClass;
import java.util.Date;
@MapClass("com.lq.entity.SysLog")
public class SysLogModel{
	
	private Integer id;
	private Integer type;
	private Integer targetId;
	private String operator;
	private Date operateTime;
	private String operateIp;
	private Integer status;
	private String oldValue;
	private String newValue;
		
	public void setId(Integer id){
		this.id = id;
	}
	
	public Integer getId(){
		return this.id;
	}
		
	public void setType(Integer type){
		this.type = type;
	}
	
	public Integer getType(){
		return this.type;
	}
		
	public void setTargetId(Integer targetId){
		this.targetId = targetId;
	}
	
	public Integer getTargetId(){
		return this.targetId;
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
		
	public void setStatus(Integer status){
		this.status = status;
	}
	
	public Integer getStatus(){
		return this.status;
	}
		
	public void setOldValue(String oldValue){
		this.oldValue = oldValue;
	}
	
	public String getOldValue(){
		return this.oldValue;
	}
		
	public void setNewValue(String newValue){
		this.newValue = newValue;
	}
	
	public String getNewValue(){
		return this.newValue;
	}
		
		
}