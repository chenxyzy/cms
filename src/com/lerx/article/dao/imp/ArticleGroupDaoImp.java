package com.lerx.article.dao.imp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lerx.article.dao.IArticleGroupDao;
import com.lerx.article.util.ArticleGroupUtil;
import com.lerx.article.util.vo.ArticleGroupShowModel;
import com.lerx.article.vo.ArticleGroup;
import com.lerx.style.site.vo.SiteStyle;
import com.lerx.sys.util.StringUtil;


public class ArticleGroupDaoImp extends HibernateDaoSupport implements
		IArticleGroupDao {

	@Override
	public boolean add(ArticleGroup articleGroup) {
		try {
			String hql;
			int initParentGroupFooterRight;
			if (articleGroup.getParentGroup()==null){
				hql="select max(a.footerRight) from ArticleGroup a";
				if (this.getHibernateTemplate().find(hql).get(0)==null){
					initParentGroupFooterRight=1;
				}else{
					initParentGroupFooterRight=(Integer) this.getHibernateTemplate().find(hql).get(0) + 1;
				}
			}else{
				initParentGroupFooterRight=articleGroup.getParentGroup().getFooterRight();
				
			}
			hql="update ArticleGroup a set a.footerLeft=a.footerLeft+2 where a.footerLeft>"+initParentGroupFooterRight;
			this.getHibernateTemplate().bulkUpdate(hql);
			
			hql="update ArticleGroup a set a.footerRight=a.footerRight+2 where a.footerRight>="+initParentGroupFooterRight;
			this.getHibernateTemplate().bulkUpdate(hql);

			articleGroup.setFooterLeft(initParentGroupFooterRight);
			articleGroup.setFooterRight(initParentGroupFooterRight+1);
			if (articleGroup.getParentGroup()==null){
				hql="select count(*) from ArticleGroup a where a.parentGroup is null";
				
			}else{
				hql="select count(*) from ArticleGroup a where a.parentGroup.id="+articleGroup.getParentGroup().getId();
				
			}
			long count= (Long) this.getHibernateTemplate().find(hql).get(0);
			count ++;
			String displayOrder;
			if (articleGroup.getParentGroup()==null){
				displayOrder="o";
			}else{
				displayOrder=articleGroup.getParentGroup().getDisplayOrder();
			}
			if (count <= 9){
				displayOrder+="00"+count;
			}else if(count > 9 && count <= 99){
				displayOrder+="0"+count;
			}else{
				displayOrder+=count;
			}
			
			articleGroup.setDisplayOrder(displayOrder);
			articleGroup.setState(true);
			articleGroup.setShare(true);
			articleGroup.setShowAll(true);
			articleGroup.setShowOnParent(true);
			this.getHibernateTemplate().saveOrUpdate(articleGroup);
			if (articleGroup.getParentGroup()==null){
				sortDisplayOrder(0);
			}else{
				sortDisplayOrder(articleGroup.getParentGroup().getId());
			}
			
			return true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	@Override
	public boolean add(ArticleGroup articleGroup,
			ArticleGroup parentGroup) {
		try {
			int initParentGroupFooterRight=parentGroup.getFooterRight();
			String hql="update ArticleGroup a set a.footerLeft=a.footerLeft+2 where a.footerLeft>"+initParentGroupFooterRight;
			this.getHibernateTemplate().bulkUpdate(hql);
			
			hql="update ArticleGroup a set a.footerRight=a.footerRight+2 where a.footerRight>="+initParentGroupFooterRight;
			this.getHibernateTemplate().bulkUpdate(hql);
			
			articleGroup.setFooterLeft(initParentGroupFooterRight);
			articleGroup.setFooterRight(initParentGroupFooterRight+1);
			hql="select count(*) from ArticleGroup a where a.parentGroup="+parentGroup.getId();
			int count=(Integer) this.getHibernateTemplate().find(hql).get(0)+1;
			String displayOrder=parentGroup.getDisplayOrder();
			if (count <= 9){
				displayOrder+="00"+count;
			}else if(count > 9 && count <= 99){
				displayOrder+="0"+count;
			}else{
				displayOrder+=count;
			}
			articleGroup.setDisplayOrder(displayOrder);
			articleGroup.setState(true);
			articleGroup.setShare(true);
			articleGroup.setShowAll(true);
			articleGroup.setShowOnParent(true);
			articleGroup.setRefuseStaticHtml(false);
			this.getHibernateTemplate().saveOrUpdate(articleGroup);
			sortDisplayOrder(parentGroup.getId());
			return true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean del(ArticleGroup articleGroup) {
		if (countByAg(articleGroup.getId())>0){
			return false;
		}
		int parentId;
		if (articleGroup.getParentGroup()==null){
			parentId=0;
		}else{
			parentId=articleGroup.getParentGroup().getId();
		}
		String hql="select count(*) from ArticleGroup a where a.parentGroup.id="+articleGroup.getId();
		if ((Long)this.getHibernateTemplate().find(hql).get(0) > 0){
			return false;
		}
		//如果有子分类，不予删除  待写
		try {
			hql="update ArticleGroup a set a.footerRight=a.footerRight-2 where a.footerRight>"+articleGroup.getFooterRight();
			this.getHibernateTemplate().bulkUpdate(hql);
			hql="update ArticleGroup a set a.footerLeft=a.footerLeft-2 where a.footerLeft>"+articleGroup.getFooterRight();
			this.getHibernateTemplate().bulkUpdate(hql);

			this.getHibernateTemplate().delete(articleGroup);
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
	public boolean modify(ArticleGroup articleGroup) {
		
		this.getHibernateTemplate().saveOrUpdate(articleGroup);
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public ArticleGroup findById(int id) {
		return (ArticleGroup) this.getHibernateTemplate().get(
				ArticleGroup.class, id);
		
	}

	@Override
	public List<ArticleGroup> findParentById(int id) {
		ArticleGroup cur=findById(id);
		String hql="from ArticleGroup a where a.footerLeft<"+cur.getFooterLeft()+" and a.footerRight>"+cur.getFooterRight()+" order by a.displayOrder asc";
		@SuppressWarnings("unchecked")
		List<ArticleGroup> list = (List<ArticleGroup>)this.getHibernateTemplate().find(hql);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ArticleGroup> findByParent(ArticleGroup parentGroup) {
		String hql="from ArticleGroup a where a.parentGroup.id="+parentGroup.getId()+" order by a.displayOrder asc";
		List<ArticleGroup> list = this.getHibernateTemplate().find(hql);
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ArticleGroup> findByParentId(int parentGroupId) {
		String hql;
		if (parentGroupId>0){
			hql="from ArticleGroup a where a.parentGroup.id="+parentGroupId + " order by a.displayOrder asc";
		}else{
			hql="from ArticleGroup a where a.parentGroup is null order by a.displayOrder asc";
		}
		
		List<ArticleGroup> list = this.getHibernateTemplate().find(hql);
		return list;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ArticleGroup> findAll() {
		String hql="from ArticleGroup a order by a.displayOrder asc";
		List<ArticleGroup> list = this.getHibernateTemplate().find(hql);
		return list;
	}
	
	@Override
	public List<ArticleGroup> findAllChanged() {
		String hql="from ArticleGroup a where a.changed is true order by a.displayOrder asc";
		@SuppressWarnings("unchecked")
		List<ArticleGroup> list = this.getHibernateTemplate().find(hql);
		return list;
	}
	
	@SuppressWarnings({ "unchecked" })
	@Override
	public List<ArticleGroupShowModel> findAllModel(String prefix,String filler) {
		String hql="from ArticleGroup a order by a.displayOrder asc";
		List<ArticleGroup> list = this.getHibernateTemplate().find(hql);
		
		List<ArticleGroupShowModel> modelList=new ArrayList<ArticleGroupShowModel>();

		int displayOrderStrlength;
		String prefixAll,displayOrderStr;
		if (!list.isEmpty()){
			Iterator<ArticleGroup> it = list.iterator();
			while(it.hasNext()){
				ArticleGroupShowModel m=new ArticleGroupShowModel();
				ArticleGroup a =it.next();
				m.setArticleGroup(a);
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
	public boolean delById(int id) {
		if (countByAg(id)>0){
			return false;
		}
		
		String hql;
		//如果有子分类，不予删除  待写
		ArticleGroup articleGroup=(ArticleGroup) this.getHibernateTemplate().get(
				ArticleGroup.class, id);
		int parentId;
		if (articleGroup.getParentGroup()==null){
			parentId=0;
		}else{
			parentId=articleGroup.getParentGroup().getId();
		}
		hql="select count(*) from ArticleGroup a where a.parentGroup.id="+id;
		if ((Long)this.getHibernateTemplate().find(hql).get(0) > 0){
			return false;
		}
				
		
		try {
			
			hql="update ArticleGroup a set a.footerRight=a.footerRight-2 where a.footerRight>"+articleGroup.getFooterRight();
			this.getHibernateTemplate().bulkUpdate(hql);
			hql="update ArticleGroup a set a.footerLeft=a.footerLeft-2 where a.footerLeft>"+articleGroup.getFooterRight();
			this.getHibernateTemplate().bulkUpdate(hql);
			

			this.getHibernateTemplate().delete(articleGroup);
			
			sortDisplayOrder(parentId);
			return true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}

	@Override
	public boolean modifyAndMove(ArticleGroup articleGroup,
			int toParentId) {
		
		//从数据库中取真实的左脚和右脚
		ArticleGroup aTrue =  findById(articleGroup.getId());
		int trueLeft,trueRight;
		trueLeft= aTrue.getFooterLeft();
		trueRight= aTrue.getFooterRight();
		
		//当前记录左右脚跨度+1
		int footSpanAll = (trueRight - trueLeft) + 1;
		
		//System.out.println("观察：id:"+articleGroup.getId() + " 左脚："+trueLeft + " 右脚："+trueRight + " 跨度：" + footSpanAll);
		
		articleGroup.setFooterLeft(trueLeft);
		articleGroup.setFooterRight(trueRight);
		
		//System.out.println("测试点2---");
		//将本记录及记录下的脚值变为负数
		String hql="update ArticleGroup a set a.footerRight= 0 - a.footerRight,a.footerLeft= 0 - a.footerLeft where a.footerLeft>="+trueLeft + " and a.footerRight <=" + trueRight;
		this.getHibernateTemplate().bulkUpdate(hql);
		//System.out.println("测试点3---");
		
		//将大于当前右脚的记录的左右脚按当前记录的左右脚跨度+1进行更新
		hql = "update ArticleGroup a set a.footerLeft = a.footerLeft-"+footSpanAll + " where a.footerLeft > " + trueRight;
		this.getHibernateTemplate().bulkUpdate(hql);
		hql = "update ArticleGroup a set a.footerRight=a.footerRight-" + footSpanAll + " where a.footerRight > " + trueRight;
		this.getHibernateTemplate().bulkUpdate(hql);
//		System.out.println("测试点4---");
//		//更新目录的右脚及大于右脚的记录
//			//取目录左右脚，因为将会改变
		int toParentFootRight;
		if (toParentId!=0){
			toParentFootRight=findById(toParentId).getFooterRight();
		}else{ //如果移动至根
			hql="select max(a.footerRight) from ArticleGroup a";
			if (this.getHibernateTemplate().find(hql).get(0)==null){
				toParentFootRight=1;
			}else{
				toParentFootRight=(Integer) this.getHibernateTemplate().find(hql).get(0) + 1;
			}
			
		}
		

		hql="update ArticleGroup a set a.footerLeft=a.footerLeft+"+footSpanAll+" where a.footerLeft>"+toParentFootRight;
		this.getHibernateTemplate().bulkUpdate(hql);
		
		hql="update ArticleGroup a set a.footerRight=a.footerRight+"+footSpanAll+" where a.footerRight>="+toParentFootRight;
		this.getHibernateTemplate().bulkUpdate(hql);
//		因为数据库中的当前记录的左右值已变，用aTrue再取
//		//更新当前及其子记录(已变成负数)
		aTrue=null;
		aTrue =  findById(articleGroup.getId());
//		System.out.println("值左:"+aTrue.getFooterLeft());
//		System.out.println("值右:"+aTrue.getFooterRight());
		
		int spanValue=toParentFootRight - trueLeft;
		hql="update ArticleGroup a set a.footerLeft=a.footerLeft-"+spanValue+",a.footerRight=a.footerRight-"+spanValue+" where a.footerLeft<="+aTrue.getFooterLeft()+" and a.footerRight>=" + aTrue.getFooterRight();
		
		this.getHibernateTemplate().bulkUpdate(hql);
		
//		因为数据库中的当前记录的左右值已变，用aTrue再取		
		aTrue=null;
		aTrue =  findById(articleGroup.getId());
		trueLeft= aTrue.getFooterLeft();
		trueRight= aTrue.getFooterRight();
		String currentDisStr=aTrue.getDisplayOrder();
		
		hql="update ArticleGroup a set a.footerLeft=ABS(a.footerLeft),a.footerRight=ABS(a.footerRight) where a.footerLeft<0 or a.footerRight<0";
		//System.out.println("测试hql:"+hql);
		this.getHibernateTemplate().bulkUpdate(hql);
		
		String displayOrder;
		//更新当前值的父对象及displayOrderStr
		if (toParentId==0){
			articleGroup.setParentGroup(null);
			hql="select max(a.displayOrder) from ArticleGroup a where a.parentGroup is null";
			displayOrder="o";
		}else{
			articleGroup.setParentGroup(findById(toParentId));
			//2011/10/14 发现差一个.id
			hql="select max(a.displayOrder) from ArticleGroup a where a.parentGroup.id="+toParentId;
			//hql="select count(*) from ArticleGroup a where a.parentGroup="+toParentId;
			displayOrder=articleGroup.getParentGroup().getDisplayOrder();
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
		articleGroup.setDisplayOrder(displayOrder);
		//update dede_addonarticle set body=replace(body ,'大法','方法')
		
//		因为数据库中的当前记录的左右值已变，用aTrue再取		
		aTrue=null;
		aTrue =  findById(articleGroup.getId());
		trueLeft= aTrue.getFooterLeft();
		trueRight= aTrue.getFooterRight();
		hql = "update ArticleGroup a set a.displayOrder=replace(a.displayOrder,'"+currentDisStr+"','"+displayOrder+"') where a.footerLeft>"+trueLeft + " and a.footerRight<"+trueRight;
		this.getHibernateTemplate().bulkUpdate(hql);
		articleGroup.setFooterLeft(trueLeft);
		articleGroup.setFooterRight(trueRight);
		if (articleGroup.getStyle().getId()==0){
			articleGroup.setStyle(null);
		}
		this.getHibernateTemplate().saveOrUpdate(articleGroup);
		
		sortDisplayOrder(toParentId); //将目录orderStr重新排序
//		
//		// TODO Auto-generated method stub
		return true;
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public boolean sortDisplayOrder(int praentGroupId) {
		ArticleGroup praentGroup;
		String parentOrderStr;
		if (praentGroupId==0){
			praentGroup=null;
			parentOrderStr="o";
		}else{
			praentGroup = findById(praentGroupId);
			parentOrderStr=praentGroup.getDisplayOrder();
		}
		
		//String parentOrderStr=praentGroup.getDisplayOrder();
		String toOrderStr,hql;
		int parentFooterLeft,parentFooterRight;
		
		if (praentGroupId==0){
			parentFooterLeft=0;
			hql="select max(a.footerRight) from ArticleGroup a";
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
			hql="from ArticleGroup a where a.footerLeft>"+parentFooterLeft+" and a.footerRight<"+parentFooterRight+" and a.parentGroup is null order by length(a.displayOrder) asc,a.displayOrder asc";
			
		}else{
			hql="from ArticleGroup a where a.footerLeft>"+parentFooterLeft+" and a.footerRight<"+parentFooterRight+" and a.parentGroup.id="+praentGroup.getId()+" order by length(a.displayOrder) asc,a.displayOrder asc";
			
		}
		
		
		List<ArticleGroup> list = this.getHibernateTemplate().find(hql);
		if (!list.isEmpty()){
			Iterator<ArticleGroup> it = list.iterator();
			int step=0;
			while(it.hasNext()){
				step++;
				toOrderStr="";
				ArticleGroup a =it.next();
				if (step <= 9){
					toOrderStr+="00"+step;
				}else if(step > 9 && step <= 99){
					toOrderStr+="0"+step;
				}else{
					toOrderStr+=step;
				}
				toOrderStr=parentOrderStr+toOrderStr;
				
//				hql = "update ArticleGroup a set a.displayOrder=replace(a.displayOrder,'"+curOrderStr+"','"+toOrderStr+"') where a.footerLeft>"+parentFooterLeft + " and a.footerRight<"+parentFooterRight;
//				System.out.println("测试");
				
				hql = "update ArticleGroup a set a.displayOrder=replace(a.displayOrder,'"+a.getDisplayOrder()+"','"+toOrderStr+"') where a.footerLeft>"+a.getFooterLeft() + " and a.footerRight<"+a.getFooterRight();
				this.getHibernateTemplate().bulkUpdate(hql);
				
				a.setDisplayOrder(toOrderStr);
				this.getHibernateTemplate().saveOrUpdate(a);
				
//				this.getHibernateTemplate().bulkUpdate(hql);
				
			}
		}
		// TODO Auto-generated method stub
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ArticleGroup> findShowOnIndex(int mod) {
		String hql;
//		if (mod==0){
			hql="from ArticleGroup a where a.state=true and a.showOnIndex=true";
//		}else{
//			hql="from ArticleGroup a where a.state=true";
//		}
		
		List<ArticleGroup> list = this.getHibernateTemplate().find(hql);
		return list;
	}

	@Override
	public boolean swap(ArticleGroup s, ArticleGroup t) {
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
		
		hql = "update ArticleGroup a set a.displayOrder=replace(a.displayOrder,'"+sOrderStr+"','"+ttmp+"') where a.footerLeft>"+sParentFooterLeft + " and a.footerRight<"+sParentFooterRight;
		this.getHibernateTemplate().bulkUpdate(hql);
		
		
		hql = "update ArticleGroup a set a.displayOrder=replace(a.displayOrder,'"+tOrderStr+"','"+stmp+"') where a.footerLeft>"+tParentFooterLeft + " and a.footerRight<"+tParentFooterRight;
		this.getHibernateTemplate().bulkUpdate(hql);
		
		hql = "update ArticleGroup a set a.displayOrder=replace(a.displayOrder,'"+ttmp+"','"+tOrderStr+"') where a.footerLeft>"+sParentFooterLeft + " and a.footerRight<"+sParentFooterRight;
		this.getHibernateTemplate().bulkUpdate(hql);
		
		
		hql = "update ArticleGroup a set a.displayOrder=replace(a.displayOrder,'"+stmp+"','"+sOrderStr+"') where a.footerLeft>"+tParentFooterLeft + " and a.footerRight<"+tParentFooterRight;
		this.getHibernateTemplate().bulkUpdate(hql);
		
		s.setDisplayOrder(tOrderStr);
		t.setDisplayOrder(sOrderStr);
		
		modify(s);
		
		modify(t);
		int sParentId,tParentId;
		if (s.getParentGroup()==null){
			sParentId=0;
		}else{
			sParentId=s.getParentGroup().getId();
		}
		if (t.getParentGroup()==null){
			tParentId=0;
		}else{
			tParentId=t.getParentGroup().getId();
		}
		if (s.getParentGroup()==t.getParentGroup()){
			sortDisplayOrder(sParentId);
		}else{
			sortDisplayOrder(sParentId);
			sortDisplayOrder(tParentId);
		}
		
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String findAllHostAllowStrByArticleGroup(ArticleGroup g) {
		String hql="from ArticleGroup a where a.footerLeft<="+g.getFooterLeft() + " and a.footerRight>="+g.getFooterRight() + " and a.hostsAllow  is not null and trim(a.hostsAllow)<>''";
		ArticleGroup a;
		String strRe="";
//		System.out.println("断点1");
		
		@SuppressWarnings("unchecked")
		// 下一行，在静态生成时会产生如下提示：
		/*
		 * 警告: Function template anticipated 4 arguments, but 1 arguments encountered
		 */
		//目前不清楚原因，暂不能解决，但不影响使用
		List<ArticleGroup> list = this.getHibernateTemplate().find(hql);
//		System.out.println("断点2");
		if (!list.isEmpty()){
			Iterator<ArticleGroup> it = list.iterator();
			int step=0;
			while(it.hasNext()){
				step++;
				a =it.next();
				if (step==1){
					strRe=a.getHostsAllow();
				}else{
					if (a.getHostsAllow()!=null && !a.getHostsAllow().trim().equals("")){
						strRe+=","+a.getHostsAllow();
					}
					
				}
			}
		}else{
			return null;
		}
		return strRe;
	}

	@SuppressWarnings({ "unchecked" })
	@Override
	public boolean checkShare(ArticleGroup g) {
		String hql="from ArticleGroup a where a.footerLeft<="+g.getFooterLeft() + " and a.footerRight>="+g.getFooterRight() + " and a.share=false";
		
		List<ArticleGroup> list = this.getHibernateTemplate().find(hql);
		if (!list.isEmpty()){
			return false;
		}else{
			return true;
		}
	}

	@Override
	public boolean statViews() {
		List<ArticleGroup> list = this.getHibernateTemplate().loadAll(ArticleGroup.class);
		if (!list.isEmpty()){
			//Iterator<ArticleGroup> it = list.iterator();
			long sum;
			for (ArticleGroup ag : list) {
				String hql="select sum(a.views) from ArticleThread a where a.articleGroup.footerLeft>="+ag.getFooterLeft() + " and a.articleGroup.footerRight<="+ag.getFooterRight();
				sum = (Long) this.getHibernateTemplate()
				.find(hql).get(0);
				ag.setViews(sum);
				this.getHibernateTemplate().saveOrUpdate(ag);
			}
			
		}
			
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public SiteStyle findParentForceStyle(ArticleGroup g) {
		String hql="from ArticleGroup g where g.compulsionDocStyle is true and g.state is true and g.style is not null and g.footerLeft<"+g.getFooterLeft()+" and g.footerRight>"+g.getFooterRight() + " order by g.footerRight asc";
		@SuppressWarnings("unchecked")
		List<ArticleGroup> list = this.getHibernateTemplate().find(hql);
		if (list.isEmpty()){
//			System.out.println("没有找到了！hsql:"+hql);
			return null;
		}else{
//			System.out.println("找到了！数量："+list.size());
			
			ArticleGroup ag=list.get(0);
			return ag.getStyle();
			
		}
	}

	@Override
	public boolean changed(ArticleGroup g) {
		String hql="update ArticleGroup a set a.changed=true where a.footerLeft<="+g.getFooterLeft() + " and a.footerRight>="+g.getFooterRight();
		this.getHibernateTemplate().bulkUpdate(hql);
		return false;
	}

	@Override
	public ArticleGroup findLastPollParent(ArticleGroup g) {
		if (ArticleGroupUtil.pollChk(g)){
			return g;
		}else{
			String hql="from ArticleGroup g where (g.pollFmt+0)>0 and g.footerLeft<"+g.getFooterLeft()+" and g.footerRight>"+g.getFooterRight() + " order by g.footerRight asc";
			@SuppressWarnings("unchecked")
			List<ArticleGroup> list = this.getHibernateTemplate().find(hql);
			if (list.isEmpty()){
				return null;
			}else{
				return list.get(0);
			}
		
		}
	}
	
	@Override
	public long countByAg(int gid) {
		ArticleGroup g=(ArticleGroup) this.getHibernateTemplate().get(ArticleGroup.class, gid);
		String hqlTmp = " where a.articleGroup.footerLeft >=" + g.getFooterLeft()
		+ " and a.articleGroup.footerRight<=" + g.getFooterRight();
		String hql= "select count(*) from ArticleThread a "+hqlTmp;
		long count = (Long) this.getHibernateTemplate()
		.find(hql).get(0);
		return count;
	}

}
