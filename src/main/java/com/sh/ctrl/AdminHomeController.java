package com.sh.ctrl;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sh.dto.CustomUserDetails;
import com.sh.service.BoardService;

@RestController
public class AdminHomeController {

	@Autowired
	private BoardService boardService;

	@RequestMapping(value = "/admin", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView adminView(ModelAndView modelView, @AuthenticationPrincipal CustomUserDetails authUser,
			HttpServletRequest request) throws Exception {
		
		System.out.println("authUser : " + authUser.getAuthorities());
		
		
		modelView.setViewName("admin");

		return modelView;
	}

	@RequestMapping(value = "/admin/user", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView adminUserView(ModelAndView modelView, @AuthenticationPrincipal CustomUserDetails authUser)
			throws Exception {

		modelView.setViewName("adminUser");

		if (!authUser.getUserRoles().get(0).equals("관리자")) {

			modelView.setViewName("redirect:/");
		}

		return modelView;
	}

	@RequestMapping(value = "/admin/notice", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView adminNoticeView(ModelAndView modelView, @AuthenticationPrincipal CustomUserDetails authUser)
			throws Exception {

		modelView.setViewName("adminNotice");

		if (!authUser.getUserRoles().get(0).equals("관리자")) {

			modelView.setViewName("redirect:/");
		}

		return modelView;
	}

	@RequestMapping(value = "/admin/noticeDetail/{boardId}", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView adminNoticeView(ModelAndView modelView, @PathVariable String boardId,
			@AuthenticationPrincipal CustomUserDetails authUser) throws Exception {

		System.out.println("boardId : " + boardId);

		Map<String, Object> result = boardService.getAdminNoticeDetail(boardId);

		modelView.addObject("boardData", result);

		modelView.setViewName("adminNoticeDetail");

		if (!authUser.getUserRoles().get(0).equals("관리자")) {

			modelView.setViewName("redirect:/");
		}

		return modelView;
	}

	@RequestMapping(value = "/admin/noticeModify/{boardId}", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView adminNoticeModifyView(ModelAndView modelView, @PathVariable String boardId,
			@AuthenticationPrincipal CustomUserDetails authUser) throws Exception {

		System.out.println("boardId : " + boardId);

		Map<String, Object> result = boardService.getAdminNoticeDetail(boardId);

		modelView.addObject("boardData", result);
		modelView.setViewName("admiNoticeModify");

		if (!authUser.getUserRoles().get(0).equals("관리자")) {

			modelView.setViewName("redirect:/");
		}

		return modelView;
	}

	@RequestMapping(value = "/admin/hospital", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView adminHospitalView(ModelAndView modelView, @AuthenticationPrincipal CustomUserDetails authUser)
			throws Exception {

		modelView.setViewName("adminHospital");

		if (!authUser.getUserRoles().get(0).equals("관리자")) {

			modelView.setViewName("redirect:/");
		}

		return modelView;
	}

	@RequestMapping(value = "/admin/noticeWrite", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView adminNoticeWriteView(ModelAndView modelView,
			@AuthenticationPrincipal CustomUserDetails authUser) throws Exception {

		modelView.setViewName("adminNoticeWrite");

		if (!authUser.getUserRoles().get(0).equals("관리자")) {

			modelView.setViewName("redirect:/");
		}

		return modelView;
	}
}
