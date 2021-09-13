package com.green.biz.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.green.biz.dto.WorkerVO;

@Repository
public class AdminDAO {
	
	/*
	 * 자동객체생성 
	 */
	@Autowired
	private SqlSessionTemplate mybatis;
		
	/* 
	 * 관리자 로그인 인증 - 관리자 id의 존재여부 확인
	 */
	public String workerCheck(String id) { // id에 대한 조건값 = 1개 selectOne호출
		return mybatis.selectOne("AdminDAO.workerCheck", id);
	}
	
	/* 
	 * 관리자 정보 조회 
	 */
	public WorkerVO getEmployee(String id) {
		return mybatis.selectOne("AdminDAO.getEmployee", id);
	}
	
}
