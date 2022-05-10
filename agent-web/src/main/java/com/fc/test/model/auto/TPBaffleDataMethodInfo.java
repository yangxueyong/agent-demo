
package com.fc.test.model.auto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 字典类型表
 * 
 * @author 一休
 * @email 438081243@qq.com
 * @date 2019-09-05 12:34:25
 */
public class TPBaffleDataMethodInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 主键 **/
	private Long id;

	/** 字典名称 **/
	private String methodName;

	/** 字典类型 **/
	private String systemCode;

	private String methodChinaName;

	/** 创建人 **/
	private String createUserNo;

	private Integer status;


	/** 创建时间 **/
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date createTime;

	/** 修改人 **/
	private String maintenanceUserNo;


	/** 修改时间 **/
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date maintenanceTime;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getMethodChinaName() {
		return methodChinaName;
	}

	public void setMethodChinaName(String methodChinaName) {
		this.methodChinaName = methodChinaName;
	}

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	public String getCreateUserNo() {
		return createUserNo;
	}

	public void setCreateUserNo(String createUserNo) {
		this.createUserNo = createUserNo;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getMaintenanceUserNo() {
		return maintenanceUserNo;
	}

	public void setMaintenanceUserNo(String maintenanceUserNo) {
		this.maintenanceUserNo = maintenanceUserNo;
	}

	public Date getMaintenanceTime() {
		return maintenanceTime;
	}

	public void setMaintenanceTime(Date maintenanceTime) {
		this.maintenanceTime = maintenanceTime;
	}
}