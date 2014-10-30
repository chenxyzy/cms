package com.lerx.user.dao;

import com.lerx.user.vo.Interconnection;
import com.lerx.user.vo.User;

public interface IInterconnectionDao {
	public Interconnection create(User user,int type,String openID);
	public User findUserByOpenID(String openID,int type);
	public Interconnection findUserByUid(long uid,int type);
	public Interconnection findIcByOpenID(String openID,int type);
	public boolean clear(User user,int type);
}
