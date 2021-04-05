package cn.wilton.framework.file.modules.controller;

import cn.wilton.framework.file.common.util.SecurityUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p> 页面控制器
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/3/27
 */
@Controller
@RequestMapping
public class ViewController {

    @GetMapping("/toLogin")
    public String login(){
        return "auth-sign-in";
    }

    @GetMapping("/")
    public String files(Model model,Long fid){
        model.addAttribute("fid",fid);
        model.addAttribute("userName", SecurityUtil.getLoginUser().getFullName());
        return "page-files";
    }

    /**
     * 分享
     * @param model
     * @return
     */
    @GetMapping("/share")
    public String share(Model model){
        return "page-share";
    }

    /**
     * 回收站
     * @return
     */
    @GetMapping("/recycle")
    public String recycle(){
        return "page-recycle";
    }
}
