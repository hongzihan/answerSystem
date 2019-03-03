package com.hzh.answer.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hzh.answer.domain.SysUser;

/**
 * 过滤非法访问，没有登录的人不能访问除登录页外的其他页面
 * 且所有页面都通过action的方式访问
 * @author ken
 *
 */
public class LoginFilter implements Filter {

	private HttpServletRequest request;
	private HttpServletResponse response;

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("jsp过滤器结束");
	}

	@Override
	public void doFilter(ServletRequest request1, ServletResponse response1, FilterChain chain)
			throws IOException, ServletException {
		this.request = (HttpServletRequest) request1;
		this.response = (HttpServletResponse) response1;
		String requestURI = request.getRequestURI();
		boolean isLoginJsp = request.getRequestURI().contains("/answerSystem/public/login.jsp");
		// 过滤掉所有不是login.jsp的jspURL
		if (!isLoginJsp) {
			System.out.println("是login.jsp");
			response.sendRedirect("/answerSystem/public/login.jsp");
			return;
		}
//		boolean isJsp = requestURI.contains("jsp");
//		if(isJsp) {
//			System.out.println("是jsp");
//			boolean isLoginJsp = request.getRequestURI().contains("/answerSystem/public/login.jsp");
//			// 过滤掉所有不是login.jsp的jspURL
//			if (!isLoginJsp) {
//				System.out.println("是login.jsp");
//				response.sendRedirect("/answerSystem/public/login.jsp");
//				return;
//			}
//		} else if(requestURI.contains(".css") || requestURI.contains(".js") || requestURI.contains(".png") || requestURI.contains(".jpg")) {
//			return;
//		} else if(!requestURI.contains(".action")) {
//			response.sendRedirect("/answerSystem/public/login.jsp");
//			return;
//		} 
		
		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("jsp过滤器开始");
	}

}
