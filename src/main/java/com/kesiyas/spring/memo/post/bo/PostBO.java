package com.kesiyas.spring.memo.post.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public int create(int userId, String title, String content) {
		
		return postDAO.insertMemo(userId, title, content);
		
	}
	
	// id와 일치하는 하나의 메모 얻어오기
	public Post getMemo(int id) {
		
		return postDAO.selectMemo(id);
	}
}
