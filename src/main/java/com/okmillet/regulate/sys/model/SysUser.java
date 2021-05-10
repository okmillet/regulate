package com.okmillet.regulate.sys.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.okmillet.regulate.common.utils.DateUtils;

/**
 * 系统用户
 */
public class SysUser implements java.io.Serializable{
	private static final long serialVersionUID = 6563771620628182283L;
	
	private Integer id;//ID
	private String userName;//用户名
	private String userPwd;//密码
	private Integer createTime;//创建时间

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

	public void setCreateTime(Integer value) {
		this.createTime = value;
	}

	@JSONField(serialize=false)
	public Integer getCreateTime() {
		return this.createTime;
	}

	public String getCreateTimeStr() {
		return DateUtils.intTS(this.createTime);
	}

	public String toStr() {
		StringBuilder str = new StringBuilder();
		if (this.id!=null) {
			str.append("ID:"+getId()+";");
		}
		if (this.userName!=null) {
			str.append("用户名:"+getUserName()+";");
		}
		if (this.userPwd!=null) {
			str.append("密码:"+getUserPwd()+";");
		}
		if (this.createTime!=null) {
			str.append("创建时间:"+getCreateTimeStr()+";");
		}
		return str.toString();
	}
}