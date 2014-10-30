package com.lerx.style.vote.vo;

public class VoteStyle implements Cloneable{
	private Integer id;
	private String title;
	private boolean state;
	private String author;
	private String description;
	private String barMaxValue;
	private VoteStyleSubElementInCommon publicStyle;
	private VoteStyleSubElementInCommon voteStyle;
	private VoteStyleSubElementInCommon itemStyle;
	private VoteStyleSubElementInCommon resultStyle;
	private VoteStyleSubElementInCommon joinStyle;
	
	private String resultPageCode;
	
	private String publicCode1;
	private String publicCode2;
	private String publicCode3;
	private String publicCode4;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getBarMaxValue() {
		return barMaxValue;
	}
	public void setBarMaxValue(String barMaxValue) {
		this.barMaxValue = barMaxValue;
	}
	public VoteStyleSubElementInCommon getPublicStyle() {
		return publicStyle;
	}
	public void setPublicStyle(VoteStyleSubElementInCommon publicStyle) {
		this.publicStyle = publicStyle;
	}
	public VoteStyleSubElementInCommon getVoteStyle() {
		return voteStyle;
	}
	public void setVoteStyle(VoteStyleSubElementInCommon voteStyle) {
		this.voteStyle = voteStyle;
	}
	public VoteStyleSubElementInCommon getItemStyle() {
		return itemStyle;
	}
	public void setItemStyle(VoteStyleSubElementInCommon itemStyle) {
		this.itemStyle = itemStyle;
	}
	public VoteStyleSubElementInCommon getResultStyle() {
		return resultStyle;
	}
	public void setResultStyle(VoteStyleSubElementInCommon resultStyle) {
		this.resultStyle = resultStyle;
	}
	public VoteStyleSubElementInCommon getJoinStyle() {
		return joinStyle;
	}
	public void setJoinStyle(VoteStyleSubElementInCommon joinStyle) {
		this.joinStyle = joinStyle;
	}
	public String getResultPageCode() {
		return resultPageCode;
	}
	public void setResultPageCode(String resultPageCode) {
		this.resultPageCode = resultPageCode;
	}
	public String getPublicCode1() {
		return publicCode1;
	}
	public void setPublicCode1(String publicCode1) {
		this.publicCode1 = publicCode1;
	}
	public String getPublicCode2() {
		return publicCode2;
	}
	public void setPublicCode2(String publicCode2) {
		this.publicCode2 = publicCode2;
	}
	public String getPublicCode3() {
		return publicCode3;
	}
	public void setPublicCode3(String publicCode3) {
		this.publicCode3 = publicCode3;
	}
	public String getPublicCode4() {
		return publicCode4;
	}
	public void setPublicCode4(String publicCode4) {
		this.publicCode4 = publicCode4;
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		Object o = null;
		try {
			o = (VoteStyle) super.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println(e.toString());
		}
		return o;
	}
	
}
