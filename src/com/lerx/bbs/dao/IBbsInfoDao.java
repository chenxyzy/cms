package com.lerx.bbs.dao;

import com.lerx.bbs.vo.BbsInfo;

public interface IBbsInfoDao {
	public boolean modify(BbsInfo bi);
	public BbsInfo query();
	
}
