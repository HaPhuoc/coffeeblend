package com.group4.coffeeblend.helpers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtils {

	public static void saveFile(String uploadDir, String fileName,
			MultipartFile multipartFile) throws IOException {
		
		Path uploadPath = Paths.get(uploadDir); //path cua java.nio
		
		if(!Files.exists(uploadPath)) { 
			Files.createDirectories(uploadPath);
		}

		InputStream inputStream = multipartFile.getInputStream();//gui file qua inputstream
		Path filePath = uploadPath.resolve(fileName);
		Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
	}
}
