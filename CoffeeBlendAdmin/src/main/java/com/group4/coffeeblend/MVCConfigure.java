package com.group4.coffeeblend;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.group4.coffeeblend.helpers.Constant;

@Configuration
public class MVCConfigure implements WebMvcConfigurer{
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// TODO Auto-generated method stub
		exposeDirectory(Constant.UPLOAD_FOLDER, registry);
		exposeDirectory(Constant.UPLOAD_IMAGE, registry);
		
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}
	
	
	// Khai báo để Spring biết nơi lưu hình mà mapping về
	private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
		Path uploadDir = Paths.get(dirName);

		String uploadPath = uploadDir.toFile().getAbsolutePath();
		registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:" + uploadPath + "/");
		System.out.println("exposeDirectory: " + uploadPath);
	}
	
	
	
}