package com.sh.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.common.Maps;
import com.sh.dao.HospitalDao;

@Service
public class HospitalServiceImpl implements HospitalService {

	@Autowired
	private HospitalDao hospitalDao;

	@Override
	public Map<String, Object> saveHospital(Map<String, Object> param) {

		// 저장된 병원이 있는지 OPEN API에서 제공해주는 고유코드로 체크
		int hCount = hospitalDao.findByHpid(param.get("hpid").toString());

		System.out.println(hCount);

		if (hCount > 0) {
			System.out.println("이미 존재하는 병원");
			return Maps.json("F-1", "이미 저장된 병원 입니다.");
		}

		hospitalDao.saveHospital(param);
		System.out.println("[ " + param.get("dutyName") + " ] 저장 완료");

		return Maps.json("S-1", "ok");
	}

	@Override
	public Map<String, Object> hospitalList() {
		List<Map<String, Object>> result = hospitalDao.hospitalList();

		return Maps.json("S-1", "ok", result);
	}

	@Override
	public Map<String, Object> removeHospital(String hpid) {
		// TODO Auto-generated method stub
		hospitalDao.removeHospital(hpid);
		return Maps.json("S-1", "ok");
	}

	@Override
	public Map<String, Object> showHospitalByAreaList(String area) {
		// TODO Auto-generated method stub
		System.out.println(" [ area ] " + area);

		List<Map<String, Object>> result = hospitalDao.showHospitalByAreaList(area);

		return Maps.json("S-1", "ok", result);
	}

	@Override
	public Map<String, Object> hospitalAdminMemo(Map<String, Object> param) {
		// TODO Auto-generated method stub
		hospitalDao.hospitalAdminMemo(param);

		return Maps.json("S-1", "해당 병원에 메모가 등록되었습니다.");
	}

	@Override
	public Map<String, Object> findByHospital(String hName) {
		Map<String, Object> result = hospitalDao.findByHospital(hName);

		return Maps.json("S-1", "ok", result);
	}

	@Override
	public Map<String, Object> hospitalMemo(Map<String, Object> param) {
		// TODO Auto-generated method stub
		System.out.println("param : " + param);
		hospitalDao.hospitalMemo(param);
		
		return Maps.json("S-1", "ok");
	}

}
