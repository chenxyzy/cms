package com.lerx.user.dao.imp;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lerx.user.dao.IPasserDao;
import com.lerx.user.vo.Passer;
import com.lerx.user.vo.PasserLockUtilVo;
import com.lerx.user.vo.User;
import com.lerx.user.vo.UserGroup;

public class PasserDaoImp extends HibernateDaoSupport implements IPasserDao {

	@Override
	public Passer add(Passer passer) {
		this.getHibernateTemplate().save(passer);
		// TODO Auto-generated method stub
		return passer;
	}

	@Override
	public boolean delById(Long id) {
		try {
			this.getHibernateTemplate().delete(
					this.getHibernateTemplate()
							.get("com.lerx.user.vo.Passer", id));
			return true;
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			return false;
		}
	}

	@Override
	public Passer findById(Long id) {
		// TODO Auto-generated method stub
		return 
		(Passer) this.getHibernateTemplate()
		.get("com.lerx.user.vo.Passer", id);
	}

	@Override
	public Passer modify(Passer passer) {
		this.getHibernateTemplate().saveOrUpdate(passer);
		// TODO Auto-generated method stub
		return passer;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Passer> queryByUG(int ugId) {
		List<Passer> list;
		if (ugId==0){
//			list = this.getHibernateTemplate().loadAll(Passer.class);
			String hql="from Passer p order by p.passerInf1 asc";
			list = this.getHibernateTemplate().find(hql);
		}else{
			String hql="from Passer p where p.toUG.id=? order by p.passerInf1 asc";
			list = this.getHibernateTemplate().find(hql, ugId);

			
		}
		// TODO Auto-generated method stub
		if (list.isEmpty()) {
			return null;
		} else {
			return list;
		}
	}

	@Override
	public Passer find(User u, UserGroup ug) {
		String hql="from Passer p where p.toUG.id="+ug.getId()+" and p.user.id="+u.getId();
		@SuppressWarnings("unchecked")
		List<Passer> list = this.getHibernateTemplate().find(hql);
		if (list.isEmpty()){
			return null;
		}else{
			return list.get(0);
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public Passer find(User u) {
		String hql="from Passer p where p.user.id="+u.getId();
		@SuppressWarnings("unchecked")
		List<Passer> list = this.getHibernateTemplate().find(hql);
		if (list.isEmpty()){
			return null;
		}else{
			return list.get(0);
		}
	}

	@Override
	public boolean butchLock(PasserLockUtilVo pluv) {
		try {
			String lockInf1,lockInf2,lockPriTag1,lockPriTag2;
			if (pluv.isLockInf1()){
				lockInf1="p.lockPasserInf1=true";
			}else{
				lockInf1="p.lockPasserInf1=false";
			}
			
			if (pluv.isLockInf2()){
				lockInf2="p.lockPasserInf2=true";
			}else{
				lockInf2="p.lockPasserInf2=false";
			}
			
			if (pluv.isLockPriTag1()){
				lockPriTag1="p.lockPriTag1=true";
			}else{
				lockPriTag1="p.lockPriTag1=false";
			}
			
			if (pluv.isLockPriTag2()){
				lockPriTag2="p.lockPriTag2=true";
			}else{
				lockPriTag2="p.lockPriTag2=false";
			}
			String hqlTmp=lockInf1+","+lockInf2+","+lockPriTag1+","+lockPriTag2;
			String hql="update Passer p set "+hqlTmp;
			this.getHibernateTemplate().bulkUpdate(hql);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
		// TODO Auto-generated method stub
	}

}
