package com.sh.config;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.GenericFilterBean;

public class CustomFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		// Enumeration<String> headerNames = httpRequest.getHeaderNames();
		Enumeration eHeader = httpRequest.getHeaderNames();

		String value = "";
		if (eHeader != null) {
			while (eHeader.hasMoreElements()) {
				String request_Name = (String) eHeader.nextElement();
				String request_value = httpRequest.getHeader(request_Name);

				System.out.println(request_Name + " : " + request_value);

				if (request_Name.equals("keys")) {
					value = request_value;
					break;
				}

			}
		}

		chain.doFilter(request, response);
	}
}