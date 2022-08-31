package com.kesiyas.spring.memo.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kesiyas.spring.memo.user.bo.UserBO;
import com.kesiyas.spring.memo.user.model.User;

@RestController
@RequestMapping("/user")
public class UserRestController {
	@Autowired
	UserBO userBO;

	// 회원 정보를 입력받고 회원 가입을 하는 기능
	@PostMapping("/signup")
	public Map<String, String> signup(
			@RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			, @RequestParam("name") String name
			, @RequestParam("email") String email) {
		
		Map<String, String> map = new HashMap<>();
		
		int count = userBO.signup(loginId, password, name, email);
		
		if(count == 1) {
			map.put("result", "success");
		} else	{
			map.put("result", "fail");
		}
		
		return map;
	}
	
	// 로그인 과정을 진행하는 기능
	@PostMapping("/signin")
	public Map<String, String> signIn(
			@RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			, HttpServletRequest request) {
		
		
		User user = userBO.getUser(loginId, password);
		
		Map<String, String> map = new HashMap<>();
		
		if(user != null) {
			map.put("result", "success");
			
			HttpSession session = request.getSession();
			// 사용자 이름 저장
			session.setAttribute("userId", user.getId());
			session.setAttribute("userName", user.getName());
			
		} else {
			map.put("result", "fail");
		}
		
		return map;
	}
	
}
