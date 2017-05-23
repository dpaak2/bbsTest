package com.board.web.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.board.web.dao.BoardDAO;
import com.board.web.daoImpl.BoardDAOImpl;
import com.board.web.domain.ArticleBean;
import com.board.web.service.BoardService;

public class BoardServiceImpl implements BoardService {
	private BoardDAO dao= BoardDAOImpl.getInstance();
	
	public static BoardServiceImpl instance= new BoardServiceImpl();
	public static BoardServiceImpl getInstance(){
		return instance;
	}
	private BoardServiceImpl(){}
	 
	@Override
	public void writeArticle(ArticleBean article) {
		dao.insertArticle(article);
	}

	@Override
	public int numberOfArticles() {
		int count=dao.countArticles();
		System.out.println("BoardServiceImpl enter");
		System.out.println("service count: "+count);
		return count;
	}

	@Override
	public ArticleBean findArticle(ArticleBean article) {
		return dao.selectArticle(article);
	}

	@Override
	public List<ArticleBean> list(Map<String, Object> paramMap) {
		System.out.println("service 진입@@@@@");
		List<ArticleBean> list =new ArrayList<>();
		list=dao.list(paramMap);
		System.out.println("serviceImpl list: "+list);
		return list;
	}

	@Override
	public void updateArticle(ArticleBean article) {
		System.out.println("service 진입@@updateArticle@@");
		dao.updateArticle(article);
	}

	@Override
	public ArticleBean deleteArticle(ArticleBean article) {
	return dao.deleteArticel(article);
	}
	@Override
	public List<ArticleBean> searchByName(Map<String, Object> paramMap){
		System.out.println("service 진입@@searchList@@");
		List<ArticleBean> rsList= new ArrayList<>();
		rsList= dao.list(paramMap);
		return rsList;
	}
}
