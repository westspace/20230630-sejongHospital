package com.sh.ctrl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sh.common.Maps;
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

	
}
