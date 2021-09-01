package cn.wilton.framework.file.modules.controller;

import cc.vihackerframework.core.api.ViHackerResult;
import cn.wilton.framework.file.common.util.SecurityUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/3
 */
@RestController
@RequestMapping("admin")
public class AdminController {

    @GetMapping("loginUser")
    public ViHackerResult loginUser(){
        return ViHackerResult.data(SecurityUtil.getLoginUser());
    };

    @GetMapping("currentPrincipal")
    public ViHackerResult getCurrentPrincipal(){
        return ViHackerResult.data(SecurityUtil.getCurrentPrincipal());
    };


}
