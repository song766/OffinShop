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
	
	// 조회한 총 상품 갯수 - 조건 name(조회엔 return값 필요)
	int countOrderList(String mname);
	
	public List<OrderVO> listOrder(String key);
	
	public void updateOrderResult(int odseq);
	
	// 페이징 처리
	public List<OrderVO> getorderListWithpaging(Criteria criteria, String key);

}