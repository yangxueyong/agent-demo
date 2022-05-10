
package com.fc.test.model.auto;

import java.io.Serializable;

/**
 * 字典类型表
 * 
 * @author 一休
 * @email 438081243@qq.com
 * @date 2019-09-05 12:34:25
 */
public class TPBaffleDataDetailInfoExample implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 字典类型 **/
	private Long methodId;

	private String name;

	public Long getMethodId() {
		return methodId;
	}

	public void setMethodId(Long methodId) {
		this.methodId = methodId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}