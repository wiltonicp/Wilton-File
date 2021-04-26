package cn.wilton.framework.file.modules.service;

import cn.wilton.framework.file.common.entity.ChatRoom;
import cn.wilton.framework.file.common.entity.ChatRoomUser;
import cn.wilton.framework.file.modules.dto.ChatRoomDto;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/26
 */
public interface IChatRoomService extends IService<ChatRoom> {

    /**
     * 新增用户到聊天室
     * @param roomUser
     * @return
     */
    boolean addUserToRoom(ChatRoomUser roomUser);

    /**
     * 查询聊天室详情
     * @param roomId
     * @return
     */
    ChatRoomDto roomDetailById(long roomId);
}
