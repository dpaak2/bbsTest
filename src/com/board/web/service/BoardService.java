package com.board.web.service;
import java.util.List;
import java.util.Map;

import com.board.web.domain.ArticleBean;

public interface BoardService {
	public void writeArticle(ArticleBean article);
	public int numberOfArticles();
	public int lastInsertedSeqno();
	public int numberOfResults(Map<String, Object>paramMap);
	public ArticleBean findArticle(ArticleBean article);
	public List<ArticleBean> list(Map<String, Object> paramMap);
	public List<ArticleBean> searchByName(Map<String, Object> paramMap);
	public List<ArticleBean> searchByTitle(Map<String, Object>paramMap);
	public ArticleBean updateArticle(ArticleBean article);
	public ArticleBean deleteArticle(ArticleBean article);
}
