package cn.wilton.framework.file.modules.controller;

import cn.wilton.framework.file.common.entity.FreeStorage;
import cn.wilton.framework.file.common.entity.User;
import cn.wilton.framework.file.common.util.SecurityUtil;
import cn.wilton.framework.file.modules.dto.UserInput;
import cn.wilton.framework.file.modules.service.IUserService;
import com.vihackerframework.common.api.ViHackerResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.autoconfigure.security.saml2.Saml2RelyingPartyProperties.Identityprovider.Verification;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
     * 注册用户
     * @return
     */
    @PostMapping("register")
    public ViHackerResult add(@Validated UserInput input){
        userService.addUser(input);
        return ViHackerResult.success();
    }

    /**
     * 当前用户存储空间使用情况
     * @return
     */
    @GetMapping("freeStorage")
    private ViHackerResult<FreeStorage> getFreeSize(){
        return ViHackerResult.data(userService.getUserFreeStorageSize());
    }

    /**
     * 登录用户信息
     * @return
     */
    @GetMapping("loginUser")
    public ViHackerResult loginUser(){
        return ViHackerResult.data(SecurityUtil.getLoginUser());
    };

}
