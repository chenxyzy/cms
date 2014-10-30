package com.lerx.web.ajax.service;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.lerx.attachment.dao.IAttachmentDao;
import com.lerx.attachment.util.AttaUtil;
import com.lerx.attachment.vo.Attachment;
import com.lerx.sys.dao.IExternalHostCharsetDao;
import com.lerx.sys.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;

public class AttachmentAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware {
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletResponse response;
	private IAttachmentDao attachmentDaoImp;
	private IExternalHostCharsetDao externalHostCharsetDaoImp;
	private String encryptedParmStr;
	private long id;
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setAttachmentDaoImp(IAttachmentDao attachmentDaoImp) {
		this.attachmentDaoImp = attachmentDaoImp;
	}
	
	public void setExternalHostCharsetDaoImp(
			IExternalHostCharsetDao externalHostCharsetDaoImp) {
		this.externalHostCharsetDaoImp = externalHostCharsetDaoImp;
	}

	public String getEncryptedParmStr() {
		return encryptedParmStr;
	}

	public void setEncryptedParmStr(String encryptedParmStr) {
		this.encryptedParmStr = encryptedParmStr;
	}

	public void encryptedParmStr() throws IOException{
		Attachment atta=attachmentDaoImp.find(id);
		String encryptedParmStr=StringUtil.md5(StringUtil.randomString(64));
		atta.setEncryptedParmStr(encryptedParmStr);
		attachmentDaoImp.modify(atta);
		response.setCharacterEncoding(getText("lerx.charset"));
		response.setContentType("text/html;charset="+getText("lerx.charset"));
		response.getWriter().write(encryptedParmStr);
	}
	
	public void mediaUrl() throws IOException{
		boolean con;
		String mediaUrl=null;
		Attachment atta=attachmentDaoImp.find(id);
		if (atta.getEncryptedParmStr()!=null && atta.getEncryptedParmStr().trim().equals(encryptedParmStr)){
			mediaUrl=atta.getUrl();
			
			if (mediaUrl==null || mediaUrl.trim().equals("")) {
				con=false;
			}else{
				con=true;
			}
		}else{
			con=false;
		}
		
		atta.setEncryptedParmStr(null);
		attachmentDaoImp.modify(atta);
		response.setCharacterEncoding(getText("lerx.charset"));
		response.setContentType("text/html;charset="+getText("lerx.charset"));
		
		if (con && mediaUrl!=null){
			mediaUrl=AttaUtil.encoder(externalHostCharsetDaoImp,this, mediaUrl,1);
//			mediaUrl=StringUtil.urlEncoder(mediaUrl, getText("lerx.attaURLEncoderCharset"));
			response.getWriter().write(mediaUrl);
		}else{
			response.getWriter().write("null");
		}
	}
	
	public void url() throws IOException{
		Attachment atta=attachmentDaoImp.find(id);
		response.setCharacterEncoding(getText("lerx.charset"));
		response.setContentType("text/html;charset="+getText("lerx.charset"));
		String mediaUrl=atta.getUrl();
		mediaUrl=AttaUtil.encoder(externalHostCharsetDaoImp,this, mediaUrl,1);
//		mediaUrl=StringUtil.urlEncoder(mediaUrl, getText("lerx.attaURLEncoderCharset"));
		response.getWriter().write(mediaUrl);
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;

	}

	@Override
	public void setServletRequest(HttpServletRequest request) {

	}

}
