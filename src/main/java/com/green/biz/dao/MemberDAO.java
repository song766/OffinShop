package com.green.biz.dao;

import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.dto.AddressVO;
import com.green.biz.dto.MemberVO;

@Repository
public class MemberDAO {

	@Autowired // mybatis 객체생성 (applicationContext에 있는 sqlsessiontemplate 객체를 자동으로 받아옴)
	private SqlSessionTemplate mybatis;
	
	// 회원 id를 조건으로 회원 상세정보 조회
	public MemberVO getMember(String id) {
		return mybatis.selectOne("MemberDAO.getMember", id); //member-mapping의 mapping namespace 일치
	}
	
	// 회원ID 인증 (존재 여부 조회, 회원id가 있는지 없는지)
	public int confirmID(String id) {
		String pwd = mybatis.selectOne("MemberDAO.confirmID", id); //member-mapping의 namespace=confirmID
		
		if(pwd != null) { // 비밀번호가 있으면 return 1
			return 1;
		} else {          // 비밀번호가 없으면 return -1
			return -1;
		}
	}
	
	//회원 로그인
	public int loginID(MemberVO vo) {
		int result = -1;
		String pwd = null;
		
		pwd = mybatis.selectOne("MemberDAO.confirmID", vo.getId());
		
		// DB에서 조회한 password와 사용자가 입력한 password를 비교
		if (pwd == null) { // 비밀번호가 일치하는 경우
			result = -1;
		} else if (pwd.equals(vo.getPwd())) { // id와 pwd가 일치하는 경우
			result = 1;
		} else {
			result =0; // 비밀번호가 일치하지 않는 경우
		}
		
		return result;
	}
	
	// 회원 등록메소드
	public void insertMember(MemberVO vo) { //데이터 다 받기 위해 MemberVO타입
		mybatis.insert("MemberDAO.insertMember", vo);
	}
	
	/* 동 이름으로 우편번호 찾기*/
	public List<AddressVO> selectAddressByDong(String dong){
		return mybatis.selectList("MemberDAO.selectAddressByDong", dong);
	}
	
	// 사용자의 이름(name)과 email을 조건으로 사용자 정보 조회
	public MemberVO getMemberByNameAndEmail(MemberVO vo) {
		return mybatis.selectOne("MemberDAO.getMemberByNameAndEmail", vo);
	}
	
	// 비밀번호 찾기 - 이름, 이메일, 아이디 조건에 맞는 멤버 조회
	public MemberVO findPassword(MemberVO vo) {
		return mybatis.selectOne("MemberDAO.findPassword", vo);
	}
	
	// 비밀번호 변경 업데이트
	public void changePassword(MemberVO vo) {
		mybatis.update("MemberDAO.changePassword", vo);
	}
	
	// 전체 회원 조회
	public List<MemberVO> listMember(String name) {
		return mybatis.selectList("MemberDAO.listMember", name);
	}
	
}
