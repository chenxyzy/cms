package com.lerx.admin.service;

import java.util.List;
import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import com.lerx.admin.dao.IAdminDao;
import com.lerx.admin.util.AdminUtil;
import com.lerx.admin.vo.Admin;
import com.lerx.site.dao.ISiteInfoDao;
import com.lerx.sys.util.IpUtil;
import com.lerx.sys.util.LogWrite;
import com.lerx.sys.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AdminAction extends ActionSupport implements ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private Admin admin;
	private String verifyCode;
	private String password;
	private List<Admin> list;
	private boolean state;
	private ISiteInfoDao siteInfDaoImp;
	private IAdminDao adminDaoImp;

	public void setAdminDaoImp(IAdminDao adminDaoImp) {
		this.adminDaoImp = adminDaoImp;
	}

	HttpServletRequest request;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
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

	public void setSiteInfDaoImp(ISiteInfoDao siteInfDaoImp) {
		this.siteInfDaoImp = siteInfDaoImp;
	}

	public String logout() {
		siteInfDaoImp.query();
		String safeAdminSessionStr = getText(
				"lerx.sessionPrefixOfAdminAuthentication").trim();
		safeAdminSessionStr = safeAdminSessionStr.replace("servername",
				getText("lerx.host.current"));
		// String randStr = (String) ActionContext.getContext().getSession()
		// .get(sessionStr);
		// ActionContext.getContext().getSession()
		// .put(safeAdminSessionStr, "");
		ActionContext.getContext().getSession().remove(safeAdminSessionStr);
		return SUCCESS;
	}

	public String login() throws Exception {
		siteInfDaoImp.query();
		String ip = IpUtil.getRealRemotIP(request);
		String from = "admin";
		String sessionStr = from + "_" +getText("lerx.host.current")+ "_" + ip
				+ "_random";
		String safeAdminSessionStr = getText(
				"lerx.sessionPrefixOfAdminAuthentication").trim();
		safeAdminSessionStr = safeAdminSessionStr.replace("servername",
				getText("lerx.host.current"));

		String randStr = (String) ActionContext.getContext().getSession()
				.get(sessionStr);

		String defaultAdminUsername = getText("lerx.default.admin.username")
				.trim().toLowerCase();
		String defaultAdminPassword = getText("lerx.default.admin.password")
				.trim().toLowerCase();

		// Log4jInit.init();
		// System.out.println("测试点");
		if (randStr == null || verifyCode == null
				|| !verifyCode.trim().equalsIgnoreCase(randStr.trim())) {
			this.addActionError(getText("lerx.err.verifyCode"));
			
			if (admin==null){
				LogWrite.logWrite(request, "info:非法后台访问");
			}else{
				LogWrite.logWrite(request, "info:管理员登录--" + "<失败> 验证码错误 user:"
						+ admin.getName() + " pws:" + admin.getPassword());
			}
			
			return LOGIN;
		}

		// 从数据库中判断
		admin.setName(admin.getName().trim().toLowerCase());
		boolean pwdMD5ToLowerCase;
		if (getText("lerx.pwdMD5ToLowerCase").trim().equalsIgnoreCase("true")){
			pwdMD5ToLowerCase=true;
		}else{
			pwdMD5ToLowerCase=false;
		}
		id = adminDaoImp.findAdminByNameAndPassword(admin.getName(),
				admin.getPassword(),pwdMD5ToLowerCase);
		if (id > 0) {
			ActionContext.getContext().getSession()
					.put(safeAdminSessionStr, "passed");
			admin = adminDaoImp.findAdminById(id);
			admin.setLastLoginDatetime(new Timestamp(System.currentTimeMillis()));
			admin.setLastLoginIP(IpUtil.getRealRemotIP(request));
			// admin.setPassword(password);
			adminDaoImp.modifyAdmin(admin);
			
			ActionContext.getContext().getSession()
			.put(safeAdminSessionStr+"_adminName", admin.getName());
			LogWrite.logWrite(request, "info:管理员登录--" + "<成功> from DB user:"
					+ admin.getName() + " pws:" + admin.getPassword());
			return SUCCESS;
		}

		// 从配置文件中取默认后台用户和密码
		if (admin.getName().trim().toLowerCase().equals(defaultAdminUsername)
				&& admin.getPassword().trim().toLowerCase()
						.equals(defaultAdminPassword)) {
			ActionContext.getContext().getSession()
					.put(safeAdminSessionStr, "passed");
			ActionContext.getContext().getSession()
			.put(safeAdminSessionStr+"_adminName", admin.getName());
			LogWrite.logWrite(request, "info:管理员登录--"
					+ "<成功> from properties user:" + admin.getName() + " pws:"
					+ admin.getPassword());

			return SUCCESS;
		} else {
			this.addActionError(getText("lerx.fail.auth"));

		}
		LogWrite.logWrite(request,
				"info:管理员登录--" + "<失败> user:" + admin.getName() + " pws:"
						+ admin.getPassword());

		return LOGIN;
	}

	public String queryAll() throws Exception {
		siteInfDaoImp.query();
		if (checkAdmin()) {
			list = adminDaoImp.findAllAdmin();
			ActionContext.getContext().getValueStack().set("adminAll", list);
			return SUCCESS;
		} else {
			return INPUT;
		}

	}

	public String add() throws Exception {
		siteInfDaoImp.query();
		boolean pwdMD5ToLowerCase;
		if (getText("lerx.pwdMD5ToLowerCase").trim().equalsIgnoreCase("true")){
			pwdMD5ToLowerCase=true;
		}else{
			pwdMD5ToLowerCase=false;
		}
		if (checkAdmin()) {
			admin.setName(admin.getName().trim().toLowerCase());
			if (adminDaoImp.findAdminByName(admin.getName())) {
				this.addActionError(getText("lerx.fail.exists.userName"));
				
				queryAll();
				return INPUT;
			} else {
				adminDaoImp.addAdmin(admin,pwdMD5ToLowerCase);
				LogWrite.logWrite(request, "增加管理员："+admin.getName());
				queryAll();
				return SUCCESS;
			}
		} else {
			return INPUT;
		}

	}

	public String del() throws Exception {
		siteInfDaoImp.query();
		if (checkAdmin()) {
			if (adminDaoImp.delAdminById(id)) {
				LogWrite.logWrite(request, "删除管理员：id号--"+id);
				queryAll();
				return SUCCESS;
			} else {
				this.addActionError(getText("lerx.err.db"));
				queryAll();
				return INPUT;
			}
		} else {
			return INPUT;
		}

	}

	public String changeState() throws Exception {
		siteInfDaoImp.query();
		if (checkAdmin()) {
			adminDaoImp.setState(id, state);
			LogWrite.logWrite(request, "改变管理员状态：id号--"+id);
			queryAll();
			return SUCCESS;
		} else {
			return INPUT;
		}

	}

	public String findByID() throws Exception {

		try {

			ActionContext.getContext().getValueStack()
					.set("admin", adminDaoImp.findAdminById(id));
			return SUCCESS;
		} catch (Exception e) {
			return ERROR;
		}
	}

	public String modify() throws Exception {
		siteInfDaoImp.query();
		if (checkAdmin()) {
			Admin iadmin = adminDaoImp.findAdminById(id);
			
			if (!password.trim().equals("")) {
				String passwordMd5;
				if (getText("lerx.pwdMD5ToLowerCase").trim().equalsIgnoreCase("true")){
					passwordMd5 = StringUtil.md5(
							StringUtil.md5(password).toLowerCase()
									.concat(iadmin.getSalt())).toLowerCase();
				}else{
					passwordMd5 = StringUtil.md5(
							StringUtil.md5(password)
									.concat(iadmin.getSalt()));
				}
				

				iadmin.setPassword(passwordMd5);

			}
			iadmin.setState(state);
			adminDaoImp.modifyAdmin(iadmin);
			LogWrite.logWrite(request, "修改管理员资料："+iadmin.getName());
			queryAll();
			return SUCCESS;
		} else {
			return INPUT;
		}

	}

	private boolean checkAdmin(){
		return AdminUtil.checkAdmin(this,getText("lerx.host.current"), request);
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}

}
