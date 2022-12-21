package com.sh.interceptor;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.sh.dao.UserDao;
import com.sh.dto.UserDTO;

@Component("beforeActionInterceptor")
public class BeforeActionInterceptor implements HandlerInterceptor {

	@Autowired
	private UserDao userDao;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		// System.out.println("principal : " + principal);

		if (principal.toString() != "anonymousUser") {
			UserDetails userDetails = (UserDetails) principal;

			ArrayList<UserDTO> user = userDao.findByUserID(userDetails.getUsername().toString());

			for (GrantedAuthority user_auth : userDetails.getAuthorities()) {
				if (user_auth.toString().equals("ADMIN")) {
					request.setAttribute("admin", true);
				}
			}

			request.setAttribute("isLogined", true);
			request.setAttribute("username", user.get(0).getNAME());
			request.setAttribute("agency", user.get(0).getAGENCY());

		} else {
			request.setAttribute("isLogined", false);
			request.setAttribute("admin", false);
		}
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

}
