package logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class MyController {

  private final StorageService storageService;

  @Autowired
  public MyController(StorageService storageService) {
    this.storageService = storageService;
  }

  @RequestMapping(value = "/doUpload", method = RequestMethod.POST,
      consumes = {"multipart/form-data"})
  public String upload(@RequestParam MultipartFile file) {

    storageService.uploadFile(file);

    return "redirect:/success.html";
  }

  @ExceptionHandler(StorageException.class)
  public String handleStorageFileNotFound(StorageException e) {

    return "redirect:/failure.html";
  }

}
