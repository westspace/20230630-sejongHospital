package com.sh.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sh.dto.UserDTO;

@Mapper
public interface UserDao {
	
	Map<String, Object> userChk(Map<String, Object> param);

	List<Map<String, Object>> getUserList();

	int findById(String string);

	void userJoin(Map<String, Object> param);

	void removeUser(@Param("USER_CODE") String userCode);

	int findByUserCode(@Param("USER_CODE") String string);

	Map<String, Object> findByUser(Map<String, Object> param);

	void userAccountStop(Map<String, Object> param);

	void userAccountStart(Map<String, Object> param);

	public ArrayList<UserDTO> findByUserID(String username);
}
