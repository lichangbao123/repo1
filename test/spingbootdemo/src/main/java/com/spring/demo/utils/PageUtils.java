package com.spring.demo.utils;

import javax.servlet.http.HttpServletRequest;

/**
* Copyright: Copyright (c) 2019 liming
* 
* @ClassName: PageUtils.java
* @Description: 用于layui table 分页
*
* @version: v1.0.0
* @author: ALPHA.GJ.
* @date: 2019年4月3日 下午12:00:47 
 */
public class PageUtils {
	
	private Integer start;
	private Integer page;
	private Integer pageSize;
	
	/**
	 * 
	 */
	public PageUtils(HttpServletRequest request) {
		
		this.page = Integer.parseInt(request.getParameter("page"));
		this.pageSize = Integer.parseInt(request.getParameter("limit"));
		this.start = (page-1) * pageSize;
	}
	/**
	 * @return the start
	 */
	public Integer getStart() {
		return start;
	}
	/**
	 * @param start the start to set
	 */
	public void setStart(Integer start) {
		this.start = start;
	}
	/**
	 * @return the page
	 */
	public Integer getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(Integer page) {
		this.page = page;
	}
	/**
	 * @return the pageSize
	 */
	public Integer getPageSize() {
		return pageSize;
	}
	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}


}
