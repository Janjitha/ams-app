package com.ams.utility;

import com.ams.exception.FileInvalidExtensionException;
import com.ams.exception.FileNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class FileUtility {

    public static void validateFile(MultipartFile file){
        if(file.isEmpty())
            throw new FileNotFoundException("Please select file to upload");

        List<String> allowedExts = List.of("png", "jpeg", "jpg");
        // Extract the extension of uploaded file
        String filename = file.getOriginalFilename(); //laptop.jpeg
        String ext = filename.split("\\.")[1];

        if(!allowedExts.contains(ext))
            throw new FileInvalidExtensionException(ext + " not allowed");
    }
}