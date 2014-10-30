package com.lerx.style.bbs.dao;

import java.util.List;

import com.lerx.style.bbs.vo.BbsStyle;

public interface IBbsStyleDao {
	public boolean add(BbsStyle style);
	public boolean addStyle(String styleName,String author,String description);
	public boolean modify(BbsStyle style);
	public boolean del(BbsStyle style);
	public boolean delById(Integer id);
	public BbsStyle findById(Integer id);
	public List<BbsStyle> findAll(int mod);
	public boolean changeState(int id,boolean state);
	public boolean findStyleByName(String styleName);
	public boolean setDef(int id);
	public BbsStyle findDef();
	
}
