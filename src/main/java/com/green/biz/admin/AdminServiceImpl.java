package com.green.biz.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.green.biz.dao.AdminDAO;
import com.green.biz.dto.WorkerVO;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO adminDao;
	
	/*
	 * 입력 파라미터(매개변수):
	 *   id - 사용자 입력id
	 *   pwd - 사용자 입력 패스워드
	 */
	@Override
	public int workerCheck(String id, String pwd) {
		int result = -1;  // 결과값 -1 초기화
		
		String pwd_in_db = adminDao.workerCheck(id); // db테이블에 존재하는 관리자 id의 pwd반환
		
		if(pwd_in_db == null) { // db에 없는 pwd일 때
			result = -1; // id가 존재하지 않음
		} else {
			if(pwd.equals(pwd_in_db)) { // 입력pwd = db테이블 pwd 일치
				result = 1;  // 결과값 1
			} else { // 일치하지 않으면
				result = 0;
			}
		}
		return result; // 최종 결과값 리턴
	}

	@Override
	public WorkerVO getEmployee(String id) {
		return adminDao.getEmployee(id);
	}


}
