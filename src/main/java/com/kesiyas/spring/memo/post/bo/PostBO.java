package com.kesiyas.spring.memo.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kesiyas.spring.memo.common.FileManagerService;
import com.kesiyas.spring.memo.post.dao.PostDAO;
import com.kesiyas.spring.memo.post.model.Post;

@Service
public class PostBO {

	@Autowired
	private PostDAO postDAO;
	
	// 메모 전체 리스트 조회
	public List<Post> getMemoList(int userId) { 
		
		return postDAO.selectMemoList(userId);
		
	}
	
	// 메모 내용 입력
	public int create(int userId, String title, String content, MultipartFile file) {
		
		// 파이릉 서버에 특정 위치에 저장
		// 해당 파일을 접근할 수 있는 주소 경로를 dao에 전달
		String imgPath = null;
		
		if(file != null) {
			
			imgPath = FileManagerService.saveFile(userId, file);
			
			if(imgPath == null) {
				// 파일 저장 실패
				return 0;
			}
		}			
		return postDAO.insertMemo(userId, title, content, imgPath);
		
	}
	
	// id와 일치하는 하나의 메모 얻어오기
	public Post getMemo(int id) {
		
		return postDAO.selectMemo(id);
	}
	
	// 게시물 수정하기
	public int updatePost(int postId, String title, String content) {
		
		return postDAO.updatePost(postId, title, content);
	}
}
