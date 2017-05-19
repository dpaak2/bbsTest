package com.board.web.dao;

import java.util.HashMap;
import java.util.List;

import com.board.web.domain.ArticleBean;

public interface BoardDAO {
	public void insertArticle(ArticleBean article);
	public int countArticles();
	public ArticleBean selectArticle(ArticleBean article);
	public List<ArticleBean> selectArticles(HashMap<String,Object> paramMap);
	public void updateArticle(ArticleBean article);
	public ArticleBean deleteArticel(ArticleBean article);
}
