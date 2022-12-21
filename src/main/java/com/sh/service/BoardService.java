package com.sh.service;

import java.util.Map;

public interface BoardService {

	Map<String, Object> getBoard();

	Map<String, Object> getNoticeBoard();

	Map<String, Object> article(Map<String, Object> param);

	Map<String, Object> removeArticle(Map<String, Object> param);

}
