package com.okmillet.regulate.sys.dao;

import org.apache.ibatis.annotations.Mapper;

import com.okmillet.regulate.framework.BaseMapper;
import com.okmillet.regulate.sys.model.SysUser;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser, Integer> {

}
