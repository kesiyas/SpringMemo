package com.kesiyas.spring.memo.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kesiyas.spring.memo.post.bo.PostBO;
import com.kesiyas.spring.memo.post.model.Post;

@Controller
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostBO postBO;

	// 로그인한 사용자의 메모 리스트를 얻어오는 기능
	@GetMapping("/list/view")
	public String memoList(Model model
			, HttpServletRequest request){
		
		HttpSession session = request.getSession();
		int userId  = (Integer)session.getAttribute("userId");
		
		List<Post> memoList = postBO.getMemoList(userId);
		
		model.addAttribute("memoList", memoList);
		
		return "post/list";
	}
	
	@GetMapping("/create/view")
	public String memoInput() {
		return "post/create";
	}
	
	@GetMapping("/detail/view")
	public String memoDetail(@RequestParam("id") int id, Model model) {
		
		Post post = postBO.getMemo(id);
		
		model.addAttribute("post", post);
		
		return "post/detail";
	}
	
	
	
	
	
}
