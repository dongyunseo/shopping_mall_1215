package com.human.dto;

public class Board_PageingVO {
	private int pageDataCount;  // 한 페이지에 보여줄 게시글 수
	private int currentpageNum; // 현재 페이지
	private int totalDataCount; // 전체 데이터 개수

	private int pageSize; 		// 한 페이지에 보여줄 게시글 개수
	private int firstPageNum; 	// 첫번째 게시글 번호
	private int lastPageNum; 	// 마지막 게시글 번호
	private int prevPageNum; 	// 이전 페이지
	private int nextPageNum; 	// 다음 페이지
	private int startPageNum; 	// 페이징의 시작 페이지 번호
	private int endPageNum; 	// 페이징의 마지막 페이지 번호
	
	@Override
	public String toString() {
		return "Board_PageingVO [pageDataCount=" + pageDataCount + ", currentpageNum=" + currentpageNum
				+ ", totalDataCount=" + totalDataCount + ", pageSize=" + pageSize + ", firstPageNum=" + firstPageNum
				+ ", lastPageNum=" + lastPageNum + ", prevPageNum=" + prevPageNum + ", nextPageNum=" + nextPageNum
				+ ", startPageNum=" + startPageNum + ", endPageNum=" + endPageNum + "]";
	}
	
	public void Board_Pageing (int page, int pageDataCount, int totalDataCount) {
		if (totalDataCount == 0)
			return;
		this.totalDataCount = totalDataCount;
		this.currentpageNum = page;
		this.pageDataCount = pageDataCount;

		this.pageSize = 10; 
		// < << 1 2 3 4 5 6 7 8 9 10 >> >
		 
		this.firstPageNum = 1; 
		// 게시글 출력은 1 페이지부터 시작한다.
		
		this.lastPageNum = (totalDataCount - 1) / pageDataCount + 1;
		// 만약 총 게시물이 73개라면 (총 게시물 - 1) / 한페이지에 보여줄 게시글 개수 + 1
		// (73 - 1) / 3 + 1 = 25, 마지막 페이지는 25페이지입니다. 

		this.startPageNum = ((this.currentpageNum - 1) / this.pageSize) * 10 + 1;
		// startpage = < << 1...10 >> >				 < << 11...20 >> > 	
		// 자신의 페이지가 10 이상이라면 ((11 - 1) / 10) * 10 + 1 = 11
		
		this.endPageNum = this.startPageNum + 9;
		// 처음 페이지가 11 이라면 + 9 를 하여  < << 11...20 >> > 을 만든다.
		
		if (this.endPageNum > this.lastPageNum) {
			this.endPageNum = this.lastPageNum;
		}
		
		this.prevPageNum = this.startPageNum - this.pageSize;
		// prevPageNum을 클릭하면  if(만약 사용자가 11페이지에 있다면) { 11-10 이므로 1페이지로 이동하게 됩니다 } 
		if (this.prevPageNum < 1) {
			prevPageNum = 1;
		}
		this.nextPageNum = this.endPageNum + this.pageSize - 9;
		// nextPageNum을 클릭하면   1페이지에서 11페이지로 이동 , 11페이지에서 21페이지로 이동
		if (this.nextPageNum > this.lastPageNum) {
			this.nextPageNum = this.lastPageNum;
		}
	}

	public int getPageDataCount() {
		return pageDataCount;
	}
	public void setPageDataCount(int pageDataCount) {
		this.pageDataCount = pageDataCount;
	}
	public int getCurrentpageNum() {
		return currentpageNum;
	}
	public void setCurrentpageNum(int currentpageNum) {
		this.currentpageNum = currentpageNum;
	}
	public int getTotalDataCount() {
		return totalDataCount;
	}
	public void setTotalDataCount(int totalDataCount) {
		this.totalDataCount = totalDataCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getFirstPageNum() {
		return firstPageNum;
	}
	public void setFirstPageNum(int firstPageNum) {
		this.firstPageNum = firstPageNum;
	}
	public int getLastPageNum() {
		return lastPageNum;
	}
	public void setLastPageNum(int lastPageNum) {
		this.lastPageNum = lastPageNum;
	}
	public int getPrevPageNum() {
		return prevPageNum;
	}
	public void setPrevPageNum(int prevPageNum) {
		this.prevPageNum = prevPageNum;
	}
	public int getNextPageNum() {
		return nextPageNum;
	}
	public void setNextPageNum(int nextPageNum) {
		this.nextPageNum = nextPageNum;
	}
	public int getStartPageNum() {
		return startPageNum;
	}
	public void setStartPageNum(int startPageNum) {
		this.startPageNum = startPageNum;
	}
	public int getEndPageNum() {
		return endPageNum;
	}
	public void setEndPageNum(int endPageNum) {
		this.endPageNum = endPageNum;
	}
	
	
}
