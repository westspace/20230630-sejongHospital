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

	/* 관리자 공지 */
	@RequestMapping("/api/article")
	public Map<String, Object> article(@RequestParam Map<String, Object> param) {

		System.out.println("[ article ] : " + param);

		Map<String, Object> result = boardService.article(param);

		return result;
	}

	/* 게시글 삭제 */
	@RequestMapping("/api/removeArticle")
	public Map<String, Object> removeArticle(@RequestParam Map<String, Object> param) {

		System.out.println("[ removeArticle ] : " + param);

		Map<String, Object> result = boardService.removeArticle(param);

		return result;
	}

	/* 관리자 공지 리스트 */
	@RequestMapping("/api/getNoticeArticleList")
	public Map<String, Object> getNoticeArticleList() {

		Map<String, Object> result = boardService.getNoticeArticleList();

		return result;
	}

	/* 관리자 공지 등록 */
	@RequestMapping("/api/noticeArticle")
	public Map<String, Object> noticeArticle(@RequestParam Map<String, Object> param,
			MultipartHttpServletRequest mtfRequest, @AuthenticationPrincipal CustomUserDetails authUser)
			throws IOException {

		Map<String, Object> result = null;

		// if (authUser != null) {
		// System.out.println("authUser : " + authUser.getUserCode());
		result = boardService.noticeArticle(param, mtfRequest);
		// }

		return result;
	}

}
