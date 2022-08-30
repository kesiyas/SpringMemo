package com.kesiyas.spring.memo.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	@GetMapping("/signup/view")
	public String singupView() {
		
		return "user/signup";
	}
	
}
