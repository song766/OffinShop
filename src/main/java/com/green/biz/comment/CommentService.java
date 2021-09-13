package com.green.biz.comment;

import java.util.List;

import com.green.biz.dto.ProductCommentVO;
import com.green.biz.utils.Criteria;

public interface CommentService {

	// ��ǰ�� ��� ��ȸ
	List<ProductCommentVO> getCommentList(int pseq);

	// ��ǰ�� ����
	int saveComment(ProductCommentVO vo);

	// ��ǰ�� ���� ������Ʈ
	int updateComment(ProductCommentVO vo);

	// ��ǰ�� ����
	int deleteComment(int comment_seq);

	/* ��ǰ�� ���� ��ȸ
	int countCommentList(int pseq);
	*/

	/* ��ǰ�� ����¡
	List<ProductCommentVO> getCommentListWithPaging(Criteria criteria, int pseq);
	*/
}