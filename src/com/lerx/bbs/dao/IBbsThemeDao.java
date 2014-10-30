package com.lerx.bbs.dao;

import java.util.Date;

import com.lerx.bbs.vo.BbsTheme;
import com.lerx.bbs.vo.BbsThreadInf;
import com.lerx.sys.util.vo.HotScore;
import com.lerx.sys.util.vo.Rs;

public interface IBbsThemeDao {
	public long addBbsTheme(BbsTheme theme);
	public boolean modifyBbsTheme(BbsTheme theme);
	public boolean delBbsTheme(BbsTheme theme);
	public boolean delBbsThemeById(long id);
	public boolean delBbsThemeByRootId(long id);
	public BbsTheme findThemeById(long id);
	public BbsTheme findLastThemeByRoot(BbsTheme rootTheme);
	public long findThemesCountByRoot(BbsTheme rootTheme);
	public long findThemesCountByForumId(int id);
	
	public BbsTheme findLastThemeByForumId(int id);
	public BbsTheme findLastReplyThemeByForumId(int id);
	public BbsThreadInf findThreadInfByThemeId(long id);
	public BbsThreadInf findThreadInfByTheme(BbsTheme theme);
	public long findThreadsCountByForumId(int fid);
	public long findThreadsCountByDate(int fid,Date d);
	public long findThemesCountByDate(int fid,Date d);
	public int pageCountByRootThemeId(long tid,int pageSize);
	public Rs findThemesByParentThemeId(long id,int page,int pageSize,boolean sortMod);
	public Rs findThemesAndOwnByParentThemeId(long id,int page,int pageSize,boolean sortMod);
	public Rs findThemesByForumId(int id,int page,int pageSize,boolean sortMod);
	public Rs findAllThemesByForumId(int id,int page,int pageSize,boolean sortMod);
	public Rs findThemesByUserId(long uid,int page,int pageSize,boolean sortMod,boolean th);
	public Rs search(String key,int fid,boolean al,int page, int pageSize);
	public boolean changeThemeState(BbsTheme theme,boolean state);
	public boolean changeThemeTopMod(BbsTheme theme,int topMod);
	public BbsTheme findBySecCode(String secCode);
	public boolean shield(long tid,boolean s);
	public boolean top(long tid,boolean s);
	public Rs findThemesByRule(long fid,int smod,int hours,int page, int pageSize,HotScore hs);
	public boolean sink(BbsTheme theme,boolean sink);
	
	public BbsTheme findLastByUid(long uid);
	public boolean findReplyer(long tid,long uid);
	
}
