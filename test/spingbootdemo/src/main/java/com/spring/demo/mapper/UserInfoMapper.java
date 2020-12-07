package com.spring.demo.mapper;

import com.spring.demo.domain.UserInfo;
import com.spring.demo.utils.MyMapper;

public interface UserInfoMapper extends MyMapper<UserInfo> {

	UserInfo getUserByUsername(String username);
}