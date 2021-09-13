package com.green.biz.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.dto.OrderVO;
import com.green.biz.utils.Criteria;

@Repository
public class OrderDAO {
	
	@Autowired // mybatis객체생성(applicationContext에 있는 sqlsessiontemplate객체 자동으로 받아옴)
	private SqlSessionTemplate mybatis;
	
	//장바구니 상품을 주문 테이블에 넣는다
	public int selectMaxOseq() {
		return mybatis.selectOne("OrderDAO.selectMaxOseq");
	}
	
	// 주문 테이블에 주문id 넣어준다
	public void insertOrder(OrderVO vo) {
		mybatis.insert("OrderDAO.insertOrder", vo);
	}
	
	public void insertOrderDetail(OrderVO vo) {
		mybatis.insert("OrderDAO.insertOrderDetail",vo);
	}
	
	public List<OrderVO> listOrderById(OrderVO vo) {
		return mybatis.selectList("OrderDAO.listOrderById", vo);
	}
	
	public List<Integer> selectSeqOrdering(OrderVO vo){
		return mybatis.selectList("OrderDAO.selectSeqOrdering", vo);
	}
	
	// 조회한 총 주문 갯수 - 조건 name(조회엔 return값 필요)
	public int countOrderList(String mname) {
		return mybatis.selectOne("OrderDAO.countOrderList", mname); //입력값name
	}
	
	// 전체 주문내역 조회
	public List<OrderVO> listOrder(String key){
		return mybatis.selectList("OrderDAO.listOrder", key);
	}
	
	// 주문 완료처리
	public void updateOrderResult(int odseq) {
		mybatis.update("OrderDAO.updateOrderResult", odseq);
	}
	
	// 페이징 처리
	public List<OrderVO> getorderListWithpaging(Criteria criteria, String key){
		HashMap<String, Object> map = new HashMap<>();
		map.put("criteria", criteria);
		map.put("key", key);
		
		return mybatis.selectList("OrderDAO.orderListWithPaging", map);
	}
}
