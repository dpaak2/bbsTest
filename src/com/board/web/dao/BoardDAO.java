package com.board.web.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.board.web.domain.ArticleBean;

public interface BoardDAO {
	public void insertArticle(ArticleBean article);
	public int countArticles();
	public int lastInsertedSeqno();
	public int searchCount(Map<String, Object> paramMap);
	public ArticleBean selectArticle(ArticleBean article);
	public List<ArticleBean> list(Map<String, Object> paramMap);
	public List<ArticleBean> searchByName(Map<String, Object> paramMap);
	public List<ArticleBean> searchByTitle(Map<String, Object>paramMap);
	public ArticleBean updateArticle(ArticleBean article);
	public ArticleBean deleteArticle(ArticleBean article);
}
