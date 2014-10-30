package com.lerx.unicode.dao.imp;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lerx.unicode.dao.ChineseCharacterUnicodeDao;
import com.lerx.unicode.vo.ChineseCharacter;

public class ChineseCharacterUnicodeDaoImp extends HibernateDaoSupport
		implements ChineseCharacterUnicodeDao {

	@Override
	public ChineseCharacter findById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int findOrderByChar(char cc) {
		
		String hql = "from ChineseCharacter c where c.cc=?";

		@SuppressWarnings("unchecked")
		List<ChineseCharacter> list = this.getHibernateTemplate().find(hql, cc);

		if (list.isEmpty()) {
			return 0;
		} else {
			ChineseCharacter cu=list.get(0);
			return cu.getId();
		}
		
	}

}
