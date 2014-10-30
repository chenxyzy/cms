package com.lerx.qa.dao;

import java.util.List;

import com.lerx.qa.model.QaNavShowModel;
import com.lerx.qa.vo.QaNav;

public interface IQaNavDao {
	public long add(QaNav qn);
	public boolean del(QaNav qn);
	public boolean delById(int id);
	public boolean modify(QaNav qn);
	public boolean modify(QaNav qn,int mod);
	public QaNav findById(int id);
	public boolean setState(int id,boolean state);
	public List<QaNav> findAllNav(int mod);
	public List<QaNav> findByParent(int parentId,int mod);
	public List<QaNav> findAllClassNav(int mod);
	public boolean sortDisplayOrder(int parentNavId);
	public boolean swapQaNav(QaNav s,QaNav t);
	List<QaNavShowModel> findAllQaNavModel(String prefix,String filler);
	
}
