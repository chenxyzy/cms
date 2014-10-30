package com.lerx.vote.dao.imp;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lerx.vote.dao.IVoteItemDao;
import com.lerx.vote.vo.VoteItem;
import com.lerx.vote.vo.VoteSubject;

public class VoteItemDaoImp extends HibernateDaoSupport implements IVoteItemDao {

	@Override
	public long add(VoteItem vi) {
		this.getHibernateTemplate().save(vi);
		return vi.getId();
	}

	@Override
	public boolean modify(VoteItem vi) {
		this.getHibernateTemplate().saveOrUpdate(vi);
		return true;
	}

	@Override
	public boolean del(VoteItem vi) {
		this.getHibernateTemplate().delete(vi);
		return true;
	}

	@Override
	public boolean delById(long id) {
		this.getHibernateTemplate().delete(
				this.getHibernateTemplate().get("com.lerx.vote.vo.VoteItem",
						id));
		return true;
	}

	@Override
	public VoteItem findById(long id) {
		// TODO Auto-generated method stub
		return (VoteItem) this.getHibernateTemplate().get("com.lerx.vote.vo.VoteItem",
				id);
	}

	@Override
	public List<Long> queryAll(int mod,int subId,int state) {
		String hql,hqlAdd;
//		System.out.println("mod:"+mod);
		switch (state){
		case 1:
			hqlAdd = " and v.state=false ";
			break;
		case 2:
			hqlAdd = "";
			break;
		default:
			hqlAdd = " and v.state=true ";
			break;
		}
		switch (mod) {

		case 1:
			hql = "select v.id from VoteItem v where v.sub.id=" + subId + hqlAdd +  " order by v.title asc";
			
			break;
		case 2:

			hql = "select v.id from VoteItem v where v.sub.id=" + subId + hqlAdd +  " order by v.unicodeOrder asc";
			break;
		case 3:
			hql = "select v.id from VoteItem v where v.sub.id=" + subId + hqlAdd +  " order by rand()";
			break;
		case 9:
			hql = "select v.id from VoteItem v where v.sub.id=" + subId + hqlAdd +  " order by v.recNum desc";
			break;
		default:
			hql = "select v.id from VoteItem v where v.sub.id=" + subId + hqlAdd + " order by v.id asc";
			
			break;

		}

		@SuppressWarnings("unchecked")
		List<Long> list = this.getHibernateTemplate().find(hql);
		return list;
	}

	@Override
	public boolean setState(long id, boolean state) {

		VoteItem vi=(VoteItem) this.getHibernateTemplate().get(
				"com.lerx.vote.vo.VoteItem", id);
		vi.setState(state);
		this.getHibernateTemplate().saveOrUpdate(vi);
		return true;
	}

	@Override
	public Long count(VoteSubject vs, int mod) {
		Long count;
		int subId=vs.getId();
		String hql,hqlAdd;
		switch (mod){
		case 1:
			hqlAdd=" and v.state is true ";
			break;
		case 2:
			hqlAdd=" and v.state is not true ";
			break;
		default:
			hqlAdd="";
			break;
		}
		hql="select count(*) from VoteItem v where v.sub.id=" + subId + hqlAdd;
		
		count = (Long) this.getHibernateTemplate()
		.find(hql).get(0);
		
		return count;
	}

	@Override
	public long findMaxRecNum(int subId) {
		String hql = "select max(v.recNum) from VoteItem v where v.sub.id=" + subId;
		return (Long) this.getHibernateTemplate()
		.find(hql).get(0);
	}

	@Override
	public boolean delBySubId(int subId) {
		String hql="delete from VoteItem v where v.sub.id=" + subId;
		this.getHibernateTemplate().bulkUpdate(hql);
		return true;
	}

	@Override
	public boolean clearRecBySubId(int subId) {
		String hql="update VoteItem v set v.recNum=0 where v.sub.id=" + subId;
		this.getHibernateTemplate().bulkUpdate(hql);
		return true;
	}

}
