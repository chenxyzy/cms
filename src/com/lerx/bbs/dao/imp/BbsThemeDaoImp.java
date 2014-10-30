package com.lerx.bbs.dao.imp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lerx.bbs.dao.IBbsThemeDao;
import com.lerx.bbs.vo.BbsForum;
import com.lerx.bbs.vo.BbsTheme;
import com.lerx.bbs.vo.BbsThreadInf;
import com.lerx.sys.util.HibernateCallbackUtil;
import com.lerx.sys.util.RsInit;
import com.lerx.sys.util.TimeUtil;
import com.lerx.sys.util.vo.HibernateCallbackUtilVo;
import com.lerx.sys.util.vo.HotScore;
import com.lerx.sys.util.vo.Rs;

public class BbsThemeDaoImp extends HibernateDaoSupport implements IBbsThemeDao {

	@Override
	public long addBbsTheme(BbsTheme theme) {
		this.getHibernateTemplate().save(theme);
		return theme.getId();
	}

	@Override
	public boolean modifyBbsTheme(BbsTheme theme) {
		this.getHibernateTemplate().saveOrUpdate(theme);
		return true;

	}

	@Override
	public boolean delBbsTheme(BbsTheme theme) {
		this.getHibernateTemplate().delete(theme);
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean delBbsThemeById(long id) {
		this.getHibernateTemplate().delete(
				this.getHibernateTemplate().get(
						"com.lerx.bbs.vo.BbsTheme", id));
		return true;
	}

	/*
	 * 计算帖子总页数
	 */
	public int pageCountByRootThemeId(long tid, int pageSize) {
		String hqlTmp = " where t.rootTheme.id=" + tid;
		long count = (Long) this.getHibernateTemplate()
				.find("select count(*) from BbsTheme t " + hqlTmp).get(0);
		count++; // 加上自己
		Rs rs = RsInit.rsInit(1, pageSize, count);
		return rs.getPageCount();

	}

	@Override
	public Rs findThemesAndOwnByParentThemeId(long id, int page,
			int pageSize, boolean sortMod) {

		long count;
		String hqlTmp = " where t.id=" + id + " or t.rootTheme.id=" + id;

		count = (Long) this.getHibernateTemplate()
				.find("select count(*) from BbsTheme t " + hqlTmp).get(0);

//		Rs rs = RsInit.rsInit(page, pageSize, count);
		if (sortMod) {
			hqlTmp += " order by t.id asc";
		} else {
			hqlTmp += " order by t.chgTime desc";
		}

		String hql = "select t.id from BbsTheme t " + hqlTmp;
		
		HibernateCallbackUtilVo hcuv = new HibernateCallbackUtilVo();
		hcuv.setFirstResult(0);
		hcuv.setHql(hql);
		hcuv.setIbernateTemplate(this.getHibernateTemplate());
		hcuv.setPageSize(pageSize);
		hcuv.setPage(page);
		hcuv.setCount(count);
		Rs rs = HibernateCallbackUtil.exeRs(hcuv);
		
//		HibernateCallback hc = new HibernateCallback() {
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
//				query.setFirstResult((page - 1) * pageSize);
//				query.setMaxResults(n);
//				
//				List<Long> list = query.list();
//
//				if (list.isEmpty()) {
//				}
//				return list;
//			}
//
//		};
//		rs.setList(getHibernateTemplate().executeFind(hc));
		return rs;
	
	}
	
	@Override
	public Rs findThemesByParentThemeId(long id, final int page,
			final int pageSize, boolean sortMod) {
		long count;
		String hqlTmp = " where t.rootTheme.id=" + id;

		count = (Long) this.getHibernateTemplate()
				.find("select count(*) from BbsTheme t " + hqlTmp).get(0);

//		Rs rs = RsInit.rsInit(page, pageSize, count);
		if (sortMod) {
			hqlTmp += " order by t.sink asc,t.id asc";
		} else {
			hqlTmp += " order by t.sink asc,t.chgTime desc";
		}

		// System.out.println("内部测试：page="+page+"  pageSize="+pageSize);
		String hql = "select t.id from BbsTheme t " + hqlTmp;
		
		HibernateCallbackUtilVo hcuv = new HibernateCallbackUtilVo();
		hcuv.setHql(hql);
		hcuv.setIbernateTemplate(this.getHibernateTemplate());
		hcuv.setPageSize(pageSize);
		hcuv.setPage(page);
		hcuv.setCount(count);
		Rs rs = HibernateCallbackUtil.exeRsOnBbs(hcuv);
		
		return rs;
	}

	@Override
	public Rs findThemesByForumId(int id, int page, int pageSize,
			boolean sortMod) {

		long count;
		String hqlTmp = " where t.rootTheme is null and t.forum.id=" + id;

		count = (Long) this.getHibernateTemplate()
				.find("select count(*) from BbsTheme t " + hqlTmp).get(0);

//		Rs rs = RsInit.rsInit(page, pageSize, count);
		if (sortMod) {
			hqlTmp += " order by t.top desc,t.sink asc,t.id asc";
		} else {
			hqlTmp += " order by t.top desc,t.sink asc,t.chgTime desc";
		}
		String hql = "select t.id from BbsTheme t " + hqlTmp;
		
		HibernateCallbackUtilVo hcuv = new HibernateCallbackUtilVo();
		hcuv.setFirstResult(0);
		hcuv.setHql(hql);
		hcuv.setIbernateTemplate(this.getHibernateTemplate());
		hcuv.setPageSize(pageSize);
		hcuv.setPage(page);
		hcuv.setCount(count);
		Rs rs = HibernateCallbackUtil.exeRs(hcuv);
		return rs;

	}

	@Override
	public Rs findAllThemesByForumId(int id, int page,
			int pageSize, boolean sortMod) {

		long count;
		BbsForum f = (BbsForum) this.getHibernateTemplate().get(
				"com.lerx.bbs.vo.BbsForum", id);
		String hqlTmp = " where  t.rootTheme is null and  t.forum.footerLeft >="
				+ f.getFooterLeft()
				+ " and t.forum.footerRight<="
				+ f.getFooterRight();
		;

		count = (Long) this.getHibernateTemplate()
				.find("select count(*) from BbsTheme t " + hqlTmp).get(0);

		if (sortMod) {
			hqlTmp += " order by t.top desc,t.sink asc,t.id asc";
		} else {
			hqlTmp += " order by t.top desc,t.sink asc,t.chgTime desc";
		}
		String hql = "select t.id from BbsTheme t " + hqlTmp;
		
		HibernateCallbackUtilVo hcuv = new HibernateCallbackUtilVo();
		hcuv.setFirstResult(0);
		hcuv.setHql(hql);
		hcuv.setIbernateTemplate(this.getHibernateTemplate());
		hcuv.setPageSize(pageSize);
		hcuv.setPage(page);
		hcuv.setCount(count);
		Rs rs = HibernateCallbackUtil.exeRs(hcuv);
		return rs;

	}

	@Override
	public Rs findThemesByUserId(long uid, int page, int pageSize,
			boolean sortMod,boolean th) {
		long count;
		String hqlTmp = " where t.user.id=" + uid;
		
		if (th){
			hqlTmp+=" and t.rootTheme is null";
		}
		
		count = (Long) this.getHibernateTemplate()
				.find("select count(*) from BbsTheme t " + hqlTmp).get(0);

//		Rs rs = RsInit.rsInit(page, pageSize, count);
		if (sortMod) {
			hqlTmp += " order by t.sink asc,t.id asc";
		} else {
			hqlTmp += " order by t.sink asc,t.chgTime desc";
		}
		String hql = "select t.id from BbsTheme t " + hqlTmp;
		
		HibernateCallbackUtilVo hcuv = new HibernateCallbackUtilVo();
		hcuv.setFirstResult(0);
		hcuv.setHql(hql);
		hcuv.setIbernateTemplate(this.getHibernateTemplate());
		hcuv.setPageSize(pageSize);
		hcuv.setPage(page);
		hcuv.setCount(count);
		Rs rs = HibernateCallbackUtil.exeRs(hcuv);
		rs=filterSam(rs);
		return rs;
	}

	public boolean changeThemeState(BbsTheme theme, boolean state) {
		theme.setState(state);
		this.getHibernateTemplate().saveOrUpdate(theme);
		return true;
	}

	@Override
	public boolean changeThemeTopMod(BbsTheme theme, int topMod) {
		theme.setTopMod(topMod);
		this.getHibernateTemplate().saveOrUpdate(theme);
		return true;
	}

	@Override
	public BbsTheme findThemeById(long id) {
		return (BbsTheme) this.getHibernateTemplate().get(
				"com.lerx.bbs.vo.BbsTheme", id);
	}

	@Override
	public BbsTheme findLastThemeByRoot(BbsTheme rootTheme) {
		String hql = "select max(id) from BbsTheme a where a.rootTheme.id="
				+ rootTheme.getId();
		if (this.getHibernateTemplate().find(hql).get(0) == null) {
			return null;
		}
		long id = (Long) this.getHibernateTemplate().find(hql).get(0);
		return (BbsTheme) this.getHibernateTemplate().get(
				"com.lerx.bbs.vo.BbsTheme", id);
		// return
		// ((Long)sessionFactory.getCurrentSession().createQuery(hql).uniqueResult()).intValue();

	}

	@Override
	public long findThemesCountByRoot(BbsTheme rootTheme) {
		String hql = "select count(*) from BbsTheme a where a.rootTheme.id="
				+ rootTheme.getId();
		return (Long) this.getHibernateTemplate().find(hql).get(0);
	}

	// 根据版块id号统计所有贴数
	@Override
	public long findThemesCountByForumId(int id) {

		BbsForum bf = (BbsForum) this.getHibernateTemplate().get(
				"com.lerx.bbs.vo.BbsForum", id);

		String hql = "select count(*) from BbsTheme a where a.forum.footerLeft>="
				+ bf.getFooterLeft()
				+ " and a.forum.footerRight<="
				+ bf.getFooterRight();

		// System.out.println("hql:"+hql);
		return (Long) this.getHibernateTemplate().find(hql).get(0);
	}

	// 根据版块id号统计所有主题贴数
	@Override
	public long findThreadsCountByForumId(int id) {
		BbsForum bf = (BbsForum) this.getHibernateTemplate().get(
				"com.lerx.bbs.vo.BbsForum", id);

		String hql = "select count(*) from BbsTheme a where a.forum.footerLeft>="
				+ bf.getFooterLeft()
				+ " and a.forum.footerRight<="
				+ bf.getFooterRight()
				+ " and (a.rootTheme.id is null or a.rootTheme.id=0)";
		return (Long) this.getHibernateTemplate().find(hql).get(0);
	}

	// 从某一天0点起 所有贴数
	@Override
	public long findThemesCountByDate(int fid, Date d) {
		BbsForum bf = (BbsForum) this.getHibernateTemplate().get(
				"com.lerx.bbs.vo.BbsForum", fid);
		String timesTmp = " and a.addTimeUnix > " + d.getTime();
		String hql = "select count(*) from BbsTheme a where a.forum.footerLeft>="
				+ bf.getFooterLeft()
				+ " and a.forum.footerRight<="
				+ bf.getFooterRight() + timesTmp;
		return (Long) this.getHibernateTemplate().find(hql).get(0);
	}

	// 从某一天0点起 所有主题数
	@Override
	public long findThreadsCountByDate(int fid, Date d) {
		BbsForum bf = (BbsForum) this.getHibernateTemplate().get(
				"com.lerx.bbs.vo.BbsForum", fid);
		String timesTmp = " and a.addTimeUnix > " + d.getTime();
		String hql = "select count(*) from BbsTheme a where a.forum.footerLeft>="
				+ bf.getFooterLeft()
				+ " and a.forum.footerRight<="
				+ bf.getFooterRight()
				+ timesTmp
				+ " and (a.rootTheme.id is null or a.rootTheme.id=0)";
		return (Long) this.getHibernateTemplate().find(hql).get(0);
	}

	@Override
	public BbsTheme findLastThemeByForumId(int id) {
		String hql = "select max(id) from BbsTheme a where a.rootTheme.id=0 and a.forum.id="
				+ id;
		if (this.getHibernateTemplate().find(hql).get(0) == null) {
			return null;
		}
		long tid = (Long) this.getHibernateTemplate().find(hql).get(0);
		if (tid > 0) {
			return (BbsTheme) this.getHibernateTemplate().get(
					"com.lerx.bbs.vo.BbsTheme", tid);
		} else {
			return null;
		}

	}

	@Override
	public BbsTheme findLastReplyThemeByForumId(int id) {
		String hql = "select max(id) from BbsTheme a where a.rootTheme.id>0 and a.forum.id="
				+ id;
		if (this.getHibernateTemplate().find(hql).get(0) == null) {
			return null;
		}
		long tid = (Long) this.getHibernateTemplate().find(hql).get(0);
		return (BbsTheme) this.getHibernateTemplate().get(
				"com.lerx.bbs.vo.BbsTheme", tid);
	}

	@Override
	public BbsThreadInf findThreadInfByThemeId(long id) {
		BbsTheme themeLeader = (BbsTheme) this.getHibernateTemplate().get(
				"com.lerx.bbs.vo.BbsTheme", id);
		BbsThreadInf bfi = new BbsThreadInf();

		if (themeLeader == null) {
			bfi.setLeaderCount(0);
			bfi.setThemeLeader(null);
			bfi.setThemeTail(null);
			return bfi;
		}
		BbsTheme themeTail = findLastThemeByRoot(themeLeader);
		int leaderCount = (int) findThemesCountByRoot(themeLeader);

		bfi.setLeaderCount(leaderCount);
		bfi.setThemeLeader(themeLeader);
		bfi.setThemeTail(themeTail);
		// TODO Auto-generated method stub
		return bfi;
	}

	@Override
	public BbsThreadInf findThreadInfByTheme(BbsTheme theme) {
		BbsThreadInf bfi = new BbsThreadInf();

		if (theme == null) {
			bfi.setLeaderCount(0);
			bfi.setThemeLeader(null);
			bfi.setThemeTail(null);
			return bfi;
		}
		BbsTheme themeTail = findLastThemeByRoot(theme);
		int leaderCount = (int) findThemesCountByRoot(theme);

		bfi.setLeaderCount(leaderCount);
		bfi.setThemeLeader(theme);
		bfi.setThemeTail(themeTail);
		// TODO Auto-generated method stub
		return bfi;
	}

	@Override
	public BbsTheme findBySecCode(String secCode) {
		String hql = "from BbsTheme b where b.secCode=?";
		@SuppressWarnings("unchecked")
		List<BbsTheme> list = this.getHibernateTemplate().find(hql, secCode);
		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public boolean delBbsThemeByRootId(long id) {
		String hql = "delete from BbsTheme b where b.rootTheme.id=?";

		this.getHibernateTemplate().bulkUpdate(hql);
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean shield(long tid, boolean s) {

		BbsTheme bt = (BbsTheme) this.getHibernateTemplate().get(
				"com.lerx.bbs.vo.BbsTheme", tid);

		bt.setShield(s);
		
		this.getHibernateTemplate().saveOrUpdate(bt);
		// TODO Auto-generated method stub
		return s;
	}

	@Override
	public boolean top(long tid, boolean s) {
		BbsTheme bt = (BbsTheme) this.getHibernateTemplate().get(
				"com.lerx.bbs.vo.BbsTheme", tid);
//		System.out.println("tid:"+tid+"  s:"+s);
		bt.setTop(s);
		if (s && bt.isSink()){
			bt.setSink(false);
		}
		this.getHibernateTemplate().saveOrUpdate(bt);
		// TODO Auto-generated method stub
		return s;
	}

	@Override
	public Rs search(String key, int fid, boolean al, int page,
			int pageSize) {
		BbsForum bf = null;
		String hql;
		String whereSql="";
		String hqlSearchTmp;
		if (key == null || key.trim().equals("")) {
			return null;
		} else {
			if (fid > 0) {
				bf = (BbsForum) this.getHibernateTemplate().get(
						"com.lerx.bbs.vo.BbsForum", fid);
			}

			if (bf != null) {
				hql = "from BbsTheme t where  t.forum.footerLeft>=" + bf.getFooterLeft()
						+ " and t.forum.footerRight<=" + bf.getFooterRight();
				whereSql=" and ";
			} else {
				hql = "from BbsTheme t ";
				whereSql=" where ";
			}

			if (al) {
				hqlSearchTmp = whereSql+ " (t.title like '%" + key
						+ "%'  or t.body like '%" + key + "%')";
			} else {
				hqlSearchTmp = whereSql+ " t.title like '%" + key + "%'";
			}

			String endhql = "select t.id "+hql + hqlSearchTmp;
			// System.out.println(orderHql);
			long count = (Long) this.getHibernateTemplate()
					.find("select count(*) " + hql+hqlSearchTmp).get(0);

//			Rs rs = RsInit.rsInit(page, pageSize, count);
			
			HibernateCallbackUtilVo hcuv = new HibernateCallbackUtilVo();
			hcuv.setFirstResult(0);
			hcuv.setHql(endhql);
			hcuv.setIbernateTemplate(this.getHibernateTemplate());
			hcuv.setPageSize(pageSize);
			hcuv.setPage(page);
			hcuv.setCount(count);
			Rs rs = HibernateCallbackUtil.exeRs(hcuv);
			rs=filterSam(rs);

			return rs;

		}

	}

	@Override
	public Rs findThemesByRule(long fid, int hours, int smod,
			int page, int pageSize,HotScore hs) {
		String hql;
		BbsForum bf = null;
		String hoursTmp = "";

		Timestamp t;
		if (hours > 0) {
			t = new Timestamp(System.currentTimeMillis());
			t = TimeUtil.cal(t, 4, 0 - hours);
			// java.text.SimpleDateFormat formatter = new
			// java.text.SimpleDateFormat(
			// "yyyy-MM-dd hh:mm:ss");
			hoursTmp = " and t.addTimeUnix > " + t.getTime();

		}

		if (fid > 0) {
			bf = (BbsForum) this.getHibernateTemplate().get(
					"com.lerx.bbs.vo.BbsForum", fid);
		}
		if (bf != null) {
			hql = "from BbsTheme t where t.rootTheme is null " + hoursTmp
					+ " and t.forum.footerLeft>=" + bf.getFooterLeft()
					+ " and t.forum.footerRight<=" + bf.getFooterRight();
		} else {
			hql = "from BbsTheme t where t.rootTheme is null " + hoursTmp;
		}
		String tmpHql;

		tmpHql = "select max(t.exoticActors) ";

		int maxExRepi; // 外来回复数
		if (this.getHibernateTemplate().find(tmpHql + hql).get(0) == null) {
			maxExRepi = 0;
		} else {
			maxExRepi = (Integer) this.getHibernateTemplate()
					.find(tmpHql + hql).get(0);
		}
		if (maxExRepi == 0) {
			maxExRepi = 1; // 最大值为0，防止错误，少写些代码，+1， 最大都是0了，反正也是0
		}

		tmpHql = "select max(t.reps) ";
		int maxReps; // 回复数
		if (this.getHibernateTemplate().find(tmpHql + hql).get(0) == null) {
			maxReps = 0;
		} else {
			maxReps = (Integer) this.getHibernateTemplate().find(tmpHql + hql)
					.get(0);
		}

		if (maxReps == 0) {
			maxReps = 1; // 同上
		}

		tmpHql = "select max(t.views) ";
		int maxViews;
		if (this.getHibernateTemplate().find(tmpHql + hql).get(0) == null) {
			maxViews = 0;
		} else {
			maxViews = (Integer) this.getHibernateTemplate().find(tmpHql + hql)
					.get(0);
		}

		if (maxViews == 0) {
			maxViews = 1; // 同上
		}

		String orderHql;

		switch (smod) {

		case 1:
			orderHql = "select t.id " + hql
					+ " order by t.sink asc,((t.exoticActors*100*"+hs.getValueE()+")/" + maxExRepi
					+ "+(t.reps*100*"+hs.getValueP()+")/" + maxReps + "+(t.views*100*"+hs.getValueV()+")/" + maxViews
					+ ") desc";
//			System.out.println("orderHql:"+orderHql);
			break;
		case 2:
			orderHql = "select t.id "
					+ hql
					+ " order by t.sink asc,(t.views/(TO_DAYS(NOW())-(TO_DAYS(t.addTime)-1))) desc";
			break;
		case 3:
			orderHql = "select t.id " + hql
					+ " order by t.sink asc,t.chgTime desc";
			break;
		default:
			orderHql = "select t.id " + hql + " order by t.sink asc,t.id desc";
			break;
		}

		// System.out.println(orderHql);
		long count = (Long) this.getHibernateTemplate()
				.find("select count(*) " + hql).get(0);

		
		HibernateCallbackUtilVo hcuv = new HibernateCallbackUtilVo();
		hcuv.setFirstResult(0);
		hcuv.setHql(orderHql);
		hcuv.setIbernateTemplate(this.getHibernateTemplate());
		hcuv.setPageSize(pageSize);
		hcuv.setPage(page);
		hcuv.setCount(count);
		Rs rs = HibernateCallbackUtil.exeRs(hcuv);
		return rs;
	}

	@Override
	public boolean sink(BbsTheme theme, boolean sink) {
		theme.setSink(sink);
		if (sink && theme.isTop()){
			theme.setTop(false);
		}
		this.getHibernateTemplate().saveOrUpdate(theme);
		return theme.isSink();
	}

	
	private Rs filterSam(Rs rs){
		
		Set<Long> set = new HashSet<Long>();

		List<Long> newList = new ArrayList<Long>();

		@SuppressWarnings("unchecked")
		List<Long> list = (List<Long>) rs.getList();
		Long id;
		BbsTheme bt;
		Long idTmp;
		for (Iterator<Long> iter = list.iterator(); iter.hasNext();) {

			id = iter.next();
			bt = findThemeById(id);

			if (bt.getRootTheme() == null) {
				idTmp = bt.getId();
			} else {
				idTmp = bt.getRootTheme().getId();
			}
			if (set.add(idTmp)) {
				newList.add(idTmp);
			}

		}
		rs = RsInit.rsInit(rs.getPage(), rs.getPageSize(), newList.size());
		rs.setList(newList);
		return rs;
	}

	@Override
	public BbsTheme findLastByUid(long uid) {
		final String hql = "from BbsTheme t where t.user.id=" + uid + " order by t.id desc";
		
		HibernateCallbackUtilVo hcuv = new HibernateCallbackUtilVo();
		hcuv.setFirstResult(0);
		hcuv.setHql(hql);
		hcuv.setIbernateTemplate(this.getHibernateTemplate());
		hcuv.setPageSize(1);
		hcuv.setPage(0);
		@SuppressWarnings("unchecked")
		List<BbsTheme> list = (List<BbsTheme>) HibernateCallbackUtil.exeList(hcuv);
		if (list.isEmpty()){
			return null;
		}else{
			return list.get(0);
		}
	}

	@Override
	public boolean findReplyer(long tid, long uid) {
		String hql = "select b.id from BbsTheme b where b.rootTheme.id="+tid + " and b.user.id="+uid;
		
		@SuppressWarnings("unchecked")
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	
}
