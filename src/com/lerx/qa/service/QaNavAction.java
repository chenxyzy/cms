package com.lerx.qa.service;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.lerx.admin.util.AdminUtil;
import com.lerx.qa.dao.IQaNavDao;
import com.lerx.qa.vo.QaNav;
import com.lerx.sys.util.LogWrite;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class QaNavAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private QaNav qn;
	private int id;
	private int mod;
	private int parentID;
	private boolean state;
	private HttpServletRequest request;;
	private IQaNavDao qaNavDaoImp;
	private int sid;
	private int tid;
	
	public QaNav getQn() {
		return qn;
	}



	public void setQn(QaNav qn) {
		this.qn = qn;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}

	public int getSid() {
		return sid;
	}



	public void setSid(int sid) {
		this.sid = sid;
	}



	public int getTid() {
		return tid;
	}



	public void setTid(int tid) {
		this.tid = tid;
	}







	public int getParentID() {
		return parentID;
	}



	public void setParentID(int parentID) {
		this.parentID = parentID;
	}



	public boolean isState() {
		return state;
	}



	public void setState(boolean state) {
		this.state = state;
	}



	public int getMod() {
		return mod;
	}



	public void setMod(int mod) {
		this.mod = mod;
	}



	public void setQaNavDaoImp(IQaNavDao qaNavDaoImp) {
		this.qaNavDaoImp = qaNavDaoImp;
	}



	public String add(){
		if (checkAdmin()){
			if (qn.getTitle()==null || qn.getTitle().trim().equals("")){
				return INPUT;
			}
			if (qn.getParentNav().getId()==0){
				qn.setParentNav(null);
			}
			if (getText("lerx.qaLoginNeedDefault").trim().equalsIgnoreCase("true")){
				qn.setLoginNeed(true);
			}else{
				qn.setLoginNeed(false);
			}
			qn.setState(true);
			qn.setSendMail(false);
			qn.setRefuseStaticHtml(false);
			qaNavDaoImp.add(qn);
			LogWrite.logWrite(request, "增加问答系统分类："+qn.getTitle());
			return SUCCESS;
		}else{
			return INPUT;
		}
	}
	
	public String del(){
		if (checkAdmin()){
			if (id>0){
				if (!qaNavDaoImp.delById(id)){
					request.setAttribute("resultAltStr", getText("lerx.fail.del.exists.parent"));
				}else{
					LogWrite.logWrite(request, "删除问答系统分类： id号 - "+id);
				}
				
			}else{
				if (qn!=null){
					qaNavDaoImp.del(qn);
					LogWrite.logWrite(request, "删除问答系统分类："+qn.getTitle());
				}else{
					return INPUT;
				}
				
			}
			
			
			return SUCCESS;
		}else{
			return INPUT;
		}
	}
	
	public String modify(){
		if (checkAdmin()){
			if (qn.getStyle().getId()==0){
				qn.setStyle(null);
			}
			QaNav db=qaNavDaoImp.findById(qn.getId());
			qn.setDisplayOrder(db.getDisplayOrder());
			qaNavDaoImp.modify(qn);
			LogWrite.logWrite(request, "修改问答系统分类： "+qn.getTitle());
			return SUCCESS;
		}else{
			return INPUT;
		}
	}
	
	public String changeState(){
		if (checkAdmin()){
			qaNavDaoImp.setState(id, state);
			return SUCCESS;
		}else{
			return INPUT;
		}
	}
	
	public String findById(){
		ActionContext.getContext().getValueStack()
		.set("qn", qaNavDaoImp.findById(id));
		//request.setAttribute("qn", qaNavDaoImp.findById(id));
		return SUCCESS;
	}
	
	public String findAll(){
//		List<QaNav> list=qaNavDaoImp.findAllNav(mod);
		request.setAttribute("qaNavAll", qaNavDaoImp.findAllNav(mod));
		return SUCCESS;
	}
	
	public String findByParentId(){
		request.setAttribute("qaNavAll", qaNavDaoImp.findByParent(id, mod));
		return SUCCESS;
	}
	
	public String findAllClass(){
		request.setAttribute("qaClassAll", qaNavDaoImp.findAllClassNav(mod));
		return SUCCESS;
	}
	private boolean checkAdmin(){
		return AdminUtil.checkAdmin(this,getText("lerx.host.current"), request);
	}

	public String swap(){
		QaNav s=qaNavDaoImp.findById(sid);
		QaNav t=qaNavDaoImp.findById(tid);
		qaNavDaoImp.swapQaNav(s, t);
		LogWrite.logWrite(request, "后台修改--交换问答系统栏目sid号：<"+s.getId()+"> tid号：<"+t.getId()+"> 成功。 ");
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
		
	}
	
}
