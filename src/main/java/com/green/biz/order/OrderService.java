package com.green.biz.order;

import java.util.List;

import com.green.biz.dto.OrderVO;
import com.green.biz.utils.Criteria;

public interface OrderService {

	int selectMaxOseq();

	int insertOrder(OrderVO vo);

	void insertOrderDetail(OrderVO vo);
	
	public List<OrderVO> listOrderById(OrderVO vo);
	
	public List<Integer> selectSeqOrdering(OrderVO vo);
	
	// ��ȸ�� �� ��ǰ ���� - ���� name(��ȸ�� return�� �ʿ�)
	int countOrderList(String mname);
	
	public List<OrderVO> listOrder(String key);
	
	public void updateOrderResult(int odseq);
	
	// ����¡ ó��
	public List<OrderVO> getorderListWithpaging(Criteria criteria, String key);

}