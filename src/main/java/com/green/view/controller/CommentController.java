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
@RestController // 데이터를 리턴하는 컨트롤러
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	/*리뷰 리스트*/
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
	
	/*리뷰등록*/
	@PostMapping(value="/save")
	public String saveComment(
			ProductCommentVO commentVO, HttpSession session) {   // 데이터를 받으려면 ProductCommentVO필요
		MemberVO loginUser = (MemberVO)session.getAttribute("loginUser"); // 사용자로그인 체크
		
		if(loginUser == null) {
			return "not_logedin"; // 로그인 안되있을경우 not_logedin을 호출
		} else {
			commentVO.setWriter(loginUser.getId()); //작성자는 화면에서 입력되지 않으므로 로그인정보에서 추출
						
			if(commentService.saveComment(commentVO) == 1) {
				return "success";
			} else {
				return "fail";
			}
		}
		
	}
	
}
