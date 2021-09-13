package com.green.biz.dao;

import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.green.biz.dto.QnaVO;

@Repository
public class QnaDAO {
	
	@Autowired // mybatis 자동객체생성
	private SqlSessionTemplate mybatis;
	
	// 전체 qna 목록 조회
	public List<QnaVO> listQna(String id){
		return mybatis.selectList("QnaDAO.listQna", id);
	}
	
	// 일련번호 별 게시글 한 건 조회
	public QnaVO getQna(int qseq) {
		return mybatis.selectOne("QnaDAO.getQna", qseq);
	}
	
	// 게시글 insert구문
	public void insertQna(QnaVO vo) {
		mybatis.insert("QnaDAO.insertQna", vo);
	}
	
	// 게시판 전체 조회
	public List<QnaVO> listAllQna(){
		return mybatis.selectList("QnaDAO.listAllQna");
	}
	
	// 게시판 답변 처리
	public void updateQna(QnaVO vo) {
		mybatis.update("QnaDAO.updateQna", vo);
	}
}
