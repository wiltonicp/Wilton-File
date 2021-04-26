package cn.wilton.framework.file.modules.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * <p>
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/24
 */
@Getter
@Setter
public class UserInput {

    @NotBlank(message = "邮箱不能为空！")
    private String username;

    @NotBlank(message = "密码不能为空！")
    private String password;

    @NotBlank(message = "昵称不能为空！")
    private String nickName;

    @NotNull(message = "验证码不能为空！")
    private Integer verifyCode;

    private String email;
}
