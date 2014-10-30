package com.lerx.bbs.dao;

import java.util.List;

import com.lerx.bbs.vo.ScoreScheme;

public interface IScoreSchemeDao {
	public boolean addScoreScheme(ScoreScheme scheme);
	public boolean delScoreSchemeById(int id);
	public boolean modifyScoreScheme(ScoreScheme scheme);
	public ScoreScheme findScoreSchemeById(int id);
	public List<ScoreScheme> findAll();
	public boolean setState(int id);
	public ScoreScheme findCurScoreScheme();
}
