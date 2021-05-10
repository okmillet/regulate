package com.okmillet.regulate.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.okmillet.regulate.framework.BaseMapper;
import com.okmillet.regulate.framework.BaseService;
import com.okmillet.regulate.sys.dao.SysUserMapper;
import com.okmillet.regulate.sys.model.SysUser;
import com.okmillet.regulate.sys.query.SysUserQuery;
import com.okmillet.regulate.sys.service.SysUserService;

@Service(value="sysUserService")
public class SysUserServiceImpl extends BaseService<SysUser,Integer> implements SysUserService {

	@Autowired
	private SysUserMapper sysUserMapper;

	public BaseMapper<SysUser,Integer> getBaseMapper() {
		return this.sysUserMapper;
	}

	@Override
	public SysUser getByUserName(String username) {
		SysUserQuery q = new SysUserQuery();
		q.setUserName(username);
		List<SysUser> lt = find(q);
		if(lt.size()==1) {
			return lt.get(0);
		}
		return null;
	}
}
