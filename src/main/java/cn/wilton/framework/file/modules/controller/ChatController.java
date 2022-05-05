package cn.wilton.framework.file.modules.controller;

import cc.vihackerframework.core.api.ViHackerApiResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created By Ranger on 2022/5/5.
 */
@Controller
@RequestMapping("chat")
public class ChatController {

    @GetMapping("show")
    public ViHackerApiResult show(){
        return ViHackerApiResult.success();
    }

    @GetMapping("lists")
    public ViHackerApiResult list(){
        return ViHackerApiResult.success();
    }
}
