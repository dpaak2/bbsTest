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
import javax.sound.midi.Sequencer;

import com.board.web.domain.ArticleBean;
import com.board.web.service.BoardService;
import com.board.web.serviceImpl.BoardServiceImpl;



@WebServlet("/board.do")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardService service =BoardServiceImpl.getInstance();
		ArticleBean bean=new ArticleBean(); 
	    String 	path=request.getServletPath(),
	    		directory =path.substring(0,path.indexOf(".")),
	    		action=request.getParameter("action"),
	    		pageName=request.getParameter("pageName"),
	    		view="/WEB-INF/views/"+directory+"/"+pageName+".jsp",
	    		title="",
	    		content="";
	  
		System.out.println("BoardController 진입");
		System.out.println("controller action:"+ request.getParameter("action"));
		/*java.util.HashMap<String, Object> param=new HashMap<>();*/
		
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
			System.out.println("controller write entered!!!");
			String writer=request.getParameter("writer");
			String regiDate=request.getParameter("regiDate");
			title=request.getParameter("title");
			content=request.getParameter("content");
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
			System.out.println("controller update entered ");
			title=request.getParameter("title");
			content=request.getParameter("content");
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
		     int pageNumber=Integer.parseInt(request.getParameter("pageNumber")),
			    		pagesPerOneBlock=5,
			    		rowsPerOnePage=5,
			    		theNumberOfRows=service.numberOfArticles(),
			    		theNumberOfPages=(theNumberOfRows%rowsPerOnePage==0)?theNumberOfRows/rowsPerOnePage:theNumberOfRows/rowsPerOnePage+1,
			    		startPage=pageNumber-((pageNumber-1)%pagesPerOneBlock),
			    		endPage=((startPage+rowsPerOnePage-1) < theNumberOfPages)?startPage+pagesPerOneBlock-1:theNumberOfPages,
			    		startRow=(pageNumber-1)*rowsPerOnePage+1,
			    		endRow= pageNumber*rowsPerOnePage,
			    	    prevBlock= startPage-pagesPerOneBlock,
			    	    nextBlock=startPage+pagesPerOneBlock;
		     		
			    	System.out.println("controller pagesPerOneBlock"+pagesPerOneBlock);
			    	System.out.println("controller theNumberOfRows"+theNumberOfRows);
			    	System.out.println("controller theNumberOfPages "+theNumberOfPages);
			    	System.out.println("controller startPage"+ startPage);
			    	System.out.println("controller endPage"+endPage);
			    	System.out.println("controller startRow"+startRow);
			    	System.out.println("controller endRow"+endRow);
			    	System.out.println("controller prevBlock"+prevBlock);
			    	System.out.println("controller nextBlock"+nextBlock);
			    	Map<String, Object> paramMap = new HashMap<>();
			    	java.util.List<ArticleBean> list=new ArrayList<>();
			/*param.put("pageNumber", pageNumber);
			param.put("totalCount",String.valueOf(theNumberOfRows));*/
			    	paramMap.put("startRow", startRow);
			    	paramMap.put("endRow", endRow);
			    	list=service.list(paramMap);
			request.setAttribute("pagesPerOneBlock", pagesPerOneBlock);
			request.setAttribute("rowsPerOnePage", rowsPerOnePage);
			request.setAttribute("theNumberOfRows", theNumberOfRows);
			request.setAttribute("theNumberOfPages", theNumberOfPages);
			request.setAttribute("pageNumber", pageNumber);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("startRow", startRow);
			request.setAttribute("endRow", endRow);
			request.setAttribute("prevBlock", prevBlock);
			request.setAttribute("nextBlock", nextBlock);
			request.setAttribute("list", list);
			request.setAttribute("count", service.numberOfArticles());
			request.getRequestDispatcher(view).forward(request, response);
			break;
			
		case "search":
			String option=request.getParameter("searchOption");
			  int pageNumber1=Integer.parseInt(request.getParameter("pageNumber")),
			    		pagesPerOneBlock1=5,
			    		rowsPerOnePage1=5,
			    		theNumberOfRows1=service.numberOfArticles(),
			    		theNumberOfPages1=(theNumberOfRows1%rowsPerOnePage1==0)?theNumberOfRows1/rowsPerOnePage1:theNumberOfRows1/rowsPerOnePage1+1,
			    		startPage1=pageNumber1-((pageNumber1-1)%pagesPerOneBlock1),
			    		endPage1=((startPage1+rowsPerOnePage1-1) < theNumberOfPages1)?startPage1+pagesPerOneBlock1-1:theNumberOfPages1,
			    		startRow1=(pageNumber1-1)*rowsPerOnePage1+1,
			    		endRow1= pageNumber1*rowsPerOnePage1,
			    	    prevBlock1= startPage1-pagesPerOneBlock1,
			    	    nextBlock1=startPage1+pagesPerOneBlock1;
			if(option.equals("searchByName")){
				System.out.println("controller searchByName entered");
				
				 /*   	System.out.println("controller pagesPerOneBlock"+pagesPerOneBlock1);
				    	System.out.println("controller theNumberOfRows"+theNumberOfRows1);
				    	System.out.println("controller theNumberOfPages "+theNumberOfPages1);
				    	System.out.println("controller startPage"+ startPage1);
				    	System.out.println("controller endPage"+endPage1);
				    	System.out.println("controller startRow"+startRow1);
				    	System.out.println("controller endRow"+endRow1);
				    	System.out.println("controller prevBlock"+prevBlock1);
				    	System.out.println("controller nextBlock"+nextBlock1);*/
				Map<String, Object> searchMap = new HashMap<>();
				java.util.List<ArticleBean> searchListByName=new ArrayList<>();
				String searchWriter=request.getParameter("searchWord");
				System.out.println("controller searchWriter"+ searchWriter);
				searchMap.put("writer", searchWriter);
				searchMap.put("startRow", startRow1);
				searchMap.put("endRow", endRow1);
				System.out.println("controller map writer: "+ searchMap.toString());
				searchListByName=service.searchByName(searchMap);
				System.out.println("controller searchList: "+ searchListByName.toString());
				request.setAttribute("count", service.numberOfResults(searchMap));
				request.setAttribute("list", searchListByName);
				request.getRequestDispatcher(view).forward(request, response);
				break;
			}else{
				System.out.println("controller searchByName entered");
			
				    	System.out.println("controller pagesPerOneBlock"+pagesPerOneBlock1);
				    	System.out.println("controller theNumberOfRows"+theNumberOfRows1);
				    	System.out.println("controller theNumberOfPages "+theNumberOfPages1);
				    	System.out.println("controller startPage"+ startPage1);
				    	System.out.println("controller endPage"+endPage1);
				    	System.out.println("controller startRow"+startRow1);
				    	System.out.println("controller endRow"+endRow1);
				    	System.out.println("controller prevBlock"+prevBlock1);
				    	System.out.println("controller nextBlock"+nextBlock1);
				Map<String, Object> searchMapByTitle = new HashMap<>();
				java.util.List<ArticleBean> searchListTitle=new ArrayList<>();
				String searchTitle=request.getParameter("searchWord");
				System.out.println("controller searchTitle"+ searchTitle);
				searchMapByTitle.put("title", searchTitle);
				searchMapByTitle.put("startRow", startRow1);
				searchMapByTitle.put("endRow", endRow1);
				System.out.println("controller map title: "+ searchMapByTitle.toString());
				searchListTitle=service.searchByName(searchMapByTitle);
				System.out.println("controller searchList: "+ searchListTitle.toString());
				request.setAttribute("count", service.numberOfResults(searchMapByTitle));
				request.setAttribute("list", searchListTitle);
				request.getRequestDispatcher(view).forward(request, response);
				break;
			}
		}
	}
}

