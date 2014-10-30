package com.lerx.style.qa.service;

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
import com.lerx.style.qa.dao.IQaStyleDao;
import com.lerx.style.qa.vo.QaStyle;
import com.lerx.sys.service.FileAction;
import com.lerx.sys.util.LogWrite;
import com.lerx.sys.util.Obj;
import com.lerx.sys.util.SrvInf;
import com.lerx.sys.util.TimeUtil;
import com.lerx.sys.util.vo.FileV;
import com.opensymphony.xwork2.ActionSupport;

public class QaStyleAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private int id;
	private int styleID;
	private int mod;
	private int oid;
	private FileV f;
	private String newStyleName;
	private boolean state;
	private QaStyle qs;
	private ISiteInfoDao siteInfDaoImp;
	private IQaStyleDao qaStyleDaoImp;
	private String curModel;
	
	
	private List<QaStyle> list;
	
	
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
	
	
	public FileV getF() {
		return f;
	}

	public void setF(FileV f) {
		this.f = f;
	}

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public List<QaStyle> getList() {
		return list;
	}

	public void setList(List<QaStyle> list) {
		this.list = list;
	}

	public void setQaStyleDaoImp(IQaStyleDao qaStyleDaoImp) {
		this.qaStyleDaoImp = qaStyleDaoImp;
	}

	public void setSiteInfDaoImp(ISiteInfoDao siteInfDaoImp) {
		this.siteInfDaoImp = siteInfDaoImp;
	}

	public QaStyle getQs() {
		return qs;
	}

	public void setQs(QaStyle qs) {
		this.qs = qs;
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
	
	
	private QaStyle styleInit(QaStyle d){
		d.setId(null);
		d.getPublicStyle().setId(null);
		d.getIndexStyle().setId(null);
		d.getNavStyle().setId(null);
		d.getItemStyle().setId(null);
		return d;
	}
	

	public String query() throws Exception {

		list = qaStyleDaoImp.findAll(mod);

		request.setAttribute("qaStyleAll", list);
		return SUCCESS;
	}
	
	public String add() throws Exception{
		if (checkAdmin()) {
			if (qaStyleDaoImp.findStyleByName(qs.getStyleName())){
				this.addActionError(getText("lerx.fail.exists.all"));
				query();
				return INPUT;
			}else{
				qs.setState(false);
//				qs=styleInit(qs);
				qaStyleDaoImp.add(qs,true);
				query();
				SiteInfo site=siteInfDaoImp.query();
				LogWrite.logWrite(request,
						"管理员<" + AdminUtil.findCurAdmin(this,SrvInf.obtainHostName(request, site.getHost()), request)
								+ ">增加问答系统风格模板：" + qs.getStyleName() + "成功。");
				return SUCCESS;
			}
		} else {
			return INPUT;
		}
		
	}

	public String del() throws Exception {
		if (checkAdmin()) {
			if (qaStyleDaoImp.delById(id)) {
				query();
				SiteInfo site=siteInfDaoImp.query();
				request.setAttribute("resultAltStr",
						getText("lerx.success.all"));
				LogWrite.logWrite(request, "管理员<" + AdminUtil.findCurAdmin(this,SrvInf.obtainHostName(request, site.getHost()),request)
						+ ">删除问答系统风格模板id：" + id + "成功。");
				return SUCCESS;
			} else {
				this.addActionError(getText("lerx.err.db"));
				query();
				return INPUT;
			}
		}else {
			return INPUT;
		}
	}
	
	public String copy() throws Exception {
//		System.out.println("aaabbbbb");
		SiteInfo site = siteInfDaoImp.query();
		if (checkAdmin()) {
			QaStyle s = (QaStyle) qaStyleDaoImp.findById(id);
			QaStyle d = (QaStyle) s.clone();
			d.setId(null);
			d=styleInit(d);
			d.setStyleName(newStyleName);
			qaStyleDaoImp.add(d,false);
			LogWrite.logWrite(request,
					"管理员<" + AdminUtil.findCurAdmin(this,SrvInf.obtainHostName(request, site.getHost()), request)
							+ ">复制问答系统风格模板：" + newStyleName + "成功。");
			query();
			return SUCCESS;
		} else {
			return INPUT;
		}

	}
	
	public String setDefault() throws Exception {
//		SiteInfo  site = siteInfDaoImp.query();
		if (checkAdmin()) {
			qaStyleDaoImp.setDefault(id);
			query();
			return SUCCESS;
		} else {
			return INPUT;
		}

	}
	
	public String saveToFile() throws Exception {
		SiteInfo site = siteInfDaoImp.query();
		String dateStr= TimeUtil.createCurrTimestampStr(getText("lerx.default.format.datetime.filename"));
		if (checkAdmin()) {
			QaStyle curStyle = qaStyleDaoImp.findById(oid);
			String path = ServletActionContext.getServletContext().getRealPath(
					"");
			path += File.separator + "tmp";
			File filePath = new File(path);
			if (!filePath.exists()) {
				filePath.mkdirs();
			}
			String fileName = "lerxQaStyleTemplate_"+dateStr+ ".xml";
			Obj.objectXmlEncoder(curStyle, path + File.separator + fileName);
			try {
				FileAction.download(response, path, fileName);
				LogWrite.logWrite(request, "管理员<" + AdminUtil.findCurAdmin(this,SrvInf.obtainHostName(request, site.getHost()),request)
						+ ">下载问答系统风格模板id：" + oid + "成功。");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
		}
		return null;
	}

	public String importFromFile() throws Exception {
		SiteInfo site = siteInfDaoImp.query();
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

			QaStyle s = (QaStyle) Obj.objectXmlDecoder(filePath.toString())
					.get(0);
			s.setStyleName(getText("lerx.importQaStyleDefaultName")+"_"+dateStr);
			s=styleInit(s);
//			System.out.println("s.getPublicStyle().getCssCode()"+s.getPublicStyle().getCssCode());
//			s.setId(null);
			s.setState(false);
			if (qaStyleDaoImp.imp(s)>0) {
				LogWrite.logWrite(request, "管理员<" + AdminUtil.findCurAdmin(this,SrvInf.obtainHostName(request, site.getHost()),request)
						+ ">成功导入一个新的问答系统风格模板。");
			} else {
				LogWrite.logWrite(request, "管理员<" + AdminUtil.findCurAdmin(this,SrvInf.obtainHostName(request, site.getHost()),request)
						+ ">导入问答系统风格模板失败。");
			}
			// System.out.println("获得的对象："+s.getStyleName());
		}
		return SUCCESS;
	}
	
	public String findNameById() throws Exception {

		String styleName = qaStyleDaoImp.findById(styleID)
				.getStyleName();
		request.setCharacterEncoding(getText("lerx.charset"));

		request.setAttribute("currentStyleName", styleName);
		request.setAttribute("currentModelName", "--" + curModel + "模块--");
		return SUCCESS;
	}
	
	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
		
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
		
	}
	
	private boolean checkAdmin() {
		return AdminUtil
				.checkAdmin(this, getText("lerx.host.current"), request);
	}

}
