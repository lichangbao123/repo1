package com.spring.demo.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "apsc_roadmap_content")
public class ApscRoadmapContent {
    private String id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 图线图标题
     */
    private String title;

    /**
     * 课题组ID 关联表 apsc_roadmap_info.id
     */
    @Column(name = "subject_id")
    private String subjectId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 路线图xml文件内容
     */
    @Column(name = "xml_content")
    private String xmlContent;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取用户ID
     *
     * @return user_id - 用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId 用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取图线图标题
     *
     * @return title - 图线图标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置图线图标题
     *
     * @param title 图线图标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取课题组ID 关联表 apsc_roadmap_info.id
     *
     * @return subject_id - 课题组ID 关联表 apsc_roadmap_info.id
     */
    public String getSubjectId() {
        return subjectId;
    }

    /**
     * 设置课题组ID 关联表 apsc_roadmap_info.id
     *
     * @param subjectId 课题组ID 关联表 apsc_roadmap_info.id
     */
    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取路线图xml文件内容
     *
     * @return xml_content - 路线图xml文件内容
     */
    public String getXmlContent() {
        return xmlContent;
    }

    /**
     * 设置路线图xml文件内容
     *
     * @param xmlContent 路线图xml文件内容
     */
    public void setXmlContent(String xmlContent) {
        this.xmlContent = xmlContent;
    }
}