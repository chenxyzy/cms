package com.lerx.sys.util;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Obj implements  Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void objectXmlEncoder(Object obj,String fileName) throws FileNotFoundException,IOException,Exception{
		File fo=new File(fileName);
//		if (!fo.exists()){
//			String path=fileName.substring(0,fileName.lastIndexOf(File.separatorChar));
//			File pFile=new File(path);
//			pFile.mkdirs();
//		}
		if (fo.exists()){
			fo.delete();
		}
		FileOutputStream fos=new FileOutputStream(fo);
		XMLEncoder encoder=new XMLEncoder(fos);
		encoder.writeObject(obj);
		encoder.flush();
		encoder.close();
		fos.flush();
		fos.close();
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List objectXmlDecoder(String objSource) throws FileNotFoundException,IOException,Exception{
		List objList=new ArrayList();
		File fin=new File(objSource);
		FileInputStream fis=new FileInputStream(fin);
		XMLDecoder decoder=new XMLDecoder(fis);
		Object obj=null;
		try{
			while((obj=decoder.readObject())!=null){
				objList.add(obj);
			}
		}catch(Exception e){
			
		}
		fis.close();
		decoder.close();
		return objList;
	}
	
	public static Object copy(Object oldObj)  {   

	    Object obj = null;   

	    
	    try {   
	        ByteArrayOutputStream bos = new ByteArrayOutputStream();   

	        ObjectOutputStream out = new ObjectOutputStream(bos);   

	        out.writeObject(oldObj);   

	        out.flush();   

	        out.close();   

	        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());    

	        ObjectInputStream in = new ObjectInputStream(bis);   

	        obj = in.readObject();   

	    } catch (IOException e) {   

	        e.printStackTrace();   

	    } catch (ClassNotFoundException cnfe) {   

	        cnfe.printStackTrace();   

	    }   

	    return obj;   

	}  

	
}
