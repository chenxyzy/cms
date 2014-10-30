package com.lerx.admin.dao;

import java.util.List;

import com.lerx.admin.vo.Admin;

public interface IAdminDao {
	public boolean addAdmin(Admin admin,boolean pwdMD5ToLowerCase);
	public boolean delAdmin(Admin admin);
	public boolean modifyAdmin(Admin admin);
	public boolean delAdminById(int id);
	public Admin findAdminById(int id);
	public boolean findAdminByName(String name);
	public int findAdminByNameAndPassword(String name,String password,boolean pwdMD5ToLowerCase);
	public List<Admin> findAllAdmin();
	public boolean setState(int id,boolean state);
}
