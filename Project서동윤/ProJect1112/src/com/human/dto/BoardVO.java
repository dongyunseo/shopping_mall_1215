package com.human.dto;

import java.sql.Date;

public class BoardVO {
	private int bNumber;		// 게시판 번호
	private String bTitle;		// 게시판 타이틀
	private String bContent;	// 게시판 내용
	private String id;			// 작성자
	private Date bDate;			// 게시글 작성날짜
	private int bHit;			// 조회수
	private int bGroup;			// 글 그룹 (본글, 댓글 )
	private int bIndent;		// 댓글 갯수 
	
	public BoardVO() {}
	
	public BoardVO(int bNumber, String bTitle, String bContent, String id, Date bDate, int bHit, int bGroup,
			int bIndent) {
		super();
		this.bNumber = bNumber;
		this.bTitle = bTitle;
		this.bContent = bContent;
		this.id = id;
		this.bDate = bDate;
		this.bHit = bHit;
		this.bGroup = bGroup;
		this.bIndent = bIndent;
	}

	public int getbNumber() {
		return bNumber;
	}

	public void setbNumber(int bNumber) {
		this.bNumber = bNumber;
	}

	public String getbTitle() {
		return bTitle;
	}

	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}

	public String getbContent() {
		return bContent;
	}

	public void setbContent(String bContent) {
		this.bContent = bContent;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getbDate() {
		return bDate;
	}

	public void setbDate(Date bDate) {
		this.bDate = bDate;
	}

	public int getbHit() {
		return bHit;
	}

	public void setbHit(int bHit) {
		this.bHit = bHit;
	}

	public int getbGroup() {
		return bGroup;
	}

	public void setbGroup(int bGroup) {
		this.bGroup = bGroup;
	}

	public int getbIndent() {
		return bIndent;
	}

	public void setbIndent(int bIndent) {
		this.bIndent = bIndent;
	}
	
	
}
