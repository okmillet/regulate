package com.okmillet.regulate.sys.service;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.okmillet.regulate.framework.Page;
import com.okmillet.regulate.framework.VEntity;
import com.okmillet.regulate.sys.model.SysUser;

public interface SysUserService {

	public SysUser getById(Integer id);
	
	public void save(SysUser entity) throws Exception;
	
	public void removeById(Integer id) throws Exception;
	
	public void update(SysUser entity) throws Exception;

	public Page findPage(VEntity p) throws DataAccessException;

	public List<SysUser> find(VEntity p) throws DataAccessException;

	public Number count(VEntity p) throws DataAccessException;

	public SysUser getByUserName(String username);
}
