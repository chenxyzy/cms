package com.lerx.user.dao.imp;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lerx.user.dao.IIntegralDao;
import com.lerx.user.vo.Integral;

public class IntegralDaoImp extends HibernateDaoSupport implements IIntegralDao {

	@Override
	public long add(Integral integral) {
		if (find(integral) == 0) {
			this.getHibernateTemplate().save(integral);
			return integral.getId();
		} else {
			return 0;
		}
	}

	@Override
	public boolean delById(long id) {
		try {
			this.getHibernateTemplate().delete(
					this.getHibernateTemplate().get(
							"com.lerx.user.vo.Integral", id));
			return true;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delByUid(long uid) {
		try {
			String hql="delete from Integral i where i.user.id="+uid;
			this.getHibernateTemplate().bulkUpdate(hql);
			return true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public long find(Integral integral) {
		String hql = "select i.id from Integral i where i.user.id="
				+ integral.getUser().getId() + " and i.type=" + integral.getType()
				+ " and i.tagId=" + integral.getTagId();
		@SuppressWarnings("unchecked")
		List<Long> list =  this.getHibernateTemplate().find(hql);
		if (list.isEmpty()) {
			return 0;
		} else {
			return list.get(0);
		}

	}

	@Override
	public Integral findById(long id) {
		// TODO Auto-generated method stub
		return (Integral) this.getHibernateTemplate().get(
				"com.lerx.user.vo.Integral", id);
	}

}
