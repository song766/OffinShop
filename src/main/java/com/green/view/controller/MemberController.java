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

@Controller /* ������ ��Ʈ�ѷ� ��ü ���� */
@SessionAttributes("loginUser") /* loginUser�Ӽ� ���ǿ� ���� */
public class MemberController {
	
	@Autowired /* �ڵ���ü ���� */
	private MemberService memberService;

	@Autowired
	private CartService cartService;
	
	/*
	 * �α��� ȭ�� ǥ�� 
	 */
	@RequestMapping(value="/login_form", method=RequestMethod.GET)
	public String loginView() {
		return "member/login";
	}
	
	/*
	 * ����� �α��� ó��
	 * 
	 *  id, pwd ����� �Է��� �޾ƿ���, -> Ŀ�ǵ� ��ü
	 *  confirmID�� ������ id, pwd ����
	 *  ����� ���� �� ����� ���� ��ȸ�Ͽ� index�� redirect: redirect:/index
	 *  ����� ���� ���� �� : login_fail.jsp ȭ��ǥ��
	 */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginAction(MemberVO vo, Model model, HttpSession session) { // ȭ�鿡�ִ� ������ MemberVO�� ����
		MemberVO loginUser = null;
		int result = memberService.loginID(vo);
		
		if(result == 1) { //�α��� ����� 1�ϰ�� (���� ������ ���)
			loginUser = memberService.getMember(vo.getId()); // ����� ������ ���ͼ� loginUser�� ����
			model.addAttribute("loginUser", loginUser);      // ����� ���� ���� ����� ���ͼ� ���������� ����
						
			// ��ٱ��� ī��Ʈ
			int totalCount = 0;
			totalCount = cartService.listCartid(loginUser.getId());
			model.addAttribute("totalCount", totalCount);
			session.setAttribute("totalCount", totalCount);
			
			return "redirect:/index";
		} else { //�α��� ������ ���
			return "member/login_fail";
		}
	}
	
	
	// ��� ���� ������ �̵� �޼ҵ� - ȭ������ ����ϰ�=StringŸ������, ��ũ�����̵��ϴ°�=GET
	@RequestMapping(value="/contract", method=RequestMethod.GET)
	public String contractView() {
		return "member/contract"; // ������� ȭ��
	}
	
	// ȸ������ ȭ�� ��� �޼ҵ�
	@RequestMapping(value="/join_form", method=RequestMethod.POST)
	public String joinView() {
		return "member/join";
	}
	
	// ���̵� �ߺ� üũ ȭ�� ��� �޼ҵ�
	@RequestMapping(value="/id_check_form", method=RequestMethod.GET)
	public String idCheckView(@RequestParam(value="id", defaultValue="", required=true)
						String id, Model model) {   //required - �ʼ� �Է°����� �ƴ��� 
		model.addAttribute("id", id);
		return "member/idcheck";
	}
	
	/*
	 * ����� ID �ߺ� üũ
	 * POST ��� ó��
	 * URL : /id_check_form
	 */
	@RequestMapping(value="/id_check_form", method=RequestMethod.POST)
	public String idCheckAction(@RequestParam(value="id", defaultValue="", required=true)
	String id, Model model) {
		// �Էµ� ID�� ���� getMember() ���񽺷� ��ȸ�� ��
		MemberVO user = memberService.getMember(id);
		// �����Ͱ� ������ message �Ӽ��� 1�� ����
		// �����Ͱ� ������ message �Ӽ��� -1�� ����
		if(user != null) {// ����� ID�� ����
			model.addAttribute("message", 1);
		} else {
			model.addAttribute("message", -1);
		}
		// id���� model�� �����Ͽ�
		model.addAttribute("id", id);
		
		//member/idcheck.jsp ȭ�� ����
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
	 * �����ȣ ã�� ȭ�� ��û ó��
	 */
	@RequestMapping(value="/find_zip_num", method=RequestMethod.GET)
	public String findZipNumView() {
		return "member/findZipNum"; //ȭ�鸸 �Ѱ���
	}
	
	/*
	 * �� �̸����� �����ȣ ã�� ��ȸó�� (submitó��) 
	 * ���� address ��ü�����ϱ� �Ű������� AddressVO�� ����
	 */
	@RequestMapping(value="/find_zip_num", method=RequestMethod.POST)
	public String findZipNumAction(AddressVO vo, Model model) {
		List<AddressVO> addrList = memberService.selectAddressByDong(vo.getDong());
		
		model.addAttribute("addressList", addrList);
		return "member/findZipNum";
	}
	
	
	/*
	 * �α׾ƿ�
	 */
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(SessionStatus status, HttpSession session) {
		status.setComplete(); // ���� ������ �����Ѵٴ� �ǹ�
		session.invalidate(); // ���� ��ü ������
		
		return "member/login";
	}
	
	/*
	 * ���̵�, ��й�ȣã�� ���� 
	 */
	@RequestMapping(value="/find_id_form", method=RequestMethod.GET)
	public String findIdView() {
		return "member/findIdAndPassword";
	}
	
	/*
	 * IDã��  
	 */
	@RequestMapping(value="/find_id", method=RequestMethod.GET)
	public String findIdAction(MemberVO vo, Model model) {
		
		MemberVO member = memberService.getMemberByNameAndEmail(vo);
		
		if(member != null) { // ���̵� �����ϴ� ���
			// ����ڰ� �����ϸ� message�Ӽ��� 1�� �Ҵ��ϰ� id�� �Ѱ���
			model.addAttribute("message", 1);
			model.addAttribute("id", member.getId());
		} else {
			model.addAttribute("message", -1);
		}
		return "member/findResult";
	}
	
	/*
	 * password ã��/���� 
	 */
	@RequestMapping(value="/find_password", method=RequestMethod.GET)
	public String findPassword(MemberVO vo, Model model) {
		
		MemberVO member = memberService.findPassword(vo);
		
		if(member != null) { // ���̵� �����ϴ� ���
			// ����ڰ� �����ϸ� message�Ӽ��� 1�� �Ҵ��ϰ� id�� �Ѱ���
			model.addAttribute("message", 1);
			model.addAttribute("id", member.getId());
		} else {
			model.addAttribute("message", -1);
		}
		return "member/findPwdResult";
	}
	
	@RequestMapping(value="/change_password", method=RequestMethod.POST)
	public String changePassword(MemberVO vo, Model model) {
		memberService.changePassword(vo); // void�⶧���� return�� ����
		
		// �˾�â �ݱ�
		return "member/close";
	}
	
	
}
