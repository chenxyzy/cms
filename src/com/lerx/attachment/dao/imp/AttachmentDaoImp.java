package com.lerx.attachment.dao.imp;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lerx.attachment.dao.IAttachmentDao;
import com.lerx.attachment.vo.Attachment;

public class AttachmentDaoImp extends HibernateDaoSupport implements
		IAttachmentDao {

	@Override
	public boolean add(Attachment atta) {
		try {
			this.getHibernateTemplate().save(atta);
			return true;
		} catch (RuntimeException re) {
			throw re;
		}
		
	}

	@Override
	public List<Attachment> findByHostId(long hid) {
		
		String hql="from Attachment a where a.hostId="+hid+" order by a.orderNum asc,a.id asc";
		@SuppressWarnings("unchecked")
		List<Attachment> list = this.getHibernateTemplate().find(hql);
		return list;
	}

	@Override
	public boolean del(long id) {
		this.getHibernateTemplate().delete(
				this.getHibernateTemplate().get(
						"com.lerx.attachment.vo.Attachment", id));
		return true;
	}

	@Override
	public boolean modify(Attachment atta) {
		this.getHibernateTemplate().saveOrUpdate(atta);
		return true;
	}

	@Override
	public Attachment find(long id) {
		return	(Attachment) this.getHibernateTemplate().get(
				"com.lerx.attachment.vo.Attachment", id);
	}

}
