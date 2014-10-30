package com.lerx.tools.vo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;



public class Conn {
	
	private String driver;
	private String dbFile;
	private String url;
	private String server;
	private String port;
	private String db;
	private String user;
	private String password;
	private String table;
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	private ResultSetMetaData rsmd;
	private int colCount;
	private long recCount;
	private String charSet;
	private int dbType;

	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getDb() {
		return db;
	}
	public void setDb(String db) {
		this.db = db;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDbFile() {
		return dbFile;
	}
	public void setDbFile(String dbFile) {
		this.dbFile = dbFile;
	}
	public String getTable() {
		return table;
	}
	public void setTable(String table) {
		this.table = table;
	}
	public Connection getConn() {
		return conn;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	public Statement getSt() {
		return st;
	}
	public void setSt(Statement st) {
		this.st = st;
	}
	public ResultSet getRs() {
		return rs;
	}
	public void setRs(ResultSet rs) {
		this.rs = rs;
	}
	public ResultSetMetaData getRsmd() {
		return rsmd;
	}
	public void setRsmd(ResultSetMetaData rsmd) {
		this.rsmd = rsmd;
	}
	public int getColCount() {
		return colCount;
	}
	public void setColCount(int colCount) {
		this.colCount = colCount;
	}
	public long getRecCount() {
		return recCount;
	}
	public void setRecCount(long recCount) {
		this.recCount = recCount;
	}
	public String getCharSet() {
		return charSet;
	}
	public void setCharSet(String charSet) {
		this.charSet = charSet;
	}
	public int getDbType() {
		return dbType;
	}
	public void setDbType(int dbType) {
		this.dbType = dbType;
	}
	
	
}
