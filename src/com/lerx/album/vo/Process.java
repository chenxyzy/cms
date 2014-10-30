package com.lerx.album.vo;

public class Process {
	
	//话题区块
	
	private long id;
	private Album album;
	private String proTitle;		//区块标题
	private String mainImg;			//区块图片
	private String proText;			//区块文字
	private int displayOrder;				//顺序
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public Album getAlbum() {
		return album;
	}
	public void setAlbum(Album album) {
		this.album = album;
	}
	public String getProTitle() {
		return proTitle;
	}
	public void setProTitle(String proTitle) {
		this.proTitle = proTitle;
	}
	public String getMainImg() {
		return mainImg;
	}
	public void setMainImg(String mainImg) {
		this.mainImg = mainImg;
	}
	public String getProText() {
		return proText;
	}
	public void setProText(String proText) {
		this.proText = proText;
	}
	public int getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(int displayOrder) {
		this.displayOrder = displayOrder;
	}
	
	
}
