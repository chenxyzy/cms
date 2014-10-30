package com.lerx.integral.rule.dao.imp;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lerx.integral.rule.dao.IIntegralRuleDao;
import com.lerx.integral.rule.vo.IntegralRule;

public class IntegralRuleDaoImp extends HibernateDaoSupport implements IIntegralRuleDao {

	@Override
	public int add(IntegralRule ir) {
		if (findByName(ir.getName())==null){
			this.getHibernateTemplate().save(ir);
			return ir.getId();
		}else{
			return 0;
		}
		
	}

	@Override
	public boolean modify(IntegralRule ir) {
		try {
			IntegralRule irdb=findById(ir.getId());
			if (ir.getCreateTime()==null){
				ir.setCreateTime(irdb.getCreateTime());
			}
			if (ir.getLocalPostion()==0){
				ir.setLocalPostion(irdb.getLocalPostion());
			}
			if (ir.isState()){
				String hql="update IntegralRule i set i.state=false where i.localPostion=?";
				this.getHibernateTemplate().bulkUpdate(hql,ir.getLocalPostion());
			}
			this.getHibernateTemplate().saveOrUpdate(ir);
			return true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean deyById(int id) {
		try {
			this.getHibernateTemplate().delete(
					this.getHibernateTemplate()
							.get("com.lerx.integral.rule.vo.IntegralRule", id));
			return true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public IntegralRule findById(int id) {
		// TODO Auto-generated method stub
		return 
		(IntegralRule) this.getHibernateTemplate()
		.get("com.lerx.integral.rule.vo.IntegralRule", id);
	}

	@Override
	public List<IntegralRule> query(int localPostion) {
		String hql="from IntegralRule i where i.localPostion=?";
		@SuppressWarnings("unchecked")
		List<IntegralRule> list = (List<IntegralRule>)this.getHibernateTemplate().find(hql, localPostion);
		return list;
	}

	@Override
	public IntegralRule findByName(String name) {
		String hql="from IntegralRule i where i.name=?";
		@SuppressWarnings("unchecked")
		List<IntegralRule> list=(List<IntegralRule>)this.getHibernateTemplate().find(hql, name);
		if (list.isEmpty()){
			return null;
		}else{
			return list.get(0);
		}
	}

	@Override
	public boolean changeState(int id,boolean state,int localPostion) {
		String hql="";
		if (state){
			System.out.println("localPostion:"+localPostion);
			hql="update IntegralRule i set i.state=false where i.localPostion=?";
			this.getHibernateTemplate().bulkUpdate(hql,localPostion);
			hql="update IntegralRule i set i.state=true where i.id="+id;
			this.getHibernateTemplate().bulkUpdate(hql);
		}else{
			IntegralRule i=findById(id);
			i.setState(state);
			this.getHibernateTemplate().saveOrUpdate(i);
//			hql="update IntegralRule i set i.state=false where i.id="+id;
//			this.getHibernateTemplate().bulkUpdate(hql);
		}
		return true;
	}

	@Override
	public IntegralRule findDefault(int localPostion) {
		String hql="from IntegralRule i where i.state=true and i.localPostion=?";
		@SuppressWarnings("unchecked")
		List<IntegralRule> list=(List<IntegralRule>)this.getHibernateTemplate().find(hql,localPostion);
		if (list.isEmpty()){
			return null;
		}else{
			return list.get(0);
		}
//		return null;
	}

}
