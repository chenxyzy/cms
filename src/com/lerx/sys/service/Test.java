package com.lerx.sys.service;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletResponseAware;
import com.lerx.sys.util.StringUtil;
import com.lerx.sys.util.SysUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @description
 * @author li
 * @date 2009-11-10
 * @version 1.0.0
 * @since 1.0
 */
public class Test extends ActionSupport implements ServletResponseAware
 {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletResponse response;


	public static boolean result;
	
//	private static  NetMsgclient netMsgclient;
//	private static  Receive receive;

	/**
	 * @description
	 * @author li
	 * @date 2009-11-10
	 * @version 1.0.0
	 * @param args
	 * @throws IOException 
	 * @throws SocketException
	 */
public void t() throws IOException{
	long v=5;
	String secStr=StringUtil.md5(StringUtil.uuidStr());
	System.out.println("uuid:"+secStr);
	long e=SysUtil.covValue(v, secStr);
	System.out.println("数值："+v+"被加密成："+e);
	System.out.println("数值："+e+"被解密成："+SysUtil.reCovValue(e, secStr));
	
//	
//	String a=RobotAway.createJS();
//	System.out.println(a);
//	System.out.println(RobotAway.getCheckFunction());
	response.getWriter().write("aaaaa");
}
//	public static void send() throws SocketException {
////		System.gc();
//		NetMsgclient client = netMsgclient;
//		boolean isLogin;
//		
//		/* ReceiveMsgImpl为ReceiveMsg类的子类，构造时，构造自己继承的子类就行 */
//		ReceiveMsg receiveMsg = receive;
//		
//		/* 初始化参数 */
//		if (client.socket==null || !client.socket.isConnected()){
//			client = client.initParameters("202.X.41.X", 9005, "X",
//					"X", receiveMsg);
//			try {
//				isLogin = client.anthenMsg(client);
//			} catch (IOException e) {
//				isLogin=false;
//			}
//		}else{
//			isLogin=true;
//		}
//		String m;
//		for (int i = 0; i < 3; i++) {
//			
//			if (isLogin) {
//				System.out.println("----5----");
//				System.out.println("测试点5");
//				System.out.println("----5----");
//				m = client.sendMsg(client, 0, "13852665280", "hello world!", 1);
//			}
//
//		}
//	}



@Override
public void setServletResponse(HttpServletResponse response) {
	this.response=response;
	
}

//	public void setNetMsgclient(NetMsgclient netMsgclient) {
//		this.netMsgclient = netMsgclient;
//	}
//
//	public void setReceive(Receive receive) {
//		this.receive = receive;
//	}

}
