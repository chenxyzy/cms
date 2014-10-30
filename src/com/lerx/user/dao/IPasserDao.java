package com.lerx.user.dao;

import java.util.List;

import com.lerx.user.vo.Passer;
import com.lerx.user.vo.PasserLockUtilVo;
import com.lerx.user.vo.User;
import com.lerx.user.vo.UserGroup;

public interface IPasserDao {
	public Passer add(Passer passer);
	public boolean delById(Long id);
	public Passer findById(Long id);
	
	public Passer modify(Passer passer);
	public List<Passer> queryByUG(int ugId);
	public Passer find(User u,UserGroup ug);
	public Passer find(User u);
	public boolean butchLock(PasserLockUtilVo pluv);
}
