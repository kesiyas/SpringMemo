package com.kesiyas.spring.memo.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class hello {

	@ResponseBody
	@RequestMapping("/memo/hello")
	public String helloHorld() {
		
		return "Hello world!";
	}
}
