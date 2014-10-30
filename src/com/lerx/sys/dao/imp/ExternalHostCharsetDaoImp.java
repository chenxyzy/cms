package com.lerx.sys.dao.imp;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lerx.sys.dao.IExternalHostCharsetDao;
import com.lerx.sys.vo.ExternalHostCharset;

public class ExternalHostCharsetDaoImp extends HibernateDaoSupport implements
		IExternalHostCharsetDao {

	@Override
	public int add(ExternalHostCharset ehc) {
		this.getHibernateTemplate().save(ehc);
		return ehc.getId();
	}

	@Override
	public boolean delById(int id) {
		try {
			this.getHibernateTemplate().delete(
					this.getHibernateTemplate().get("com.lerx.sys.vo.ExternalHostCharset",
							id));
			return true;
		} catch (DataAccessException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public ExternalHostCharset modify(ExternalHostCharset ehc) {
		try {
			this.getHibernateTemplate().saveOrUpdate(ehc);
		} catch (DataAccessException e) {
			e.printStackTrace();
			return null;
		}
		return ehc;
	}

	@Override
	public ExternalHostCharset findById(int id) {
		return 
		(ExternalHostCharset) this.getHibernateTemplate().get("com.lerx.sys.vo.ExternalHostCharset",
				id);
	}

	@Override
	public List<ExternalHostCharset> query() {
		List<ExternalHostCharset> list;
		list = this.getHibernateTemplate().loadAll(ExternalHostCharset.class);
		return list;
	}

	@Override
	public ExternalHostCharset findByHostAndPortAndType(String host,int port, int type) {
		String hql = "from ExternalHostCharset e where e.host=? and e.port="+port+" and e.type="+type;
		@SuppressWarnings("unchecked")
		List<ExternalHostCharset> list = this.getHibernateTemplate().find(hql, host.trim());
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public ExternalHostCharset findByHostAndPortAndTypeAndOtherId(String host,int port,
			int type, int id) {
		String hql = "from ExternalHostCharset e where e.host=?  and e.port="+port+" and e.id<>"+id+" and e.type="+type;
		@SuppressWarnings("unchecked")
		List<ExternalHostCharset> list = this.getHibernateTemplate().find(hql, host.trim());
		if (!list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

}
