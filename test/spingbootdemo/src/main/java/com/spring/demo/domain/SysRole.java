package com.spring.demo.domain;

import java.util.List;
import javax.persistence.*;

@Table(name = "sys_role")
public class SysRole {
    private Integer id;

    private Integer available;

    private String description;

    private String role;
    
    private List<SysPermission> permissions;
    
    private List<UserInfo> userInfos;// 一个角色对应多个用户

    public List<SysPermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<SysPermission> permissions) {
		this.permissions = permissions;
	}

	public List<UserInfo> getUserInfos() {
		return userInfos;
	}

	public void setUserInfos(List<UserInfo> userInfos) {
		this.userInfos = userInfos;
	}

	/**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return available
     */
    public Integer getAvailable() {
        return available;
    }

    /**
     * @param available
     */
    public void setAvailable(Integer available) {
        this.available = available;
    }

    /**
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role
     */
    public void setRole(String role) {
        this.role = role;
    }
}