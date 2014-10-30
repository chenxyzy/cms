package com.lerx.draw.dao.imp;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lerx.draw.dao.IDrawDao;
import com.lerx.draw.vo.Draw;

public class DrawDaoImp extends HibernateDaoSupport implements IDrawDao {

	@Override
	public int add(Draw draw) {
		try {
			this.getHibernateTemplate().save(draw);
			return draw.getId();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			return 0;
		}
	}

	@Override
	public boolean delById(int id) {
		try {
			this.getHibernateTemplate().delete(
				this.getHibernateTemplate().get("com.lerx.draw.vo.Draw", id));
			return true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}

	@Override
	public boolean modify(Draw draw) {
		try {
			this.getHibernateTemplate().saveOrUpdate(draw);
			return true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}

	

	@Override
	public Draw findById(int id) {
		return (Draw) this.getHibernateTemplate().get(
				"com.lerx.draw.vo.Draw", id);
		
	}

	@Override
	public boolean setState(int id, boolean state) {
		Draw draw=(Draw) this.getHibernateTemplate().get(
				"com.lerx.draw.vo.Draw", id);
		draw.setState(state);
		this.getHibernateTemplate().saveOrUpdate(draw);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Draw> findAllDraw(int mod) {
		String hql;
		List<Draw> list;
		switch (mod) {
		case 1:
			hql="from Draw d where d.state=false  order by d.drawStartTime desc";
			break;
//			sqlAdd="";
//			break;
		case 2:
			hql="from Draw d order by d.drawStartTime desc";
			break;
			
		default:
			hql="from Draw d where d.state=true  order by d.drawStartTime desc";
			break;
			
//			list = this.getHibernateTemplate().loadAll(QaNav.class);
//			return list;
			
		}
		
		list = (List<Draw>)this.getHibernateTemplate().find(hql);
//		System.out.println("list.size():"+list.size());
		return list;
		// TODO Auto-generated method stub
	}


}
