package cn.wilton.framework.file.common.util;

import cc.vihackerframework.core.exception.ViHackerException;
import cc.vihackerframework.core.exception.ViHackerRuntimeException;
import cn.hutool.core.util.IdUtil;
import cn.wilton.framework.file.common.constant.WiltonConstant;
import cn.wilton.framework.file.common.entity.FileEntity;
import cn.wilton.framework.file.common.entity.enums.FileTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/3/15
 */
@Slf4j
public class FileUtil extends cn.hutool.core.io.FileUtil{

    /**
     * 系统临时目录
     * <br>
     * windows 包含路径分割符，但Linux 不包含,
     * 在windows \\==\ 前提下，
     * 为安全起见 同意拼装 路径分割符，
     * <pre>
     *       java.io.tmpdir
     *       windows : C:\Users/xxx\AppData\Local\Temp\
     *       linux: /temp
     * </pre>
     */
    public static final String SYS_TEM_DIR = System.getProperty("java.io.tmpdir") + File.separator;
    /**
     * 定义GB的计算常量
     */
    public static final int GB = 1024 * 1024 * 1024;
    /**
     * 定义MB的计算常量
     */
    private static final int MB = 1024 * 1024;
    /**
     * 定义KB的计算常量
     */
    private static final int KB = 1024;

    /**
     * 获取文件大小单位为B的double值
     */
    public static final int SIZE_TYPE_B = 1;
    /**
     * 获取文件大小单位为KB的double值
     */
    public static final int SIZE_TYPE_KB = 2;
    /**
     * 获取文件大小单位为MB的double值
     */
    public static final int SIZE_TYPE_MB = 3;
    /**
     * 获取文件大小单位为GB的double值
     */
    public static final int SIZE_TYPE_GB = 4;

    /**
     * 格式化小数
     */
    private static final DecimalFormat DF = new DecimalFormat("0.00");

    public static final String IMAGE = "图片";
    public static final String TXT = "文档";
    public static final String MUSIC = "音乐";
    public static final String VIDEO = "视频";
    public static final String OTHER = "其他";


    /**
     * MultipartFile转File
     */
    public static File toFile(MultipartFile multipartFile) {
        // 获取文件名
        String fileName = multipartFile.getOriginalFilename();
        // 获取文件后缀
        String prefix = "." + getExtensionName(fileName);
        File file = null;
        try {
            // 用uuid作为文件名，防止生成的临时文件重复
            file = File.createTempFile(IdUtil.simpleUUID(), prefix);
            // MultipartFile to File
            multipartFile.transferTo(file);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return file;
    }

    /**
     * 获取文件扩展名，不带 .
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    /**
     * Java文件操作 获取不带扩展名的文件名
     */
    public static String getFileNameNoEx(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length()))) {
                return filename.substring(0, dot);
            }
        }
        return filename;
    }

    /**
     * 文件大小转换
     */
    public static String getSize(long size) {
        String resultSize;
        if (size / GB >= 1) {
            //如果当前Byte的值大于等于1GB
            resultSize = DF.format(size / (double) GB) + " GB ";
        } else if (size / MB >= 1) {
            //如果当前Byte的值大于等于1MB
            resultSize = DF.format(size / (double) MB) + " MB ";
        } else if (size / KB >= 1) {
            //如果当前Byte的值大于等于1KB
            resultSize = DF.format(size / (double) KB) + " KB ";
        } else {
            resultSize = size + " B   ";
        }
        return resultSize;
    }

    /**
     * 转换文件大小,指定转换的类型
     * @param fileS 文件大小
     * @param SIZE_TYPE
     * @return
     */
    private static double FormetFileSize(long fileS, int SIZE_TYPE) {
        DecimalFormat df = new DecimalFormat("#.00");
        double fileSizeLong = 0;
        switch (SIZE_TYPE) {
            case SIZE_TYPE_B:
                fileSizeLong = Double.valueOf(df.format((double) fileS));
                break;
            case SIZE_TYPE_KB:
                fileSizeLong = Double.valueOf(df.format((double) fileS / KB));
                break;
            case SIZE_TYPE_MB:
                fileSizeLong = Double.valueOf(df.format((double) fileS / MB));
                break;
            case SIZE_TYPE_GB:
                fileSizeLong = Double.valueOf(df.format((double) fileS / GB));
                break;
            default:
                break;
        }
        return fileSizeLong;
    }

    /**
     * inputStream 转 File
     */
    static File inputStreamToFile(InputStream ins, String name) throws Exception {
        File file = new File(SYS_TEM_DIR + name);
        if (file.exists()) {
            return file;
        }
        OutputStream os = new FileOutputStream(file);
        int bytesRead;
        int len = 8192;
        byte[] buffer = new byte[len];
        while ((bytesRead = ins.read(buffer, 0, len)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        os.close();
        ins.close();
        return file;
    }

    /**
     * 将文件名解析成文件的上传路径
     */
    public static FileEntity upload(MultipartFile file, String filePath) {
        String uuid = IdUtil.simpleUUID();
        String suffix = getExtensionName(file.getOriginalFilename());
        String nowStr = "-" + DateUtil.formatFullTime(LocalDateTime.now(), DateUtil.FULLS_TIME_PATTERN);
        try {
            String fileName = uuid + nowStr + "." + suffix;
            String path = filePath + fileName;
            // getCanonicalFile 可解析正确各种路径
            File dest = new File(path).getCanonicalFile();
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                if (!dest.getParentFile().mkdirs()) {
                    log.error("was not successful");
                }
            }
            // 文件写入
            file.transferTo(dest);
            log.info("文件写入成功");
            FileEntity fileInfo = new FileEntity();
            fileInfo.setStoreName(path);
            return fileInfo;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public static String getFileType(String type) {
        String documents = "txt doc pdf ppt pps xlsx xls docx";
        String music = "mp3 wav wma mpa ram ra aac aif m4a";
        String video = "avi mpg mpe mpeg asf wmv mov qt rm mp4 flv m4v webm ogv ogg";
        String image = "bmp dib pcp dif wmf gif jpg tif eps psd cdr iff tga pcd mpt png jpeg ico";
        if (image.contains(type)) {
            return FileTypeEnum.IMAGE.getCode();
        } else if (documents.contains(type)) {
            return FileTypeEnum.TXT.getCode();
        } else if (music.contains(type)) {
            return FileTypeEnum.MUSIC.getCode();
        } else if (video.contains(type)) {
            return FileTypeEnum.VIDEO.getCode();
        } else {
            return FileTypeEnum.OTHER.getCode();
        }
    }

    public static void checkSize(long maxSize, long size) {
        // 1M
        int len = 1024 * 1024;
        if (size > (maxSize * len)) {
            throw new ViHackerRuntimeException("文件超出规定大小");
        }
    }

    /**
     * 判断两个文件是否相同
     */
    public static boolean check(File file1, File file2) {
        String img1Md5 = getMd5(file1);
        String img2Md5 = getMd5(file2);
        return img1Md5.equals(img2Md5);
    }

    /**
     * 判断两个文件是否相同
     */
    public static boolean check(String file1Md5, String file2Md5) {
        return file1Md5.equals(file2Md5);
    }

    private static byte[] getByte(File file) {
        // 得到文件长度
        byte[] b = new byte[(int) file.length()];
        try {
            InputStream in = new FileInputStream(file);
            try {
                System.out.println(in.read(b));
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(), e);
            return null;
        }
        return b;
    }

    private static String getMd5(byte[] bytes) {
        // 16进制字符
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(bytes);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            // 移位 输出字符串
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 下载文件
     * @param request
     * @param response
     * @param file
     * @param fileName 下载文件名称
     * @param deleteOnExit 下载后是否删除源文件
     * @throws ViHackerException
     */
    public static void downloadFile(HttpServletRequest request, HttpServletResponse response, File file,String fileName, boolean deleteOnExit) throws ViHackerException {
        if (!file.exists()) {
            throw new ViHackerException("文件未找到");
        }
        response.setCharacterEncoding(request.getCharacterEncoding());
        // 设置强制下载不打开
        //response.setContentType("application/force-download");
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
            response.addHeader(HttpHeaders.CONTENT_LENGTH, "" + file.length());
            IOUtils.copy(fis, response.getOutputStream());
            response.flushBuffer();
        } catch (Exception e) {
            throw new ViHackerException("文件下载失败！");
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                    if (deleteOnExit) {
                        file.deleteOnExit();
                    }
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
    }

    public static String getMd5(File file) {
        return getMd5(getByte(file));
    }

    /**
     * 初始化文件路径
     * @param path
     */
    public static void initLoginUserFilePath(String path){
        /**
         * 初始化上传路径
         */
        File realPath = new File(path + File.separator + WiltonConstant.REAL_PATH + File.separator);
        if (!realPath.exists()) {
            realPath.mkdirs();
        }
        File tempPath = new File(path + File.separator + WiltonConstant.TEMP_PATH + File.separator);
        if (!tempPath.exists()) {
            tempPath.mkdirs();
        }
    }
}
