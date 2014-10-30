package com.lerx.comment.dao.imp;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.lerx.comment.dao.ICommentDao;
import com.lerx.comment.vo.Comment;
import com.lerx.sys.util.HibernateCallbackUtil;
import com.lerx.sys.util.vo.HibernateCallbackUtilVo;
import com.lerx.sys.util.vo.Rs;

public class CommentDaoImp extends HibernateDaoSupport implements ICommentDao {

	@Override
	public boolean addComment(Comment comment) {
		this.getHibernateTemplate().save(comment);
		return true;
	}

	@Override
	public boolean delComment(Comment comment) {
		this.getHibernateTemplate().delete(comment);
		return true;
	}

	@Override
	public boolean delCommentById(long id) {
		// System.out.println("删 除评论测试点1:"+id);
		this.getHibernateTemplate().delete(
				this.getHibernateTemplate().get("com.lerx.comment.vo.Comment",
						id));
		// System.out.println("删 除评论测试点2:"+id);
		return true;
	}

	@Override
	public Comment queryById(long id) {
		return (Comment) this.getHibernateTemplate().get(
				"com.lerx.comment.vo.Comment", id);
	}

	@Override
	public Rs queryByThreadId(long threadId, int page,
			int pageSize, int stateMod) {

		String hql, hqlAdd;
		switch (stateMod) {
		case 1:
			hqlAdd = "";
			break;
		case 2:
			hqlAdd = " and c.state=false ";
			break;
		default:
			hqlAdd = " and c.state=true ";
			break;
		}

		hql = "from Comment c where c.thread.id=" + threadId + hqlAdd;
		long count;
		count = (Long) this
				.getHibernateTemplate()
				.find("select count(*) from Comment c where c.thread.id="
						+ threadId + hqlAdd).get(0);
//		Rs rs = RsInit.rsInit(page, pageSize, count);
		hql += " order by c.addTime desc ";
		HibernateCallbackUtilVo hcuv = new HibernateCallbackUtilVo();
		hcuv.setHql(hql);
		hcuv.setIbernateTemplate(this.getHibernateTemplate());
		hcuv.setPageSize(pageSize);
		hcuv.setCount(count);
		hcuv.setPage(page);
		Rs rs =  HibernateCallbackUtil.exeRs(hcuv);
		
//		HibernateCallback hc = new HibernateCallback() {
//
//			@SuppressWarnings("unchecked")
//			@Override
//			public Object doInHibernate(org.hibernate.Session session)
//					throws HibernateException, SQLException {
//				int n = pageSize;
//				if (n == 0) {
//					n = 10;
//				}
//				Query query = session.createQuery(hqlCmd);
//				query.setFirstResult((page - 1) * pageSize);
//				query.setMaxResults(n);
//
//				List<Comment> list = query.list();
//
//				if (list.isEmpty()) {
//					// System.out.println("找不到记录");
//				}
//				// System.out.println("测试点5");
//				return list;
//			}
//
//		};
//		rs.setList(getHibernateTemplate().executeFind(hc));
		return rs;
	}

	@Override
	public boolean changeCommentStateById(long id) {
		Comment c = (Comment) this.getHibernateTemplate().get(
				"com.lerx.comment.vo.Comment", id);
		if (c.isState()) {
			c.setState(false);
		} else {
			c.setState(true);
		}

		this.getHibernateTemplate().saveOrUpdate(c);
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delCommentByThreadId(long id) {
		this.getHibernateTemplate().bulkUpdate(
				"delete from Comment c where c.thread.id=" + id);
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Rs queryByGroupId(long groupId, int page, int pageSize) {
		String hql;
		if (groupId == 0) {
			hql = "from Comment c";
		} else {
			hql = "from Comment c where c.thread.articleGroup.id=" + groupId;
		}

		long count;
		count = (Long) this.getHibernateTemplate()
				.find("select count(*) " + hql).get(0);
		hql += " order by c.addTime desc ";
		
		HibernateCallbackUtilVo hcuv = new HibernateCallbackUtilVo();
		hcuv.setHql(hql);
		hcuv.setIbernateTemplate(this.getHibernateTemplate());
		hcuv.setPageSize(pageSize);
		hcuv.setCount(count);
		hcuv.setPage(page);
		Rs rs =  HibernateCallbackUtil.exeRs(hcuv);
		
//		HibernateCallback hc = new HibernateCallback() {
//
//			@SuppressWarnings("unchecked")
//			@Override
//			public Object doInHibernate(org.hibernate.Session session)
//					throws HibernateException, SQLException {
//				int n = pageSize;
//				if (n == 0) {
//					n = 10;
//				}
//				Query query = session.createQuery(hqlCmd);
//				query.setFirstResult((page - 1) * pageSize);
//				query.setMaxResults(n);
//
//				List<Comment> list = query.list();
//
//				if (list.isEmpty()) {
//					// System.out.println("找不到记录");
//				}
//				// System.out.println("测试点5");
//				return list;
//			}
//
//		};
//		rs.setList(getHibernateTemplate().executeFind(hc));
		return rs;
	}

	@Override
	public long count(long tid, int mod) {
		String hqlAdd;
		switch (mod) {
		case 1:
			hqlAdd="c.state is not true and";
			break;
		case 2:
			hqlAdd="";
			break;
		default:
			hqlAdd="c.state is true and";
			break;
		}
		String hql = "from Comment c where "+hqlAdd+" c.thread.id="
				+ tid;
		long count = (Long) this.getHibernateTemplate()
				.find("select count(*) " + hql).get(0);
		return count;
	}

}
