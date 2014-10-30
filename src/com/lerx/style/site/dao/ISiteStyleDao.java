package com.lerx.style.site.dao;

import java.util.List;

import com.lerx.style.site.vo.SiteStyle;

public interface ISiteStyleDao {
	public boolean addStyle(String styleName,String author,String description);
	public boolean findStyleByName(String styleName);
	public SiteStyle findStyleById(int id);
	public boolean delStyleById(int id);
	public List<SiteStyle> findAll(int state);
	public boolean changeState(int id,boolean state);
	public boolean add(SiteStyle style);
	public boolean setDef(int id);
	public SiteStyle findDef();
	
}
