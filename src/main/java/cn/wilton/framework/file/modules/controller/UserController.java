package cn.wilton.framework.file.modules.controller;

import cn.wilton.framework.file.common.api.WiltonResult;
import cn.wilton.framework.file.common.entity.FreeStorage;
import cn.wilton.framework.file.common.util.SecurityUtil;
import cn.wilton.framework.file.modules.service.IUserService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final IUserService userService;

    /**
     * 当前用户存储空间使用情况
     * @return
     */
    @GetMapping("freeStorage")
    private WiltonResult<FreeStorage> getFreeSize(){
        return WiltonResult.data(userService.getUserFreeStorageSize());
    }

    /**
     * 登录用户信息
     * @return
     */
    @GetMapping("loginUser")
    public WiltonResult loginUser(){
        return WiltonResult.data(SecurityUtil.getLoginUser());
    };

}