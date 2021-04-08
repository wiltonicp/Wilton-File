package cn.wilton.framework.file.modules.controller;

import cn.wilton.framework.file.common.api.WiltonResult;
import cn.wilton.framework.file.common.constant.WiltonConstant;
import cn.wilton.framework.file.common.entity.FileEntity;
import cn.wilton.framework.file.common.entity.FolderEntity;
import cn.wilton.framework.file.common.exception.BizException;
import cn.wilton.framework.file.common.exception.WiltonException;
import cn.wilton.framework.file.common.util.FileUtil;
import cn.wilton.framework.file.common.util.PicUtil;
import cn.wilton.framework.file.modules.service.IFileService;
import cn.wilton.framework.file.modules.service.IFolderService;
import cn.wilton.framework.file.properties.WiltonProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;


/**
 * 文件操作
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/3/27
 */
@Controller
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileController {

    private final IFileService fileService;
    private final WiltonProperties properties;
    private final IFolderService folderService;

    /**
     * 根据文件夹id查询 文件和文件夹
     * @param model
     * @param folderId
     * @return
     */
    @GetMapping("list")
    public String listPage(Model model,Long folderId){
        List<FileEntity> fileList = fileService.listPage(folderId);
        List<FolderEntity> folderList = folderService.list(folderId);
        model.addAttribute("fileList",fileList);
        model.addAttribute("folderList",folderList);
        return "page-files::gridTable";
    }


    @GetMapping("listToTable")
    public String list(Model model,Long folderId){
        List<FileEntity> fileList = fileService.listPage(folderId);
        List<FolderEntity> folderList = folderService.list(folderId);
        model.addAttribute("fileList",fileList);
        model.addAttribute("folderList",folderList);
        return "page-files::gridList";
    }

    @PostMapping("/update")
    public@ResponseBody WiltonResult<Void> update(FileEntity fileEntity){
        FileEntity entity = fileService.getById(fileEntity.getId());
        BeanUtils.copyProperties(fileEntity,entity);
        entity.update();
        fileService.updateById(entity);
        return WiltonResult.success();
    }

    /**
     * 删除文件
     * @param fileId
     * @return
     */
    @PostMapping("/delete")
    public@ResponseBody WiltonResult<Void> delete(long fileId){
        boolean b = fileService.removeById(fileId);
        return WiltonResult.success();
    }

    /**
     * 文件预览
     * @param request
     * @param response
     */
    @GetMapping("thumb")
    public@ResponseBody void preview(String fid, Long md, HttpServletResponse response) throws BizException{
        FileEntity fileEntity = fileService.getByFileId(Long.valueOf(FileUtil.getFileNameNoEx(fid)));
        if(fileEntity == null){
            new BizException("参数无效!");
        }
        File file = new File(properties.getUserPath() + WiltonConstant.REAL_PATH
                + File.separator + fileEntity.getStoreName());
        if (file.exists()) {
            byte[] data = null;
            FileInputStream input=null;
            try {
                input= new FileInputStream(file);
                data = new byte[input.available()];
                //图片压缩
                byte[] bytes = PicUtil.compressPicForScale(data, 500, fid);
                input.read(bytes);
                response.getOutputStream().write(bytes);
            } catch (Exception e) {
                new WiltonException("文件处理异常");
            }finally{
                try {
                    if(input!=null){
                        input.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
