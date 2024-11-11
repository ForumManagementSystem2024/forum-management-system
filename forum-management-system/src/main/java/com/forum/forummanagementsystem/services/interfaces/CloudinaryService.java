package com.forum.forummanagementsystem.services.interfaces;

import com.forum.forummanagementsystem.services.CloudinaryImage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryService {

    CloudinaryImage upload(MultipartFile multipartFile) throws IOException;
}
