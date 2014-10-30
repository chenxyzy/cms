package com.lerx.bbs.dao.imp;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lerx.bbs.dao.IScoreGroupDao;
import com.lerx.bbs.vo.ScoreGroup;

public class ScoreGroupDaoImp extends HibernateDaoSupport implements
		IScoreGroupDao {

	@Override
	public boolean addScoreGroup(ScoreGroup scoreGroup) {
		this.getHibernateTemplate().save(scoreGroup);
		return true;
	}

	@Override
	public boolean delScoreGroupById(int id) {
		this.getHibernateTemplate().delete(this.getHibernateTemplate().get(
				"com.lerx.bbs.vo.ScoreGroup", id));
		return true;
	}

	@Override
	public boolean modifyScoreGroup(ScoreGroup scoreGroup) {
		this.getHibernateTemplate().saveOrUpdate(scoreGroup);
		return true;
	}

	@Override
	public ScoreGroup findScoreGroupByID(int id) {
		// TODO Auto-generated method stub
		return (ScoreGroup) this.getHibernateTemplate().get(
				"com.lerx.bbs.vo.ScoreGroup", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ScoreGroup> findGroupBySchemeId(int id) {
		final String hql="from ScoreGroup g where g.scheme.id="+id+" order by g.valueLowerLimit asc";
		List<ScoreGroup> list = this.getHibernateTemplate().find(hql);
		return list;
	}

	@Override
	public ScoreGroup findScoreGroupByIdAndValue(int id, int value) {
		final String hql="from ScoreGroup g where g.scheme.id="+id+" and g.valueLowerLimit<="+value+" and g.valueUpperLimit>="+value+" order by g.valueLowerLimit asc";
		if (this.getHibernateTemplate().find(hql).size()>0){
			return (ScoreGroup) this.getHibernateTemplate().find(hql).get(0);
		}else{
			return null;
		}
		
	}

}
