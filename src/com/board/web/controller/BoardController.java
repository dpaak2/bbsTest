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
		ArticleBean bean=new ArticleBean(); 
	    String 	path=request.getServletPath(),
	    		directory =path.substring(0,path.indexOf(".")),
	    		action=request.getParameter("action"),
	    		foo = request.getParameter("pageNumber"),
	    		pageName=request.getParameter("pageName"),
	    		title="",
	    		content="";
	    action=(action == null) ? "list" : action;
	    foo=(foo==null)?"0":foo;
	    int pageNumber = Integer.parseInt(foo);
		System.out.println("BoardController 진입");
		System.out.println("controller action:"+ request.getParameter("action"));
		int pagesPerOneBlock = 5, rowsPerOnePage = 5, theNumberOfRows = service.numberOfArticles(),
				theNumberOfPages = (theNumberOfRows % rowsPerOnePage == 0) ? theNumberOfRows / rowsPerOnePage
						: theNumberOfRows / rowsPerOnePage + 1,
				startPage = pageNumber - ((pageNumber - 1) % pagesPerOneBlock),
				endPage = ((startPage + rowsPerOnePage - 1) < theNumberOfPages) ? startPage + pagesPerOneBlock - 1
						: theNumberOfPages,
				startRow = (pageNumber - 1) * rowsPerOnePage + 1, endRow = pageNumber * rowsPerOnePage,
				prevBlock = startPage - pagesPerOneBlock, nextBlock = startPage + pagesPerOneBlock;
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
			title=request.getParameter("title");
			content=request.getParameter("content");
			String updateSubject=request.getParameter("updateSubject");
			bean.setSeqNo(updateSubject);
			bean.setTitle(title);
			bean.setContent(content);
			service.updateArticle(bean);
			request.setAttribute("title", bean.getTitle());
			request.setAttribute("content", bean.getContent());
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
			java.util.List<ArticleBean> list=new ArrayList<>();
			/*param.put("pageNumber", pageNumber);
			param.put("totalCount",String.valueOf(theNumberOfRows));*/
			param.put("startRow", startRow);
			param.put("endRow", endRow);
			    list=service.list(param);
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
			request.setAttribute("actionType", "list");
			request
			.getRequestDispatcher(VIEW_DIRECTORY + directory + "/list.jsp")
			.forward(request, response);
		
			break;
			
		case "search":
			String option=request.getParameter("searchOption");
				Map<String, Object> searchMap = new HashMap<>();
				String searchWord=request.getParameter("searchWord");
			if(option.equals("searchByName")){
				System.out.println("controller searchByName entered");
				System.out.println("controller searchWriter"+ searchWord);
				searchMap.put("searchType", "writer");
				searchMap.put("searchWord", searchWord);
				searchMap.put("startRow", startRow);
				searchMap.put("endRow", endRow);
				java.util.List<ArticleBean> searchList=new ArrayList<>();
				System.out.println("controller map writer: "+ searchMap.toString());
				searchList=service.searchByName(searchMap);
				System.out.println("controller searchList: "+ searchList.toString());
				request.setAttribute("count", service.numberOfResults(searchMap));
				param.put("startRow", startRow);
				param.put("endRow", endRow);
				    list=service.list(param);
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
				request.setAttribute("list", searchList);
				request.setAttribute("actionType", "search");
				request.getRequestDispatcher(VIEW_DIRECTORY + directory + "/list.jsp").forward(request, response);
				break;
			}else if(option.equals("searchByTitle")){
				System.out.println("controller searchByTitle entered");
				    	System.out.println("controller pagesPerOneBlock"+pagesPerOneBlock);
				    	System.out.println("controller theNumberOfRows"+theNumberOfRows);
				    	System.out.println("controller theNumberOfPages "+theNumberOfPages);
				    	System.out.println("controller startPage"+ startPage);
				    	System.out.println("controller endPage"+endPage);
				    	System.out.println("controller startRow"+startRow);
				    	System.out.println("controller endRow"+endRow);
				    	System.out.println("controller prevBlock"+prevBlock);
				    	System.out.println("controller nextBlock"+nextBlock);
				System.out.println("controller searchTitle"+ searchWord);
				searchMap.put("searchType", "title");
				searchMap.put("searchWord", searchWord);
				System.out.println("controller searchMap title: "+searchMap.toString());
				searchMap.put("startRow", startRow);
				searchMap.put("endRow", endRow);
				java.util.List<ArticleBean> searchListTitle=new ArrayList<>();
				System.out.println("controller map title: "+ searchMap.toString());
				searchListTitle=service.searchByTitle(searchMap);
				System.out.println("controller searchList: "+ searchListTitle.toString());
				request.setAttribute("count", service.numberOfResults(searchMap));
				param.put("startRow", startRow);
				param.put("endRow", endRow);
				    list=service.list(param);
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

