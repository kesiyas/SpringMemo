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
	
	// 파일 삭제 기능
	public static boolean removeFile(String filePath) { // /images/2_12313141414/test.png
		// 삭제 경로는 /images 를 제거 하고,
		// 실제 파일 저장 경로를 이어 붙여주면 된다.
		// D:\\정민우\\spring\\test\\memofile\\upload/1_4651445145/asdf.jpg
		
		if(filePath == null) {
			return false;
		}
		
		String realFilePath = FILE_UPLOAD_PATH + filePath.replace("/images", "");
		
		Path path = Paths.get(realFilePath);
		
		// 파일이 있는지 확인
		// 파일 삭제
		if(Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		
		// 디렉토리 삭제
		// D:\\정민우\\spring\\test\\memofile\\upload/1_4651445145
		
		// 해당 파일이 포함되어 있는 디렉토리 경로
		path =  path.getParent();
		if(Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		
		return true;
	}
	
}
