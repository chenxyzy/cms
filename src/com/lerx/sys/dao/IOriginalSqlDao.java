package com.lerx.sys.dao;

import java.util.List;

import com.lerx.sys.util.vo.MetaDataModel;

public interface IOriginalSqlDao {
	public List<MetaDataModel> getMetaData(int id,String table);
	public boolean executeSql(String sql);
	public boolean executeUpdateSql(String sql);
	public String strSQL(String sql);

}
