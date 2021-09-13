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
	 * �Է� �Ķ����(�Ű�����):
	 *   id - ����� �Է�id
	 *   pwd - ����� �Է� �н�����
	 */
	@Override
	public int workerCheck(String id, String pwd) {
		int result = -1;  // ����� -1 �ʱ�ȭ
		
		String pwd_in_db = adminDao.workerCheck(id); // db���̺� �����ϴ� ������ id�� pwd��ȯ
		
		if(pwd_in_db == null) { // db�� ���� pwd�� ��
			result = -1; // id�� �������� ����
		} else {
			if(pwd.equals(pwd_in_db)) { // �Է�pwd = db���̺� pwd ��ġ
				result = 1;  // ����� 1
			} else { // ��ġ���� ������
				result = 0;
			}
		}
		return result; // ���� ����� ����
	}

	@Override
	public WorkerVO getEmployee(String id) {
		return adminDao.getEmployee(id);
	}


}
