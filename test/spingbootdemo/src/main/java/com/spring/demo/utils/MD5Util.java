package com.spring.demo.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.spring.demo.domain.UserInfo;

import java.util.Random;

/**
 * Copyright: Copyright (c) 2019 liming
 * 
 * @Description: 该类的功能描述
 * @author: ALPHA.GJ.
 * @date: 2019年7月4日 上午10:53:53
 */
public class MD5Util {

	public static final String md5(String password, String salt) {
		// 加密方式
		String hashAlgorithmName = "MD5";
		// 盐：为了即使相同的密码不同的盐加密后的结果也不同
		ByteSource byteSalt = ByteSource.Util.bytes(salt);
		// 密码
		Object source = password;
		// 加密次数
		int hashIterations = 2;
		SimpleHash result = new SimpleHash(hashAlgorithmName, source, byteSalt, hashIterations);
		return result.toString();
	}

	public static final String md5(String salt) {
		// 加密方式
		String hashAlgorithmName = "MD5";
		// 密码
		Object password = salt;

		SimpleHash result = new SimpleHash(hashAlgorithmName, password);
		return result.toString();
	}

	public static void main(String[] args) {
//		Random random = new Random();
//		String salt = String.valueOf(random.nextLong());
//		System.out.println(salt);
//		String s = md5(salt);
//		System.out.println(s);
//		String str = md5("123456", "test"+salt);
//		System.out.print(str);
		
		/**
		 * 根据提供的用户名和密码   生成  加密密码（2次md5）
		 */
		Random random = new Random();
        long saltL = random.nextLong();
        //使用md5加密 将随机数转为32位长度的字符串
        String salt = MD5Util.md5(String.valueOf(saltL));
        UserInfo user=new UserInfo();
        user.setSalt(salt);
        user.setUsername("bb");
        user.setPassword("111");
        System.out.println("加密盐："+salt);
        System.out.println("密码："+user.getPassword());
        System.out.println("用户名："+user.getUsername());
        System.out.println("用户名+加密盐："+user.getCredentialsSalt());
        //使用MD5二次加密算法生成密码
        String password = MD5Util.md5(user.getPassword(), user.getCredentialsSalt());
        System.out.println("加密密码："+password);
	}
}
