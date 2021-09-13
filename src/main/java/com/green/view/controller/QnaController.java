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
		// ���ǿ� ����� �α��� ������ �о��
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		if (loginUser == null) {
			return "member/login"; //�α����� �ȵǾ����� ���
		} else {
			// �α��μ����� qnaService�� ���� listQnaȣ��
			List<QnaVO> qnaList = qnaService.listQna(loginUser.getId());
			
			model.addAttribute("qnaList", qnaList);
			
			return "qna/qnaList";
		}
	}
	
	//QnA �Խñ� ���ȭ�� ǥ�� �޼ҵ�
	@RequestMapping(value="/qna_write_form", method=RequestMethod.GET)
	public String qnaWriteView(HttpSession session) {
		// ���ǿ� ����� �α��� ������ �о��
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		if (loginUser == null) {
			return "member/login"; //�α����� �ȵǾ����� ���
		} else {
			return "qna/qnaWrite"; //Qna �Խñ� ���ȭ�� ǥ��
		}
	}
	
	/* QnA �Խñ� ��� */
	@RequestMapping(value="/qna_write", method=RequestMethod.POST)
	public String qnaWriteAction(QnaVO vo, HttpSession session) {
		// ���ǿ� ����� �α��� ������ �о��
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		if (loginUser == null) {
			return "member/login"; //�α����� �ȵǾ����� ���
		} else {
			//ȭ�鿡�� �о�� ����� ���̵� ������ QnaVO��ü�� ���� (�ڵ������� subject, content ���)
			vo.setId(loginUser.getId());
			
			//qnaService��ü���� insertQna(qnaVO,id)�� ȣ���Ͽ� �Խñ� ����
			qnaService.insertQna(vo);
			
			//����� �����ϸ� �Խñ� ���ȭ������ �̵� - ȭ�鸸 ����ϸ�ȵǰ� ȭ���� ä���� ��������ϱ⶧���� redirect
			return "redirect:qna_list";
		}
	}
	
	/* Qna �Խñ� �󼼺��� */
	@RequestMapping(value="/qna_view", method=RequestMethod.GET)
	public String qnaView(QnaVO vo, HttpSession session, Model model) {
		// ���ǿ� ����� �α��� ������ �о��
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser");
		
		if (loginUser == null) {
			return "member/login"; //�α����� �ȵǾ����� ���
		} else {
			//qnaService��ü���� getQna()�� ȣ���� ��ȸ����� qnaVOŰ�� �𵨰�ü�� ����
			// ���� �Խñ� ����
			QnaVO qna = qnaService.getQna(vo.getQseq());
			
			model.addAttribute("qnaVO", qna); // ȭ�鿡�� qnaVO�� �޾ƿԱ⶧����
			
			return "qna/qnaView";
		}
	}

	
}
