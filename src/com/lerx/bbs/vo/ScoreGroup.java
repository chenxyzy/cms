package com.lerx.bbs.vo;

public class ScoreGroup {
	private Integer id;
	private ScoreScheme scheme;
	private String groupName;
	private int valueLowerLimit;
	private int valueUpperLimit;
	private String powerMask;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ScoreScheme getScheme() {
		return scheme;
	}
	public void setScheme(ScoreScheme scheme) {
		this.scheme = scheme;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public int getValueLowerLimit() {
		return valueLowerLimit;
	}
	public void setValueLowerLimit(int valueLowerLimit) {
		this.valueLowerLimit = valueLowerLimit;
	}
	public int getValueUpperLimit() {
		return valueUpperLimit;
	}
	public void setValueUpperLimit(int valueUpperLimit) {
		this.valueUpperLimit = valueUpperLimit;
	}
	public String getPowerMask() {
		return powerMask;
	}
	public void setPowerMask(String powerMask) {
		this.powerMask = powerMask;
	}
	
}
