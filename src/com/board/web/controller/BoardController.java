package com.board.web.controller;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.board.web.domain.ArticleBean;
import com.board.web.service.BoardService;
import com.board.web.service.PaginationService;
import com.board.web.serviceImpl.BoardServiceImpl;
import com.board.web.serviceImpl.PaginationServiceImpl;


@SuppressWarnings("unused")
@WebServlet("/board.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIRECTORY = "upload";
	private static final String VIEW_DIRECTORY   = "/WEB-INF/views/";
	private static final String SAVE_PATH   = "C:\\Users\\hb2000\\JavaProjects\\eclipse4class14\\workspace\\noticeboard1705mvc\\WebContent\\resources\\upload\\";
	private static final String SAVE_DIRECTORY   = "upload"; 
	private static final int MAX_MEMORY_SIZE     = 1024 * 1024 * 3;  // 3MB
	private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BoardService service =BoardServiceImpl.getInstance();
		PaginationService pService=new PaginationServiceImpl();
		HttpSession session= request.getSession();
		ArticleBean bean=new ArticleBean(); 
	    String 	path=request.getServletPath(),
	    		directory =path.substring(0,path.indexOf(".")),
	    		action=request.getParameter("action"),
	    		pageNumber = request.getParameter("pageNumber"),
	    		pageName=request.getParameter("pageName"),
	    		title="",
	    		content="";
	    action=(action == null) ? "list" : action;
	    pageNumber=(pageNumber==null)?"0":pageNumber;
	  
		System.out.println("BoardController 진입");
		System.out.println("controller action:"+ request.getParameter("action"));
		String []params=new String[4];
		int[] rowsValues=new int[7];
		
		HashMap<String, Object> param = new HashMap<>();
		switch (action) {
		case "move":
			request
			.getRequestDispatcher(VIEW_DIRECTORY + directory + "/" + pageName + ".jsp")
			.forward(request, response);
			break;

		case "write":
			System.out.println("controller write entered!!!");
			String writer=request.getParameter("writer");
			System.out.println("controller write writer from UI: "+writer);
			title=request.getParameter("title");
			System.out.println("controller write title from UI: "+title);
			content=request.getParameter("content");
			System.out.println("controller write content from UI: "+content);
			bean.setWriter(writer);
			bean.setTitle(title);
			bean.setContent(content);
			service.writeArticle(bean);
			System.out.println("controller write bean toString(): "+bean.toString());
			request.setAttribute("writer", bean.getWriter());
			request.setAttribute("title", bean.getTitle());
			request.setAttribute("content", bean.getContent());
			request.setAttribute("seqNo", bean.getSeqNo());
			System.out.println("########################S");
			/*request
			.getRequestDispatcher(VIEW_DIRECTORY + directory + "/"+pageName+".jsp")
			.forward(request, response);*/
			response.sendRedirect("board.do?action=detail&pageName=detail");
			break;
			
		case "detail":
			System.out.println("CONTROLLER DETAIL ENTER !!");
			bean=new ArticleBean();
			String seqNo=request.getParameter("seqNo");
			System.out.println("detial seqNO: "+ seqNo);
			bean.setSeqNo(seqNo);
			bean= service.findArticle(bean);
			request.setAttribute("seqNo", bean.getSeqNo());
			request.setAttribute("writer", bean.getWriter());
			request.setAttribute("title", bean.getTitle());
			request.setAttribute("content", bean.getContent());
			request.getRequestDispatcher(VIEW_DIRECTORY + directory + "/" + pageName + ".jsp").forward(request, response);
			break;
		case "update":
			System.out.println("controller update entered ");
			bean=new ArticleBean();
			String updateSeqNo=request.getParameter("seqNo");
			System.out.println("update seqNO controller: "+ updateSeqNo);
			title=request.getParameter("title");
			content=request.getParameter("content");
			System.out.println("controller update befor go to dao title "+title);
			System.out.println("controller update befor go to dao content "+content);
			bean.setTitle(title);
			bean.setContent(content);
			bean.setSeqNo(updateSeqNo);
			request.setAttribute("title", bean.getTitle());
			request.setAttribute("content", bean.getContent());
			System.out.println("controller update title: "+bean.getTitle());
			System.out.println("controller update content: "+bean.getContent());
			request.setAttribute("seqNo", updateSeqNo);
			System.out.println("삭제 될 seq_no: "+updateSeqNo);
			bean=service.updateArticle(bean);
			request.getRequestDispatcher(VIEW_DIRECTORY + directory + "/" + pageName + ".jsp").forward(request, response);
			break;
			
		case "delete":
			System.out.println("delete entered from controller");
			bean=new ArticleBean();
			String deleteSeqNo=request.getParameter("seqNo");
			System.out.println("delete seqNO controller: "+ deleteSeqNo);
			request.setAttribute("title", bean.getTitle());
			request.setAttribute("content", bean.getContent());
			request.setAttribute("writer", bean.getWriter());
			request.setAttribute("regiDate", bean.getRegiDate());
			request.setAttribute("seqNo", deleteSeqNo);
			System.out.println("삭제 될 seq_no: "+deleteSeqNo);
			bean.setSeqNo(deleteSeqNo);
			bean=service.deleteArticle(bean);
			request
			.getRequestDispatcher(VIEW_DIRECTORY + directory + "/" + pageName + ".jsp")
			.forward(request, response);
			break;	
			
		case "list":
			System.out.println("controller list enter");
			params[0]=pageNumber;
			params[1]=String.valueOf(service.numberOfArticles());
			params[2]="5";
			params[3]="5";
			List<ArticleBean> list=new ArrayList<>();
			rowsValues=pService.calculateRows(params);
			param.put("startRow", rowsValues[0]);
			param.put("endRow", rowsValues[1]);
			System.out.println("controller list param.get(startRow)"+param.get("startRow"));
			System.out.println("controller list param.get(endRow)"+param.get("endRow"));
			list=service.list(param);
			request.setAttribute("pagesPerOneBlock", params[3]);
			request.setAttribute("rowsPerOnePage", params[2]);
			request.setAttribute("theNumberOfRows", params[1]);
			request.setAttribute("theNumberOfPages", rowsValues[6]);
			request.setAttribute("pageNumber", params[0]);
			request.setAttribute("startPage", rowsValues[2]);
			request.setAttribute("endPage", rowsValues[3]);
			request.setAttribute("startRow", rowsValues[0]);
			request.setAttribute("endRow", rowsValues[1]);
			request.setAttribute("prevBlock", rowsValues[4]);
			request.setAttribute("nextBlock", rowsValues[5]);
			request.setAttribute("list", list);
			request.setAttribute("count", service.numberOfArticles());
			request.setAttribute("actionType", "list");
			System.out.println("rowsValues[5] ::::"+rowsValues[5]);
			System.out.println("rowsValues[6]::::"+rowsValues[6]);
			request
			.getRequestDispatcher(VIEW_DIRECTORY + directory + "/list.jsp")
			.forward(request, response);
			break;
			
		case "search":
			String option=request.getParameter("searchOption");
			String searchWord=request.getParameter("searchWord");
			if(option!=null){
				session.setAttribute("option", option);
				session.setAttribute("searchWord",searchWord);
			}else{
				option= (String) session.getAttribute("option");
				searchWord= (String) session.getAttribute("searchWord");
			}
		System.out.println("controller option: "+ option);
				Map<String, Object> searchMap = new HashMap<>();
			
			if(option.equals("searchByName")){
				System.out.println("controller searchByName entered");
				System.out.println("controller searchWriter"+ searchWord);
				searchMap.put("searchType", "writer");
				searchMap.put("searchWord", searchWord);
				params[0]=pageNumber;
				params[1]=String.valueOf(service.numberOfResults(searchMap));
				params[2]="5";
				params[3]="5";
				rowsValues=pService.calculateRows(params);
				searchMap.put("startRow",  rowsValues[0]);
				searchMap.put("endRow",  rowsValues[1]);
				List<ArticleBean> searchList=new ArrayList<>();
				System.out.println("controller map writer: "+ searchMap.toString());
				searchList=service.searchByName(searchMap);
				System.out.println("controller searchList: "+ searchList.toString());
				request.setAttribute("count", service.numberOfResults(searchMap));
     			request.setAttribute("pagesPerOneBlock",  params[3]);
				request.setAttribute("rowsPerOnePage", params[2]);
				request.setAttribute("theNumberOfRows", params[1]);
				request.setAttribute("theNumberOfPages", rowsValues[6]);
				request.setAttribute("pageNumber", params[0]);
				request.setAttribute("startPage", rowsValues[2]);
				request.setAttribute("endPage", rowsValues[3]);
				request.setAttribute("startRow", rowsValues[0]);
				request.setAttribute("endRow",  rowsValues[1]);
				request.setAttribute("prevBlock", rowsValues[4]);
				request.setAttribute("nextBlock",  rowsValues[5]);
				request.setAttribute("list", searchList);
				request.setAttribute("actionType", "search");
				request.getRequestDispatcher(VIEW_DIRECTORY + directory + "/list.jsp").forward(request, response);
				break;
			}else if(option.equals("searchByTitle")){
				System.out.println("controller searchByTitle entered");
				searchMap.put("searchType", "title");
				searchMap.put("searchWord", searchWord);
				params[0]=pageNumber;
				params[1]=String.valueOf(service.numberOfResults(searchMap));
				params[2]="5";
				params[3]="5";
				rowsValues=pService.calculateRows(params);
				searchMap.put("startRow",  rowsValues[0]);
				searchMap.put("endRow",  rowsValues[1]);
				System.out.println("controller searchTitle"+ searchWord);
				List<ArticleBean> searchListTitle=new ArrayList<>();
				System.out.println("controller searchMap title: "+searchMap.toString());
				System.out.println("controller map title: "+ searchMap.toString());
				searchListTitle=service.searchByTitle(searchMap);
				System.out.println("controller searchList: "+ searchListTitle.toString());
				request.setAttribute("count", service.numberOfResults(searchMap));
    			request.setAttribute("pagesPerOneBlock",  params[3]);
			   request.setAttribute("rowsPerOnePage", params[2]);
			   request.setAttribute("theNumberOfRows", params[1]);
			   request.setAttribute("theNumberOfPages", rowsValues[6]);
		       request.setAttribute("pageNumber", params[0]);
		       request.setAttribute("startPage", rowsValues[2]);
	   	       request.setAttribute("endPage", rowsValues[3]);
		       request.setAttribute("startRow", rowsValues[0]);
		       request.setAttribute("endRow",  rowsValues[1]);
		       request.setAttribute("prevBlock", rowsValues[4]);
		       request.setAttribute("nextBlock",  rowsValues[5]);
		       request.setAttribute("actionType", "search");
			   request.setAttribute("list", searchListTitle);
			   request
				.getRequestDispatcher(VIEW_DIRECTORY + directory + "/" + pageName + ".jsp")
				.forward(request, response);
				break;
			}else{System.out.println("Message for developer only");}
		}
	}
}

