package com.lerx.user.dao.imp;

import java.util.List;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lerx.user.dao.IUserGroupDao;
import com.lerx.user.vo.UserGroup;

public class UserGroupDaoImp extends HibernateDaoSupport implements IUserGroupDao {

	@Override
	public boolean addUserGroup(UserGroup userGroup) {
		try {
			if (findUserGroupByName(userGroup.getGroupName())){
				return false;
			}else{
				this.getHibernateTemplate().save(userGroup);
				return true;
			}
			
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			return false;
		}

	}

	@Override
	public boolean delUserGroupById(int id) {
		if (countUsers(id)>0){
			return false;
		}
		this.getHibernateTemplate().delete(this.getHibernateTemplate().get(
				"com.lerx.user.vo.UserGroup", id));
		return true;
		
	}

	@Override
	public boolean modifyUserGroup(UserGroup usergroup) {
		try {
			this.getHibernateTemplate().saveOrUpdate(usergroup);
			return true;
		} catch (RuntimeException re) {

			throw re;
		}
	}

	@Override
	public UserGroup findUserGroupByID(int id) {
		return (UserGroup) this.getHibernateTemplate().get(
				"com.lerx.user.vo.UserGroup", id);
	}

	@Override
	public List<UserGroup> findAll() {
		List<UserGroup> list = this.getHibernateTemplate().loadAll(UserGroup.class);
		if (!list.isEmpty()){
			int len=list.size();
			long count;
			for (int i=0;i<len;i++){
				count=countUsers(list.get(i).getId());
				list.get(i).setUserCount(new Long(count).intValue());
			}
		}
		// TODO Auto-generated method stub
		return list;
	}

	@Override
	public boolean setStateById(int id,boolean state) {
		UserGroup userGroup=(UserGroup) this.getHibernateTemplate().get(
				"com.lerx.user.vo.UserGroup", id);
		userGroup.setState(state);
		modifyUserGroup(userGroup);
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean findUserGroupByName(String name) {
		String hql = "from UserGroup u where u.groupName=?";

		List<UserGroup> list = this.getHibernateTemplate().find(hql, name);

		if (list.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public long countUsers(int id) {
		String hql = "from User u where u.userGroup.id=?";
		long count = (Long) this.getHibernateTemplate()
		.find("select count(*) " + hql,id).get(0);
		// TODO Auto-generated method stub
		return count;
	}

}
