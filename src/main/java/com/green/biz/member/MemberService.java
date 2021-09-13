package com.green.biz.member;

import java.util.List;
import com.green.biz.dto.AddressVO;
import com.green.biz.dto.MemberVO;

public interface MemberService {
	
	/* ȸ�� �α��� */
	public int loginID(MemberVO vo);

	/* ȸ�� id�� �������� ȸ�� ������ ��ȸ */
	MemberVO getMember(String id);

	/* ȸ��ID ���� (���� ���� ��ȸ) */ 
	int confirmID(String id);

	/* ȸ�� ��ϸ޼ҵ�(�߰�) */
	void insertMember(MemberVO vo);
	
	/* ��, �̸����� �����ȣ ã�� */
	List<AddressVO> selectAddressByDong(String dong);
	
	/* ����� �̸�, �̸��� �������� ����� ���� ��ȸ */
	public MemberVO getMemberByNameAndEmail(MemberVO vo);
	
	/* ����� �̸���, �̸�, ���̵� ���ǿ� �´� ��� ��ȸ (��й�ȣ ã��) */
	public MemberVO findPassword(MemberVO vo);
	
	/* ����� ��ȣ ���� */
	public void changePassword(MemberVO vo);
	
	/* ��ü ȸ�� ��ȸ */
	public List<MemberVO> listMember(String name);
}