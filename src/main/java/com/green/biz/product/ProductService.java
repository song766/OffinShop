package com.green.biz.product;

import java.util.List;

import com.green.biz.dto.ProductVO;
import com.green.biz.utils.Criteria;

public interface ProductService {

	// �Ż�ǰ ���
	List<ProductVO> getNewProductList();
	
	List<ProductVO> getNewProductListAll();

	List<ProductVO> getBestProductList();
	
	List<ProductVO> getBestProductListAll();

	ProductVO getProduct(ProductVO vo);

	List<ProductVO> getProductListByKind(String kind);
	
	List<ProductVO> productListAll();
	
	List<ProductVO> productListAllSearch(String name);

	// ��ȸ�� �� ��ǰ ���� - ���� name(��ȸ�� return�� �ʿ�)
	int countProductList(String name);

	// ��ǰ ��� ��ȸ
	List<ProductVO> listProduct(String name);

	// ��ǰ �߰� - insert�� void�� 
	void insertProduct(ProductVO vo);

	// ��ǰ���� ���� - update
	void updateProduct(ProductVO vo);
	
	// ��ǰ���� ���� - delete
	public void deleteProduct(int pseq);
	
	//����¡ó��
	public List<ProductVO> getListWithPaging(Criteria criteria, String key);

}