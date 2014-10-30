package com.lerx.sys.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import com.lerx.sys.util.vo.PropertiesFileInf;

public class PropertiesUtil {
	
	private static Properties props = new Properties(); 


	

	public static void updateProperties(PropertiesFileInf pf,String keyname, String keyvalue) throws FileNotFoundException, IOException {
		String profile;
//		String filepath=System.getProperty("user.dir");
//		String profilepath = "mail.properties";
		switch (pf.getFile()) {
		case 1:
			profile="resourcesMessage_"+pf.getLocal();
			break;
		case 2:
			profile="resourcesStyle_"+pf.getLocal();
			break;
		case 3:
			profile="resourcesUploadFileSize_"+pf.getLocal();
			break;
		case 4:
			profile="resourcesHostInf_"+pf.getLocal();
			break;
		default:
			profile="resourcesApplication_"+pf.getLocal();
			break;

		}
		
		profile+=".properties";
		profile=pf.getPath()+File.separator+"WEB-INF"+File.separator+"classes"+File.separator+profile;
		props.load(new FileInputStream(profile)); 
//		System.out.println("profile:"+profile);
		try {
			props.load(new FileInputStream(profile));
			// 调用 Hashtable 的方法 put，使用 getProperty 方法提供并行性。
			// 强制要求为属性的键和值使用字符串。返回值是 Hashtable 调用 put 的结果。
			OutputStream fos = new FileOutputStream(profile);
			props.setProperty(keyname, keyvalue);
			// 以适合使用 load 方法加载到 Properties 表中的格式，
			// 将此 Properties 表中的属性列表（键和元素对）写入输出流
			props.store(fos, null);
			
		} catch (IOException e) {
//			System.err.println("属性文件更新错误");
		}
	}

}
