package com.lerx.vote.dao;

import com.lerx.draw.vo.Draw;
import com.lerx.sys.util.vo.Rs;
import com.lerx.vote.vo.VoteRec;
import com.lerx.vote.vo.VoteSubject;

public interface IVoteRecDao {
	public long add(VoteRec vr);
	public boolean del(VoteRec vr);
	public boolean delById(long id);
	public boolean delBySubId(int subId);
	public boolean modify(VoteRec vr);
	public VoteRec findById(long id);
	public boolean findRecByIpAndSaltOnSameDay(int subId,String ip, String salt);
	public boolean findRecByIpInTimeRange(int subId,String ip,int hours);
	public boolean findRecByPhoneCode(int subId,String phone);
	public boolean findRecByIdentity(int subId,String identity);
	public Rs findBySub(VoteSubject vs,int page,int pageSize);
	public Rs findMesBySub(VoteSubject vs,int page,int pageSize,boolean state);
	public Long findRecCountBySub(VoteSubject vs);
	public String draw(Draw draw,int n);
}

