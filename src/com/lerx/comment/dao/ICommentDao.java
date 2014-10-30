package com.lerx.comment.dao;

import com.lerx.comment.vo.Comment;
import com.lerx.sys.util.vo.Rs;

public interface ICommentDao {
	public boolean addComment(Comment comment);
	public boolean delComment(Comment comment);
	public boolean delCommentById(long id);
	public boolean delCommentByThreadId(long id);
	public boolean changeCommentStateById(long id);
	public Comment queryById(long id);
	public Rs queryByThreadId(long threadId,int page,int pageSize,int stateMod);
	public Rs queryByGroupId(long groupId,int page,int pageSize);
	public long count(long tid,int mod);
}
