package com.lerx.bbs.dao.imp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lerx.bbs.dao.IBbsBMDao;
import com.lerx.bbs.vo.BM;
import com.lerx.bbs.vo.BMInfo;
import com.lerx.bbs.vo.BbsForum;
import com.lerx.user.vo.User;

public class BbsBMDaoImp extends HibernateDaoSupport implements IBbsBMDao {

	
	@Override
	public List<BMInfo> queryByFid(int fid) {
		String hql="from BM b where b.bf.id=?";
		@SuppressWarnings("unchecked")
		List<BM> list = this.getHibernateTemplate().find(hql,fid);
		List<BMInfo> out= new ArrayList<BMInfo>();
		@SuppressWarnings("rawtypes")
		Iterator itr = list.iterator();
		BMInfo bmi;
		while (itr.hasNext()) {
			bmi = new BMInfo();
		    BM bm = (BM) itr.next();
		    User u=(User) this.getHibernateTemplate().get("com.lerx.user.vo.User", bm.getUser().getId());
		    bmi.setName(u.getUserName());
		    bmi.setUid(u.getId());
		    bmi.setId(bm.getId());
		    out.add(bmi);
		}

		return out;
	}

	@Override
	public boolean add(BM bm) {
		
		this.getHibernateTemplate().saveOrUpdate(bm);
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean del( BM bm) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delById(long id) {
		try {
			this.getHibernateTemplate().delete(
					this.getHibernateTemplate()
							.get("com.lerx.bbs.vo.BM", id));
			return true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			return false;
		}

	}

	@Override
	public boolean find(BM bm) {
		String hql="from BM b where b.bf.id="+bm.getBf().getId() + " and b.user.id=" + bm.getUser().getId();
		@SuppressWarnings("unchecked")
		List<BM> list = this.getHibernateTemplate().find(hql);
		if (list.isEmpty()){
			return false;
		}else{
			return true;
		}
		
	}

	@Override
	public boolean checkPower(User user, BbsForum bf) {
		boolean r;
		String hql="from BM b where b.bf.footerLeft<="+bf.getFooterLeft() + " and b.bf.footerRight>= "+bf.getFooterRight()+" and b.user.id=" + user.getId();
		@SuppressWarnings("unchecked")
		List<BM> list = this.getHibernateTemplate().find(hql);
		if (list.isEmpty()){
			r= false;
		}else{
			r=true;
		}

//		if (!r){
//			hql="from BM b where b.bf.footerLeft<="+bf.getFooterLeft() + " and b.bf.footerRight>= "+bf.getFooterRight()+" and b.user.id=" + user.getId();
//			System.out.println("hql:"+hql);
//			list = this.getHibernateTemplate().find(hql);
//			if (list.isEmpty()){
//				r= false;
//			}else{
//				r=true;
////				System.out.println("发现上级已有了！");
//			}
//		}
		
		return r;
	}

	@Override
	public boolean chkIdcByUid(long uid) {
		String hql="from BM b where b.user.id=" + uid;
		@SuppressWarnings("unchecked")
		List<BM> list = this.getHibernateTemplate().find(hql);
		if (list.isEmpty()){
			return false;
		}else{
			return true;
		}
	}

}
