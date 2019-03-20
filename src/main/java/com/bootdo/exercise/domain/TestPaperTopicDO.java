package com.bootdo.exercise.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 试题题目表
 * 
 * @author zq
 * @email 519996418
 * @date 2019-03-20 19:46:06
 */
public class TestPaperTopicDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//
	private String operator;
	//
	private Date createdate;
	//
	private Date modifydate;
	//试题题目
	private String testPaperTopic;
	//试题类型id
	private String testPaperTypeId;

	/**
	 * 设置：
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}
	/**
	 * 获取：
	 */
	public String getOperator() {
		return operator;
	}
	/**
	 * 设置：
	 */
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	/**
	 * 获取：
	 */
	public Date getCreatedate() {
		return createdate;
	}
	/**
	 * 设置：
	 */
	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}
	/**
	 * 获取：
	 */
	public Date getModifydate() {
		return modifydate;
	}
	/**
	 * 设置：试题题目
	 */
	public void setTestPaperTopic(String testPaperTopic) {
		this.testPaperTopic = testPaperTopic;
	}
	/**
	 * 获取：试题题目
	 */
	public String getTestPaperTopic() {
		return testPaperTopic;
	}
	/**
	 * 设置：试题类型id
	 */
	public void setTestPaperTypeId(String testPaperTypeId) {
		this.testPaperTypeId = testPaperTypeId;
	}
	/**
	 * 获取：试题类型id
	 */
	public String getTestPaperTypeId() {
		return testPaperTypeId;
	}
}
