package com.lerx.user.dao.imp;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lerx.user.dao.IInterconnectionDao;
import com.lerx.user.vo.Interconnection;
import com.lerx.user.vo.User;

public class InterconnectionDaoImp extends HibernateDaoSupport implements
		IInterconnectionDao {

	@Override
	public Interconnection create(User user, int type, String openID) {
		Interconnection ic = findIcByOpenID(openID, type);
		if (ic == null) {
			ic = new Interconnection();
			ic.setCreateTimstamp(new Timestamp(System.currentTimeMillis()));
			ic.setType(type);
			ic.setUser(user);
			ic.setOpenID(openID);
			this.getHibernateTemplate().save(ic);
		}

		return ic;
	}

	@Override
	public User findUserByOpenID(String openID, int type) {

		Interconnection ic = findIcByOpenID(openID, type);

		if (ic != null) {
			return ic.getUser();
		} else {
			return null;
		}
		// TODO Auto-generated method stub

	}

	@Override
	public Interconnection findIcByOpenID(String openID, int type) {
		String hql = "from Interconnection i where i.openID=? and i.type="
				+ type;

		try {
			@SuppressWarnings("unchecked")
			List<Interconnection> list = (List<Interconnection>) this
					.getHibernateTemplate().find(hql, openID);
			if (!list.isEmpty()) {
				Interconnection ic;
				ic = list.get(0);
				return ic;
			} else {
				return null;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			return null;
		}

	}

	@Override
	public boolean clear(User user, int type) {
		String hql = "delete from Interconnection i where i.user.id="
				+ user.getId() + " and i.type=" + type;
		this.getHibernateTemplate().bulkUpdate(hql);
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Interconnection findUserByUid(long uid, int type) {
		String hql = "from Interconnection i where i.user.id=? and i.type="
				+ type;
		
//		System.out.println("hql:"+hql);
		@SuppressWarnings("unchecked")
		
		List<Interconnection> list = (List<Interconnection>) this
				.getHibernateTemplate().find(hql, uid);
		if (!list.isEmpty()) {
			Interconnection ic = list.get(0);
			return ic;
		} else {
			return null;
		}
	}

}
