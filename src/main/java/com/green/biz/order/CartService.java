package com.green.biz.order;

import java.util.List;

import com.green.biz.dto.CartVO;

public interface CartService {

	// 장바구니에 담기 저장
	void insertCart(CartVO vo);
	
	// 상품리스트에서 장바구니에 담기 저장
	void insertCartProduct(CartVO vo);

	// 장바구니 목록 조회
	List<CartVO> listCart(String userid);
	
	// 회원 장바구니 갯수 조회
	int listCartid(String userid);

	// 장바구니 삭제 기능
	void deleteCart(int cseq);

	// 장바구니 항목을 '처리'로 업데이트
	void updateCart(int cseq);

}