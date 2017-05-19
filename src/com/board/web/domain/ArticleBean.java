package com.board.web.domain;

public class ArticleBean {
	
	private String seqNo,title,content,writer,regiDate,hitCount;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSeqNo() {
		return seqNo;
	}

	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getRegiDate() {
		return regiDate;
	}

	public void setRegiDate(String regiDate) {
		this.regiDate = regiDate;
	}

	public String getHitCount() {
		return hitCount;
	}

	public void setHitCount(String hitCount) {
		this.hitCount = hitCount;
	}

	@Override
	public String toString() {
		return "ArticleBean [seqNo=" + seqNo + ", title=" + title + ", content=" + content + ", writer=" + writer
				+ ", regiDate=" + regiDate + ", hitCount=" + hitCount + "]";
	}
	
}
