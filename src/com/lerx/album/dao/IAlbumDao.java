package com.lerx.album.dao;

import java.util.List;

import com.lerx.album.vo.Album;

public interface IAlbumDao {

	public Album add(Album album);
	public Album modify(Album album);
	public boolean delById(long id);
	public Album findById(long id);
	public List<Album> findByUid(long uid);
}
