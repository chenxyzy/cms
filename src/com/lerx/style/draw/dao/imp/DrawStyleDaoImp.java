package com.lerx.style.draw.dao.imp;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lerx.style.draw.dao.IDrawStyleDao;
import com.lerx.style.draw.vo.DrawStyle;

public class DrawStyleDaoImp extends HibernateDaoSupport implements
		IDrawStyleDao {

	@Override
	public int add(DrawStyle style) {
		this.getHibernateTemplate().save(style);
		return style.getId();
	}

	@Override
	public int imp(DrawStyle style) {
		try {
			this.getHibernateTemplate().saveOrUpdate(style);
			return style.getId();
		}catch (RuntimeException re) {

			return 0;
		}
	}

	@Override
	public boolean del(DrawStyle style) {
		this.getHibernateTemplate().delete(style);
		return true;
	}

	@Override
	public boolean delById(Integer id) {
		this.getHibernateTemplate().delete(
				this.getHibernateTemplate().get("com.lerx.style.draw.vo.DrawStyle",
						id));

		return true;
	}

	@Override
	public DrawStyle findById(Integer id) {
		return (DrawStyle) this.getHibernateTemplate().get(
				"com.lerx.style.draw.vo.DrawStyle", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DrawStyle> findAll(int mod) {
//		System.out.println("mod:"+mod);
		List<DrawStyle> list;
		String hql;
		switch (mod) {

		case 1:
			hql = "from DrawStyle s where s.state=true";
			list = this.getHibernateTemplate().find(hql);
			break;
		case 2:

			hql = "from DrawStyle s where s.state=false";
			list = this.getHibernateTemplate().find(hql);
			break;
		default:
			list = this.getHibernateTemplate().loadAll(DrawStyle.class);
//			System.out.println("list.size():"+list.size());
			break;

		}

		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public boolean findStyleByName(String styleName) {

		String hql = "from DrawStyle s where s.styleName=?";

		@SuppressWarnings("unchecked")
		List<DrawStyle> list = this.getHibernateTemplate().find(hql,
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
		hql="update DrawStyle a set a.state=false";
		this.getHibernateTemplate().bulkUpdate(hql);
		hql="update DrawStyle a set a.state=true where a.id="+id;
		this.getHibernateTemplate().bulkUpdate(hql);
		
		return true;
	}

	@Override
	public DrawStyle findDefault() {
		String hql="from DrawStyle a where a.state is true";
		@SuppressWarnings("unchecked")
		List<DrawStyle> list = this.getHibernateTemplate().find(hql);
//		System.out.println("数量："+list.size());
		if (list.isEmpty()) {
			return null;
		} else {
			
			return list.get(0);
		}
	}

}
