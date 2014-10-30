package com.lerx.poll.vo;

import java.sql.Timestamp;

import com.lerx.user.vo.User;


/*
 * 点赞记录
 * （未使用）
 */
public class Poll {
	private long id;
	private long tid;
	private int pos;
	private User user;
	private Timestamp pollTime;
	private int standPoint;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getTid() {
		return tid;
	}
	public void setTid(long tid) {
		this.tid = tid;
	}
	public int getPos() {
		return pos;
	}
	public void setPos(int pos) {
		this.pos = pos;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Timestamp getPollTime() {
		return pollTime;
	}
	public void setPollTime(Timestamp pollTime) {
		this.pollTime = pollTime;
	}
	public int getStandPoint() {
		return standPoint;
	}
	public void setStandPoint(int standPoint) {
		this.standPoint = standPoint;
	}
	
	
}
