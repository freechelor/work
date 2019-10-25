package com.tuya.smart.model;

import org.apache.commons.lang.builder.ToStringBuilder;

public class ResponseMessage {
	private boolean isSuccess;
	private String errorCode;
	private String errorMsg;
	private Object result;

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public boolean isSuccess() {
		return this.isSuccess;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return this.errorCode;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getErrorMsg() {
		return this.errorMsg;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public Object getResult() {
		return this.result;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
