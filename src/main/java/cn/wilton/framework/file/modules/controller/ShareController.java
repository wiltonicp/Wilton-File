package cn.wilton.framework.file.modules.controller;

import cn.wilton.framework.file.common.entity.ShareEntity;
import cn.wilton.framework.file.common.entity.User;
import cn.wilton.framework.file.common.util.FileUtil;
import cn.wilton.framework.file.common.util.SecurityUtil;
import cn.wilton.framework.file.modules.service.IShareService;
import cn.wilton.framework.file.modules.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.vihackerframework.common.api.ViHackerResult;
import com.vihackerframework.common.exception.ViHackerException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * <p>
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/14
 */
@Controller
@RequestMapping("/share")
@RequiredArgsConstructor
public class ShareController {

    @Value("${wilton.share.path}")
    private String sharePath;
    private final IUserService userService;
    private final IShareService shareService;

    @GetMapping("list")
    public String listPage(Model model){
        List<ShareEntity> list = shareService.list(new QueryWrapper<ShareEntity>()
                .eq("created_by", SecurityUtil.getLoginUser().getId())
        );
        list.forEach(file ->{
            file.created(sharePath);
        });
        model.addAttribute("shareList",list);
        return "page-share::gridList";
    }

    /**
     * 新增分享
     * @param fileId
     * @return
     */
    @PostMapping("add")
    public String add(Model model,long fileId){
        ShareEntity entity = shareService.add(fileId);
        entity.created(sharePath);
        model.addAttribute("shareEntity",entity);
        return "common/share::toShare";
    }

    /**
     * 修改分享
     * @param input
     * @return
     */
    @PostMapping("update")
    public ViHackerResult<Void> update(ShareEntity input){
        shareService.updateById(input);
        return ViHackerResult.success();
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("delete/{id}")
    public ViHackerResult<Void> deleteById(Long id){
        shareService.removeById(id);
        return ViHackerResult.success();
    }

}
