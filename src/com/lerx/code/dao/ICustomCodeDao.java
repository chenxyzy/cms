package com.lerx.code.dao;

import java.util.List;

import com.lerx.code.vo.CustomCode;

public interface ICustomCodeDao {
	public Long add(CustomCode code);
	public boolean delById(Long id);
	public boolean modify(CustomCode code);
	public CustomCode findById(Long id);
	public CustomCode find(int gid,int mode);
	public List<CustomCode> findByType(int gid);
}
