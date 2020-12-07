package com.spring.demo.service.impl;

import java.util.List;
import java.util.Map;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.spring.demo.domain.SysRole;
import com.spring.demo.domain.UserInfo;
import com.spring.demo.mapper.SysPermissionMapper;
import com.spring.demo.mapper.SysRoleMapper;
import com.spring.demo.mapper.SysRolePermissionMapper;
import com.spring.demo.mapper.SysUserRoleMapper;
import com.spring.demo.mapper.UserInfoMapper;
import com.spring.demo.service.UserInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Copyright: Copyright (c) 2019 liming
 * 
 * @Description: 该类的功能描述
 * @author: ALPHA.GJ.
 * @date: 2019年7月4日 下午4:33:22
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoMapper userMapper;
	@Autowired
	private SysRoleMapper roleMapper;
	@Autowired
	private SysPermissionMapper permissionMapper;
	@Autowired
	private SysRolePermissionMapper rolePermissionMapper;
	@Autowired
	private SysUserRoleMapper userRoleMapper;

	@Override
	@Cacheable(value = "user", key = "#username")//3
	public UserInfo findByUsername(String username) {

		UserInfo user = userMapper.getUserByUsername(username);
		List<SysRole> roleList = roleMapper.getRoleListByUsername(username);
		user.setRoleList(roleList);
		return user;
	}

	 


}
