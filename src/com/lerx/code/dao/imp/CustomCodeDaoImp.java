package com.lerx.code.dao.imp;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lerx.code.dao.ICustomCodeDao;
import com.lerx.code.vo.CustomCode;
import com.lerx.sys.util.HibernateCallbackUtil;
import com.lerx.sys.util.vo.HibernateCallbackUtilVo;

public class CustomCodeDaoImp extends HibernateDaoSupport implements
		ICustomCodeDao {

	@Override
	public Long add(CustomCode code) {
		this.getHibernateTemplate().save(code);
		
		// TODO Auto-generated method stub
		return code.getId();
	}

	@Override
	public boolean delById(Long id) {
		this.getHibernateTemplate().delete(
				this.getHibernateTemplate().get(
						"com.lerx.code.vo.CustomCode", id));
		return true;
	}

	@Override
	public boolean modify(CustomCode code) {
		this.getHibernateTemplate().saveOrUpdate(code);
		return true;
	}

	@Override
	public CustomCode findById(Long id) {
		// TODO Auto-generated method stub
		return (CustomCode) this.getHibernateTemplate().get("com.lerx.code.vo.CustomCode", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public CustomCode find(int gid,int mode) {
		
		String hql,orderStr=" order by c.id desc";
		String hql2;
		Long maxId,minId,rid;
		long curId;
		CustomCode cc;
		//找到当前记录
		hql="select c.id from CustomCode c where c.cc.id="+gid+" and c.current=true";
		List<Long> list=(List<Long>)this.getHibernateTemplate().find(hql);
		if (list.isEmpty()){
			curId=0;
		}else{
			curId=(Long) list.get(0);
		}
//		System.out.println("curId:"+curId);
		//找到最高id
		hql="select c.id from CustomCode c where c.cc.id="+gid+" and c.state is true order by c.id desc";
		maxId=(Long) this.getHibernateTemplate().find(hql).get(0);
//		System.out.println("maxId:"+maxId);
		//找到最低id
		hql="select c.id from CustomCode c where c.cc.id="+gid+" and c.state is true  order by c.id asc";
		minId=(Long) this.getHibernateTemplate().find(hql).get(0);
//		System.out.println("minId:"+minId);
		if (maxId==0){//如果没有记录
			return null;
		}
		if (maxId==minId){//如果只有一条记录
			cc=(CustomCode) this.getHibernateTemplate().get("com.lerx.code.vo.CustomCode", maxId);
			return cc;
		}
		
		if (mode==0){//按顺序
			if (curId==minId || curId==0){//如果已是最后一个
				rid=maxId;
			}else{
				hql2 = "select c.id from CustomCode c where c.cc.id="+gid+"  and c.state is true and  c.id<"+curId+orderStr;
				
				HibernateCallbackUtilVo hcuv = new HibernateCallbackUtilVo();
				hcuv.setFirstResult(0);
				hcuv.setHql(hql2);
				hcuv.setIbernateTemplate(this.getHibernateTemplate());
				hcuv.setPageSize(1);
				hcuv.setPage(0);
				list = (List<Long>) HibernateCallbackUtil.exeList(hcuv);
				
				
//				HibernateCallback hc = new HibernateCallback() {
//
//					@SuppressWarnings("unchecked")
//					@Override
//					public Object doInHibernate(org.hibernate.Session session)
//							throws HibernateException, SQLException {
//						Query query = session.createQuery(hql2);
//						query.setMaxResults(1);
//
//						List<Long> list = query.list();
//
//						
//						return list;
//					}
//
//				};
				
				if (list.size() == 0) {
					return null;
				} else {
					rid = (Long) list.get(0);
				}
				
			}
			
		}else{//随机
			hql2 = "select c.id from CustomCode c where c.cc.id=" + gid + " and c.state is true  and c.current<>true order by rand()";
			rid=(Long) this.getHibernateTemplate().find(hql2).get(0);
			
		}
//		System.out.println("rid:"+rid);
		if (rid>0){
			cc=(CustomCode) this.getHibernateTemplate().get("com.lerx.code.vo.CustomCode", rid);
			this.getHibernateTemplate().bulkUpdate("update CustomCode c set c.current=false where c.cc.id="+gid);
			cc.setCurrent(true);
			this.getHibernateTemplate().saveOrUpdate(cc);
			return cc;
		}else{
			return null;
		}
		
	}

	@Override
	public List<CustomCode> findByType(int gid) {
		String hql="from CustomCode c where c.cc.id=?";
		@SuppressWarnings("unchecked")
		List<CustomCode> list = this.getHibernateTemplate().find(hql,gid);
		return list;
	}

}
