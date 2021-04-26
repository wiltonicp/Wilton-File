package cn.wilton.framework.file.modules.service.impl;

import cn.wilton.framework.file.common.entity.ChatRoom;
import cn.wilton.framework.file.common.entity.ChatRoomUser;
import cn.wilton.framework.file.common.entity.User;
import cn.wilton.framework.file.modules.dto.ChatRoomDto;
import cn.wilton.framework.file.modules.mapper.ChatRoomMapper;
import cn.wilton.framework.file.modules.mapper.ChatRoomUserMapper;
import cn.wilton.framework.file.modules.mapper.UserMapper;
import cn.wilton.framework.file.modules.service.IChatRoomService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/26
 */
@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl extends ServiceImpl<ChatRoomMapper,ChatRoom> implements IChatRoomService {

    private final UserMapper userMapper;
    private final ChatRoomMapper roomMapper;
    private final ChatRoomUserMapper roomUserMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addUserToRoom(ChatRoomUser roomUser) {
        roomUserMapper.insert(roomUser);
        return true;
    }

    @Override
    public ChatRoomDto roomDetailById(long roomId) {
        ChatRoomDto chatRoomDto = new ChatRoomDto();

        ChatRoom chatRoom = roomMapper.selectById(roomId);
        List<ChatRoomUser> roomUsers = roomUserMapper.selectList(new QueryWrapper<ChatRoomUser>()
                .eq("room_id", roomId)
        );
        List<User> userList = new ArrayList<>();
        roomUsers.forEach(roomUser ->{
            userList.add(userMapper.selectById(roomUser.getUserId()));
        });

        chatRoomDto.setRoomId(roomId);
        chatRoomDto.setRoomName(chatRoom.getRoomName());
        chatRoomDto.setUserList(userList);
        return chatRoomDto;
    }
}
