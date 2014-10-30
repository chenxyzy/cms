package com.lerx.integral.rule.service;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.lerx.admin.util.AdminUtil;
import com.lerx.integral.rule.dao.IIntegralRuleDao;
import com.lerx.integral.rule.vo.IntegralRule;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class IntegralRuleService extends ActionSupport  implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IntegralRule ir;
	private int id;
	private int localPostion;
	private boolean state;
	private IIntegralRuleDao integralRuleDaoImp;
	
	private HttpServletRequest request;
	
	public IntegralRule getIr() {
		return ir;
	}
	public void setIr(IntegralRule ir) {
		this.ir = ir;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getLocalPostion() {
		return localPostion;
	}
	public void setLocalPostion(int localPostion) {
		this.localPostion = localPostion;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public void setIntegralRuleDaoImp(IIntegralRuleDao integralRuleDaoImp) {
		this.integralRuleDaoImp = integralRuleDaoImp;
	}
	
	//增加
	public String add(){
		
//		System.out.println("11111");
		if (checkAdmin()){
			ir.setLocalPostion(localPostion);
			ir.setCreateTime(new Timestamp(System.currentTimeMillis()));
			integralRuleDaoImp.add(ir);
//			System.out.println("asdfasdf");
			queryAll();
			return SUCCESS;
		}else{
			System.out.println("22222");
			queryAll();
			return INPUT;
		}
		
	}
	
	
	public String del(){
		if (checkAdmin()){
			integralRuleDaoImp.deyById(id);
			queryAll();
			return SUCCESS;
		}else{
			queryAll();
			return INPUT;
		}
		
	}
	
	public String findById(){
		if (checkAdmin()){
			IntegralRule ir=integralRuleDaoImp.findById(id);
			ActionContext.getContext().getValueStack().set("localPostion", localPostion);
			request.setAttribute("localPostion",localPostion);
			ActionContext.getContext().getValueStack().set("ir", ir);
			request.setAttribute("ir",ir);
			return SUCCESS;
		}else{
			queryAll();
			return INPUT;
		}
	}
	
	public String modify(){
		if (checkAdmin()){
			
			integralRuleDaoImp.modify(ir);
			queryAll();
			return SUCCESS;
		}else{
			queryAll();
			return INPUT;
		}
	}
	
	public String changeState(){
		if (checkAdmin()){
//			System.out.println("asdfad");
			integralRuleDaoImp.changeState(id, state,localPostion);
			queryAll();
			return SUCCESS;
		}else{
			queryAll();
			return INPUT;
		}
	}
	
	
	private void queryAll(){
		List<IntegralRule> list=integralRuleDaoImp.query(localPostion);
		ActionContext.getContext().getValueStack().set("localPostion", localPostion);
		request.setAttribute("localPostion",localPostion);
		ActionContext.getContext().getValueStack().set("integralRuleAll", list);
		request.setAttribute("integralRuleAll",list);
	}
	
	public String query(){
		if (checkAdmin()){
			queryAll();
		}
		return SUCCESS;
	}
	
	private boolean checkAdmin(){
		
		return AdminUtil.checkAdmin(this,getText("lerx.host.current"), request);
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
		// TODO Auto-generated method stub
		
	}
	

}
