package com.lerx.user.dao;

import java.util.List;

import com.lerx.user.vo.UserGroup;

public interface IUserGroupDao {
	public boolean addUserGroup(UserGroup usergroup);
	public boolean delUserGroupById(int id);
	public boolean modifyUserGroup(UserGroup usergroup);
	public UserGroup findUserGroupByID(int id);
	public List<UserGroup> findAll();
	public boolean setStateById(int id,boolean state);
	public boolean findUserGroupByName(String name);
	public long countUsers(int id);
}
