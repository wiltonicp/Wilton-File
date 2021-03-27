package cn.wilton.framework.file.modules.controller;

import cn.wilton.framework.file.common.entity.FolderEntity;
import cn.wilton.framework.file.modules.service.IFolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 文件夹、文件路径操作
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/3/27
 */
@Controller
@RequestMapping("/folder")
@RequiredArgsConstructor
public class FolderController {

    private final IFolderService folderService;

    @GetMapping("list/{parentId}")
    public String listByParentId(Model model,@PathVariable Long parentId){
        List<FolderEntity> list = folderService.list(parentId);
        model.addAttribute("breadcrumb",list);
        return "page-files::breadcrumb";
    }

}
