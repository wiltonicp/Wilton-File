package cn.wilton.framework.file.modules.controller;

import cn.wilton.framework.file.common.api.WiltonResult;
import cn.wilton.framework.file.common.util.SecurityUtil;
import org.springframework.security.core.userdetails.User;
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
    public WiltonResult loginUser(){
        return WiltonResult.data(SecurityUtil.getLoginUser());
    };

    @GetMapping("currentPrincipal")
    public WiltonResult getCurrentPrincipal(){
        return WiltonResult.data(SecurityUtil.getCurrentPrincipal());
    };


}
