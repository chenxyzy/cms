package com.lerx.user.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.lerx.admin.util.AdminUtil;
import com.lerx.sys.util.CdmUtil;
import com.lerx.sys.util.CookieUtil;
import com.lerx.sys.util.vo.CookieDoModel;
import com.lerx.sys.util.vo.UserCookie;
import com.lerx.user.dao.IInterconnectionDao;
import com.lerx.user.dao.IPasserDao;
import com.lerx.user.dao.IUserDao;
import com.lerx.user.vo.Passer;
import com.lerx.user.vo.PasserLockUtilVo;
import com.lerx.user.vo.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class PasserAction extends ActionSupport implements ServletRequestAware,
		ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Passer passer;
	private boolean state;
	private IPasserDao passerDaoImp;
	private IUserDao userDaoImp;
	private IInterconnectionDao interconnectionDaoImp;
	private int gid;
	private User nu;
	private PasserLockUtilVo pluv;
	

	public PasserLockUtilVo getPluv() {
		return pluv;
	}

	public void setPluv(PasserLockUtilVo pluv) {
		this.pluv = pluv;
	}

	public void setInterconnectionDaoImp(IInterconnectionDao interconnectionDaoImp) {
		this.interconnectionDaoImp = interconnectionDaoImp;
	}

	public int getGid() {
		return gid;
	}

	public void setGid(int gid) {
		this.gid = gid;
	}

	public void setPasserDaoImp(IPasserDao passerDaoImp) {
		this.passerDaoImp = passerDaoImp;
	}

	public void setUserDaoImp(IUserDao userDaoImp) {
		this.userDaoImp = userDaoImp;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public Passer getPasser() {
		return passer;
	}

	public void setPasser(Passer passer) {
		this.passer = passer;
	}

	public User getNu() {
		return nu;
	}

	public void setNu(User nu) {
		this.nu = nu;
	}
	
	/*
	 * 批量锁定
	 */
	public String lockBatch(){
		if (checkAdmin()) {
			passerDaoImp.butchLock(pluv);
			
		}
		queryAll();
		return SUCCESS;
	}

	public String add() {
		if (checkAdmin()) {
			if (passerDaoImp.find(passer.getUser()) == null) {
				passer.setState(true);
				passerDaoImp.add(passer);
			} else {
				this.addActionError(getText("lerx.err.duplicate"));

			}

			queryAll();
		}
		return SUCCESS;
	}

	public String del() {
		if (checkAdmin()) {
			passerDaoImp.delById(passer.getId());
			queryAll();
		}
		return SUCCESS;
	}

	public String modify() {
		if (checkAdmin()) {
			passerDaoImp.modify(passer);
			queryAll();
		}
		return SUCCESS;
	}
	
	public String modifyByUser(){
		
		Passer pdb=passerDaoImp.findById(passer.getId());
		if (pdb!=null){
			
			pdb.setQuestion1(passer.getQuestion1());
			pdb.setQuestion2(passer.getQuestion2());
			pdb.setAnswer1(passer.getAnswer1());
			pdb.setAnswer2(passer.getAnswer2());
			if (!pdb.isLockPasserInf1()){
				pdb.setPasserInf1(passer.getPasserInf1());
			}
			if (!pdb.isLockPasserInf2()){
				pdb.setPasserInf2(passer.getPasserInf2());
			}
			if (!pdb.isLockPriTag1()){
				pdb.setQuestionForPriTag1(passer.getQuestionForPriTag1());
				pdb.setPriTag1(passer.getPriTag1());
			}
			if (!pdb.isLockPriTag2()){
				pdb.setQuestionForPriTag2(passer.getQuestionForPriTag2());
				pdb.setPriTag2(passer.getPriTag2());
			}
			passerDaoImp.modify(pdb);
		}
		
		return SUCCESS;
	}

	public String changeState() {
		if (checkAdmin()) {
			passer = passerDaoImp.findById(passer.getId());
			passer.setState(state);
			passerDaoImp.modify(passer);
			queryAll();
		}

		return SUCCESS;
	}

	public String query() {
		if (checkAdmin()) {
			queryAll();

		}
		return SUCCESS;
	}

	public String queryByGid() {
		List<Passer> list = passerDaoImp.queryByUG(gid);

		ActionContext.getContext().getValueStack().set("passerAll", list);
		return SUCCESS;
	}

	public String queryByID() {
		if (checkAdmin()) {
			passer = passerDaoImp.findById(passer.getId());
			ActionContext.getContext().getValueStack().set("passer", passer);

		}

		return SUCCESS;
	}

	private void queryAll() {
		List<Passer> list = passerDaoImp.queryByUG(0);

		ActionContext.getContext().getValueStack().set("passerAll", list);
		request.setAttribute("passerAll", list);
		if (nu!=null){
			ActionContext.getContext().getValueStack().set("nuID", list);
			request.setAttribute("nuID", nu.getId());
		}
		
	}
	
	public String send(){
		passer=passerDaoImp.findById(passer.getId());
		User u = userDaoImp.findUserById(nu.getId());
		boolean con=true;
		
		if (passer.getAnswer1()==null || passer.getAnswer2()==null){
			con=false;
		}else{
			
		}
		
		if (con){
			ActionContext.getContext().getValueStack().set("passer", passer);
			ActionContext.getContext().getValueStack().set("user", u);
			return SUCCESS;
		}else{
			return ERROR;
		}
		
		
	}
	
	public void findCur() throws IOException{
		UserCookie uc;
		CookieDoModel cdm;
		cdm =CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
		uc = CookieUtil.query(cdm);
		if (uc!=null){
			Passer passer=passerDaoImp.find(userDaoImp.findUserById(uc.getUserId()));
			if (passer!=null){
				request.setAttribute("passer", passer);
			}
		}
		
	}

	private boolean checkAdmin() {
		return AdminUtil
				.checkAdmin(this, getText("lerx.host.current"), request);
	}
	
	

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {

		this.request = request;

	}

}
