package com.lerx.user.dao;

import com.lerx.sys.util.vo.Rs;
import com.lerx.user.vo.User;
import com.lerx.user.vo.UserArtsCount;

public interface IUserArtsCountDao {
	public boolean modify(UserArtsCount uac);
	public UserArtsCount findByUK(User user,int timeKey);
	public Rs findTopByUKAndGroup(int groupId,int timeKey,int mod,int page,
			int pageSize);
}
