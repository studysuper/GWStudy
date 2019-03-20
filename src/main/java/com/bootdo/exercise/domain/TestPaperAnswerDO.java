package com.bootdo.exercise.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 试题答案表
 * 
 * @author zq
 * @email 519996418
 * @date 2019-03-20 19:46:05
 */
public class TestPaperAnswerDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//
	private String operator;
	//
	private Date createdate;
	//
	private Date modifydate;
	//试题选项
	private String testPaperAnswer;
	//试题题目id
	private String testPaperTopicId;

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
	 * 设置：试题选项
	 */
	public void setTestPaperAnswer(String testPaperAnswer) {
		this.testPaperAnswer = testPaperAnswer;
	}
	/**
	 * 获取：试题选项
	 */
	public String getTestPaperAnswer() {
		return testPaperAnswer;
	}
	/**
	 * 设置：试题题目id
	 */
	public void setTestPaperTopicId(String testPaperTopicId) {
		this.testPaperTopicId = testPaperTopicId;
	}
	/**
	 * 获取：试题题目id
	 */
	public String getTestPaperTopicId() {
		return testPaperTopicId;
	}
}
