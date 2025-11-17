package com.intellect.bugpilot.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class FileUploadService {
	
	public String saveBase64File(String base64Data) throws IOException {

	    // Generate unique ID for file
	    String fileId = UUID.randomUUID().toString();

	    // Decode Base64
	    byte[] fileBytes = Base64.getDecoder().decode(base64Data);

	    // Build file path inside Tomcat
	    String uploadDir = "/opt/uploaddir/";
	    File directory = new File(uploadDir);

	    if (!directory.exists()) {
	        directory.mkdirs(); // create folder if not exists
	    }

	    // Full file path: /tomcat/uploads/<unique-id>
	    String filePath = uploadDir + fileId;

	    // Write file
	    FileOutputStream fos = new FileOutputStream(filePath);
	    fos.write(fileBytes);
	    fos.close();

	    // Return unique file path (store this in DB)
	    return filePath;
	}


}
