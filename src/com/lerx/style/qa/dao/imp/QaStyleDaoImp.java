package com.lerx.style.qa.dao.imp;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lerx.style.qa.dao.IQaStyleDao;
import com.lerx.style.qa.vo.QaStyle;
import com.lerx.style.qa.vo.QaStyleSubElementInCommon;

public class QaStyleDaoImp extends HibernateDaoSupport implements IQaStyleDao {

	@Override
	public int add(QaStyle style,boolean init) {
		if (init){
			style.setPublicStyle(new QaStyleSubElementInCommon());
			style.setIndexStyle(new QaStyleSubElementInCommon());
			style.setNavStyle(new QaStyleSubElementInCommon());
			style.setItemStyle(new QaStyleSubElementInCommon());
		}
		
		this.getHibernateTemplate().save(style);
		return style.getId();

	}

	@Override
	public boolean del(QaStyle style) {
		this.getHibernateTemplate().delete(style);
		return true;
	}

	@Override
	public boolean delById(Integer id) {
		this.getHibernateTemplate().delete(
				this.getHibernateTemplate().get("com.lerx.style.qa.vo.QaStyle",
						id));

		return true;
	}

	@Override
	public QaStyle findById(Integer id) {
		// TODO Auto-generated method stub
		return (QaStyle) this.getHibernateTemplate().get(
				"com.lerx.style.qa.vo.QaStyle", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QaStyle> findAll(int mod) {
		List<QaStyle> list;
		String hql;
		switch (mod) {

		case 1:
			hql = "from QaStyle s where s.state=true";
			list = this.getHibernateTemplate().find(hql);
			break;
		case 2:

			hql = "from QaStyle s where s.state=false";
			list = this.getHibernateTemplate().find(hql);
			break;
		default:
			list = this.getHibernateTemplate().loadAll(QaStyle.class);
			
			break;

		}

		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public boolean findStyleByName(String styleName) {
		String hql = "from QaStyle s where s.styleName=?";

		@SuppressWarnings("unchecked")
		List<QaStyle> list = this.getHibernateTemplate().find(hql,
				styleName.trim());

		if (list.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean setDefault(int id) {
		
		
		String hql="";
		hql="update QaStyle a set a.state=false";
		this.getHibernateTemplate().bulkUpdate(hql);
		hql="update QaStyle a set a.state=true where a.id="+id;
		this.getHibernateTemplate().bulkUpdate(hql);
		
		return true;
//		QaStyle style = (QaStyle) this.getHibernateTemplate().get(
//				"com.lerx.style.qa.vo.QaStyle", id);
//		style.setState(state);
//		try {
//			this.getHibernateTemplate().saveOrUpdate(style);
//			return true;
//		} catch (RuntimeException re) {
//
//			return false;
//		}
	}

	@Override
	public QaStyle findDefault() {
		String hql="from QaStyle a where a.state is true";
		@SuppressWarnings("unchecked")
		List<QaStyle> list = this.getHibernateTemplate().find(hql);
//		System.out.println("数量："+list.size());
		if (list.isEmpty()) {
			return null;
		} else {
			
			return list.get(0);
		}
		
	}

	@Override
	public int imp(QaStyle style) {
		try {
			this.getHibernateTemplate().saveOrUpdate(style);
			return style.getId();
		}catch (RuntimeException re) {

			return 0;
		}
	}

}
