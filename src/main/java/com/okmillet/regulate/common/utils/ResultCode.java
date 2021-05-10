package com.okmillet.regulate.common.utils;

public class ResultCode {

	public static ResultCode SUCCESS = ResultCode.of(0,"成功"),
			EXCEPTION_OCCURE = ResultCode.of(500,"系统异常");

	private static ResultCode of(int code, String msg) {
		return new ResultCode(code, msg);
	}

	public ResultCode(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	private int code;
	private String msg;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
