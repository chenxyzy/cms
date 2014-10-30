package com.lerx.draw.vo;

import java.sql.Timestamp;

import com.lerx.style.draw.vo.DrawStyle;

public class Draw {
	
	private int id;
	private boolean state;
	private String title;
	private String votesRange;
	private int resultNum;
	private String resultRecStr;
	private String exceptedRecStr;
	private String password;
	private Timestamp drawStartTime;
	private Timestamp drawEndTime;
	
	private DrawStyle ds;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getVotesRange() {
		return votesRange;
	}
	public void setVotesRange(String votesRange) {
		this.votesRange = votesRange;
	}
	public int getResultNum() {
		return resultNum;
	}
	public void setResultNum(int resultNum) {
		this.resultNum = resultNum;
	}
	public String getResultRecStr() {
		return resultRecStr;
	}
	public void setResultRecStr(String resultRecStr) {
		this.resultRecStr = resultRecStr;
	}
	public String getExceptedRecStr() {
		return exceptedRecStr;
	}
	public void setExceptedRecStr(String exceptedRecStr) {
		this.exceptedRecStr = exceptedRecStr;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Timestamp getDrawStartTime() {
		return drawStartTime;
	}
	public void setDrawStartTime(Timestamp drawStartTime) {
		this.drawStartTime = drawStartTime;
	}
	public Timestamp getDrawEndTime() {
		return drawEndTime;
	}
	public void setDrawEndTime(Timestamp drawEndTime) {
		this.drawEndTime = drawEndTime;
	}
	public DrawStyle getDs() {
		return ds;
	}
	public void setDs(DrawStyle ds) {
		this.ds = ds;
	}
}
