package com.blogging.Application.Services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.web.multipart.MultipartFile;

public interface FileService 
{
	String uploadImage(String  path,MultipartFile file)throws IOException;
    InputStream getResource(String pathString ,String filename)throws FileNotFoundException;
}
