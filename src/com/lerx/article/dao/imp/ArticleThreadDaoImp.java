package com.lerx.article.dao.imp;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lerx.article.dao.IArticleThreadDao;
import com.lerx.article.vo.ArticleGroup;
import com.lerx.article.vo.ArticleThread;
import com.lerx.sys.util.HibernateCallbackUtil;
import com.lerx.sys.util.RsInit;
import com.lerx.sys.util.vo.HibernateCallbackUtilVo;
import com.lerx.sys.util.vo.Rs;

public class ArticleThreadDaoImp extends HibernateDaoSupport implements
		IArticleThreadDao {

	@Override
	public long add(ArticleThread thread) {
		this.getHibernateTemplate().save(thread);
//		System.out.println("增加的id:"+thread.getId());
		return thread.getId();
	}

	@Override
	public boolean del(ArticleThread thread) {
		this.getHibernateTemplate().delete(thread);
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean delById(long id) {
		this.getHibernateTemplate().delete(
				this.getHibernateTemplate().get(
						"com.lerx.article.vo.ArticleThread", id));
		return true;
	}

	@Override
	public boolean modify(ArticleThread thread) {
		this.getHibernateTemplate().saveOrUpdate(thread);
		return true;
	}

	@Override
	public ArticleThread findById(long id) {
		return (ArticleThread) this.getHibernateTemplate().get(
				ArticleThread.class, id);

	}

	@Override
	public Rs findByGroup(int groupId, int page, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean changeState(long id, boolean state) {
		ArticleThread thread = (ArticleThread) this.getHibernateTemplate().get(
				"com.lerx.article.vo.ArticleThread", id);
		thread.setState(state);

		modify(thread);
		return true;

	}

	@Override
	public Rs findByGroupAndMod(int groupId, int page,
			int pageSize, int mod, int state, boolean notice, int soul,int firstResult,boolean image,int speedMod) {
		/*
		 * mode 0 按最新 1 按浏览数 2 按平均浏览数 3按id倒序
		 */
//		long t=System.currentTimeMillis();
//		System.out.println("测试在这里");
		long count;
		String orderStr, hqlTmp;
		
		hqlTmp = "";
		
		switch (state) {
		case 1:
			if (hqlTmp.equals("")){
				hqlTmp += " where a.state<>0 ";
			}else{
				hqlTmp += " and a.state<>0 ";
			}
			
			
			break;
		case 2:
			if (hqlTmp.equals("")){
				hqlTmp += " where a.state=0 ";
			}else{
				hqlTmp += " and a.state=0 ";
			}
			
			
			break;
		default:
			break;
		}
		
		if (groupId != 0) {
			ArticleGroup g = (ArticleGroup) this.getHibernateTemplate().get(
					"com.lerx.article.vo.ArticleGroup", groupId);
			if (g==null){
				return null;
			}else{
				if (hqlTmp.equals("")){
					hqlTmp = " where (a.articleGroup.footerLeft >=" + g.getFooterLeft()
					+ " and a.articleGroup.footerRight<=" + g.getFooterRight()+") ";
				}else{
					hqlTmp += " and (a.articleGroup.footerLeft >=" + g.getFooterLeft()
					+ " and a.articleGroup.footerRight<=" + g.getFooterRight()+") ";
				}
				
			}
			
		}
		
		
		
		if (notice) {
			if (hqlTmp.equals("")) {
				hqlTmp += " where a.notice is true ";
			} else {
				hqlTmp += " and a.notice is true ";
			}

		}
		String soulOrderTmp="";
		if (soul==1){
			soulOrderTmp += ",a.soul desc";
		}else{
			soulOrderTmp += "";
		}
				
		if (image){
			if (!hqlTmp.trim().equals("")){
				hqlTmp+=" and ((a.mainImg is not null and trim(a.mainImg)<>'') or (a.thumbnail is not null and trim(a.thumbnail)<>'')) ";
			}else{
				hqlTmp+=" where ((a.mainImg is not null and trim(a.mainImg)<>'') or (a.thumbnail is not null and trim(a.thumbnail)<>'')) ";
			}
		}
		count = (Long) this.getHibernateTemplate()
				.find("select count(*) from ArticleThread a " + hqlTmp).get(0);

		

		switch (mod) {
		case 1:
			orderStr = " order by a.topOne desc"+soulOrderTmp+",a.views desc ";
			break;
		case 2:
			orderStr = " order by a.topOne desc"+soulOrderTmp+",(a.views/(TO_DAYS(NOW())-(TO_DAYS(a.addTime)-1))) desc ";
			break;
		case 3:
			orderStr = " order by a.topOne desc"+soulOrderTmp+",a.id desc ";
			break;
		default:
			orderStr = " order by a.topOne desc"+soulOrderTmp+",a.addTimeLong desc ";
			break;
		}
		
		String hql;
		
		if (speedMod==1){
//			System.out.println("模式1");
			hql = "from ArticleThread a " + hqlTmp + orderStr;
			
		}else{
//			System.out.println("模式0");
			hql = "select a.id from ArticleThread a " + hqlTmp + orderStr;
			
		}
		
		HibernateCallbackUtilVo hcuv = new HibernateCallbackUtilVo();
		hcuv.setCount(count);
		hcuv.setFirstResult(firstResult);
		hcuv.setHql(hql);
		hcuv.setIbernateTemplate(this.getHibernateTemplate());
		hcuv.setPageSize(pageSize);
		hcuv.setPage(page);
		
		return HibernateCallbackUtil.exeRs(hcuv);
		
//		HibernateCallback<Object> hc = new HibernateCallback<Object>() {
//
//			@SuppressWarnings("unchecked")
//			@Override
//			public Object doInHibernate(org.hibernate.Session session)
//					throws HibernateException, SQLException {
//				int n = pageSize;
//				if (n == 0) {
//					n = 10;
//				}
//				Query query = session.createQuery(hql);
//				if (firstResult==0){
//					query.setFirstResult((page - 1) * pageSize);
//				}else{
//					query.setFirstResult(firstResult-1);
//				}
//				
//				query.setMaxResults(n);
//
//				List<Long> list = query.list();
//
//				if (list.isEmpty()) {
//					
//				}else{
//				}
//				return list;
//			}
//
//		};
//		rs.setList(getHibernateTemplate().executeFind(hc));
//		return rs;
		// TODO Auto-generated method stub
	}

	@Override
	public Rs findByGroupAndMod(String groupIdStr,int page,
			int pageSize, int mod, int state, boolean notice, int soul,int firstResult,boolean image,int speedMod) {
		/*
		 * mode 0 按最新 1 按浏览数 2 按平均浏览数 3按id倒序
		 */
		long count;
		String orderStr, hqlTmp="";
		
		
		
		ArticleGroup g;
		if (groupIdStr==null){
			groupIdStr="";
		}
		groupIdStr=groupIdStr.trim();
		if (!groupIdStr.equals("")){
			String[] sArray = groupIdStr.split("_");
			int groupId;
			if (sArray.length>0){
				for (int step=0;step<sArray.length;step++){
					groupId=Integer.valueOf(sArray[step]);
					g = (ArticleGroup) this.getHibernateTemplate().get(
							"com.lerx.article.vo.ArticleGroup", groupId);
					if (g!=null){
						if (step==0){
							hqlTmp = " (a.articleGroup.footerLeft >=" + g.getFooterLeft()
							+ " and a.articleGroup.footerRight<=" + g.getFooterRight()+") ";
						}else{
							hqlTmp += " or (a.articleGroup.footerLeft >=" + g.getFooterLeft()
							+ " and a.articleGroup.footerRight<=" + g.getFooterRight() + ") ";
						}
					}
					
				}
				if (!hqlTmp.trim().equals("")){
					hqlTmp = " where ("+hqlTmp+") ";
				}
				
			}
		}
//		System.out.println("1:"+hqlTmp);
		switch (state) {
		case 1:
			if (hqlTmp.equals("")) {
				hqlTmp += " where a.state is true ";
			}else{
				hqlTmp += " and a.state is true ";
			}
				
			break;
		case 2:
			if (hqlTmp.equals("")) {
				hqlTmp += " where a.state is not true ";
			}else{
				hqlTmp += " and a.state is not true ";
			}
				
			break;
		default:
			break;
		}
//		System.out.println("2:"+hqlTmp);
		
		if (notice) {
			if (hqlTmp.equals("")) {
				hqlTmp += " where a.notice=true ";
			} else {
				hqlTmp += " and a.notice=true ";
			}

		}
		switch (soul) {
		case 1:
			if (hqlTmp.equals("")) {
				hqlTmp += " where a.soul is true ";
			} else {
				hqlTmp += " and a.soulis true ";
			}
			break;
		case 2:
			if (hqlTmp.equals("")) {
				hqlTmp += " where a.soul is not true ";
			} else {
				hqlTmp += " and a.soul is not true ";
			}
			break;
		default:
			break;
		}
//		System.out.println("3:"+hqlTmp);
		if (image){
			if (!hqlTmp.trim().equals("")){
				hqlTmp+=" and ((a.mainImg is not null and trim(a.mainImg)<>'') or (a.thumbnail is not null and trim(a.thumbnail)<>'')) ";
			}else{
				hqlTmp+=" where ((a.mainImg is not null and trim(a.mainImg)<>'') or (a.thumbnail is not null and trim(a.thumbnail)<>'')) ";
			}
		}
//		System.out.println("4:"+hqlTmp);
		count = (Long) this.getHibernateTemplate()
				.find("select count(*) from ArticleThread a " + hqlTmp).get(0);

		
//		Rs rs = RsInit.rsInit(page, pageSize, count);

		switch (mod) {
		case 1:
			orderStr = " order by a.topOne desc,a.views desc ";
			break;
		case 2:
			orderStr = " order by a.topOne desc,(a.views/(TO_DAYS(NOW())-(TO_DAYS(a.addTime)-1))) desc ";
			break;
		case 3:
			orderStr = " order by a.topOne desc,a.id desc ";
			break;
				
		default:
			orderStr = " order by a.topOne desc,a.addTimeLong desc ";
			break;
		}
		
		String hql;
		
		if (speedMod==1){
			hql = "from ArticleThread a " + hqlTmp + orderStr;
		}else{
			hql = "select a.id from ArticleThread a " + hqlTmp + orderStr;
		}
		
		

		HibernateCallbackUtilVo hcuv = new HibernateCallbackUtilVo();
		hcuv.setCount(count);
		hcuv.setFirstResult(firstResult);
		hcuv.setHql(hql);
		hcuv.setIbernateTemplate(this.getHibernateTemplate());
		hcuv.setPageSize(pageSize);
		hcuv.setPage(page);
		
		return HibernateCallbackUtil.exeRs(hcuv);
		
	}
	
	@Override
	public Rs findByUserId(long userId, int gid,final int page, final int pageSize,int mod,int speedMod) {
		String hqlTmp,gidTmp="";
		if (gid>0){
			ArticleGroup g = (ArticleGroup) this.getHibernateTemplate().get(
					"com.lerx.article.vo.ArticleGroup", gid);
			if (g!=null){
				gidTmp = " and (a.articleGroup.footerLeft >=" + g.getFooterLeft()
				+ " and a.articleGroup.footerRight<=" + g.getFooterRight() + ") ";
			}
			
			
		}
		switch (mod){
		case 1:
			hqlTmp="from ArticleThread a where " + gidTmp + " a.user.id=";
			break;
			
		case 2:
			hqlTmp="from ArticleThread a where " + gidTmp + " a.state=false and a.user.id=";
			break;
		default:
			
			hqlTmp="from ArticleThread a where " + gidTmp + " a.state=true and a.user.id=";
			break;
				
		}
		final String hql;
		if (speedMod==1){
			hql = hqlTmp + userId
			+ " order by a.topOne desc,a.id desc";
		}else{
			hql = "select a.id " + hqlTmp + userId
			+ " order by a.topOne desc,a.id desc";
		}
		long count = (Long) this
				.getHibernateTemplate()
				.find("select count(*) "+hqlTmp
						+ userId).get(0);
		final int curPage;
		Rs rs = RsInit.rsInit(page, pageSize, count);
		if (page > rs.getPageCount()) {
			curPage = rs.getPageCount();
		} else if (page < 1) {
			curPage = 1;
		} else {
			curPage = page;
		}
		
		
		HibernateCallbackUtilVo hcuv = new HibernateCallbackUtilVo();
		hcuv.setCount(count);
		hcuv.setFirstResult(0);
		hcuv.setHql(hql);
		hcuv.setIbernateTemplate(this.getHibernateTemplate());
		hcuv.setPageSize(pageSize);
		hcuv.setPage(curPage);
		
		return HibernateCallbackUtil.exeRs(hcuv);
		
		
	}

	@Override
	public boolean changeStateByStr(String hql) {
		this.getHibernateTemplate().bulkUpdate(hql);
		return true;
	}

	@Override
	public ArticleThread findAdjacent(long id, int mod,
			int groupId, int stateMode) {
		ArticleGroup g;
		String hql;
		boolean state;
		String modStr;
		if (mod == 0) {
			modStr = " a.id < " + id + " order by a.addTime desc";
		} else {
			modStr = " a.id > " + id + " order by a.addTime asc";
		}

		if (stateMode == 2) {
			state = true;
		} else {
			state = false;
		}

		if (groupId != 0) {
			g = (ArticleGroup) this.getHibernateTemplate().get(
					"com.lerx.article.vo.ArticleGroup", groupId);
		} else {
			g = null;
		}

		if (g != null) {
			if (state) {
				hql = "from ArticleThread a where a.articleGroup.id=" + groupId
						+ " and " + modStr;

			} else {
				hql = "from ArticleThread a where a.state=true and a.articleGroup.id="
						+ groupId + " and " + modStr;
			}

		} else {
			if (state) {
				hql = "from ArticleThread a where " + modStr;
			} else {
				hql = "from ArticleThread a where a.state=true  where "
						+ modStr;
			}

		}
		
		
		HibernateCallbackUtilVo hcuv = new HibernateCallbackUtilVo();
		hcuv.setFirstResult(0);
		hcuv.setHql(hql);
		hcuv.setIbernateTemplate(this.getHibernateTemplate());
		hcuv.setPageSize(1);
		hcuv.setPage(0);
		
		@SuppressWarnings("unchecked")
		List<ArticleThread> list= (List<ArticleThread>) HibernateCallbackUtil.exeList(hcuv);
		

		ArticleThread t;
		if (list.size() == 0) {
			t = null;
		} else {
			t = (ArticleThread) list.get(0);
		}

		// TODO Auto-generated method stub
		return t;
	}

	@Override
	public boolean changeSoul(long id, boolean soul) {
		ArticleThread thread = (ArticleThread) this.getHibernateTemplate().get(
				"com.lerx.article.vo.ArticleThread", id);
		thread.setSoul(soul);
		modify(thread);
		return true;
	}

	@Override
	public boolean articlesExistAtGroup(ArticleGroup articleGroup) {
		String hql = "select count(*) from ArticleThread a where a.articleGroup.id = "
				+ articleGroup.getId();
		
		long count = (Long) this.getHibernateTemplate()
		.find(hql)
		.get(0);
		
		if (count<1) {
			
			return false;
		} else {
			return true;
		}
	}

	@Override
	public long findCountArticleInTodayByUserId(long userId, int groupId) {
		long count = (Long) this
				.getHibernateTemplate()
				.find("select count(*) from ArticleThread a where a.user.id="
						+ userId + " and a.articleGroup.id="+groupId+" and TO_DAYS(addTime)-TO_DAYS(CURDATE())=0")
				.get(0);
		return count;
	}
	
	@Override
	public long countByUserId(long userId,int mode) {
		String hql;
		switch (mode) {
		case 1:
			hql="select count(*) from ArticleThread a where a.state is true and ";
			break;
		case 2:
			hql="select count(*) from ArticleThread a where a.state is false and ";
			break;
		default:
			hql="select count(*) from ArticleThread a where ";
			break;
		}
		long count = (Long) this
		.getHibernateTemplate()
		.find(hql+" a.user.id="
				+ userId)
		.get(0);
		return count;
	}
	
	@Override
	public long countByUserIdAndOther(long userId, int mode, String navsStr,
			Timestamp begin, Timestamp end) {
		String hql,navHqlAdd="",addTimeHqlAdd="";
		ArticleGroup g;
		int gid,gcount=0;
		
		//判断文章组
		if (navsStr!=null && !navsStr.trim().equals("")){
			String[] navIdArray = navsStr.split(",");
			for (int step = 0; step < navIdArray.length; step++) {
				gid=Integer.valueOf(navIdArray[step]);
				g=(ArticleGroup) this.getHibernateTemplate().get(
						"com.lerx.article.vo.ArticleGroup", gid);
				if (g!=null){
					gcount++;
					if (navHqlAdd.trim().equals("")){
						navHqlAdd += " (a.articleGroup.footerLeft >=" + g.getFooterLeft()
						+ " and a.articleGroup.footerRight<=" + g.getFooterRight()+") ";
					}else{
						navHqlAdd += " or (a.articleGroup.footerLeft >=" + g.getFooterLeft()
						+ " and a.articleGroup.footerRight<=" + g.getFooterRight()+") ";
					}
				}
				
			}
			
		}
		if (gcount > 1 && !navHqlAdd.trim().equals("")){
			navHqlAdd="("+navHqlAdd+")";
		}
		if (gcount > 0 && !navHqlAdd.trim().equals("")){
			navHqlAdd=" and "+navHqlAdd;
		}
		
		//判断时间
		if (begin!=null){
			if (end==null){
				end=new Timestamp(System.currentTimeMillis());
			}
			
			addTimeHqlAdd=" and a.addTime>='"+begin+"' and a.addTime<'"+end+"'";
		}
		
		switch (mode) {
		case 1:
			hql="select count(*) from ArticleThread a where a.state is true and ";
			break;
		case 2:
			hql="select count(*) from ArticleThread a where a.state is false and ";
			break;
		default:
			hql="select count(*) from ArticleThread a where ";
			break;
		}
		long count = (Long) this
		.getHibernateTemplate()
		.find(hql+" a.user.id="
				+ userId + navHqlAdd + addTimeHqlAdd)
		.get(0);
		return count;
	}
	
	@Override
	public long count(int mode) {
		String hql;
		switch (mode) {
		case 1:
			hql="select count(*) from ArticleThread a where a.state is true";
			break;
		case 2:
			hql="select count(*) from ArticleThread a where a.state is false";
			break;
		default:
			hql="select count(*) from ArticleThread";
			break;
		}
		long count = (Long) this.getHibernateTemplate()
				.find(hql)
				.get(0);
		return count;
	}

	@Override
	public boolean changThreadUserToNull(long uid) {
		this.getHibernateTemplate().bulkUpdate("update ArticleThread c set c.user=null where c.user.id="+uid);
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean delByUserId(long uid) {
		this.getHibernateTemplate().bulkUpdate("delete from ArticleThread a where a.user.id="+uid);
		return true;
	}

	@Override
	public Rs search(String key, int groupId, int page, int pageSize, boolean inBody) {

		long count;
		String orderStr, hqlTmp;
		ArticleGroup g = (ArticleGroup) this.getHibernateTemplate().get(
				"com.lerx.article.vo.ArticleGroup", groupId);

		if (groupId == 0) {
			hqlTmp = "";
			hqlTmp+= " where a.title like '%"+key+"%'";
		} else {
			hqlTmp = " where a.articleGroup.footerLeft >=" + g.getFooterLeft()
					+ " and a.articleGroup.footerRight<=" + g.getFooterRight();
			hqlTmp+= " and a.title like '%"+key+"%'";
		}
		
		if (inBody){
			hqlTmp = " or a.body like '%"+key+"%'";
		}

		orderStr=" order by id desc";
		
		count = (Long) this.getHibernateTemplate()
				.find("select count(*) from ArticleThread a " + hqlTmp).get(0);

//		Rs rs = RsInit.rsInit(page, pageSize, count);


		String hql = "select a.id from ArticleThread a " + hqlTmp + orderStr;
		
		HibernateCallbackUtilVo hcuv = new HibernateCallbackUtilVo();
		hcuv.setFirstResult(0);
		hcuv.setHql(hql);
		hcuv.setIbernateTemplate(this.getHibernateTemplate());
		hcuv.setPageSize(pageSize);
		hcuv.setPage(page);
		hcuv.setCount(count);
		Rs rs = HibernateCallbackUtil.exeRs(hcuv);
		
		return rs;
		// TODO Auto-generated method stub
	
	}

	@Override
	public Rs findAll(int mod,int htmlCreated) {
		String hql;
		long count;
		String hqlAdd;

		switch (htmlCreated){
		case 1:
			hqlAdd=" a.htmlCreated is true ";
			break;
		case 2:
			hqlAdd=" a.htmlCreated is false ";
			break;
		default:
			hqlAdd="";
			break;
		}
		
		switch (mod){
		case 1:
			hql = "from ArticleThread a where a.state is false and "+hqlAdd;
			break;
		case 2:
			if (hqlAdd.trim().equals("")){
				hql = "from ArticleThread a ";
			}else{
				hql = "from ArticleThread a where "+hqlAdd;
			}
			
			break;
		default:
			if (hqlAdd.trim().equals("")){
				hql = "from ArticleThread a where a.state is true";;
			}else{
				hql = "from ArticleThread a where a.state is true and "+hqlAdd;;
			}
			break;
		}
		
		
		count = (Long) this.getHibernateTemplate()
		.find("select count(*) "+hql).get(0);

		hql = "select a.id "+hql;;
		@SuppressWarnings("unchecked")
		List<Long> list = this.getHibernateTemplate().find(hql);
		Rs rs=new Rs();
		rs.setCount(count);
		rs.setList(list);
		return rs;
	}

	@Override
	public boolean topOne(long id,boolean state) {
		this.getHibernateTemplate().bulkUpdate("update ArticleThread c set c.topOne=false");
		if (state){
			this.getHibernateTemplate().bulkUpdate("update ArticleThread c set c.topOne=true where c.id="+id);
		}
		return true;
	}

	@Override
	public long move(int sGid, int tGid) {
		long count;
		String hql= "select count(*) from ArticleThread a where a.articleGroup.id="+sGid;
		count = (Long) this.getHibernateTemplate()
		.find(hql).get(0);
		try {
			this.getHibernateTemplate().bulkUpdate("update ArticleThread a set a.articleGroup.id="+tGid+" where a.articleGroup.id="+sGid);
			return count;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0L;
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

	@Override
	public List<Long> findAllID() {
		
		String hql="select a.id from ArticleThread a order by a.id asc";
		@SuppressWarnings("unchecked")
		List<Long> list=this.getHibernateTemplate().find(hql);
		return list;
	}

}
