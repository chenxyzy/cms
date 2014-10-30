package com.lerx.vote.dao.imp;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lerx.draw.vo.Draw;
import com.lerx.sys.util.HibernateCallbackUtil;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.vo.HibernateCallbackUtilVo;
import com.lerx.sys.util.vo.Rs;
import com.lerx.vote.dao.IVoteRecDao;
import com.lerx.vote.vo.VoteRec;
import com.lerx.vote.vo.VoteSubject;

public class VoteRecDaoImp extends HibernateDaoSupport implements IVoteRecDao {

	@Override
	public long add(VoteRec vr) {
		if (!findRecByIpAndSaltOnSameDay(vr.getSub().getId(),vr.getAddIp(),vr.getSalt())){
			this.getHibernateTemplate().save(vr);
			return vr.getId();
		}else{
			return 0;
		}
		
	}

	@Override
	public boolean del(VoteRec vr) {
		this.getHibernateTemplate().delete(vr);
		return true;
	}

	@Override
	public boolean delById(long id) {
		this.getHibernateTemplate()
				.delete(this.getHibernateTemplate().get(
						"com.lerx.vote.vo.VoteRec", id));
		return true;
	}

	@Override
	public boolean modify(VoteRec vr) {
		this.getHibernateTemplate().saveOrUpdate(vr);
		return true;
	}

	@Override
	public VoteRec findById(long id) {
		// TODO Auto-generated method stub
		return (VoteRec) this.getHibernateTemplate().get(
				"com.lerx.vote.vo.VoteRec", id);
	}

	@Override
	public Rs findBySub(VoteSubject vs, int page, int pageSize) {

		long count;
		String orderStr, hqlTmp;

		if (vs == null) {

			return null;
		} else {

			hqlTmp = " where v.sub.id =" + vs.getId();

		}

		count = (Long) this.getHibernateTemplate()
				.find("select count(*) from VoteRec v " + hqlTmp).get(0);
//		Rs rs = RsInit.rsInit(page, pageSize, count);
		orderStr = " order by v.id desc ";

		String hql = "from VoteRec v " + hqlTmp + orderStr;
		
		HibernateCallbackUtilVo hcuv = new HibernateCallbackUtilVo();
		hcuv.setHql(hql);
		hcuv.setIbernateTemplate(this.getHibernateTemplate());
		hcuv.setPageSize(pageSize);
		hcuv.setCount(count);
		hcuv.setPage(page);
//		hcuv.setFirstResult(firstResult);
		Rs rs =  HibernateCallbackUtil.exeRs(hcuv);
		
//		HibernateCallback hc = new HibernateCallback() {
//
//			@SuppressWarnings("unchecked")
//			@Override
//			public Object doInHibernate(org.hibernate.Session session)
//					throws HibernateException, SQLException {
//
//				Query query = session.createQuery(hql);
//				query.setFirstResult((page - 1) * pageSize);
//				query.setMaxResults(pageSize);
//
//				List<VoteRec> list = query.list();
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
	public boolean findRecByIpAndSaltOnSameDay(int subId, String ip, String salt) {

		long count = (Long) this
				.getHibernateTemplate()
				.find("select count(*) from VoteRec v where v.sub.id=" + subId
						+ " and v.salt='" + salt + "' and v.addIp='"
						+ ip.trim()
						+ "' and TO_DAYS(v.addTime)-TO_DAYS(CURDATE())=0")
				.get(0);
		if (count > 0) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public boolean findRecByIpInTimeRange(int subId, String ip, int hours) {
		String hql = "from VoteRec v where v.sub.id=" + subId
				+ " and v.addIp='" + ip.trim() + "' order by v.addTime desc";

		HibernateCallbackUtilVo hcuv = new HibernateCallbackUtilVo();
		hcuv.setHql(hql);
		hcuv.setIbernateTemplate(this.getHibernateTemplate());
		hcuv.setPageSize(1);
//		hcuv.setCount(count);
		hcuv.setPage(0);
//		hcuv.setFirstResult(firstResult);
		@SuppressWarnings("unchecked")
		List<VoteRec> list =  (List<VoteRec>) HibernateCallbackUtil.exeList(hcuv);
		
//		HibernateCallback hc = new HibernateCallback() {
//
//			@SuppressWarnings("unchecked")
//			@Override
//			public Object doInHibernate(org.hibernate.Session session)
//					throws HibernateException, SQLException {
//
//				Query query = session.createQuery(hql);
//				query.setFirstResult(0);
//				query.setMaxResults(1);
//
//				List<VoteRec> list = query.list();
//
//				if (list.isEmpty()) {
//					// System.out.println("找不到记录");
//				}
//				// System.out.println("测试点5");
//				return list;
//			}
//
//		};
//		@SuppressWarnings("unchecked")
//		List<VoteRec> list = this.getHibernateTemplate().executeFind(hc);

		long count = list.size();
		if (count > 0) {
			VoteRec vr = list.get(0);
			Long voteTime;
			voteTime = vr.getAddTime().getTime();
			Long curTime = System.currentTimeMillis();
			if ((curTime - voteTime) < (hours * 1000 * 60 * 60)) {
				return true;
			} else {
				return false;
			}

		} else {
			return false;
		}
		// TODO Auto-generated method stub
	}

	@Override
	public boolean findRecByPhoneCode(int subId, String phone) {
		long count = (Long) this
				.getHibernateTemplate()
				.find("select count(*) from VoteRec v where v.sub.id=" + subId
						+ " and v.phone='" + phone + "'").get(0);
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean findRecByIdentity(int subId, String identity) {
		long count = (Long) this
				.getHibernateTemplate()
				.find("select count(*) from VoteRec v where v.sub.id=" + subId
						+ " and v.identity='" + identity + "'").get(0);
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Rs findMesBySub(VoteSubject vs, int page, int pageSize,boolean state) {

		long count;
		String orderStr, hqlTmp,mesStateSqlAdd;
		if (state){
			mesStateSqlAdd="";
		}else{
			mesStateSqlAdd=" and v.mesState is true ";
		}
		if (vs == null) {

			return null;
		} else {

			hqlTmp = " where v.sub.id =" + vs.getId() + " and v.mes<>'' and v.mes is not null" + mesStateSqlAdd;

		}

		count = (Long) this.getHibernateTemplate()
				.find("select count(*) from VoteRec v " + hqlTmp).get(0);
//		System.out.println("count:"+count);
//		Rs rs = RsInit.rsInit(page, pageSize, count);
		orderStr = " order by v.id desc ";

		String hql = "from VoteRec v " + hqlTmp + orderStr;
		
		HibernateCallbackUtilVo hcuv = new HibernateCallbackUtilVo();
		hcuv.setHql(hql);
		hcuv.setIbernateTemplate(this.getHibernateTemplate());
		hcuv.setPageSize(pageSize);
		hcuv.setCount(count);
		hcuv.setPage(page);
//		hcuv.setFirstResult(firstResult);
		Rs rs =  HibernateCallbackUtil.exeRs(hcuv);
		
//		HibernateCallback hc = new HibernateCallback() {
//
//			@SuppressWarnings("unchecked")
//			@Override
//			public Object doInHibernate(org.hibernate.Session session)
//					throws HibernateException, SQLException {
//
//				Query query = session.createQuery(hql);
//				query.setFirstResult((page - 1) * pageSize);
//				query.setMaxResults(pageSize);
//
//				List<VoteRec> list = query.list();
//
//				if (list.isEmpty()) {
////					 System.out.println("找不到记录");
//				}
////				 System.out.println("测试点5");
//				return list;
//			}
//
//		};
////		System.out.println("测试点");
//		rs.setList(getHibernateTemplate().executeFind(hc));
		return rs;
		// TODO Auto-generated method stub
	}

	@Override
	public String draw(Draw draw,int n) {
		long count;
		String hql;
		String voteIdArr=draw.getVotesRange();
		String[] voteIdArray = voteIdArr.split(",");
		
		int voteId;
		String hqlAdd=null;
		//排除结果
		String exceptedRecStr=draw.getExceptedRecStr();
		
		int exceptedRecCount;
		if (exceptedRecStr==null || exceptedRecStr.trim().equals("")){
			exceptedRecCount=0;
		}else{
			String[] exceptedRec = exceptedRecStr.split(",");
			exceptedRecCount=exceptedRec.length;
		}
		
		if (exceptedRecStr!=null && !exceptedRecStr.trim().equals("")){
			exceptedRecStr=exceptedRecStr.trim();
			exceptedRecStr=StringUtil.strReplace(exceptedRecStr, ",", "}{");
			exceptedRecStr="{"+exceptedRecStr;
			exceptedRecStr+="}";
		}else{
			exceptedRecStr=null;
		}
		
		if (voteIdArray.length>0){
			hqlAdd=" where ";
			for (int step = 0; step < voteIdArray.length; step++) {
				voteId=Integer.valueOf(voteIdArray[step]);
				if (step==0){
					hqlAdd += " v.sub.id ="+voteId;
				}else{
					hqlAdd += " or v.sub.id ="+voteId;
				}
			}
		}else{
			return null;
		}
		
			hql = "from VoteRec v " + hqlAdd;
			
			count = (Long) this.getHibernateTemplate()
			.find("select count(*) "+hql).get(0);
			if ((count-(int)exceptedRecCount)<n){
				return null;
			}
//			System.out.println(hql);
//			System.out.println("count:"+count);
			hql = "select v.id "+hql;;
			@SuppressWarnings("unchecked")
			List<Long> list = this.getHibernateTemplate().find(hql);
			Rs rs=new Rs();
			rs.setCount(count);
			rs.setList(list);
			
			Set<Long> nums = new HashSet<Long>();
			if (n>count){
				n=(int)count;
			}
			Long tmpRec;
			
			
			while(nums.size()<n){//产生n个不重复数
				int randMax=(int) (Math.random()*count);
//				System.out.println("nums.size():"+nums.size());
//				System.out.println("n:"+n);
				tmpRec=list.get(randMax);
				if (exceptedRecCount>0){
					if (exceptedRecStr.indexOf("{"+tmpRec+"}")==-1){
						 nums.add(tmpRec);
					}
				}else{
					nums.add(tmpRec);
				}
			   
			}
			Iterator<Long> it=nums.iterator();
			String recArr="";
			int step=0;
			while( it.hasNext() ){
				if (step==0){
					recArr+=it.next();
				}else{
					recArr+= ","+it.next();
				}
				
				step++;
			}


//			list.get(index);
			return recArr;
		
		
	}

	@Override
	public Long findRecCountBySub(VoteSubject vs) {
		String hql = "from VoteRec v where v.state is true and v.sub.id=" + vs.getId();
		Long count = (Long) this.getHibernateTemplate()
		.find("select count(*) "+hql).get(0);
		return count;
	}

	@Override
	public boolean delBySubId(int subId) {
		String hql="delete from VoteRec v where v.sub.id=" + subId;
		this.getHibernateTemplate().bulkUpdate(hql);
		return true;
	}

}
