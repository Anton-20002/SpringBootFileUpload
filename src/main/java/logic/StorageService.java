package logic;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

@Service
public class StorageService {

  @Value("${spring.servlet.multipart.location}")
  private String path;

  void uploadFile(MultipartFile file) {

    if (file.isEmpty()) {
      throw new StorageException("Failed to store empty file");
    }

    try {
      String filename = file.getOriginalFilename();
      InputStream inputStream = file.getInputStream();
      if (filename != null) {
        Files.copy(inputStream, new File(path, filename).toPath());
      }
    } catch (IOException e) {
      String fail = String.format("Failed to store file %s", file.getName());
      throw new StorageException(fail, e);
    }

  }




}
