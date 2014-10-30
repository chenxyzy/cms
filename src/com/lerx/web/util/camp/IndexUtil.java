package com.lerx.web.util.camp;

import java.util.List;

import com.lerx.article.dao.IArticleThreadDao;
import com.lerx.article.util.ThreadUtil;
import com.lerx.article.vo.ArticleGroup;
import com.lerx.article.vo.ArticleThread;
import com.lerx.style.site.vo.SiteStyle;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.vo.FormatElements;
import com.lerx.sys.util.vo.Rs;
import com.lerx.web.vo.IndexAgExeElement;
import com.lerx.web.vo.IndexAgExeReturn;
import com.opensymphony.xwork2.ActionSupport;

public class IndexUtil {
	@SuppressWarnings("unchecked")
	public static IndexAgExeReturn htmlCreateByNav(IndexAgExeElement iaee){
		int speedMod=iaee.getSpeedMod();
		ArticleGroup ag=iaee.getAg();
		ActionSupport as=iaee.getAs();
		FormatElements fel=iaee.getFel();
		SiteStyle curSiteStyle=iaee.getCurSiteStyle();
//		String htmlTemplate=iaee.getHtmlTemplate();
		IArticleThreadDao articleThreadDaoImp = iaee.getArticleThreadDaoImp();
		IndexAgExeReturn iaer=new IndexAgExeReturn();
		boolean show=false;
		int noOpenShowMod;
		if (as.getText("lerx.articleNoOpenShowTitle").trim().equals("true")){
			noOpenShowMod=1;
		}else{
			noOpenShowMod=0;
		}
		int recNewMode=Integer.valueOf(as.getText("lerx.recNewMode"));
		if (ag.isShare() || (!ag.isShare() && noOpenShowMod==1)){
			show=true;
		}
		if (show && ag.isState()){

			int indexStrLength = ag.getLengthShowOnIndex();
			fel.setTitleLength(indexStrLength);
			
			int soul;
			if (ag.isSoulOnIndex()){
				soul=1;
			}else{
				soul=0;
			}
			/*
			 * 
			 * 以id会加快速度，但cms常常是以发表时间。（因为政府机关常常要改时间造假），所以在不求速度的情况下进行调整
			 */
			
			
			
			Rs rs = articleThreadDaoImp.findByGroupAndMod(
					ag.getId(), 0, ag.getNumberShowOnIndex(), recNewMode, 1,
					false, soul,0,false,speedMod);
			List<Long> lan = null;
			List<ArticleThread> lat = null;
			if (speedMod==1){
				lat=(List<ArticleThread>) rs.getList();
			}else{
				lan = (List<Long>) rs.getList();
			}
			
			String tmpA = "";

			boolean lfTmp = true;
			String lfFormat = null;
			
			// 如果首页模块存在格式
			if (curSiteStyle.getIndexStyle().getHrefLineFormat() != null
					&& !curSiteStyle.getIndexStyle()
							.getHrefLineFormat().trim().equals("")) {
				lfTmp = false;
				lfFormat = curSiteStyle.getIndexStyle()
						.getHrefLineFormat().trim();
			}
			// 如果所属文章组存在强制格式
			if (ag.getFormatOnIndex() != null
					&& !ag.getFormatOnIndex().trim().equals("")) {
				lfTmp = false;
				lfFormat = ag.getFormatOnIndex().trim();
			}
			// 如果上述均不成立或用上述取回的lfFormat为空字符串
			if (lfTmp || lfFormat == null || lfFormat.trim().equals("")) {
				if (curSiteStyle.getPublicStyle().getHrefLineFormat() != null
						&& !curSiteStyle.getPublicStyle()
								.getHrefLineFormat().trim().equals("")) {
					lfFormat = curSiteStyle.getPublicStyle()
							.getHrefLineFormat().trim();
				} else {
					lfFormat = curSiteStyle
							.getHrefLineFormatStrOverAll();
				}
			}
//			System.out.println("3:"+tmp+":5--已用时："
//					+ (System.currentTimeMillis() - wel.getPageStart()));
			indexStrLength = ag.getLengthShowOnIndex(); // 截取长度
			/*
			 * 格式取值说明 文章组强制属性 > index模块属性 > public模块属性 >
			 * 默认属性(hrefLineFormatStrOverAll)
			 */

			ArticleThread at;
			String lf;
			int step = 0;
			fel.setEditAreaCode(null);
			fel.setTitleLength(indexStrLength);
			fel.setIncludeEditArea(false);
			fel.setLf(lfFormat);
			
			if (speedMod==1){
				for (ArticleThread ati : lat) {
					fel.setLf(lfFormat);
					step++;
					at=ati;
					at=ThreadUtil.escape(at);
					lf = ThreadUtil.formatHref(fel, at);

					lf = StringUtil.strReplace(lf, "{$$sn$$}", "" + step);
					lf = StringUtil.strReplace(lf, "{$$sn0$$}", ""
							+ (step - 1));
					tmpA += lf;
				}
			}else{
				for (Long atid : lan) {
					fel.setLf(lfFormat);
					step++;
					at=articleThreadDaoImp.findById(atid);
					at=ThreadUtil.escape(at);
					lf = ThreadUtil.formatHref(fel, at);

					lf = StringUtil.strReplace(lf, "{$$sn$$}", "" + step);
					lf = StringUtil.strReplace(lf, "{$$sn0$$}", ""
							+ (step - 1));
					tmpA += lf;
				}
			}
			
			// 此处理为特殊处理，为了得到一个头尾为空，中间有间隔符号的字符串
			tmpA = StringUtil.strReplace(tmpA, "$$}{$$", ",");
			tmpA = StringUtil.strReplace(tmpA, "$$}", "");
			tmpA = StringUtil.strReplace(tmpA, "{$$", "");
			iaer.setTmpA(tmpA);
			iaer.setAgIconUrl(StringUtil.nullFilter(ag.getIconUrl()));
			
			
		
		}
		
		return iaer;
	
	}
	
	public static String rep(String htmlTemplate,int gid,IndexAgExeReturn iaer){
		String tmp=iaer.getTmpA();
		if (tmp!=null && !tmp.trim().equals("") && htmlTemplate.indexOf("{$$navIndexTopList,"
				+ gid+"$$}") != -1){
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$navIndexTopList," + gid + "$$}", tmp);

		}
		tmp=iaer.getAgIconUrl();
		if (tmp!=null && !tmp.trim().equals("")){
			htmlTemplate = StringUtil.strReplace(htmlTemplate,
					"{$$navIco," + gid + "$$}",
					tmp);
		}
		return htmlTemplate;
	}
}
