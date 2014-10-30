package com.lerx.sys.service;

import java.io.ByteArrayInputStream;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.lerx.sys.util.IpUtil;
import com.lerx.sys.util.RandomNumUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class RandomNumAction extends ActionSupport implements ServletRequestAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private RandomNumUtil randomNum;
	private ByteArrayInputStream inputStream;
	HttpServletRequest request;
	private String ip;
	private String from;
	private String sessionStr;
	private int mode;
	
	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}
	
	public ByteArrayInputStream getInputStream() {
		return inputStream;
	}
	public void setInputStream(ByteArrayInputStream inputStream) {
		this.inputStream = inputStream;
	}
	public RandomNumUtil getRandomNum() {
		return randomNum;
	}
	public void setRandomNum(RandomNumUtil randomNum) {
		this.randomNum = randomNum;
	}
	
	public String execute() throws Exception{
		int m;
		if (mode==0){
			if (getText("lerx.verificationCodeMode").trim().equals("2")){
				m=2;
			}else{
				m=1;
			}
		}else{
			m=mode;
		}
		
		randomNum = new RandomNumUtil(60,18,4,18,m);
		this.setInputStream(randomNum.getInputStream());
		ip=IpUtil.getRealRemotIP(request);
		from=request.getParameter("from");
		
		if (from==null){
			from="";
		}else{
			from=from.trim().toLowerCase();
		}
			
		
		if (from==null){
			from="";
		}
		
		//System.out.println(randomNum.getRandomCode());
		sessionStr=from+"_" +getText("lerx.host.current")+ "_"+ip+"_random";
		ActionContext.getContext().getSession().put(sessionStr, randomNum.getRandomCode());
//		System.out.println("---:"+randomNum.getRandomCode());
		//System.out.println(sessionStr);
		return SUCCESS;
	}

	public String goRegister(){
		return "success";
	}
	
	
}
