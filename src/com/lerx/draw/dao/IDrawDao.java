package com.lerx.draw.dao;

import java.util.List;

import com.lerx.draw.vo.Draw;

public interface IDrawDao {
	public int add(Draw draw);
	public boolean delById(int id);
	public boolean modify(Draw draw);
	public Draw findById(int id);
	public boolean setState(int id,boolean state);
	public List<Draw> findAllDraw(int mod);
}
