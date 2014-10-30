package com.lerx.attachment.vo;

import java.sql.Timestamp;

import com.lerx.user.vo.User;

public class Attachment {

	private Long id;
	private long hostId;
	private int hostType;
	private User user;
	private String title;
	private String url;
	private String description;
	private long fileSize;
	private int price;
	private long downloads;
	private boolean media;
	private int mediaType;
	private Timestamp addTime;
	private boolean local;
	private int orderNum;
	private String encryptedParmStr;
	private boolean delTag;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getHostId() {
		return hostId;
	}

	public void setHostId(long hostId) {
		this.hostId = hostId;
	}

	public int getHostType() {
		return hostType;
	}

	public void setHostType(int hostType) {
		this.hostType = hostType;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public long getDownloads() {
		return downloads;
	}

	public void setDownloads(long downloads) {
		this.downloads = downloads;
	}

	public boolean isMedia() {
		return media;
	}

	public void setMedia(boolean media) {
		this.media = media;
	}

	public Timestamp getAddTime() {
		return addTime;
	}

	public void setAddTime(Timestamp addTime) {
		this.addTime = addTime;
	}

	public boolean isLocal() {
		return local;
	}

	public void setLocal(boolean local) {
		this.local = local;
	}

	public int getMediaType() {
		return mediaType;
	}

	public void setMediaType(int mediaType) {
		this.mediaType = mediaType;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}

	public String getEncryptedParmStr() {
		return encryptedParmStr;
	}

	public void setEncryptedParmStr(String encryptedParmStr) {
		this.encryptedParmStr = encryptedParmStr;
	}

	public boolean isDelTag() {
		return delTag;
	}

	public void setDelTag(boolean delTag) {
		this.delTag = delTag;
	}
	
}
