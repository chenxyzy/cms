package com.lerx.user.dao;

import com.lerx.user.vo.Integral;

public interface IIntegralDao {
	public long add(Integral integral);
	public boolean delById(long id);
	public boolean delByUid(long uid);
	public long find(Integral integral);
	public Integral findById(long id);
}
