package com.green.biz.utils;

/*
 * 현재 페이지와 관련한 정보를 저장하는 클래스
 * - 현재 페이지 번호
 * - 페이지 당 출력항목 수 
 * - 각 페이지에서 시작하는 항목 번호 ex)1페이지면 1번부터 2페이지면 2번부터...
 */
public class Criteria {
	private int pageNum;     // 현재 페이지 번호
	private int rowsPerPage; // 페이지당 출력하는 행의 갯수
	
	// 생성자
	public Criteria() {
		this(1, 10); // 기본값 : 1페이지, 항목 수: 10개
	}
	
	public Criteria(int pageNum, int rowsPerPage) {
		this.pageNum = pageNum;
		this.rowsPerPage = rowsPerPage;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) { 
		// 페이지 번호는 음수가 들어오면 안되기 때문에 if문 작성하여 음수제거
		if (pageNum <= 0)
			this.pageNum = 1;
		else
			this.pageNum = pageNum;
	}

	public int getRowsPerPage() {
		return rowsPerPage;
	}
	
	// 페이지당 항목수를 최대 10개로 제한둘 때
	public void setRowsPerPage(int rowsPerPage) {
		if(rowsPerPage <= 0 || rowsPerPage > 10)
			this.rowsPerPage = 10;
		else
			this.rowsPerPage = rowsPerPage;
	}	
	
	/*
	 * 각 페이지에서 시작하는 항목 번호 
	 */
	public int getPageStart() {
		return (pageNum-1) * rowsPerPage + 1;
	}

	@Override
	public String toString() {
		return "Criteria [pageNum=" + pageNum + ", rowsPerPage=" + rowsPerPage + "]";
	}
	
	
}
