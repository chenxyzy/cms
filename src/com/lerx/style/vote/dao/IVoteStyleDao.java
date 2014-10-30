package com.lerx.style.vote.dao;

import java.util.List;

import com.lerx.style.vote.vo.VoteStyle;

public interface IVoteStyleDao {
	public int add(VoteStyle style,boolean init);
	
	public int imp(VoteStyle style);
	public boolean del(VoteStyle style);
	public boolean delById(Integer id);
	public VoteStyle findById(Integer id);
	public List<VoteStyle> findAll(int mod);
	public boolean findStyleByName(String styleName);
	public boolean setDefault(int id);
	public VoteStyle findDefault();
}
