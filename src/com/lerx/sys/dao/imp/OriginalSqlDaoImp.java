package com.lerx.sys.dao.imp;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lerx.sys.dao.IOriginalSqlDao;
import com.lerx.sys.util.vo.MetaDataModel;

public class OriginalSqlDaoImp extends HibernateDaoSupport implements IOriginalSqlDao {

	public List<MetaDataModel> getMetaData(int id,String table) {

		String sql = "select * from "+table+" where id=" + id;
		
		//System.out.println("sql测试："+sql);
		 List<MetaDataModel> metaData=new ArrayList<MetaDataModel>();
		//site_sitestyle
		try {

			java.sql.Connection c = null;
			c = SessionFactoryUtils.getDataSource(getSessionFactory())
					.getConnection();
			java.sql.Statement st = c.createStatement();
			java.sql.ResultSet rs = st.executeQuery(sql);

			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
			int colCount = rsmd.getColumnCount();
			//System.out.println("列数：" + colCount);

			for (int i = 1; i <= colCount; i++) {
				if (rsmd.getColumnType(i)==12 || rsmd.getColumnType(i)==-1) {
					MetaDataModel m=new MetaDataModel();
					m.setColName(rsmd.getColumnName(i));
					m.setColType(rsmd.getColumnType(i));
					m.setId(i);
					metaData.add(m);
				}
				
				//System.out.println("列名：" + rsmd.getColumnName(i) + "类型："+ rsmd.getColumnType(i));
			}

			rs.close();
			st.close();
			c.close();
			return metaData;
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean executeSql(String sql) {
		
		try {
			java.sql.Connection c = null;
			c = SessionFactoryUtils.getDataSource(getSessionFactory())
					.getConnection();
			java.sql.Statement st = c.createStatement();
			st.execute(sql);
			//st.execute(sql);
			st.close();
			c.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}

	@Override
	public String strSQL(String sql) {
		String s=null;
		try {
			
			java.sql.Connection c = null;
			c = SessionFactoryUtils.getDataSource(getSessionFactory())
					.getConnection();
			java.sql.Statement st = c.createStatement();
			//System.out.println("正在执行查询操作1:"+sql);
			java.sql.ResultSet rs = st.executeQuery(sql);
			if (rs.next()){
				s=rs.getString(1);
			}
			
			rs.close();
			st.close();
			c.close();
			return s;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}

	@Override
	public boolean executeUpdateSql(String sql) {
		try {
			java.sql.Connection c = null;
			c = SessionFactoryUtils.getDataSource(getSessionFactory())
					.getConnection();
			java.sql.Statement st = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
			 
			//System.out.println("正在执行更新操作:"+sql);
			st.executeUpdate(sql);
			//System.out.println("更新操作结束:"+sql);
			
			//st.execute(sql);
			st.close();
			c.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
