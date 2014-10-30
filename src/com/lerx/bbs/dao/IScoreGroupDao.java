package com.lerx.bbs.dao;

import java.util.List;

import com.lerx.bbs.vo.ScoreGroup;

public interface IScoreGroupDao {
	public boolean addScoreGroup(ScoreGroup scoreGroup);
	public boolean delScoreGroupById(int id);
	public boolean modifyScoreGroup(ScoreGroup scoreGroup);
	public ScoreGroup findScoreGroupByID(int id);
	public List<ScoreGroup> findGroupBySchemeId(int id);
	public ScoreGroup findScoreGroupByIdAndValue(int id,int value);
}
