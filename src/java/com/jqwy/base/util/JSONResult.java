package com.jqwy.base.util;

import java.io.Serializable;

public class JSONResult implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2691549365288044136L;
	private String status;
	private Object result;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object object) {
		this.result = object;
	}
}
