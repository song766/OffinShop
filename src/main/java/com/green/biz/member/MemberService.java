package com.green.biz.member;

import java.util.List;
import com.green.biz.dto.AddressVO;
import com.green.biz.dto.MemberVO;

public interface MemberService {
	
	/* 회원 로그인 */
	public int loginID(MemberVO vo);

	/* 회원 id를 조건으로 회원 상세정보 조회 */
	MemberVO getMember(String id);

	/* 회원ID 인증 (존재 여부 조회) */ 
	int confirmID(String id);

	/* 회원 등록메소드(추가) */
	void insertMember(MemberVO vo);
	
	/* 동, 이름으로 우편번호 찾기 */
	List<AddressVO> selectAddressByDong(String dong);
	
	/* 사용자 이름, 이메일 조건으로 사용자 정보 조회 */
	public MemberVO getMemberByNameAndEmail(MemberVO vo);
	
	/* 사용자 이메일, 이름, 아이디 조건에 맞는 멤버 조회 (비밀번호 찾기) */
	public MemberVO findPassword(MemberVO vo);
	
	/* 사용자 암호 변경 */
	public void changePassword(MemberVO vo);
	
	/* 전체 회원 조회 */
	public List<MemberVO> listMember(String name);
}