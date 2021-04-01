package cn.wilton.framework.file.modules.controller;

import cn.wilton.framework.file.common.api.WiltonResult;
import cn.wilton.framework.file.common.constant.WiltonConstant;
import cn.wilton.framework.file.common.entity.FileEntity;
import cn.wilton.framework.file.common.exception.WiltonException;
import cn.wilton.framework.file.common.util.FileUtil;
import cn.wilton.framework.file.common.util.IdUtils;
import cn.wilton.framework.file.modules.service.IFileService;
import cn.wilton.framework.file.modules.service.IUploadService;
import cn.wilton.framework.file.properties.WiltonProperties;
import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 文件上传
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/3/15
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/upload")
public class UploadController {

    private final IFileService fileService;
    private final IUploadService uploadService;
    private final WiltonProperties properties;

    /**
     * 查看当前分片是否上传
     * @param request
     */
    @PostMapping("checkblock")
    public WiltonResult checkMd5(HttpServletRequest request) {
        String chunk = request.getParameter("chunk");
        String chunkSize = request.getParameter("chunkSize");
        String guid = request.getParameter("guid");
        return WiltonResult.data(uploadService.checkMd5(chunk, Integer.parseInt(chunkSize), guid));
    }

    /**
     * 上传分片
     * @param file 文件
     * @param folderId 文件夹id
     * @param chunk 分片下标
     * @param guid 上传文件的MD5值
     * @return
     * @throws IOException
     */
    @PostMapping("save")
    public WiltonResult<Void> upload(@RequestParam MultipartFile file, Long folderId, Integer chunk, String guid) throws IOException {
        this.uploadService.uploadFile(file,folderId,chunk,guid);
        return WiltonResult.success();
    }

    /**
     * 合并文件
     * @param guid 上传文件的MD5值
     * @param fileName 文件名称
     */
    @PostMapping("combine")
    public WiltonResult<Void> combineBlock(String guid, String fileName) {
        this.uploadService.combineBlock(guid,fileName);
        return WiltonResult.success();
    }

    /**
     * 文件下载
     *
     * @param fileName 文件名称
     * @param response HttpServletResponse
     */
    @GetMapping("downloadFile")
    public void downLoadFile(String fileName, HttpServletResponse response) {
        File file = new File(properties.path + File.separator + "real" + File.separator + fileName);
        if (file.exists()) {
            InputStream is = null;
            OutputStream os = null;
            try {
                response.reset();
                // 设置强制下载不打开
                response.setContentType("application/force-download");
                //设置下载文件名
                response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
                response.addHeader("Content-Length", "" + file.length());
                //定义输入输出流
                os = new BufferedOutputStream(response.getOutputStream());
                is = new BufferedInputStream(new FileInputStream(file));
                byte[] buffer = new byte[1024];
                int len;
                while ((len = is.read(buffer)) > 0) {
                    os.write(buffer, 0, len);
                    os.flush();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    is.close();
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                log.info("文件下载成功——文件名：" + fileName);
            }
        }
    }

}
