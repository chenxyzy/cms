package com.lerx.article.service;

import java.util.List;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.apache.struts2.interceptor.ServletRequestAware;
//import org.apache.struts2.interceptor.ServletResponseAware;

import com.lerx.article.dao.IArticleThreadDao;
import com.lerx.article.vo.ArticleThread;
import com.lerx.sys.util.SysUtil;
import com.opensymphony.xwork2.ActionSupport;

public class ArticleResetAction  extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long curStep;
	private long stepAll;
	private String actionName;
	private int complete;
	private String arg1;
	private String arg2;
	private String arg3;
	private String message;
	private String showTitle;
	private String ss;
	private boolean compel;
//	private HttpServletRequest request;
//	private HttpServletResponse response;
	private IArticleThreadDao articleThreadDaoImp;
	
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

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getShowTitle() {
		return showTitle;
	}

	public void setShowTitle(String showTitle) {
		this.showTitle = showTitle;
	}

	public String getSs() {
		return ss;
	}

	public void setSs(String ss) {
		this.ss = ss;
	}

	public boolean isCompel() {
		return compel;
	}

	public void setCompel(boolean compel) {
		this.compel = compel;
	}
	
	public void setArticleThreadDaoImp(IArticleThreadDao articleThreadDaoImp) {
		this.articleThreadDaoImp = articleThreadDaoImp;
	}

	public String reset() throws InterruptedException{
		this.actionName = "art_reset.action";
		this.showTitle="正在处理 ... !";
		this.showTitle="Spiders creeping ... !";
		if (ss==null || !ss.trim().equals(SysUtil.readRec(this, "lerx.createStaticSafeStr").trim())){
			message=SysUtil.readRec(this, "lerx.err.secStr.illegalOperation");
			return ERROR;
		}
		
		
//		ReportArgUtil  rau = new ReportArgUtil();
//		rau.setRealPath(realPath);
//		rau.setRootFolder(rootFolder);
		
		boolean chg;
		ArticleThread t;
		List<Long> list=articleThreadDaoImp.findAllID();
		long step=0;
		long allArtNum=list.size();
		for (long id:list){
			step++;
			t = articleThreadDaoImp.findById(id);
			if (t!=null){
				chg=false;
				if (t.getAddTime()!=null){
					chg=true;
					t.setAddTimeLong(t.getAddTime().getTime());
				}
				if (t.getLastEditTime()!=null){
					chg=true;
					t.setLastEditTimeLong(t.getLastEditTime().getTime());
				}
				if (t.getLastViewTime()!=null){
					chg=true;
					t.setLastViewTimeLong(t.getLastViewTime().getTime());
				}
				if (t.getPassingTime()!=null){
					chg=true;
					t.setPassingTimeLong(t.getPassingTime().getTime());
				}
				
				if (chg){
					articleThreadDaoImp.modify(t);
				}
				Thread.sleep(3000);
				this.message= " "+t.getTitle() + " ";
				double r = ((double) step * 100) / (double) allArtNum;
				int percent = (int) r;
				this.complete = percent;
				this.curStep=step;
				this.stepAll=allArtNum;
			}
			
		}
		
		
		return SUCCESS;
	}

//	@Override
//	public void setServletRequest(HttpServletRequest request) {
//		this.request=request;
//		
//	}
//
//	@Override
//	public void setServletResponse(HttpServletResponse response) {
//		this.response=response;
//	}

}
