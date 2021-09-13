package com.green.biz.qna;

import java.util.List;
import com.green.biz.dto.QnaVO;

public interface QnaService {

	// ��ü qna ��� ��ȸ
	List<QnaVO> listQna(String id);

	// �Ϸù�ȣ �� �Խñ� �� �� ��ȸ
	QnaVO getQna(int qseq);

	// �Խñ� insert����
	void insertQna(QnaVO vo);
	
	// �Խ��� ��ü ��ȸ
	List<QnaVO> listAllQna();
	
	// �Խ��� �亯 ó��
	public void updateQna(QnaVO vo);

}
