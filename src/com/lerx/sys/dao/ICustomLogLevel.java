package com.lerx.sys.dao;

import org.apache.log4j.Level;
import org.apache.log4j.Priority;
import org.apache.log4j.net.SyslogAppender;

import com.lerx.sys.util.CustomLevel;

public interface ICustomLogLevel {
	public static final Level CUSTOM_LEVEL = new CustomLevel(Priority.FATAL_INT - 50, "CUSTOM", SyslogAppender.LOG_LOCAL0);

}
