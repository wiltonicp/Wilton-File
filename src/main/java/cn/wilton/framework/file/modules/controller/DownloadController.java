package cn.wilton.framework.file.modules.controller;

import cn.wilton.framework.file.common.constant.WiltonConstant;
import cn.wilton.framework.file.common.entity.FileEntity;
import cn.wilton.framework.file.common.exception.WiltonException;
import cn.wilton.framework.file.common.util.FileUtil;
import cn.wilton.framework.file.modules.service.IFileService;
import cn.wilton.framework.file.properties.WiltonProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 文件下载
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/5
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("download")
public class DownloadController {

    private final IFileService fileService;
    private final WiltonProperties properties;

    @GetMapping("file")
    public void downloadFile(Long fileId,HttpServletRequest request,
                             HttpServletResponse response) throws WiltonException {
        FileEntity entity = fileService.getById(fileId);
        File file = new File(properties.getUserPath() + WiltonConstant.REAL_PATH
                + File.separator + entity.getStoreName());
        FileUtil.downloadFile(request,response,file,entity.getFileName(),false);
    }
}
