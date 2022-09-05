package com.kesiyas.spring.memo.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.kesiyas.spring.memo.post.model.Post;

@Repository
public interface PostDAO {
	
	// 메모 리스트 전체 조회
	public List<Post> selectMemoList(@Param("userId") int userId);
	
	// 메모 내용 입력
	public int insertMemo(
			@Param("userId") int userId
			, @Param("title") String title
			, @Param("content") String content);
	
	// i와 일치하는 하나의 메모 조회
	public Post selectMemo(@Param("id") int id);
}
