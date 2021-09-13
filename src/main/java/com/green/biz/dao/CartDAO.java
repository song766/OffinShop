package com.green.biz.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.green.biz.dto.CartVO;

@Repository
public class CartDAO {
	
	@Autowired // mybatis 자동객체생성을 위한 Autowired
	private SqlSessionTemplate mybatis;
	
	// 장바구니에 담기 저장
	public void insertCart(CartVO vo) {
		mybatis.insert("CartDAO.insertCart", vo);
	}
	
	// 상품리스트에서 장바구니에 담기 저장
	public void insertCartProduct(CartVO vo) {
		mybatis.insert("CartDAO.insertCartProduct", vo);
	}
	
	// 장바구니 목록 조회
	public List<CartVO> listCart(String userid){
		return mybatis.selectList("CartDAO.listCart", userid);
	}
	
	// 회원 장바구니 갯수 조회
	public int listCartid(String userid) {
		return mybatis.selectOne("CartDAO.listCartid", userid);
	}
	
	// 장바구니 삭제 기능
	public void deleteCart(int cseq) {
		mybatis.delete("CartDAO.deleteCart", cseq);
	}
	
	// 장바구니 항목을 '처리'로 업데이트
	public void updateCart(int cseq) {
		mybatis.update("CartDAO.updateCart", cseq);
	}
}
