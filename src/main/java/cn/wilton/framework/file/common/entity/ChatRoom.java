package cn.wilton.framework.file.common.entity;

import cc.vihackerframework.core.entity.ViHackerEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * <p>
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/26
 */
@Data
@TableName("room")
public class ChatRoom extends ViHackerEntity {

    private Long id;

    private String roomName;
}
