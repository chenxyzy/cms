package com.lerx.qa.dao.imp;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lerx.qa.dao.IQaItemDao;
import com.lerx.qa.vo.QaItem;
import com.lerx.qa.vo.QaNav;
import com.lerx.sys.util.HibernateCallbackUtil;
import com.lerx.sys.util.vo.HibernateCallbackUtilVo;
import com.lerx.sys.util.vo.Rs;

public class QaItemDaoImp extends HibernateDaoSupport implements IQaItemDao {

	@Override
	public long add(QaItem qi) {
		this.getHibernateTemplate().save(qi);
		return qi.getId();
	}

	@Override
	public boolean del(QaItem qi) {
		this.getHibernateTemplate().delete(qi);
		return true;
	}

	@Override
	public boolean delById(long qid) {
		this.getHibernateTemplate().delete(
				this.getHibernateTemplate().get(QaItem.class, qid));
		return true;
	}

	@Override
	public boolean modify(QaItem qi) {
		try {
			this.getHibernateTemplate().saveOrUpdate(qi);
			return true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			return false;
		}
		
	}

	@Override
	public QaItem findById(long qid) {
		
		return (QaItem) this.getHibernateTemplate().get(
				QaItem.class, qid);
	}

	@Override
	public Rs findQaItemsByGroupAndMod(int navId, int page,
			int pageSize, int mod, int state,int open, int firstResult) {
		long count;
		String orderStr, hqlTmp;
		if (navId == 0) {
			hqlTmp = "";
		} else {
			QaNav qn = (QaNav) this.getHibernateTemplate().get(
					QaNav.class, navId);
			if (qn == null) {
				return null;
			} else {
				if (qn.getParentNav()==null){
					hqlTmp = " where q.qn.parentNav.id=" + qn.getId();
				}else{
					hqlTmp = " where q.qn.id=" + qn.getId();
				}
				
			}
		}
		switch (state) {
		case 1:
			if (hqlTmp.equals("")) {
				hqlTmp += " where q.state=true ";
			} else {
				hqlTmp += " and q.state=true ";
			}
			break;
		case 2:
			if (hqlTmp.equals("")) {
				hqlTmp += " where q.state=false ";
			} else {
				hqlTmp += " and q.state=false ";
			}
			break;
		default:
			break;
		}
		
		switch (open) {
		case 1:
			if (hqlTmp.equals("")) {
				hqlTmp += " where q.open=true ";
			} else {
				hqlTmp += " and q.open=true ";
			}
			break;
		case 2:
			if (hqlTmp.equals("")) {
				hqlTmp += " where q.open=false ";
			} else {
				hqlTmp += " and q.open=false ";
			}
			break;
		default:
			break;
		}
		
		switch (mod) {
		case 1:
			orderStr = " order by q.views desc ";
			break;
		case 2:
			orderStr = " order by (q.views/(TO_DAYS(NOW())-(TO_DAYS(q.addTime)-1))) desc ";
			break;
		default:
			orderStr = " order by q.addTime desc ";
			break;
		}
//		System.out.println("hql:"+hqlTmp);
		count = (Long) this.getHibernateTemplate()
				.find("select count(*) from QaItem q " + hqlTmp).get(0);
//		System.out.println("count:"+count);
		String hql = "select q.id from QaItem q " + hqlTmp + orderStr;
		
		HibernateCallbackUtilVo hcuv = new HibernateCallbackUtilVo();
		hcuv.setHql(hql);
		hcuv.setIbernateTemplate(this.getHibernateTemplate());
		hcuv.setPageSize(pageSize);
		hcuv.setCount(count);
		hcuv.setPage(page);
		hcuv.setFirstResult(firstResult);
		Rs rs =  HibernateCallbackUtil.exeRs(hcuv);
		
//		HibernateCallback hc = new HibernateCallback() {
//
//			@Override
//			public Object doInHibernate(org.hibernate.Session session)
//					throws HibernateException, SQLException {
//				int n = pageSize;
//				if (n == 0) {
//					n = 10;
//				}
//				Query query = session.createQuery(hql);
//				if (firstResult == 0) {
//					query.setFirstResult((page - 1) * pageSize);
//				} else {
//					query.setFirstResult(firstResult - 1);
//				}
//
//				query.setMaxResults(n);
//
//				@SuppressWarnings("unchecked")
//				List<Long> list = query.list();
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

	
	@Override
	public Rs findQaItemsByParentAndMod(int navId, int page,
			int pageSize, int mod, int state, int firstResult) {
		long count;
		String orderStr, hqlTmp;
		if (navId == 0) {
			hqlTmp = "";
		} else {
			QaNav qn = (QaNav) this.getHibernateTemplate().get(
					QaNav.class, navId);
			if (qn == null) {
				return null;
			} else {
				hqlTmp = " where q.qn.parentNav.id=" + qn.getId();
			}
		}
		switch (state) {
		case 1:
			if (hqlTmp.equals("")) {
				hqlTmp += " where q.state=true ";
			} else {
				hqlTmp += " and q.state=true ";
			}
			break;
		case 2:
			if (hqlTmp.equals("")) {
				hqlTmp += " where q.state=false ";
			} else {
				hqlTmp += " and q.state=false ";
			}
			break;
		default:
			break;
		}
		switch (mod) {
		case 1:
			orderStr = " order by q.views desc ";
			break;
		case 2:
			orderStr = " order by (q.views/(TO_DAYS(NOW())-(TO_DAYS(q.addTime)-1))) desc ";
			break;
		default:
			orderStr = " order by q.addTime desc ";
			break;
		}

		count = (Long) this.getHibernateTemplate()
				.find("select count(*) from QaItem q " + hqlTmp).get(0);

		String hql = "select q.id from QaItem q " + hqlTmp + orderStr;
		
		HibernateCallbackUtilVo hcuv = new HibernateCallbackUtilVo();
		hcuv.setHql(hql);
		hcuv.setIbernateTemplate(this.getHibernateTemplate());
		hcuv.setPageSize(pageSize);
		hcuv.setCount(count);
		hcuv.setPage(page);
		hcuv.setFirstResult(firstResult);
		Rs rs =  HibernateCallbackUtil.exeRs(hcuv);
		
//		HibernateCallback hc = new HibernateCallback() {
//
//			@SuppressWarnings("unchecked")
//			@Override
//			public Object doInHibernate(org.hibernate.Session session)
//					throws HibernateException, SQLException {
//				int n = pageSize;
//				if (n == 0) {
//					n = 10;
//				}
//				Query query = session.createQuery(hql);
//				if (firstResult == 0) {
//					query.setFirstResult((page - 1) * pageSize);
//				} else {
//					query.setFirstResult(firstResult - 1);
//				}
//
//				query.setMaxResults(n);
//
//				List<Long> list = query.list();
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

	
	@Override
	public boolean findQaItemByIpAndSaltOnSameDay(String ip, String salt) {
		long count = (Long) this
				.getHibernateTemplate()
				.find("select count(*) from QaItem q where q.salt='" + salt
						+ "' and q.addIp='" + ip.trim()
						+ "' and TO_DAYS(q.addTime)-TO_DAYS(CURDATE())=0")
				.get(0);
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> findAllID() {
		String hql="select q.id from QaItem q order by q.id desc";
		List<Long> list=this.getHibernateTemplate().find(hql);
		return list;
	}

}
