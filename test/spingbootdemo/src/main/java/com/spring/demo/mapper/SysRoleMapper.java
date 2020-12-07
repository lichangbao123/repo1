package com.spring.demo.mapper;

import java.util.List;

import com.spring.demo.domain.SysRole;
import com.spring.demo.utils.MyMapper;

public interface SysRoleMapper extends MyMapper<SysRole> {

	List<SysRole> getRoleListByUsername(String username);
}