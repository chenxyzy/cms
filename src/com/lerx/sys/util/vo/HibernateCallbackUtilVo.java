package com.lerx.sys.util.vo;

import org.springframework.orm.hibernate3.HibernateTemplate;

public class HibernateCallbackUtilVo {
	
	private int page;
	private int pageSize;
	private int firstResult;
	private String hql;
	private HibernateTemplate ibernateTemplate;
	private long count;
	
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getFirstResult() {
		return firstResult;
	}
	public void setFirstResult(int firstResult) {
		this.firstResult = firstResult;
	}
	public String getHql() {
		return hql;
	}
	public void setHql(String hql) {
		this.hql = hql;
	}
	public HibernateTemplate getIbernateTemplate() {
		return ibernateTemplate;
	}
	public void setIbernateTemplate(HibernateTemplate ibernateTemplate) {
		this.ibernateTemplate = ibernateTemplate;
	}
	public long getCount() {
		return count;
	}
	public void setCount(long count) {
		this.count = count;
	}
	
}
