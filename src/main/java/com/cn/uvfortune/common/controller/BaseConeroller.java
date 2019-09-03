package com.cn.uvfortune.common.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * 
 * @author aodun
 *
 */
public class BaseConeroller {
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response){
		this.request = request;
		this.response = response;
	}
	
	public String replay(String str){
		try {
			response.setContentType("text/html;charset=utf-8");
			response.setCharacterEncoding("utf-8");
			response.getWriter().print(str);
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String realPath(){
		return request.getSession().getServletContext().getRealPath("/");
	}
	
	
	
}
