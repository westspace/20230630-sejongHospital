package com.sh.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sh.common.Maps;
import com.sh.dao.BoardDao;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao boardDao;

	@Value("${files.external}")
	String path;

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

	@Override
	public Map<String, Object> getNoticeArticleList() {
		// TODO Auto-generated method stub
		List<Map<String, Object>> result = boardDao.getNoticeArticleList();
		return Maps.json("S-1", "ok", result);
	}

	@Override
	public Map<String, Object> noticeArticle(Map<String, Object> param, MultipartHttpServletRequest mtfRequest) {
		// TODO Auto-generated method stub

		System.out.println("param : " + param);
		
		List<MultipartFile> fileList = mtfRequest.getFiles("formFiles");

		param.put("USER_CODE", 1);
		
		if (fileList.size() > 0) {
			for (MultipartFile mf : fileList) {
				String originFileName = mf.getOriginalFilename(); // 원본 파일 명
				long fileSize = mf.getSize(); // 파일 사이즈
				String safeFile = path + "articleImage/" + System.currentTimeMillis() + originFileName;

				System.out.println("originFileName : " + originFileName);
				System.out.println("fileSize : " + fileSize);
				System.out.println("safeFile : " + safeFile);

				try {
					mf.transferTo(new File(safeFile));

				} catch (IllegalStateException e) {
					System.out.println("[ noticeArticle IllegalStateException ] : " + e.getMessage());
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("[ noticeArticle IOException ] : " + e.getMessage());
					e.printStackTrace();
				}
			}
		}

		boardDao.noticeArticle(param);

		return Maps.json("S-1", "ok");
	}

	@Override
	public Map<String, Object> getNoticeAllList() {
		List<Map<String, Object>> result = boardDao.getNoticeAllList();
		return Maps.json("S-1", "ok", result);
	}
}
