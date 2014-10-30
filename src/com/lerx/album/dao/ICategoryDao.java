package com.lerx.album.dao;

import com.lerx.album.vo.Category;

public interface ICategoryDao {

	public Category add(Category ca);
	public Category pass(long cid);
	public Category unpass(long cid);
	public Category delById(long cid);
	
}
