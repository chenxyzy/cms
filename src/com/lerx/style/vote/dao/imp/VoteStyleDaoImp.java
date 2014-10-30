package com.lerx.style.vote.dao.imp;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lerx.style.vote.dao.IVoteStyleDao;
import com.lerx.style.vote.vo.VoteStyle;
import com.lerx.style.vote.vo.VoteStyleSubElementInCommon;

public class VoteStyleDaoImp extends HibernateDaoSupport implements
		IVoteStyleDao {

	@Override
	public int add(VoteStyle style,boolean init) {
		if (init){
			style.setPublicStyle(new VoteStyleSubElementInCommon());
			style.setItemStyle(new VoteStyleSubElementInCommon());
			style.setJoinStyle(new VoteStyleSubElementInCommon());
			style.setResultStyle(new VoteStyleSubElementInCommon());
			style.setVoteStyle(new VoteStyleSubElementInCommon());
		}
		
		this.getHibernateTemplate().save(style);
		return style.getId();
	}

	@Override
	public boolean del(VoteStyle style) {
		this.getHibernateTemplate().delete(style);
		return true;
	}

	@Override
	public boolean delById(Integer id) {
		this.getHibernateTemplate().delete(
				this.getHibernateTemplate().get("com.lerx.style.vote.vo.VoteStyle",
						id));

		return true;
	}

	@Override
	public VoteStyle findById(Integer id) {
		return (VoteStyle) this.getHibernateTemplate().get(
				"com.lerx.style.vote.vo.VoteStyle", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VoteStyle> findAll(int mod) {
		List<VoteStyle> list;
		String hql;
		switch (mod) {

		case 1:
			hql = "from VoteStyle s where s.state=true";
			list = this.getHibernateTemplate().find(hql);
			break;
		case 2:

			hql = "from VoteStyle s where s.state=false";
			list = this.getHibernateTemplate().find(hql);
			break;
		default:
			list = this.getHibernateTemplate().loadAll(VoteStyle.class);
			
			break;

		}

		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public boolean findStyleByName(String styleName) {
		String hql = "from VoteStyle s where s.title=?";

		@SuppressWarnings("unchecked")
		List<VoteStyle> list = this.getHibernateTemplate().find(hql,
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
		hql="update VoteStyle a set a.state=false";
		this.getHibernateTemplate().bulkUpdate(hql);
		hql="update VoteStyle a set a.state=true where a.id="+id;
		this.getHibernateTemplate().bulkUpdate(hql);
		
		return true;
	}

	@Override
	public VoteStyle findDefault() {
		String hql="from VoteStyle a where a.state is true";
		@SuppressWarnings("unchecked")
		List<VoteStyle> list = this.getHibernateTemplate().find(hql);
		if (list.isEmpty()) {
			return null;
		} else {
			
			return list.get(0);
		}
		
	}

	@Override
	public int imp(VoteStyle style) {
		try {
			this.getHibernateTemplate().saveOrUpdate(style);
			return style.getId();
		}catch (RuntimeException re) {

			return 0;
		}
	}

}
