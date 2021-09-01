package cn.wilton.framework.file.common.entity;

import cc.vihackerframework.core.entity.ViHackerEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * <p>
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/12
 */
@Data
@TableName("t_token")
public class AccessTokenEntity extends ViHackerEntity {

    private Long id;

    private String appId;

    private String accessToken;
}
