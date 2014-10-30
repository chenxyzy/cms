package com.lerx.user.util;

import java.util.List;

import com.lerx.article.dao.IArticleGroupDao;
import com.lerx.article.vo.ArticleGroup;
import com.lerx.sys.util.StringUtil;
import com.lerx.user.vo.UserGroup;
import com.opensymphony.xwork2.ActionSupport;

public class UserGroupUtil {
	public static UserGroup repair(ActionSupport as,IArticleGroupDao articleGroupDaoImp,UserGroup ug){
		if (as.getText("lerx.userGroupNavsSelectStr").trim().equalsIgnoreCase("true")){
			String powerMask=ug.getPowerMask();
			if (powerMask!=null && !powerMask.trim().equals("")){
				boolean save=true;
				String[] powerMaskArray = powerMask.split(",");
				for (int step = 0; step < powerMaskArray.length; step++) {
					if (powerMaskArray[step].equals("0") || powerMaskArray[step].equals("a0")) {	//如果是管理员或全部发表权就不处理
						save=false;
						break;

					}
				}
				if (save){
					String agSelectStr="",mask,curMask;
					int agId;
					for (int step = 0; step < powerMaskArray.length; step++) {
						mask=powerMaskArray[step];
						if (!mask.trim().equals("")){
							mask=mask.trim();
							String tmp=mask.substring(0, 1);
							if (tmp.equals("a") || tmp.equals("p")){
								mask=StringUtil.filterUnNumber(mask);	//过滤非数字字符
								if (mask!=null && !mask.trim().equals("")){
									agId=Integer.valueOf(mask.trim());
									//找到该组上所有上层
									List<ArticleGroup> list=articleGroupDaoImp.findParentById(agId);
									for (ArticleGroup ag:list){
										curMask="{$$"+ag.getId()+"$$}";
										if (agSelectStr.indexOf(curMask)== -1){
											if (agSelectStr.trim().equals("")){
												agSelectStr+=curMask;
											}else{
												agSelectStr+=","+curMask;
											}
										}
										
									}
									curMask="{$$"+agId+"$$}";
									if (agSelectStr.trim().equals("")){
										agSelectStr+=curMask;
									}else{
										agSelectStr+=","+curMask;
									}
									
									
								}
							}
						}
					}
					if (agSelectStr!=null && !agSelectStr.trim().equals("")){
						agSelectStr=StringUtil.strReplace(agSelectStr, "$$},{$$", ",");
						agSelectStr=StringUtil.strReplace(agSelectStr, "{$$", "");
						agSelectStr=StringUtil.strReplace(agSelectStr, "$$}", "");
						ug.setSiteArticleGroupsSelectCustomStr(agSelectStr);
					}
					
					
				}
			}
			
			
			
			
			
		}
		return ug;
	}
}
