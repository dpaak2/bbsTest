package com.board.web.service;

import java.util.List;

import com.board.web.domain.ArticleBean;

public interface BoardService {
	public void writeArticle(ArticleBean article);
	public int numberOfArticles();
	public ArticleBean findArticle(ArticleBean article);
	public List<ArticleBean> list(ArticleBean article);
	public void updateArticle(ArticleBean article);
	public ArticleBean deleteArticle(ArticleBean article);
}
