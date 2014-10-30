package com.lerx.web.ajax.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.simple.JSONObject;

import com.lerx.qa.dao.IQaItemDao;
import com.lerx.qa.dao.IQaNavDao;
import com.lerx.qa.util.QaItemUtil;
import com.lerx.qa.vo.QaItem;
import com.lerx.qa.vo.QaNav;
import com.lerx.site.dao.ISiteInfoDao;
import com.lerx.style.qa.dao.IQaStyleDao;
import com.lerx.style.qa.vo.QaStyle;
import com.lerx.style.qa.vo.QaStyleSubElementInCommon;
import com.lerx.style.site.dao.ISiteStyleDao;
import com.lerx.style.site.util.SiteStyleUtil;
import com.lerx.style.site.vo.SiteStyle;
import com.lerx.sys.util.CdmUtil;
import com.lerx.sys.util.CookieUtil;
import com.lerx.sys.util.RsInit;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.vo.CookieDoModel;
import com.lerx.sys.util.vo.FormatElements;
import com.lerx.sys.util.vo.Rs;
import com.lerx.sys.util.vo.UserCookie;
import com.lerx.user.dao.IInterconnectionDao;
import com.lerx.user.dao.IUserDao;
import com.lerx.user.vo.ChkUtilVo;
import com.opensymphony.xwork2.ActionSupport;

public class QaItemShowAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int gid;
	private IQaStyleDao qaStyleDaoImp;
	private IQaItemDao qaItemDaoImp;
	private IQaNavDao qaNavDaoImp;
	private IUserDao userDaoImp;
	private IInterconnectionDao interconnectionDaoImp;
	private ISiteStyleDao siteStyleDaoImp;
	private UserCookie uc;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private int page;
	private int pageSize;
	private int n;
	private int l;
	private int mod;
	private int f;
	private int showMode;
	private String url;
	private boolean json;

	public boolean isJson() {
		return json;
	}

	public void setJson(boolean json) {
		this.json = json;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getShowMode() {
		return showMode;
	}

	public void setShowMode(int showMode) {
		this.showMode = showMode;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getL() {
		return l;
	}

	public void setL(int l) {
		this.l = l;
	}

	public int getMod() {
		return mod;
	}

	public void setMod(int mod) {
		this.mod = mod;
	}

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public int getF() {
		return f;
	}

	public void setF(int f) {
		this.f = f;
	}

	public void setQaStyleDaoImp(IQaStyleDao qaStyleDaoImp) {
		this.qaStyleDaoImp = qaStyleDaoImp;
	}

	public void setQaItemDaoImp(IQaItemDao qaItemDaoImp) {
		this.qaItemDaoImp = qaItemDaoImp;
	}

	public void setQaNavDaoImp(IQaNavDao qaNavDaoImp) {
		this.qaNavDaoImp = qaNavDaoImp;
	}

	public void setInterconnectionDaoImp(IInterconnectionDao interconnectionDaoImp) {
		this.interconnectionDaoImp = interconnectionDaoImp;
	}

	public void setUserDaoImp(IUserDao userDaoImp) {
		this.userDaoImp = userDaoImp;
	}

	public void setSiteInfDaoImp(ISiteInfoDao siteInfDaoImp) {
	}

	public void setSiteStyleDaoImp(ISiteStyleDao siteStyleDaoImp) {
		this.siteStyleDaoImp = siteStyleDaoImp;
	}

	@SuppressWarnings("unchecked")
	public void show() throws IOException {
		String pageShowStr="";
		SiteStyle curStyle = siteStyleDaoImp.findDef();
		if (page == 0) {
			page = 1;
		}
		if (n > 0) {
			pageSize = n;
		}
		if (pageSize <= 0) {
			pageSize = Integer.valueOf(getText("lerx.pageSize.result.default"));
		}
		String lf = "", tmpAll = "";
		QaItem qi;
		if (gid == 0) {
			tmpAll = "";
		} else {
			QaNav qn = qaNavDaoImp.findById(gid);

			if (qn == null) {
				tmpAll = "";

			} else {
				int state, open;
				CookieDoModel cdm =CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
//				CookieDoModel cdm = initCdm();
				uc = CookieUtil.query(cdm);
				if (uc!=null){
				}else{
					showMode=0;
				}
				
				if (showMode == 1) {
					
					if (checkUserOnQa(qn) == 1) {
						open = 0;
						state = 0;
					} else {
						state = 1;
						open = 1;
					}
				}else{
					state = 1;
					open = 1;
				}
				QaStyle qaStyle = qn.getStyle();
				if (qaStyle == null) {
					qaStyle = qaStyleDaoImp.findDefault();
				}
				if (qaStyle != null) {
					QaStyleSubElementInCommon ec = qaStyle.getPublicStyle();
					if (f == 1) {
						lf = ec.getItemsLoopFormat();
					} else {
						lf = ec.getItemsLoopFormatNoStateInf();
						state = 1;
					}

					List<Long> qil;
					Rs rs;
					FormatElements qiel = elQaItemInit();
					rs = qaItemDaoImp.findQaItemsByGroupAndMod(qn.getId(),
							page, pageSize, mod, state, open, 0);
					
					
					pageShowStr=RsInit.rsPageStrShowAtFun(rs, url, SiteStyleUtil.pageFormatShowInit(this,curStyle));
					qiel.setLf(lf);
					if (rs.getList().size() > 0) {
						qil = (List<Long>) rs.getList();

						for (Long qiid : qil) {
							qi = qaItemDaoImp.findById(qiid);
							qi.setTitle(StringUtil.escape(qi.getTitle()));
							lf = QaItemUtil.formatHref(qiel, qi);
							if (state == 0) {
								if (qi.isOpen()) {
									lf = StringUtil.strReplace(lf,
											"{$$openStr$$}",
											qaStyle.getOpenStr());
								} else {
									lf = StringUtil.strReplace(lf,
											"{$$openStr$$}",
											qaStyle.getNoOpenStr());
								}
								if (qi.isState()) {
									lf = StringUtil.strReplace(lf,
											"{$$processedStr$$}",
											qaStyle.getProcessedStr());
								} else {
									lf = StringUtil.strReplace(lf,
											"{$$processedStr$$}",
											qaStyle.getNoProcessedStr());
								}

							} else {
								lf = StringUtil.strReplace(lf, "{$$openStr$$}",
										"");
								if (qi.isState()) {
									lf = StringUtil.strReplace(lf,
											"{$$processedStr$$}",
											qaStyle.getProcessedStr());
								} else {
									lf = StringUtil.strReplace(lf,
											"{$$processedStr$$}",
											qaStyle.getNoProcessedStr());
								}
							}
							tmpAll += lf;
						}

					}
				} else {
					tmpAll = "";
				}

			}

		}
		String outStr;
		if (json){
			JSONObject obj = new JSONObject();
			obj.put("body", tmpAll);
			obj.put("pageShow", pageShowStr);
			outStr=obj.toJSONString();
		}else{
			outStr=tmpAll;
		}
		
		response.setCharacterEncoding(getText("lerx.charset"));
		response.setContentType("text/html;charset=" + getText("lerx.charset"));
		response.getWriter().write(outStr);
	}

	private FormatElements elQaItemInit() {
		FormatElements qiel = new FormatElements();
		qiel.setAs(this);
		qiel.setRequest(request);
		qiel.setQaItemDaoImp(qaItemDaoImp);
		return qiel;
	}

	private int checkUserOnQa(QaNav qn) throws IOException {
		boolean pwdMD5ToLowerCase;
		if (getText("lerx.pwdMD5ToLowerCase").trim().equalsIgnoreCase("true")) {
			pwdMD5ToLowerCase = true;
		} else {
			pwdMD5ToLowerCase = false;
		}
		
		UserCookie uc;
		CookieDoModel cdm = CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
		uc = CookieUtil.query(cdm);
		ChkUtilVo cuv = new ChkUtilVo();
		cuv.setAs(this);
		cuv.setInterconnectionDaoImp(interconnectionDaoImp);
		cuv.setRequest(request);
		cuv.setPwdMD5ToLowerCase(pwdMD5ToLowerCase);
		cuv.setUc(uc);
		cuv.setUserDaoImp(userDaoImp);
		cuv.setQn(qn);
		
		return userDaoImp.checkUserOnQa(cuv);

	}

//	private CookieDoModel initCdm() {
//		CookieDoModel cdm = new CookieDoModel();
//		cdm.setActionSupport(this);
//		cdm.setEncodingCode(getText("lerx.charset").trim());
//		cdm.setPrefix(getText("lerx.prefixOfCookieForLogin"));
//		cdm.setHost(getText("lerx.host.current"));
//		cdm.setHostSecFile(getText("lerx.hostSecFile"));
//		cdm.setRequest(request);
//		cdm.setResponse(response);
//		cdm.setUserDaoImp(userDaoImp);
//		return cdm;
//	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;

	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}

}
