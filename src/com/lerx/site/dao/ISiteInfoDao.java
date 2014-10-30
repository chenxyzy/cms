package com.lerx.site.dao;

import com.lerx.site.vo.SiteInfo;

public interface ISiteInfoDao {
	public boolean modify(SiteInfo site);
	public SiteInfo query();
	
}
