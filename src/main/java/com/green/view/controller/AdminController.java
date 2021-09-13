package com.green.view.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import com.green.biz.admin.AdminService;
import com.green.biz.dto.MemberVO;
import com.green.biz.dto.OrderVO;
import com.green.biz.dto.ProductVO;
import com.green.biz.dto.QnaVO;
import com.green.biz.dto.WorkerVO;
import com.green.biz.member.MemberService;
import com.green.biz.order.CartService;
import com.green.biz.order.OrderService;
import com.green.biz.product.ProductService;
import com.green.biz.qna.QnaService;
import com.green.biz.utils.Criteria;
import com.green.biz.utils.PageMaker;

@SessionAttributes("adminUser")
@Controller
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private QnaService qnaService;	
	
	// Admin mainȭ�� ����
	@RequestMapping(value="/admin_login_form")
	public String adminLoginView() {
		return "admin/main";
	}
	
	// Admin �α���
	@RequestMapping(value="/admin_login")
	public String adminLogin(@RequestParam(value="workerId") String workerId,  //�Է¹��� �� ����ϱ� ���� ȭ�鿡�ִ� id�� �ۼ�
							@RequestParam(value="workerPwd") String workerPwd,
							Model model) {
		
		int result = adminService.workerCheck(workerId, workerPwd);
		
		if(result == 1) { // ���� �α��� -> ��ǰ���ȭ������ �̵�
			WorkerVO adminUser = adminService.getEmployee(workerId);
			
			model.addAttribute("adminUser", adminUser);
			
			return "redirect:admin_product_list";
		} else { // ������α����ϰ�� -> �˷�
			if(result == 0) {
				model.addAttribute("message", "��й�ȣ�� Ȯ���ϼ���");
			} else {
				model.addAttribute("message", "���̵� Ȯ���ϼ���");
			}
			
			return "admin/main";
		}
	}
	
	/*
	 * �α׾ƿ�
	 */	
	@RequestMapping(value="admin_logout")
	public String adminLogout(SessionStatus status) {
		status.setComplete(); // ��������
		return "admin/main";
	}
	
	/*
	 * admin ��ǰ����Ʈ(product_list) ȭ��
	 */
	@RequestMapping(value="admin_product_list")
	public String adminProductList(
			@RequestParam(value="key", defaultValue="") String key,
			Criteria criteria, HttpSession session, Model model) {
		WorkerVO adminUser = (WorkerVO)session.getAttribute("adminUser"); // "adminUser"�� �Ǿ��ִ� session��ȸ
		
		if(adminUser == null) {
			return "admin/main";
		} else {
			//List<ProductVO> prodList = productService.listProduct(key);
			List<ProductVO> prodList = productService.getListWithPaging(criteria, key);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(criteria); // ���� �������� �������� �׸� �� ����
			
			// ��ü �Խñ��� �� ��ȸ
			int totalCount = productService.countProductList(key);
			pageMaker.setTotalCount(totalCount);
			System.out.println("����¡ ����=" + pageMaker);
			
			model.addAttribute("productListSize", prodList.size());
			model.addAttribute("productList", prodList);
			model.addAttribute("pageMaker", pageMaker);
			
			return "admin/product/productList";
		}
	}
	
	/*
	 * ��ǰ ��� ������ ǥ�� 
	 */
	@RequestMapping(value="admin_product_write_form")
	public String adminProductWriteView(Model model) {

		// kindList[] �迭�� ����
		String kindList[] = {"OUTER", "TOP", "PANTS", "SKIRT" , "DRESS"};
		
		model.addAttribute("kindList", kindList);
		
		return "admin/product/productWrite";
	}
	
	/*
	 * ��ǰ ��� ó�� 
	 */
	@RequestMapping(value="admin_product_write")
	public String adminProductWrite(
			@RequestParam(value="product_image") MultipartFile uploadFile, // @RequestParam (�ش�-product_image), Ÿ��, ����
			ProductVO vo, HttpSession session) { // �����͸� �������� ProductVO�ʿ�
		
		// WorkerVO Ÿ���� adminUser�� ���ǿ��ִ� adminUser������ ����
		WorkerVO adminUser = (WorkerVO)session.getAttribute("adminUser");
		
		if(adminUser == null) {
			return "admin/main"; //�α��� �ƴҰ�� main
		} else {
			String fileName = ""; // fileName�� ���ڷ� �ʱ�ȭ ��Ų ��
			
			if(!uploadFile.isEmpty()) { // ȭ�鿡�� product_image�ʵ忡 �̹����� �Էµ� ���
				fileName = uploadFile.getOriginalFilename();
				vo.setImage(fileName); // VO��ü�� �̹��� ���ϸ� ����
				
				// �̹��� ������ ���ε� �ϱ� ���ؼ� �̹��� ���� ���� ��θ� ���Ѵ�.
				String image_path = session.getServletContext().getRealPath("WEB-INF/resources/product_images/");
				System.out.println("�̵� �̹��� ���:" + image_path);
				
				try {
				File file = new File(image_path+fileName);
					uploadFile.transferTo(file); // ��ǰ �̹��� ���� ��η� �̵���Ű�� ��
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				} 
			}
			
			System.out.println("�����ǰ==" + vo);
			
			productService.insertProduct(vo);
			
			return "redirect:admin_product_list";
		}
		
	}
	
	/*
	 * ��ǰ Ŭ�� �� ��ǰ �� �̵�
	 */
	@RequestMapping(value="admin_product_detail")
	public String adminProductDetail(ProductVO vo, Model model) {
		String[] kindList = {"", "OUTER", "TOP", "PANTS", "SKIRT", "DRESS"};
		
		ProductVO product = productService.getProduct(vo);
		
		model.addAttribute("productVO", product); // ȭ�鿡 ������ ��ǰ������
		
		int index = Integer.parseInt(product.getKind()); // ��ǰ �з��ڵ带 �о�´�.
		model.addAttribute("kind", kindList[index]);
		
		return "admin/product/productDetail";
	}
	
	/*
	 * ��ǰ ���� ������Ʈ
	 */
	@RequestMapping(value="admin_product_update_form")
	public String adminProductUpdateView(ProductVO vo, Model model) {
		String[] kindList = {"OUTER", "TOP", "PANTS", "SKIRT", "DRESS"};
		
		ProductVO product = productService.getProduct(vo);
		
		model.addAttribute("productVO", product); // ȭ�鿡 ������ ��ǰ������
		model.addAttribute("kindList", kindList);
		
		return "admin/product/productUpdate";
	}
	
   @RequestMapping(value="admin_product_update")
   public String adminProductUpdate(@RequestParam(value="product_image") MultipartFile uploadFile,
                     ProductVO vo, HttpSession session) {
      WorkerVO adminUser = (WorkerVO) session.getAttribute("adminUser");

      if (adminUser == null) {
         return "admin/main";
      } else {
         
         String fileName = "";
         
         if (!uploadFile.isEmpty()) {   // ȭ�鿡�� product_image �ʵ忡 �̹����� �Էµ� ���
            fileName = uploadFile.getOriginalFilename();
            vo.setImage(fileName); // VO ��ü�� �̹������ϸ� ����
            
            // �̹��� ������ ���ε� �ϱ� ���� �̹��� ���� ���� ��θ� ���Ѵ�.
            String image_path 
            = session.getServletContext().getRealPath("WEB-INF/resources/product_images/");
            System.out.println("�̵� �̹��� ���: " + image_path);
            
            try {
               File file = new File(image_path+fileName);
               uploadFile.transferTo(file); // ��ǰ�̹��� ���� ��η� �̵���Ŵ
            } catch (IllegalStateException | IOException e) {
               e.printStackTrace();
            }   
         }
         
         if (vo.getUseyn() == null) {
            vo.setUseyn("n");
         }
         if (vo.getBestyn() == null) {
            vo.setBestyn("n");
         }
         
         
         System.out.println("��ǰ����="+vo);
         productService.updateProduct(vo);
         
         return "redirect:admin_product_detail?pseq="+vo.getPseq();
      }
   }
	   
   /*
    *  ��ü �ֹ����� ��ȸ
    */
   @RequestMapping(value="admin_order_list")
   public String adminOrderList(
		   @RequestParam(value="key", defaultValue="") String key,
		   Criteria criteria, HttpSession session, Model model) {
	   WorkerVO adminUser = (WorkerVO)session.getAttribute("adminUser"); // "adminUser"�� �Ǿ��մ� session��ȸ
	   
	   if(adminUser == null) {
			return "admin/main";
		} else {
			//List<OrderVO> orderList = orderService.listOrder(key);
			List<OrderVO> orderList = orderService.getorderListWithpaging(criteria, key);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(criteria); // ���� �������� �������� �׸� �� ����
			
			// ��ü �Խñ� �� ��ȸ
			int totalCount = orderService.countOrderList(key);
			pageMaker.setTotalCount(totalCount);
			System.out.println("����¡ ����=" + pageMaker);
			
			model.addAttribute("orderListSize", orderList.size());
			model.addAttribute("orderList", orderList);
			model.addAttribute("pageMaker", pageMaker);
			   
			return "admin/order/orderList";
		}
   }
		   
   /*
    * �ֹ� �Ϸ� ó��(�Ա� Ȯ��) 
    */
   @RequestMapping(value="admin_order_save")
   public String adminOrderSave(@RequestParam(value="result") int[] odseq) {
	   
	   for(int i=0; i<odseq.length; i++) {
		   orderService.updateOrderResult(odseq[i]);
	   }
	   
	   return "redirect:admin_order_list";
   }
	   
	/*
	 * ȸ����� ��ȸó��
	 */
   @RequestMapping(value="admin_member_list")
   public String adminMemberList(
		   @RequestParam(value="key", defaultValue="") String name,
		   Model model) {
	   List<MemberVO> listMember = memberService.listMember(name);
	   model.addAttribute("memberList", listMember);
	   
	   return "admin/member/memberList";
   }
	   
   /*
    * Q&A ��� ���
    */
   @RequestMapping(value="admin_qna_list")
   public String adminQnaList(Model model) {
	   List<QnaVO> qnaList = qnaService.listAllQna();
	   
	   model.addAttribute("qnaList", qnaList);
	   return "admin/qna/qnaList";
   }
   
   /*
    * Ŭ���� QnA������ ��ȸ�Ͽ� ����ϴ� �κ� 
    */
   @RequestMapping(value="admin_qna_detail")
   public String adminQnaDetail(QnaVO vo, Model model) {
	   QnaVO qna = qnaService.getQna(vo.getQseq());
	   
	   model.addAttribute("qnaVO", qna);
	   
	   return "admin/qna/qnaDetail";
   }
   
   /*
    *  QnA ������ �亯ó��
    */
   @RequestMapping(value="admin_qna_repsave")
   public String adminQnaRepSave(QnaVO vo) {
	   qnaService.updateQna(vo);
	   return "redirect:admin_qna_list";
   }
	   
}
