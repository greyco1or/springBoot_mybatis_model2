package ksmart.mybatis.interceptor;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoginInterceptor implements HandlerInterceptor{
	
	private static final Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

	
	/**
	 * 매개변수: HttpServletRequest, HttpServletResponse, Object handler
	 * HandlerMapping이 핸들러 객체를 결정한 후 HandlerAdapter가 핸들러를 호출하기 전에 호출되는 메소드
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// TODO Auto-generated method stub
		log.info("interceptor :: preHandle 실행");
		HttpSession session = request.getSession();
		String sessionId = (String) session.getAttribute("SID");
		
		int memberLevel = (int) Optional.ofNullable(session.getAttribute("SLEVEL")).orElse(0);
		
		String requestUri = request.getRequestURI();

		if(sessionId != null && memberLevel > 0) {
			
			//판매자일 경우
			if(memberLevel == 2) {
				if(requestUri.indexOf("/member/memberList") > -1
					||requestUri.indexOf("/member/modify") > -1
					||requestUri.indexOf("/member/remove") > -1) {
					
					response.sendRedirect("/");
					return false;
				}
			//구매자일 경우	
			}else if(memberLevel == 3) {
				if(requestUri.indexOf("/member/memberList") > -1
					||requestUri.indexOf("/member/modify") > -1
					||requestUri.indexOf("/member/remove") > -1
					||requestUri.indexOf("/goods/modify") > -1
					||requestUri.indexOf("/goods/remove") > -1) {
				
					response.sendRedirect("/");
					return false;
				}
			}
				
				
			return true;
		}
		
		response.sendRedirect("/member/login");
		
		return false;
	}
	
	/**
	 * 매개변수: HttpServletRequest, HttpServletResponse, Object handler, ModelAndView
	 * HandlerAdapter가 실제로 핸들러를 호출한 후  DispatcherServlet이 뷰를 랜더링하기 전에 호출되는 뷰
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
	
	/**
	 * 매개변수: HttpServletRequest, HttpServletResponse, Object handler
	 * DispatcherServlet이 뷰를 렌더링한 후에 호출되는 메소드
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}
}
