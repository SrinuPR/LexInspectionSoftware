package com.deloitte.inspection.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.deloitte.inspection.constant.AuthorizeConstants;


public class LoginFilter implements Filter{
	
	private static final Logger logger = LogManager.getLogger(LoginFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		logger.info("Creating session for the user");
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpSession session = httpServletRequest.getSession(false);
        String loginURI = httpServletRequest.getContextPath() + "/login";
        
        boolean loggedIn = session != null && session.getAttribute("user") != null;
        boolean loginRequest = AuthorizeConstants.noAuthUrls.contains(httpServletRequest.getRequestURI());
        
        if (loggedIn || loginRequest) {
            chain.doFilter(httpServletRequest, httpServletResponse);
        } else {
        	httpServletResponse.sendRedirect(loginURI);
        }
	}

	@Override
	public void destroy() {		
	}

}
