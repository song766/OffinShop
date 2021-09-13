package com.green.biz.order.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.biz.dao.OrderDAO;
import com.green.biz.dto.CartVO;
import com.green.biz.dto.OrderVO;
import com.green.biz.order.CartService;
import com.green.biz.order.OrderService;
import com.green.biz.utils.Criteria;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDAO orderDao;
	
	@Autowired
	private CartService cartService; // 장바구니 목록읽어올때 필요한 객체 생성
	
	@Override
	public int selectMaxOseq() {
		return orderDao.selectMaxOseq();
	}
	
	/*
	 * 주문정보 저장 
	 */
	@Override
	public int insertOrder(OrderVO vo) {
		int oseq = 0;
		
		// 주문테이블에서 다음 oseq번호를 읽어온다.(selectMaxOseq를 읽어온다는 뜻)
		oseq =  selectMaxOseq(); // 최대 주문번호 +1이 다음주문번호가 되는 것
		
		// 주문 테이블에 주문번호와 회원 ID 저장
		vo.setOseq(oseq);
		orderDao.insertOrder(vo);
		
		// 장바구니 목록을 읽어온다. userId를 갖고와야하는데 위에 객체가 vo니까 vo.getId()를 호출
		List<CartVO> cartList = cartService.listCart(vo.getId());
		
		// 장바구니의 내역을 order_detail 테이블에 저장
		for(CartVO cartVO : cartList) {
			OrderVO order = new OrderVO();
			order.setOseq(oseq);
			order.setPseq(cartVO.getPseq());
			order.setQuantity(cartVO.getQuantity());
			
			insertOrderDetail(order);
			
			// 장바구니 테이블 업데이트
			cartService.updateCart(cartVO.getCseq());
		}
		
		return oseq;
	}

	@Override
	public void insertOrderDetail(OrderVO vo) {
		orderDao.insertOrderDetail(vo);
	}

	@Override
	public List<OrderVO> listOrderById(OrderVO vo) {
		return orderDao.listOrderById(vo);
	}

	@Override
	public List<Integer> selectSeqOrdering(OrderVO vo) {
		return orderDao.selectSeqOrdering(vo);
	}
	
	// 전체 주문 내역 조회
	@Override
	public List<OrderVO> listOrder(String key) {
		return orderDao.listOrder(key);
	}
	
	// 주문 완료 처리
	@Override
	public void updateOrderResult(int odseq) {
		orderDao.updateOrderResult(odseq);		
	}

	@Override
	public List<OrderVO> getorderListWithpaging(Criteria criteria, String key) {
		return orderDao.getorderListWithpaging(criteria, key);
	}

	@Override
	public int countOrderList(String mname) {
		return orderDao.countOrderList(mname);
	}

}
