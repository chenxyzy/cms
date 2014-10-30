package com.lerx.style.qa.dao;

import java.util.List;

import com.lerx.style.qa.vo.QaStyle;

public interface IQaStyleDao {
	public int add(QaStyle style,boolean init);
	public int imp(QaStyle style);
	public boolean del(QaStyle style);
	public boolean delById(Integer id);
	public QaStyle findById(Integer id);
	public List<QaStyle> findAll(int mod);
	public boolean findStyleByName(String styleName);
	public boolean setDefault(int id);
	public QaStyle findDefault();
}
