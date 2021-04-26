package kosta.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kosta.dto.Goods;
import kosta.service.GoodsService;
import kosta.service.GoodsServiceImpl;

public class GoodsController implements Controller {
	GoodsService goodsService = new GoodsServiceImpl();
	
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		return null;
	}
	
	/**
	 * 전체검색
	 * */
	public ModelAndView selectAll(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<Goods> list = goodsService.selectAll();
		request.setAttribute("list", list);   //  ( 프론트에서 쓸변수, 백에서 프론트로 넘길 때의 값 ) 연결해준다는 의미로.  프론트에서 쓸 수 있다고 값을 넣어줬다. !set(값을 넣어주는 거 ), get (값을 가지고 오기) 
		ModelAndView mv = new ModelAndView();
		mv.setViewName("category.jsp");
		
		return mv;

	} 
	/**
	 * 등록하기
	 * */
	/*public ModelAndView insert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String saveDir = request.getServletContext().getRealPath("/save");
		String encoding = "UTF-8";
		int maxSize = 1024*1024*100; //100MB
		
		MultipartRequest m = new MultipartRequest(request, saveDir, maxSize, encoding, new DefaultFileRenamePolicy());
		
		String gdCode = m.getParameter("gd_code");
		String gdName = m.getParameter("gd_name");
		String price = m.getParameter("price");
		String brand = m.getParameter("brand");
		String gdLike = m.getParameter("gd_like");
		String stCode = m.getParameter("st_code");
		String gdContent = m.getParameter("gd_content");
		String img = m.getParameter("img");
		
		Goods goods = new Goods(gdCode, gdName, Integer.parseInt(price), brand, 
				Integer.parseInt(gdLike), stCode, gdContent, img);
		
		goodsService.insert(goods);
		
		return new ModelAndView();//처리방식
	}
	}*/
	
	/**
	 * 수정하기
	 * */
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String gdCode = request.getParameter("gdCode");
		String gdName = request.getParameter("gdNmae");
		String price = request.getParameter("price");
		String brand = request.getParameter("brand");
		String gdLike = request.getParameter("gdLike");
		String stCode = request.getParameter("stCode");
		String gdContent = request.getParameter("gdContent");
		String img = request.getParameter("img");
		
		Goods goods = new Goods(gdCode, gdName, Integer.parseInt(price), brand, Integer.parseInt(gdLike), stCode, gdContent, img);
		goodsService.update(goods);
		
		goods = goodsService.selectByGdName(gdCode);
		request.setAttribute("goods", goods);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("??");
		
		return mv;
	}
	
	/**
	 * 삭제하기
	 * */
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String gdCode = request.getParameter("gdCode");
		
		goodsService.delete(gdCode);
		
		ModelAndView mv = new ModelAndView("??", true);
		
		return mv;
	}
	
	
	
	/**
	 * 상품명 검색
	 * */

	public ModelAndView selectByGdName(HttpServletRequest request, HttpServletResponse response)  throws Exception{
		String goodsName = request.getParameter("goodsName");
		
	   Goods goods  = goodsService.selectByGdName(goodsName);
		
	   request.setAttribute("goods", goods);
	   ModelAndView mv = new ModelAndView();
	   mv.setViewName("category.jsp");
	   return mv;
	}
	
	
	/**
	 * 브랜드 검색 
	 * */
	public ModelAndView selectByBrand(HttpServletRequest request, HttpServletResponse response)  throws Exception{
		String brand= request.getParameter("brand");
		
	   Goods goods  = goodsService.selectByBrand(brand);
		
	   request.setAttribute("goods", goods);
	   ModelAndView mv = new ModelAndView();
	   mv.setViewName("category.jsp");
	   return mv;
	}
	
	/**
	 *가격대 검색 
	 * */ 
  public  ModelAndView selectByPrice(HttpServletRequest request, HttpServletResponse response) throws Exception{
       int  price = Integer.parseInt(request.getParameter("price"));
        
        Goods goods = goodsService.selectByPrice(price);
        
      request.setAttribute("price", price);
      ModelAndView mv = new ModelAndView();
      mv.setViewName("category.jsp");
      return mv; 
        
 }
      
	 /**
	  * 좋아요 기능
	  *  - 메인페이지, 상세페이지에서 좋아요 버튼 누르면 회원에 연결. 
	  * */
      
      public  ModelAndView updateLikes(HttpServletRequest request, HttpServletResponse response) throws Exception{
         String gdCode= request.getParameter("gdCode");
         
    	  ModelAndView mv = new ModelAndView("index.jsp", true);
  		
  		return mv;
 }
      /**
       * 상세페이지 이동
       * */
      public  ModelAndView gdDetail(HttpServletRequest request, HttpServletResponse response) throws Exception{
 	     String gdCode = request.getParameter("gdCode");  //얻어온 거 gdCode 그릇에 담기 ! 
 	     Goods goods = goodsService.gdDetail(gdCode);      //인수로 넣어줘서 서비스의 디테일호출하기 
 	     request.setAttribute("goods", goods);   
 	     ModelAndView mv = new ModelAndView();
 	     mv.setViewName("single-product.jsp");  
       return mv; 
       }

}
