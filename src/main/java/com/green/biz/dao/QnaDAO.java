package com.green.biz.dao;

import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.green.biz.dto.QnaVO;

@Repository
public class QnaDAO {
	
	@Autowired // mybatis �ڵ���ü����
	private SqlSessionTemplate mybatis;
	
	// ��ü qna ��� ��ȸ
	public List<QnaVO> listQna(String id){
		return mybatis.selectList("QnaDAO.listQna", id);
	}
	
	// �Ϸù�ȣ �� �Խñ� �� �� ��ȸ
	public QnaVO getQna(int qseq) {
		return mybatis.selectOne("QnaDAO.getQna", qseq);
	}
	
	// �Խñ� insert����
	public void insertQna(QnaVO vo) {
		mybatis.insert("QnaDAO.insertQna", vo);
	}
	
	// �Խ��� ��ü ��ȸ
	public List<QnaVO> listAllQna(){
		return mybatis.selectList("QnaDAO.listAllQna");
	}
	
	// �Խ��� �亯 ó��
	public void updateQna(QnaVO vo) {
		mybatis.update("QnaDAO.updateQna", vo);
	}
}
