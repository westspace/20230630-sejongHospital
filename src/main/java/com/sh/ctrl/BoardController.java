package com.sh.ctrl;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sh.dto.CustomUserDetails;
import com.sh.service.BoardService;

@RestController
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping("/api/getBoard")
	public Map<String, Object> getBoard(@RequestParam Map<String, Object> param) {

		System.out.println("[ getBoard ] : " + param);

		Map<String, Object> result = boardService.getBoard();

		return result;
	}

	@RequestMapping(value = "/api/getNoticeBoard", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> getNoticeBoard(@RequestParam Map<String, Object> param) {

		System.out.println("[ getNoticeBoard ]");

		Map<String, Object> result = boardService.getNoticeBoard();

		return result;
	}

	@RequestMapping(value = "/api/getNoticeAllList", method = { RequestMethod.GET, RequestMethod.POST })
	public Map<String, Object> getNoticeAllList(@RequestParam Map<String, Object> param) {

		System.out.println("[ getNoticeAllList ]");

		Map<String, Object> result = boardService.getNoticeAllList();

		return result;
	}

	
}
