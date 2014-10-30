package com.lerx.site.dao.imp;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lerx.site.dao.ISiteInfoDao;
import com.lerx.site.vo.SiteInfo;

public class SiteInfoDaoImp extends HibernateDaoSupport implements ISiteInfoDao {

	@Override
	public boolean modify(SiteInfo site) {
		try {
			site.setId(1);
			this.getHibernateTemplate().saveOrUpdate(site);
			return true;
		} catch (RuntimeException re) {

			throw re;
		}
		
	}

	@Override
	public SiteInfo query() {
		SiteInfo site;
		site= (SiteInfo) this.getHibernateTemplate().get(
				"com.lerx.site.vo.SiteInfo", 1);
		
		if (site==null){
			site = new SiteInfo();
			site.setId(1);
			site.setFullSiteName("default");
			site.setUserRegAllow(true);
			site.setState(true);
			site.setBbsState(true);
			site.setUserLoginAllow(true);
			modify(site);
			
		}
		
		return site;
	}

}
