package cn.wilton.framework.file.modules.controller;

import cn.wilton.framework.file.common.util.SecurityUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    public String file(Model model,Long fid){
        model.addAttribute("fid",fid);
        model.addAttribute("cid",10010);
        model.addAttribute("userName", SecurityUtil.getLoginUser().getFullName());
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
    @GetMapping("/s/{pickupId}")
    public String pickup(Model model,@PathVariable("pickupId") String pickupId){
        model.addAttribute("cid",10013);
        return "pickup-share";
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
