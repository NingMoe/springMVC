package com.lph.app.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/product")
public class ProductController {

	private static final String PRODUCT_VIEW = "product";

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(HttpServletRequest request) {
		System.out.println("Scheme:" + request.getScheme());
		System.out.println("ServerName:" + request.getServerName());
		System.out.println("ServerPort:" + request.getServerPort());
		System.out.println("ServletPath:" + request.getServletPath());
		System.out.println("ServletContext:" + request.getServletContext());
		System.out.println("ContextPath:" + request.getContextPath());
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
		request.getSession().setAttribute("basePath", basePath);
		return PRODUCT_VIEW;
	}

	@RequestMapping(value = "/indexData", method = RequestMethod.POST)
	public ResponseEntity<List<String>> indexData(HttpServletRequest request) {
		List<String> images=new ArrayList<String>(50);
		for(int i=0;i<50;i++){
			images.add("http://www.smartwasp.com/upfile/201704/2017040857099769.jpg");
		}
		return new ResponseEntity<List<String>>(images,HttpStatus.OK);
	}
}
