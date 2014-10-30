package com.lerx.article.dao;

import java.util.List;

import com.lerx.article.util.vo.ArticleGroupShowModel;
import com.lerx.article.vo.ArticleGroup;
import com.lerx.style.site.vo.SiteStyle;

public interface IArticleGroupDao {
	
	public boolean add(ArticleGroup articleGroup,ArticleGroup praentGroup);
	public boolean add(ArticleGroup articleGroup);
	public boolean del(ArticleGroup articleGroup);
	public boolean delById (int id);
	public boolean modify(ArticleGroup articleGroup);
	public boolean modifyAndMove(ArticleGroup articleGroup,int toParentId);
	public ArticleGroup findById (int id);
	public List<ArticleGroup> findParentById (int id);
	public List<ArticleGroup> findByParent(ArticleGroup parentGroup);
	public List<ArticleGroup> findByParentId(int parentGroupId);
	public List<ArticleGroup> findAll();
	public List<ArticleGroup> findAllChanged();
	List<ArticleGroupShowModel> findAllModel(String prefix,String filler);
	public boolean sortDisplayOrder (int praentGroupId);
	public List<ArticleGroup> findShowOnIndex(int mod);
	public boolean swap(ArticleGroup s,ArticleGroup t);
	public String findAllHostAllowStrByArticleGroup(ArticleGroup g);
	public boolean checkShare(ArticleGroup g);
	public boolean statViews();
	public SiteStyle findParentForceStyle(ArticleGroup g);
	public boolean changed(ArticleGroup g);
	public ArticleGroup findLastPollParent(ArticleGroup g);
	public long countByAg(int gid);
	
}
