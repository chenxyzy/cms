package com.lerx.sys.util.vo;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Mail {
	public static String subject;
	public static String body;
	public static String toMail;
	public static String mailType;
	public static String charset;
	public static MailSmtpSrv srv;

	public Mail() {
		Mail.srv = new MailSmtpSrv();
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		Mail.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		Mail.body = body;
	}

	public String getToMail() {
		return toMail;
	}

	public void setToMail(String toMail) {
		Mail.toMail = toMail;
	}

	public static String getMailType() {
		return mailType;
	}

	public static void setMailType(String mailType) {
		Mail.mailType = mailType;
	}

	public static String getCharset() {
		return charset;
	}

	public static void setCharset(String charset) {
		Mail.charset = charset;
	}

	public MailSmtpSrv getSrv() {
		return srv;
	}

	public void setSrv(MailSmtpSrv srv) {
		Mail.srv = srv;
	}

	public static boolean send() {
		boolean bState = true;
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", srv.getSmtpServ());
			props.put("mail.smtp.auth", "true");
			Session s = Session.getDefaultInstance(props);
			s.setDebug(false);

			MimeMessage message = new MimeMessage(s);
			MimeMultipart mp = new MimeMultipart();

			InternetAddress from = new InternetAddress(srv.getFrom());
			message.setFrom(from);

			InternetAddress[] to = new InternetAddress[1];
			to[0] = new InternetAddress(toMail);
			message.setRecipients(Message.RecipientType.TO, to);
			message.setSubject(subject, charset);

			BodyPart html = new MimeBodyPart();
			
			if (charset == null) {
				charset = "UTF-8";
			}
			
			if (mailType == null) {
				mailType = "text/html";
			}
			
			html.setContent(body, mailType + ";charset=" + charset); // ！！！注意设置编码
			mp.addBodyPart(html);
			message.setContent(mp);

			message.saveChanges();

			// smtp验证
			Transport transport = s.getTransport("smtp");
			transport.connect(srv.getSmtpServ(), srv.getSmtpUser(),
					srv.getSmtpPassword());
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			bState = true;
			return bState;

		} catch (Exception exp) {
			bState = false;
			return bState;
		}
	}

}
