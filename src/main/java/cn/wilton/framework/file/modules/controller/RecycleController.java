package cn.wilton.framework.file.modules.controller;

import cn.wilton.framework.file.common.entity.FileEntity;
import cn.wilton.framework.file.common.entity.FolderEntity;
import cn.wilton.framework.file.modules.service.IFileService;
import cn.wilton.framework.file.modules.service.IFolderService;
import cc.vihackerframework.core.api.ViHackerResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 回收站
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/5
 */
@Controller
@RequestMapping("/recycle")
@RequiredArgsConstructor
public class RecycleController {

    private final IFileService fileService;
    private final IFolderService folderService;

    @GetMapping("list")
    public String list(Model model){
        List<FileEntity> fileList = fileService.deletedList();
        List<FolderEntity> folderList = folderService.deletedList();
        model.addAttribute("fileList",fileList);
        model.addAttribute("folderList",folderList);
        return "page-recycle::gridList";
    }

    /**
     * 还原文件
     * @param fileId
     * @return
     */
    @PostMapping("/restoreFile")
    public@ResponseBody
    ViHackerResult<Void> restoreFile(long fileId){
        fileService.restoreById(fileId);
        return ViHackerResult.success();
    }

    /**
     * 还原文件夹
     * @param folderId
     * @return
     */
    @PostMapping("/restoreFolder")
    public@ResponseBody ViHackerResult<Void> restoreFolder(long folderId){
        folderService.restoreById(folderId);
        return ViHackerResult.success();
    }

    /**
     * 永久删除文件
     * @param fileId
     * @return
     */
    @PostMapping("/deleteFile")
    public@ResponseBody ViHackerResult<Void> deleteFile(long fileId){
        fileService.deletePermanentlyById(fileId);
        return ViHackerResult.success();
    }

    /**
     * 删除文件夹
     * @param folderId
     * @return
     */
    @PostMapping("/deleteFolder")
    public@ResponseBody ViHackerResult<Void> deleteFolder(long folderId){
        folderService.deletePermanentlyById(folderId);
        return ViHackerResult.success();
    }
}
