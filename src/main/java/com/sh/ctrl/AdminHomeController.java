package com.sh.ctrl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sh.service.BoardService;

@RestController
public class AdminHomeController {
	
	@Autowired
	private BoardService boardService;
	
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
	
	@RequestMapping(value = "/admin/noticeDetail/{boardId}", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView adminNoticeView(ModelAndView modelView, @PathVariable String boardId) throws Exception {

		System.out.println("boardId : " + boardId);
		
		Map<String, Object> result = boardService.getAdminNoticeDetail(boardId);

		modelView.addObject("boardData", result);
		
		modelView.setViewName("adminNoticeDetail");

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
