package cn.wilton.framework.file.modules.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传文件
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/3/15
 */
@RestController
@RequestMapping("/upload")
public class UploadController {


    @PostMapping("uploadMultiFile")
    public void upload(@RequestParam("username") String username,
                       @RequestParam("file") MultipartFile file){

    }
}
