package com.sh.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sh.common.Maps;
import com.sh.dao.UserDao;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Map<String, Object> userLogin(Map<String, Object> param) {

		Map<String, Object> member = userDao.userChk(param);

		System.out.println("member : " + member);
		if (member == null) {
			return Maps.json("F-1", "등록되지 않은 유저");
		}

		return Maps.json("S-1", "ok", member);
	}

	@Override
	public Map<String, Object> getUserList() {

		List<Map<String, Object>> list = userDao.getUserList();

		return Maps.json("S-1", "ok", list);
	}

	@Override
	public Map<String, Object> userJoin(Map<String, Object> param) {

		if (	   param.get("USER_ID").toString().trim().equals("") 
				|| param.get("USER_PW").toString().trim().equals("")
				|| param.get("NAME").toString().trim().equals("")
				|| param.get("DEPARTMENT").toString().trim().equals("")
				|| param.get("AGENCY").toString().trim().equals("")
				|| param.get("ROLE").toString().trim().equals("")
		) {
			return Maps.json("F-1", "회원정보를 입력해 주세요.");
		}

		int count = userDao.findById(param.get("USER_ID").toString());

		if (count > 0)
			return Maps.json("F-2", "이미 사용중인 아이디 입니다.");

		String encodedPassword = passwordEncoder.encode(param.get("USER_PW").toString());

		param.put("USER_PW", encodedPassword);

		userDao.userJoin(param);

		return Maps.json("S-1", "회원이 추가 되었습니다.");
	}

	@Override
	public Map<String, Object> removeUser(String userCode) {

		userDao.removeUser(userCode);

		return Maps.json("S-1", userCode + "번 회원이 삭제 되었습니다.");
	}

	@Override
	public Map<String, Object> updateUser(Map<String, Object> param) {
		// TODO Auto-generated method stub

		Map<String, Object> user = userDao.findByUser(param);

		if (user.get("USER_TYPE").toString().equals("1")) {
			System.out.println("0으로 변경");

			userDao.userAccountStop(param);

			return Maps.json("S-1", "해당 계정은 정지 되었습니다.");
		} else {
			System.out.println("1으로 변경");

			userDao.userAccountStart(param);

			return Maps.json("S-1", "해당 계정으로 로그인 할 수 있어요~");
		}
	}
}
