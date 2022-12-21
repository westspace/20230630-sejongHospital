package com.sh.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sh.common.Maps;
import com.sh.dao.BoardDao;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao boardDao;

	@Override
	public Map<String, Object> getBoard() {

		List<Map<String, Object>> result = boardDao.getBoard();

		return Maps.json("S-1", "ok", result);
	}

	@Override
	public Map<String, Object> getNoticeBoard() {
		List<Map<String, Object>> result = boardDao.getNoticeBoard();

		return Maps.json("S-1", "ok", result);
	}

	@Override
	public Map<String, Object> article(Map<String, Object> param) {
		boardDao.article(param);
		return Maps.json("S-1", "ok");
	}

	@Override
	public Map<String, Object> removeArticle(Map<String, Object> param) {
		boardDao.removeArticle(param);
		return Maps.json("S-1", "ok");
	}
}
