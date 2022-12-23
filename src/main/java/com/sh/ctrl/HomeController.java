package com.sh.ctrl;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sh.dto.CustomUserDetails;

@RestController
public class HomeController {

	@RequestMapping(value = "/", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView home(ModelAndView modelView) throws Exception {

		modelView.setViewName("index");

		return modelView;
	}

	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView loginView(ModelAndView modelView, @RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "exception", required = false) String exception, Model model,
			@AuthenticationPrincipal CustomUserDetails authUser) {

		if (authUser != null) {
			modelView.setViewName("redirect:/");
			return modelView;
		}

		model.addAttribute("error", error);
		model.addAttribute("exception", exception);

		modelView.setViewName("login");
		return modelView;
	}

	@RequestMapping(value = "/notice", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView noticeView(ModelAndView modelView) {

		modelView.setViewName("notice");
		return modelView;
	}

	@RequestMapping(value = "/hospital", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView hospitalView(ModelAndView modelView) {

		modelView.setViewName("hospital");
		return modelView;
	}

	@RequestMapping(value = "/hospitalDetail", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView hospitalDetailView(ModelAndView modelView) {

		modelView.setViewName("hospitalDetail");
		return modelView;
	}

	/*
	 * @RequestMapping(value = "/{path}", method = { RequestMethod.GET,
	 * RequestMethod.POST }) public ModelAndView
	 * dynamicUrlMapping(@PathVariable("path") String path, ModelAndView modelView)
	 * throws Exception {
	 * 
	 * modelView.setViewName(path);
	 * 
	 * return modelView; }
	 */
}