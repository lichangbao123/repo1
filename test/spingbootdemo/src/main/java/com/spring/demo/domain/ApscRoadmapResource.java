package com.spring.demo.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "apsc_roadmap_resource")
public class ApscRoadmapResource {
    /**
     * 主键
     */
    private Integer id;

    /**
     * 所属用户ID
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 项目名称
     */
    private String name;

    /**
     * 课题ID 对应表 apsc_roadmap_info.id
     */
    @Column(name = "subject_id")
    private String subjectId;

    /**
     * 上级id
     */
    @Column(name = "p_id")
    private String pId;

    /**
     * 类型(1:里程碑，2：子里程碑，3：任务，4：子任务)
     */
    @Column(name = "create_type")
    private Integer createType;

    /**
     * 开始年
     */
    @Column(name = "start_year")
    private String startYear;

    /**
     * 结束年
     */
    @Column(name = "end_year")
    private String endYear;

    /**
     * 2:手工录入 1:导入数据
     */
    private Integer source;

    /**
     * 排序
     */
    @Column(name = "sort_order")
    private Integer sortOrder;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取所属用户ID
     *
     * @return user_id - 所属用户ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置所属用户ID
     *
     * @param userId 所属用户ID
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取项目名称
     *
     * @return name - 项目名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置项目名称
     *
     * @param name 项目名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取课题ID 对应表 apsc_roadmap_info.id
     *
     * @return subject_id - 课题ID 对应表 apsc_roadmap_info.id
     */
    public String getSubjectId() {
        return subjectId;
    }

    /**
     * 设置课题ID 对应表 apsc_roadmap_info.id
     *
     * @param subjectId 课题ID 对应表 apsc_roadmap_info.id
     */
    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * 获取上级id
     *
     * @return p_id - 上级id
     */
    public String getpId() {
        return pId;
    }

    /**
     * 设置上级id
     *
     * @param pId 上级id
     */
    public void setpId(String pId) {
        this.pId = pId;
    }

    /**
     * 获取类型(1:里程碑，2：子里程碑，3：任务，4：子任务)
     *
     * @return create_type - 类型(1:里程碑，2：子里程碑，3：任务，4：子任务)
     */
    public Integer getCreateType() {
        return createType;
    }

    /**
     * 设置类型(1:里程碑，2：子里程碑，3：任务，4：子任务)
     *
     * @param createType 类型(1:里程碑，2：子里程碑，3：任务，4：子任务)
     */
    public void setCreateType(Integer createType) {
        this.createType = createType;
    }

    /**
     * 获取开始年
     *
     * @return start_year - 开始年
     */
    public String getStartYear() {
        return startYear;
    }

    /**
     * 设置开始年
     *
     * @param startYear 开始年
     */
    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    /**
     * 获取结束年
     *
     * @return end_year - 结束年
     */
    public String getEndYear() {
        return endYear;
    }

    /**
     * 设置结束年
     *
     * @param endYear 结束年
     */
    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    /**
     * 获取2:手工录入 1:导入数据
     *
     * @return source - 2:手工录入 1:导入数据
     */
    public Integer getSource() {
        return source;
    }

    /**
     * 设置2:手工录入 1:导入数据
     *
     * @param source 2:手工录入 1:导入数据
     */
    public void setSource(Integer source) {
        this.source = source;
    }

    /**
     * 获取排序
     *
     * @return sort_order - 排序
     */
    public Integer getSortOrder() {
        return sortOrder;
    }

    /**
     * 设置排序
     *
     * @param sortOrder 排序
     */
    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
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
}