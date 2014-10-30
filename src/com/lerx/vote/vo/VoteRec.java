package com.lerx.vote.vo;

import java.sql.Timestamp;

import com.lerx.user.vo.User;

public class VoteRec {
	
	private long id;
	private VoteSubject sub;
	private User user;
	private String name;
	private String addIp;
	private Timestamp addTime;
	private String identity;
	private String address;
	private String phone;
	private String occ;
	private String itemsRec;
	private String mes;
	private boolean mesState;
	private int recCount;
	private String email;
	private boolean state;
	private String salt;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public VoteSubject getSub() {
		return sub;
	}
	public void setSub(VoteSubject sub) {
		this.sub = sub;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddIp() {
		return addIp;
	}
	public void setAddIp(String addIp) {
		this.addIp = addIp;
	}
	public Timestamp getAddTime() {
		return addTime;
	}
	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getOcc() {
		return occ;
	}
	public void setOcc(String occ) {
		this.occ = occ;
	}
	public String getItemsRec() {
		return itemsRec;
	}
	public void setItemsRec(String itemsRec) {
		this.itemsRec = itemsRec;
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public boolean isMesState() {
		return mesState;
	}
	public void setMesState(boolean mesState) {
		this.mesState = mesState;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public int getRecCount() {
		return recCount;
	}
	public void setRecCount(int recCount) {
		this.recCount = recCount;
	}
	
	
}
