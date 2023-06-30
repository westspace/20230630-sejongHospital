package com.sh.ctrl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sh.service.UserService;

@RestController
public class AdminUserController {

	@Autowired
	private UserService userService;

	/* 유저 리스트 */
	@RequestMapping(value = "/api/userList", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> getUserList() {

		System.out.println("[ userList ] ");

		Map<String, Object> result = userService.getUserList();

		return result;
	}

	/* 관리자가 유저 추가 */
	@RequestMapping(value = "/api/userJoin", method = { RequestMethod.POST })
	public Map<String, Object> regiUser(@RequestBody Map<String, Object> param) {

		System.out.println("[ regiUser ] : " + param);

		Map<String, Object> result = userService.userJoin(param);
		return result;
	}

	/* 관리자가 유저 업데이트 */
	@RequestMapping("/api/updateUser")
	public Map<String, Object> updateUser(@RequestParam Map<String, Object> param) {

		System.out.println("[ updateUser ] : " + param);

		Map<String, Object> result = userService.updateUser(param);

		return result;
	}

	/* 관리자가 유저 삭제 */
	@RequestMapping(value = "/api/removeUser", method = { RequestMethod.POST })
	public Map<String, Object> removeUser(@RequestParam String userCode) {

		System.out.println("[ removeUser ] : " + userCode);

		Map<String, Object> result = userService.removeUser(userCode);

		return result;
	}
}
