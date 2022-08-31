package com.kesiyas.spring.memo.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kesiyas.spring.memo.common.EncryptUtils;
import com.kesiyas.spring.memo.user.dao.UserDAO;
import com.kesiyas.spring.memo.user.model.User;

@Service
public class UserBO {

	@Autowired
	UserDAO userDAO;
	
	// 회원정보를 user 테이블에 저장
	public int signup(String loginId, String password, String name, String email) {
		
		// 비밀번호 암호화
		String encryptPassword  = EncryptUtils.md5(password);
		
		return userDAO.insertUser(loginId, encryptPassword, name, email);
	}
	
	// 아이디 패스워드로 사용자 조회
	public User getUser(String loginId, String password) {
		
		String encryptPassword  = EncryptUtils.md5(password);
		
		return userDAO.selectUser(loginId, encryptPassword);
	}
	
}
