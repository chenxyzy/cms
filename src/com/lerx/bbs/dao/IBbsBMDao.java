package com.lerx.bbs.dao;

import java.util.List;

import com.lerx.bbs.vo.BM;
import com.lerx.bbs.vo.BMInfo;
import com.lerx.bbs.vo.BbsForum;
import com.lerx.user.vo.User;

public interface IBbsBMDao {
	public List<BMInfo> queryByFid(int fid);
	public boolean add(BM bm);
	public boolean del(BM bm);
	public boolean delById(long id);
	public boolean find(BM bm);
	public boolean checkPower(User user,BbsForum bf);
	public boolean chkIdcByUid(long uid);
}
