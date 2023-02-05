package com.sh.ctrl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sh.common.Maps;
import com.sh.dto.CustomUserDetails;
import com.sh.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/api/userLogin", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> userLogin(@RequestParam Map<String, Object> param) {

		System.out.println("[ userLogin ] : " + param);

		Map<String, Object> result = userService.userLogin(param);

		return result;
	}

	@GetMapping("/api/bookmark/{hpid}")
	public Map<String, Object> bookmark(@PathVariable String hpid, @AuthenticationPrincipal CustomUserDetails authUser) {
		
		if (authUser == null) {
			return Maps.json("F-1", "로그인 후 이용해주세요.");
		}
		
		Map<String, Object> result = userService.bookmark(hpid, authUser.getUserCode());

		return result;
	}

}
