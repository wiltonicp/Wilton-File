package cn.wilton.framework.file.modules.controller;

import cn.wilton.framework.file.common.entity.FolderEntity;
import cn.wilton.framework.file.modules.service.IFolderService;
import com.vihackerframework.common.api.ViHackerResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 根据 id 查询所有父节点
     * @param model
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public String listByParentId(Model model,@PathVariable Long id){
        List<FolderEntity> folderList = folderService.findParentById(id);
        model.addAttribute("breadcrumb",folderList);
        return "page-files::breadcrumbList";
    }

    /**
     * 新建文件夹
     * @param folderEntity
     * @return
     */
    @PostMapping("/add")
    public@ResponseBody
    ViHackerResult<FolderEntity> add(@Validated FolderEntity folderEntity){
        folderEntity.created();
        boolean save = folderService.save(folderEntity);
        return ViHackerResult.data(folderEntity);
    }

    /**
     * 修改文件夹名称
     * @param folderEntity
     * @return
     */
    @PostMapping("/update")
    public@ResponseBody ViHackerResult<Void> update(FolderEntity folderEntity){
        FolderEntity entity = folderService.getById(folderEntity.getId());
        BeanUtils.copyProperties(folderEntity,entity);
        entity.update();
        folderService.updateById(entity);
        return ViHackerResult.success();
    }

    /**
     * 删除文件夹
     * @param folderId
     * @return
     */
    @PostMapping("/delete")
    public@ResponseBody ViHackerResult<Void> delete(long folderId){
        folderService.removeById(folderId);
        return ViHackerResult.success();
    }

}
