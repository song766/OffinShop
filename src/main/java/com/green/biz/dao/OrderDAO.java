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
	
	@Autowired // mybatis��ü����(applicationContext�� �ִ� sqlsessiontemplate��ü �ڵ����� �޾ƿ�)
	private SqlSessionTemplate mybatis;
	
	//��ٱ��� ��ǰ�� �ֹ� ���̺� �ִ´�
	public int selectMaxOseq() {
		return mybatis.selectOne("OrderDAO.selectMaxOseq");
	}
	
	// �ֹ� ���̺� �ֹ�id �־��ش�
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
	
	// ��ȸ�� �� �ֹ� ���� - ���� name(��ȸ�� return�� �ʿ�)
	public int countOrderList(String mname) {
		return mybatis.selectOne("OrderDAO.countOrderList", mname); //�Է°�name
	}
	
	// ��ü �ֹ����� ��ȸ
	public List<OrderVO> listOrder(String key){
		return mybatis.selectList("OrderDAO.listOrder", key);
	}
	
	// �ֹ� �Ϸ�ó��
	public void updateOrderResult(int odseq) {
		mybatis.update("OrderDAO.updateOrderResult", odseq);
	}
	
	// ����¡ ó��
	public List<OrderVO> getorderListWithpaging(Criteria criteria, String key){
		HashMap<String, Object> map = new HashMap<>();
		map.put("criteria", criteria);
		map.put("key", key);
		
		return mybatis.selectList("OrderDAO.orderListWithPaging", map);
	}
}
