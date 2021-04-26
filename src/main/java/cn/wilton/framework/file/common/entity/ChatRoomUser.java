package cn.wilton.framework.file.common.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * <p>
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/26
 */
@Data
@TableName("room_user")
public class ChatRoomUser {

    private Long roomId;

    private Long userId;
}
