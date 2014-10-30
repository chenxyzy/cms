package com.lerx.vote.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.lerx.admin.util.AdminUtil;
import com.lerx.site.dao.ISiteInfoDao;
import com.lerx.style.site.dao.ISiteStyleDao;
import com.lerx.style.site.vo.SiteStyle;
import com.lerx.style.vote.dao.IVoteStyleDao;
import com.lerx.style.vote.vo.VoteStyle;
import com.lerx.sys.util.ActionPageUtil;
import com.lerx.sys.util.ArrayUtil;
import com.lerx.sys.util.CdmUtil;
import com.lerx.sys.util.CookieUtil;
import com.lerx.sys.util.IpUtil;
import com.lerx.sys.util.SecCheck;
import com.lerx.sys.util.SrvInf;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.SysUtil;
import com.lerx.sys.util.vo.ActionPage;
import com.lerx.sys.util.vo.CookieDoModel;
import com.lerx.sys.util.vo.UserCookie;
import com.lerx.unicode.dao.ChineseCharacterUnicodeDao;
import com.lerx.user.dao.IInterconnectionDao;
import com.lerx.user.dao.IUserDao;
import com.lerx.user.vo.User;
import com.lerx.vote.dao.IVoteItemDao;
import com.lerx.vote.dao.IVoteRecDao;
import com.lerx.vote.dao.IVoteSubjectDao;
import com.lerx.vote.util.VoteRecUtil;
import com.lerx.vote.vo.VoteItem;
import com.lerx.vote.vo.VoteRec;
import com.lerx.vote.vo.VoteSubject;
import com.lerx.web.util.camp.ResultPage;
import com.lerx.web.vo.ResultEl;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class VoteItemAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VoteItem vi;
	private int subId;
	private long id;
	private int mod;
	private boolean state;
	private UserCookie uc;
	private CookieDoModel cdm;
	private String refererUrl;
	private String workingUrl;
	private ActionPage ap;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private ISiteStyleDao siteStyleDaoImp;
	private IVoteStyleDao voteStyleDaoImp;
	private IVoteItemDao voteItemDaoImp;
	private ChineseCharacterUnicodeDao chineseCharacterUnicodeDaoImp;
	private IVoteSubjectDao voteSubjectDaoImp;
	private IVoteRecDao voteRecDaoImp;
	private IUserDao userDaoImp;
	private IInterconnectionDao interconnectionDaoImp;
	private String secStr;
	private String randKey;
	private String verifyCode;
	private String password;
//	private String jsBuildKey;

	private Long[] ck;
	private VoteRec vr;
	private String key;
	
	
	
//	public String getJsBuildKey() {
//		return jsBuildKey;
//	}
//
//	public void setJsBuildKey(String jsBuildKey) {
//		this.jsBuildKey = jsBuildKey;
//	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public VoteRec getVr() {
		return vr;
	}

	public void setVr(VoteRec vr) {
		this.vr = vr;
	}

	public VoteItem getVi() {
		return vi;
	}

	public void setVi(VoteItem vi) {
		this.vi = vi;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getSubId() {
		return subId;
	}

	public void setSubId(int subId) {
		this.subId = subId;
	}

	public int getMod() {
		return mod;
	}

	public void setMod(int mod) {
		this.mod = mod;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public String getSecStr() {
		return secStr;
	}

	public void setSecStr(String secStr) {
		this.secStr = secStr;
	}

	public String getRandKey() {
		return randKey;
	}

	public void setRandKey(String randKey) {
		this.randKey = randKey;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public Long[] getCk() {
		return ck;
	}

	public void setCk(Long[] ck) {
		this.ck = ck;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRefererUrl() {
		return refererUrl;
	}

	public void setRefererUrl(String refererUrl) {
		this.refererUrl = refererUrl;
	}

	public String getWorkingUrl() {
		return workingUrl;
	}

	public void setWorkingUrl(String workingUrl) {
		this.workingUrl = workingUrl;
	}

	public void setSiteInfDaoImp(ISiteInfoDao siteInfDaoImp) {
	}

	public void setSiteStyleDaoImp(ISiteStyleDao siteStyleDaoImp) {
		this.siteStyleDaoImp = siteStyleDaoImp;
	}

	public void setVoteStyleDaoImp(IVoteStyleDao voteStyleDaoImp) {
		this.voteStyleDaoImp = voteStyleDaoImp;
	}

	public void setVoteItemDaoImp(IVoteItemDao voteItemDaoImp) {
		this.voteItemDaoImp = voteItemDaoImp;
	}

	public void setChineseCharacterUnicodeDaoImp(
			ChineseCharacterUnicodeDao chineseCharacterUnicodeDaoImp) {
		this.chineseCharacterUnicodeDaoImp = chineseCharacterUnicodeDaoImp;
	}

	public void setVoteSubjectDaoImp(IVoteSubjectDao voteSubjectDaoImp) {
		this.voteSubjectDaoImp = voteSubjectDaoImp;
	}

	public void setUserDaoImp(IUserDao userDaoImp) {
		this.userDaoImp = userDaoImp;
	}

	public void setInterconnectionDaoImp(IInterconnectionDao interconnectionDaoImp) {
		this.interconnectionDaoImp = interconnectionDaoImp;
	}

	public void setVoteRecDaoImp(IVoteRecDao voteRecDaoImp) {
		this.voteRecDaoImp = voteRecDaoImp;
	}

	public String sign() throws IOException {
		boolean con = true,findSiteRP=false;
		String resultPageCode;
		VoteSubject vs = null;
		VoteStyle curVoteStyle=null;
		refererInit();
		
		if (subId > 0) {
			vs = voteSubjectDaoImp.findById(subId);
			curVoteStyle=vs.getStyle();
			if (curVoteStyle==null){
				curVoteStyle=voteStyleDaoImp.findDefault();
			}
			if (curVoteStyle==null || curVoteStyle.getResultPageCode()==null || curVoteStyle.getResultPageCode().trim().equals("")){
				findSiteRP=true;
			}
		}else{
			findSiteRP=true;
		}
		if (findSiteRP){
			SiteStyle curStyle=siteStyleDaoImp.findDef();
			resultPageCode = curStyle.getResultPageCode();
		}else{
			resultPageCode=curVoteStyle.getResultPageCode();
		}
		
		
		ResultEl re = reInit(refererUrl, 0, resultPageCode);
		if (vs == null) { // 没有相应的投票，参数错误
			re.setMes(getText("lerx.err.parameter"));
			con = false;
		} else {
			
			vi.setSub(vs);
			Long curTime = System.currentTimeMillis();
			Long startTime;
			Long endTime;
			if (vs.getSignStartTime() == null) {
				startTime = null;
			} else {
				startTime = vs.getSignStartTime().getTime();
			}
			if (vs.getSignEndTime() == null) {
				endTime = null;
			} else {
				endTime = vs.getSignEndTime().getTime();
			}

			if (startTime != null && curTime < startTime) {
				con = false;
			}

			if (endTime != null && curTime > endTime) {
				con = false;
			}

			if (!vs.isNetJoin() || !con) { // 不允许网上报名...报名时间过期处理
			// System.out.println("不允许网上报名...报名时间过期处理");
				re.setMes(getText("lerx.fail.vote.join.forbidden"));
				con = false;
			} else {
				cdm =CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
				uc = CookieUtil.query(cdm);
				if (vs.isNetJoinMustBeMember() && uc == null) {
					con = false;
					re.setMes(getText("lerx.fail.auth"));
				}
			}

		}

		if (con) {
			if (uc != null) {
				User u = userDaoImp.findUserById(uc.getUserId());
				if (u != null) {
					vi.setUser(u);
				}
			}

			String curIP = IpUtil.getRealRemotIP(request).trim();
			vi.setAddIp(curIP);
			vi.setAddTime(new Timestamp(System.currentTimeMillis()));
			vi.setTitle(vi.getTitle().trim());
			if (vs.isNetJoinAutoPassed()) {
				vi.setState(true);
			} else {
				vi.setState(false);
			}

			String title = vi.getTitle();

			if (title.length() > 50) {
				title = title.substring(0, 50);
			}
			int len = title.length();
			char charIndex;
			String order = "";
			for (int i = 0; i < len; i++) {

				charIndex = vi.getTitle().charAt(i);

				int ord = chineseCharacterUnicodeDaoImp
						.findOrderByChar(charIndex);
				String tmp = StringUtil.covIntToStr(ord, 5);

				order += tmp;
			}
			// System.out.println("order:"+order);
			vi.setSub(voteSubjectDaoImp.findById(subId));
			vi.setUnicodeOrder(order);

			voteItemDaoImp.add(vi);
			re.setMes(getText("lerx.success.vote.joinByNet"));

		}
		re.setRefererUrl(ap.getWorkingUrl());
		// re.setRefererUrl(workingUrl);
		if (con){
			re.setMod(0);
		}else{
			re.setMod(2);
		}
		resultPageCode = ResultPage.init(re);
		// resultPageCode = resultPage(resultPageCode,refererUrl,mes, 0);
		request.setAttribute("lerxCmsBody", resultPageCode);

		return SUCCESS;
	}

	public String add() throws IOException {
		if (checkAdmin()) {
			cdm =CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
			uc = CookieUtil.query(cdm);
			if (uc == null) {
				vi.setUser(null);
			} else {
				User u = userDaoImp.findUserById(uc.getUserId());
				if (u != null) {
					vi.setUser(u);
				}
			}

			String curIP = IpUtil.getRealRemotIP(request).trim();
			vi.setAddIp(curIP);
			vi.setAddTime(new Timestamp(System.currentTimeMillis()));
			vi.setTitle(vi.getTitle().trim());
			vi.setState(true);
			String title = vi.getTitle();

			if (title.length() > 50) {
				title = title.substring(0, 50);
			}
			int len = title.length();
			char charIndex;
			String order = "";
			for (int i = 0; i < len; i++) {

				charIndex = vi.getTitle().charAt(i);

				int ord = chineseCharacterUnicodeDaoImp
						.findOrderByChar(charIndex);
				String tmp = StringUtil.covIntToStr(ord, 5);

				order += tmp;
			}
			// System.out.println("order:"+order);
			vi.setSub(voteSubjectDaoImp.findById(subId));
			vi.setUnicodeOrder(order);

			voteItemDaoImp.add(vi);
			findAll();
			return SUCCESS;
		} else {
			findAll();
			return INPUT;
		}
	}

	public String delById() {

		if (checkAdmin()) {
			voteItemDaoImp.delById(id);
			findAll();
			return SUCCESS;
		} else {
			findAll();
			return INPUT;
		}
	}

	public String changeState() {
		if (checkAdmin()) {

			voteItemDaoImp.setState(id, state);
			findAll();
			return SUCCESS;
		} else {
			findAll();
			return INPUT;
		}
	}

	public String findAll() {
		if (checkAdmin()) {
			// System.out.println("mod:"+mod);
			List<Long> list;
			list = voteItemDaoImp.queryAll(mod, subId, 2);
			ActionContext.getContext().getValueStack().set("viAll", list);
			return SUCCESS;
		} else {
			return INPUT;
		}
	}

	public String findById() {
		// System.out.println("id:"+id);
		VoteItem vi = voteItemDaoImp.findById(id);
		// System.out.println(vi.getTitle());
		ActionContext.getContext().getValueStack().set("vi", vi);
		request.setAttribute("curVi", vi);
		return SUCCESS;
	}

	public String modify() {
		VoteItem vidb = voteItemDaoImp.findById(vi.getId());
		vi.setAddIp(vidb.getAddIp());
		if (vidb.getAddTime()==null){
			vi.setAddTime(new Timestamp(System.currentTimeMillis()));
		}else{
			vi.setAddTime(vidb.getAddTime());
		}
		
		vi.setUser(vidb.getUser());
		vi.setSub(vidb.getSub());
		vi.setRecNum(vidb.getRecNum());
		vi.setTitle(vi.getTitle().trim());

//		if (vi.getTitle().equals(vidb.getTitle())) {
//			vi.setUnicodeOrder(vidb.getUnicodeOrder());
//		} else {
			String title = vi.getTitle();

			if (title.length() > 50) {
				title = title.substring(0, 50);
			}
			int len = title.length();
			char charIndex;
			String order = "";
			for (int i = 0; i < len; i++) {

				charIndex = vi.getTitle().charAt(i);

				int ord = chineseCharacterUnicodeDaoImp
						.findOrderByChar(charIndex);
				String tmp = StringUtil.covIntToStr(ord, 5);

				order += tmp;
			}

			vi.setUnicodeOrder(order);
//		}
		voteItemDaoImp.modify(vi);
		findAll();
		return SUCCESS;
	}

	public String post() throws IOException {
		boolean con = true;
//		System.out.println(request.getHeader("Referer"));
		VoteSubject vs;
		String cookieKey=null;
		if (subId > 0) {
			vs = voteSubjectDaoImp.findById(subId);
		} else {
			vs = null;
		}
		if (vs==null){
			con=false;
		}
		
		VoteStyle curStyle;
		if (vs==null || vs.getStyle() == null) {
			curStyle = voteStyleDaoImp.findDefault();

		} else {
			curStyle = vs.getStyle();
		}
		String resultPageCode;
		String mes=null;
		if (curStyle != null) {
			resultPageCode = curStyle.getResultPageCode();
		} else {
			
			SiteStyle curSiteStyle = siteStyleDaoImp.findDef();
			resultPageCode = curSiteStyle.getResultPageCode();
		}
		refererInit();
		ResultEl re = reInit(refererUrl, 0, resultPageCode);
		
		if (con){
			if (vr == null) {
				vr = new VoteRec();
			}else{
				vr=VoteRecUtil.prosTrim(vr);
			}
			
			cdm =CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);

			
			
			String curIP = IpUtil.getRealRemotIP(request).trim();
			
			
			String  ref=request.getHeader("Referer");
			String postUrl = SrvInf.srvUrl(request, getText("lerx.host.current"),
					Integer.valueOf(getText("lerx.serverPort")));
			postUrl+="/votePost.action?gid="+vs.getId();
			if (ref==null || !ref.trim().equalsIgnoreCase(postUrl.trim()) || vs == null) {
				con = false;
				mes = getText("lerx.err.parameter");
			}
			if (con && (ck==null || ck.length==0)){
				con = false;
				mes = getText("lerx.fail.vote.null.selected");
			}
			
			// 安全检测
			if (con){
				String fileSec="";
				try {
					fileSec = SrvInf.readSecStr(request, getText("lerx.hostSecFile"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				String secStr2 = StringUtil.md5(fileSec.concat(randKey)).toLowerCase();
				
				if (!secStr2.trim().equalsIgnoreCase(secStr.trim()) ){
					con = false;
					mes = getText("lerx.err.secStr.illegalOperation");
				}
				if (con && !SecCheck.check(this, request, secStr, randKey)) {
					con = false;
					mes = getText("lerx.err.secStr.illegalOperation");
				}
			}
			
			
			
			
			//检查当前的时间是否在投票规定的时间以内
			if (con){
				
				Long curTime = System.currentTimeMillis();
				Long startTime;
				Long endTime;
				if (vs.getVoteStartTime() == null) {
					startTime = null;
				} else {
					startTime = vs.getVoteStartTime().getTime();
				}
				if (vs.getVoteEndTime() == null) {
					endTime = null;
				} else {
					endTime = vs.getVoteEndTime().getTime();
				}

				if (startTime != null && curTime < startTime) {
					con = false;
				}

				if (endTime != null && curTime > endTime) {
					con = false;
				}
				
				if (!con){
					mes = getText("lerx.fail.vote.time.out");
					
				}
			}
			
			//是否密码投票
			String votePws=vs.getVotePassword();
			if (con && votePws!=null && !votePws.trim().equals("")){
				if (password==null || password.trim().equals("")){
					con = false;
					mes = getText("lerx.err.password");
				}else{
					boolean pwdMD5ToLowerCase;
					if (getText("lerx.pwdMD5ToLowerCase").trim().equalsIgnoreCase("true")){
						pwdMD5ToLowerCase=true;
					}else{
						pwdMD5ToLowerCase=false;
					}
					String salt=vs.getSalt();
					String passwordMd5;
					if (pwdMD5ToLowerCase){
						passwordMd5=StringUtil.md5(StringUtil.md5(password).toLowerCase().concat(salt)).toLowerCase();
					}else{
						passwordMd5=StringUtil.md5(StringUtil.md5(password).concat(salt));
					}
					if (!passwordMd5.equals(votePws)){
						con = false;
						mes = getText("lerx.err.password");
					}
				}
				
				
			}
			
			//验证码
			if (con && vs.isUseVerifyCode()){
				String from = "vote";
				String sessionStr = from + "_" +getText("lerx.host.current")+ "_" + curIP
				+ "_random";
				String randStr = (String) ActionContext.getContext().getSession()
				.get(sessionStr);
				if (randStr == null || verifyCode == null
						|| !verifyCode.trim().equalsIgnoreCase(randStr.trim())) {
					con = false;
					mes = getText("lerx.err.verifyCode");
				}else{
					
					ActionContext.getContext().getSession().remove(sessionStr);
//					String randStr = (String) ActionContext.getContext().getSession()
//					.get(sessionStr);
				}
			}
			
			// 检查选中的记录的id号是否相同
			if (con && !ArrayUtil.arraySamCheckRandomMod(ck)) {
				con = false;
				mes = getText("lerx.err.secStr.illegalOperation");
			}
			
			//检查投票数是否超过最大投票数，或与系统强制的投票数不同
			if (con && vs.getUpperLimit()>0){
				int upperLimit=vs.getUpperLimit();
				boolean fullUpperConstraint = vs.isFullUpperConstraint();
				if (ck.length>upperLimit){
					con = false;
					mes = getText("lerx.fail.vote.selected.out");
					
				}else{
					if (fullUpperConstraint && upperLimit!=ck.length){
						con = false;
						mes = getText("lerx.fail.vote.selected.under");
					}
				}
				
			}
			
			//ip限制
			if (con && vs.getHoursAtIpRule()>0){
				if (voteRecDaoImp.findRecByIpInTimeRange(subId,curIP, vs.getHoursAtIpRule())){
					con = false;
					mes = getText("lerx.fail.vote.rule.out.ip");
				}
			}
			
			//ip范围
			if (con && vs.getIpArea()!=null && !vs.getIpArea().trim().equals("")){
				if (!IpUtil.isInRange(curIP, vs.getIpArea().trim())) {
					con = false;
					mes = getText("lerx.fail.ip.out");
				}
			}
			
			//cookie
			if(con && vs.getHoursAtMachineRule()>0){
				String k=getText("lerx.prefixOfCookieForLogin");
				k = StringUtil.strReplace(k, "servername", cdm.getHost());
				if (k!=null && !k.trim().equals("") && !k.trim().equals("lerx.prefixOfCookieForLogin")){
					k+="_vote_rec_id_"+subId;
				}else{
					k+=cdm.getHost()+"_vote_rec_id_"+subId;
				}
				if (CookieUtil.query(cdm, k)!=null){
					con=false;
//					System.out.println("k:"+k);
//					System.out.println("CookieUtil.query(cdm, k):"+CookieUtil.query(cdm, k));
					mes = getText("lerx.fail.vote.rule.out.cookie");
				}else{
					cookieKey=k;
					
				}
			}
			
			//手机或电话号码
			if(con && vs.isPhoneCodeRule()){
				String phone=vr.getPhone();
				if (phone==null || phone.trim().equals("")){
					con = false;
					mes = getText("lerx.fail.vote.null.phone");
				}else{
					phone=phone.trim();
					if (voteRecDaoImp.findRecByPhoneCode(subId, phone)){
						con = false;
						mes = getText("lerx.fail.vote.rec.exists");
					}
				}
			}
			
			//身份证件号检查
			if (con && vs.isIdentityRule()){
				String identity=vr.getIdentity();
				if (identity==null || identity.trim().equals("")){
					con = false;
					mes = getText("lerx.fail.vote.null.idc");
				}else{
					identity=identity.trim();
					if (voteRecDaoImp.findRecByIdentity(subId, identity)){
						con = false;
						mes = getText("lerx.fail.vote.rec.exists");
					}
				}
			}
			
			// 查询是否在当天有相同的IP&&randKey
			if (con && voteRecDaoImp.findRecByIpAndSaltOnSameDay(subId,curIP, key)) {
				con = false;
//				System.out.println("-------在时间段内已存在");
				mes = getText("lerx.fail.vote.duplicate");
			}
			
			
			//汉字检查
			if (con & vs.isPosterNameCCUChk()){
				String name=vr.getName();
				name=StringUtil.strReplace(name, " ", "");
				name=StringUtil.strReplace(name, "　", "");
				if (name==null || name.length()<2){
					con=false;
				}
				if (con){
					int namelen = name.length();
					char charIndex;
					if (name.length() > 50) {
						name = name.substring(0, 50);
					}
					
					for (int i = 0; i < namelen; i++) {

						charIndex = name.charAt(i);

						int ord = chineseCharacterUnicodeDaoImp
								.findOrderByChar(charIndex);
						if (ord<1){
							con=false;
							break;
						}
					}
				}
				
				
			}
			//
			
			if (con) {

				
				VoteItem vi;
				uc = CookieUtil.query(cdm);
				if (uc == null) {
					vr.setUser(null);
				} else {
					User u = userDaoImp.findUserById(uc.getUserId());
					if (u != null) {
						vr.setUser(u);
					}
				}
				
				if (vs.isMesAutoPassed()){
					vr.setMesState(true);
				}else{
					vr.setMesState(false);
				}
				ck=SysUtil.reCovValue(ck, key);
				String postStr = "";
				long pid;
				int len = ck.length;
				for (int i = 0; i < len; i++) {
//					System.out.println("-----222------");
//					System.out.println("ck["+i+"]:"+ck[i]);
					pid=ck[i];
					vi = voteItemDaoImp.findById(pid);
					if (vi != null) {
//						vi.setRecNum(vi.getRecNum() + 1);
//						voteItemDaoImp.modify(vi);
						if (i == 0) {
							postStr += ck[i];
						} else {
							postStr += "_" + ck[i];
						}
					}else{
						con=false;
					}
				}
				if (con){
					postStr="_"+postStr+"_";

					vr.setSub(vs);
					vr.setRecCount(len);
					vr.setItemsRec(postStr);
					vr.setAddIp(curIP);
					vr.setAddTime(new Timestamp(System.currentTimeMillis()));
					vr.setState(true);
					vr.setSalt(key);
					
					if (voteRecDaoImp.add(vr)>0){
						for (int j = 0; j < len; j++) {
							vi = voteItemDaoImp.findById(ck[j]);
							if (vi != null) {
								vi.setRecNum(vi.getRecNum() + 1);
								voteItemDaoImp.modify(vi);
							}
						}
					}
					
					
					if (cookieKey!=null){
						CookieUtil.save(cdm, cookieKey, "true", vs.getHoursAtMachineRule()*60*60);
					}
					
					mes = getText("lerx.success.vote.post");
					if (vs.isOpenResult()) {
						re.setRefererUrl("voteRank.action?gid=" + vs.getId()+"&mod=9");
					} else {
						re.setRefererUrl("votePost.action?gid=" + vs.getId());
					}
				}else{//如果检查失败
					mes = getText("lerx.err.secStr.illegalOperation");
					con=false;
					re.setRefererUrl("votePost.action?gid=" + vs.getId());
				}
				
				
			

			} else {
//				con=false;
				re.setRefererUrl("votePost.action?gid=" + vs.getId());
				if (mes==null || mes.trim().equals("")){
					mes = getText("lerx.fail.all");
				}
				
			}
		}else{
			con=false;
			re.setRefererUrl(ap.getRefererUrl());
			mes = getText("lerx.fail.all");
		}
		if (con){
			re.setMod(0);
		}else{
			re.setMod(2);
		}
		re.setMes(mes);
		resultPageCode = ResultPage.init(re);
		request.setAttribute("lerxCmsBody", resultPageCode);
		return SUCCESS;
	}

	private boolean checkAdmin() {
		return AdminUtil
				.checkAdmin(this, getText("lerx.host.current"), request);
	}

	private ResultEl reInit(String refererUrl, int mod, String codeStr) {
		ResultEl re = new ResultEl();
		re.setAs(this);
		re.setRequest(request);
		re.setRefererUrl(refererUrl);
		re.setMod(mod);
		re.setCodeStr(codeStr);
		re.setSiteStyleDaoImp(siteStyleDaoImp);
		return re;
	}

//	private void initCdm() {
//		cdm = new CookieDoModel();
//		cdm.setActionSupport(this);
//		cdm.setEncodingCode(getText("lerx.charset").trim());
//		cdm.setPrefix(getText("lerx.prefixOfCookieForLogin"));
//		cdm.setHost(getText("lerx.host.current"));
//		cdm.setHostSecFile(getText("lerx.hostSecFile"));
//		cdm.setRequest(request);
//		cdm.setResponse(response);
//		cdm.setUserDaoImp(userDaoImp);
//	}

	private void refererInit() {

		ap = new ActionPage();
		ap.setActionSupport(this);
		ap.setRequest(request);
		ap.setAc(ActionContext.getContext());
		ap = ActionPageUtil.refererInit(ap);

	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;

	}
}
