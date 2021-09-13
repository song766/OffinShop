package com.green.biz.comment;

import java.util.List;

import com.green.biz.dto.ProductCommentVO;
import com.green.biz.utils.Criteria;

public interface CommentService {

	// 상품평 목록 조회
	List<ProductCommentVO> getCommentList(int pseq);

	// 상품평 저장
	int saveComment(ProductCommentVO vo);

	// 상품평 수정 업데이트
	int updateComment(ProductCommentVO vo);

	// 상품평 삭제
	int deleteComment(int comment_seq);

	/* 상품평 갯수 조회
	int countCommentList(int pseq);
	*/

	/* 상품평 페이징
	List<ProductCommentVO> getCommentListWithPaging(Criteria criteria, int pseq);
	*/
}