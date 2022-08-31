package com.kesiyas.spring.memo.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kesiyas.spring.memo.user.bo.UserBO;

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
	
}
