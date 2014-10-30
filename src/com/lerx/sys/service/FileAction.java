package com.lerx.sys.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.simple.JSONObject;

import com.lerx.site.dao.ISiteInfoDao;
import com.lerx.site.vo.SiteInfo;
import com.lerx.sys.util.CdmUtil;
import com.lerx.sys.util.CookieUtil;
import com.lerx.sys.util.SrvInf;
import com.lerx.sys.util.TimeUtil;
import com.lerx.sys.util.vo.CookieDoModel;
import com.lerx.sys.util.vo.FileV;
import com.lerx.sys.util.vo.UserCookie;
import com.opensymphony.xwork2.ActionSupport;

import com.lerx.sys.util.StringUtil;
import com.lerx.user.dao.IInterconnectionDao;
import com.lerx.user.dao.IUserDao;

public class FileAction extends ActionSupport implements ServletResponseAware,
		ServletRequestAware {

	/**
	 * http://www.lerx.com
	 */
	private static final long serialVersionUID = 1L;
	private FileV f;
	private String col;
	private HttpServletResponse response;
	private HttpServletRequest request;
	private ISiteInfoDao siteInfDaoImp;
	private IUserDao userDaoImp;
	private IInterconnectionDao interconnectionDaoImp;
	private File imgFile;
	private CookieDoModel cdm;

	public void setUserDaoImp(IUserDao userDaoImp) {
		this.userDaoImp = userDaoImp;
	}

	public void setInterconnectionDaoImp(IInterconnectionDao interconnectionDaoImp) {
		this.interconnectionDaoImp = interconnectionDaoImp;
	}

	public File getImgFile() {
		return imgFile;
	}

	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}

	public void setSiteInfDaoImp(ISiteInfoDao siteInfDaoImp) {
		this.siteInfDaoImp = siteInfDaoImp;
	}

	public String getCol() {
		return col;
	}

	public void setCol(String col) {
		this.col = col;
	}

	public FileV getF() {
		return f;
	}

	public void setF(FileV f) {
		this.f = f;
	}

	public static boolean download(HttpServletResponse response, String path,
			String filename) {
		try {

			String charset = "uft-8";

			filename = new String(filename.getBytes("gb2312"), "ISO8859-1");

			OutputStream o = response.getOutputStream();
			BufferedOutputStream bos = new BufferedOutputStream(o);
			// 输出文件用的字节数组,每次发送500个字节到输出流：
			byte b[] = new byte[500];
			// 下载的文件：
			File fileLoad = new File(path, filename);
			// 客户使用保存文件的对话框：
			response.setCharacterEncoding(charset);
			response.setHeader("Content-type", "text/html;charset=" + charset);
			response.setHeader("Content-disposition", "attachment;filename="
					+ filename);
			// 通知客户文件的MIME类型：
			response.setContentType("application/x-tar");
			// 通知客户文件的长度：
			long fileLength = fileLoad.length();
			String length = String.valueOf(fileLength);
			response.setHeader("Content_Length", length);
			// 读取文件,并发送给客户下载:
			FileInputStream in = new FileInputStream(fileLoad);
			int n = 0;
			while ((n = in.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			in.close();
			bos.flush();
			bos.close();
			o.flush();
			o.close();

			return true;
		} catch (Exception exp) {

			return false;
		}
	}

	@SuppressWarnings("unchecked")
	public void uploadForKE() throws Exception {

		String url, msg = "";
		MultiPartRequestWrapper req = (MultiPartRequestWrapper) request;
		Enumeration<?> e = req.getFileParameterNames();
		JSONObject obj = new JSONObject();
		response.setCharacterEncoding(getText("lerx.charset"));
		response.setContentType("text/html;charset=" + getText("lerx.charset"));
		boolean con = false;
		File upFile = null;
		while (e.hasMoreElements()) {
			String key = (String) e.nextElement();
			upFile = req.getFiles(key)[0];
			String fileName = req.getFileNames(key)[0];
			long fileSize = req.getFiles(key)[0].length();

			String extType;
			int p = fileName.lastIndexOf(".");
			if (p > 0) {
				extType = fileName.substring(p + 1, fileName.length());
			} else {
				extType = "";
			}
			extType = extType.toLowerCase();

			String pre = "lerx.uploadMaxKbSize." + extType;
			String returnText = getText(pre);
			if (returnText.trim().equals(pre) || returnText.trim().equals("")) {
				returnText = getText("lerx.uploadMaxKbSize.default");
			}
			long maxSize = Integer.valueOf(returnText);

			// 检查扩展名
			SiteInfo site = siteInfDaoImp.query();
			String allExtType = site.getFileUploadExtName();
			if (allExtType == null || allExtType.trim().equals("")) {
				allExtType = getText("lerx.uploadTypeAllow.default");
			}
			String[] typeArray = allExtType.split(",");
			for (int step = 0; step < typeArray.length; step++) {
				if (typeArray[step].trim().equalsIgnoreCase(extType)) {
					con = true;
					break;
				}
			}
			if (con) {
				// 检查文件大小
				if (maxSize != 0 && fileSize > maxSize * 1000) {
					msg = getText("lerx.fail.upload.tooBig") + maxSize + "KB";
					con = false;
				} else {
					con = true;
				}
			} else {
				msg = getText("lerx.uploadFailForExtType");
			}

			if (con) {
				url = getText("lerx.uploadPath");
				String newFileName;

				String path = ServletActionContext.getServletContext()
						.getRealPath(url);
				cdm =CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);

				UserCookie uc = CookieUtil.query(cdm);
				File filePath = new File(path);
				if (!filePath.exists()) {
					filePath.mkdir();
				}
				filePath = new File(filePath.toString()
						+ File.separator
						+ TimeUtil.getDateVar(
								(java.sql.Date) new java.sql.Date(System
										.currentTimeMillis()), 2));
				url += File.separator
						+ TimeUtil.getDateVar(
								(java.sql.Date) new java.sql.Date(System
										.currentTimeMillis()), 2);
				if (!filePath.exists()) {
					filePath.mkdir();
				}
				filePath = new File(filePath.toString()
						+ File.separator
						+ TimeUtil.getDateVar(
								(java.sql.Date) new java.sql.Date(System
										.currentTimeMillis()), 1));
				url += File.separator
						+ TimeUtil.getDateVar(
								(java.sql.Date) new java.sql.Date(System
										.currentTimeMillis()), 1);
				if (!filePath.exists()) {
					filePath.mkdir();
				}
				filePath = new File(filePath.toString()
						+ File.separator
						+ TimeUtil.getDateVar(
								(java.sql.Date) new java.sql.Date(System
										.currentTimeMillis()), 0));
				url += File.separator
						+ TimeUtil.getDateVar(
								(java.sql.Date) new java.sql.Date(System
										.currentTimeMillis()), 0);
				if (!filePath.exists()) {
					filePath.mkdir();
				}
				newFileName = TimeUtil.createCurrTimestampStr("HHmmssSSS");

				if (uc != null && uc.getUsername() != null
						&& !uc.getUsername().trim().equals("")) {

					newFileName += "_" + uc.getUserId();

					String file = filePath.toString() + File.separator
							+ newFileName + "." + extType;
					url += File.separator + newFileName + "." + extType;

					FileInputStream in = new FileInputStream(upFile);
					FileOutputStream out = new FileOutputStream(file);
					byte[] buffer = new byte[1024];
					int len;
					while ((len = in.read(buffer)) != -1) {
						out.write(buffer, 0, len);
					}
					in.close();
					out.close();
					if (File.separator.trim().equals("\\")) {
						url = StringUtil.strReplace(url, "\\", "/");
					}
					String ymp,cookieDomain= cdm.getActionSupport().getText("lerx.cookieDomain");
					
					if (cookieDomain!=null && !cookieDomain.trim().equals("") && !cookieDomain.trim().equalsIgnoreCase("null")){
						url = SrvInf.srvUrl(request, "",
								Integer.valueOf(getText("lerx.serverPort"))) + "/" + url;
						ymp="<script type=\"text/javascript\">try"+"\n"+"{document.domain = \""+cookieDomain+"\";}catch(e){}</script>";
					}else{
						url = request.getContextPath() + "/" + url;
						ymp="";
					}
					
					
					boolean flashPost;
					String postReferer=request.getHeader("referer");
					if (postReferer==null || postReferer.trim().length()<1){	//检查来源页，如果没有就是flash上传
						flashPost=true;
					}else{
						flashPost=false;
					}
					obj.put("error", 0);
					obj.put("url", url);
					String outStr,objstr;
					if (flashPost){
						outStr=obj.toJSONString();
					}else{
						
						
						objstr="<div id=\"lerxjson\">"+obj.toJSONString()+"</div>";
						
						outStr="<html><body>"+ymp+objstr+"</body></html>";
					}
					
					response.getWriter().write(outStr);
				}

			} else {

				response.getWriter().write(getError(obj, msg));
			}
		}

	}

	public String upload() throws Exception {
		String url;
		boolean con = false;
		// MultiPartRequestWrapper wrapper = (MultiPartRequestWrapper) request;
		// if (imgFile!=null){
		// f.setFile(imgFile);
		// f.setFileFileName(imgFile.getName());
		// }
		if (f!=null){
			String oldFname = f.getFileFileName();

			// System.out.println(f.getFile().length());

			String extType;
			int p = oldFname.lastIndexOf(".");
			if (p > 0) {
				extType = oldFname.substring(p + 1, oldFname.length());
			} else {
				extType = "";
			}
			extType = extType.toLowerCase();
			String pre = "lerx.uploadMaxKbSize." + extType;
			String returnText = getText(pre);
			if (returnText.trim().equals(pre) || returnText.trim().equals("")) {
				returnText = getText("lerx.uploadMaxKbSize.default");
			}
			long maxSize = Integer.valueOf(returnText);
			// fileSize=
			if (maxSize != 0 && f.getFile().length() > maxSize * 1000) {
				request.setAttribute("UploadMsg", getText("lerx.fail.upload.tooBig")
						+ maxSize + "KB");
			} else {
				SiteInfo site = siteInfDaoImp.query();
				String allExtType = site.getFileUploadExtName();
				if (allExtType == null || allExtType.trim().equals("")) {
					allExtType = getText("lerx.uploadTypeAllow.default");
				}
				String[] typeArray = allExtType.split(",");
				for (int step = 0; step < typeArray.length; step++) {
					if (typeArray[step].trim().equalsIgnoreCase(extType)) {
						con = true;
						break;
					}
				}

				if (con) {
					try {
						if (extType != null && !extType.trim().equals("")) {
							extType = "." + extType;
						}
						url = getText("lerx.uploadPath");
						String newFileName;

						String path = ServletActionContext.getServletContext()
								.getRealPath(url);
						cdm =CdmUtil.init(this, request, response, userDaoImp,interconnectionDaoImp);
						// CookieDoModel cdm=new CookieDoModel();
						// cdm.setActionSupport(this);
						// cdm.setEncodingCode(getText("lerx.charset").trim());
						// cdm.setPrefix(getText("lerx.prefixOfCookieForLogin"));
						// cdm.setHost(getText("lerx.host.current"));
						// cdm.setHostSecFile(getText("lerx.hostSecFile"));
						// cdm.setResponse(response);
						// cdm.setRequest(request);

						UserCookie uc = CookieUtil.query(cdm);
						File filePath = new File(path);
						if (!filePath.exists()) {
							filePath.mkdir();
						}
						filePath = new File(filePath.toString()
								+ File.separator
								+ TimeUtil.getDateVar(
										(java.sql.Date) new java.sql.Date(System
												.currentTimeMillis()), 2));
						url += File.separator
								+ TimeUtil.getDateVar(
										(java.sql.Date) new java.sql.Date(System
												.currentTimeMillis()), 2);
						if (!filePath.exists()) {
							filePath.mkdir();
						}
						filePath = new File(filePath.toString()
								+ File.separator
								+ TimeUtil.getDateVar(
										(java.sql.Date) new java.sql.Date(System
												.currentTimeMillis()), 1));
						url += File.separator
								+ TimeUtil.getDateVar(
										(java.sql.Date) new java.sql.Date(System
												.currentTimeMillis()), 1);
						if (!filePath.exists()) {
							filePath.mkdir();
						}
						filePath = new File(filePath.toString()
								+ File.separator
								+ TimeUtil.getDateVar(
										(java.sql.Date) new java.sql.Date(System
												.currentTimeMillis()), 0));
						url += File.separator
								+ TimeUtil.getDateVar(
										(java.sql.Date) new java.sql.Date(System
												.currentTimeMillis()), 0);
						if (!filePath.exists()) {
							filePath.mkdir();
						}
						newFileName = TimeUtil.createCurrTimestampStr("HHmmssSSS");

						if (uc != null && uc.getUsername() != null
								&& !uc.getUsername().trim().equals("")) {
							// newFileName += uc.getUsername();
							newFileName += "_" + uc.getUserId();

							String file = filePath.toString() + File.separator
									+ newFileName + extType;
							url += File.separator + newFileName + extType;
							// System.out.println("新的文件：" + file);
							FileInputStream in = new FileInputStream(f.getFile());
							FileOutputStream out = new FileOutputStream(file);
							byte[] buffer = new byte[1024];
							int len;
							while ((len = in.read(buffer)) != -1) {
								out.write(buffer, 0, len);
							}
							in.close();
							out.close();
							if (File.separator.trim().equals("\\")) {
								url = StringUtil.strReplace(url, "\\", "/");
							}
							response.setCharacterEncoding(getText("lerx.charset"));
							String cookieDomain= cdm.getActionSupport().getText("lerx.cookieDomain");
							
							if (cookieDomain!=null && !cookieDomain.trim().equals("") && !cookieDomain.trim().equalsIgnoreCase("null")){
								url = SrvInf.srvUrl(request, "",
										Integer.valueOf(getText("lerx.serverPort"))) + "/" + url;
							}else{
								url = request.getContextPath() + "/" + url;
							}
							


							request.setAttribute("FileUrl", url);
							request.setAttribute("col", col);
							request.setAttribute("fileSize", ""
									+ f.getFile().length());
							request.setAttribute("UploadMsg",
									getText("lerx.success.upload"));
						} else {
							request.setAttribute(
									"UploadMsg",
									getText("lerx.fail.upload.auth"));
						}
					} catch (Exception e) {
						request.setAttribute("UploadMsg",
								getText("lerx.fail.upload.all"));
						// return INPUT;
					}
				} else {
					request.setAttribute("UploadMsg",
							getText("lerx.fail.upload.extType"));
				}
			}
		}else{
			request.setAttribute("UploadMsg",
					getText("lerx.fail.upload.noFile"));
		}
		

		return SUCCESS;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;

	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@SuppressWarnings("unchecked")
	private String getError(JSONObject obj, String message) {
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
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

}
