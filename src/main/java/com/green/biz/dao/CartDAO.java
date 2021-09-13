package com.green.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.green.biz.dto.CartVO;

@Repository
public class CartDAO {
	
	@Autowired // mybatis �ڵ���ü������ ���� Autowired
	private SqlSessionTemplate mybatis;
	
	// ��ٱ��Ͽ� ��� ����
	public void insertCart(CartVO vo) {
		mybatis.insert("CartDAO.insertCart", vo);
	}
	
	// ��ǰ����Ʈ���� ��ٱ��Ͽ� ��� ����
	public void insertCartProduct(CartVO vo) {
		mybatis.insert("CartDAO.insertCartProduct", vo);
	}
	
	// ��ٱ��� ��� ��ȸ
	public List<CartVO> listCart(String userid){
		return mybatis.selectList("CartDAO.listCart", userid);
	}
	
	// ȸ�� ��ٱ��� ���� ��ȸ
	public int listCartid(String userid) {
		return mybatis.selectOne("CartDAO.listCartid", userid);
	}
	
	// ��ٱ��� ���� ���
	public void deleteCart(int cseq) {
		mybatis.delete("CartDAO.deleteCart", cseq);
	}
	
	// ��ٱ��� �׸��� 'ó��'�� ������Ʈ
	public void updateCart(int cseq) {
		mybatis.update("CartDAO.updateCart", cseq);
	}
}
