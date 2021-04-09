package cn.wilton.framework.file.modules.weixin.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/9
 */
@Data
public class WxAccessToken {

    private String id;
    private String accessToken;
    private String appId;
    private LocalDateTime createTime;
}
