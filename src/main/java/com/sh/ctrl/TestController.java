package com.sh.ctrl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sh.common.Maps;
import com.sh.service.TestService;

@RestController
public class TestController {
	
	@Autowired
	private TestService testService;

	@RequestMapping(value = "/test", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView testView(ModelAndView modelView) throws Exception {

		modelView.setViewName("test");

		return modelView;
	}

	@RequestMapping(value = "/test/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView test(ModelAndView modelView, Model model, @PathVariable int id) throws Exception {

		modelView.setViewName("test");
		// For input string: "id"
		Map<String, Object> map = new HashMap();

		map.put("detail", id);

		model.addAttribute("detail", map);

		return modelView;
	}

	@RequestMapping(value = "/sqlSessionTest", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> sqlSessionTest() throws Exception {
		
		List<Map<String, Object>> test = testService.sqlSessionTest();
		
		System.out.println("test : " + test);
		
		return Maps.json("", "ok");
	}

}
