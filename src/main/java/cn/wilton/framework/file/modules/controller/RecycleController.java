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

    @GetMapping("listToTable")
    public String list(Model model,Long folderId){
        List<FileEntity> fileList = fileService.listPage(folderId);
        List<FolderEntity> folderList = folderService.list(folderId);
        model.addAttribute("fileList",fileList);
        model.addAttribute("folderList",folderList);
        return "page-recycle::gridList";
    }
}
