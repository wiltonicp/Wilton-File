package cn.wilton.framework.file.modules.dto;

import cn.wilton.framework.file.common.entity.User;
import lombok.Data;

import java.util.List;

/**
 * 聊天室信息
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/26
 */
@Data
public class ChatRoomDto {

    private Long roomId;

    private String roomName;

    private List<User> userList;
}
