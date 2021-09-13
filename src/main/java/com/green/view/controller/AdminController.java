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
	
	// Admin main화면 연결
	@RequestMapping(value="/admin_login_form")
	public String adminLoginView() {
		return "admin/main";
	}
	
	// Admin 로그인
	@RequestMapping(value="/admin_login")
	public String adminLogin(@RequestParam(value="workerId") String workerId,  //입력받은 값 사용하기 위해 화면에있는 id로 작성
							@RequestParam(value="workerPwd") String workerPwd,
							Model model) {
		
		int result = adminService.workerCheck(workerId, workerPwd);
		
		if(result == 1) { // 정상 로그인 -> 제품목록화면으로 이동
			WorkerVO adminUser = adminService.getEmployee(workerId);
			
			model.addAttribute("adminUser", adminUser);
			
			return "redirect:admin_product_list";
		} else { // 비정상로그인일경우 -> 알럿
			if(result == 0) {
				model.addAttribute("message", "비밀번호를 확인하세요");
			} else {
				model.addAttribute("message", "아이디를 확인하세요");
			}
			
			return "admin/main";
		}
	}
	
	/*
	 * 로그아웃
	 */	
	@RequestMapping(value="admin_logout")
	public String adminLogout(SessionStatus status) {
		status.setComplete(); // 세션해지
		return "admin/main";
	}
	
	/*
	 * admin 상품리스트(product_list) 화면
	 */
	@RequestMapping(value="admin_product_list")
	public String adminProductList(
			@RequestParam(value="key", defaultValue="") String key,
			Criteria criteria, HttpSession session, Model model) {
		WorkerVO adminUser = (WorkerVO)session.getAttribute("adminUser"); // "adminUser"로 되어있는 session조회
		
		if(adminUser == null) {
			return "admin/main";
		} else {
			//List<ProductVO> prodList = productService.listProduct(key);
			List<ProductVO> prodList = productService.getListWithPaging(criteria, key);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(criteria); // 현재 페이지와 페이지당 항목 수 설정
			
			// 전체 게시글의 수 조회
			int totalCount = productService.countProductList(key);
			pageMaker.setTotalCount(totalCount);
			System.out.println("페이징 정보=" + pageMaker);
			
			model.addAttribute("productListSize", prodList.size());
			model.addAttribute("productList", prodList);
			model.addAttribute("pageMaker", pageMaker);
			
			return "admin/product/productList";
		}
	}
	
	/*
	 * 상품 등록 페이지 표시 
	 */
	@RequestMapping(value="admin_product_write_form")
	public String adminProductWriteView(Model model) {

		// kindList[] 배열로 저장
		String kindList[] = {"OUTER", "TOP", "PANTS", "SKIRT" , "DRESS"};
		
		model.addAttribute("kindList", kindList);
		
		return "admin/product/productWrite";
	}
	
	/*
	 * 상품 등록 처리 
	 */
	@RequestMapping(value="admin_product_write")
	public String adminProductWrite(
			@RequestParam(value="product_image") MultipartFile uploadFile, // @RequestParam (해당-product_image), 타입, 변수
			ProductVO vo, HttpSession session) { // 데이터를 받으려면 ProductVO필요
		
		// WorkerVO 타입의 adminUser에 세션에있는 adminUser데이터 저장
		WorkerVO adminUser = (WorkerVO)session.getAttribute("adminUser");
		
		if(adminUser == null) {
			return "admin/main"; //로그인 아닐경우 main
		} else {
			String fileName = ""; // fileName을 빈문자로 초기화 시킨 후
			
			if(!uploadFile.isEmpty()) { // 화면에서 product_image필드에 이미지가 입력된 경우
				fileName = uploadFile.getOriginalFilename();
				vo.setImage(fileName); // VO객체에 이미지 파일명 저장
				
				// 이미지 파일을 업로드 하기 위해서 이미지 저장 실제 경로를 구한다.
				String image_path = session.getServletContext().getRealPath("WEB-INF/resources/product_images/");
				System.out.println("이동 이미지 경로:" + image_path);
				
				try {
				File file = new File(image_path+fileName);
					uploadFile.transferTo(file); // 상품 이미지 저장 경로로 이동시키게 됨
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				} 
			}
			
			System.out.println("등록제품==" + vo);
			
			productService.insertProduct(vo);
			
			return "redirect:admin_product_list";
		}
		
	}
	
	/*
	 * 상품 클릭 시 상품 상세 이동
	 */
	@RequestMapping(value="admin_product_detail")
	public String adminProductDetail(ProductVO vo, Model model) {
		String[] kindList = {"", "OUTER", "TOP", "PANTS", "SKIRT", "DRESS"};
		
		ProductVO product = productService.getProduct(vo);
		
		model.addAttribute("productVO", product); // 화면에 전달할 상품상세정보
		
		int index = Integer.parseInt(product.getKind()); // 상품 분류코드를 읽어온다.
		model.addAttribute("kind", kindList[index]);
		
		return "admin/product/productDetail";
	}
	
	/*
	 * 상품 수정 업데이트
	 */
	@RequestMapping(value="admin_product_update_form")
	public String adminProductUpdateView(ProductVO vo, Model model) {
		String[] kindList = {"OUTER", "TOP", "PANTS", "SKIRT", "DRESS"};
		
		ProductVO product = productService.getProduct(vo);
		
		model.addAttribute("productVO", product); // 화면에 전달할 상품상세정보
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
         
         if (!uploadFile.isEmpty()) {   // 화면에서 product_image 필드에 이미지가 입력된 경우
            fileName = uploadFile.getOriginalFilename();
            vo.setImage(fileName); // VO 객체에 이미지파일명 저장
            
            // 이미지 파일을 업로드 하기 위해 이미지 저장 실제 경로를 구한다.
            String image_path 
            = session.getServletContext().getRealPath("WEB-INF/resources/product_images/");
            System.out.println("이동 이미지 경로: " + image_path);
            
            try {
               File file = new File(image_path+fileName);
               uploadFile.transferTo(file); // 상품이미지 저장 경로로 이동시킴
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
         
         
         System.out.println("상품정보="+vo);
         productService.updateProduct(vo);
         
         return "redirect:admin_product_detail?pseq="+vo.getPseq();
      }
   }
	   
   /*
    *  전체 주문내역 조회
    */
   @RequestMapping(value="admin_order_list")
   public String adminOrderList(
		   @RequestParam(value="key", defaultValue="") String key,
		   Criteria criteria, HttpSession session, Model model) {
	   WorkerVO adminUser = (WorkerVO)session.getAttribute("adminUser"); // "adminUser"로 되어잇는 session조회
	   
	   if(adminUser == null) {
			return "admin/main";
		} else {
			//List<OrderVO> orderList = orderService.listOrder(key);
			List<OrderVO> orderList = orderService.getorderListWithpaging(criteria, key);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(criteria); // 현재 페이지와 페이지당 항목 수 설정
			
			// 전체 게시글 수 조회
			int totalCount = orderService.countOrderList(key);
			pageMaker.setTotalCount(totalCount);
			System.out.println("페이징 정보=" + pageMaker);
			
			model.addAttribute("orderListSize", orderList.size());
			model.addAttribute("orderList", orderList);
			model.addAttribute("pageMaker", pageMaker);
			   
			return "admin/order/orderList";
		}
   }
		   
   /*
    * 주문 완료 처리(입금 확인) 
    */
   @RequestMapping(value="admin_order_save")
   public String adminOrderSave(@RequestParam(value="result") int[] odseq) {
	   
	   for(int i=0; i<odseq.length; i++) {
		   orderService.updateOrderResult(odseq[i]);
	   }
	   
	   return "redirect:admin_order_list";
   }
	   
	/*
	 * 회원목록 조회처리
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
    * Q&A 목록 출력
    */
   @RequestMapping(value="admin_qna_list")
   public String adminQnaList(Model model) {
	   List<QnaVO> qnaList = qnaService.listAllQna();
	   
	   model.addAttribute("qnaList", qnaList);
	   return "admin/qna/qnaList";
   }
   
   /*
    * 클릭한 QnA정보를 조회하여 출력하는 부분 
    */
   @RequestMapping(value="admin_qna_detail")
   public String adminQnaDetail(QnaVO vo, Model model) {
	   QnaVO qna = qnaService.getQna(vo.getQseq());
	   
	   model.addAttribute("qnaVO", qna);
	   
	   return "admin/qna/qnaDetail";
   }
   
   /*
    *  QnA 관리자 답변처리
    */
   @RequestMapping(value="admin_qna_repsave")
   public String adminQnaRepSave(QnaVO vo) {
	   qnaService.updateQna(vo);
	   return "redirect:admin_qna_list";
   }
	   
}
