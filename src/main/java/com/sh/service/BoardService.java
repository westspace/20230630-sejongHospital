package com.sh.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface BoardService {

	Map<String, Object> getBoard();

	Map<String, Object> getNoticeBoard();

	Map<String, Object> article(Map<String, Object> param);

	Map<String, Object> removeArticle(Map<String, Object> param);

	Map<String, Object> getNoticeArticleList();

	Map<String, Object> noticeArticle(Map<String, Object> param, MultipartHttpServletRequest mtfRequest);

	Map<String, Object> getNoticeAllList();

}
