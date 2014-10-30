package com.lerx.user.util;

import com.lerx.sys.util.vo.Rs;
import com.lerx.user.dao.IPasserDao;
import com.lerx.user.dao.IUserDao;
import com.lerx.user.vo.Passer;
import com.lerx.user.vo.User;

public class PasserUtil {
	public static boolean chk(User u,IPasserDao passerDaoImp,IUserDao userDaoImp,int page,int pageSize,boolean state){
		Passer passer=passerDaoImp.find(u);
		if (passer!=null){
			
			Rs rs = userDaoImp.findUserByPasserUid(u.getId(), state, page,
					pageSize);
			if (!rs.getList().isEmpty()){
				return true;
			}
			
		}
		return false;
	}
}
