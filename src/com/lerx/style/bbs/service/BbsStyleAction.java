package com.lerx.style.bbs.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.lerx.admin.util.AdminUtil;
import com.lerx.site.dao.ISiteInfoDao;
import com.lerx.site.vo.SiteInfo;
import com.lerx.style.bbs.dao.IBbsStyleDao;
import com.lerx.style.bbs.vo.BbsStyle;
import com.lerx.style.bbs.vo.BbsStyleSubElementInCommon;
import com.lerx.sys.service.FileAction;
import com.lerx.sys.util.LogWrite;
import com.lerx.sys.util.Obj;
import com.lerx.sys.util.SrvInf;
import com.lerx.sys.util.TimeUtil;
import com.lerx.sys.util.vo.FileV;
import com.opensymphony.xwork2.ActionSupport;

public class BbsStyleAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private int id;
	private FileV f;
	private int oid;
	private int styleID;
	private int mod;
	private boolean state;
	private String styleName;
	private String author;
	private String description;
	private List<BbsStyle> list;
	private IBbsStyleDao bbsStyleDaoImp;
	private String curModel;
	private String newStyleName;
	private ISiteInfoDao siteInfDaoImp;
	private SiteInfo site;
	
	
	public void setSiteInfDaoImp(ISiteInfoDao siteInfDaoImp) {
		this.siteInfDaoImp = siteInfDaoImp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public FileV getF() {
		return f;
	}

	public void setF(FileV f) {
		this.f = f;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public int getStyleID() {
		return styleID;
	}

	public void setStyleID(int styleID) {
		this.styleID = styleID;
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

	public String getStyleName() {
		return styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<BbsStyle> getList() {
		return list;
	}

	public void setList(List<BbsStyle> list) {
		this.list = list;
	}

	public String getCurModel() {
		return curModel;
	}

	public void setCurModel(String curModel) {
		this.curModel = curModel;
	}

	public String getNewStyleName() {
		return newStyleName;
	}

	public void setNewStyleName(String newStyleName) {
		this.newStyleName = newStyleName;
	}

	public void setBbsStyleDaoImp(IBbsStyleDao bbsStyleDaoImp) {
		this.bbsStyleDaoImp = bbsStyleDaoImp;
	}
	
	private BbsStyle styleInit(BbsStyle s){
		s.setId(null);
		if (s.getForumStyle()==null){
			s.setForumStyle(new BbsStyleSubElementInCommon());
		}
		if (s.getThemeStyle()==null){
			s.setThemeStyle(new BbsStyleSubElementInCommon());
		}
		if (s.getIndexStyle()==null){
			s.setIndexStyle(new BbsStyleSubElementInCommon());
		}
		if (s.getSearchStyle()==null){
			s.setSearchStyle(new BbsStyleSubElementInCommon());
		}
		if (s.getPublicStyle()==null){
			s.setPublicStyle(new BbsStyleSubElementInCommon());
		}
		if (s.getGenericStyle()==null){
			s.setGenericStyle(new BbsStyleSubElementInCommon());
		}
		if (s.getEditThreadStyle()==null){
			s.setEditThreadStyle(new BbsStyleSubElementInCommon());
		}
		s.getForumStyle().setId(null);
		s.getThemeStyle().setId(null);
		s.getIndexStyle().setId(null);
		s.getSearchStyle().setId(null);
		s.getPublicStyle().setId(null);
		s.getGenericStyle().setId(null);
		s.getEditThreadStyle().setId(null);
		s.setDef(false);
		return s;
	}

	public String copy() throws Exception {
		site = siteInfDaoImp.query();
		if (checkAdmin()) {
			BbsStyle s = (BbsStyle) bbsStyleDaoImp.findById(id);
			BbsStyle d = (BbsStyle) s.clone();
			d=styleInit(d);
			d.setStyleName(newStyleName);
			bbsStyleDaoImp.add(d);
			LogWrite.logWrite(request,
					"管理员<" + AdminUtil.findCurAdmin(this,SrvInf.obtainHostName(request, site.getHost()), request)
							+ ">复制论坛风格模板：" + newStyleName + "成功。");
			query();
			return SUCCESS;
		} else {
			return INPUT;
		}

	}

	public String query() throws Exception {

		list = bbsStyleDaoImp.findAll(mod);

		request.setAttribute("bbsStyleAll", list);
		return SUCCESS;
	}

	public String add() throws Exception {
		site = siteInfDaoImp.query();
		if (checkAdmin()) {
			if (bbsStyleDaoImp.findStyleByName(styleName)) {
				this.addActionError(getText("lerx.fail.exists.all"));
				query();
				return INPUT;
			} else {
				bbsStyleDaoImp.addStyle(styleName, author, description);
				query();
				LogWrite.logWrite(request,
						"管理员<" + AdminUtil.findCurAdmin(this,SrvInf.obtainHostName(request, site.getHost()), request)
								+ ">增加论坛风格模板：" + styleName + "成功。");
				return SUCCESS;
			}
		} else {
			return INPUT;
		}

	}
	
	public String findNameById() throws Exception {

		String styleName = bbsStyleDaoImp.findById(styleID)
				.getStyleName();
		request.setCharacterEncoding(getText("lerx.charset"));

		request.setAttribute("currentStyleName", styleName);
		request.setAttribute("currentModelName", "--" + curModel + "模块--");
		return SUCCESS;
	}
	
	public String del() throws Exception {
		site = siteInfDaoImp.query();
		if (checkAdmin()) {
			if (bbsStyleDaoImp.delById(id)) {
				query();
				request.setAttribute("resultAltStr",
						getText("lerx.success.all"));
				LogWrite.logWrite(request, "管理员<" + AdminUtil.findCurAdmin(this,SrvInf.obtainHostName(request, site.getHost()),request)
						+ ">删除论坛风格模板id：" + id + "成功。");
				return SUCCESS;
			} else {
				this.addActionError(getText("lerx.err.db"));
				query();
				return INPUT;
			}
		} else {
			return INPUT;
		}
	}
	
	public String changeState() throws Exception {
		site = siteInfDaoImp.query();
		if (checkAdmin()) {
			bbsStyleDaoImp.changeState(id, state);
			query();
			return SUCCESS;
		} else {
			return INPUT;
		}

	}
	
	public String setDefault() throws Exception{
		if (checkAdmin()) {
			bbsStyleDaoImp.setDef(id);
			query();
			return SUCCESS;
		}else {
			return INPUT;
		}
	}
	
	public String saveToFile() throws Exception {
		site = siteInfDaoImp.query();
		String dateStr= TimeUtil.createCurrTimestampStr(getText("lerx.default.format.datetime.filename"));
		if (checkAdmin()) {
			BbsStyle curStyle = bbsStyleDaoImp.findById(oid);
			String path = ServletActionContext.getServletContext().getRealPath(
					"");
			path += File.separator + "tmp";
			File filePath = new File(path);
			if (!filePath.exists()) {
				filePath.mkdirs();
			}
			String fileName = "lerxBbsStyleTemplate_"+dateStr+ ".xml";
			Obj.objectXmlEncoder(curStyle, path + File.separator + fileName);
			try {
				FileAction.download(response, path, fileName);
				LogWrite.logWrite(request, "管理员<" + AdminUtil.findCurAdmin(this,SrvInf.obtainHostName(request, site.getHost()),request)
						+ ">下载论坛风格模板id：" + oid + "成功。");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
		}
		return null;
	}

	public String importFromFile() throws Exception {
		site = siteInfDaoImp.query();
		String dateStr= TimeUtil.createCurrTimestampStr(getText("lerx.default.format.datetime.filename"));
		if (checkAdmin()) {
			String path = ServletActionContext.getServletContext().getRealPath(
					"tmp");
			File filePath = new File(path);
			if (!filePath.exists()) {
				filePath.mkdir();
			}
			filePath = new File(filePath.toString() + File.separator
					+ "importtmp.xml");
			if (filePath.exists()) {
				filePath.delete();
			}
			FileInputStream in = new FileInputStream(f.getFile());
			FileOutputStream out = new FileOutputStream(filePath);
			byte[] buffer = new byte[1024];
			int len;
			while ((len = in.read(buffer)) != -1) {
				out.write(buffer, 0, len);
			}
			in.close();
			out.close();

			BbsStyle s = (BbsStyle) Obj.objectXmlDecoder(filePath.toString())
					.get(0);
			s.setStyleName(getText("lerx.importBbsStyleDefaultName")+"_"+dateStr);
			s=styleInit(s);
			if (bbsStyleDaoImp.add(s)) {
				LogWrite.logWrite(request, "管理员<" + AdminUtil.findCurAdmin(this,SrvInf.obtainHostName(request, site.getHost()),request)
						+ ">成功导入一个新的论坛风格模板。");
			} else {
				LogWrite.logWrite(request, "管理员<" + AdminUtil.findCurAdmin(this,SrvInf.obtainHostName(request, site.getHost()),request)
						+ ">导入论坛风格模板失败。");
			}
			// System.out.println("获得的对象："+s.getStyleName());
		}
		return SUCCESS;
	}
	
	private boolean checkAdmin() {
		return AdminUtil
				.checkAdmin(this, getText("lerx.host.current"), request);
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
