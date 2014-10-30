package com.lerx.style.site.service;

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
import com.lerx.style.site.dao.ISiteStyleDao;
import com.lerx.style.site.vo.SiteStyle;
import com.lerx.sys.service.FileAction;
import com.lerx.sys.util.LogWrite;
import com.lerx.sys.util.Obj;
import com.lerx.sys.util.SrvInf;
import com.lerx.sys.util.TimeUtil;
import com.lerx.sys.util.vo.FileV;
import com.opensymphony.xwork2.ActionSupport;

public class SiteStyleAction extends ActionSupport implements
		ServletResponseAware, ServletRequestAware {
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
	private List<SiteStyle> list;
	private ISiteStyleDao siteStyleDaoImp;
	private String curModel;
	private String newStyleName;
	private SiteInfo site;
	private ISiteInfoDao siteInfDaoImp;
	
	

	public void setSiteInfDaoImp(ISiteInfoDao siteInfDaoImp) {
		this.siteInfDaoImp = siteInfDaoImp;
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

	public int getMod() {
		return mod;
	}

	public void setMod(int mod) {
		this.mod = mod;
	}

	public String getNewStyleName() {
		return newStyleName;
	}

	public void setNewStyleName(String newStyleName) {
		this.newStyleName = newStyleName;
	}

	public String getCurModel() {
		return curModel;
	}

	public void setCurModel(String curModel) {
		this.curModel = curModel;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStyleID() {
		return styleID;
	}

	public void setStyleID(int styleID) {
		this.styleID = styleID;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setSiteStyleDaoImp(ISiteStyleDao siteStyleDaoImp) {
		this.siteStyleDaoImp = siteStyleDaoImp;
	}
	private SiteStyle styleInit(SiteStyle d){
		d.setId(null);
		d.getClassStyle().setId(null);
		d.getArticleAddStyle().setId(null);
		d.getArticleEditStyle().setId(null);
		d.getGenericStyle().setId(null);
		d.getIndexStyle().setId(null);
		d.getLoginStyle().setId(null);
		d.getSearchStyle().setId(null);
		d.getPublicStyle().setId(null);
		d.getRegStyle().setId(null);
		d.getThreadStyle().setId(null);
		d.getUserCenterStyle().setId(null);
		d.getCommentStyle().setId(null);
		return d;
	}

	
	public String copy() throws Exception {
		site = siteInfDaoImp.query();
		if (checkAdmin()) {
			SiteStyle s = (SiteStyle) siteStyleDaoImp.findStyleById(id);
			SiteStyle d = (SiteStyle) s.clone();
			d=styleInit(d);
			d.setStyleName(newStyleName);
			d.setDef(false);
			siteStyleDaoImp.add(d);
			LogWrite.logWrite(request, "管理员<" + AdminUtil.findCurAdmin(this,SrvInf.obtainHostName(request, site.getHost()),request) + ">复制门户风格模板："
					+ newStyleName + "成功。");
			query();
			return SUCCESS;
		} else {
			return INPUT;
		}

	}

	public String add() throws Exception {
		site = siteInfDaoImp.query();
		if (checkAdmin()) {
			if (siteStyleDaoImp.findStyleByName(styleName)) {
				this.addActionError(getText("lerx.fail.exists.all"));
				query();
				return INPUT;
			} else {
				siteStyleDaoImp.addStyle(styleName, author, description);
				query();
				LogWrite.logWrite(request, "管理员<" + AdminUtil.findCurAdmin(this,SrvInf.obtainHostName(request, site.getHost()),request) + ">增加门户风格模板："
						+ styleName + "成功。");
				return SUCCESS;
			}
		} else {
			return INPUT;
		}

	}

	public String query() throws Exception {
		// if (!StringUtil.isNumber(mod)){
		// mod=1;
		// }
		// System.out.println("----mod:"+mod);
		list = siteStyleDaoImp.findAll(mod);

		request.setAttribute("siteStyleAll", list);
		return SUCCESS;
	}

	public String findNameById() throws Exception {

		String styleName = siteStyleDaoImp.findStyleById(styleID)
				.getStyleName();
		request.setCharacterEncoding(getText("lerx.charset"));
		// curModel=new
		// String(curModel.getBytes("ISO-8859-1"),getText("lerx.charset"));
		request.setAttribute("currentStyleName", styleName);
		request.setAttribute("currentModelName", "--" + curModel + "模块--");
		return SUCCESS;
	}

	public String del() throws Exception {
		site = siteInfDaoImp.query();
		if (checkAdmin()) {
			if (siteStyleDaoImp.delStyleById(id)) {
				query();
				request.setAttribute("resultAltStr",
						getText("lerx.success.all"));
				LogWrite.logWrite(request, "管理员<" + AdminUtil.findCurAdmin(this,SrvInf.obtainHostName(request, site.getHost()),request)
						+ ">删除门户风格模板id：" + id + "成功。");
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
			siteStyleDaoImp.changeState(id, state);
			query();
			return SUCCESS;
		} else {
			return INPUT;
		}

	}
	
	public String setDefault() throws Exception{
		if (checkAdmin()) {
			siteStyleDaoImp.setDef(id);
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
			SiteStyle curStyle = siteStyleDaoImp.findStyleById(oid);
			String path = ServletActionContext.getServletContext().getRealPath(
					"");
			path += File.separator + "tmp";
			File filePath = new File(path);
			if (!filePath.exists()) {
				filePath.mkdirs();
			}
			String fileName = "lerxSiteStyleTemplate_"+dateStr + ".xml";
			Obj.objectXmlEncoder(curStyle, path + File.separator + fileName);
			try {
				FileAction.download(response, path, fileName);
				LogWrite.logWrite(request, "管理员<" + AdminUtil.findCurAdmin(this,SrvInf.obtainHostName(request, site.getHost()),request)
						+ ">下载门户风格模板id：" + oid + "成功。");
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

			SiteStyle s = (SiteStyle) Obj.objectXmlDecoder(filePath.toString())
					.get(0);
			s.setStyleName(getText("lerx.importSiteStyleDefaultName")+"_"+dateStr);
			s=styleInit(s);
			s.setDef(false);
//			System.out.println("测试1");
			if (siteStyleDaoImp.add(s)) {
//				System.out.println("测试2");
				LogWrite.logWrite(request, "管理员<" + AdminUtil.findCurAdmin(this,SrvInf.obtainHostName(request, site.getHost()),request)
						+ ">成功导入一个新的门户风格模板。");
			} else {
//				System.out.println("测试3");
				LogWrite.logWrite(request, "管理员<" + AdminUtil.findCurAdmin(this,SrvInf.obtainHostName(request, site.getHost()),request)
						+ ">导入门户风格模板失败。");
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
