package cn.wilton.framework.file.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * <p>
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/25
 */
@Data
@TableName("msg_log")
public class ChatMessageLog {

    private Long id;

    private Long msgId;

    private Long roomId;

    @TableField("from_uid")
    private Long fromUId;

    @TableField("to_uid")
    private Long toUId;

    @TableField("is_read")
    private Boolean isRead;
}
