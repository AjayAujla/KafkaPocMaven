package com.ajayaujlawork.kafkapocmaven;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class MicrosoftEmulatorInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final Object o) throws Exception {
		log.info("AJAY preHandle: " + httpServletRequest.getRequestURI());
		return true;
	}

	@Override
	public void postHandle(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final Object o, final ModelAndView modelAndView) throws Exception {
		log.info("AJAY postHandle: " + httpServletRequest.getRequestURI());
	}

	@Override
	public void afterCompletion(final HttpServletRequest httpServletRequest, final HttpServletResponse httpServletResponse, final Object o, final Exception e) throws Exception {
		log.info("AJAY afterCompletion: " + httpServletRequest.getRequestURI());
	}
}
