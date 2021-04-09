package cn.wilton.framework.file.modules.weixin.entity;

import lombok.Data;

/**
 * <p>
 *x
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/8
 */
@Data
public class AccessToken {
    private String access_token;
    private String expires_in;
}
