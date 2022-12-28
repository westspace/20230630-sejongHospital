package com.sh.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.sh.dto.AdminNoticeArticle;

@Mapper
public interface BoardDao {
	List<Map<String, Object>> getBoard();
	List<Map<String, Object>> getNoticeBoard();
	void article(Map<String, Object> param);
	void removeArticle(Map<String, Object> param);
	List<Map<String, Object>> getNoticeArticleList();
	//void noticeArticle(Map<String, Object> param);
	List<Map<String, Object>> getNoticeAllList();
	Map<String, Object> getAdminNoticeDetail(String boardId);
	void adminBoardRemove(Map<String, Object> param);
	void noticeArticle(AdminNoticeArticle article);
	void noticeArticleImage(Map<String, Object> imageMap);
	void adminBoardImageClear(Map<String, Object> param);
	void noticeModify(AdminNoticeArticle article);
	Map<String, Object> findByImageCode(AdminNoticeArticle article);
	void noticeArticleImageClearUpdate(Map<String, Object> imageMap);
}
