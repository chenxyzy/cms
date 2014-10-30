package com.lerx.sys.util;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.servlet.http.HttpServletRequest;

import com.lerx.qa.vo.QaNav;
import com.lerx.site.vo.SiteInfo;
import com.lerx.sys.util.vo.MaiCreateArg;
import com.lerx.sys.util.vo.Mail;
import com.lerx.sys.util.vo.ReadFileArg;
import com.opensymphony.xwork2.ActionSupport;


/**
* Title:
* Description:
* Copyright: Copyright (c) 2001
* Company:www.lerx.com
* @author: lzh
* @version 1.0
*/

public class MailSender {

public MailSender() {
}
	// 邮件发送  
  // String strSubject		-	邮件主题
  // String strBody				-	邮件正文
  // String strFrom				-	发信人
  // String strTo					-	收信人
  // String strSmtpServer	- smtp服务器
  // String strSmtpUser		- smtp服务器认证用户名
  // String strSmtpPwd		- smtp服务器认证用户密码
  // boolean bState				- 发送结果
	


/*
 * 下面的方法已不用，现在使用com.lerx.sys.util.vo.Mail对象的send方法
 */
  public static boolean mailsend(String strSubject,String strBody,String strFrom,String strTo,String strSmtpServer,String strSmtpUser,String strSmtpPwd)
  {
  	boolean bState=true;
		try 
		{
			Properties props=new Properties();
			props.put("mail.smtp.host",strSmtpServer);
			props.put("mail.smtp.auth","true");
			Session s = Session.getDefaultInstance(props);
			s.setDebug(false);

			MimeMessage message = new MimeMessage(s);
			MimeMultipart mp = new MimeMultipart();

			InternetAddress from = new InternetAddress(strFrom);
			message.setFrom(from);

			InternetAddress[] to = new InternetAddress[1];
			to[0] = new InternetAddress(strTo);

			message.setRecipients(Message.RecipientType.TO,to);
			message.setSubject(strSubject,"GB2312");


			BodyPart body = new MimeBodyPart();
			body.setContent(strBody, "text/html;charset=GB2312"); // ！！！注意设置编码
			mp.addBodyPart(body);
			message.setContent(mp);


			message.saveChanges();

			//smtp验证
			Transport transport = s.getTransport("smtp");
			transport.connect(strSmtpServer, strSmtpUser, strSmtpPwd);
			transport.sendMessage(message,message.getAllRecipients());
			transport.close();
			bState=true;
			return bState;
		}
 		catch(Exception exp)
 	 		{
    	bState=false;
    	return bState;
    }
  }
  
  /*
   * 邮件服务初始化
   */
  public static Mail init(SiteInfo site,ActionSupport actionSupport,HttpServletRequest request){
	  //本例的mail无body
	  Mail mail = new Mail();
	  if (site.getMailSmtpServer() != null
				&& !site.getMailSmtpServer().trim().equals("")) {
			mail.getSrv().setSmtpServ(site.getMailSmtpServer());
			mail.getSrv().setFrom(site.getMailSenderAddr());
			mail.getSrv().setSmtpUser(site.getMailSmtpUser());
			mail.getSrv().setSmtpPassword(site.getMailSmtpPws());
			mail.setSubject(site.getMailTitleFromSite());
			mail.setBody(site.getMailBodyForReg());
			
			Mail.setCharset(actionSupport.getText("lerx.charset"));
			
//			 if (site.getMailBodyForReg()!=null && !site.getMailBodyForReg().trim().equals("") ){
////				  body=site.getMailBodyForQaReply();
//				  mail.setBody(site.getMailBodyForReg());
//			  }else{
//				  String body=FileUtil.readConfigFile(actionSupport, request, "mailBodyForReg.txt");
////				  body=as.getText("lerx.mailBody");
//				  mail.setBody(body);
//			  }

		} else {
			mail.getSrv().setSmtpServ(actionSupport.getText("lerx.mailSrv"));
			mail.getSrv().setFrom(actionSupport.getText("lerx.mailSender"));
			mail.getSrv().setSmtpUser(actionSupport.getText("lerx.mailSenderUsername"));
			mail.getSrv().setSmtpPassword(actionSupport.getText("lerx.mailSenderUserPws"));
			mail.setSubject(actionSupport.getText("lerx.mailSubject"));
			mail.setBody(site.getMailBodyForReg());
//			String body=FileUtil.readConfigFile(actionSupport, request, "mailBodyForReg.txt");
//			mail.setBody(body);
		}
		// System.out.println("邮件初始化成功");
	  	
		return mail;
  }
  
  
  public static boolean check(Mail mail){
	  boolean con=true;
	  if (mail.getSrv().getSmtpServ()==null || mail.getSrv().getSmtpServ().trim().equals("lerx.mailSrv")){
	  		con=false;
	  }
	  if (con && (mail.getSrv().getSmtpUser()==null || mail.getSrv().getSmtpUser().trim().equals("lerx.mailSenderUsername"))){
	  		con=false;
	  }
	  if (con && (mail.getSrv().getSmtpPassword()==null || mail.getSrv().getSmtpPassword().trim().equals("lerx.mailSenderUserPws"))){
	  		con=false;
	  }
	  return con;
  }
  
//  public static Mail mailInit(MaiCreateArg mca){
//	  Mail mail = new Mail();
//	  if (site.getMailSmtpServer() != null
//				&& !site.getMailSmtpServer().trim().equals("")) {
//			mail.getSrv().setSmtpServ(site.getMailSmtpServer());
//			mail.getSrv().setFrom(site.getMailSenderAddr());
//			mail.getSrv().setSmtpUser(site.getMailSmtpUser());
//			mail.getSrv().setSmtpPassword(site.getMailSmtpPws());
//			mail.setSubject(site.getMailTitleFromSite());
//			mail.setBody(site.getMailBodyForReg());
//			
//			Mail.setCharset(actionSupport.getText("lerx.charset"));
//			
//			 if (site.getMailBodyForReg()!=null && !site.getMailBodyForReg().trim().equals("") ){
////				  body=site.getMailBodyForQaReply();
//				  mail.setBody(site.getMailBodyForReg());
//			  }else{
//				  
//				  
//				  String body=FileUtil.readConfigFile(actionSupport, request, "mailBodyForReg.txt","mailBody");
////				  body=as.getText("lerx.mailBody");
//				  mail.setBody(body);
//			  }
//
//		} else {
//			mail.getSrv().setSmtpServ(actionSupport.getText("lerx.mailSrv"));
//			mail.getSrv().setFrom(actionSupport.getText("lerx.mailSender"));
//			mail.getSrv().setSmtpUser(actionSupport.getText("lerx.mailSenderUsername"));
//			mail.getSrv().setSmtpPassword(actionSupport.getText("lerx.mailSenderUserPws"));
//			mail.setSubject(actionSupport.getText("lerx.mailSubject"));
//			mail.setBody(site.getMailBodyForReg());
//			String body=FileUtil.readConfigFile(actionSupport, request, "mailBodyForReg.txt","mailBody");
//			mail.setBody(body);
//		}
//		// System.out.println("邮件初始化成功");
//	  
//		return mail;
//	  
//  }
  
  
  
  public static Mail mailInit(MaiCreateArg mca){
	  Mail mail = new Mail();
	  int sta=mca.getSta();
	  SiteInfo site=mca.getSite();
	  HttpServletRequest request=mca.getRequest();
	  QaNav qn=mca.getQn();
	  ActionSupport as=mca.getAs();
	  int mod=mca.getMod();
	  String rootFolder=mca.getRootFolder();
	  String server,from,user,password,subject,body;
	  if (sta==0){
		  if (qn.getServerOfSendEmail()==null || qn.getServerOfSendEmail().trim().equals("")){
			  if (site.getMailSmtpServer()!=null && !site.getMailSmtpServer().trim().equals("")){
				  server=site.getMailSmtpServer();
			  }else{
				  server=as.getText("lerx.mailSrv");
			  }
			  
		  }else{
			  server=qn.getServerOfSendEmail();
		  }
		  
		  if (qn.getSendEmail()==null || qn.getSendEmail().trim().equals("")){
			  if (site.getMailSenderAddr()!=null && !site.getMailSenderAddr().trim().equals("")){
				  from=site.getMailSenderAddr();
			  }else{
				  from=as.getText("lerx.mailSender");
			  }
			  
		  }else{
			  from=qn.getSendEmail();
		  }
		  
		  if (qn.getUsernameOfSendEmail()==null || qn.getUsernameOfSendEmail().trim().equals("")){
			  if (site.getMailSmtpUser()!=null && !site.getMailSmtpUser().trim().equals("")){
				  user=site.getMailSmtpUser();
			  }else{
				  user=as.getText("lerx.mailSenderUsername");
			  }
			  
		  }else{
			  user=qn.getUsernameOfSendEmail();
		  }
		  
		  if (qn.getPasswordOfSendEmail()==null || qn.getPasswordOfSendEmail().trim().equals("")){
			  if (site.getMailSmtpPws()!=null && !site.getMailSmtpPws().trim().equals("")){
				  password=site.getMailSmtpPws();
			  }else{
				  password=as.getText("lerx.mailSenderUserPws");
			  }
			  
		  }else{
			  password=qn.getPasswordOfSendEmail();
		  }
		  
		  if (qn.getSendTitle()==null || qn.getSendTitle().trim().equals("")){
			  if (site.getMailTitleFromSite()!=null && !site.getMailTitleFromSite().trim().equals("")){
				  subject=site.getMailTitleFromSite();
			  }else{
				  subject=as.getText("lerx.mailSubject");
			  }
			  
		  }else{
			  subject=qn.getSendTitle();
		  }
		  
		  if (mod==0){
			  if (qn.getSendTemplateForAdd()==null || qn.getSendTemplateForAdd().trim().equals("")){
				  if (site.getMailBodyForQaAdd()!=null && !site.getMailBodyForQaAdd().trim().equals("")){
					  body=site.getMailBodyForQaAdd();
				  }else{
					  
//						rootFolder=curStyle.getRootFolder();
						ReadFileArg rfv=new ReadFileArg();
						rfv.setAs(as);
						rfv.setRequest(request);
						rfv.setRootFolder(rootFolder);
						rfv.setFileName(mca.getFileName());
						rfv.setSubFolder("mail");
						
						body = FileUtil.readFile(rfv);
					  
//					  body=FileUtil.readConfigFile(as, request, "qaAdd.txt","mailBody");
//					  body=as.getText("lerx.mailBody");
				  }
				  
			  }else{
				  body=qn.getSendTemplateForAdd();
			  }
		  }else{
			  if (qn.getSendTemplateForReply()==null || qn.getSendTemplateForReply().trim().equals("")){
				  if (site.getMailBodyForQaReply()!=null && !site.getMailBodyForQaReply().trim().equals("") ){
					  body=site.getMailBodyForQaReply();
				  }else{
					  ReadFileArg rfv=new ReadFileArg();
						rfv.setAs(as);
						rfv.setRequest(request);
						rfv.setRootFolder(rootFolder);
						rfv.setFileName(mca.getFileName());
						rfv.setSubFolder("mail");
						
						body = FileUtil.readFile(rfv);
//					  body=FileUtil.readConfigFile(as, request, "mailBodyForQaReply.txt","mailBody");
//					  body=as.getText("lerx.mailBody");
				  }
				  
			  }else{
				  body=qn.getSendTemplateForReply();
			  } 
		  }
		  
		  mail.getSrv().setSmtpServ(server);
		  mail.getSrv().setFrom(from);
		  mail.getSrv().setSmtpUser(user);
		  mail.getSrv().setSmtpPassword(password);
		  mail.setSubject(subject);
		  mail.setBody(body);
	  }else{
		  if (site.getMailSmtpServer() != null
					&& !site.getMailSmtpServer().trim().equals("")) {
				mail.getSrv().setSmtpServ(site.getMailSmtpServer());
				mail.getSrv().setFrom(site.getMailSenderAddr());
				mail.getSrv().setSmtpUser(site.getMailSmtpUser());
				mail.getSrv().setSmtpPassword(site.getMailSmtpPws());
				mail.setSubject(site.getMailTitleFromSite());
				mail.setBody(site.getMailBodyForReg());
				
//				Mail.setCharset(as.getText("lerx.charset"));
				
				 if (site.getMailBodyForReg()!=null && !site.getMailBodyForReg().trim().equals("") ){
//					  body=site.getMailBodyForQaReply();
					  mail.setBody(site.getMailBodyForReg());
				  }else{
					  

						ReadFileArg rfv=new ReadFileArg();
						rfv.setAs(as);
						rfv.setRequest(request);
						rfv.setRootFolder(rootFolder);
						rfv.setFileName(mca.getFileName());
						rfv.setSubFolder("mail");
						
						body = FileUtil.readFile(rfv);
					  body=FileUtil.readFile(rfv);
//					  body=as.getText("lerx.mailBody");
					  mail.setBody(body);
				  }

			} else {
				mail.getSrv().setSmtpServ(as.getText("lerx.mailSrv"));
				mail.getSrv().setFrom(as.getText("lerx.mailSender"));
				mail.getSrv().setSmtpUser(as.getText("lerx.mailSenderUsername"));
				mail.getSrv().setSmtpPassword(as.getText("lerx.mailSenderUserPws"));
				mail.setSubject(as.getText("lerx.mailSubject"));
				mail.setBody(site.getMailBodyForReg());
				body=FileUtil.readConfigFile(as, request, "mailBodyForReg.txt","mailBody");
				mail.setBody(body);
			}
	  }
	  
	  
	  
	  
	  
	  
	  
	  
	  Mail.setCharset(as.getText("lerx.charset"));

		return mail;
	  
  }
}






