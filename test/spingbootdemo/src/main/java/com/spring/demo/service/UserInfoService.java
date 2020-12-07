package com.spring.demo.service;

import com.spring.demo.domain.UserInfo;

public interface UserInfoService {

	UserInfo findByUsername(String username);

}
