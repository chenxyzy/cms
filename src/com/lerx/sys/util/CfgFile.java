package com.lerx.sys.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


public class CfgFile {
    private Properties property;
    private FileInputStream inFile;
    private FileOutputStream outFile;
    public boolean HandleFileNoExist(String FilePath) {
        File f = new File(FilePath);
        if (!f.exists()){// 如果不存在配置文件，写入默认配置
//            setValue("ip", "127.0.0.1");
//            setValue("port", "6789");
//            saveCfg(FilePath, desc);
        	return false;
        }
        return true;
    }
    
    public CfgFile(String FilePath) {
        property = new Properties();
        try {
            HandleFileNoExist(FilePath);
            inFile = new FileInputStream(FilePath);
            property.load(inFile);
            inFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("配置文件不存在");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void saveCfg(String FileName, String description){
        try {
            outFile = new FileOutputStream(FileName);
            property.store(outFile, description);
            outFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getValue(String Key){
        if (property.containsKey(Key)){
            return property.getProperty(Key);
        }else{
            return "null";
        }
    }
    
    public void setValue(String Key, String Value){
        property.setProperty(Key, Value);
    }
    
    public static String read(String fileName,String key) {
    	CfgFile cf = new CfgFile(fileName);
        String value = cf.getValue(key);
//        cf.finalize();
//        cf=null;
        
        return value;
    }
    
    
//    public static void main(String[] args) {
//        // 读取配置文件
//        CfgFile cf = new CfgFile("./config.property", "config");
//        String ip = cf.getValue("ip");
//        System.out.println(ip);
//        String port = cf.getValue("port");
//        System.out.println(port);
//        // 修改保存配置文件
//        cf.setValue("ip", "192.198.1.237");
//        cf.saveCfg("./config.property", "config");
//        System.out.println(cf.getValue("ip"));
//        
//    }

}
 
