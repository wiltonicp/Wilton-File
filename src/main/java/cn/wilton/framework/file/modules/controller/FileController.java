package cn.wilton.framework.file.modules.controller;

import cn.wilton.framework.file.common.entity.FileEntity;
import cn.wilton.framework.file.common.entity.FolderEntity;
import cn.wilton.framework.file.modules.service.IFileService;
import cn.wilton.framework.file.modules.service.IFolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
