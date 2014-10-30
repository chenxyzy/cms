package com.lerx.sys.util;

import org.apache.log4j.Logger;

import com.lerx.sys.dao.ICustomLogLevel;

public class CustomLogger {
	private Logger log;
	public void custom(Object pm_objLogInfo){

		log=Logger.getLogger(getClass());
		
		try {
			log.log(ICustomLogLevel.CUSTOM_LEVEL,pm_objLogInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
