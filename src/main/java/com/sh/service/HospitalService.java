package com.sh.service;

import java.util.Map;

public interface HospitalService {

	Map<String, Object> saveHospital(Map<String, Object> param);

	Map<String, Object> hospitalList();

	Map<String, Object> removeHospital(String hpid);

	Map<String, Object> showHospitalByAreaList(String area);

	Map<String, Object> hospitalAdminMemo(Map<String, Object> param);

	Map<String, Object> findByHospital(String hName);

	Map<String, Object> hospitalMemo(Map<String, Object> param);

}
