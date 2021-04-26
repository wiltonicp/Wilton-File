package cn.wilton.framework.file.modules.dto;

import cn.wilton.framework.file.common.entity.enums.MessageTypeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/26
 */
@Getter
@Setter
public class RoomMessage {

    private MessageTypeEnum msgType;

    private String msg;

    private Long userId;

    private String nackName;

    private String avatarUrl;

    /**
     * 在线人数
     */
    private Integer onlineNumber;
}
