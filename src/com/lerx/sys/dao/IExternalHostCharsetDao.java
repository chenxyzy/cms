package com.lerx.sys.dao;

import java.util.List;

import com.lerx.sys.vo.ExternalHostCharset;

public interface IExternalHostCharsetDao {
	public int add(ExternalHostCharset ehc);
	public boolean delById(int id);
	public ExternalHostCharset modify(ExternalHostCharset ehc);
	public ExternalHostCharset findById(int id);
	public List<ExternalHostCharset> query();
	public ExternalHostCharset findByHostAndPortAndType(String host,int port,int type);
	public ExternalHostCharset findByHostAndPortAndTypeAndOtherId(String host,int port,int type,int id);
}
