package com.mohyehia.bookstore.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mohyehia.bookstore.utils.ImageUtils;

@RestController
@RequestMapping("/api/v1/images")
public class ImageController {
	
	@PostMapping("/upload")
	public String upload(@RequestParam("image") MultipartFile file) {
		String name = ImageUtils.uploadImage(file);
		if(name != null)
			return String.format("New image with name %s added successfully!", name);
		return "Error uploading the image!";
	}
}
