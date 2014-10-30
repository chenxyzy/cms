package com.lerx.web.service;

import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.lerx.article.dao.IArticleGroupDao;
import com.lerx.article.dao.IArticleThreadDao;
import com.lerx.article.util.ThreadUtil;
import com.lerx.article.vo.ArticleGroup;
import com.lerx.article.vo.ArticleThread;
import com.lerx.qa.dao.IQaItemDao;
import com.lerx.qa.dao.IQaNavDao;
import com.lerx.qa.util.QaItemUtil;
import com.lerx.qa.vo.QaItem;
import com.lerx.qa.vo.QaNav;
import com.lerx.site.dao.ISiteInfoDao;
import com.lerx.site.vo.SiteInfo;
import com.lerx.style.site.dao.ISiteStyleDao;
import com.lerx.sys.service.SysUtilAction;
import com.lerx.sys.util.CfgFile;
import com.lerx.sys.util.FileUtil;
import com.lerx.sys.util.LogWrite;
import com.lerx.sys.util.SrvInf;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.vo.FileEl;
import com.lerx.sys.util.vo.Rs;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.LocalizedTextUtil;

public class StaticHtmlAction extends ActionSupport implements
		ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IArticleGroupDao articleGroupDaoImp;
	private IArticleThreadDao articleThreadDaoImp;
	private IQaNavDao qaNavDaoImp;
	private ISiteInfoDao siteInfDaoImp;
	private IQaItemDao qaItemDaoImp;
	private String ss;
	private int complete;
	private String showTitle;
	private HttpServletRequest request;
	private String arg1;
	private String arg2;
	private String arg3;
	private String actionName;
	private String requestIsNull;
	private String message;
	private long curStep;
	private long stepAll;
	private boolean allAg;

	public boolean isAllAg() {
		return allAg;
	}

	public void setAllAg(boolean allAg) {
		this.allAg = allAg;
	}

	public long getCurStep() {
		return curStep;
	}

	public void setCurStep(long curStep) {
		this.curStep = curStep;
	}

	public long getStepAll() {
		return stepAll;
	}

	public void setStepAll(long stepAll) {
		this.stepAll = stepAll;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSs() {
		return ss;
	}

	public void setSs(String ss) {
		this.ss = ss;
	}

	public int getComplete() {
		return complete;
	}

	public void setComplete(int complete) {
		this.complete = complete;
	}

	public String getArg1() {
		return arg1;
	}

	public void setArg1(String arg1) {
		this.arg1 = arg1;
	}

	public String getArg2() {
		return arg2;
	}

	public void setArg2(String arg2) {
		this.arg2 = arg2;
	}

	public String getArg3() {
		return arg3;
	}

	public void setArg3(String arg3) {
		this.arg3 = arg3;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getShowTitle() {
		return showTitle;
	}

	public void setShowTitle(String showTitle) {
		this.showTitle = showTitle;
	}

	public String getRequestIsNull() {
		return requestIsNull;
	}

	public void setRequestIsNull(String requestIsNull) {
		this.requestIsNull = requestIsNull;
	}

	public void setArticleGroupDaoImp(IArticleGroupDao articleGroupDaoImp) {
		this.articleGroupDaoImp = articleGroupDaoImp;
	}

	public void setArticleThreadDaoImp(IArticleThreadDao articleThreadDaoImp) {
		this.articleThreadDaoImp = articleThreadDaoImp;
	}

	public void setQaNavDaoImp(IQaNavDao qaNavDaoImp) {
		this.qaNavDaoImp = qaNavDaoImp;
	}

	public void setSiteInfDaoImp(ISiteInfoDao siteInfDaoImp) {
		this.siteInfDaoImp = siteInfDaoImp;
	}

	public void setQaItemDaoImp(IQaItemDao qaItemDaoImp) {
		this.qaItemDaoImp = qaItemDaoImp;
	}

	public String allQaItemCreate() throws InterruptedException{

		System.gc();
		if (request == null || request.getSession() == null
				|| request.getSession().getServletContext() == null) {
			this.requestIsNull = "yes";
			return ERROR;
		}
		this.requestIsNull = "no";
		String realRootPath = arg1;
		String tmod;

		String createStaticSafeStr = readRec("lerx.createStaticSafeStr").trim();
		if (ss != null && ss.trim().equals(createStaticSafeStr)) {
			this.arg3 = ss;
			if (ss == null || ss.trim().equals("")) {
				ss = arg3;
			}
			if (arg2 == null) {
				tmod = "0";
			} else {
				tmod = arg2;
			}
			QaItem qi;
			long step = 0, allStep = 0, aid;
			this.showTitle = readRec("lers.title.show.statcCreate");
			this.actionName = "createQaItemStatic.action";
			List<Long> list=qaItemDaoImp.findAllID();

			allStep = list.size();

			@SuppressWarnings("rawtypes")
			Iterator it = list.iterator();
			FileEl fe;
			String baseUrl = SrvInf.srvUrl(request, readRec("lerx.host.current"),
					Integer.valueOf(readRec("lerx.serverPort")));
			
			
			String trueFolder;
			if (realRootPath == null || realRootPath.trim().equals("")) {
				trueFolder = request.getSession().getServletContext()
						.getRealPath("/");
				realRootPath = trueFolder;
			} else {
				trueFolder = realRootPath;
			}

			this.arg1 = realRootPath;
			this.arg2 = tmod;
//			boolean show;
			while (it.hasNext()) {
				step++;
				
				aid = (Long) it.next();
				qi=qaItemDaoImp.findById(aid);
				this.message=qi.getTitle();
				if (qi.isOpen() && qi.getReplier()!=null){

					if (qi.getHtmlURLFile()==null || qi.getHtmlURLFile().trim().equals("") || qi.getHtmlUrlPath()==null || qi.getHtmlUrlPath().trim().equals("")){
						fe =QaItemUtil.fileNameFormat(this,qi,0);
						qi.setHtmlURLFile(fe.getName());
						qi.setHtmlUrlPath(fe.getPath());
//						qidb.setHtmlCreated(false);
					}
					String staticHTMLFile = qi.getHtmlURLFile();
					String folderUrl = qi.getHtmlUrlPath();
					if (File.separator.equals("/")) {
						trueFolder = request.getContextPath() + File.separator
								+ folderUrl;
					} else {
						trueFolder = request.getContextPath() + File.separator
								+ folderUrl;
						trueFolder = StringUtil.strReplace(folderUrl, "/", "\\");
					}
					trueFolder = request.getSession().getServletContext()
							.getRealPath(trueFolder);
					if (FileUtil.createHtml(baseUrl + "/qa.action?tid="
							+ qi.getId(), staticHTMLFile, trueFolder,
							readRec("lerx.charset"))) {
						qi.setHtmlCreated(true);
						qaItemDaoImp.modify(qi);
						
					} 
				
				}
				

				double r = ((double) step * 100) / (double) allStep;
				int percent = (int) r;
				this.complete = percent;
				this.curStep=step;
				this.stepAll=allStep;
				

			}

			return SUCCESS;

		} else {
			return ERROR;
		}

	
	}
	
	public String allArtCreate() throws InterruptedException {
		System.gc();
		if (request == null || request.getSession() == null
				|| request.getSession().getServletContext() == null) {
			this.requestIsNull = "yes";
			return ERROR;
		}
		this.requestIsNull = "no";
		String realRootPath = arg1;
		String tmod;

		String createStaticSafeStr = readRec("lerx.createStaticSafeStr").trim();
		if (ss != null && ss.trim().equals(createStaticSafeStr)) {
			this.arg3 = ss;
			if (ss == null || ss.trim().equals("")) {
				ss = arg3;
			}
			if (arg2 == null) {
				tmod = "0";
			} else {
				tmod = arg2;
			}

			long step = 0, allStep = 0, aid;
			ArticleThread at;
			this.showTitle = readRec("lers.title.show.statcCreate");
			String charset = readRec("lerx.charset");
			String articleViewPageFileName = readRec("lerx.articleViewPageFileName");
			this.actionName = "createArtStatic.action";

			Rs rs;
			if (tmod.trim().equals("1")) {
				rs = articleThreadDaoImp.findAll(0, 0);
			} else {
				rs = articleThreadDaoImp.findAll(0, 2);
			}

			allStep = rs.getCount();

			@SuppressWarnings("rawtypes")
			Iterator it = rs.getList().iterator();
			FileEl fe;
			String baseUrl = SrvInf.srvUrl(request, readRec("lerx.host.current"),
					Integer.valueOf(readRec("lerx.serverPort")));
			
			
			String trueFolder;
			if (realRootPath == null || realRootPath.trim().equals("")) {
				trueFolder = request.getSession().getServletContext()
						.getRealPath("/");
				realRootPath = trueFolder;
			} else {
				trueFolder = realRootPath;
			}

			this.arg1 = realRootPath;
			this.arg2 = tmod;
			ArticleGroup ag;
//			boolean show;
			while (it.hasNext()) {
				step++;

				aid = (Long) it.next();
				at = articleThreadDaoImp.findById(aid);
				this.message=at.getTitle();
				ag=at.getArticleGroup();
				boolean agIpCheck=false;
				if (ag!=null && ag.getHostsAllow()!=null && ag.getHostsAllow().trim().length()>7){
					agIpCheck=true;
				}
				if (ag!=null && ag.isShare() && !agIpCheck){
					fe = ThreadUtil.fileNameFormat(request, at,
							Integer.valueOf(tmod));
					if (!at.isLinkJump() && FileUtil.createHtml(
							baseUrl + "/" + articleViewPageFileName.trim()
									+ "?tid=" + at.getId(), fe.getName(),
							trueFolder + fe.getRealPath(), charset)) {
						at.setHtmlCreated(true);
						at.setHtmlURLFile(fe.getName());
						at.setHtmlUrlPath(fe.getPath());
						articleThreadDaoImp.modify(at);
					} 
				}
				

				double r = ((double) step * 100) / (double) allStep;
				int percent = (int) r;
				this.complete = percent;
				this.curStep=step;
				this.stepAll=allStep;
				

			}

			return SUCCESS;

		} else {
			return ERROR;
		}

	}

	public String create() throws InterruptedException {
		System.gc();
		String realRootPath = arg1;

		String realHtmlRootPath = arg2;

		if (ss == null || ss.trim().equals("")) {
			ss = arg3;
		}
		String safeStr = readRec("lerx.createStaticSafeStr");
		this.showTitle = readRec("lers.title.show.statcCreate");
		String staticHtmlRoot = readRec("lerx.htmlPath");
		
		String staticFileFolderOnRoot=readRec("lerx.staticFileFolderOnRoot");
		boolean staticOnRoot;
		
		if (staticFileFolderOnRoot!=null && staticFileFolderOnRoot.trim().equals("true")){
			staticOnRoot=true;
		}else{
			staticOnRoot=false;
		}
		if (staticOnRoot){
			staticHtmlRoot="";
		}else{
			staticHtmlRoot += "/";
		}
		
		
		String charset = readRec("lerx.charset");
		String staticHTMLFile = readRec("lerx.defaultHtml");
		this.actionName = "createStatic.action";
		
		if (ss != null && ss.trim().equals(safeStr)) {
			this.arg3 = ss;
			int step = 0, allStep = 0;
			SiteInfo site = siteInfDaoImp.query();

			List<ArticleGroup> gl = null;
			List<QaNav> ql = null;
			if (site.getStaticHtmlMode() == 2) {
				if (allAg){
//					System.out.println("------!!!!全部栏目处理");
					gl = articleGroupDaoImp.findAll();
				}else{
//					System.out.println("------!!!!只处理改变的栏目");
					gl = articleGroupDaoImp.findAllChanged();
				}
//				gl = articleGroupDaoImp.findAllArticleGroup();
				allStep = gl.size() + 1;
				ql = qaNavDaoImp.findAllNav(1);
				if (ql.size() > 0) {
					allStep += ql.size();
				}
			} else if (site.getStaticHtmlMode() == 1) {
				allStep = 1;
			} else {
				allStep = 0;
			}
			String baseUrl = SrvInf.srvUrl(request, readRec("lerx.host.current"),
					Integer.valueOf(readRec("lerx.serverPort")));
			String trueFolder, trueHtmlFolder;

			if (realRootPath == null || realRootPath.trim().equals("")) {
				trueFolder = request.getSession().getServletContext()
						.getRealPath("/");
				realRootPath = trueFolder;
			} else {
				trueFolder = realRootPath;
			}
			this.arg1 = realRootPath;

			if (realHtmlRootPath == null || realHtmlRootPath.trim().equals("")) {
				trueHtmlFolder = request.getSession().getServletContext()
						.getRealPath(staticHtmlRoot);
				realHtmlRootPath = trueHtmlFolder;
			} else {
				trueHtmlFolder = realHtmlRootPath;
			}
			this.arg2 = realHtmlRootPath;
			if (staticHTMLFile == null || staticHTMLFile.trim().equals("")
					|| staticHTMLFile.trim().equals("lerx.defaultHtml")) {
				staticHTMLFile = "index.html";
			} else {
				staticHTMLFile = staticHTMLFile.trim();
			}

			if (site.getStaticHtmlMode() == 1 || site.getStaticHtmlMode() == 2) {
				step++;
				if (FileUtil.createHtml(baseUrl + "/" + "index.action",
						staticHTMLFile, trueFolder, charset)) {
				} else {
					LogWrite.logWrite(request, "创建静态文件失败：" + trueFolder
							+ File.separator + staticHTMLFile);
				}

				double r = ((double) step * 100) / (double) allStep;
				int percent = (int) r;
				this.complete = percent;
				this.curStep=step;
				this.stepAll=allStep;
				this.message="index";
			}

			if (site.getStaticHtmlMode() == 2) {

				// 文章系统栏目静态
				String articleGroupPageFileName = readRec("lerx.articleGroupPageFileName");

				String htmlFolder;
				String siteFileFolder = readRec("lerx.staticSiteFileFolder");
				
				String staticSiteFileFolderOnNav=readRec("lerx.staticSiteFileFolderOnNav");
				String staticQaFileFolderOnNav=readRec("lerx.staticQaFileFolderOnNav");
				//上面这个参数是判别在生成栏目静态时，是在门户静态目录下建还是在htmlRoot下建
				boolean onSiteFileFolder,onQaFileFolder;
				if (staticSiteFileFolderOnNav.trim().equals("true")){
					onSiteFileFolder=true;
				}else{
					onSiteFileFolder=false;
				}
				
				if (staticQaFileFolderOnNav.trim().equals("true")){
					onQaFileFolder=true;
				}else{
					onQaFileFolder=false;
				}
				if (staticOnRoot){
					siteFileFolder = null;
				}
				if (siteFileFolder == null
						|| siteFileFolder.trim().equalsIgnoreCase("null") || !onSiteFileFolder) {
					siteFileFolder = "";
				} else {
					siteFileFolder += File.separator;
				}
				
				//
				

				for (ArticleGroup g : gl) {
					step++;
					this.message=g.getGroupName();
//					if (realHtmlRootPath.length()<2){	//不知道为什么会=1，先这样判断处理吧
//						realHtmlRootPath = request.getSession().getServletContext()
//						.getRealPath(staticHtmlRoot);	
//					}
					
					if (g!=null && !g.isRefuseStaticHtml()) {
						
						htmlFolder = g.getStaticHtmlFolder();
						if (htmlFolder == null || htmlFolder.trim().equals("")) {
							trueHtmlFolder = realHtmlRootPath + File.separator
									+ siteFileFolder + "c" + g.getId();
						} else {
							trueHtmlFolder = realHtmlRootPath + File.separator
									+ siteFileFolder + htmlFolder;
						}
						if (FileUtil.createHtml(
								baseUrl + "/" + articleGroupPageFileName
										+ "?gid=" + g.getId(), staticHTMLFile,
								trueHtmlFolder, charset)) {
							g.setChanged(false);
							articleGroupDaoImp.modify(g);
//							articleGroupDaoImp.changed(g);
						} else {
							LogWrite.logWrite(request, "创建静态文件失败：" + trueFolder
									+ File.separator + staticHTMLFile);
						}
					}
					double r = ((double) step * 100) / (double) allStep;
					int percent = (int) r;
					this.complete = percent;
					this.curStep=step;
					this.stepAll=allStep;

				}

				// 问答系统栏目静态

				String qaFileFolder = readRec("lerx.staticQaFileFolder");
				if (staticOnRoot){
					qaFileFolder = null;
				}
				if (qaFileFolder == null
						|| qaFileFolder.trim().equalsIgnoreCase("null") || !onQaFileFolder) {
					qaFileFolder = "";
				} else {
					qaFileFolder += File.separator;
				}
				
				for (QaNav q : ql) {
					
					step++;
					htmlFolder = q.getStaticHtmlFolder();
					this.message=q.getTitle();
					
					if (htmlFolder == null || htmlFolder.trim().equals("")) {
						trueHtmlFolder = realHtmlRootPath + File.separator
								+ qaFileFolder + "q" + q.getId();
					} else {
						trueHtmlFolder = realHtmlRootPath + File.separator
								+ qaFileFolder + htmlFolder;
					}

//					System.out.println("正在建立静态文件"+trueHtmlFolder);
					if (FileUtil.createHtml(
							baseUrl + "/qaNav.action?gid=" + q.getId(),
							staticHTMLFile, trueHtmlFolder, charset)) {
					} else {
						LogWrite.logWrite(request, "创建静态文件失败：" + trueFolder
								+ File.separator + staticHTMLFile);
					}
					double r = ((double) step * 100) / (double) allStep;
					int percent = (int) r;
					this.complete = percent;
					this.curStep=step;
					this.stepAll=allStep;
					

				}

			}

			return SUCCESS;
		} else {
//			System.out.println("----ss:"+ss);
//			System.out.println("----safeStr:"+safeStr);
			return ERROR;
		}

	}

	private String readRec(String key) {
		String value=LocalizedTextUtil.findDefaultText(key, new Locale("zh_CN"));
		if (value==null){
			String curPath=
			 request.getSession().getServletContext().getRealPath("");
			 curPath+=File.separator+"WEB-INF"+File.separator+"classes"+File.separator;
			 String resources=CfgFile.read(curPath+"struts.properties",
					 "struts.custom.i18n.resources").trim();
			 String[] files=resources.split(",");
			 for (int i=0;i<files.length;i++){
				 value=CfgFile.read(curPath+files[i]+"_zh_CN.properties",
						 key).trim();
				 if (value!=null){
					 break;
				 }
			 }
			 
				
		}
//		return LocalizedTextUtil.findDefaultText(key, new Locale("zh_CN"));
		
		 return value;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}

}
