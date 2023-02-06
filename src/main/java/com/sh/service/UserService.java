package com.sh.service;

import java.util.List;
import java.util.Map;

public interface UserService {

	Map<String, Object> userJoin(Map<String, Object> param);

	Map<String, Object> userLogin(Map<String, Object> param);

	Map<String, Object> getUserList();

	Map<String, Object> removeUser(String userCode);

	Map<String, Object> updateUser(Map<String, Object> param);

	Map<String, Object> bookmark(String hpid, String userCode);

	boolean findByBookMark(String object);

	List<Map<String, Object>> getUserBookMarkData();

}
