package com.sh.ctrl;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sh.dto.AdminNoticeArticle;
import com.sh.dto.CustomUserDetails;
import com.sh.service.BoardService;

@RestController
public class AdminBoardController {

	@Autowired
	private BoardService boardService;

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
	public Map<String, Object> noticeArticle(AdminNoticeArticle article, MultipartHttpServletRequest mtfRequest,
			@AuthenticationPrincipal CustomUserDetails authUser) throws IOException {

		Map<String, Object> result = null;

		if (authUser != null) {
			System.out.println("authUser : " + authUser.getUserCode());
			article.setUSER_CODE(authUser.getUserCode());
			result = boardService.noticeArticle(mtfRequest, article);
		}

		return result;
	}
	
	/* 관리자 공지 수정 */
	@RequestMapping("/api/noticeModify")
	public Map<String, Object> noticeModify(AdminNoticeArticle article,
			@RequestParam(value="formFiles", required = false) MultipartFile mtfRequest,
			@AuthenticationPrincipal CustomUserDetails authUser) throws IOException {

		Map<String, Object> result = null;

		if (authUser != null) {
			System.out.println("authUser : " + authUser.getUserCode());
			article.setUSER_CODE(authUser.getUserCode());
			result = boardService.noticeModify(mtfRequest, article);
		}

		return result;
	}

	/* 관리자 공지 삭제 */
	@RequestMapping("/api/adminBoardRemove")
	public Map<String, Object> adminBoardRemove(@RequestBody Map<String, Object> param,
			@AuthenticationPrincipal CustomUserDetails authUser) throws IOException {

		System.out.println("param : " + param);

		Map result = boardService.adminBoardRemove(param);

		return result;
	}

}
