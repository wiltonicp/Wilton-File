package cn.wilton.framework.file.modules.controller;

import cn.wilton.framework.file.common.api.WiltonResult;
import cn.wilton.framework.file.common.constant.WiltonConstant;
import cn.wilton.framework.file.common.entity.FileEntity;
import cn.wilton.framework.file.common.exception.WiltonException;
import cn.wilton.framework.file.common.util.FileUtil;
import cn.wilton.framework.file.common.util.IdUtils;
import cn.wilton.framework.file.modules.service.IFileService;
import cn.wilton.framework.file.properties.WiltonProperties;
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
    private final WiltonProperties properties;

    /**
     * 批量上传文件
     * @param files
     * @param folderId
     * @return
     */
    @PostMapping("batch/file")
    public WiltonResult<Void> batchUpload(
                               @RequestParam(value = "files") MultipartFile[] files,
                               Long folderId){
        if(files == null || files.length == 0){
            new WiltonException("请选文件需要上传的文件");
        }
        for(int i = 0;i < files.length;i++){
            MultipartFile file = files[i];
            //保存文件
            if (!file.isEmpty()){
                String fileName = file.getOriginalFilename();
                FileEntity fileInfo = FileUtil.upload(file, properties.path);
                fileInfo.setFolderId(folderId);
                if(fileInfo == null){
                    new WiltonException("文件上传失败!");
                }
                fileInfo.setFileName(fileName);
                fileInfo.setFileType(FileUtil.getFileType(FileUtil.getExtensionName(fileName)));
                fileInfo.setFileSize(new BigDecimal(file.getSize()));
                fileInfo.setOpen(true);
                fileInfo.setCreatedBy(1L);
                fileInfo.setModifyTime(LocalDateTime.now());
                fileService.save(fileInfo);
            }
        }
        return WiltonResult.success();
    }



    /**
     * 查看当前分片是否上传
     *
     * @param request
     * @param response
     */
    @PostMapping("checkblock")
    public void checkMd5(HttpServletRequest request, HttpServletResponse response) {
        //当前分片
        String chunk = request.getParameter("chunk");
        //分片大小
        String chunkSize = request.getParameter("chunkSize");
        //当前文件的MD5值
        String guid = request.getParameter("guid");
        //分片上传路径
        String tempPath = properties.path + File.separator + WiltonConstant.TEMP_PATH;
        File checkFile = new File(tempPath + File.separator + guid + File.separator + chunk);
        response.setContentType("text/html;charset=utf-8");
        try {
            //如果当前分片存在，并且长度等于上传的大小
            if (checkFile.exists() && checkFile.length() == Integer.parseInt(chunkSize)) {
                response.getWriter().write("{\"ifExist\":1}");
            } else {
                response.getWriter().write("{\"ifExist\":0}");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传分片
     *
     * @param file
     * @param chunk 分片下标
     * @param guid 上传文件的MD5值
     * @throws IOException
     */
    @PostMapping("save")
    public void upload(@RequestParam MultipartFile file, Long folderId, Integer chunk, String guid) throws IOException {
        String filePath = properties.path + File.separator + WiltonConstant.TEMP_PATH + File.separator + guid;
        File tempPath = new File(filePath);
        if (!tempPath.exists()) {
            tempPath.mkdirs();
        }
        RandomAccessFile raFile = null;
        BufferedInputStream inputStream = null;
        if (chunk == null) {
            chunk = 0;
        }
        try {
            File dirFile = new File(filePath, String.valueOf(chunk));
            //以读写的方式打开目标文件
            raFile = new RandomAccessFile(dirFile, "rw");
            raFile.seek(raFile.length());
            inputStream = new BufferedInputStream(file.getInputStream());
            byte[] buf = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buf)) != -1) {
                raFile.write(buf, 0, length);
            }
            /**
             * 如果下标为 0，初始化到数据库
             */
            if(chunk == 0){
                FileEntity fileByMd5 = this.fileService.getByFileMd5(guid);
                String fileName = file.getOriginalFilename();
                if(fileByMd5 == null){
                    fileByMd5 = new FileEntity();
                    fileByMd5.setFolderId(folderId);
                    fileByMd5.setFileName(fileName);
                    fileByMd5.setFileType(FileUtil.getFileType(FileUtil.getExtensionName(fileName)));
                    fileByMd5.setFileSize(new BigDecimal(file.getSize()));
                    fileByMd5.setFileMd5(guid);
                    fileByMd5.setOpen(true);
                    fileByMd5.setCreatedBy(1L);
                    fileByMd5.setModifyTime(LocalDateTime.now());
                    this.fileService.save(fileByMd5);
                }else{
                    FileEntity fileInfo = new FileEntity();
                    BeanUtils.copyProperties(fileByMd5,fileInfo);
                    fileInfo.setId(null);
                    fileInfo.setFolderId(folderId);
                    fileInfo.setFileName(fileName);
                    fileInfo.setFileType(FileUtil.getFileType(FileUtil.getExtensionName(fileName)));
                    fileInfo.setFileSize(new BigDecimal(file.getSize()));
                    fileInfo.setFileMd5(guid);
                    fileInfo.setOpen(true);
                    fileInfo.setCreatedBy(1L);
                    fileInfo.setModifyTime(LocalDateTime.now());
                    this.fileService.save(fileInfo);
                }
            }
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (raFile != null) {
                raFile.close();
            }
        }
    }

    /**
     * 合并文件
     *
     * @param guid
     * @param fileName
     */
    @PostMapping("combine")
    public void combineBlock(String guid, String fileName) {
        //分片文件临时目录
        File tempPath = new File(properties.path + File.separator + "temp" + File.separator + guid);
        String realFilePath = properties.path + File.separator + "real" + File.separator + IdUtils.getId() + fileName;
        File realFile = new File(realFilePath);
        /**
         * 文件追加写入
         */
        FileOutputStream os = null;
        FileChannel fcin = null;
        FileChannel fcout = null;
        try {
            log.info("合并文件——开始 [ 文件名称：" + fileName + " ，MD5值：" + guid + " ]");
            os = new FileOutputStream(realFile, true);
            fcout = os.getChannel();
            if (tempPath.exists()) {
                //获取临时目录下的所有文件
                File[] tempFiles = tempPath.listFiles();
                //按名称排序
                Arrays.sort(tempFiles, (o1, o2) -> {
                    if (Integer.parseInt(o1.getName()) < Integer.parseInt(o2.getName())) {
                        return -1;
                    }
                    if (Integer.parseInt(o1.getName()) == Integer.parseInt(o2.getName())) {
                        return 0;
                    }
                    return 1;
                });
                ByteBuffer buffer = ByteBuffer.allocate(10 * 1024 * 1024);
                for (int i = 0; i < tempFiles.length; i++) {
                    FileInputStream fis = new FileInputStream(tempFiles[i]);
                    fcin = fis.getChannel();
                    if (fcin.read(buffer) != -1) {
                        buffer.flip();
                        while (buffer.hasRemaining()) {
                            fcout.write(buffer);
                        }
                    }
                    buffer.clear();
                    fis.close();
                    //删除分片
                    tempFiles[i].delete();
                }
                os.close();
                //删除临时目录
                if (tempPath.isDirectory() && tempPath.exists()) {
                    System.gc(); // 回收资源
                    tempPath.delete();
                }
                /**
                 * 更新数据库文件路径
                 */
                FileEntity fileEntity = this.fileService.getByFileMd5(guid);
                fileEntity.setPath(realFilePath);
                this.fileService.updateById(fileEntity);
                log.info("文件合并——结束 [ 文件名称：" + fileName + " ，MD5值：" + guid + " ]");
            }
        } catch (Exception e) {
            log.error("文件合并——失败 " + e.getMessage());
        }
    }

    /**
     * 查询上传目录下的全部文件
     *
     * @return
     */
    @GetMapping("/getFiles")
    public Map getFiles() {
        Map map = new HashMap();
        map.put("fileList", null);
        return map;

    }

    /**
     * Long 转 Date
     *
     * @param time
     * @return
     */
    private String getDate(Long time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
        Date date = new Date(time);
        return sdf.format(date);
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

    /**
     * 删除文件
     *
     * @param fileName
     * @return
     */
    @GetMapping("/delFile")
    public Map delFile(String fileName) {
        boolean b = false;
        File file = new File(properties.path + File.separator + "real" + File.separator + fileName);
        if (file.exists() && file.isFile()) {
            b = file.delete();
        }
        Map map = new HashMap();
        map.put("result", b + "");
        return map;
    }
}
