package com.lerx.user.dao.imp;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lerx.article.vo.ArticleGroup;
import com.lerx.qa.vo.QaNav;
import com.lerx.sys.util.HibernateCallbackUtil;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.vo.HibernateCallbackUtilVo;
import com.lerx.sys.util.vo.Rs;
import com.lerx.sys.util.vo.UserCookie;
import com.lerx.user.dao.IInterconnectionDao;
import com.lerx.user.dao.IUserDao;
import com.lerx.user.util.UserUtil;
import com.lerx.user.vo.ChkUtilVo;
import com.lerx.user.vo.TransferUserUtil;
import com.lerx.user.vo.User;
import com.lerx.user.vo.UserGroup;
import com.lerx.user.vo.UserInf;

public class UserDaoImp extends HibernateDaoSupport implements IUserDao {

	
	@Override
	public UserInf addUser(UserInf user,boolean pwdMD5ToLowerCase) {

		if (findUserByName(user.getUserName()) == null) {
			try {
				String salt = user.getSalt();
				if (salt == null || salt.trim().equals("")) {
					salt = StringUtil.randomString(6);
					user.setSalt(salt);
				}
				String passwordMd5;
				if (pwdMD5ToLowerCase){
					passwordMd5 = StringUtil.md5(
							StringUtil.md5(user.getPassword()).toLowerCase()
									.concat(salt)).toLowerCase();
				}else{
					passwordMd5 = StringUtil.md5(
							StringUtil.md5(user.getPassword())
									.concat(salt));
				}
				

				user.setPassword(passwordMd5);
				this.getHibernateTemplate().save(user);
				return user;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
		// TODO Auto-generated method stub

	}

	@Override
	public boolean delUser(User user) {
		try {
			this.getHibernateTemplate().delete(user);
			return true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}

	@Override
	public boolean delUserById(long id) {
		try {
			this.getHibernateTemplate().delete(
					this.getHibernateTemplate()
							.get("com.lerx.user.vo.User", id));
			return true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			return false;
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public User findUserByName(String name) {
		String hql = "from User u where u.userName=?";
		List<User> list = this.getHibernateTemplate().find(hql, name);

		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public User findUserByNameAndPassowrd(String name, String password,boolean pwdMD5ToLowerCase,boolean staCheck) {
		if (name==null || name.trim().equals("") || password==null || password.trim().equals("")){
			return null;
		}
		String hql;
		if (staCheck){
			hql = "from User a where a.userName=? and a.state is true";
		}else{
			hql = "from User a where a.userName=?";
		}
		
		List<User> list;
		try {
			list = this.getHibernateTemplate().find(hql, name.trim());
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			list=null;
			e.printStackTrace();
		}
		String md5Tmp;
		if (list!=null && !list.isEmpty()) {
			for (User iuser : list) {
				if (pwdMD5ToLowerCase){
					md5Tmp=
						StringUtil.md5(
								StringUtil.md5(password).toLowerCase()
										.concat(iuser.getSalt()))
								.toLowerCase();
				}else{
					md5Tmp=
						StringUtil.md5(
								StringUtil.md5(password)
										.concat(iuser.getSalt()));
				}
				if (iuser.getPassword() != null
						&& iuser.getSalt() != null
						&& iuser.getPassword().equals(md5Tmp)) {
					return iuser;
				}
			}
		}
		return null;
	}

	@Override
	public Rs findUserByGroup(int groupId, int page, int pageSize,
			int order, int orderMod, String findStr) {
		String hql;
		String orderStr, orderModStr;
		long count;
		// count= (Long)
		// this.getHibernateTemplate().find("select count(*) from User").get(0);
		//
		// Rs rs = RsInit.rsInit(page,pageSize,count);
		if (orderMod == 1) {
			orderModStr = " asc";
		} else {
			orderModStr = " desc";
		}
		switch (order) {
		case 1:
			orderStr = " order by u.userName " + orderModStr;
			break;
		case 2:
			orderStr = " order by u.state " + orderModStr;
			break;
		default:
			orderStr = " order by u.id " + orderModStr;
			break;
		}
		if (findStr == null || findStr.trim().equals("")) {
			if (groupId > 0) {
				hql = "from User u where u.userGroup.id=" + groupId + orderStr;
			} else {
				hql = "from User u " + orderStr;
			}
		} else {
			if (groupId > 0) {
				hql = "from User u where u.userName like '%" + findStr + "%' and u.userGroup.id="+ groupId
				+ orderStr;
			}else{
				hql = "from User u where u.userName like '%" + findStr + "%'"
				+ orderStr;
			}
			
		}

		count = (Long) this.getHibernateTemplate()
				.find("select count(*) " + hql).get(0);

//		Rs rs = RsInit.rsInit(page, pageSize, count);

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
//				List<User> list = query.list();
//
//				if (list.isEmpty()) {
//					// System.out.println("找不到记录");
//				}
//				// System.out.println("测试点5");
//				return list;
//			}
//
//		};
//		List<?> list;
//		list=getHibernateTemplate().executeFind(hc);
////		try {
////			list=getHibernateTemplate().executeFind(hc);
////		} catch (DataAccessException e) {
////			list=null;
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		if (list!=null){
//			rs.setList(list);
//		}
		
		return rs;
	}

	@Override
	public boolean modifyUser(User user) {
		try {
			this.getHibernateTemplate().saveOrUpdate(user);
			return true;
		} catch (RuntimeException re) {

			throw re;
		}
	}

	@Override
	public boolean modifyUserInf(UserInf user) {
		try {
			if (user.getPassingTime()==null){
				user.setPassingTime(new Timestamp(System.currentTimeMillis()));
			}
			this.getHibernateTemplate().saveOrUpdate(user);
			return true;
		} catch (RuntimeException re) {

			throw re;
		}
	}

	@Override
	public boolean setState(long id, boolean state) {
		try {
			User user = (User) this.getHibernateTemplate().get(
					"com.lerx.user.vo.User", id);
			user.setState(state);

			modifyUser(user);
			return true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			return false;
		}

	}

	@Override
	public User findUserById(long id) {
		return (User) this.getHibernateTemplate().get("com.lerx.user.vo.User",
				id);

	}

	@Override
	public UserInf findUserInfById(long id) {
		return (UserInf) this.getHibernateTemplate().get(
				"com.lerx.user.vo.User", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public User findUserByEmail(String email) {
		String hql = "from User u where u.email=?";

		List<User> list = this.getHibernateTemplate().find(hql, email);

		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}

	@Override
	public User findUserByUuid(String uuid) {
		String hql = "from User u where u.uuid=?";
		@SuppressWarnings("unchecked")
		List<User> list = this.getHibernateTemplate().find(hql, uuid);

		if (list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
		// TODO Auto-generated method stub
		// return null;
	}

	@Override
	public int checkUserOnSite(ChkUtilVo cuv) {
		IInterconnectionDao InterconnectionDaoImp=cuv.getInterconnectionDaoImp();
		ArticleGroup ag=cuv.getAg();
		UserCookie uc=cuv.getUc();
		int mode=cuv.getMode();
		boolean pwdMD5ToLowerCase=cuv.isPwdMD5ToLowerCase();
		int powerState = 0;
		if (uc == null) {
			// 测试
			powerState = 0;
		} else {
			if (uc.getUsername() == null || uc.getUsername().trim().equals("")
					|| uc.getPasswd() == null
					|| uc.getPasswd().trim().equals("")) {
				powerState = 0;
			} else {
				/*
				 * 数据库校验
				 * 
				 * 
				 */
				
				User u;
				if (uc.getOpenID()!=null && !uc.getOpenID().trim().equals("")){
					
					u=InterconnectionDaoImp.findUserByOpenID(uc.getOpenID(),1);
				}else{
					u = findUserByNameAndPassowrd(uc.getUsername(),
							uc.getPasswd(),pwdMD5ToLowerCase,true);
				}
				
				if (u == null || !u.isState()) {
					powerState = 0;
				} else {
					/*
					 * 权限检查
					 */
					UserGroup g = u.getUserGroup();

					if (g == null || !g.isState()) {
						powerState = 0;
//						String powerStr = g.getPowerMask();
//						String[] powerFilterArray = powerStr.split(",");
//						for (int step = 0; step < powerFilterArray.length; step++) {
//							if (powerFilterArray[step].equals("0")) {
//								powerState = 2;
//								break;
//
//							}
//						}
					} else {
						String powerStr = g.getPowerMask();
						String[] powerFilterArray = powerStr.split(",");
						for (int step = 0; step < powerFilterArray.length; step++) {
							if (mode == 1 && powerFilterArray[step].equals("f")) {
								powerState = 2;
								break;
							}
							if (powerFilterArray[step].equals("0")) {
								powerState = 2;
								break;

							}

							if (ag != null
									&& powerFilterArray[step].equals("p"
											+ ag.getId())) {
								powerState = 2;
								break;

							}
							if (powerFilterArray[step].equals("a0")) {
								powerState = 1;
								// break;

							}
							if (ag != null
									&& powerFilterArray[step].equals("a"
											+ ag.getId())) {
								powerState = 1;
								break;

							}

						}
					}

				}
			}
		}
		return powerState;
	}

	@Override
	public Rs findUserInfByGroupOrderByThread(int groupId, int page,
			int pageSize, int mode) {
		String hql;
		long count;
		String order;
		if (mode == 0) {
			order = " order by u.articleThreadsPassed desc";
		} else {
			order = " order by u.articleThreadsCount desc";
		}

		if (groupId > 0) {
			hql = "from UserInf u where u.state is true and u.userGroup.id=" + groupId + order;
		} else {
			hql = "from UserInf u where u.state is true " + order;
		}

		count = (Long) this.getHibernateTemplate()
				.find("select count(*) " + hql).get(0);

//		Rs rs = RsInit.rsInit(page, pageSize, count);

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
//				List<UserInf> list = query.list();
//
//				if (list.isEmpty()) {
//					// System.out.println("找不到记录");
//				}
//				// System.out.println("测试点5");
//				return list;
//			}
//
//		};
//		rs.setList(getHibernateTemplate().executeFind(hc));
		return rs;
	}

	@Override
	public long count(int mode) {
		String hql;
		switch (mode) {
		case 1:
			hql = "select count(*) from User u where u.state is true";
			break;
		case 2:
			hql = "select count(*) from User u where u.state is false";
			break;
		default:
			hql = "select count(*) from User";
			break;
		}
		long count = (Long) this.getHibernateTemplate().find(hql).get(0);
		return count;
	}

	@Override
	public boolean findUserByIpAndSaltOnSameDay(String ip, String salt) {
		long count = (Long) this
				.getHibernateTemplate()
				.find("select count(*) from User u where u.salt='" + salt
						+ "' and u.regIp='" + ip.trim()
						+ "' and TO_DAYS(u.regTimstamp)-TO_DAYS(CURDATE())=0")
				.get(0);
		if (count > 0) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public long importUser(UserInf user) {
		this.getHibernateTemplate().save(user);
//		System.out.println("增加的id:"+thread.getId());
		return user.getId();
	}

	@Override
	public int checkUserOnQa(ChkUtilVo cuv) {

		int powerState = 0;
		UserCookie uc=cuv.getUc();
		IInterconnectionDao interconnectionDaoImp=cuv.getInterconnectionDaoImp();
		QaNav qn=cuv.getQn();
		if (uc == null) {
			// 测试
			powerState = 0;
		} else {
			if (uc.getUsername() == null || uc.getUsername().trim().equals("")
					|| uc.getPasswd() == null
					|| uc.getPasswd().trim().equals("")) {
				powerState = 0;
			} else {
				/*
				 * 数据库校验
				 */
				String openID=uc.getOpenID();
				User u;
				TransferUserUtil tuu = new TransferUserUtil();
				User ut = new User();
				ut.setUserName(uc.getUsername());
				ut.setPassword(uc.getPasswd());
				ut.setId(uc.getUserId());
				tuu.setAs(cuv.getAs());
				tuu.setRequest(cuv.getRequest());
				tuu.setUser(ut);
				tuu.setUserDaoImp(cuv.getUserDaoImp());
				tuu.setW(true);
				tuu.setPwsMode(2);
				tuu.setStaCheck(true);
				
				if (openID!=null && !openID.trim().equals("")){
					u=interconnectionDaoImp.findUserByOpenID(openID, 1);
					tuu.setUser(u);
					tuu.setIgnorePws(true);
					u = UserUtil.check(tuu);
					u = cuv.getUserDaoImp().findUserById(u.getId());
				}else{
					tuu.setIgnorePws(false);
					u = UserUtil.check(tuu);
					u = cuv.getUserDaoImp().findUserById(u.getId());
				}
//				System.out.println("uc.getUsername():"+uc.getUsername());
				if (u == null || !u.isState()) {
					powerState = 0;
				} else {
					/*
					 * 权限检查
					 */
					UserGroup g = u.getUserGroup();

					if (g == null || !g.isState()) {
						powerState = 0;
					} else {
						String powerStr = g.getPowerMask();
//						System.out.println("powerStr:"+powerStr);
						String[] powerFilterArray = powerStr.split(",");
						for (int step = 0; step < powerFilterArray.length; step++) {
							if (powerFilterArray[step].equals("0")) {
								powerState = 1;
								break;

							}
							if (powerFilterArray[step].equals("q")) {
								powerState = 1;
								break;

							}

							if (qn != null
									&& powerFilterArray[step].equals("q"
											+ qn.getId())) {
								powerState = 1;
								break;

							}

						}
					}

				}
			}
		}
		return powerState;
	
	}

	@Override
	public boolean checkUserOnVote(ChkUtilVo cuv) {

		UserCookie uc=cuv.getUc();
		boolean powerState = false;
		IInterconnectionDao interconnectionDaoImp=cuv.getInterconnectionDaoImp();
		if (uc == null) {
			// 测试
			powerState = false;
		} else {
			if (uc.getUsername() == null || uc.getUsername().trim().equals("")
					|| uc.getPasswd() == null
					|| uc.getPasswd().trim().equals("")) {
				powerState = false;
			} else {
				/*
				 * 数据库校验
				 */
				String openID=uc.getOpenID();
				User u;
				TransferUserUtil tuu = new TransferUserUtil();
				User ut = new User();
				ut.setUserName(uc.getUsername());
				ut.setPassword(uc.getPasswd());
				ut.setId(uc.getUserId());
				tuu.setAs(cuv.getAs());
				tuu.setRequest(cuv.getRequest());
				tuu.setUser(ut);
				tuu.setUserDaoImp(cuv.getUserDaoImp());
				tuu.setW(true);
				tuu.setPwsMode(2);
				tuu.setStaCheck(true);
				
				if (openID!=null && !openID.trim().equals("")){
					u=interconnectionDaoImp.findUserByOpenID(openID, 1);
					tuu.setUser(u);
					tuu.setIgnorePws(true);
					u = UserUtil.check(tuu);
					u = cuv.getUserDaoImp().findUserById(u.getId());
				}else{
					tuu.setIgnorePws(false);
					u = UserUtil.check(tuu);
					u = cuv.getUserDaoImp().findUserById(u.getId());
				}
				
				
//				User u = findUserByNameAndPassowrd(uc.getUsername(),
//						uc.getPasswd(),pwdMD5ToLowerCase,true);
				if (u == null || !u.isState()) {
					powerState = false;
				} else {
					/*
					 * 权限检查
					 */
					UserGroup g = u.getUserGroup();

					if (g == null || !g.isState()) {
						powerState = false;
					} else {
						String powerStr = g.getPowerMask();
						String[] powerFilterArray = powerStr.split(",");
						for (int step = 0; step < powerFilterArray.length; step++) {
							if (powerFilterArray[step].equals("0")) {
								powerState = true;
								break;

							}
							if (powerFilterArray[step].equals("v")) {
								powerState = true;
								break;

							}


						}
					}

				}
			}
		}
		return powerState;
	
	
	}

	@Override
	public List<Long> findAllUser() {
		String hql="select u.id from User u" ;
		@SuppressWarnings("unchecked")
		List<Long> list = (List<Long>)this.getHibernateTemplate().find(hql);
		return list;
	}

	@Override
	public List<Long> findAllUserByGroup(int gid) {
		String hql="select u.id from User u where u.userGroup.id="+gid ;
		@SuppressWarnings("unchecked")
		List<Long> list = (List<Long>)this.getHibernateTemplate().find(hql);
		return list;
	}

	@Override
	public UserInf add(UserInf user, boolean pwdMD5ToLowerCase) {

		User u = findUserByName(user.getUserName());
		if (u == null) {
			try {
				String salt = user.getSalt();
				if (salt == null || salt.trim().equals("")) {
					salt = StringUtil.randomString(6);
					user.setSalt(salt);
				}
				String passwordMd5;
				if (pwdMD5ToLowerCase){
					passwordMd5 = StringUtil.md5(
							StringUtil.md5(user.getPassword()).toLowerCase()
									.concat(salt)).toLowerCase();
				}else{
					passwordMd5 = StringUtil.md5(
							StringUtil.md5(user.getPassword())
									.concat(salt));
				}
				user.setPassingTime(new Timestamp(System.currentTimeMillis()));

				user.setPassword(passwordMd5);
				this.getHibernateTemplate().save(user);
				return user;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return (UserInf) u;
		}
		// TODO Auto-generated method stub

	
	}

	@Override
	public Rs findUserByPasserUid(long uid, boolean state,int page,int pageSize) {

		String hql;
		String st;
		long count;
		String order;
		if (state){
			st="true";
		}else{
			st="false";
		}
			order = " order by u.id desc";
			
			hql = "from UserInf u where u.passer.id=" + uid + " and u.state is "+st+order;

			
			

		count = (Long) this.getHibernateTemplate()
				.find("select count(*) " + hql).get(0);

		
//		Rs rs = RsInit.rsInit(page, pageSize, count);

		HibernateCallbackUtilVo hcuv = new HibernateCallbackUtilVo();
		hcuv.setHql(hql);
		hcuv.setIbernateTemplate(this.getHibernateTemplate());
		hcuv.setPageSize(pageSize);
		hcuv.setCount(count);
		hcuv.setPage(page);
//		hcuv.setFirstResult(firstResult);
		Rs rs =  HibernateCallbackUtil.exeRs(hcuv);
//		
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
//				List<UserInf> list = query.list();
//
//				if (list.isEmpty()) {
//					// System.out.println("找不到记录");
//				}
//				// System.out.println("测试点5");
//				return list;
//			}
//
//		};
//		rs.setList(getHibernateTemplate().executeFind(hc));
		return rs;
	
	}

}
