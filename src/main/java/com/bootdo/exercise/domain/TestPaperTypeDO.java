package com.bootdo.exercise.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 试卷表
 * 
 * @author zq
 * @email 519996418
 * @date 2019-03-20 19:46:06
 */
public class TestPaperTypeDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//
	private String operator;
	//
	private Date createdate;
	//
	private Date modifydate;
	//试卷题目
	private String exerciseTitle;
	//试题类型(01选择题（单选），02填空题，03判断题，04主观题)
	private String exerciseType;

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
	 * 设置：试卷题目
	 */
	public void setExerciseTitle(String exerciseTitle) {
		this.exerciseTitle = exerciseTitle;
	}
	/**
	 * 获取：试卷题目
	 */
	public String getExerciseTitle() {
		return exerciseTitle;
	}
	/**
	 * 设置：试题类型(01选择题（单选），02填空题，03判断题，04主观题)
	 */
	public void setExerciseType(String exerciseType) {
		this.exerciseType = exerciseType;
	}
	/**
	 * 获取：试题类型(01选择题（单选），02填空题，03判断题，04主观题)
	 */
	public String getExerciseType() {
		return exerciseType;
	}
}
