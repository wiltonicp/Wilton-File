package cn.wilton.framework.file.modules.dto;

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

    private String msgType;

    private String msg;

    private Long userId;

    private String nackName;

    private String avatarUrl;
}
