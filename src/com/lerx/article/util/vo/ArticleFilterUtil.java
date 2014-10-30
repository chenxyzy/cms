package com.lerx.article.util.vo;

import com.lerx.article.vo.ArticleThread;

public class ArticleFilterUtil {
	
	private ArticleThread t;
	private boolean found;
	private boolean rep;
	private boolean con;

	public ArticleThread getT() {
		return t;
	}

	public void setT(ArticleThread t) {
		this.t = t;
	}

	public boolean isRep() {
		return rep;
	}

	public void setRep(boolean rep) {
		this.rep = rep;
	}

	public boolean isFound() {
		return found;
	}

	public void setFound(boolean found) {
		this.found = found;
	}

	public boolean isCon() {
		return con;
	}

	public void setCon(boolean con) {
		this.con = con;
	}

}
