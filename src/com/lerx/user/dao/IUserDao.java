package com.lerx.user.dao;

import java.util.List;

import com.lerx.sys.util.vo.Rs;
import com.lerx.user.vo.ChkUtilVo;
import com.lerx.user.vo.User;
import com.lerx.user.vo.UserInf;

public interface IUserDao {
	public UserInf addUser(UserInf user,boolean pwdMD5ToLowerCase) ;
	public UserInf add(UserInf user,boolean pwdMD5ToLowerCase) ;
	public boolean delUser(User user);
	public boolean delUserById(long id);
	public User findUserByName(String name);
	public User findUserByNameAndPassowrd(String name,String password,boolean pwdMD5ToLowerCase,boolean staCheck);
//	public User findUserByNameAndDbPassowrd(String name,String dbPassword);
	public Rs findUserByGroup(int groupId,int page,int pageSize,int order,int orderMod,String findStr);
	public Rs findUserInfByGroupOrderByThread(int groupId,int page,int pageSize,int mode);
	public boolean modifyUser(User user);
	public boolean modifyUserInf(UserInf user);
	public boolean setState(long id,boolean state);
	public User findUserById(long id);
	public UserInf findUserInfById(long id);
	public User findUserByEmail(String email);
	public User findUserByUuid(String uuid);
	public int checkUserOnSite(ChkUtilVo cuv);
	public int checkUserOnQa(ChkUtilVo cuv);
	public boolean checkUserOnVote(ChkUtilVo cuv);
	public long count(int mode);
	public boolean findUserByIpAndSaltOnSameDay(String ip,String salt);
	public long importUser(UserInf user);
	public List<Long> findAllUser();
	public List<Long> findAllUserByGroup(int gid);
	public Rs findUserByPasserUid(long uid,boolean state,int page,int pageSize);
	
}
