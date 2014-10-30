package com.lerx.style.vote.service;

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
import com.lerx.style.vote.dao.IVoteStyleDao;
import com.lerx.style.vote.vo.VoteStyle;
import com.lerx.sys.service.FileAction;
import com.lerx.sys.util.LogWrite;
import com.lerx.sys.util.Obj;
import com.lerx.sys.util.SrvInf;
import com.lerx.sys.util.TimeUtil;
import com.lerx.sys.util.vo.FileV;
import com.opensymphony.xwork2.ActionSupport;

public class VoteStyleAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private VoteStyle vs;
	private int id;
	private int mod;
	private int oid;
	private FileV f;
	private int styleID;
	private String curModel;
	private IVoteStyleDao voteStyleDaoImp;
	private ISiteInfoDao siteInfDaoImp;
	private List<VoteStyle> list;
	private String newStyleName;

	public VoteStyle getVs() {
		return vs;
	}

	public void setVs(VoteStyle vs) {
		this.vs = vs;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getStyleID() {
		return styleID;
	}

	public void setStyleID(int styleID) {
		this.styleID = styleID;
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

	public void setVoteStyleDaoImp(IVoteStyleDao voteStyleDaoImp) {
		this.voteStyleDaoImp = voteStyleDaoImp;
	}

	public void setSiteInfDaoImp(ISiteInfoDao siteInfDaoImp) {
		this.siteInfDaoImp = siteInfDaoImp;
	}

	private VoteStyle styleInit(VoteStyle d){
		d.setId(null);
		d.getPublicStyle().setId(null);
		d.getJoinStyle().setId(null);
		d.getItemStyle().setId(null);
		d.getResultStyle().setId(null);
		d.getVoteStyle().setId(null);
		return d;
	}
	
	public String add(){
		if (checkAdmin()) {
			if (voteStyleDaoImp.findStyleByName(vs.getTitle())){
				this.addActionError(getText("lerx.fail.exists.all"));
				query();
				return INPUT;
			}else{
				vs.setState(false);
				
				voteStyleDaoImp.add(vs,true);
				SiteInfo site=siteInfDaoImp.query();
				LogWrite.logWrite(request,
						"管理员<" + AdminUtil.findCurAdmin(this,SrvInf.obtainHostName(request, site.getHost()), request)
								+ ">增加投票系统风格模板：" + vs.getTitle() + "成功。");
				return SUCCESS;
			}
			
		}else{
			return INPUT;
		}
		
		
	}

	public String query(){
		list = voteStyleDaoImp.findAll(mod);

		request.setAttribute("voteStyleAll", list);
		return SUCCESS;
	}
	
	public String del() throws Exception {
		if (checkAdmin()) {
			if (voteStyleDaoImp.delById(id)) {
				query();
				SiteInfo site=siteInfDaoImp.query();
				request.setAttribute("resultAltStr",
						getText("lerx.success.all"));
				LogWrite.logWrite(request, "管理员<" + AdminUtil.findCurAdmin(this,SrvInf.obtainHostName(request, site.getHost()),request)
						+ ">删除投票系统风格模板id：" + id + "成功。");
				return SUCCESS;
			} else {
				this.addActionError(getText("lerx.err.db"));
				query();
				return INPUT;
			}
		}else {
			query();
			return INPUT;
		}
	}
	
	public String copy() throws Exception {
		SiteInfo site = siteInfDaoImp.query();
		if (checkAdmin()) {
			VoteStyle s = (VoteStyle) voteStyleDaoImp.findById(id);
//			if (s==null){
//				System.out.println("为空找不到对象啊 id:"+id);
//			}
			VoteStyle d = (VoteStyle) s.clone();
			
			d=styleInit(d);
//			System.out.println("getHtmlTemplate:"+d.getPublicStyle().getHtmlTemplate());
			
			d.setTitle(newStyleName);
			voteStyleDaoImp.add(d,false);
			LogWrite.logWrite(request,
					"管理员<" + AdminUtil.findCurAdmin(this,SrvInf.obtainHostName(request, site.getHost()), request)
							+ ">复制投票系统风格模板：" + newStyleName + "成功。");
			query();
			return SUCCESS;
		} else {
			return INPUT;
		}

	}
	
	public String setDefault() throws Exception {
//		SiteInfo  site = siteInfDaoImp.query();
		if (checkAdmin()) {
			voteStyleDaoImp.setDefault(id);
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
			VoteStyle curStyle = voteStyleDaoImp.findById(oid);
			String path = ServletActionContext.getServletContext().getRealPath(
					"");
			path += File.separator + "tmp";
			File filePath = new File(path);
			if (!filePath.exists()) {
				filePath.mkdirs();
			}
			String fileName = "lerxVoteStyleTemplate_"+dateStr+ ".xml";
			Obj.objectXmlEncoder(curStyle, path + File.separator + fileName);
			try {
				FileAction.download(response, path, fileName);
				LogWrite.logWrite(request, "管理员<" + AdminUtil.findCurAdmin(this,SrvInf.obtainHostName(request, site.getHost()),request)
						+ ">下载投票系统风格模板id：" + oid + "成功。");
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

			VoteStyle s = (VoteStyle) Obj.objectXmlDecoder(filePath.toString())
					.get(0);
			s.setTitle(getText("lerx.importVoteStyleDefaultName")+"_"+dateStr);
			s=styleInit(s);
			s.setId(null);
			s.setState(false);
			if (voteStyleDaoImp.imp(s)>0) {
				LogWrite.logWrite(request, "管理员<" + AdminUtil.findCurAdmin(this,SrvInf.obtainHostName(request, site.getHost()),request)
						+ ">成功导入一个新的投票系统风格模板。");
			} else {
				LogWrite.logWrite(request, "管理员<" + AdminUtil.findCurAdmin(this,SrvInf.obtainHostName(request, site.getHost()),request)
						+ ">导入投票系统风格模板失败。");
			}
			// System.out.println("获得的对象："+s.getStyleName());
		}
		return SUCCESS;
	}
	
	public String findNameById() throws Exception {

		String styleName = voteStyleDaoImp.findById(styleID)
				.getTitle();
		request.setCharacterEncoding(getText("lerx.charset"));

		request.setAttribute("currentStyleName", styleName);
		request.setAttribute("currentModelName", "--" + curModel + "模块--");
		return SUCCESS;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
		
	}

	private boolean checkAdmin() {
		return AdminUtil
				.checkAdmin(this, getText("lerx.host.current"), request);
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
		
	}

}
