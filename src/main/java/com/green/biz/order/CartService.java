package com.green.biz.order;

import java.util.List;

import com.green.biz.dto.CartVO;

public interface CartService {

	// ��ٱ��Ͽ� ��� ����
	void insertCart(CartVO vo);
	
	// ��ǰ����Ʈ���� ��ٱ��Ͽ� ��� ����
	void insertCartProduct(CartVO vo);

	// ��ٱ��� ��� ��ȸ
	List<CartVO> listCart(String userid);
	
	// ȸ�� ��ٱ��� ���� ��ȸ
	int listCartid(String userid);

	// ��ٱ��� ���� ���
	void deleteCart(int cseq);

	// ��ٱ��� �׸��� 'ó��'�� ������Ʈ
	void updateCart(int cseq);

}