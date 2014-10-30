package com.lerx.style.site.dao.imp;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.lerx.style.site.dao.ISiteStyleDao;
import com.lerx.style.site.vo.SiteStyle;
import com.lerx.style.site.vo.SiteStyleSubElementInCommon;

public class SiteStyleDaoImp extends HibernateDaoSupport implements
		ISiteStyleDao {

	@Override
	public boolean addStyle(String styleName, String author, String description) {

		if (findStyleByName(styleName)) {
			return false;
		} else {
			//try {
				SiteStyle style = new SiteStyle();
				style.setStyleName(styleName);
				style.setAuthor(author);
				style.setDescription(description);
				style.setState(true);
				style.setDef(false);
				style.setPublicStyle(new SiteStyleSubElementInCommon());
				style.setIndexStyle(new SiteStyleSubElementInCommon());
				style.setClassStyle(new SiteStyleSubElementInCommon());
				style.setRegStyle(new SiteStyleSubElementInCommon());
				style.setThreadStyle(new SiteStyleSubElementInCommon());
				style.setLoginStyle(new SiteStyleSubElementInCommon());
				style.setArticleAddStyle(new SiteStyleSubElementInCommon());
				style.setArticleEditStyle(new SiteStyleSubElementInCommon());
				style.setSearchStyle(new SiteStyleSubElementInCommon());
				style.setGenericStyle(new SiteStyleSubElementInCommon());
				style.setUserCenterStyle(new SiteStyleSubElementInCommon());
				style.setCommentStyle(new SiteStyleSubElementInCommon());
				this.getHibernateTemplate().save(style);
				return true;

			//} catch (RuntimeException re) {
			//	System.out.print(re);
			//	return false;
			//}
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean findStyleByName(String styleName) {
		String hql = "from SiteStyle s where s.styleName=?";

		List<SiteStyle> list = this.getHibernateTemplate().find(hql,
				styleName.trim());

		if (list.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public SiteStyle findStyleById(int id) {
		return (SiteStyle) this.getHibernateTemplate().get(
				"com.lerx.style.site.vo.SiteStyle", id);
	}

	@Override
	public boolean delStyleById(int id) {
		try {
			this.getHibernateTemplate().delete(
					this.getHibernateTemplate().get(
							"com.lerx.style.site.vo.SiteStyle", id));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public List<SiteStyle> findAll(int mod) {
		
		List<SiteStyle> list;
		String hql;
		switch (mod){
		
		case 1:
			list = this.getHibernateTemplate().loadAll(
					SiteStyle.class);
			break;
		case 2:
			
			hql="from SiteStyle s where s.state=false";
			list = this.getHibernateTemplate().find(hql);
			break;
		default:
			
			hql="from SiteStyle s where s.state=true";
			list = this.getHibernateTemplate().find(hql);
			break;
			
		}
			
		
		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public boolean changeState(int id, boolean state) {
		SiteStyle style = (SiteStyle) this.getHibernateTemplate().get(
				"com.lerx.style.site.vo.SiteStyle", id);
		style.setState(state);
		try {
			this.getHibernateTemplate().saveOrUpdate(style);
			return true;
		} catch (RuntimeException re) {

			return false;
		}
	}

	@Override
	public boolean add(SiteStyle style) {
		try {
			
			this.getHibernateTemplate().saveOrUpdate(style);
			return true;
		}catch (RuntimeException re) {

			return false;
		}
		
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean setDef(int id) {
		String hql="";
		hql="update SiteStyle s set s.def=false";
		this.getHibernateTemplate().bulkUpdate(hql);
		hql="update SiteStyle s set s.def=true where s.id="+id;
		this.getHibernateTemplate().bulkUpdate(hql);
		
		return true;
	}

	@Override
	public SiteStyle findDef() {
		String hql = "from SiteStyle s where s.def=true";

		@SuppressWarnings("unchecked")
		List<SiteStyle> list = this.getHibernateTemplate().find(hql);

		if (list.isEmpty()) {
			return null;
		} else {
			return (SiteStyle) this.getHibernateTemplate().get(
					"com.lerx.style.site.vo.SiteStyle", list.get(0).getId());
		}
		
		
		
	}
}
