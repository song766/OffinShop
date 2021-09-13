package com.green.view.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.Attributes;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.green.biz.comment.CommentService;
import com.green.biz.dto.MemberVO;
import com.green.biz.dto.ProductCommentVO;
import com.green.biz.utils.Criteria;
import com.green.biz.utils.PageMaker;

@RequestMapping("/comments/")
@RestController // �����͸� �����ϴ� ��Ʈ�ѷ�
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	/*���� ����Ʈ*/
	@GetMapping(value="/list")
	public Map<String, Object> commentList(
			@RequestParam(value="pseq") int pseq) {
		Map<String, Object> commentInfo = new HashMap<>();
		
		List<ProductCommentVO> commentList = commentService.getCommentList(pseq);
		//List<ProductCommentVO> commentList = commentService.getCommentListWithPaging(criteria, pseq);
		
		for(ProductCommentVO cVo:commentList) {
			System.out.println(cVo);
		}

		int totalComment = commentList.size();
		
		commentInfo.put("total", totalComment);
		commentInfo.put("commentList", commentList);
		
		return commentInfo;
	}
	
	/*������*/
	@PostMapping(value="/save")
	public String saveComment(
			ProductCommentVO commentVO, HttpSession session) {   // �����͸� �������� ProductCommentVO�ʿ�
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser"); // ����ڷα��� üũ
		
		if(loginUser == null) {
			return "not_logedin"; // �α��� �ȵ�������� not_logedin�� ȣ��
		} else {
			commentVO.setWriter(loginUser.getId()); //�ۼ��ڴ� ȭ�鿡�� �Էµ��� �����Ƿ� �α����������� ����
						
			if(commentService.saveComment(commentVO) == 1) {
				return "success";
			} else {
				return "fail";
			}
		}
		
	}
	
}
