package com.kesiyas.spring.memo.post;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kesiyas.spring.memo.post.model.Post;

@RestController
@RequestMapping("/post")
public class PostRestController {

	public List<Post> getPostList(){
		
		List<Post> list = new ArrayList<>();
		
		return list;
	}
}
