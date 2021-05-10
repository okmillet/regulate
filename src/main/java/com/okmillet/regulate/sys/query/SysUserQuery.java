package com.okmillet.regulate.sys.query;

import com.okmillet.regulate.common.utils.DateUtils;
import com.okmillet.regulate.framework.VEntity;

public class SysUserQuery extends VEntity implements java.io.Serializable {
	private static final long serialVersionUID = 20210510142942370L;

	private Integer id;// ID
	private String userName;// 用户名
	private String userPwd;// 密码
	private Integer createTimeBegin;// 开始创建时间
	private Integer createTimeEnd;// 截至创建时间

	public void setId(Integer value) {
		this.id = value;
	}

	public Integer getId() {
		return this.id;
	}

	public void setUserName(String value) {
		this.userName = value;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserPwd(String value) {
		this.userPwd = value;
	}

	public String getUserPwd() {
		return this.userPwd;
	}

	public void setCreateTimeBegin(String value) {
		this.createTimeBegin = DateUtils.sTInt(value);
	}

	public Integer getCreateTimeBegin() {
		return this.createTimeBegin;
	}

	public void setCreateTimeEnd(String value) {
		this.createTimeEnd = DateUtils.sTInt(value);
	}

	public Integer getCreateTimeEnd() {
		return this.createTimeEnd;
	}

}