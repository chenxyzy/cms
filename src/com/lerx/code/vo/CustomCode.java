package com.lerx.code.vo;

public class CustomCode {
	private Long id;
	private String name;
	private String description;
	private String code;
	private CodeCategory cc;
	private boolean state;
	private boolean current;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public CodeCategory getCc() {
		return cc;
	}
	public void setCc(CodeCategory cc) {
		this.cc = cc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public boolean isCurrent() {
		return current;
	}
	public void setCurrent(boolean current) {
		this.current = current;
	}
	
}
