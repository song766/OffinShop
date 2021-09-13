package com.green.biz.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.green.biz.dto.WorkerVO;

@Repository
public class AdminDAO {
	
	/*
	 * �ڵ���ü���� 
	 */
	@Autowired
	private SqlSessionTemplate mybatis;
		
	/* 
	 * ������ �α��� ���� - ������ id�� ���翩�� Ȯ��
	 */
	public String workerCheck(String id) { // id�� ���� ���ǰ� = 1�� selectOneȣ��
		return mybatis.selectOne("AdminDAO.workerCheck", id);
	}
	
	/* 
	 * ������ ���� ��ȸ 
	 */
	public WorkerVO getEmployee(String id) {
		return mybatis.selectOne("AdminDAO.getEmployee", id);
	}
	
}
