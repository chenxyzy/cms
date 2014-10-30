package com.lerx.vote.vo;

import java.sql.Timestamp;

import com.lerx.style.vote.vo.VoteStyle;

public class VoteSubject {
	
	private Integer id;
	private String title;
	private VoteStyle style;
	private Timestamp createTime;
	private Timestamp signStartTime;
	private Timestamp signEndTime;
	private Timestamp voteStartTime;
	private Timestamp voteEndTime;
	private int upperLimit;					//最大投票数
	private boolean fullUpperConstraint;	//强制投满
	private String ipArea;					//指定可以投票的ip范围
	private int hoursAtIpRule;				//IP投票时间限制
	private int hoursAtMachineRule;			//机器投票时间限制
	private boolean useVerifyCode;			//是否使用验证码
	private boolean identityRule;			//是否身份强制检查
	private boolean phoneCodeRule;			//是否电话号码强制检查
	private boolean posterNameCCUChk;				//是否中文检测投票人性名
	private boolean state;
	private boolean openResult;				//是否允许查看结果
	private boolean netJoin;				//是否允许网上报名
	private boolean netJoinMustBeMember;	//如果网上报名是否一定要是会员
	private boolean netJoinAutoPassed;		//网上报名是否自动通过
	private boolean mesAutoPassed;			//投票时留言是否自动通过
	private String salt;
	private String votePassword;
	private int orderType;
	private int percent;
	private String secStr;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getSignStartTime() {
		return signStartTime;
	}
	public void setSignStartTime(Timestamp signStartTime) {
		this.signStartTime = signStartTime;
	}
	public Timestamp getSignEndTime() {
		return signEndTime;
	}
	public void setSignEndTime(Timestamp signEndTime) {
		this.signEndTime = signEndTime;
	}
	public Timestamp getVoteStartTime() {
		return voteStartTime;
	}
	public void setVoteStartTime(Timestamp voteStartTime) {
		this.voteStartTime = voteStartTime;
	}
	public Timestamp getVoteEndTime() {
		return voteEndTime;
	}
	public void setVoteEndTime(Timestamp voteEndTime) {
		this.voteEndTime = voteEndTime;
	}
	public int getUpperLimit() {
		return upperLimit;
	}
	public void setUpperLimit(int upperLimit) {
		this.upperLimit = upperLimit;
	}
	public boolean isFullUpperConstraint() {
		return fullUpperConstraint;
	}
	public void setFullUpperConstraint(boolean fullUpperConstraint) {
		this.fullUpperConstraint = fullUpperConstraint;
	}
	public String getIpArea() {
		return ipArea;
	}
	public void setIpArea(String ipArea) {
		this.ipArea = ipArea;
	}
	public int getHoursAtIpRule() {
		return hoursAtIpRule;
	}
	public void setHoursAtIpRule(int hoursAtIpRule) {
		this.hoursAtIpRule = hoursAtIpRule;
	}
	public int getHoursAtMachineRule() {
		return hoursAtMachineRule;
	}
	public void setHoursAtMachineRule(int hoursAtMachineRule) {
		this.hoursAtMachineRule = hoursAtMachineRule;
	}
	public boolean isIdentityRule() {
		return identityRule;
	}
	public void setIdentityRule(boolean identityRule) {
		this.identityRule = identityRule;
	}
	public boolean isPhoneCodeRule() {
		return phoneCodeRule;
	}
	public void setPhoneCodeRule(boolean phoneCodeRule) {
		this.phoneCodeRule = phoneCodeRule;
	}
	public boolean isPosterNameCCUChk() {
		return posterNameCCUChk;
	}
	public void setPosterNameCCUChk(boolean posterNameCCUChk) {
		this.posterNameCCUChk = posterNameCCUChk;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public boolean isOpenResult() {
		return openResult;
	}
	public void setOpenResult(boolean openResult) {
		this.openResult = openResult;
	}
	public boolean isNetJoin() {
		return netJoin;
	}
	public void setNetJoin(boolean netJoin) {
		this.netJoin = netJoin;
	}
	public boolean isNetJoinMustBeMember() {
		return netJoinMustBeMember;
	}
	public void setNetJoinMustBeMember(boolean netJoinMustBeMember) {
		this.netJoinMustBeMember = netJoinMustBeMember;
	}
	public boolean isMesAutoPassed() {
		return mesAutoPassed;
	}
	public void setMesAutoPassed(boolean mesAutoPassed) {
		this.mesAutoPassed = mesAutoPassed;
	}
	public boolean isNetJoinAutoPassed() {
		return netJoinAutoPassed;
	}
	public void setNetJoinAutoPassed(boolean netJoinAutoPassed) {
		this.netJoinAutoPassed = netJoinAutoPassed;
	}
	public boolean isUseVerifyCode() {
		return useVerifyCode;
	}
	public void setUseVerifyCode(boolean useVerifyCode) {
		this.useVerifyCode = useVerifyCode;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getVotePassword() {
		return votePassword;
	}
	public void setVotePassword(String votePassword) {
		this.votePassword = votePassword;
	}
	public int getOrderType() {
		return orderType;
	}
	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}
	public int getPercent() {
		return percent;
	}
	public void setPercent(int percent) {
		this.percent = percent;
	}
	public VoteStyle getStyle() {
		return style;
	}
	public void setStyle(VoteStyle style) {
		this.style = style;
	}
	public String getSecStr() {
		return secStr;
	}
	public void setSecStr(String secStr) {
		this.secStr = secStr;
	}
}
