package com.kesiyas.spring.memo.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FileManagerService {
	
	public static final String FILE_UPLOAD_PATH = "D:\\정민우\\spring\\test\\memofile\\upload";

	// 파일을 저장하고, 해당 파일을 접근할 경로를 돌려주는 기능
	public static String saveFile(int userId, MultipartFile file) {
		
		// 서버에 저장할 위치를 잡아준다
		// 디렉토리 (폴더) 생성
		// 사용자 id + 현재 시간
		// UNIX TIME : 1970 년 1월1일을 기준으로 현재까지 흐른 시간(millisecond 1/1000)
		// 1_4651445145
		// D:\\정민우\\spring\\test\\memofile\\upload\\1_4651445145\\asdf.jpg
		
		String directoryName = "/" + userId + "_" + System.currentTimeMillis() + "/";
		
		// 디렉 토리 생성
		String filePath = FILE_UPLOAD_PATH + directoryName;	
		File directory = new File(filePath);
		
		if(directory.mkdir() == false) {
			// 디렉토리 생성 실패!
			return null;
		}
		
		try {
			byte[] bytes = file.getBytes();
			String fileFullPath = filePath + file.getOriginalFilename();
			Path path = Paths.get(fileFullPath);
			Files.write(path, bytes);
			
		} catch (IOException e) {
			e.printStackTrace();
			
			// 파일 저장 실패
			return null;
		}
		
		// 클라이언트에서 접근 가능한 경로
		// D:\\정민우\\spring\\test\\memofile\\upload 해당 디렉토리 아래 경로
		// /images/~
		
		return "/images" + directoryName + file.getOriginalFilename();
		
		
	}
	
}
