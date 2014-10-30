package com.lerx.attachment.dao;

import java.util.List;

import com.lerx.attachment.vo.Attachment;

public interface IAttachmentDao {
	public boolean add(Attachment atta);
	public List<Attachment> findByHostId(long hid);
	public Attachment find(long id);
	public boolean del(long id);
	public boolean modify(Attachment atta);
}
