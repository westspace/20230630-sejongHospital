package com.sh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminNoticeArticle {
	private String ARTICLE_CODE;
	private String BOARD_CODE;
	private String USER_CODE;
	private String REG_DATE;
	private String TITLE;
	private String BODY;
}
