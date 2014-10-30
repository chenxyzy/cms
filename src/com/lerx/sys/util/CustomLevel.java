package com.lerx.sys.util;

import org.apache.log4j.Level;

public class CustomLevel extends Level {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CustomLevel(int level, String name, int sysLogLevel) {
         super(level, name, sysLogLevel);
    }

}
