package com.green.biz.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.dto.ProductCommentVO;
import com.green.biz.utils.Criteria;

@Repository
public class CommentDAO {
	
	@Autowired // mybatis 객체생성 (applicationContext > sqlsessiontemplate 객체를 자동으로 받아옴)
	private SqlSessionTemplate mybatis;
	
	// 상품평 목록 조회
	public List<ProductCommentVO> getCommentList(int pseq){
		return mybatis.selectList("CommentDAO.getCommentList", pseq);
	}
	
	// 상품평 저장
	public int saveComment(ProductCommentVO vo) {
		return mybatis.insert("CommentDAO.saveComment", vo);
	}
	
	// 상품평 수정 업데이트
	public int updateComment(ProductCommentVO vo) {
		return mybatis.update("CommentDAO.updateComment", vo);
	}
	
	// 상품평 삭제
	public int deleteComment(int comment_seq) {
		return mybatis.delete("CommentDAO.deleteComment", comment_seq);
	}
	
	/* 상품평 갯수 조회
	public int countCommentList(int pseq) {
		return mybatis.selectOne("CommentDAO.countCommentList", pseq);
	}*/
	
	/* 상품평 페이징
	public List<ProductCommentVO> getCommentListWithPaging(Criteria criteria, int pseq){
		HashMap<String, Object> map = new HashMap<>();
		
		map.put("criteria", criteria);
		map.put("pseq", pseq);
		
		return mybatis.selectList("CommentDAO.getCommentListWithPaging", map);
	}
	*/
	
}
