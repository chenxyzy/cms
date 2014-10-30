package com.lerx.sys.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.lerx.style.bbs.dao.IBbsStyleDao;
import com.lerx.style.bbs.vo.BbsStyle;
import com.lerx.style.qa.dao.IQaStyleDao;
import com.lerx.style.qa.vo.QaStyle;
import com.lerx.style.site.dao.ISiteStyleDao;
import com.lerx.style.site.vo.SiteStyle;
import com.lerx.style.vote.dao.IVoteStyleDao;
import com.lerx.style.vote.vo.VoteStyle;
import com.lerx.sys.dao.IOriginalSqlDao;
import com.lerx.sys.util.vo.MetaDataModel;
import com.opensymphony.xwork2.ActionSupport;

public class TableMetaDataAction extends ActionSupport implements
		ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;

	private int id;
	private String col;
	private String table;
	private IOriginalSqlDao originalSqlDaoImp;
	private ISiteStyleDao siteStyleDaoImp;
	private IBbsStyleDao bbsStyleDaoImp;
	private IQaStyleDao qaStyleDaoImp;
	private IVoteStyleDao voteStyleDaoImp;
	private int styleID;

	public int getStyleID() {
		return styleID;
	}

	public void setStyleID(int styleID) {
		this.styleID = styleID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCol() {
		return col;
	}

	public void setCol(String col) {
		this.col = col;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public void setOriginalSqlDaoImp(IOriginalSqlDao originalSqlDaoImp) {
		this.originalSqlDaoImp = originalSqlDaoImp;
	}

	public void setSiteStyleDaoImp(ISiteStyleDao siteStyleDaoImp) {
		this.siteStyleDaoImp = siteStyleDaoImp;
	}
	

	public void setBbsStyleDaoImp(IBbsStyleDao bbsStyleDaoImp) {
		this.bbsStyleDaoImp = bbsStyleDaoImp;
	}


	public void setQaStyleDaoImp(IQaStyleDao qaStyleDaoImp) {
		this.qaStyleDaoImp = qaStyleDaoImp;
	}

	public void setVoteStyleDaoImp(IVoteStyleDao voteStyleDaoImp) {
		this.voteStyleDaoImp = voteStyleDaoImp;
	}

	public String findMetaData() {

		// System.out.println("table:"+table);
		List<MetaDataModel> metaData = originalSqlDaoImp.getMetaData(styleID,
				table);
		request.setAttribute("metaData", metaData);
		request.setAttribute("styleId", styleID);
		int from;
		String fromTag=table.trim().toLowerCase();
		if (fromTag.equals("bbs_style") || fromTag.equals("bbs_style_element")){
			from=1;
		}else if (fromTag.equals("space_style") || fromTag.equals("space_style_element")){
			from=2;
		}else if (fromTag.equals("minspace_style") || fromTag.equals("minspace_style_element")){
			from=3;
		}else if(fromTag.equals("qa_style") || fromTag.equals("qa_style_element")){
			from=4;
		}else if(fromTag.equals("vote_style")|| fromTag.equals("vote_style_element")){
//			System.out.println("投票系统");
			from=5;
		}else if(fromTag.equals("draw_style")){
			from=6;
		}else{
			from=0;
		}
		
		
//		System.out.println("测试fromTag:"+fromTag);
//		System.out.println("测试from:"+from);
		switch (from) {
		case 1:
			BbsStyle bs = bbsStyleDaoImp.findById(styleID);
			request.setAttribute("pid", bs.getPublicStyle().getId());
			request.setAttribute("iid", bs.getIndexStyle().getId());
			request.setAttribute("sid", bs.getSearchStyle().getId());
			request.setAttribute("gid", bs.getGenericStyle().getId());
			request.setAttribute("fid", bs.getForumStyle().getId());
			request.setAttribute("eid", bs.getEditThreadStyle().getId());
			request.setAttribute("tid", bs.getThemeStyle().getId());
			break;
		case 2:
			break;
		case 3:
			break;
		case 4:
			QaStyle qs=qaStyleDaoImp.findById(styleID);
			request.setAttribute("pid", qs.getPublicStyle().getId());
			request.setAttribute("iid", qs.getIndexStyle().getId());
			request.setAttribute("nid", qs.getNavStyle().getId());
			request.setAttribute("qid", qs.getItemStyle().getId());
			break;
		case 5:
			VoteStyle vs=voteStyleDaoImp.findById(styleID);
			
//			System.out.println("pid:"+vs.getPublicStyle().getId());
//			System.out.println("iid:"+vs.getItemStyle().getId());
//			System.out.println("jid:"+vs.getJoinStyle().getId());
//			System.out.println("rid:"+vs.getResultStyle().getId());
//			System.out.println("vid:"+vs.getVoteStyle().getId());
			
			request.setAttribute("pid", vs.getPublicStyle().getId());
			request.setAttribute("iid", vs.getItemStyle().getId());
			request.setAttribute("jid", vs.getJoinStyle().getId());
			request.setAttribute("rid", vs.getResultStyle().getId());
			request.setAttribute("vid", vs.getVoteStyle().getId());
//			System.out.println("---投票系统");
			break;
		case 6:
			break;
		default:
			SiteStyle ss = siteStyleDaoImp.findStyleById(styleID);
			request.setAttribute("pid", ss.getPublicStyle().getId());
			request.setAttribute("iid", ss.getIndexStyle().getId());
			request.setAttribute("cid", ss.getClassStyle().getId());
			request.setAttribute("tid", ss.getThreadStyle().getId());
			request.setAttribute("rid", ss.getRegStyle().getId());
			request.setAttribute("lid", ss.getLoginStyle().getId());
			request.setAttribute("aid", ss.getArticleAddStyle().getId());
			request.setAttribute("eid", ss.getArticleEditStyle().getId());
			request.setAttribute("sid", ss.getSearchStyle().getId());
			request.setAttribute("gid", ss.getGenericStyle().getId());
			request.setAttribute("uid", ss.getUserCenterStyle().getId());
			request.setAttribute("mid", ss.getCommentStyle().getId());
//			System.out.println("默认系统");
			break;

		}

		
		return SUCCESS;
	}

	public String findColData() {

		col = (String) request.getAttribute("col");
		String sql = "select " + col + " from " + table + " where id="
				+ styleID;
		String colValue = originalSqlDaoImp.strSQL(sql);
		request.setAttribute("colValue", colValue);
		return SUCCESS;

	}

	public String test() {
		// System.out.println("方法3被 执行");
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}

}
