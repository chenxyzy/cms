package com.lerx.sys.service;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.lerx.admin.util.AdminUtil;
import com.lerx.site.dao.ISiteInfoDao;
import com.lerx.sys.dao.IOriginalSqlDao;
import com.lerx.sys.util.LogWrite;
import com.opensymphony.xwork2.ActionSupport;

public class StyleSqlAction extends ActionSupport implements
		ServletRequestAware {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	HttpServletRequest request;
	
	private int id;
	private String table;
	private String col;
	private String styleName;
	private String colValue;
	private IOriginalSqlDao originalSqlDaoImp;
	private int styleID;
	private ISiteInfoDao siteInfDaoImp;
	public void setSiteInfDaoImp(ISiteInfoDao siteInfDaoImp) {
		this.siteInfDaoImp = siteInfDaoImp;
	}

	public int getStyleID() {
		return styleID;
	}

	public void setStyleID(int styleID) {
		this.styleID = styleID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

	public String getStyleName() {
		return styleName;
	}

	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getCol() {
		return col;
	}

	public void setCol(String col) {
		this.col = col;
	}
	
	public String getColValue() {
		return colValue;
	}

	public void setColValue(String colValue) {
		this.colValue = colValue;
	}

	public void setOriginalSqlDaoImp(IOriginalSqlDao originalSqlDaoImp) {
		this.originalSqlDaoImp = originalSqlDaoImp;
	}
	
	public String find(){
		
		
		String sql = "select " + col + " from " + table + " where id="+id; 
		String r;
		
		r=originalSqlDaoImp.strSQL(sql);
		if (r!=null) {
			r=r.replaceAll("<textarea>", "&lt;textarea&gt;");
			r=r.replaceAll("</textarea>", "&lt;/textarea&gt;");
		}
		
		request.setAttribute("returnStrFromSql", r);
		request.setAttribute("col", col);
		request.setAttribute("table", table);
		request.setAttribute("styleID", styleID);
		
		
		
		return SUCCESS;
	}
	
	public String modify(){
		siteInfDaoImp.query();
		if (checkAdmin()){
			colValue=colValue.replaceAll("'", "''");
			String sql = "update " + table + " set " + col +"='" + colValue + "' where id="+id; 
			originalSqlDaoImp.executeUpdateSql(sql);
			LogWrite.logWrite(request, "修改table："+table+"."+col+"成功。");
			request.setAttribute("resultAltStr", getText("lerx.success.all"));
			return SUCCESS;
		}else{
			return INPUT;
		}
		
	}
	
	private boolean checkAdmin() {
		return AdminUtil
				.checkAdmin(this, getText("lerx.host.current"), request);
	}
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;

	}

}
