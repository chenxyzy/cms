package com.lerx.qa.dao;

import java.util.List;

import com.lerx.qa.vo.QaItem;
import com.lerx.sys.util.vo.Rs;

public interface IQaItemDao {
	public long add(QaItem qi);

	public boolean del(QaItem qi);

	public boolean delById(long qid);

	public boolean modify(QaItem qi);

	public QaItem findById(long qid);

	public Rs findQaItemsByGroupAndMod(int navId, int page,
			int pageSize, int mod, int state,int open, int firstResult);

	public boolean findQaItemByIpAndSaltOnSameDay(String ip, String salt);

	public Rs findQaItemsByParentAndMod(int navId, int page, int pageSize, int mod,
			int state, int firstResult);
	public List<Long> findAllID();
}
