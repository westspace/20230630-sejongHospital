package com.sh.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HospitalDao {

	int findByHpid(String hpid);

	void saveHospital(Map<String, Object> param);

	List<Map<String, Object>> hospitalList();

	void removeHospital(String hpid);

	List<Map<String, Object>> showHospitalByAreaList(String area);

	void hospitalAdminMemo(Map<String, Object> param);
}
