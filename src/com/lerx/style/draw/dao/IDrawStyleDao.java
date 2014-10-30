package com.lerx.style.draw.dao;

import java.util.List;

import com.lerx.style.draw.vo.DrawStyle;

public interface IDrawStyleDao {
	public int add(DrawStyle style);
	public int imp(DrawStyle style);
	public boolean del(DrawStyle style);
	public boolean delById(Integer id);
	public DrawStyle findById(Integer id);
	public List<DrawStyle> findAll(int mod);
	public boolean findStyleByName(String styleName);
	public boolean setDefault(int id);
	public DrawStyle findDefault();
}
