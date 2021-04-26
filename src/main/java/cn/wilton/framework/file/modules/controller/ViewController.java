package cn.wilton.framework.file.modules.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.wilton.framework.file.common.entity.FolderEntity;
import cn.wilton.framework.file.common.entity.ShareEntity;
import cn.wilton.framework.file.common.entity.User;
import cn.wilton.framework.file.common.util.FileUtil;
import cn.wilton.framework.file.common.util.MaskUtil;
import cn.wilton.framework.file.common.util.SecurityUtil;
import cn.wilton.framework.file.modules.service.IFolderService;
import cn.wilton.framework.file.modules.service.IShareService;
import cn.wilton.framework.file.modules.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.QueryChainWrapper;
import com.vihackerframework.common.exception.ViHackerException;
import com.vihackerframework.common.exception.ViHackerRuntimeException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.NotBlank;

/**
 * <p> 页面控制器
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/3/27
 */
@Controller
@RequestMapping
@RequiredArgsConstructor
public class ViewController {

    private final IUserService userService;
    private final IShareService shareService;
    private final IFolderService folderService;

    @GetMapping("/toLogin")
    public String login(){
        return "auth-sign-in";
    }

    @GetMapping("/toRegister")
    public String register(){
        return "auth-sign-up";
    }

    @GetMapping("/file")
    public String file(Model model,Long fid){
        if(ObjectUtils.isEmpty(fid)){
            FolderEntity byUserId = folderService.findByUserId(SecurityUtil.getLoginUser().getId());
            fid = byUserId.getId();
        }
        model.addAttribute("fid",fid);
        model.addAttribute("cid",10010);
        model.addAttribute("userName", SecurityUtil.getLoginUser().getNickName());
        return "page-files";
    }

    /**
     * 我的项目
     * @param model
     * @return
     */
    @GetMapping("/project")
    public String project(Model model){
        model.addAttribute("cid",10011);
        return "pages-maintenance";
    }

    /**
     * 分享
     * @param model
     * @return
     */
    @GetMapping("/share")
    public String share(Model model) {
        model.addAttribute("cid",10012);
        return "page-share";
    }

    /**
     * 取货页面
     * @return
     */
    @GetMapping("/s/{sharecode}")
    public String pickup(Model model,@PathVariable("sharecode") @NotBlank() String sharecode,
                         String pickupcode){
        ShareEntity share = shareService.getOne(new QueryWrapper<ShareEntity>()
                .eq("share_code", sharecode)
        );
        if(ObjectUtils.isEmpty(share)){
            return "common/error/404";
        }

        if(StringUtils.isBlank(pickupcode)){
            User user = userService.getById(share.getCreatedBy());
            model.addAttribute("pickupId",sharecode);
            model.addAttribute("userName", MaskUtil.getAnonymousRealName(user.getNickName()));
            return "pickup-share";
        }else {
            ShareEntity one = shareService.getOne(new QueryWrapper<ShareEntity>()
                    .eq("share_code", sharecode)
                    .eq("pickup_code", pickupcode)
            );
            if(ObjectUtils.isEmpty(one)){
                return "common/error/404";
            }
            one.setFileSizeVal(FileUtil.getSize(one.getFileSize()));
            model.addAttribute("share",one);
            return "pickup-download";
        }
    }

    /**
     * 资源中心
     * @return
     */
    @GetMapping("/")
    public String resources(Model model){
        model.addAttribute("cid",10015);
        return "resources-center";
    }

    /**
     * 回收站
     * @return
     */
    @GetMapping("/recycle")
    public String recycle(Model model){
        model.addAttribute("cid",10013);
        return "page-recycle";
    }
}
