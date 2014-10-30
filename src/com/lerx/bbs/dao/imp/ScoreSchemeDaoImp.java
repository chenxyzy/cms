package com.lerx.bbs.dao.imp;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lerx.bbs.dao.IScoreSchemeDao;
import com.lerx.bbs.vo.ScoreScheme;

public class ScoreSchemeDaoImp extends HibernateDaoSupport implements
		IScoreSchemeDao {

	@Override
	public boolean addScoreScheme(ScoreScheme scheme) {
		this.getHibernateTemplate().save(scheme);
//		System.out.println("增加方案成功");
		return true;
	}

	@Override
	public boolean delScoreSchemeById(int id) {
		this.getHibernateTemplate().delete(this.getHibernateTemplate().get(
				"com.lerx.bbs.vo.ScoreScheme", id));
		return true;
	}

	@Override
	public boolean modifyScoreScheme(ScoreScheme scheme) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().saveOrUpdate(scheme);
		return true;
	}

	@Override
	public ScoreScheme findScoreSchemeById(int id) {
		// TODO Auto-generated method stub
		return (ScoreScheme) this.getHibernateTemplate().get(
				"com.lerx.bbs.vo.ScoreScheme", id);
	}

	@Override
	public List<ScoreScheme> findAll() {
		List<ScoreScheme> list = this.getHibernateTemplate().loadAll(ScoreScheme.class);
		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public boolean setState(int id) {
		String hql="";
		hql="update ScoreScheme a set a.state=false";
		this.getHibernateTemplate().bulkUpdate(hql);
		hql="update ScoreScheme a set a.state=true where a.id="+id;
		this.getHibernateTemplate().bulkUpdate(hql);
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public ScoreScheme findCurScoreScheme() {
		String hql="from ScoreScheme a where a.state=true";
		@SuppressWarnings("unchecked")
		List<ScoreScheme> scheme=(List<ScoreScheme>) this.getHibernateTemplate().find(hql);
		if (scheme.size()>0){
			return scheme.get(0);
//			return (ScoreScheme) this.getHibernateTemplate().find(hql).get(0);
		}else{
			return null;
		}
		
		// TODO Auto-generated method stub
	}
	

}
