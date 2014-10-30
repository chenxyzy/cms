package com.lerx.bbs.dao.imp;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lerx.bbs.dao.IBbsInfoDao;
import com.lerx.bbs.vo.BbsInfo;

public class BbsInfoDaoImp extends HibernateDaoSupport implements IBbsInfoDao {

	@Override
	public boolean modify(BbsInfo bi) {
		try {
			bi.setId(1);
			this.getHibernateTemplate().saveOrUpdate(bi);
			return true;
		} catch (RuntimeException re) {

			throw re;
		}
	}

	@Override
	public BbsInfo query() {
		BbsInfo bi;
		bi= (BbsInfo) this.getHibernateTemplate().get(
				"com.lerx.bbs.vo.BbsInfo", 1);
		
		if (bi==null){
			bi = new BbsInfo();
			bi.setId(1);

			modify(bi);
			
		}
		
		return bi;
	}

}
