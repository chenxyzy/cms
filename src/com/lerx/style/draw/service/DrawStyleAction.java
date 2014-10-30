package com.lerx.style.draw.service;

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
import com.lerx.style.draw.dao.IDrawStyleDao;
import com.lerx.style.draw.vo.DrawStyle;
import com.lerx.sys.service.FileAction;
import com.lerx.sys.util.LogWrite;
import com.lerx.sys.util.Obj;
import com.lerx.sys.util.SrvInf;
import com.lerx.sys.util.TimeUtil;
import com.lerx.sys.util.vo.FileV;
import com.opensymphony.xwork2.ActionSupport;

public class DrawStyleAction extends ActionSupport implements
ServletRequestAware, ServletResponseAware{

	
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
	private DrawStyle ds;
	private ISiteInfoDao siteInfDaoImp;
	private IDrawStyleDao drawStyleDaoImp;
	private String curModel;
	private List<DrawStyle> list;
	
	public DrawStyle getDs() {
		return ds;
	}
	public void setDs(DrawStyle ds) {
		this.ds = ds;
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
	public int getMod() {
		return mod;
	}
	public void setMod(int mod) {
		this.mod = mod;
	}
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public FileV getF() {
		return f;
	}
	public void setF(FileV f) {
		this.f = f;
	}
	public String getNewStyleName() {
		return newStyleName;
	}
	public void setNewStyleName(String newStyleName) {
		this.newStyleName = newStyleName;
	}
	public boolean isState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public String getCurModel() {
		return curModel;
	}
	public void setCurModel(String curModel) {
		this.curModel = curModel;
	}
	public void setSiteInfDaoImp(ISiteInfoDao siteInfDaoImp) {
		this.siteInfDaoImp = siteInfDaoImp;
	}
	public void setDrawStyleDaoImp(IDrawStyleDao drawStyleDaoImp) {
		this.drawStyleDaoImp = drawStyleDaoImp;
	}
	
	
	public List<DrawStyle> getList() {
		return list;
	}
	public void setList(List<DrawStyle> list) {
		this.list = list;
	}
	public String query() throws Exception {

		list = drawStyleDaoImp.findAll(mod);

		request.setAttribute("drawStyleAll", list);
		return SUCCESS;
	}
	
	public String add() throws Exception{
		if (checkAdmin()) {
			if (drawStyleDaoImp.findStyleByName(ds.getStyleName())){
				this.addActionError(getText("lerx.fail.exists.all"));
				query();
				return INPUT;
			}else{
				ds.setState(false);
//				qs=styleInit(qs);
				drawStyleDaoImp.add(ds);
				query();
				SiteInfo site=siteInfDaoImp.query();
				LogWrite.logWrite(request,
						"管理员<" + AdminUtil.findCurAdmin(this,SrvInf.obtainHostName(request, site.getHost()), request)
								+ ">增加抽奖系统风格模板：" + ds.getStyleName() + "成功。");
				return SUCCESS;
			}
		} else {
			return INPUT;
		}
		
	}
	
	public String del() throws Exception {
		if (checkAdmin()) {
			if (drawStyleDaoImp.delById(id)) {
				query();
				SiteInfo site=siteInfDaoImp.query();
				request.setAttribute("resultAltStr",
						getText("lerx.success.all"));
				LogWrite.logWrite(request, "管理员<" + AdminUtil.findCurAdmin(this,SrvInf.obtainHostName(request, site.getHost()),request)
						+ ">删除抽奖系统风格模板id：" + id + "成功。");
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
		SiteInfo site = siteInfDaoImp.query();
		if (checkAdmin()) {
			DrawStyle s = (DrawStyle) drawStyleDaoImp.findById(id);
			DrawStyle d = (DrawStyle) s.clone();
			d.setId(null);
			d.setStyleName(newStyleName);
			drawStyleDaoImp.add(d);
			LogWrite.logWrite(request,
					"管理员<" + AdminUtil.findCurAdmin(this,SrvInf.obtainHostName(request, site.getHost()), request)
							+ ">复制抽奖系统风格模板：" + newStyleName + "成功。");
			query();
			return SUCCESS;
		} else {
			return INPUT;
		}

	}
	
	
	public String setDefault() throws Exception {
//		SiteInfo  site = siteInfDaoImp.query();
		if (checkAdmin()) {
			drawStyleDaoImp.setDefault(id);
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
			DrawStyle curStyle = drawStyleDaoImp.findById(oid);
			String path = ServletActionContext.getServletContext().getRealPath(
					"");
			path += File.separator + "tmp";
			File filePath = new File(path);
			if (!filePath.exists()) {
				filePath.mkdirs();
			}
			String fileName = "lerxDrawStyleTemplate_"+dateStr+ ".xml";
			Obj.objectXmlEncoder(curStyle, path + File.separator + fileName);
			try {
				FileAction.download(response, path, fileName);
				LogWrite.logWrite(request, "管理员<" + AdminUtil.findCurAdmin(this,SrvInf.obtainHostName(request, site.getHost()),request)
						+ ">下载抽奖系统风格模板id：" + oid + "成功。");
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

			DrawStyle s = (DrawStyle) Obj.objectXmlDecoder(filePath.toString())
					.get(0);
			s.setStyleName(getText("lerx.importDrawStyleDefaultName")+"_"+dateStr);
			s.setId(null);
			s.setState(false);
			if (drawStyleDaoImp.imp(s)>0) {
				LogWrite.logWrite(request, "管理员<" + AdminUtil.findCurAdmin(this,SrvInf.obtainHostName(request, site.getHost()),request)
						+ ">成功导入一个新的抽奖系统风格模板。");
			} else {
				LogWrite.logWrite(request, "管理员<" + AdminUtil.findCurAdmin(this,SrvInf.obtainHostName(request, site.getHost()),request)
						+ ">导入抽奖系统风格模板失败。");
			}
			// System.out.println("获得的对象："+s.getStyleName());
		}
		return SUCCESS;
	}
	
	public String findNameById() throws Exception {

		String styleName = drawStyleDaoImp.findById(styleID)
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
