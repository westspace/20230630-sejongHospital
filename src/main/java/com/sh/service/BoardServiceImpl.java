package com.sh.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.sh.common.Maps;
import com.sh.dao.BoardDao;
import com.sh.dto.AdminNoticeArticle;

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
	public Map<String, Object> getNoticeAllList() {
		List<Map<String, Object>> result = boardDao.getNoticeAllList();
		return Maps.json("S-1", "ok", result);
	}

	@Override
	public Map<String, Object> getAdminNoticeDetail(String boardId) {
		Map<String, Object> result = boardDao.getAdminNoticeDetail(boardId);

		return Maps.json("S-1", "ok", result);
	}

	@Override
	public Map adminBoardRemove(Map<String, Object> param) {
		System.out.println(" adminBoardRemove param : " + param);

		if (param.get("IMAGE_CODE") == null) {
			// System.out.println("이미지 없는 게시글");
			boardDao.adminBoardRemove(param);
			return Maps.json("S-1", "해당 게시글이 삭제되었습니다.");
		}

		try {
			File file = new File(param.get("IMAGE_PATH").toString());

			if (file.exists()) {
				if (file.delete()) {
					System.out.println("파일삭제 성공");
					// ARTICLE_CODE
				} else {
					System.out.println("파일삭제 실패");
				}
			} else {
				System.out.println("파일이 존재하지 않습니다.");
			}

			boardDao.adminBoardImageClear(param);
			boardDao.adminBoardRemove(param);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("[ adminBoardRemove ] 파일 삭제중 에러가 발생했습니다. " + e.getMessage());
		}

		return Maps.json("S-1", "해당 게시글이 삭제되었습니다.");
	}

	@Override
	public Map<String, Object> noticeArticle(MultipartHttpServletRequest mtfRequest, AdminNoticeArticle article) {
		// TODO Auto-generated method stub

		System.out.println("AdminNoticeArticle : " + article);

		List<MultipartFile> fileList = mtfRequest.getFiles("formFiles");

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

					boardDao.noticeArticle(article);

					Map<String, Object> imageMap = new HashMap<>();

					imageMap.put("originFileName", originFileName);
					imageMap.put("safeFile", safeFile);
					imageMap.put("article_code", article.getARTICLE_CODE());

					boardDao.noticeArticleImage(imageMap);

					return Maps.json("S-1", "게시글이 등록되었습니다.");

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

		boardDao.noticeArticle(article);

		System.out.println(article.getARTICLE_CODE());

		return Maps.json("S-1", "게시글이 등록되었습니다.");
	}

	@Override
	public Map<String, Object> noticeModify(MultipartFile mtfRequest, AdminNoticeArticle article) {
		System.out.println("noticeModify : " + article);

		if (mtfRequest != null) {
			String originFileName = mtfRequest.getOriginalFilename(); // 원본 파일 명
			// long fileSize = fileList.getSize(); // 파일 사이즈
			String safeFile = path + "articleImage/" + System.currentTimeMillis() + originFileName;

			try {

				mtfRequest.transferTo(new File(safeFile));

				Map<String, Object> result = boardDao.findByImageCode(article);
				Map<String, Object> imageMap = new HashMap<>();
				imageMap.put("originFileName", originFileName);
				imageMap.put("safeFile", safeFile);
				imageMap.put("article_code", article.getARTICLE_CODE());

				if (result != null) {
					// 등록된 이미지가 있음 
					// 이미지 삭제 후 DB 업데이트 해줘야 됨
					
					File file = new File(result.get("IMAGE_PATH").toString());

					if (file.exists()) {
						if (file.delete()) {
							System.out.println("파일삭제 성공");
						} else {
							System.out.println("파일삭제 실패");
						}
					} else {
						System.out.println("파일이 존재하지 않습니다.");
					}
					
					boardDao.noticeArticleImageClearUpdate(imageMap);

					return Maps.json("S-1", "관리자 공지사항 수정 완료");
				}

				boardDao.noticeArticleImage(imageMap);
				
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("notify modify : " + e.getMessage());
			}

		}

		boardDao.noticeModify(article);

		return Maps.json("S-1", "관리자 공지사항 수정 완료");
	}
}
