package com.lerx.album.dao.imp;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lerx.album.dao.ICategoryDao;
import com.lerx.album.vo.Category;

public class CategoryDaoImp extends HibernateDaoSupport implements ICategoryDao {

	@Override
	public Category add(Category ca) {
		this.getHibernateTemplate().saveOrUpdate(ca);
		return ca;
	}

	@Override
	public Category pass(long cid) {
		Category ca=(Category) this.getHibernateTemplate().get("com.lerx.album.vo.Category", cid);
		ca.setState(true);
		this.getHibernateTemplate().saveOrUpdate(ca);
		// TODO Auto-generated method stub
		return ca;
	}

	@Override
	public Category unpass(long cid) {
		Category ca=(Category) this.getHibernateTemplate().get("com.lerx.album.vo.Category", cid);
		ca.setState(false);
		this.getHibernateTemplate().saveOrUpdate(ca);
		return ca;
	}

	@Override
	public Category delById(long cid) {
		this.getHibernateTemplate().delete(this.getHibernateTemplate().get("com.lerx.album.vo.Category", cid));
				
		// TODO Auto-generated method stub
		return null;
	}

}
