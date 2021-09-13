package com.green.biz.utils;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {
	private Criteria cri;     // ���� ������ ��ȣ, �������� �׸� ��
	private int totalCount;   // ��ü�Խñ� 
	private int startPage;    // ���������� ��ȣ
	private int endPage;      // ����������ȣ
	private boolean prev;     // ���� ������ ��ư ����
	private boolean next;     // ���� ������ ��ư ����
	private int cntPageNum = 10; //ȭ�� �ϴܿ� ���̴� ������ ��ư�� �� 
	private int realEndPage;  // ��ü �׸� ���� ���� ���� �������� ��ȣ ����
	
	// ��ü �Խñ� ���� ���� ������� ���� �ʱ�ȭ
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		
		fieldInit();
	}
	
	public void fieldInit() {
		// �� ������ ��� (���� ������ ���� -> �Ǽ� ���ü� �����Ƿ� double�� ����ȯ �� int�� �纯ȯ)
		endPage = (int)(Math.ceil(cri.getPageNum() / (double)cntPageNum) * cntPageNum);
		
		startPage = (endPage - cntPageNum) + 1;
		
		// ������ ���������� ǥ�����ֱ� ���� (����ΰ��� ����ϰ� Mathe.ceil���� �� int�� ����ȯ����)
		// ���� �� ������ ��ȣ = Math.ceil(�Խñ��� �� �׸� �� / �������� �׸� ��)
		realEndPage = (int)(Math.ceil(totalCount / (double)cri.getRowsPerPage()));
		
		// ���� �� �������� endPage�� ����
		if (endPage > realEndPage) {
			endPage = realEndPage;
		}
		
		prev = startPage == 1 ? false : true; // ������������ 1�������ϰ�� '����' ��ư �ʿ����
		next = endPage * cri.getRowsPerPage() >= totalCount ? false : true;		
	}
	
	/*
	 * ȭ�鿡�� ������ ��ȣ�� Ŭ���� ��, ��������ȣ�� �������� �׸� ���� �̿��ؼ� QueryString�� ������ִ� �޼ҵ�
	 * ��� �� : ?pageNum=3&rowsPerPage=10 
	 */
	public String makeQuery(int page) {
		UriComponents uriComp = UriComponentsBuilder.newInstance()
								.queryParam("pageNum", page)
								.queryParam("rowsPerPage", cri.getRowsPerPage())
								.build();
		return uriComp.toString();
	}
	
	
	// ������� Getter, Setter

	public Criteria getCri() {
		return cri;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getCntPageNum() {
		return cntPageNum;
	}

	public void setCntPageNum(int cntPageNum) {
		this.cntPageNum = cntPageNum;
	}

	public int getRealEndPage() {
		return realEndPage;
	}

	public void setRealEndPage(int realEndPage) {
		this.realEndPage = realEndPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	@Override
	public String toString() {
		return "PageMaker [cri=" + cri + ", totalCount=" + totalCount + ", startPage=" + startPage + ", endPage="
				+ endPage + ", prev=" + prev + ", next=" + next + ", cntPageNum=" + cntPageNum + ", realEndPage="
				+ realEndPage + "]";
	}
	
}
