package com.lerx.qa.dao.imp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lerx.qa.dao.IQaNavDao;
import com.lerx.qa.model.QaNavShowModel;
import com.lerx.qa.vo.QaNav;
import com.lerx.sys.util.StringUtil;

public class QaNavDaoImp extends HibernateDaoSupport implements IQaNavDao {

	@Override
	public long add(QaNav qn) {
		
		String hql;
		String displayOrder;
		if (qn.getParentNav()==null){
			hql="select count(*) from QaNav a where a.parentNav is null";
			displayOrder="o";
		}else{
			hql="select count(*) from QaNav a where a.parentNav.id="+qn.getParentNav().getId();
			displayOrder=qn.getParentNav().getDisplayOrder();
		}
		
		Long count= (Long) this.getHibernateTemplate().find(hql).get(0);
		count ++;
		if (count <= 9){
			displayOrder+="00"+count;
		}else if(count > 9 && count <= 99){
			displayOrder+="0"+count;
		}else{
			displayOrder+=count;
		}
		qn.setDisplayOrder(displayOrder);
		
		this.getHibernateTemplate().save(qn);
		
		int parentId;
		
		if (qn.getParentNav()==null){
			parentId=0;
		}else{
			parentId=qn.getParentNav().getId();
		}
		sortDisplayOrder(parentId);
//		System.out.println("增加的id:"+thread.getId());
		return qn.getId();
	}

	@Override
	public boolean del(QaNav qn) {
		int parentId;
		
		if (qn.getParentNav()==null){
			parentId=0;
		}else{
			parentId=qn.getParentNav().getId();
		}
		try{
			this.getHibernateTemplate().delete(qn);
			sortDisplayOrder(parentId);
			return true;
			
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean delById(int id) {
		int parentId;
		QaNav qnDb=findById(id);
		if (qnDb.getParentNav()==null){
			parentId=0;
		}else{
			parentId=qnDb.getParentNav().getId();
		}
		try {
			this.getHibernateTemplate().delete(
					this.getHibernateTemplate().get(
							"com.lerx.qa.vo.QaNav", id));
			sortDisplayOrder(parentId);
			return true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			return false;
		}
		
//		return false;
	}

	@Override
	public boolean modify(QaNav qn) {
		int qnParentId,dbParentId;
		QaNav qnDb=findById(qn.getId());
		if (qn.getParentNav()==null){
			qnParentId=0;
		}else{
			qnParentId=qn.getParentNav().getId();
		}
		if (qnDb.getParentNav()==null){
			dbParentId=0;
		}else{
			dbParentId=qnDb.getParentNav().getId();
		}
//		if (qnParentId!=dbParentId){
//			String displayOrder,displayOrderOfParent;
//			displayOrder=qn.getDisplayOrder();
//			displayOrderOfParent=qnDb.getParentNav().getDisplayOrder();
//			displayOrder=StringUtil.strReplace(displayOrder, displayOrderOfParent, qn.getParentNav().getDisplayOrder());
//			qn.setDisplayOrder(displayOrder);
//		}
		
//		this.getHibernateTemplate().save(qn);
//		System.out.println("testid="+qn.getId());
		
//		System.out.println(qn.getId());
		if (qn.getParentNav().getId()==0){
			qn.setParentNav(null);
			
		}
		this.getHibernateTemplate().saveOrUpdate(qn);
		if (qn.getParentNav()==null){
			sortDisplayOrder(0);
		}else{
			sortDisplayOrder(qn.getParentNav().getId());
		}
		if (qnParentId!=dbParentId){
			sortDisplayOrder(dbParentId);
		}
		return true;
	}

	@Override
	public QaNav findById(int id) {
		return (QaNav) this.getHibernateTemplate().get(
				"com.lerx.qa.vo.QaNav", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QaNav> findAllNav(int mod) {
		String hql;
		List<QaNav> list;
		switch (mod) {
		case 1:
			hql="from QaNav q where q.state=true  order by q.displayOrder asc";
			break;
//			sqlAdd="";
//			break;
		case 2:
			hql="from QaNav q where q.state=false  order by q.displayOrder asc";
			break;
		default:
			hql="from QaNav q order by q.displayOrder asc";
			break;
//			list = this.getHibernateTemplate().loadAll(QaNav.class);
//			return list;
			
		}
		list = this.getHibernateTemplate().find(hql);
		return list;
		// TODO Auto-generated method stub
	}

	@Override
	public boolean setState(int id, boolean state) {
		QaNav qn=(QaNav) this.getHibernateTemplate().get(
				"com.lerx.qa.vo.QaNav", id);
		qn.setState(state);
		this.getHibernateTemplate().saveOrUpdate(qn);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QaNav> findByParent(int parentId,int mod) {
		String hql;
		List<QaNav> list;
		switch (mod) {
		case 1:
			hql="from QaNav q where q.parentNav.id="+parentId+" and q.state=true  order by q.displayOrder asc";
			break;
//			sqlAdd="";
//			break;
		case 2:
			hql="from QaNav q where q.parentNav.id="+parentId+" and q.state=false  order by q.displayOrder asc";
			break;
		default:
			hql="from QaNav q where q.parentNav.id="+parentId+"  order by q.displayOrder asc";
			break;
			
		}
		list = this.getHibernateTemplate().find(hql);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QaNav> findAllClassNav(int mod) {
		String hql;
		List<QaNav> list;
		switch (mod) {
		case 1:
			hql="from QaNav q where q.parentNav is null and q.state=true order by q.displayOrder asc";
			break;
//			sqlAdd="";
//			break;
		case 2:
			hql="from QaNav q where q.parentNav is null and q.state=false  order by q.displayOrder asc";
			break;
		default:
			hql="from QaNav q where q.parentNav is null order by q.displayOrder asc";
			break;
			
		}
		list = this.getHibernateTemplate().find(hql);
		return list;
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean sortDisplayOrder(int parentNavId) {
		QaNav praentNav;
		String parentOrderStr;
		if (parentNavId==0){
			praentNav=null;
			parentOrderStr="o";
		}else{
			praentNav = findById(parentNavId);
			parentOrderStr=praentNav.getDisplayOrder();
		}
		
		//String parentOrderStr=praentGroup.getDisplayOrder();
		String toOrderStr,hql;
		
		
		
		if (parentNavId==0){
			hql="from QaNav a where  a.parentNav is null order by length(a.displayOrder) asc,a.displayOrder asc,a.id asc";
			
		}else{
			hql="from QaNav a where  a.parentNav.id="+parentNavId+" order by length(a.displayOrder) asc,a.displayOrder asc,a.id asc";
			
		}
		
		
		List<QaNav> list = this.getHibernateTemplate().find(hql);
		if (!list.isEmpty()){
			Iterator it = list.iterator();
			int step=0;
			while(it.hasNext()){
				step++;
				toOrderStr="";
				QaNav a =(QaNav) it.next();
				if (step <= 9){
					toOrderStr+="00"+step;
				}else if(step > 9 && step <= 99){
					toOrderStr+="0"+step;
				}else{
					toOrderStr+=step;
				}
				toOrderStr=parentOrderStr+toOrderStr;
				
//				hql = "update QaNav a set a.displayOrder=replace(a.displayOrder,'"+a.getDisplayOrder()+"','"+toOrderStr+"') where  a.parentNav.id="+parentNavId;
//				this.getHibernateTemplate().bulkUpdate(hql);
				
				a.setDisplayOrder(toOrderStr);
				this.getHibernateTemplate().saveOrUpdate(a);
				
//				this.getHibernateTemplate().bulkUpdate(hql);
				
			}
		}
		// TODO Auto-generated method stub
		return true;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<QaNavShowModel> findAllQaNavModel(String prefix,
			String filler) {

		String hql="from QaNav a order by a.displayOrder asc";
		List<QaNav> list = this.getHibernateTemplate().find(hql);
		
		List<QaNavShowModel> modelList=new ArrayList();

		int displayOrderStrlength;
		String prefixAll,displayOrderStr;
		if (!list.isEmpty()){
			Iterator it = list.iterator();
			while(it.hasNext()){
				QaNavShowModel m=new QaNavShowModel();
				QaNav a =(QaNav) it.next();
				m.setQaNav(a);
				if (a.getDisplayOrder()!=null){
					displayOrderStr=a.getDisplayOrder();
					displayOrderStr=displayOrderStr.substring(1, displayOrderStr.length());
					//System.out.println("测试产生的字符串："+displayOrderStr);
					displayOrderStrlength=displayOrderStr.length()/3;
					prefixAll=StringUtil.countStr(displayOrderStrlength-1, filler);
				}else{
					prefixAll="";
				}
				prefixAll+=prefix;
				m.setPrefixStr(prefixAll);
				
				modelList.add(m);
				
				
			}

		}
		
		return modelList;
	}

	@Override
	public boolean swapQaNav(QaNav s, QaNav t) {
		String sOrderStr,tOrderStr;
//		int sParentFooterLeft,sParentFooterRight,tParentFooterLeft,tParentFooterRight;
		
		sOrderStr=s.getDisplayOrder();
		
		tOrderStr=t.getDisplayOrder();
		

		
		s.setDisplayOrder(tOrderStr);
		t.setDisplayOrder(sOrderStr);
		
		modify(s,0);
		
		modify(t,0);
		
		
		
		if (s.getParentNav()==t.getParentNav()){
			sortDisplayOrder(s.getParentNav().getId());
		}else{
			sortDisplayOrder(s.getParentNav().getId());
			sortDisplayOrder(t.getParentNav().getId());
		}
		
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean modify(QaNav qn, int mod) {
		this.getHibernateTemplate().saveOrUpdate(qn);
		return true;
	}

}
