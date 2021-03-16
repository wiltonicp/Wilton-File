package cn.wilton.framework.file.modules.controller;

import cn.wilton.framework.file.common.api.WiltonResult;
import cn.wilton.framework.file.common.entity.FileInfo;
import cn.wilton.framework.file.common.exception.WiltonException;
import cn.wilton.framework.file.common.util.FileUtil;
import cn.wilton.framework.file.modules.service.IFileService;
import cn.wilton.framework.file.properties.WiltonProperties;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.UUDecoderStream;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

/**
 *
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/3/15
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/upload")
public class UploadController {

    private final IFileService fileService;
    private final WiltonProperties properties;

    @PostMapping("uploadMultiFile")
    public WiltonResult<Void> upload(
                               @RequestParam("file") MultipartFile file){
        if(file.isEmpty() || file.getSize() == 0){
            new WiltonException("请选文件需要上传的文件");
        }
        String fileName = file.getOriginalFilename();
        FileInfo fileInfo = FileUtil.upload(file, properties.path);
        if(fileInfo == null){
            new WiltonException("文件上传失败!");
        }
        fileInfo.setFileName(fileName);
        fileInfo.setFileType(FileUtil.getFileType(FileUtil.getExtensionName(fileName)));
        fileInfo.setFileSize(file.getSize());
        fileInfo.setOpen(true);
        fileInfo.setUserId(1L);
        fileInfo.setModifyTime(LocalDateTime.now());
        fileService.save(fileInfo);
        return WiltonResult.success();
    }
}
