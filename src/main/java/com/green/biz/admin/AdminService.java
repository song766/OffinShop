package com.green.biz.admin;

import com.green.biz.dto.WorkerVO;

public interface AdminService {

	/* 
	 * ������ �α��� ���� - ������ id�� ���翩�� Ȯ��
	 */
	int workerCheck(String id, String pwd);

	/* 
	 * ������ ���� ��ȸ 
	 */
	WorkerVO getEmployee(String id);

}