package com.sh.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoardDao {
	List<Map<String, Object>> getBoard();
	List<Map<String, Object>> getNoticeBoard();
	void article(Map<String, Object> param);
	void removeArticle(Map<String, Object> param);
}
