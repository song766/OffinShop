package com.green.view.controller;

import java.util.*;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.green.biz.dto.MemberVO;
import com.green.biz.dto.OrderVO;
import com.green.biz.dto.QnaVO;
import com.green.biz.qna.QnaService;

@Controller
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@RequestMapping(value="/qna_list", method=RequestMethod.GET)
	public String listQna(HttpSession session, Model model) {
		// 세션에 저장된 로그인 정보를 읽어옴
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		if (loginUser == null) {
			return "member/login"; //로그인이 안되어있을 경우
		} else {
			// 로그인성공시 qnaService를 통해 listQna호출
			List<QnaVO> qnaList = qnaService.listQna(loginUser.getId());
			
			model.addAttribute("qnaList", qnaList);
			
			return "qna/qnaList";
		}
	}
	
	//QnA 게시글 등록화면 표시 메소드
	@RequestMapping(value="/qna_write_form", method=RequestMethod.GET)
	public String qnaWriteView(HttpSession session) {
		// 세션에 저장된 로그인 정보를 읽어옴
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		if (loginUser == null) {
			return "member/login"; //로그인이 안되어있을 경우
		} else {
			return "qna/qnaWrite"; //Qna 게시글 등록화면 표시
		}
	}
	
	/* QnA 게시글 등록 */
	@RequestMapping(value="/qna_write", method=RequestMethod.POST)
	public String qnaWriteAction(QnaVO vo, HttpSession session) {
		// 세션에 저장된 로그인 정보를 읽어옴
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		if (loginUser == null) {
			return "member/login"; //로그인이 안되어있을 경우
		} else {
			//화면에서 읽어온 사용자 아이디 정보를 QnaVO객체에 저장 (자동적으로 subject, content 등록)
			vo.setId(loginUser.getId());
			
			//qnaService객체에서 insertQna(qnaVO,id)를 호출하여 게시글 저장
			qnaService.insertQna(vo);
			
			//등록이 성공하면 게시글 목록화면으로 이동 - 화면만 출력하면안되고 화면을 채워서 보내줘야하기때문에 redirect
			return "redirect:qna_list";
		}
	}
	
	/* Qna 게시글 상세보기 */
	@RequestMapping(value="/qna_view", method=RequestMethod.GET)
	public String qnaView(QnaVO vo, HttpSession session, Model model) {
		// 세션에 저장된 로그인 정보를 읽어옴
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		if (loginUser == null) {
			return "member/login"; //로그인이 안되어있을 경우
		} else {
			//qnaService객체에서 getQna()를 호출해 조회결과를 qnaVO키로 모델객체에 저장
			// 개별 게시글 저장
			QnaVO qna = qnaService.getQna(vo.getQseq());
			
			model.addAttribute("qnaVO", qna); // 화면에서 qnaVO로 받아왔기때문에
			
			return "qna/qnaView";
		}
	}

	
}
