package com.mohyehia.bookstore.utils;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class ImageUtils {
	
	@Value("${book.uploads-directory}")
	private static final String UPLOADS_DIRECTORY = "/home/mohammed/projects-static/bookstore-api";
	
	public static String uploadImage(MultipartFile file) {
		if(!file.isEmpty()) {
			try {
				//get the directory to store the file
				File dir = new File(UPLOADS_DIRECTORY);
				if(!dir.exists()) dir.mkdirs();
				
				//creating the file on server
				String extension = FilenameUtils.getExtension(file.getOriginalFilename());
				String name = System.currentTimeMillis() + "." + extension;
				String completeName = dir.getAbsolutePath() + File.separator + name;
				File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
				FileCopyUtils.copy(file.getBytes(), serverFile);
				return completeName;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
