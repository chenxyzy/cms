package com.lerx.sys.util;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.HibernateTemplate;

import com.lerx.sys.util.vo.HibernateCallbackUtilVo;
import com.lerx.sys.util.vo.Rs;

public class HibernateCallbackUtil {

	public static List<?> exeList(HibernateCallbackUtilVo hcuv) {

		final int page = hcuv.getPage();
		final int pageSize = hcuv.getPageSize();
		final int firstResult = hcuv.getFirstResult();
		final String hql = hcuv.getHql();
		HibernateTemplate ibernateTemplate = hcuv.getIbernateTemplate();

		HibernateCallback<Object> hc = new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(org.hibernate.Session session)
					throws HibernateException, SQLException {
				int n = pageSize;
				if (n == 0) {
					n = 10;
				}
				Query query = session.createQuery(hql);
				if (firstResult == 0) {
					query.setFirstResult((page - 1) * pageSize);
				} else {
					query.setFirstResult(firstResult - 1);
				}

				query.setMaxResults(n);

				List<?> list = query.list();

				if (list.isEmpty()) {

				} else {
				}
				return list;
			}

		};

		return ibernateTemplate.executeFind(hc);
	}

	public static Rs exeRs(HibernateCallbackUtilVo hcuv) {

		final int page = hcuv.getPage();
		final int pageSize = hcuv.getPageSize();
		final int firstResult = hcuv.getFirstResult();
		final String hql = hcuv.getHql();
		final long count = hcuv.getCount();

		Rs rs = RsInit.rsInit(page, pageSize, count);

		HibernateTemplate ibernateTemplate = hcuv.getIbernateTemplate();

		HibernateCallback<Object> hc = new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(org.hibernate.Session session)
					throws HibernateException, SQLException {
				int n = pageSize;
				if (n == 0) {
					n = 10;
				}
				Query query = session.createQuery(hql);
				if (firstResult == 0) {
					query.setFirstResult((page - 1) * pageSize);
				} else {
					query.setFirstResult(firstResult - 1);
				}

				query.setMaxResults(n);

				List<?> list = query.list();

				if (list.isEmpty()) {

				} else {
				}
				return list;
			}

		};
		rs.setList(ibernateTemplate.executeFind(hc));
		// System.out.println("总数1："+rs.getCount());
		// System.out.println("总数2："+rs.getList().size());
		return rs;
	}
	
	public static Rs exeRsOnBbs(HibernateCallbackUtilVo hcuv) {

		final int page = hcuv.getPage();
		final int pageSize = hcuv.getPageSize();
//		final int firstResult = hcuv.getFirstResult();
		final String hql = hcuv.getHql();
		final long count = hcuv.getCount();

		Rs rs = RsInit.rsInit(page, pageSize, count);

		HibernateTemplate ibernateTemplate = hcuv.getIbernateTemplate();

		HibernateCallback<Object> hc = new HibernateCallback<Object>() {

			@Override
			public Object doInHibernate(org.hibernate.Session session)
					throws HibernateException, SQLException {
				int n = pageSize;
				int firstResult;
				if (n == 0) {
					n = 10;
				}

				if (page == 1) {
					n--;
					firstResult = 0;
				} else {
					firstResult = ((page - 1) * pageSize) - 1;
				}

				Query query = session.createQuery(hql);
				query.setFirstResult(firstResult);
				query.setMaxResults(n);

				List<?> list = query.list();

				if (list.isEmpty()) {
					// System.out.println("找不到记录");
				}
				// System.out.println("测试点5");
				return list;
			}

		};
		rs.setList(ibernateTemplate.executeFind(hc));
		return rs;
	}

}
