package com.green.biz.dao;

import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.dto.AddressVO;
import com.green.biz.dto.MemberVO;

@Repository
public class MemberDAO {

	@Autowired // mybatis ��ü���� (applicationContext�� �ִ� sqlsessiontemplate ��ü�� �ڵ����� �޾ƿ�)
	private SqlSessionTemplate mybatis;
	
	// ȸ�� id�� �������� ȸ�� ������ ��ȸ
	public MemberVO getMember(String id) {
		return mybatis.selectOne("MemberDAO.getMember", id); //member-mapping�� mapping namespace ��ġ
	}
	
	// ȸ��ID ���� (���� ���� ��ȸ, ȸ��id�� �ִ��� ������)
	public int confirmID(String id) {
		String pwd = mybatis.selectOne("MemberDAO.confirmID", id); //member-mapping�� namespace=confirmID
		
		if(pwd != null) { // ��й�ȣ�� ������ return 1
			return 1;
		} else {          // ��й�ȣ�� ������ return -1
			return -1;
		}
	}
	
	//ȸ�� �α���
	public int loginID(MemberVO vo) {
		int result = -1;
		String pwd = null;
		
		pwd = mybatis.selectOne("MemberDAO.confirmID", vo.getId());
		
		// DB���� ��ȸ�� password�� ����ڰ� �Է��� password�� ��
		if (pwd == null) { // ��й�ȣ�� ��ġ�ϴ� ���
			result = -1;
		} else if (pwd.equals(vo.getPwd())) { // id�� pwd�� ��ġ�ϴ� ���
			result = 1;
		} else {
			result =0; // ��й�ȣ�� ��ġ���� �ʴ� ���
		}
		
		return result;
	}
	
	// ȸ�� ��ϸ޼ҵ�
	public void insertMember(MemberVO vo) { //������ �� �ޱ� ���� MemberVOŸ��
		mybatis.insert("MemberDAO.insertMember", vo);
	}
	
	/* �� �̸����� �����ȣ ã��*/
	public List<AddressVO> selectAddressByDong(String dong){
		return mybatis.selectList("MemberDAO.selectAddressByDong", dong);
	}
	
	// ������� �̸�(name)�� email�� �������� ����� ���� ��ȸ
	public MemberVO getMemberByNameAndEmail(MemberVO vo) {
		return mybatis.selectOne("MemberDAO.getMemberByNameAndEmail", vo);
	}
	
	// ��й�ȣ ã�� - �̸�, �̸���, ���̵� ���ǿ� �´� ��� ��ȸ
	public MemberVO findPassword(MemberVO vo) {
		return mybatis.selectOne("MemberDAO.findPassword", vo);
	}
	
	// ��й�ȣ ���� ������Ʈ
	public void changePassword(MemberVO vo) {
		mybatis.update("MemberDAO.changePassword", vo);
	}
	
	// ��ü ȸ�� ��ȸ
	public List<MemberVO> listMember(String name) {
		return mybatis.selectList("MemberDAO.listMember", name);
	}
	
}
