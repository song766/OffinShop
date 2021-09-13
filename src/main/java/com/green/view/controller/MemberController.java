package com.green.view.controller;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import com.green.biz.dto.AddressVO;
import com.green.biz.dto.MemberVO;
import com.green.biz.member.MemberService;
import com.green.biz.order.CartService;
import com.green.biz.product.ProductService;

@Controller /* 스프링 컨트롤러 객체 생성 */
@SessionAttributes("loginUser") /* loginUser속성 세션에 저장 */
public class MemberController {
	
	@Autowired /* 자동객체 생성 */
	private MemberService memberService;

	@Autowired
	private CartService cartService;
	
	/*
	 * 로그인 화면 표시 
	 */
	@RequestMapping(value="/login_form", method=RequestMethod.GET)
	public String loginView() {
		return "member/login";
	}
	
	/*
	 * 사용자 로그인 처리
	 * 
	 *  id, pwd 사용자 입력을 받아오고, -> 커맨드 객체
	 *  confirmID를 가지고 id, pwd 인증
	 *  사용자 인증 후 사용자 정보 조회하여 index로 redirect: redirect:/index
	 *  사용자 인증 실패 시 : login_fail.jsp 화면표시
	 */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginAction(MemberVO vo, Model model, HttpSession session) { // 화면에있는 내용을 MemberVO에 저장
		MemberVO loginUser = null;
		int result = memberService.loginID(vo);
		
		if(result == 1) { //로그인 결과가 1일경우 (인증 성공한 경우)
			loginUser = memberService.getMember(vo.getId()); // 사용자 정보를 얻어와서 loginUser에 담음
			model.addAttribute("loginUser", loginUser);      // 사용자 정보 얻어온 결과값 얻어와서 세션정보에 저장
						
			// 장바구니 카운트
			int totalCount = 0;
			totalCount = cartService.listCartid(loginUser.getId());
			model.addAttribute("totalCount", totalCount);
			session.setAttribute("totalCount", totalCount);
			
			return "redirect:/index";
		} else { //로그인 실패한 경우
			return "member/login_fail";
		}
	}
	
	
	// 약관 동의 페이지 이동 메소드 - 화면으로 출력하것=String타입으로, 링크눌러이동하는것=GET
	@RequestMapping(value="/contract", method=RequestMethod.GET)
	public String contractView() {
		return "member/contract"; // 약관동의 화면
	}
	
	// 회원가입 화면 출력 메소드
	@RequestMapping(value="/join_form", method=RequestMethod.POST)
	public String joinView() {
		return "member/join";
	}
	
	// 아이디 중복 체크 화면 출력 메소드
	@RequestMapping(value="/id_check_form", method=RequestMethod.GET)
	public String idCheckView(@RequestParam(value="id", defaultValue="", required=true)
						String id, Model model) {   //required - 필수 입력값인지 아닌지 
		model.addAttribute("id", id);
		return "member/idcheck";
	}
	
	/*
	 * 사용자 ID 중복 체크
	 * POST 방식 처리
	 * URL : /id_check_form
	 */
	@RequestMapping(value="/id_check_form", method=RequestMethod.POST)
	public String idCheckAction(@RequestParam(value="id", defaultValue="", required=true)
	String id, Model model) {
		// 입력된 ID를 갖고 getMember() 서비스로 조회한 후
		MemberVO user = memberService.getMember(id);
		// 데이터가 있으면 message 속성에 1을 설정
		// 데이터가 없으면 message 속성에 -1을 설정
		if(user != null) {// 사용자 ID가 존재
			model.addAttribute("message", 1);
		} else {
			model.addAttribute("message", -1);
		}
		// id값을 model에 저장하여
		model.addAttribute("id", id);
		
		//member/idcheck.jsp 화면 리턴
		return "member/idcheck";
	}
	
	@RequestMapping(value="/id_check_confirmed", method=RequestMethod.GET)
	public String idCheckConfirmed(MemberVO vo, Model model) {
		
		model.addAttribute("id", vo.getId());
		model.addAttribute("reid", vo.getId());
		
		return "member/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String joinAction(@RequestParam(value="addr1") String addr1,
							 @RequestParam(value="addr2") String addr2,
							 MemberVO vo) {
		
		vo.setAddress(addr1 + " " + addr2);
		memberService.insertMember(vo);
		
		return "member/login";
	}
	
	/*
	 * 우편번호 찾기 화면 요청 처리
	 */
	@RequestMapping(value="/find_zip_num", method=RequestMethod.GET)
	public String findZipNumView() {
		return "member/findZipNum"; //화면만 넘겨줌
	}
	
	/*
	 * 동 이름으로 우편번호 찾기 조회처리 (submit처리) 
	 * 동은 address 객체에오니까 매개변수에 AddressVO를 넣음
	 */
	@RequestMapping(value="/find_zip_num", method=RequestMethod.POST)
	public String findZipNumAction(AddressVO vo, Model model) {
		List<AddressVO> addrList = memberService.selectAddressByDong(vo.getDong());
		
		model.addAttribute("addressList", addrList);
		return "member/findZipNum";
	}
	
	
	/*
	 * 로그아웃
	 */
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(SessionStatus status, HttpSession session) {
		status.setComplete(); // 현재 세션을 종료한다는 의미
		session.invalidate(); // 세션 전체 날리기
		
		return "member/login";
	}
	
	/*
	 * 아이디, 비밀번호찾기 구현 
	 */
	@RequestMapping(value="/find_id_form", method=RequestMethod.GET)
	public String findIdView() {
		return "member/findIdAndPassword";
	}
	
	/*
	 * ID찾기  
	 */
	@RequestMapping(value="/find_id", method=RequestMethod.GET)
	public String findIdAction(MemberVO vo, Model model) {
		
		MemberVO member = memberService.getMemberByNameAndEmail(vo);
		
		if(member != null) { // 아이디가 존재하는 경우
			// 사용자가 존재하면 message속성에 1을 할당하고 id를 넘겨줌
			model.addAttribute("message", 1);
			model.addAttribute("id", member.getId());
		} else {
			model.addAttribute("message", -1);
		}
		return "member/findResult";
	}
	
	/*
	 * password 찾기/변경 
	 */
	@RequestMapping(value="/find_password", method=RequestMethod.GET)
	public String findPassword(MemberVO vo, Model model) {
		
		MemberVO member = memberService.findPassword(vo);
		
		if(member != null) { // 아이디가 존재하는 경우
			// 사용자가 존재하면 message속성에 1을 할당하고 id를 넘겨줌
			model.addAttribute("message", 1);
			model.addAttribute("id", member.getId());
		} else {
			model.addAttribute("message", -1);
		}
		return "member/findPwdResult";
	}
	
	@RequestMapping(value="/change_password", method=RequestMethod.POST)
	public String changePassword(MemberVO vo, Model model) {
		memberService.changePassword(vo); // void기때문에 return값 없음
		
		// 팝업창 닫기
		return "member/close";
	}
	
	
}
