package com.spring.demo.domain;

import javax.persistence.*;

@Table(name = "sys_permission")
public class SysPermission {
    private Integer id;

    private Integer available;

    private String name;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "parent_ids")
    private String parentIds;

    private String permission;

    @Column(name = "resource_type")
    private String resourceType;

    private String url;

    private Byte sort;

    private String icon;

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
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return parent_id
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * @return parent_ids
     */
    public String getParentIds() {
        return parentIds;
    }

    /**
     * @param parentIds
     */
    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    /**
     * @return permission
     */
    public String getPermission() {
        return permission;
    }

    /**
     * @param permission
     */
    public void setPermission(String permission) {
        this.permission = permission;
    }

    /**
     * @return resource_type
     */
    public String getResourceType() {
        return resourceType;
    }

    /**
     * @param resourceType
     */
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return sort
     */
    public Byte getSort() {
        return sort;
    }

    /**
     * @param sort
     */
    public void setSort(Byte sort) {
        this.sort = sort;
    }

    /**
     * @return icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }
}