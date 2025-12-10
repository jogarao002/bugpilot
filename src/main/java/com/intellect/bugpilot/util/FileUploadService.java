package com.intellect.bugpilot.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileUploadService { 
	
	@Value("${file.base-url}")
	private String fileBaseUrl;

	@Value("${file.upload-dir}")
	private String uploadDir;
	
	public String saveBase64File(String base64Data) throws IOException {

	    String fileId = UUID.randomUUID().toString();
	    byte[] fileBytes = Base64.getDecoder().decode(base64Data);

	    File directory = new File(uploadDir);
	    if (!directory.exists()) directory.mkdirs();

	    String filePath = uploadDir + fileId;
	    try (FileOutputStream fos = new FileOutputStream(filePath)) {
	        fos.write(fileBytes);
	    }

	    return fileBaseUrl + fileId;  
	}
	
	public boolean deleteFile(String filePath) {
	    try {
	        File file = new File(uploadDir + filePath.replace(fileBaseUrl, ""));
	        if (file.exists()) {
	            return file.delete();
	        }
	        return false;
	    } catch (Exception e) {
	        return false;
	    }
	}



}
