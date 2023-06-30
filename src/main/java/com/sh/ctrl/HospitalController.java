package com.sh.ctrl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sh.service.HospitalService;

@RestController
public class HospitalController {

	@Autowired
	private HospitalService hospitalService;

	@RequestMapping(value = "/api/saveHospital", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> saveHospital(@RequestBody Map<String, Object> param) {

		System.out.println("[ saveHospital ] : " + param);

		Map<String, Object> result = hospitalService.saveHospital(param);

		return result;
	}

	@RequestMapping(value = "/api/hospitalList", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> hospitalList() {

		Map<String, Object> result = hospitalService.hospitalList();

		return result;
	}
	
	/* 관리자 병원 메모 */
	@RequestMapping(value = "/api/hospitalAdminMemo", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> hospitalAdminMemo(@RequestParam Map<String, Object> param) {

		Map<String, Object> result = hospitalService.hospitalAdminMemo(param);

		return result;
	}
	
	/* 담당자 병원 메모 */
	@RequestMapping(value = "/api/hospitalMemo", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> hospitalMemo(@RequestParam Map<String, Object> param) {

		Map<String, Object> result = hospitalService.hospitalMemo(param);

		return result;
	}
	
	@RequestMapping(value = "/api/removeHospital", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> removeHospital(@RequestParam String hpid) {

		Map<String, Object> result = hospitalService.removeHospital(hpid);

		return result;
	}
	
	@RequestMapping(value = "/api/showHospitalByAreaList", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> showHospitalByAreaList(@RequestParam String area) {

		Map<String, Object> result = hospitalService.showHospitalByAreaList(area);

		return result;
	}
	
	@RequestMapping(value = "/api/findByHospital", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> findByHospital(@RequestParam String hName) {

		Map<String, Object> result = hospitalService.findByHospital(hName);

		return result;
	}
}
