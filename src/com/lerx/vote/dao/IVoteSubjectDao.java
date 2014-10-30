package com.lerx.vote.dao;

import java.util.List;

import com.lerx.vote.vo.VoteSubject;

public interface IVoteSubjectDao {
	public int add(VoteSubject vs);
	public boolean del(VoteSubject vs);
	public boolean delById(int id);
	public VoteSubject findById(int id);
	public boolean modify(VoteSubject vs);
	public List<VoteSubject> findAll(int mod);
	public boolean findByName(String subject);
}
