package com.lerx.integral.rule.vo;

import java.sql.Timestamp;

public class IntegralRule {
	
	private int id;
	private String name;
	private int localPostion;
	private Timestamp createTime;
	private int valueOfAdd;
	private int valueOfDel;
	private int valueOfReg;
	private int valueOfLogin;
	private boolean state;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLocalPostion() {
		return localPostion;
	}
	public void setLocalPostion(int localPostion) {
		this.localPostion = localPostion;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public int getValueOfAdd() {
		return valueOfAdd;
	}
	public void setValueOfAdd(int valueOfAdd) {
		this.valueOfAdd = valueOfAdd;
	}
	public int getValueOfDel() {
		return valueOfDel;
	}
	public void setValueOfDel(int valueOfDel) {
		this.valueOfDel = valueOfDel;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public int getValueOfReg() {
		return valueOfReg;
	}
	public void setValueOfReg(int valueOfReg) {
		this.valueOfReg = valueOfReg;
	}
	public int getValueOfLogin() {
		return valueOfLogin;
	}
	public void setValueOfLogin(int valueOfLogin) {
		this.valueOfLogin = valueOfLogin;
	}
}
