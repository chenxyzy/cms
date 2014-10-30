package com.lerx.vote.dao;

import java.util.List;

import com.lerx.vote.vo.VoteItem;
import com.lerx.vote.vo.VoteSubject;

public interface IVoteItemDao {
	public long add(VoteItem vi);
	public boolean modify(VoteItem vi);
	public boolean del(VoteItem vi);
	public boolean delById(long id);
	public VoteItem findById(long id);
	public long findMaxRecNum(int subId);
	public List<Long> queryAll(int mod,int subId,int state);
	public boolean setState(long id,boolean state);
	public Long count(VoteSubject vs,int mod);
	public boolean delBySubId(int subId);
	public boolean clearRecBySubId(int subId);
}
