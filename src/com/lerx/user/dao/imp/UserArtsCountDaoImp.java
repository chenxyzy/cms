package com.lerx.user.dao.imp;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lerx.sys.util.HibernateCallbackUtil;
import com.lerx.sys.util.vo.HibernateCallbackUtilVo;
import com.lerx.sys.util.vo.Rs;
import com.lerx.user.dao.IUserArtsCountDao;
import com.lerx.user.vo.User;
import com.lerx.user.vo.UserArtsCount;

public class UserArtsCountDaoImp extends HibernateDaoSupport implements IUserArtsCountDao {

	@Override
	public boolean modify(UserArtsCount uac) {
		try {
			this.getHibernateTemplate().saveOrUpdate(uac);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public UserArtsCount findByUK(User user, int timeKey) {
		String hql = "from UserArtsCount u where u.user.id="+user.getId()+" and u.timeKey=?";
		@SuppressWarnings("unchecked")
		List<UserArtsCount> list = (List<UserArtsCount>)this.getHibernateTemplate().find(hql, timeKey);

		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public Rs findTopByUKAndGroup(int groupId, int timeKey, int mod,int page,
			int pageSize) {

		String hql;
		long count;
		String order;
		if (mod == 0) {
			order = " order by u.articleThreadsPassed desc";
		} else {
			order = " order by u.articleThreadsCount desc";
		}

		if (groupId > 0) {
			hql = "from UserArtsCount u where u.user.userGroup.id=" + groupId  + "and u.timeKey="+timeKey+ order;
		} else {
			hql = "from UserArtsCount u where u.timeKey="+timeKey+ order;
		}

		count = (Long) this.getHibernateTemplate()
				.find("select count(*) " + hql).get(0);

//		Rs rs = RsInit.rsInit(page, pageSize, count);

		HibernateCallbackUtilVo hcuv = new HibernateCallbackUtilVo();
		hcuv.setHql(hql);
		hcuv.setIbernateTemplate(this.getHibernateTemplate());
		hcuv.setPageSize(pageSize);
		hcuv.setCount(count);
		hcuv.setPage(page);
//		hcuv.setFirstResult(firstResult);
		Rs rs =  HibernateCallbackUtil.exeRs(hcuv);
		
//		HibernateCallback hc = new HibernateCallback() {
//
//			@SuppressWarnings("unchecked")
//			@Override
//			public Object doInHibernate(org.hibernate.Session session)
//					throws HibernateException, SQLException {
//
//				Query query = session.createQuery(hql);
//				query.setFirstResult((page - 1) * pageSize);
//				query.setMaxResults(pageSize);
//
//				List<UserInf> list = query.list();
//
//				if (list.isEmpty()) {
//					// System.out.println("找不到记录");
//				}
//				// System.out.println("测试点5");
//				return list;
//			}
//
//		};
//		rs.setList(getHibernateTemplate().executeFind(hc));
		return rs;
	
	}

}
