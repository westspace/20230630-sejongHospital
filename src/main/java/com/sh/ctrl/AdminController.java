package com.sh.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AdminController {
	
	@RequestMapping(value = "/admin", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView adminView(ModelAndView modelView) throws Exception {

		modelView.setViewName("admin");

		return modelView;
	}

	@RequestMapping(value = "/admin/user", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView adminUserView(ModelAndView modelView) throws Exception {

		modelView.setViewName("adminUser");

		return modelView;
	}

	@RequestMapping(value = "/admin/notice", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView adminNoticeView(ModelAndView modelView) throws Exception {

		modelView.setViewName("adminNotice");

		return modelView;
	}

	@RequestMapping(value = "/admin/hospital", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView adminHospitalView(ModelAndView modelView) throws Exception {

		modelView.setViewName("adminHospital");

		return modelView;
	}

	@RequestMapping(value = "/admin/noticeWrite", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView adminNoticeWriteView(ModelAndView modelView) throws Exception {

		modelView.setViewName("adminNoticeWrite");

		return modelView;
	}
}
