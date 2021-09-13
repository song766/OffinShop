package com.green.biz.qna;

import java.util.List;
import com.green.biz.dto.QnaVO;

public interface QnaService {

	// 전체 qna 목록 조회
	List<QnaVO> listQna(String id);

	// 일련번호 별 게시글 한 건 조회
	QnaVO getQna(int qseq);

	// 게시글 insert구문
	void insertQna(QnaVO vo);
	
	// 게시판 전체 조회
	List<QnaVO> listAllQna();
	
	// 게시판 답변 처리
	public void updateQna(QnaVO vo);

}
