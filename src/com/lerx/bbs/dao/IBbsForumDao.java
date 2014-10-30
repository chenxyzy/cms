package com.lerx.bbs.dao;

import java.util.List;

import com.lerx.bbs.util.vo.BbsForumShowModel;
import com.lerx.bbs.vo.BbsForum;

public interface IBbsForumDao {
	public boolean addForum(BbsForum forum,BbsForum parentForum);
	public boolean addForum(BbsForum forum);
	public boolean delForum(BbsForum forum);
	public boolean delForumById(int id);
	public boolean modifyForum(BbsForum forum);
	public boolean modifyAndMoveBbsForum (BbsForum forum,int toParentId);
	public BbsForum findBbsForumById (int id);
	public BbsForum findParentBbsForumById (int id);
	public List<BbsForum> findBbsForumByParent(BbsForum parentForum);
	public List<BbsForum> findBbsForumByParentId(int parentForumId);
	public List<BbsForum> findAllBbsForum();
	
	List<BbsForumShowModel> findAllBbsForumModel(String prefix,String filler);
	public boolean sortDisplayOrder (int praentGroupId);
	public List<BbsForum> findBbsForumShowOnIndex();
	public boolean swapBbsForum(BbsForum s,BbsForum t);
	public List<BbsForum> findIPRange(BbsForum f);
	public BbsForum findLastPollParent(BbsForum f);
}
