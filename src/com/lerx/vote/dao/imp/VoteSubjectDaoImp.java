package com.lerx.vote.dao.imp;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lerx.vote.dao.IVoteSubjectDao;
import com.lerx.vote.vo.VoteSubject;

public class VoteSubjectDaoImp extends HibernateDaoSupport implements
		IVoteSubjectDao {

	@Override
	public int add(VoteSubject vs) {
		this.getHibernateTemplate().save(vs);
		return vs.getId();
	}

	@Override
	public boolean del(VoteSubject vs) {
		this.getHibernateTemplate().delete(vs);
		return true;
	}

	@Override
	public boolean delById(int id) {
		this.getHibernateTemplate().delete(
				this.getHibernateTemplate().get("com.lerx.vote.vo.VoteSubject",
						id));
		return true;
	}

	@Override
	public VoteSubject findById(int id) {
		// TODO Auto-generated method stub
		return (VoteSubject) this.getHibernateTemplate().get(
				"com.lerx.vote.vo.VoteSubject", id);
	}

	@Override
	public boolean modify(VoteSubject vs) {
		this.getHibernateTemplate().saveOrUpdate(vs);
		return true;
	}

	@Override
	public List<VoteSubject> findAll(int mod) {
		String hql;
		switch (mod) {

		case 1:
			hql = "from VoteSubject v order by v.id desc";
			break;
		case 2:

			hql = "from VoteSubject v order by v.id desc where v.state=false";
			break;
		default:

			hql = "from VoteSubject v order by v.id desc where v.state=true";
			break;

		}

		@SuppressWarnings("unchecked")
		List<VoteSubject> list = this.getHibernateTemplate().find(hql);
		return list;
	}

	@Override
	public boolean findByName(String subject) {
		String hql = "from VoteSubject a where a.title=?";

		@SuppressWarnings("unchecked")
		List<VoteSubject> list = this.getHibernateTemplate().find(hql,
				subject.trim());

		if (list.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

}
