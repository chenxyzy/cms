package com.lerx.bbs.dao.imp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lerx.bbs.dao.IBbsForumDao;
import com.lerx.bbs.util.ForumUtil;
import com.lerx.bbs.util.vo.BbsForumShowModel;
import com.lerx.bbs.vo.BbsForum;
import com.lerx.sys.util.StringUtil;

public class BbsForumDaoImp extends HibernateDaoSupport implements IBbsForumDao {

	@Override
	public boolean addForum(BbsForum forum, BbsForum parentForum) {

		try {
			int initParentForumFooterRight=parentForum.getFooterRight();
			String hql="update BbsForum a set a.footerLeft=a.footerLeft+2 where a.footerLeft>"+initParentForumFooterRight;
			this.getHibernateTemplate().bulkUpdate(hql);
			
			hql="update BbsForum a set a.footerRight=a.footerRight+2 where a.footerRight>="+initParentForumFooterRight;
			this.getHibernateTemplate().bulkUpdate(hql);
			
			forum.setFooterLeft(initParentForumFooterRight);
			forum.setFooterRight(initParentForumFooterRight+1);
			hql="select count(*) from BbsForum a where a.rootForum="+parentForum.getId();
			int count=(Integer) this.getHibernateTemplate().find(hql).get(0)+1;
			String displayOrder=parentForum.getDisplayOrder();
			if (count <= 9){
				displayOrder+="00"+count;
			}else if(count > 9 && count <= 99){
				displayOrder+="0"+count;
			}else{
				displayOrder+=count;
			}
			forum.setDisplayOrder(displayOrder);
			forum.setState(true);
			forum.setShare(true);
			this.getHibernateTemplate().saveOrUpdate(forum);
			sortDisplayOrder(parentForum.getId());
			return true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	
	}

	@Override
	public boolean addForum(BbsForum forum) {

		try {
			String hql;
			int initParentGroupFooterRight;
			if (forum.getRootForum()==null){
				hql="select max(a.footerRight) from BbsForum a";
				if (this.getHibernateTemplate().find(hql).get(0)==null){
					initParentGroupFooterRight=1;
				}else{
					initParentGroupFooterRight=(Integer) this.getHibernateTemplate().find(hql).get(0) + 1;
				}
			}else{
				initParentGroupFooterRight=forum.getRootForum().getFooterRight();
				
			}
			hql="update BbsForum a set a.footerLeft=a.footerLeft+2 where a.footerLeft>"+initParentGroupFooterRight;
			this.getHibernateTemplate().bulkUpdate(hql);
			
			hql="update BbsForum a set a.footerRight=a.footerRight+2 where a.footerRight>="+initParentGroupFooterRight;
			this.getHibernateTemplate().bulkUpdate(hql);

			forum.setFooterLeft(initParentGroupFooterRight);
			forum.setFooterRight(initParentGroupFooterRight+1);
			if (forum.getRootForum()==null){
				hql="select count(*) from BbsForum a where a.rootForum is null";
				
			}else{
				hql="select count(*) from BbsForum a where a.rootForum.id="+forum.getRootForum().getId();
				
			}
			long count= (Long) this.getHibernateTemplate().find(hql).get(0);
			count ++;
			String displayOrder;
			if (forum.getRootForum()==null){
				displayOrder="o";
			}else{
				displayOrder=forum.getRootForum().getDisplayOrder();
			}
			if (count <= 9){
				displayOrder+="00"+count;
			}else if(count > 9 && count <= 99){
				displayOrder+="0"+count;
			}else{
				displayOrder+=count;
			}
			
			forum.setDisplayOrder(displayOrder);
			forum.setState(true);
			forum.setShare(true);
			if (forum.getRootForum()==null){
				forum.setAsClass(true);
			}
			
//			forum.setShowAll(true);
//			forum.setShowOnParent(true);
			this.getHibernateTemplate().saveOrUpdate(forum);
			if (forum.getRootForum()==null){
				sortDisplayOrder(0);
			}else{
				sortDisplayOrder(forum.getRootForum().getId());
			}
			
			return true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	
	}

	@Override
	public boolean delForum(BbsForum forum) {
		int parentId;
		if (forum.getRootForum()==null){
			parentId=0;
		}else{
			parentId=forum.getRootForum().getId();
		}
		String hql="select count(*) from BbsForum a where a.rootForum.id="+forum.getId();
		if ((Long)this.getHibernateTemplate().find(hql).get(0) > 0){
			return false;
		}
		//如果有子分类，不予删除  待写
		try {
			hql="update BbsForum a set a.footerRight=a.footerRight-2 where a.footerRight>"+forum.getFooterRight();
			this.getHibernateTemplate().bulkUpdate(hql);
			hql="update BbsForum a set a.footerLeft=a.footerLeft-2 where a.footerLeft>"+forum.getFooterRight();
			this.getHibernateTemplate().bulkUpdate(hql);

			this.getHibernateTemplate().delete(forum);
			sortDisplayOrder(parentId);
			return true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delForumById(int id) {

		String hql;
		//如果有子分类，不予删除  待写
		BbsForum forum=(BbsForum) this.getHibernateTemplate().get(
				"com.lerx.bbs.vo.BbsForum", id);
		int parentId;
		if (forum.getRootForum()==null){
			parentId=0;
		}else{
			parentId=forum.getRootForum().getId();
		}
		hql="select count(*) from BbsForum a where a.rootForum.id="+id;
		if ((Long)this.getHibernateTemplate().find(hql).get(0) > 0){
			return false;
		}
				
		
		try {
			
			hql="update BbsForum a set a.footerRight=a.footerRight-2 where a.footerRight>"+forum.getFooterRight();
			this.getHibernateTemplate().bulkUpdate(hql);
			hql="update BbsForum a set a.footerLeft=a.footerLeft-2 where a.footerLeft>"+forum.getFooterRight();
			this.getHibernateTemplate().bulkUpdate(hql);
			

			this.getHibernateTemplate().delete(forum);
			
			sortDisplayOrder(parentId);
			return true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			return false;
		}
	
	}

	@Override
	public boolean modifyForum(BbsForum forum) {
		if (forum.getRootForum()==null){
			forum.setAsClass(true);
		}
		this.getHibernateTemplate().saveOrUpdate(forum);
		return true;
	}

	@Override
	public boolean modifyAndMoveBbsForum(BbsForum forum, int toParentId) {

		
		//从数据库中取真实的左脚和右脚
		BbsForum aTrue =  findBbsForumById(forum.getId());
		int trueLeft,trueRight;
		trueLeft= aTrue.getFooterLeft();
		trueRight= aTrue.getFooterRight();
		
		//当前记录左右脚跨度+1
		int footSpanAll = (trueRight - trueLeft) + 1;
		
		//System.out.println("观察：id:"+BbsForum.getId() + " 左脚："+trueLeft + " 右脚："+trueRight + " 跨度：" + footSpanAll);
		
		forum.setFooterLeft(trueLeft);
		forum.setFooterRight(trueRight);
		
		//System.out.println("测试点2---");
		//将本记录及记录下的脚值变为负数
		String hql="update BbsForum a set a.footerRight= 0 - a.footerRight,a.footerLeft= 0 - a.footerLeft where a.footerLeft>="+trueLeft + " and a.footerRight <=" + trueRight;
		this.getHibernateTemplate().bulkUpdate(hql);
		//System.out.println("测试点3---");
		
		//将大于当前右脚的记录的左右脚按当前记录的左右脚跨度+1进行更新
		hql = "update BbsForum a set a.footerLeft = a.footerLeft-"+footSpanAll + " where a.footerLeft > " + trueRight;
		this.getHibernateTemplate().bulkUpdate(hql);
		hql = "update BbsForum a set a.footerRight=a.footerRight-" + footSpanAll + " where a.footerRight > " + trueRight;
		this.getHibernateTemplate().bulkUpdate(hql);
//		System.out.println("测试点4---");
//		//更新目录的右脚及大于右脚的记录
//			//取目录左右脚，因为将会改变
		int toParentFootRight;
		if (toParentId!=0){
			toParentFootRight=findBbsForumById(toParentId).getFooterRight();
		}else{ //如果移动至根
			hql="select max(a.footerRight) from BbsForum a";
			if (this.getHibernateTemplate().find(hql).get(0)==null){
				toParentFootRight=1;
			}else{
				toParentFootRight=(Integer) this.getHibernateTemplate().find(hql).get(0) + 1;
			}
			
		}
		

		hql="update BbsForum a set a.footerLeft=a.footerLeft+"+footSpanAll+" where a.footerLeft>"+toParentFootRight;
		this.getHibernateTemplate().bulkUpdate(hql);
		
		hql="update BbsForum a set a.footerRight=a.footerRight+"+footSpanAll+" where a.footerRight>="+toParentFootRight;
		this.getHibernateTemplate().bulkUpdate(hql);
//		因为数据库中的当前记录的左右值已变，用aTrue再取
//		//更新当前及其子记录(已变成负数)
		aTrue=null;
		aTrue =  findBbsForumById(forum.getId());
//		System.out.println("值左:"+aTrue.getFooterLeft());
//		System.out.println("值右:"+aTrue.getFooterRight());
		
		int spanValue=toParentFootRight - trueLeft;
		hql="update BbsForum a set a.footerLeft=a.footerLeft-"+spanValue+",a.footerRight=a.footerRight-"+spanValue+" where a.footerLeft<="+aTrue.getFooterLeft()+" and a.footerRight>=" + aTrue.getFooterRight();
		
		this.getHibernateTemplate().bulkUpdate(hql);
		
//		因为数据库中的当前记录的左右值已变，用aTrue再取		
		aTrue=null;
		aTrue =  findBbsForumById(forum.getId());
		trueLeft= aTrue.getFooterLeft();
		trueRight= aTrue.getFooterRight();
		String currentDisStr=aTrue.getDisplayOrder();
		
		hql="update BbsForum a set a.footerLeft=ABS(a.footerLeft),a.footerRight=ABS(a.footerRight) where a.footerLeft<0 or a.footerRight<0";
		//System.out.println("测试hql:"+hql);
		this.getHibernateTemplate().bulkUpdate(hql);
		
		String displayOrder;
		//更新当前值的父对象及displayOrderStr
		if (toParentId==0){
			forum.setRootForum(null);
			forum.setAsClass(true);
			hql="select max(a.displayOrder) from BbsForum a where a.rootForum is null";
			displayOrder="o";
		}else{
			forum.setRootForum(findBbsForumById(toParentId));
			hql="select max(a.displayOrder) from BbsForum a where a.rootForum.id="+toParentId;
			//hql="select count(*) from BbsForum a where a.parentGroup="+toParentId;
			displayOrder=forum.getRootForum().getDisplayOrder();
		}
		
		
		int lastOrder;
		String lastOrderStr;
		//System.out.println("最后测试点："+this.getHibernateTemplate().find(hql).get(0));
		if (this.getHibernateTemplate().find(hql).get(0)!=null){
			lastOrderStr = (String) this.getHibernateTemplate().find(hql).get(0);
			lastOrderStr = lastOrderStr.substring(lastOrderStr.length()-3, lastOrderStr.length());
			lastOrder = Integer.valueOf(lastOrderStr).intValue();
		}else{
			lastOrder=0;
		}
		lastOrder ++;
		int count = lastOrder;
		//initParentGroupFooterRight=(Integer) this.getHibernateTemplate().find(hql).get(0) + 1;
		//Long count=(Long) this.getHibernateTemplate().find(hql).get(0)+1;
		//System.out.println("测试值总数如果为根："+count);
		if (count <= 9){
			displayOrder+="00"+count;
		}else if(count > 9 && count <= 99){
			displayOrder+="0"+count;
		}else{
			displayOrder+=count;
		}
		forum.setDisplayOrder(displayOrder);
		//update dede_addonarticle set body=replace(body ,'大法','方法')
		
//		因为数据库中的当前记录的左右值已变，用aTrue再取		
		aTrue=null;
		aTrue =  findBbsForumById(forum.getId());
		trueLeft= aTrue.getFooterLeft();
		trueRight= aTrue.getFooterRight();
		hql = "update BbsForum a set a.displayOrder=replace(a.displayOrder,'"+currentDisStr+"','"+displayOrder+"') where a.footerLeft>"+trueLeft + " and a.footerRight<"+trueRight;
		this.getHibernateTemplate().bulkUpdate(hql);
		forum.setFooterLeft(trueLeft);
		forum.setFooterRight(trueRight);
		this.getHibernateTemplate().saveOrUpdate(forum);
		
		sortDisplayOrder(toParentId); //将目录orderStr重新排序
//		
//		// TODO Auto-generated method stub
		return true;
	
	}

	@Override
	public BbsForum findBbsForumById(int id) {
		return (BbsForum) this.getHibernateTemplate().get(
				"com.lerx.bbs.vo.BbsForum", id);
	}

	@Override
	public BbsForum findParentBbsForumById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BbsForum> findBbsForumByParent(BbsForum parentForum) {
		String hql="from BbsForum a where a.rootForum.id="+parentForum.getId()+" order by a.displayOrder asc";
		List<BbsForum> list = this.getHibernateTemplate().find(hql);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BbsForum> findBbsForumByParentId(int parentForumId) {
		String hql;
		if (parentForumId>0){
			hql="from BbsForum a where a.rootForum.id="+parentForumId + " order by a.displayOrder asc";
		}else{
			hql="from BbsForum a where a.rootForum is null order by a.displayOrder asc";
		}
		
		List<BbsForum> list = this.getHibernateTemplate().find(hql);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BbsForum> findAllBbsForum() {
		String hql="from BbsForum a order by a.displayOrder asc";
		List<BbsForum> list = this.getHibernateTemplate().find(hql);
		return list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<BbsForumShowModel> findAllBbsForumModel(String prefix,
			String filler) {
//		System.out.println("测试点2");
		String hql="from BbsForum a order by a.displayOrder asc";
		List<BbsForum> list = this.getHibernateTemplate().find(hql);
		
		List<BbsForumShowModel> modelList=new ArrayList();

		int displayOrderStrlength;
		String prefixAll,displayOrderStr;
		if (!list.isEmpty()){
			Iterator it = list.iterator();
			while(it.hasNext()){
				BbsForumShowModel m=new BbsForumShowModel();
				BbsForum a =(BbsForum) it.next();
				m.setForum(a);
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
//		System.out.println("测试点3");
		return modelList;
	
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean sortDisplayOrder(int praentGroupId) {

		BbsForum praentGroup;
		String parentOrderStr;
		if (praentGroupId==0){
			praentGroup=null;
			parentOrderStr="o";
		}else{
			praentGroup = findBbsForumById(praentGroupId);
			parentOrderStr=praentGroup.getDisplayOrder();
		}
		
		//String parentOrderStr=praentGroup.getDisplayOrder();
		String toOrderStr,hql;
		int parentFooterLeft,parentFooterRight;
		
		if (praentGroupId==0){
			parentFooterLeft=0;
			hql="select max(a.footerRight) from BbsForum a";
			if (this.getHibernateTemplate().find(hql).get(0)==null){
				parentFooterRight=1;
			}else{
				parentFooterRight=(Integer) this.getHibernateTemplate().find(hql).get(0) + 1;
			}
			
		}else{
			parentFooterLeft=praentGroup.getFooterLeft();
			parentFooterRight=praentGroup.getFooterRight();
		}
		
		
		if (praentGroupId==0){
			hql="from BbsForum a where a.footerLeft>"+parentFooterLeft+" and a.footerRight<"+parentFooterRight+" and a.rootForum is null order by length(a.displayOrder) asc,a.displayOrder asc";
			
		}else{
			hql="from BbsForum a where a.footerLeft>"+parentFooterLeft+" and a.footerRight<"+parentFooterRight+" and a.rootForum.id="+praentGroup.getId()+" order by length(a.displayOrder) asc,a.displayOrder asc";
			
		}
		
		
		List<BbsForum> list = this.getHibernateTemplate().find(hql);
		if (!list.isEmpty()){
			Iterator it = list.iterator();
			int step=0;
			while(it.hasNext()){
				step++;
				toOrderStr="";
				BbsForum a =(BbsForum) it.next();
				if (step <= 9){
					toOrderStr+="00"+step;
				}else if(step > 9 && step <= 99){
					toOrderStr+="0"+step;
				}else{
					toOrderStr+=step;
				}
				toOrderStr=parentOrderStr+toOrderStr;
				
//				hql = "update BbsForum a set a.displayOrder=replace(a.displayOrder,'"+curOrderStr+"','"+toOrderStr+"') where a.footerLeft>"+parentFooterLeft + " and a.footerRight<"+parentFooterRight;
//				this.getHibernateTemplate().bulkUpdate(hql);
				
				hql = "update BbsForum a set a.displayOrder=replace(a.displayOrder,'"+a.getDisplayOrder()+"','"+toOrderStr+"') where a.footerLeft>"+a.getFooterLeft() + " and a.footerRight<"+a.getFooterRight();
				this.getHibernateTemplate().bulkUpdate(hql);
				
				a.setDisplayOrder(toOrderStr);
				this.getHibernateTemplate().saveOrUpdate(a);
				
			}
		}
		// TODO Auto-generated method stub
		return true;
	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BbsForum> findBbsForumShowOnIndex() {
		String hql="from BbsForum a where a.state=true and a.rootForum=null";
		List<BbsForum> list = this.getHibernateTemplate().find(hql);
		return list;
	}

	@Override
	public boolean swapBbsForum(BbsForum s, BbsForum t) {

		String sOrderStr,tOrderStr,hql,stmp,ttmp;
		int sParentFooterLeft,sParentFooterRight,tParentFooterLeft,tParentFooterRight;
		
		sOrderStr=s.getDisplayOrder();
		stmp=StringUtil.strReplace(sOrderStr,"o","s");
		sParentFooterLeft=s.getFooterLeft();
		sParentFooterRight=s.getFooterRight();
		
		tOrderStr=t.getDisplayOrder();
		ttmp=StringUtil.strReplace(sOrderStr,"o","t");
		tParentFooterLeft=t.getFooterLeft();
		tParentFooterRight=t.getFooterRight();
		
		hql = "update BbsForum a set a.displayOrder=replace(a.displayOrder,'"+sOrderStr+"','"+ttmp+"') where a.footerLeft>"+sParentFooterLeft + " and a.footerRight<"+sParentFooterRight;
		this.getHibernateTemplate().bulkUpdate(hql);
		
		
		hql = "update BbsForum a set a.displayOrder=replace(a.displayOrder,'"+tOrderStr+"','"+stmp+"') where a.footerLeft>"+tParentFooterLeft + " and a.footerRight<"+tParentFooterRight;
		this.getHibernateTemplate().bulkUpdate(hql);
		
		hql = "update BbsForum a set a.displayOrder=replace(a.displayOrder,'"+ttmp+"','"+tOrderStr+"') where a.footerLeft>"+sParentFooterLeft + " and a.footerRight<"+sParentFooterRight;
		this.getHibernateTemplate().bulkUpdate(hql);
		
		
		hql = "update BbsForum a set a.displayOrder=replace(a.displayOrder,'"+stmp+"','"+sOrderStr+"') where a.footerLeft>"+tParentFooterLeft + " and a.footerRight<"+tParentFooterRight;
		this.getHibernateTemplate().bulkUpdate(hql);
		
		s.setDisplayOrder(tOrderStr);
		t.setDisplayOrder(sOrderStr);
		
		modifyForum(s);
		
		modifyForum(t);
		int sParentId,tParentId;
		if (s.getRootForum()==null){
			sParentId=0;
		}else{
			sParentId=s.getRootForum().getId();
		}
		if (t.getRootForum()==null){
			tParentId=0;
		}else{
			tParentId=t.getRootForum().getId();
		}
		if (s.getRootForum()==t.getRootForum()){
			sortDisplayOrder(sParentId);
		}else{
			sortDisplayOrder(sParentId);
			sortDisplayOrder(tParentId);
		}
		
		// TODO Auto-generated method stub
		return false;
	
	}

	@Override
	public List<BbsForum> findIPRange(BbsForum f) {
		String hql="from BbsForum g where  g.hostsAllow is not null and trim(g.hostsAllow)<>'' and g.footerLeft<="+f.getFooterLeft()+" and g.footerRight>="+f.getFooterRight() + " order by g.footerRight asc";
		@SuppressWarnings("unchecked")
		List<BbsForum> list = this.getHibernateTemplate().find(hql);
		if (list.isEmpty()){
			return null;
		}else{
			return list;
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public BbsForum findLastPollParent(BbsForum f) {
		if (ForumUtil.pollChk(f)){
			return f;
		}else{
			String hql="from BbsForum f where (f.pollFmt+0)>0 and f.footerLeft<"+f.getFooterLeft()+" and f.footerRight>"+f.getFooterRight() + " order by f.footerRight asc";
			@SuppressWarnings("unchecked")
			List<BbsForum> list = this.getHibernateTemplate().find(hql);
			if (list.isEmpty()){
				return null;
			}else{
				return list.get(0);
			}
		
		}
	}

}
