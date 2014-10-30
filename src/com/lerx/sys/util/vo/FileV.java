package com.lerx.sys.util.vo;

import java.io.File;

public class FileV {
	private File file;
	private String name;
	private String etcType;
	
	private String fileFileName;  // 获取文件名
	private String fileContentType; // 获取文件类型
	
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEtcType() {
		return etcType;
	}
	public void setEtcType(String etcType) {
		this.etcType = etcType;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public String getFileContentType() {
		return fileContentType;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	
}
