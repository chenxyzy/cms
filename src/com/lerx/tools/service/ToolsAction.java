package com.lerx.tools.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.lerx.admin.util.AdminUtil;
import com.lerx.article.dao.IArticleGroupDao;
import com.lerx.article.dao.IArticleThreadDao;
import com.lerx.article.vo.ArticleGroup;
import com.lerx.article.vo.ArticleThread;
import com.lerx.qa.dao.IQaItemDao;
import com.lerx.qa.dao.IQaNavDao;
import com.lerx.qa.vo.QaItem;
import com.lerx.qa.vo.QaNav;
import com.lerx.sys.util.StringUtil;
import com.lerx.tools.vo.ArtsCountObj;
import com.lerx.tools.vo.Conn;
import com.lerx.user.dao.IUserDao;
import com.lerx.user.dao.IUserGroupDao;
import com.lerx.user.vo.User;
import com.lerx.user.vo.UserGroup;
import com.lerx.user.vo.UserInf;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.LocalizedTextUtil;

public class ToolsAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Conn con;
	private String replaceS1;
	private String replaceD1;
	private String replaceS2;
	private String replaceD2;
	private HttpServletRequest request;
	private Integer[] col;
	private IArticleThreadDao articleThreadDaoImp;
	private IArticleGroupDao articleGroupDaoImp;
	private IUserGroupDao userGroupDaoImp;
	private IUserDao userDaoImp;
	private IQaItemDao qaItemDaoImp;
	private IQaNavDao qaNavDaoImp;
	
	private int complete;
	private long currentRec;
	private boolean allPass;
	private boolean byEditor;
	private int dataType;
	private String sqlOrder;
	private String actionName;
	private String showTitle;
	private Timestamp begin;
	private Timestamp end;
	private String navsStr;
	private int ugid;

	public int getUgid() {
		return ugid;
	}

	public void setUgid(int ugid) {
		this.ugid = ugid;
	}

	public String getNavsStr() {
		return navsStr;
	}

	public void setNavsStr(String navsStr) {
		this.navsStr = navsStr;
	}

	public Timestamp getBegin() {
		return begin;
	}

	public void setBegin(Timestamp begin) {
		this.begin = begin;
	}

	public Timestamp getEnd() {
		return end;
	}

	public void setEnd(Timestamp end) {
		this.end = end;
	}

	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getShowTitle() {
		return showTitle;
	}

	public void setShowTitle(String showTitle) {
		this.showTitle = showTitle;
	}

	public void setComplete(int complete) {
		this.complete = complete;
	}

	public Conn getCon() {
		return con;
	}

	public void setCon(Conn con) {
		this.con = con;
	}

	public String getReplaceS1() {
		return replaceS1;
	}

	public void setReplaceS1(String replaceS1) {
		this.replaceS1 = replaceS1;
	}

	public String getReplaceD1() {
		return replaceD1;
	}

	public void setReplaceD1(String replaceD1) {
		this.replaceD1 = replaceD1;
	}

	public String getReplaceS2() {
		return replaceS2;
	}

	public void setReplaceS2(String replaceS2) {
		this.replaceS2 = replaceS2;
	}

	public String getReplaceD2() {
		return replaceD2;
	}

	public void setReplaceD2(String replaceD2) {
		this.replaceD2 = replaceD2;
	}

	public Integer[] getCol() {
		return col;
	}

	public void setCol(Integer[] col) {
		this.col = col;
	}

	public int getComplete() {
		return complete;
	}

	public long getCurrentRec() {
		return currentRec;
	}

	public void setArticleThreadDaoImp(IArticleThreadDao articleThreadDaoImp) {
		this.articleThreadDaoImp = articleThreadDaoImp;
	}

	public void setUserGroupDaoImp(IUserGroupDao userGroupDaoImp) {
		this.userGroupDaoImp = userGroupDaoImp;
	}

	public void setArticleGroupDaoImp(IArticleGroupDao articleGroupDaoImp) {
		this.articleGroupDaoImp = articleGroupDaoImp;
	}

	public void setUserDaoImp(IUserDao userDaoImp) {
		this.userDaoImp = userDaoImp;
	}

	public void setQaItemDaoImp(IQaItemDao qaItemDaoImp) {
		this.qaItemDaoImp = qaItemDaoImp;
	}

	public void setQaNavDaoImp(IQaNavDao qaNavDaoImp) {
		this.qaNavDaoImp = qaNavDaoImp;
	}

	public boolean isAllPass() {
		return allPass;
	}

	public void setAllPass(boolean allPass) {
		this.allPass = allPass;
	}

	public boolean isByEditor() {
		return byEditor;
	}

	public void setByEditor(boolean byEditor) {
		this.byEditor = byEditor;
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	public String getSqlOrder() {
		return sqlOrder;
	}

	public void setSqlOrder(String sqlOrder) {
		this.sqlOrder = sqlOrder;
	}

	private boolean conn() {
		if (checkAdmin()) {

			String url;
			String topSql;
			if (con.getCharSet() != null) {
				con.setCharSet(con.getCharSet().trim());
			}
			if (con.getDb() != null) {
				con.setDb(con.getDb().trim());
			}
			if (con.getDbFile() != null) {
				con.setDbFile(con.getDbFile().trim());
			}
			if (con.getDriver() != null) {
				con.setDriver(con.getDriver().trim());
			}
			if (con.getPassword() != null) {
				con.setPassword(con.getPassword().trim());
			}
			if (con.getPort() != null) {
				con.setPort(con.getPort().trim());
			}
			if (con.getServer() != null) {
				con.setServer(con.getServer().trim());
			}
			if (con.getTable() != null) {
				con.setTable(con.getTable().trim());
			}
			if (con.getUser() != null) {
				con.setUser(con.getUser().trim());
			}

			switch (con.getDbType()) {
			case 1:
				topSql = "select * from " + con.getTable() + " limit 0,1";
				con.setDriver("org.gjt.mm.mysql.Driver");
				url = "jdbc:mysql://" + con.getServer() + ":" + con.getPort()
						+ "/" + con.getDb() + "?user=" + con.getUser()
						+ "&password=" + con.getPassword()
						+ "&useUnicode=true&characterEncoding="
						+ con.getCharSet()
						+ "&zeroDateTimeBehavior=convertToNull";
				break;
			default:
				topSql = "select top 1 * from " + con.getTable();
				con.setDriver("sun.jdbc.odbc.JdbcOdbcDriver");
				url = "jdbc:odbc:Driver={MicroSoft Access Driver (*.mdb)};DBQ="
						+ con.getDbFile();
				// con.setUrl("jdbc:odbc:Driver={MicroSoft Access Driver (*.mdb)};DBQ="+con.getDbFile());

				break;
			}

			con.setUrl(url);
			Properties info = new Properties();
			info.put("charSet", con.getCharSet());
			// System.out.println("文件："+ac.getDbFile());

			try {
				Class.forName(con.getDriver());
			} catch (ClassNotFoundException e) {
				request.setAttribute("msg", "驱动未能加载");
				return false;
			}
			// System.out.println("con.getUrl()"+con.getUrl());
			// System.out.println("con.getDriver()"+con.getDriver());
			try {
				con.setConn((Connection) DriverManager.getConnection(
						con.getUrl(), info));
			} catch (Exception e) {
				request.setAttribute("msg", "未能获得连接或文件未找到");
				return false;
			}
			try {
				con.setSt((Statement) con.getConn().createStatement());
			} catch (Exception e) {
				request.setAttribute("msg", "st失败");
				return false;
			}
			try {
				con.setRs(con.getSt().executeQuery(
						"select count(*) from " + con.getTable()));
				con.getRs().next();
				con.setRecCount(con.getRs().getLong(1));
				con.setRs(con.getSt().executeQuery(topSql));
				con.getRs().next();
				con.setRsmd(con.getRs().getMetaData());
				con.setColCount(con.getRsmd().getColumnCount());
			} catch (Exception e) {
				// e.printStackTrace();
				request.setAttribute("msg", "读取表数据时发生错误！");
				return false;
			}
			return true;
		} else {
			return false;
		}
		// System.out.println(ac.getCharSet());
	}

	public String importInit() throws SQLException {

		if (checkAdmin() && !conn()) {
			return ERROR;
		}
		if (con==null){
			System.out.println("错误：con null");
		}
		if (con.getRsmd()==null){
			System.out.println("错误：Rsmd null");
		}
		String[] colname = new String[con.getRsmd().getColumnCount()];
		for (int i = 0; i < con.getRsmd().getColumnCount(); i++) {
			colname[i] = con.getRsmd().getColumnName(i + 1);
		}
		request.setAttribute("col", colname);
		request.setAttribute("msg", "连接成功");
		close();
		return SUCCESS;

	}

	public String importData() {
		if (checkAdmin() && !conn()) {
			return ERROR;
		}
		boolean rep1 = false, rep2 = false, falg = false;
		if (replaceS1 != null && !replaceS1.trim().equals("")) {
			replaceS1 = replaceS1.replaceAll("\\[", "~");
			replaceS1 = replaceS1.replaceAll("\\]", "@");
			rep1 = true;
		}
		if (replaceS2 != null && !replaceS2.trim().equals("")) {
			replaceS2 = replaceS2.replaceAll("\\[", "~");
			replaceS2 = replaceS2.replaceAll("\\]", "@");
			rep2 = true;
		}

		try {
			if (sqlOrder == null || sqlOrder.trim().equals("")) {
				sqlOrder = "";
			} else {
				sqlOrder = " " + sqlOrder;
			}
			con.setRs(con.getSt().executeQuery(
					"select * from " + con.getTable() + sqlOrder));
			ArticleThread at = new ArticleThread();
			ArticleGroup ag = new ArticleGroup();
			UserInf user = new UserInf();
			UserGroup ug = new UserGroup();
			QaItem qi = new QaItem();
			String tmp, body, thumbnail, mainImg, linkUrl;
			long step = 0;
			int nav;
			while (con.getRs().next()) {
				step++;
				at.setId(null);

				ag.setId(null);

				ag.setShowOnParent(true);
				user.setId(null);
				if (allPass) {
					at.setState(true);
					ag.setState(true);
					user.setState(true);
					ug.setState(true);
					qi.setState(true);
				}

				for (int i = 0; i < col.length; i++) {
					switch (col[i]) {
					case 1:
						if (dataType == 1) {
							nav = con.getRs().getInt(i + 1);
//							System.out.println("nav:"+nav);
							at.setArticleGroup(articleGroupDaoImp
									.findById(nav));
						}
						break;
					case 2:
						if (dataType == 1) {
							tmp = con.getRs().getString(i + 1).trim();
							tmp = StringUtil.htmlFilter(tmp);
							tmp = StringUtil.strReplace(tmp, "&nbsp;", "");
							if (tmp.length() > 100) {
								tmp = tmp.substring(0, 100);
							}

							at.setTitle(tmp);
						}
						break;
					case 3:
						if (dataType == 1) {
							at.setShortTitle(con.getRs().getString(i + 1));
						}
						break;
					case 4:
						if (dataType == 1) {
							at.setPensileTitle(con.getRs().getString(i + 1));
						}
						break;
					case 5:
						if (dataType == 1) {
							at.setAccessionalTitle(con.getRs().getString(i + 1));
						}
						break;
					case 6:
						if (dataType == 1) {
							at.setAuthor(con.getRs().getString(i + 1));
						}
						break;
					case 7:
						if (dataType == 1) {
							at.setAuthorDept(con.getRs().getString(i + 1));
						}
						break;
					case 8:
						if (dataType == 1) {
							at.setAuthorEmail(con.getRs().getString(i + 1));
						}
						break;
					case 9:
						if (dataType == 1) {
							at.setAuthorUrl(con.getRs().getString(i + 1));
						}
						break;
					case 10:
						if (dataType == 1) {
							at.setAddTime(con.getRs().getTimestamp(i + 1));
							at.setAddTimeLong(at.getAddTime().getTime());
						}
						break;
					case 11:
						if (dataType == 1) {
							
							at.setAddTime(new java.sql.Timestamp(con.getRs().getLong(i + 1)*1000));
							at.setAddTimeLong(at.getAddTime().getTime());
//							at.setAddTime(con.getRs().getTimestamp(i + 1));
						}
						break;
					case 12:
						if (dataType == 1) {
							at.setSynopsis(con.getRs().getString(i + 1));
						}
						break;
					case 13:
						if (dataType == 1) {
							falg = false;
							body = con.getRs().getString(i + 1);

							if (rep1  && body!=null && !body.trim().equals("")) {
								falg = true;
								body = body.replaceAll("\\[", "~");
								body = body.replaceAll("\\]", "@");
								body = StringUtil.strReplace(body, replaceS1,
										replaceD1);
							}

							if (rep2 && body!=null && !body.trim().equals("")) {
								if (!falg) {
									body = body.replaceAll("\\[", "~");
									body = body.replaceAll("\\]", "@");
								}

								body = StringUtil.strReplace(body, replaceS2,
										replaceD2);
							}
							at.setBody(body);
							// System.out.println("body:"+body);
						}
						break;
					case 14:
						if (dataType == 1) {
							at.setViews(con.getRs().getInt(i + 1));
						}
						break;
					case 15:
						if (dataType == 1) {
							at.setState(con.getRs().getBoolean(i + 1));
						}
						break;
					case 16:
						if (dataType == 1) {
							falg = false;
							thumbnail = con.getRs().getString(i + 1);

							if (rep1) {
								falg = true;
								thumbnail = thumbnail.replaceAll("\\[", "~");
								thumbnail = thumbnail.replaceAll("\\]", "@");
								thumbnail = StringUtil.strReplace(thumbnail,
										replaceS1, replaceD1);
							}

							if (rep2) {
								falg = true;
								if (!falg) {
									thumbnail = thumbnail
											.replaceAll("\\[", "~");
									thumbnail = thumbnail
											.replaceAll("\\]", "@");
								}
								thumbnail = StringUtil.strReplace(thumbnail,
										replaceS2, replaceD2);
							}

							at.setThumbnail(thumbnail);
							// System.out.println("thumbnail:"+thumbnail);
						}
						break;
					case 17:
						if (dataType == 1) {
							falg = false;
							mainImg = con.getRs().getString(i + 1);

							if (rep1 && mainImg!=null && !mainImg.trim().equals("")) {
								falg = true;
								mainImg = mainImg.replaceAll("\\[", "~");
								mainImg = mainImg.replaceAll("\\]", "@");
								mainImg = StringUtil.strReplace(mainImg,
										replaceS1, replaceD1);
							}

							if (rep2 && mainImg!=null && !mainImg.trim().equals("")) {
								falg = true;
								if (!falg) {
									mainImg = mainImg.replaceAll("\\[", "~");
									mainImg = mainImg.replaceAll("\\]", "@");
								}
								mainImg = StringUtil.strReplace(mainImg,
										replaceS2, replaceD2);
							}
							at.setMainImg(mainImg);
							// System.out.println("mainImg:"+mainImg);
						}
						break;
					case 18:
						if (dataType == 1) {
							at.setMainImgExplain(con.getRs().getString(i + 1));
						}
						break;
					case 19:
						if (dataType == 1) {
							at.setJournal(con.getRs().getString(i + 1));
						}
						break;
					case 20:
						if (dataType == 1) {
							at.setLinkJump(con.getRs().getBoolean(i + 1));
						}
						break;
					case 21:
						if (dataType == 1) {

							falg = false;
							linkUrl = con.getRs().getString(i + 1);

							if (rep1) {
								falg = true;
								linkUrl = linkUrl.replaceAll("\\[", "~");
								linkUrl = linkUrl.replaceAll("\\]", "@");
								linkUrl = StringUtil.strReplace(linkUrl,
										replaceS1, replaceD1);
							}

							if (rep2) {
								falg = true;
								if (!falg) {
									linkUrl = linkUrl.replaceAll("\\[", "~");
									linkUrl = linkUrl.replaceAll("\\]", "@");
								}
								linkUrl = StringUtil.strReplace(linkUrl,
										replaceS2, replaceD2);
							}
							at.setLinkUrl(linkUrl);
						}
						break;
					case 22:
						if (dataType == 1) {
							at.setMentor(con.getRs().getString(i + 1));
						}
						break;
					case 23:
						if (dataType == 1) {
							at.setLastViewIp(con.getRs().getString(i + 1));
						}
						break;
					case 24:
						if (dataType == 1) {
							at.setPasser(con.getRs().getString(i + 1));
						}
						break;
					case 25:
						if (dataType == 1) {
							at.setMember(con.getRs().getString(i + 1));
						}
						break;
					case 26:
						if (dataType == 1) {
							at.setLocation(con.getRs().getInt(i + 1));
						}
						break;
					case 27:
						if (dataType == 1) {
							at.setNotice(con.getRs().getBoolean(i + 1));
						}
						break;
					case 28:
						if (byEditor) {
							at.setByEditor(true);
						} else {
							if (dataType == 1) {
								at.setByEditor(con.getRs().getBoolean(i + 1));
							}
						}
						break;

					// 栏目
					case 31:
						if (dataType == 2) {
							ag.setParentGroup(articleGroupDaoImp
									.findById(con.getRs().getInt(
											i + 1)));
						}
						break;
					case 32:
						if (dataType == 2) {
							ag.setGroupName(con.getRs().getString(i + 1));
						}
						break;
					case 33:
						if (dataType == 2) {
							ag.setDisplayOrder(con.getRs().getString(i + 1));
						}
						break;
					case 34:
						if (dataType == 2) {
							ag.setShare(con.getRs().getBoolean(i + 1));
						}
						break;
					case 35:
						if (dataType == 2) {
							ag.setAsClass(con.getRs().getBoolean(i + 1));
						}
						break;
					case 36:
						if (dataType == 2) {
							ag.setState(con.getRs().getBoolean(i + 1));
						}
						break;
					case 37:
						if (dataType == 2) {
							ag.setIconUrl(con.getRs().getString(i + 1));
						}
						break;
					case 38:
						if (dataType == 2) {
							ag.setJumpUrl(con.getRs().getString(i + 1));
						}
						break;
					case 39:
						if (dataType == 2) {
							ag.setShowOnIndex(con.getRs().getBoolean(i + 1));
						}
						break;
					case 40:
						if (dataType == 2) {
							ag.setLengthShowOnIndex(con.getRs().getInt(i + 1));
						}
						break;
					case 41:
						if (dataType == 2) {
							ag.setNumberShowOnIndex(con.getRs().getInt(i + 1));
						}
						break;
					case 42:
						if (dataType == 2) {
							ag.setShowOnParent(con.getRs().getBoolean(i + 1));
						}
						break;
					case 43:
						if (dataType == 2) {
							ag.setLengthShowOnParent(con.getRs().getInt(i + 1));
						}
						break;
					case 44:
						if (dataType == 2) {
							ag.setNumberShowOnParent(con.getRs().getInt(i + 1));
						}
						break;

					// 用户
					case 51:
						if (dataType == 3) {
							user.setUserName(con.getRs().getString(i + 1));
						}
						break;
					case 52:
						if (dataType == 3) {
							user.setPassword(con.getRs().getString(i + 1));
						}
						break;
					case 53:
						if (dataType == 3) {
							user.setSalt(con.getRs().getString(i + 1));
						}
						break;
					case 54:
						if (dataType == 3) {
							user.setUserGroup(userGroupDaoImp
									.findUserGroupByID(con.getRs()
											.getInt(i + 1)));
						}
						break;
					case 55:
						if (dataType == 3) {
							user.setEmail(con.getRs().getString(i + 1));
						}
						break;
					case 56:
						if (dataType == 3) {
							user.setAvatarFile(con.getRs().getString(i + 1));
						}
						break;
					case 57:
						if (dataType == 3) {
							user.setRegIp(con.getRs().getString(i + 1));
						}
						break;
					case 58:
						if (dataType == 3) {
							user.setLastLoginIp(con.getRs().getString(i + 1));
						}
						break;
					case 59:
						if (dataType == 3) {
							user.setRegTimstamp(con.getRs().getTimestamp(i + 1));
						}
						break;
					case 60:
						if (dataType == 3) {
							user.setLastLoginTimstamp(con.getRs().getTimestamp(
									i + 1));
						}
						break;
					case 61:
						if (dataType == 3) {
							user.setArticleThreadsPassed(con.getRs().getInt(
									i + 1));
						}
						break;
					case 62:
						if (dataType == 3) {
							user.setArticleThreadsCount(con.getRs().getInt(
									i + 1));
						}
						break;
					case 63:
						if (dataType == 3) {
							user.setAllScore(con.getRs().getInt(i + 1));
						}
						break;
					case 64:
						if (dataType == 3) {
							user.setBbsScore(con.getRs().getInt(i + 1));
						}
						break;
					case 65:
						if (dataType == 3) {
							user.setTrueName(con.getRs().getString(i + 1));
						}
						break;
					case 66:
						if (dataType == 3) {
							user.setSex(con.getRs().getBoolean(i + 1));
						}
						break;
					case 67:
						if (dataType == 3) {
							user.setBirthday(con.getRs().getDate(i + 1));
						}
						break;
					case 68:
						if (dataType == 3) {
							user.setDept(con.getRs().getString(i + 1));
						}
						break;
					case 69:
						if (dataType == 3) {
							user.setCountry(con.getRs().getString(i + 1));
						}
						break;
					case 70:
						if (dataType == 3) {
							user.setProvince(con.getRs().getString(i + 1));
						}
						break;
					case 71:
						if (dataType == 3) {
							user.setCity(con.getRs().getString(i + 1));
						}
						break;
					case 72:
						if (dataType == 3) {
							user.setAddress(con.getRs().getString(i + 1));
						}
						break;
					case 73:
						if (dataType == 3) {
							user.setQq(con.getRs().getString(i + 1));
						}
						break;
					case 74:
						if (dataType == 3) {
							user.setState(con.getRs().getBoolean(i + 1));
						}
						break;

					// 用户组

					case 81:
						if (dataType == 4) {
							ug.setGroupName(con.getRs().getString(i + 1));
						}
						break;
					case 82:
						if (dataType == 4) {
							ug.setPowerMask(con.getRs().getString(i + 1));
						}
						break;
					case 83:
						if (dataType == 4) {
							ug.setState(con.getRs().getBoolean(i + 1));
						}
						break;

					// 问答
					case 91:
						if (dataType == 5) {
							nav = con.getRs().getInt(i + 1);
							QaNav qn = qaNavDaoImp.findById(nav);
							if (qn != null) {
								qi.setQn(qn);
							}

						}
						break;
					case 92:
						if (dataType == 5) {
							qi.setTitle(con.getRs().getString(i + 1));
						}
						break;
					case 93:
						if (dataType == 5) {
							qi.setQuestion(con.getRs().getString(i + 1));
						}
						break;
					case 94:
						if (dataType == 5) {
							qi.setAuthor(con.getRs().getString(i + 1));
						}
						break;
					case 95:
						if (dataType == 5) {
							qi.setAddTime(con.getRs().getTimestamp(i + 1));
						}
						break;
					case 96:
						if (dataType == 5) {
							qi.setAddIp(con.getRs().getString(i + 1));
						}
						break;
					case 97:
						if (dataType == 5) {
							qi.setEmail(con.getRs().getString(i + 1));
						}
						break;
					case 98:
						if (dataType == 5) {
							qi.setAddr(con.getRs().getString(i + 1));
						}
						break;
					case 99:
						if (dataType == 5) {
							qi.setQq(con.getRs().getString(i + 1));
						}
						break;
					case 100:
						if (dataType == 5) {
							qi.setPhone(con.getRs().getString(i + 1));
						}
						break;
					case 101:
						if (dataType == 5) {
							qi.setPassword(con.getRs().getString(i + 1));
						}
						break;
					case 102:
						if (dataType == 5) {
							qi.setReplier(con.getRs().getString(i + 1));
						}
						break;
					case 103:
						if (dataType == 5) {
							qi.setAnswer(con.getRs().getString(i + 1));
						}
						break;
					case 104:
						if (dataType == 5) {
							qi.setReplyTime(con.getRs().getTimestamp(i + 1));
						}
						break;
					case 105:
						if (dataType == 5) {
							qi.setOpen(con.getRs().getBoolean(i + 1));
						}
						break;
					case 106:
						if (dataType == 5) {
							qi.setViews(con.getRs().getInt(i + 1));
						}
						break;
					default:
						break;

					}
				}
				// 保存：
				// if (at.isState())
				String member;
				switch (dataType) {
				case 1:
					if (at.getArticleGroup() != null
							&& at.getArticleGroup().getId() != 0
							&& at.getTitle() != null) {
						member = at.getMember();
						if (member != null && !member.trim().equals("")) {
							User u = userDaoImp.findUserByName(member);
							if (u != null) {
								// System.out.println("前台找到一个记录");
								at.setUser(u);
							}
						}

						articleThreadDaoImp.add(at);
					}else{
//						System.out.println("没有导入："+at.getTitle());
//						System.out.println("at.getArticleGroup()："+at.getArticleGroup());
//						System.out.println("at.getArticleGroup().getId()："+at.getArticleGroup().getId());
					}
					break;
				case 2:
					articleGroupDaoImp.add(ag);
					break;
				case 3:
					userDaoImp.importUser(user);
					break;
				case 4:
					userGroupDaoImp.addUserGroup(ug);
					break;
				case 5:
					if (qi.getQn() != null) {
						qaItemDaoImp.add(qi);
					}

					break;
				}

				double r = ((double) step * 100) / (double) con.getRecCount();
				int percent = (int) r;
				this.complete = percent;
				this.currentRec = step;

			}
		} catch (SQLException e) {
			request.setAttribute("msg", "导入失败！");
			// TODO Auto-generated catch block
			return ERROR;
		}
		close();
		return SUCCESS;
	}

	public void query(Conn ac, String sqlCmd) {
		try {
			ac.setRs(ac.getSt().executeQuery(sqlCmd));
			ac.setRsmd(ac.getRs().getMetaData());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			con.getRs().close();
			con.getSt().close();
			con.getConn().close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public String countArtByUser() {
		if (checkAdmin()) {
			List<Long> list = userDaoImp.findAllUser();
			long step = 0;
			this.actionName = "tools_countArtByUser.action";
			for (Long uid : list) {
				step++;
				User u = userDaoImp.findUserById(uid);
				Long count = articleThreadDaoImp.countByUserId(uid, 0);
				u.setArticleThreadsCount(count);
				count = articleThreadDaoImp.countByUserId(uid, 1);
				u.setArticleThreadsPassed(count);
				userDaoImp.modifyUser(u);

				double r = ((double) step * 100) / (double) list.size();
				int percent = (int) r;
				this.complete = percent;
			}
			return SUCCESS;
		} else {
			return ERROR;
		}

	}

	public String countArtByUserAndOther() {
		if (checkAdmin()) {
			List<Long> list;
			if (ugid == 0) {
				list = userDaoImp.findAllUser();
			} else {
				list = userDaoImp.findAllUserByGroup(ugid);
			}

			long step = 0;
			
			List<ArtsCountObj> acoList = new ArrayList<ArtsCountObj>();
//			this.actionName = "tools_countArtByUserAndOther.action";
			for (Long uid : list) {
				step++;
				ArtsCountObj aco = new ArtsCountObj();
				User u = userDaoImp.findUserById(uid);
				Long count = articleThreadDaoImp.countByUserIdAndOther(uid, 0,
						navsStr, begin, end);
				aco.setAll(count);
				count = articleThreadDaoImp.countByUserIdAndOther(uid, 1,
						navsStr, begin, end);
				aco.setPassed(count);
				aco.setUid(u.getId());
				aco.setUsername(u.getUserName());
				aco.setTrueName(userDaoImp.findUserInfById(u.getId()).getTrueName());
				// System.out.println("aco.getUsername():"+aco.getUsername());
				// System.out.println("u.getUserName():"+u.getUserName());
				acoList.add(aco);
//				double r = ((double) step * 100) / (double) list.size();
//				int percent = (int) r;
//				this.complete = percent;
			}
			Comparator<ArtsCountObj> comparator = new Comparator<ArtsCountObj>() {
				public int compare(ArtsCountObj s1, ArtsCountObj s2) {
					// 先排通过数
					if (s1.getPassed() != s2.getPassed()) {
						return (int) (s2.getPassed() - s1.getPassed());
					} else {
						// 再排全部
						if (s1.getAll() != s2.getAll()) {
							return (int) (s2.getAll() - s1.getAll());
						} else {
							// 最后按id
							return (int) (s1.getUid() - s2.getUid());
						}
					}
				}
			};
			Collections.sort(acoList, comparator);
			
			request.setAttribute("allCountList", acoList);
			return SUCCESS;
		} else {
			return ERROR;
		}

	}
	
	public String navViewsStat(){
		if (checkAdmin()) {
			articleGroupDaoImp.statViews();
			return SUCCESS;
		}else {
			return ERROR;
		}
		
	}

	private boolean checkAdmin() {
		return AdminUtil.checkAdmin(readRec("lerx.host.current"), request);
	}

	private String readRec(String key) {
		return LocalizedTextUtil.findDefaultText(key, new Locale("zh_CN"));
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}

}
