package com.lerx.code.dao;

import java.util.List;

import com.lerx.code.vo.CodeCategory;

public interface ICodeCategoryDao {
	public int add(CodeCategory cc);
	public boolean modify(CodeCategory cc);
	public boolean delById(int id);
	public CodeCategory findById(int id);
	public List<CodeCategory> queryAll();
}
