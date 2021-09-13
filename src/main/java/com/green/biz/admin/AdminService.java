package com.green.biz.admin;

import com.green.biz.dto.WorkerVO;

public interface AdminService {

	/* 
	 * 관리자 로그인 인증 - 관리자 id의 존재여부 확인
	 */
	int workerCheck(String id, String pwd);

	/* 
	 * 관리자 정보 조회 
	 */
	WorkerVO getEmployee(String id);

}