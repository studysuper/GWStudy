package com.bootdo.front.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * 前端用户表
 * 
 * @author zq
 * @email 519996418
 * @date 2019-03-20 14:44:59
 */
public class FroUserDO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private String id;
	//
	private String operator;
	//
	private Date createdate;
	//
	private Date modifydate;
	//前端用户编码(系统生成)
	private String userNo;
	//前端用户姓名
	private String userName;
	//密码
	private String password;
	//年龄
	private Integer age;
	//手机号
	private Integer phone;
	//邮箱
	private String email;
	//身份证号
	private String idno;
	//头像
	private String headPortrait;
	//家庭住址
	private String address;

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
	 * 设置：前端用户编码(系统生成)
	 */
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	/**
	 * 获取：前端用户编码(系统生成)
	 */
	public String getUserNo() {
		return userNo;
	}
	/**
	 * 设置：前端用户姓名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：前端用户姓名
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：密码
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置：年龄
	 */
	public void setAge(Integer age) {
		this.age = age;
	}
	/**
	 * 获取：年龄
	 */
	public Integer getAge() {
		return age;
	}
	/**
	 * 设置：手机号
	 */
	public void setPhone(Integer phone) {
		this.phone = phone;
	}
	/**
	 * 获取：手机号
	 */
	public Integer getPhone() {
		return phone;
	}
	/**
	 * 设置：邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 获取：邮箱
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * 设置：身份证号
	 */
	public void setIdno(String idno) {
		this.idno = idno;
	}
	/**
	 * 获取：身份证号
	 */
	public String getIdno() {
		return idno;
	}
	/**
	 * 设置：头像
	 */
	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}
	/**
	 * 获取：头像
	 */
	public String getHeadPortrait() {
		return headPortrait;
	}
	/**
	 * 设置：家庭住址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取：家庭住址
	 */
	public String getAddress() {
		return address;
	}
}
