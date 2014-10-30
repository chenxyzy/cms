package com.lerx.admin.dao.imp;

import java.util.List;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.lerx.sys.util.*;
import com.lerx.admin.dao.IAdminDao;
import com.lerx.admin.vo.Admin;

public class AdminDaoImp extends HibernateDaoSupport implements IAdminDao {

	@Override
	public boolean addAdmin(Admin admin,boolean pwdMD5ToLowerCase) {
		try {
			String salt=StringUtil.randomString(6);
			String passwordMd5;
			if (pwdMD5ToLowerCase){
				passwordMd5=StringUtil.md5(StringUtil.md5(admin.getPassword()).toLowerCase().concat(salt)).toLowerCase();
			}else{
				passwordMd5=StringUtil.md5(StringUtil.md5(admin.getPassword()).concat(salt));
			}
			
			admin.setSalt(salt);
			admin.setPassword(passwordMd5);
			admin.setState(true);
			
			this.getHibernateTemplate().save(admin);
			return true;

		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public boolean delAdmin(Admin admin) {
		try {
			this.getHibernateTemplate().delete(admin);
			return true;
		} catch (RuntimeException re) {
			throw re;
		}
		// TODO Auto-generated method stub

	}

	@Override
	public boolean modifyAdmin(Admin admin) {
		try {
			this.getHibernateTemplate().saveOrUpdate(admin);
			return true;
		} catch (RuntimeException re) {

			throw re;
		}
	}

	@Override
	public boolean delAdminById(int id) {
		try {
			//this.getHibernateTemplate().delete(this.getHibernateTemplate.get("com.lerx.admin.vo.Admin", id));
			
//			Admin admin = (Admin) this.getHibernateTemplate().get(
//					"com.lerx.admin.vo.Admin", id);
			this.getHibernateTemplate().delete(this.getHibernateTemplate().get(
					Admin.class, id));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Admin findAdminById(int id) {
		return (Admin) this.getHibernateTemplate().get(
				Admin.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean findAdminByName(String name) {
		String hql = "from Admin a where a.name=?";

		List<Admin> list = this.getHibernateTemplate().find(hql, name.trim().toLowerCase());

		if (list.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public int findAdminByNameAndPassword(String name, String password,boolean pwdMD5ToLowerCase) {
		String hql = "from Admin a where a.name=? and a.state=true";
		String md5Tmp;
		List<Admin> list = this.getHibernateTemplate().find(hql, name.trim().toLowerCase());
		if (!list.isEmpty()) {
			for (Admin iadmin : list) {
				if (pwdMD5ToLowerCase){
					md5Tmp=
						StringUtil.md5(
								StringUtil.md5(password).toLowerCase()
										.concat(iadmin.getSalt()))
								.toLowerCase();
				}else{
					md5Tmp=
						StringUtil.md5(
								StringUtil.md5(password)
										.concat(iadmin.getSalt()));
				}
				if (iadmin.getPassword()!=null && iadmin.getSalt()!=null && iadmin.getPassword().equals(md5Tmp)) {
					return iadmin.getId();
				}
			}
		}
		return 0;
	}

	@Override
	public List<Admin> findAllAdmin() {
		List<Admin> list = this.getHibernateTemplate().loadAll(Admin.class);
		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public boolean setState(int id,boolean state) {
		Admin admin=(Admin) this.getHibernateTemplate().get(
				Admin.class, id);
		admin.setState(state);
		modifyAdmin(admin);
		return true;
	}
}
