package com.board.web.controller;
import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.board.web.domain.ArticleBean;
import com.board.web.service.BoardService;
import com.board.web.serviceImpl.BoardServiceImpl;
import com.sun.javafx.collections.MappingChange.Map;



@WebServlet("/board.do")
public class BoardController extends HttpServlet {
	BoardService service =BoardServiceImpl.getInstance();
	ArticleBean bean=new ArticleBean(); 
    String page,view;
    String title;
    String content;
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BoardController 진입");
		String path=request.getServletPath();
		String directory =path.substring(0,path.indexOf("."));
		String action=request.getParameter("action");
		System.out.println("controller action:"+ request.getParameter("action"));
		page=request.getParameter("page");
		view="/WEB-INF/views/"+directory+"/"+page+".jsp";
		java.util.HashMap<String, Object> param=new HashMap<>();
		switch (action) {
		case "move":
			System.out.println("BoardService에서 가져온 count: "+service.numberOfArticles());
			request.setAttribute("count", service.numberOfArticles());
			System.out.println("BoardController count: "+request.getAttribute("count"));
			bean.setSeqNo("1");
			bean=service.findArticle(bean);
			request.setAttribute("seqNo", bean.getSeqNo());
			request.setAttribute("title", bean.getTitle());
			request.setAttribute("content", bean.getContent());
			request.setAttribute("writer", bean.getWriter());
			request.setAttribute("regiDate", bean.getRegiDate());
			request.setAttribute("hitCounts", bean.getHitCount());
			request.getRequestDispatcher(view).forward(request, response);
			break;

		case "write":
			System.out.println("controller write");
			String writer=request.getParameter("writer");
			String regiDate=request.getParameter("regiDate");
			title=request.getParameter("title");
			content=request.getParameter("content");
			System.out.println("request.getQueryString():"+request.getQueryString());
			System.out.println("작성자: "+writer);
			System.out.println("수정날짜: "+regiDate);
			System.out.println("제목: "+title);
			System.out.println("내용: "+content);
			System.out.println("작성완료뒤 가는 페이지: "+page);
			bean.setWriter(writer);
			bean.setRegiDate(regiDate);
			bean.setTitle(title);
			bean.setContent(content);
			service.writeArticle(bean);
			request.setAttribute("title", bean.getTitle());
			request.setAttribute("content", bean.getContent());
			request.setAttribute("writer", bean.getWriter());
			request.setAttribute("regiDate", bean.getRegiDate());
			request.getRequestDispatcher(view).forward(request, response);
			
		case "update":
			System.out.println("update enetered from controller");
			title=request.getParameter("title");
			System.out.println("update title: "+title);
			content=request.getParameter("content");
			System.out.println("update content: "+content);
			System.out.println("작성완료뒤 가는 페이지: "+page);
			bean.setSeqNo("42");
			bean.setTitle(title);
			bean.setContent(content);
			service.updateArticle(bean);
			request.setAttribute("title", bean.getTitle());
			request.setAttribute("content", bean.getContent());
			request.getRequestDispatcher(view).forward(request, response);
			break;
			
		case "delete":
			System.out.println("delete entered form controller");
			String deleteObect=request.getParameter("deleteObject");
			bean.setSeqNo(deleteObect);
			bean=service.deleteArticle(bean);
			System.out.println("삭제 완료");
			request.setAttribute("title", bean.getTitle());
			request.setAttribute("content", bean.getContent());
			request.setAttribute("writer", bean.getWriter());
			request.setAttribute("regiDate", bean.getRegiDate());
			request.getRequestDispatcher(view).forward(request, response);
			break;	
			
		case "list":
			System.out.println("controller list enter");
			param.put("title", request.getParameter("title"));
			List<ArticleBean> list= service.findArticles(param);
			/*request.setAttribute("list", list);*/
			System.out.println("controller list"+list.get(0));
			System.out.println("controller list" +list);
			break;
		}
		
	}
}
