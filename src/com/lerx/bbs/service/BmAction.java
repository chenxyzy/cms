package com.lerx.bbs.service;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.lerx.bbs.dao.IBbsBMDao;
import com.lerx.bbs.dao.IBbsForumDao;
import com.lerx.bbs.vo.BM;
import com.lerx.bbs.vo.BbsForum;
import com.lerx.user.dao.IUserDao;
import com.lerx.user.vo.User;
import com.opensymphony.xwork2.ActionSupport;

public class BmAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private IBbsBMDao bbsBMDaoImp;
	private IBbsForumDao bbsForumDaoImp;
	private IUserDao userDaoImp;
	private int fid;
	private String username;
	private long uid;
	private long bid;
	
	
	public long getBid() {
		return bid;
	}
	public void setBid(long bid) {
		this.bid = bid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public void setBbsForumDaoImp(IBbsForumDao bbsForumDaoImp) {
		this.bbsForumDaoImp = bbsForumDaoImp;
	}
	public void setBbsBMDaoImp(IBbsBMDao bbsBMDaoImp) {
		this.bbsBMDaoImp = bbsBMDaoImp;
	}

	public void setUserDaoImp(IUserDao userDaoImp) {
		this.userDaoImp = userDaoImp;
	}
	public int getFid() {
		return fid;
	}


	public void setFid(int fid) {
		this.fid = fid;
	}
	
	public void queryByFid(){
		request.setAttribute("bmAll", bbsBMDaoImp.queryByFid(fid));
	}
	
	public void query(){
		request.setAttribute("fid", fid);
		request.setAttribute("forumName", bbsForumDaoImp.findBbsForumById(fid).getForumName());
		request.setAttribute("bmAll", bbsBMDaoImp.queryByFid(fid));
	}
	
	public String del(){
		bbsBMDaoImp.delById(bid);
		return SUCCESS;
	}
	
	public String add(){
		User user=null;
		
		if (uid>0){
			 user=userDaoImp.findUserById(uid);
		}
		
		if ((user==null) && (username!=null && !username.trim().equals(""))){
			user=userDaoImp.findUserByName(username.trim());
		}
		BbsForum bf = bbsForumDaoImp.findBbsForumById(fid);
		if (user!=null && bf!=null){
			
			BM bm=new BM();
			bm.setUser(user);
			bm.setBf(bf);
			if (bbsBMDaoImp.checkPower(user, bf)){
				this.addActionError(getText("lerx.err.exists.power"));
//				System.out.println("已存在！");
			}else{
				bbsBMDaoImp.add(bm);
			}
		}
		
		return SUCCESS;
	}


	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
		// TODO Auto-generated method stub
		
	}

}
