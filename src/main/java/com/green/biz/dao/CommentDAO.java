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
	
	@Autowired // mybatis ��ü���� (applicationContext > sqlsessiontemplate ��ü�� �ڵ����� �޾ƿ�)
	private SqlSessionTemplate mybatis;
	
	// ��ǰ�� ��� ��ȸ
	public List<ProductCommentVO> getCommentList(int pseq){
		return mybatis.selectList("CommentDAO.getCommentList", pseq);
	}
	
	// ��ǰ�� ����
	public int saveComment(ProductCommentVO vo) {
		return mybatis.insert("CommentDAO.saveComment", vo);
	}
	
	// ��ǰ�� ���� ������Ʈ
	public int updateComment(ProductCommentVO vo) {
		return mybatis.update("CommentDAO.updateComment", vo);
	}
	
	// ��ǰ�� ����
	public int deleteComment(int comment_seq) {
		return mybatis.delete("CommentDAO.deleteComment", comment_seq);
	}
	
	/* ��ǰ�� ���� ��ȸ
	public int countCommentList(int pseq) {
		return mybatis.selectOne("CommentDAO.countCommentList", pseq);
	}*/
	
	/* ��ǰ�� ����¡
	public List<ProductCommentVO> getCommentListWithPaging(Criteria criteria, int pseq){
		HashMap<String, Object> map = new HashMap<>();
		
		map.put("criteria", criteria);
		map.put("pseq", pseq);
		
		return mybatis.selectList("CommentDAO.getCommentListWithPaging", map);
	}
	*/
	
}
