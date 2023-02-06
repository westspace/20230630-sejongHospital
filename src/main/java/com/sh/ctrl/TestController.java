package com.sh.ctrl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sh.common.Maps;

@RestController
public class TestController {

	@RequestMapping(value = "/test/{id}", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView test(ModelAndView modelView, Model model, @PathVariable int id) throws Exception {

		modelView.setViewName("test");
		//For input string: "id"
		Map<String, Object> map = new HashMap();

		map.put("detail", id);

		model.addAttribute("detail", map);

		return modelView;
	}

}
