package com.lerx.draw.service;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.lerx.admin.util.AdminUtil;
import com.lerx.draw.dao.IDrawDao;
import com.lerx.draw.vo.Draw;
import com.lerx.sys.util.LogWrite;
import com.lerx.sys.util.StringUtil;
import com.lerx.vote.dao.IVoteRecDao;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class DrawAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Draw draw;
	private int gid;
	private int mod;
	private String password;
	private boolean state;
	private IDrawDao drawDaoImp;
	private IVoteRecDao voteRecDaoImp;
	private HttpServletRequest request;;
	public Draw getDraw() {
		return draw;
	}
	public void setDraw(Draw draw) {
		this.draw = draw;
	}
	
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public int getMod() {
		return mod;
	}
	public void setMod(int mod) {
		this.mod = mod;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public void setDrawDaoImp(IDrawDao drawDaoImp) {
		this.drawDaoImp = drawDaoImp;
	}
	
	public void setVoteRecDaoImp(IVoteRecDao voteRecDaoImp) {
		this.voteRecDaoImp = voteRecDaoImp;
	}
	public String query(){
		request.setAttribute("drawAll", drawDaoImp.findAllDraw(mod));
		return SUCCESS;
	}
	
	public String add(){
		if (checkAdmin()){
			draw.setState(true);
			draw.setResultNum(0);
			drawDaoImp.add(draw);
			query();
			return SUCCESS;
		}else{
			query();
			return INPUT;
		}
		
	}
	public String modify(){
		if (checkAdmin()){
			if (draw.getPassword().trim().equals("0")){
				draw.setPassword(null);
			}else if (draw.getPassword().equals("~")){
				Draw drawdb=drawDaoImp.findById(draw.getId());
				draw.setPassword(drawdb.getPassword());
			}else{
				if (draw.getPassword()==null || draw.getPassword().equals("")){
					Draw d=drawDaoImp.findById(draw.getId());
					draw.setPassword(d.getPassword());
				}else{
					draw.setPassword(StringUtil.md5(draw.getPassword()));
					
				}
			}
			if (draw.getDs().getId()==0){
				draw.setDs(null);
			}
			drawDaoImp.modify(draw);
			query();
			return SUCCESS;
		}else{
			query();
			return INPUT;
		}
		
	}
	public String del(){
		if (checkAdmin()){
			
			drawDaoImp.delById(gid);
			query();
			return SUCCESS;
		}else{
			query();
			return INPUT;
		}
		
	}
	
	public String findById(){
		if (checkAdmin()){
			Draw draw=
			drawDaoImp.findById(gid);
			ActionContext.getContext().getValueStack()
			.set("draw", draw);
			return SUCCESS;
		}else{
			query();
			return INPUT;
		}
		
	}
	
	public String changeState() throws Exception {
		
		if (checkAdmin()) {
			drawDaoImp.setState(gid, state);
			LogWrite.logWrite(request, "改变抽奖状态：id号--"+gid);
			query();
			return SUCCESS;
		} else {
			return INPUT;
		}

	}
	
	public String draw(){
		boolean con=true;
		draw=drawDaoImp.findById(gid);
		if (draw.getPassword()!=null && !draw.getPassword().trim().equals("")){
			if (password==null || !StringUtil.md5(password).equals(draw.getPassword())){
				con=false;
				request.setAttribute("lerxSystemMsg", getText("lerx.err.password"));
			}
		}
		if (con){
			String recArr=voteRecDaoImp.draw(draw, draw.getResultNum());
			if (recArr==null){
				request.setAttribute("lerxSystemMsg", getText("lerx.fail.draw"));
				return INPUT;
			}
			draw.setResultRecStr(recArr);
			drawDaoImp.modify(draw);
			return SUCCESS;
		}else{
			
			return INPUT;
		}
		
		
	}
	public String clear(){
		if (checkAdmin()) {
			draw=drawDaoImp.findById(gid);
			draw.setResultRecStr(null);
			drawDaoImp.modify(draw);
			return SUCCESS;
		}else {
			return INPUT;
		}
		
	}
	private boolean checkAdmin(){
		return AdminUtil.checkAdmin(this,getText("lerx.host.current"), request);
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
		
	}
}
