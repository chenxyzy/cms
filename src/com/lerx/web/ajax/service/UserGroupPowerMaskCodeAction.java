package com.lerx.web.ajax.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.lerx.admin.util.AdminUtil;
import com.lerx.article.dao.IArticleGroupDao;
import com.lerx.article.util.vo.ArticleGroupShowModel;
import com.lerx.qa.dao.IQaNavDao;
import com.lerx.qa.model.QaNavShowModel;
import com.opensymphony.xwork2.ActionSupport;

public class UserGroupPowerMaskCodeAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpServletResponse response;
	HttpServletRequest request;
	private IArticleGroupDao articleGroupDaoImp;
	private IQaNavDao qaNavDaoImp;

	public void setArticleGroupDaoImp(IArticleGroupDao articleGroupDaoImp) {
		this.articleGroupDaoImp = articleGroupDaoImp;
	}

	public void setQaNavDaoImp(IQaNavDao qaNavDaoImp) {
		this.qaNavDaoImp = qaNavDaoImp;
	}

	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void create() throws Exception {
		if (checkAdmin()){

			
//			System.out.println("code:"+code);
			String areaCode = "", lineCode, aLineCodeAll = "";
			String adminlineCode = "", noticeCode = "", vdCode = "",addAllCode = "",qaAllCode="";
			boolean isAdmin = false, notice = false,voteAndDraw = false,addAll = false,qaAll=false;
			List<ArticleGroupShowModel> agt = articleGroupDaoImp
					.findAllModel(getText("lerx.treePrefixHead"),
							getText("lerx.treePrefixBody"));
			String[] powerFilterArray = code.split(",");
			for (int step = 0; step < powerFilterArray.length; step++) {
				if (powerFilterArray[step].equals("0")) {
					adminlineCode = "<tr><td align=\"left\">管理员</td><td><input type=\"radio\" value=\"\" name=\"admin\">否 <input type=\"radio\" name=\"admin\" value=\"0\" checked >是 </td></tr>";
					isAdmin = true;
					break;
				}
				if (powerFilterArray[step].equals("f")) {
					noticeCode = "<tr><td align=\"left\">文章系统公告权</td><td><input type=\"radio\" value=\"\" name=\"notice\">否 <input type=\"radio\" name=\"notice\" value=\"f\" checked >是 </td></tr>";
					notice = true;
					// break;
				}
				if (powerFilterArray[step].equals("q")) {
					qaAllCode = "<tr><td align=\"left\">问答系统管理员</td><td><input type=\"radio\" value=\"\" name=\"qa\">否 <input type=\"radio\" name=\"qa\" value=\"q\" checked >是 </td></tr>";
					qaAll = true;
					// break;
				}
				if (powerFilterArray[step].equals("v")) {
					vdCode = "<tr><td align=\"left\">投票及抽奖系统管理员</td><td><input type=\"radio\" value=\"\" name=\"voteAndDraw\">否 <input type=\"radio\" name=\"voteAndDraw\" value=\"v\" checked >是 </td></tr>";
					voteAndDraw = true;
//					System.out.println("voteAndDraw = true");
					// break;
				}
				if (powerFilterArray[step].equals("a0")) {
					addAllCode = "<tr><td align=\"left\">文章系统全部发表权</td><td><input type=\"radio\" value=\"\" name=\"addAll\">否 <input type=\"radio\" name=\"addAll\" value=\"a0\" checked >是 </td></tr>";
					addAll = true;
					// break;
				}
			}
			if (!agt.isEmpty()) {
				int state;
				for (ArticleGroupShowModel m : agt) {
					state = 0;

					

					for (int step = 0; step < powerFilterArray.length; step++) {
						
						if (!isAdmin){
							if (!addAll){
								if (powerFilterArray[step].equals("a"
										+ m.getArticleGroup().getId())) {
									state = 1;
									break;

								}
							}
							
							if (powerFilterArray[step].equals("p"
									+ m.getArticleGroup().getId())) {
								state = 2;
								break;

							}
						}
						
					}

					if (m.getArticleGroup().isAsClass()) {
						lineCode = "";
					} else {
						switch (state) {
						case 1:
							lineCode = "<input type=\"radio\" value=\"\" name=\"ag"
									+ m.getArticleGroup().getId()
									+ "\" />无或默认 <input type=\"radio\" name=\"ag"
									+ m.getArticleGroup().getId()
									+ "\" value=\"a"
									+ m.getArticleGroup().getId()
									+ "\" checked />发文 <input type=\"radio\" name=\"ag"
									+ m.getArticleGroup().getId() + "\" value=\"p"
									+ m.getArticleGroup().getId() + "\" />管理";

							break;
						case 2:
							lineCode = "<input type=\"radio\" value=\"\" name=\"ag"
									+ m.getArticleGroup().getId()
									+ "\" />无或默认 <input type=\"radio\" name=\"ag"
									+ m.getArticleGroup().getId() + "\" value=\"a"
									+ m.getArticleGroup().getId()
									+ "\" />发文 <input type=\"radio\" name=\"ag"
									+ m.getArticleGroup().getId() + "\" value=\"p"
									+ m.getArticleGroup().getId()
									+ "\" checked />管理";

							break;
						default:
							lineCode = "<input type=\"radio\" value=\"\" checked name=\"ag"
									+ m.getArticleGroup().getId()
									+ "\" />无或默认 <input type=\"radio\" name=\"ag"
									+ m.getArticleGroup().getId()
									+ "\" value=\"a"
									+ m.getArticleGroup().getId()
									+ "\" />发文 <input type=\"radio\" name=\"ag"
									+ m.getArticleGroup().getId()
									+ "\" value=\"p"
									+ m.getArticleGroup().getId() + "\" />管理";

							break;
						}
					}

					aLineCodeAll += "<tr><td align=\"left\">" + m.getPrefixStr()
							+ m.getArticleGroup().getGroupName() + "</td><td>"
							+ lineCode + "</td></tr>";
				}
			}
			/*
			 * 
			 * 问答系统
			 */
			String eLineCodeAll="";
			List<QaNavShowModel> qgt = qaNavDaoImp.findAllQaNavModel(
					getText("lerx.treePrefixHead"), getText("lerx.treePrefixBody"));
			if (!qgt.isEmpty()) {
				int state;
				for (QaNavShowModel m : qgt) {
					state = 0;

					powerFilterArray = code.split(",");
					for (int step = 0; step < powerFilterArray.length; step++) {
						
						if (!qaAll){
							if (powerFilterArray[step].equals("q"
									+ m.getQaNav().getId())) {
								state = 1;
								break;

							}
						}
						
					}
					if (m.getQaNav().getParentNav() == null) {
						lineCode = "";
					} else {
						switch (state) {
					
						case 1:
							lineCode = "<input type=\"radio\" value=\"\" name=\"qg"
									+ m.getQaNav().getId()
									+ "\" />无或默认 <input type=\"radio\" name=\"qg"
									+ m.getQaNav().getId() + "\" value=\"q"
									+ m.getQaNav().getId() + "\" checked />管理";

							break;
						default:
							lineCode = "<input type=\"radio\" value=\"\" checked name=\"qg"
									+ m.getQaNav().getId()
									+ "\" />无或默认 <input type=\"radio\" name=\"qg"
									+ m.getQaNav().getId()
									+ "\" value=\"q"
									+ m.getQaNav().getId() + "\" />管理";

							break;
						}
					}

					eLineCodeAll += "<tr><td align=\"left\">" + m.getPrefixStr()
							+ m.getQaNav().getTitle() + "</td><td>" + lineCode
							+ "</td></tr>";
				}
			}

			if (!isAdmin) {
				adminlineCode = "<tr><td align=\"left\">管理员</td><td><input type=\"radio\" value=\"\" checked name=\"admin\">否 <input type=\"radio\" name=\"admin\" value=\"0\" >是 </td></tr>";

			}
			if (!notice) {
				noticeCode = "<tr><td align=\"left\">文章系统公告权</td><td><input type=\"radio\" value=\"\" checked name=\"notice\">否 <input type=\"radio\" name=\"notice\" value=\"f\" >是 </td></tr>";

			}
			if (!addAll) {
				addAllCode = "<tr><td align=\"left\">文章系统全部发表权</td><td><input type=\"radio\" value=\"\" checked name=\"addAll\">否 <input type=\"radio\" name=\"addAll\" value=\"a0\" >是 </td></tr>";

			}
			if (!qaAll) {
				qaAllCode = "<tr><td align=\"left\">问答系统管理员</td><td><input type=\"radio\" value=\"\" checked name=\"qaAll\">否 <input type=\"radio\" name=\"qaAll\" value=\"q\" >是 </td></tr>";

			}
			if (!voteAndDraw) {
//				System.out.println("voteAndDraw = false");
				vdCode = "<tr><td align=\"left\">投票及抽奖系统管理员</td><td><input type=\"radio\" value=\"\" checked name=\"voteAndDraw\">否 <input type=\"radio\" name=\"voteAndDraw\" value=\"v\" >是 </td></tr>";

			}
			String adminHeadCode="<tr><td width=\"100%\"  bgcolor=\"#C0C0C0\" colspan=\"2\">基本权限设置</td></tr>";
			String adminAgHeadCode="<tr><td width=\"100%\"  bgcolor=\"#C0C0C0\" colspan=\"2\">文章栏目权限设置</td></tr>";
			String qaHeadCode="<tr><td width=\"100%\"  bgcolor=\"#C0C0C0\" colspan=\"2\">问答系统权限设置</td></tr>";
			areaCode = "<table cellspacing=\"0\" cellpadding=\"0\" border=\"1\" width=\"100%\">"
					+ adminHeadCode+adminlineCode
					+ noticeCode
					+ addAllCode
					+ qaAllCode
					+ vdCode
					
					+ adminAgHeadCode+aLineCodeAll
					+ qaHeadCode+eLineCodeAll
					+ "</table>";
//			System.out.println(areaCode);
			response.setCharacterEncoding(getText("lerx.charset"));
			response.setContentType("text/html;charset="+getText("lerx.charset"));
			response.getWriter().write(areaCode);
		
		
		}else{
			response.setCharacterEncoding(getText("lerx.charset"));
			response.setContentType("text/html;charset="+getText("lerx.charset"));
			response.getWriter().write(getText("lerx.fail.auth"));
		}
	}
	
	private boolean checkAdmin(){
		return AdminUtil.checkAdmin(this,getText("lerx.host.current"), request);
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;

	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}

}
