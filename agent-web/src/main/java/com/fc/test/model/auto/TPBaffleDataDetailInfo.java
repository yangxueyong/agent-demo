
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
public class TPBaffleDataDetailInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 主键 **/
	private Long id;

	/** 字典名称 **/
	private Long methodId;

	/** 字典类型 **/
	private String userNo;

	/** 字典类型 **/
	private String inParam;

	/** 字典类型 **/
	private String outParam;

	/** 字典名称 **/
	private Integer status;

	/** 创建人 **/
	private String createUserNo;


	/** 创建时间 **/
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date createTime;

	/** 修改人 **/
	private String maintenanceUserNo;


	/** 修改时间 **/
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date maintenanceTime;

	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMethodId() {
		return methodId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setMethodId(Long methodId) {
		this.methodId = methodId;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getInParam() {
		return inParam;
	}

	public void setInParam(String inParam) {
		this.inParam = inParam;
	}

	public String getOutParam() {
		return outParam;
	}

	public void setOutParam(String outParam) {
		this.outParam = outParam;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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