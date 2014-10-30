package com.lerx.code.dao.imp;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lerx.code.dao.ICodeCategoryDao;
import com.lerx.code.vo.CodeCategory;

public class CodeCategoryDaoImp extends HibernateDaoSupport implements
		ICodeCategoryDao {

	@Override
	public int add(CodeCategory cc) {
		this.getHibernateTemplate().save(cc);
		return cc.getId();
	}

	@Override
	public boolean modify(CodeCategory cc) {
		this.getHibernateTemplate().saveOrUpdate(cc);
		return false;
	}

	@Override
	public boolean delById(int id) {
		this.getHibernateTemplate().delete(
				this.getHibernateTemplate().get(
						"com.lerx.code.vo.CodeCategory", id));
		return true;
	}

	@Override
	public CodeCategory findById(int id) {
		// TODO Auto-generated method stub
		return 
		(CodeCategory) this.getHibernateTemplate().get(
				"com.lerx.code.vo.CodeCategory", id);
	}

	@Override
	public List<CodeCategory> queryAll() {
		List<CodeCategory> list = (List<CodeCategory>)this.getHibernateTemplate().loadAll(CodeCategory.class);
		// TODO Auto-generated method stub
		return list;
		
	}

}
