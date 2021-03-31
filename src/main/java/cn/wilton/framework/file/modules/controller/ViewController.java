package cn.wilton.framework.file.modules.controller;

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

    @GetMapping("/login")
    public String login(Model model){
        return "auth-sign-in";
    }

    @GetMapping("/files")
    public String files(Model model){
        model.addAttribute("userName","Ranger");
        return "page-files";
    }

    @GetMapping("/index")
    public String index(Model model){
        return "index";
    }

}
