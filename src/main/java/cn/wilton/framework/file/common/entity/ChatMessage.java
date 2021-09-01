package cn.wilton.framework.file.common.entity;

import cc.vihackerframework.core.entity.ViHackerEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * <p>
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/25
 */
@Data
@TableName("msg")
public class ChatMessage extends ViHackerEntity {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long roomId;

    private String content;
}
