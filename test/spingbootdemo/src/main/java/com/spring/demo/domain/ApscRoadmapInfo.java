package com.spring.demo.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "apsc_roadmap_info")
public class ApscRoadmapInfo {
    private String id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * 路线图名称
     */
    private String name;

    /**
     * 承担人
     */
    @Column(name = "undertake_person")
    private String undertakePerson;

    /**
     * 承担单位
     */
    @Column(name = "undertake_unit")
    private String undertakeUnit;

    /**
     * 开题开始日期
     */
    @Column(name = "start_date")
    private Date startDate;

    /**
     * 开题结束日期
     */
    @Column(name = "end_date")
    private Date endDate;

    /**
     * 经费
     */
    private BigDecimal funding;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 是否分享 0否 1是
     */
    @Column(name = "is_share")
    private Integer isShare;

    /**
     * a4纸 纵向-0；横向-1；a3纸 纵向-2；横向-3；
     */
    @Column(name = "a4_flag")
    private String a4Flag;

    /**
     * 提交标志:0未开始,1进行中,2已提交
     */
    private String submitflag;

    /**
     * 课题介绍
     */
    @Column(name = "subject_introduce")
    private String subjectIntroduce;

    /**
     * 立项分析
     */
    @Column(name = "project_analysis")
    private String projectAnalysis;

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
     * 获取路线图名称
     *
     * @return name - 路线图名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置路线图名称
     *
     * @param name 路线图名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取承担人
     *
     * @return undertake_person - 承担人
     */
    public String getUndertakePerson() {
        return undertakePerson;
    }

    /**
     * 设置承担人
     *
     * @param undertakePerson 承担人
     */
    public void setUndertakePerson(String undertakePerson) {
        this.undertakePerson = undertakePerson;
    }

    /**
     * 获取承担单位
     *
     * @return undertake_unit - 承担单位
     */
    public String getUndertakeUnit() {
        return undertakeUnit;
    }

    /**
     * 设置承担单位
     *
     * @param undertakeUnit 承担单位
     */
    public void setUndertakeUnit(String undertakeUnit) {
        this.undertakeUnit = undertakeUnit;
    }

    /**
     * 获取开题开始日期
     *
     * @return start_date - 开题开始日期
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 设置开题开始日期
     *
     * @param startDate 开题开始日期
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 获取开题结束日期
     *
     * @return end_date - 开题结束日期
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 设置开题结束日期
     *
     * @param endDate 开题结束日期
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 获取经费
     *
     * @return funding - 经费
     */
    public BigDecimal getFunding() {
        return funding;
    }

    /**
     * 设置经费
     *
     * @param funding 经费
     */
    public void setFunding(BigDecimal funding) {
        this.funding = funding;
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
     * 获取是否分享 0否 1是
     *
     * @return is_share - 是否分享 0否 1是
     */
    public Integer getIsShare() {
        return isShare;
    }

    /**
     * 设置是否分享 0否 1是
     *
     * @param isShare 是否分享 0否 1是
     */
    public void setIsShare(Integer isShare) {
        this.isShare = isShare;
    }

    /**
     * 获取a4纸 纵向-0；横向-1；a3纸 纵向-2；横向-3；
     *
     * @return a4_flag - a4纸 纵向-0；横向-1；a3纸 纵向-2；横向-3；
     */
    public String getA4Flag() {
        return a4Flag;
    }

    /**
     * 设置a4纸 纵向-0；横向-1；a3纸 纵向-2；横向-3；
     *
     * @param a4Flag a4纸 纵向-0；横向-1；a3纸 纵向-2；横向-3；
     */
    public void setA4Flag(String a4Flag) {
        this.a4Flag = a4Flag;
    }

    /**
     * 获取提交标志:0未开始,1进行中,2已提交
     *
     * @return submitflag - 提交标志:0未开始,1进行中,2已提交
     */
    public String getSubmitflag() {
        return submitflag;
    }

    /**
     * 设置提交标志:0未开始,1进行中,2已提交
     *
     * @param submitflag 提交标志:0未开始,1进行中,2已提交
     */
    public void setSubmitflag(String submitflag) {
        this.submitflag = submitflag;
    }

    /**
     * 获取课题介绍
     *
     * @return subject_introduce - 课题介绍
     */
    public String getSubjectIntroduce() {
        return subjectIntroduce;
    }

    /**
     * 设置课题介绍
     *
     * @param subjectIntroduce 课题介绍
     */
    public void setSubjectIntroduce(String subjectIntroduce) {
        this.subjectIntroduce = subjectIntroduce;
    }

    /**
     * 获取立项分析
     *
     * @return project_analysis - 立项分析
     */
    public String getProjectAnalysis() {
        return projectAnalysis;
    }

    /**
     * 设置立项分析
     *
     * @param projectAnalysis 立项分析
     */
    public void setProjectAnalysis(String projectAnalysis) {
        this.projectAnalysis = projectAnalysis;
    }
}