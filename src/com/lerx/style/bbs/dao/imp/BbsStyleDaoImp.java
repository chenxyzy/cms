package com.lerx.style.bbs.dao.imp;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lerx.style.bbs.dao.IBbsStyleDao;
import com.lerx.style.bbs.vo.BbsStyle;
import com.lerx.style.bbs.vo.BbsStyleSubElementInCommon;

public class BbsStyleDaoImp extends HibernateDaoSupport implements IBbsStyleDao {

	@Override
	public boolean add(BbsStyle style) {
		try {
			style.setState(true);
			this.getHibernateTemplate().saveOrUpdate(style);
			
			return true;
		}catch (RuntimeException re) {

			return false;
		}
	}

	@Override
	public boolean addStyle(String styleName, String author, String description) {
		if (findStyleByName(styleName)) {
			return false;
		} else {
			BbsStyle style = new BbsStyle();
			style.setAuthor(author);
			style.setStyleName(styleName);
			style.setDescription(description);
			style.setState(true);
			style.setPublicStyle(new BbsStyleSubElementInCommon());
			style.setIndexStyle(new BbsStyleSubElementInCommon());
			style.setForumStyle(new BbsStyleSubElementInCommon());
			style.setSearchStyle(new BbsStyleSubElementInCommon());
			style.setThemeStyle(new BbsStyleSubElementInCommon());
			style.setGenericStyle(new BbsStyleSubElementInCommon());
			style.setEditThreadStyle(new BbsStyleSubElementInCommon());
			this.getHibernateTemplate().save(style);
			return true;
		}
	}

	@Override
	public boolean modify(BbsStyle style) {
		this.getHibernateTemplate().saveOrUpdate(style);
		return true;
	}

	@Override
	public boolean del(BbsStyle style) {
		this.getHibernateTemplate().delete(style);
		return true;
	}

	@Override
	public boolean delById(Integer id) {
		try {
			this.getHibernateTemplate().delete(
					this.getHibernateTemplate().get(
							"com.lerx.style.bbs.vo.BbsStyle", id));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public BbsStyle findById(Integer id) {
		return (BbsStyle) this.getHibernateTemplate().get(
				"com.lerx.style.bbs.vo.BbsStyle", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BbsStyle> findAll(int mod) {
		List<BbsStyle> list;
		String hql;
		switch (mod){
		
		case 1:
			list = this.getHibernateTemplate().loadAll(
					BbsStyle.class);
			break;
		case 2:
			
			hql="from BbsStyle s where s.state=false";
			list = this.getHibernateTemplate().find(hql);
			break;
		default:
			
			hql="from BbsStyle s where s.state=true";
			list = this.getHibernateTemplate().find(hql);
			break;
			
		}
			
		
		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public boolean changeState(int id, boolean state) {
		BbsStyle style = (BbsStyle) this.getHibernateTemplate().get(
				"com.lerx.style.bbs.vo.BbsStyle", id);
		style.setState(state);
		try {
			this.getHibernateTemplate().saveOrUpdate(style);
			return true;
		} catch (RuntimeException re) {

			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean findStyleByName(String styleName) {
		String hql = "from BbsStyle s where s.styleName=?";

		List<BbsStyle> list = this.getHibernateTemplate().find(hql,
				styleName.trim());

		if (list.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean setDef(int id) {
		String hql="";
		hql="update BbsStyle s set s.def=false";
		this.getHibernateTemplate().bulkUpdate(hql);
		hql="update BbsStyle s set s.def=true where s.id="+id;
		this.getHibernateTemplate().bulkUpdate(hql);
		
		return true;
		
	}

	@Override
	public BbsStyle findDef() {
		String hql = "from BbsStyle s where s.def=true";

		@SuppressWarnings("unchecked")
		List<BbsStyle> list = this.getHibernateTemplate().find(hql);

		if (list.isEmpty()) {
			return null;
		} else {
			return (BbsStyle) this.getHibernateTemplate().get(
					"com.lerx.style.bbs.vo.BbsStyle", list.get(0).getId());
		}
	}

}
