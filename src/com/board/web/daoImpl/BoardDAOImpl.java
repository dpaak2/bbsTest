package com.board.web.daoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.board.web.dao.BoardDAO;
import com.board.web.domain.ArticleBean;

public class BoardDAOImpl implements BoardDAO {
	public static BoardDAOImpl instance=new BoardDAOImpl();
	public static BoardDAOImpl getInstance() {
		return instance;
	}
	public static void setInstance(BoardDAOImpl instance) {
		BoardDAOImpl.instance = instance;
	}
	private BoardDAOImpl(){}
		
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://203.236.209.96:3306/hanbit";   //다른사람 디비 연결한
	private static final String USER = "hanbit";
	private static final String PW = "hanbit";
/*	public static void main(String[] args) {
		String writer="";
		try {
			Class.forName(DRIVER);
			Connection connection= DriverManager.getConnection(URL,USER,PW);
			Statement stmt=connection.createStatement();
			String sql="SELECT * FROM Board WHERE seq_no='1'";
			ResultSet rs=stmt.executeQuery(sql);
			
			if(rs.next()){
				writer=rs.getString("writer");
			}
		} catch (Exception e) {
			System.out.println("에러발생");
			e.printStackTrace();
		}
		System.out.println("작성자: "+writer);
	}*/
	@Override
	public void insertArticle(ArticleBean article) {
		try {
			Class.forName(DRIVER);
			Connection connection=DriverManager.getConnection(URL,USER,PW);
			Statement stmt=connection.createStatement();
			String sql="INSERT INTO Board (title,writer,content,regi_date) VALUES ('"
					+article.getTitle()+"','"+article.getWriter()+"','"+article.getContent()+"','"+article.getRegiDate()+"')";
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("에러 발생");
			e.printStackTrace();
		}
	}
	@Override
	public int countArticles() {
	int count=0;
	try {
		Class.forName(DRIVER);
		Connection connection=DriverManager.getConnection(URL,USER,PW);
		Statement stmt=connection.createStatement();
		String sql="SELECT COUNT(*) AS count FROM Board";
		ResultSet rs=stmt.executeQuery(sql);
		if(rs.next()){
			count=Integer.parseInt(rs.getString("count"));
		}
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	System.out.println("count: "+ count);
	return count;
	}
	@Override
	public ArticleBean selectArticle(ArticleBean article) {
		ArticleBean temp=null;
		int hitCounts=0;
		try {
			Class.forName(DRIVER);
			Connection connection=DriverManager.getConnection(URL,USER,PW);
			Statement stmt=connection.createStatement();
			String sql="SELECT seq_no,title,content,writer,regi_date,count FROM Board WHERE seq_no ='"+article.getSeqNo()+"'";
			ResultSet rs=stmt.executeQuery(sql);
			if(rs.next()){
				temp=new ArticleBean();
				temp.setSeqNo(rs.getString("seq_no"));
				System.out.println("rs.getString(seq_no) "+rs.getString("seq_no"));
				temp.setTitle(rs.getString("title"));
				System.out.println("rs.getString(title) "+rs.getString("title"));
				temp.setContent(rs.getString("content"));
				System.out.println("rs.getString(content) "+rs.getString("content"));
				temp.setWriter(rs.getString("writer"));
				System.out.println("rs.getString(writer) "+rs.getString("writer"));
				temp.setRegiDate(rs.getString("regi_date"));
				System.out.println("rs.getString(regiDate) "+rs.getString("regi_date"));
				hitCounts=Integer.parseInt(rs.getString("count"));
				temp.setHitCount(String.valueOf(++hitCounts));
				System.out.println("rs.getString(count)"+hitCounts);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return temp;
	}
	@Override
	public List<ArticleBean> list(Map<String, Object> paramMap){
		List<ArticleBean> listSome= new ArrayList<>();
		ArticleBean article =new ArticleBean();
		System.out.println("DAO IMPL 진입@@@@list");
		try {
			Class.forName(DRIVER);
			Connection conenction=DriverManager.getConnection(URL,USER,PW);
			Statement stmt=conenction.createStatement();
			String s=String.valueOf(paramMap.get("startRow"));
			System.out.println("@@@2DAO starRow"+ s);
			String e=String.valueOf(paramMap.get("endRow"));
			System.out.println("@@@DAO endRow: "+ e);
			String sql="SELECT * FROM (SELECT @NO := @NO + 1 AS ROWNUM, A.* FROM ( SELECT * FROM Board) A,( SELECT @NO := 0 ) B ) C WHERE C.ROWNUM BETWEEN "+s+" AND "+e+"";

			/*"SELECT t2.*"
							   +"\tFROM (SELECT ROWNUM seq,t.*"
							   +"\tFROM (SELECT * FROM Board ORDER BY art_seq DESC) t) t2"
							   +"\tWHERE t2.seq BETWEEN %s AND %s", String.valueOf(pramMap.get("startRow")), String.valueOf(pramMap.get("endRow")));*/
			ResultSet rs=stmt.executeQuery(sql);
			/*System.out.println("rs 값입니다"+rs);*/
			while(rs.next()){
				article=new ArticleBean();
				article.setSeqNo(rs.getString("seq_no"));
/*				System.out.println("rs.getString(seq_no)"+rs.getString("seq_no"));
*/				article.setTitle(rs.getString("title"));
/*				System.out.println("rs.getString (title)"+rs.getString("title"));
*/				article.setContent(rs.getString("content"));
				/*System.out.println("rs.content: "+ rs.getString("content"));*/
				article.setWriter(rs.getString("writer"));
				/*System.out.println("rs.writer: "+rs.getString("writer"));*/
				article.setRegiDate(rs.getString("regi_date"));
				/*System.out.println("rs.regi_date: "+rs.getString("regi_date"));*/
				article.setHitCount(rs.getString("count"));
				/*System.out.println("rs.count: "+rs.getString("count"));*/
				listSome.add(article);
			} 
		} catch (Exception e) {
			System.out.println("에러발생:");
			e.printStackTrace();
		}
		return listSome;
	}
	@Override
	public void updateArticle(ArticleBean article) {
		try {
			Class.forName(DRIVER);
			Connection connection=DriverManager.getConnection(URL,USER,PW);
			Statement stmt=connection.createStatement();
			String sql="UPDATE Board SET title='"+article.getTitle()+"',content='"+article.getContent()+"' WHERE seq_no='"+article.getSeqNo()+"'";
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("에러 발생:");
			e.printStackTrace();
		}
	}
	@Override
	public ArticleBean deleteArticle(ArticleBean article) {
		
		try {
			Class.forName(DRIVER);
			Connection connection=DriverManager.getConnection(URL,USER,PW);
			Statement stmt=connection.createStatement();
			String sql="DELETE  FROM Board WHERE seq_no='"+article.getSeqNo()+"'";
			stmt.executeUpdate(sql);
		} catch (Exception e) {
			System.out.println("에러발생");
			e.printStackTrace();
		}
		return article;
	}
	@Override
	public List<ArticleBean> searchByName(Map<String, Object> paramMap) {
		System.out.println("DAOIMPL searchByName enter");
		List<ArticleBean> searchByName= new ArrayList<>();
		ArticleBean bean=null;
		try {
			Class.forName(DRIVER);
			Connection connection= DriverManager.getConnection(URL,USER,PW);
			Statement stmt= connection.createStatement();
			String writer= (String) paramMap.get("writer");
			System.out.println("@@@@DAO searchList writer: "+writer);
			String startRow=String.valueOf(paramMap.get("startRow"));
			System.out.println("@@@2DAO starRow"+ startRow);
			String endRow=String.valueOf(paramMap.get("endRow"));
			System.out.println("@@@DAO endRow: "+ endRow);
			String sql="SELECT * FROM (SELECT @NO := @NO + 1 AS ROWNUM, A.* FROM ( SELECT * FROM Board) A,( SELECT @NO := 0 ) B ) C WHERE C.ROWNUM BETWEEN "+startRow+" AND "+endRow+"";
			String sql2="SELECT * FROM Board WHERE writer LIKE '%"+writer+"%'";
			System.out.println("DAO searchByName writer: " + writer);
			ResultSet rs= stmt.executeQuery(sql2);
			
			while(rs.next()){
				bean = new ArticleBean();
				bean.setSeqNo(rs.getString("seq_no"));
				System.out.println("@@@ DB searchByName seq_no: "+rs.getString("seq_no"));
				bean.setWriter(rs.getString("writer"));
				System.out.println("@@@ DB searchByName writer: "+rs.getString("writer"));
				bean.setTitle(rs.getString("title"));
				bean.setContent(rs.getString("content"));
				bean.setHitCount(rs.getString("count"));
				bean.setRegiDate(rs.getString("regi_date"));
				searchByName.add(bean);
			}
		} catch (Exception e) {
			System.out.println("에러발생: ");
			e.printStackTrace();
		}
		System.out.println("DB searchList 값: "+ searchByName.toString());
		return searchByName;
	}
	@Override
	public List<ArticleBean> searchByTitle(Map<String, Object> paramMap) {
		List<ArticleBean> searchByTitleList=new ArrayList<>();
		Map<String, Object> searchByTitleMap= new HashMap<>();
		ArticleBean temp= null;
		searchByTitleMap=paramMap;
		try {
			Class.forName(DRIVER);
			Connection connection=DriverManager.getConnection(URL,USER,PW);
			Statement stmt=connection.createStatement();
			String title=(String) searchByTitleMap.get("title");
			System.out.println("DAO seachByTitleMap : "+searchByTitleMap.toString());
			String sql="SELECT * FROM Board WHERE title LIKE '%"+title+"%'";
			//String sql2="SELECT * FROM Board WHERE writer LIKE '%"+writer+"%'";
			System.out.println("DAO searchByTitle title: "+title);
			ResultSet rs=stmt.executeQuery(sql);
			if(rs.next()){
				temp = new ArticleBean();
				temp.setSeqNo(rs.getString("seq_no"));
				System.out.println("@@@ DB searchByName seq_no: "+rs.getString("seq_no"));
				temp.setWriter(rs.getString("writer"));
				System.out.println("@@@ DB searchByName writer: "+rs.getString("writer"));
				temp.setTitle(rs.getString("title"));
				temp.setContent(rs.getString("content"));
				temp.setHitCount(rs.getString("count"));
				temp.setRegiDate(rs.getString("regi_date"));
				searchByTitleList.add(temp);
			}
		} catch (Exception e) {
			System.out.println("에러발생: ");
			e.printStackTrace();
		}
		System.out.println("DAO searchByTitleList: "+searchByTitleList.toString());
		return searchByTitleList;
	}
	@Override
	public int searchCount(Map<String, Object> paramMap) {
		int searchCount=0;
		try {
			Class.forName(DRIVER);
			Connection connection=DriverManager.getConnection(URL,USER,PW);
			Statement stmt= connection.createStatement();
			String searchWriter= (String) paramMap.get("writer");
			System.out.println("DAO searchCount searchWriter: "+searchWriter);
			String searchTitle=(String) paramMap.get("title");
			System.out.println("DAO searchCount searchTitle: "+ searchTitle);
			String sql="SELECT COUNT(*) AS qty FROM Board LIKE %"+searchTitle+"% ";
			if(searchWriter.equals(true)){
				sql="SELECT COUNT(*) AS qty FROM Board WHERE writer LIKE '"+searchTitle+"%'";
			}else{
				sql="SELECT COUNT(*) AS qty FROM Board lIKE '%"+searchWriter+"%'";
			}
			ResultSet rs=stmt.executeQuery(sql);
			if(rs.next()){
				searchCount=rs.getInt("qty");
			}
		} catch (Exception e) {
			System.out.println("에러발생");
			e.printStackTrace();
		}
		System.out.println("DAO searchCount: "+ searchCount);
		return searchCount;
	}

}
