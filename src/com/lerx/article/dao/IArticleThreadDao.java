package com.lerx.article.dao;

import java.sql.Timestamp;
import java.util.List;

import com.lerx.article.vo.ArticleGroup;
import com.lerx.article.vo.ArticleThread;
import com.lerx.sys.util.vo.Rs;

public interface IArticleThreadDao {
	public long add(ArticleThread thread);
	public boolean del(ArticleThread thread);
	public boolean delById(long id);
	public boolean modify(ArticleThread thread);
	public ArticleThread findById(long id);
	public Rs findByUserId(long userId,int gid,int page,int pageSize,int mod,int speedMod);
	public Rs findByGroup(int groupId,int page,int pageSize);
	public Rs findByGroupAndMod(int groupId,int page, int pageSize,int mod,int state,boolean notice,int soul,int firstResult,boolean image,int speedMod);
	public Rs findByGroupAndMod(String groupIdStr,int page, int pageSize,int mod,int state,boolean notice,int soul,int firstResult,boolean image,int speedMod);
	public Rs search(String key,int groupId,int page, int pageSize,boolean inBody);
	public boolean changeState(long id,boolean state);
	public boolean topOne(long id,boolean state);
	public boolean changeStateByStr(String hql);
	public ArticleThread findAdjacent(long id,int mod,int groupId,int stateMode);
	public boolean changeSoul(long id,boolean soul);
	public boolean articlesExistAtGroup(ArticleGroup articleGroup);
	public long findCountArticleInTodayByUserId(long userId,int groupId);
	public long count(int mode);
	public long countByUserId(long userId,int mode);
	public long countByUserIdAndOther(long userId,int mode,String navsStr,Timestamp begin,Timestamp end);
	public boolean changThreadUserToNull(long uid);
	public boolean delByUserId(long uid);
	public Rs findAll(int mod,int htmlCreated);
	public long move(int sGid,int tGid);
	public long countByAg(int gid);
	public List<Long> findAllID();
}
