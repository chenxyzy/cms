package com.lerx.web.util.camp;

import com.lerx.sys.util.vo.FormatElements;
import com.lerx.web.vo.WebElements;

public class FelInit {
//	/*
//	 * 初始化格式化输出元素
//	 */

	public static FormatElements elInit(WebElements wel) {
		int mod=0,sta;
		sta=wel.getStation();
		mod = WebStation.check(sta);
		
		FormatElements fel = new FormatElements();
		switch (mod){
		case 1:
			fel.setArticleGroupDaoImp(wel.getArticleGroupDaoImp());
			fel.setArticleThreadDaoImp(wel.getArticleThreadDaoImp());
			
			break;
		case 2:
			fel.setBbsThemeDaoImp(wel.getBbsThemeDaoImp());
			fel.setBbsForumDaoImp(wel.getBbsForumDaoImp());
			fel.setScoreGroupDaoImp(wel.getScoreGroupDaoImp());
			fel.setScoreSchemeDaoImp(wel.getScoreSchemeDaoImp());
			String icoUrl=wel.getCurBbsStyle().getIcoFolderUrl();
			fel.setIcoUrlOfGeneral(icoUrl+"/sta/general.jpg");
			fel.setIcoUrlOfHot(icoUrl+"/sta/hot.jpg");
			fel.setIcoUrlOfNew(icoUrl+"/sta/new.jpg");
			fel.setIcoUrlOfStickedOnGlobal(icoUrl+"/sta/stickedOnGlobal.jpg");
			fel.setIcoUrlOfStickedOnClass(icoUrl+"/sta/stickedOnClass.jpg");
			fel.setIcoUrlOfStickedOnForum(icoUrl+"/sta/stickedOnForum.jpg");
			break;
			
		case 3:
			fel.setQaItemDaoImp(wel.getQaItemDaoImp());
			fel.setQaNavDaoImp(wel.getQaNavDaoImp());
			break;
			
		case 4:
			
			break;
		}
		
		
		
		fel.setAs(wel.getAs());
		fel.setDateFormatOnLine(wel.getDateFormatOnLine());
		fel.setLf(wel.getHrefLineFormat());
		fel.setEditAreaCode(wel.getEditAreaCode());
		fel.setFunctionAreaCode(wel.getFunctionAreaCode());
		fel.setIncludeEditArea(false);
		fel.setRequest(wel.getRequest());
		fel.setTitleLength(0);
		fel.setUserDaoImp(wel.getUserDaoImp());
		return fel;
	}
		
}
