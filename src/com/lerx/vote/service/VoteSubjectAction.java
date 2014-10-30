package com.lerx.vote.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.lerx.admin.util.AdminUtil;
import com.lerx.sys.util.LogWrite;
import com.lerx.sys.util.StringUtil;
import com.lerx.vote.dao.IVoteItemDao;
import com.lerx.vote.dao.IVoteRecDao;
import com.lerx.vote.dao.IVoteSubjectDao;
import com.lerx.vote.util.VoteSubjectUtil;
import com.lerx.vote.vo.VoteItem;
import com.lerx.vote.vo.VoteSubject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class VoteSubjectAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private VoteSubject vs;
	private int id;
	private int sid;
	private int mod;
	private HttpServletRequest request;
	private IVoteSubjectDao voteSubjectDaoImp;
	private IVoteRecDao voteRecDaoImp;
	private IVoteItemDao voteItemDaoImp;
	private String newTitle;
	private boolean itemsCopy;
	
	
	public String getNewTitle() {
		return newTitle;
	}
	public void setNewTitle(String newTitle) {
		this.newTitle = newTitle;
	}
	public boolean isItemsCopy() {
		return itemsCopy;
	}
	public void setItemsCopy(boolean itemsCopy) {
		this.itemsCopy = itemsCopy;
	}
	public VoteSubject getVs() {
		return vs;
	}
	public void setVs(VoteSubject vs) {
		this.vs = vs;
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
	public int getMod() {
		return mod;
	}
	public void setMod(int mod) {
		this.mod = mod;
	}
	public void setVoteSubjectDaoImp(IVoteSubjectDao voteSubjectDaoImp) {
		this.voteSubjectDaoImp = voteSubjectDaoImp;
	}
	
	public void setVoteRecDaoImp(IVoteRecDao voteRecDaoImp) {
		this.voteRecDaoImp = voteRecDaoImp;
	}
	public void setVoteItemDaoImp(IVoteItemDao voteItemDaoImp) {
		this.voteItemDaoImp = voteItemDaoImp;
	}
	public String add(){
		if (checkAdmin()) {
			if (vs.getTitle()==null || vs.getTitle().trim().equals("")){
				findAll();
				return INPUT;
			}
			vs=VoteSubjectUtil.prosTrim(vs);
			if (voteSubjectDaoImp.findByName(vs.getTitle())) {
				this.addActionError(getText("lerx.err.exists.name"));
				
				findAll();
				return INPUT;
			} else {
				vs.setSecStr(StringUtil.md5(StringUtil.uuidStr()));
				vs.setState(true);
				vs.setNetJoinAutoPassed(false);
				
				vs.setVotePassword("");
				voteSubjectDaoImp.add(vs);
				LogWrite.logWrite(request, "增加投票项目："+vs.getTitle());
				findAll();
				return SUCCESS;
			}
		} else {
			return INPUT;
		}
		
	}
	
	public String copy(){
		VoteSubject vs=voteSubjectDaoImp.findById(sid);
		if (vs!=null){
			VoteSubject nvs=new VoteSubject();
			nvs.setFullUpperConstraint(vs.isFullUpperConstraint());
			nvs.setHoursAtIpRule(vs.getHoursAtIpRule());
			nvs.setHoursAtMachineRule(vs.getHoursAtMachineRule());
			nvs.setIdentityRule(vs.isIdentityRule());
			nvs.setIpArea(vs.getIpArea());
			nvs.setMesAutoPassed(vs.isMesAutoPassed());
			nvs.setNetJoin(vs.isNetJoin());
			nvs.setNetJoinAutoPassed(vs.isNetJoinAutoPassed());
			nvs.setNetJoinMustBeMember(vs.isNetJoinMustBeMember());
			nvs.setOpenResult(vs.isOpenResult());
			nvs.setOrderType(vs.getOrderType());
			nvs.setPercent(vs.getPercent());
			nvs.setPhoneCodeRule(vs.isPhoneCodeRule());
			nvs.setPosterNameCCUChk(vs.isPosterNameCCUChk());
			nvs.setState(vs.isState());
			nvs.setStyle(vs.getStyle());
			nvs.setUpperLimit(vs.getUpperLimit());
			nvs.setUseVerifyCode(vs.isUseVerifyCode());
			nvs.setTitle(newTitle);
			int nid=voteSubjectDaoImp.add(nvs);
			nvs=voteSubjectDaoImp.findById(nid);
			if (itemsCopy && nvs!=null){
				List<Long> list=voteItemDaoImp.queryAll(0, vs.getId(), 0);
				VoteItem vi;
				VoteItem nvi;
				for (Long iid : list) {
					vi=voteItemDaoImp.findById(iid);
					nvi=new VoteItem();
					nvi.setAddTime(vi.getAddTime());
					nvi.setAddIp(vi.getAddIp());
					nvi.setBody(vi.getBody());
					nvi.setRecNum(0);
					nvi.setState(vi.isState());
					nvi.setSub(nvs);
					nvi.setTitle(vi.getTitle());
					nvi.setUnicodeOrder(vi.getUnicodeOrder());
					nvi.setUser(vi.getUser());
					nvi.setItem1(vi.getItem1());
					nvi.setItem2(vi.getItem2());
					nvi.setItem3(vi.getItem3());
					nvi.setItem4(vi.getItem4());
					nvi.setItem5(vi.getItem5());
					nvi.setItem6(vi.getItem6());
					nvi.setItem7(vi.getItem7());
					nvi.setItem8(vi.getItem8());
					nvi.setItem9(vi.getItem9());
					nvi.setItem10(vi.getItem10());
					nvi.setItem11(vi.getItem11());
					nvi.setItem12(vi.getItem12());
					nvi.setItem13(vi.getItem13());
					nvi.setItem14(vi.getItem14());
					nvi.setItem15(vi.getItem15());
					nvi.setItem16(vi.getItem16());
					nvi.setItem17(vi.getItem17());
					nvi.setItem18(vi.getItem18());
					nvi.setItem19(vi.getItem19());
					nvi.setItem20(vi.getItem20());
					voteItemDaoImp.add(nvi);
				}
				findAll();
				return SUCCESS;
			}else{
				findAll();
				return INPUT;
			}
		}else{
			findAll();
			return INPUT;
		}
	}
	
	public String findAll(){
		if (checkAdmin()) {
//			System.out.println("mod:"+mod);
			List<VoteSubject> list;
			list = voteSubjectDaoImp.findAll(mod);
			ActionContext.getContext().getValueStack().set("vsAll", list);
			request.setAttribute("vsAll", list);
//			System.out.println("gggggg");
			return SUCCESS;
		} else {
			return INPUT;
		}
	}
	
	public String modify(){
		
		if (checkAdmin()) {
			vs=VoteSubjectUtil.prosTrim(vs);
			if (vs.getVotePassword().equals("0")){
				vs.setVotePassword("");
				vs.setSalt("");
			}else if (vs.getVotePassword().equals("~")){
				VoteSubject vsdb=voteSubjectDaoImp.findById(vs.getId());
				vs.setVotePassword(vsdb.getVotePassword());
				vs.setSalt(vsdb.getSalt());
			}else{
				boolean pwdMD5ToLowerCase;
				if (getText("lerx.pwdMD5ToLowerCase").trim().equalsIgnoreCase("true")){
					pwdMD5ToLowerCase=true;
				}else{
					pwdMD5ToLowerCase=false;
				}
				String salt=StringUtil.randomString(6);
				String passwordMd5;
				if (pwdMD5ToLowerCase){
					passwordMd5=StringUtil.md5(StringUtil.md5(vs.getVotePassword()).toLowerCase().concat(salt)).toLowerCase();
				}else{
					passwordMd5=StringUtil.md5(StringUtil.md5(vs.getVotePassword()).concat(salt));
				}
				vs.setSalt(salt);
				vs.setVotePassword(passwordMd5);
			}
			if (vs.getStyle().getId()==0){
				vs.setStyle(null);
			}
			vs.setSecStr(StringUtil.md5(StringUtil.uuidStr()));
			voteSubjectDaoImp.modify(vs);
			findAll();
			return SUCCESS;
		} else {
			findAll();
			return INPUT;
		}
	}
	
	public String findById(){
		findSubById();
		return SUCCESS;
	}
	
	public void findSubById(){
		VoteSubject vs=voteSubjectDaoImp.findById(id);
		ActionContext.getContext().getValueStack()
		.set("vs", vs);
		request.setAttribute("curVs", vs);
	}
	
	public String del(){
		if (checkAdmin()) {
			voteSubjectDaoImp.delById(id);
			findAll();
			return SUCCESS;
		}else {
			findAll();
			return INPUT;
		}
		
	}
	public String clear(){
		if (checkAdmin()) {
			VoteSubject vs=voteSubjectDaoImp.findById(id);
			if (vs !=null){
				voteRecDaoImp.delBySubId(vs.getId());
				voteItemDaoImp.clearRecBySubId(vs.getId());
			}
			findAll();
			return SUCCESS;
		}else {
			findAll();
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
