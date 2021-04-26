package cn.wilton.framework.file.modules.service.impl;

import cn.wilton.framework.file.common.entity.*;
import cn.wilton.framework.file.common.util.FileUtil;
import cn.wilton.framework.file.common.util.SecurityUtil;
import cn.wilton.framework.file.modules.dto.UserInput;
import cn.wilton.framework.file.modules.mapper.FileMapper;
import cn.wilton.framework.file.modules.mapper.FolderMapper;
import cn.wilton.framework.file.modules.mapper.UserMapper;
import cn.wilton.framework.file.modules.mapper.UserRoleMapper;
import cn.wilton.framework.file.modules.service.IChatRoomService;
import cn.wilton.framework.file.modules.service.IUserService;
import cn.wilton.framework.file.properties.WiltonProperties;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vihackerframework.common.exception.ViHackerRuntimeException;
import com.vihackerframework.common.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.Folder;

/**
 * <p>
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/3
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final FileMapper fileMapper;
    private final RedisService redisService;
    private final FolderMapper folderMapper;
    private final WiltonProperties properties;
    private final UserRoleMapper userRoleMapper;
    private final IChatRoomService roomService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getIdByUserName(String username) {
        return this.getOne(new QueryWrapper<User>()
                .eq("username", username)
        );
    }

    @Override
    public FreeStorage getUserFreeStorageSize() {
        long used = fileMapper.getUserFreeStorageSize(SecurityUtil.getLoginUser().getId());
        Long total = SecurityUtil.getLoginUser().getStorageSize() * FileUtil.GB;
        long freeSize = getFreeSize(total, used);
        return new FreeStorage(SecurityUtil.getLoginUser().getStorageSize() + " GB ",
                FileUtil.getSize(used),
                String.valueOf((used / total) * 100) + "%",
                FileUtil.getSize(freeSize));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean addUser(UserInput input) {
        Integer code = (Integer) redisService.get(properties.redisKey + input.getEmail());
        if(!input.getVerifyCode().equals(code)){
            throw new ViHackerRuntimeException("验证码不正确，请重新输入");
        }
        User idByUserName = this.getIdByUserName(input.getUsername());
        if(ObjectUtils.isNotEmpty(idByUserName)){
            throw new ViHackerRuntimeException("此邮箱已经注册，请前去登录");
        }
        User user = new  User();
        BeanUtils.copyProperties(input,user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.created();
        this.save(user);
        /**
         * 配置默认角色
         */
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId(2L);
        userRoleMapper.insert(userRole);
        /**
         * 配置默认文件夹信息
         */
        FolderEntity folderEntity = new FolderEntity();
        folderEntity.setFolderName("根目录");
        folderEntity.setParentId(0L);
        folderEntity.created();
        folderEntity.setModifyBy(user.getId());
        folderEntity.setCreatedBy(user.getId());
        folderMapper.insert(folderEntity);

        /**
         * 添加到默认聊天室
         */
        ChatRoomUser roomUser = new ChatRoomUser();
        roomUser.setRoomId(1L);
        roomUser.setUserId(user.getId());
        roomService.addUserToRoom(roomUser);
        return true;
    }

    /**
     * 剩余空间计算
     * @param total 空间总大小
     * @param used 已经消耗的空间
     * @return
     */
    private long getFreeSize(long total,long used){
        if(total > used){
            return total - used;
        }else{
            return 0;
        }
    }

}
