package cn.wilton.framework.file.modules.service.impl;

import cn.wilton.framework.file.common.entity.User;
import cn.wilton.framework.file.common.util.SecurityUtil;
import cn.wilton.framework.file.modules.mapper.UserMapper;
import cn.wilton.framework.file.modules.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/3
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public User getIdByUserName(String username) {
        return this.getOne(new QueryWrapper<User>()
                .eq("username", username)
        );
    }

    @Override
    public User getLoginUser() {
        return this.getIdByUserName(SecurityUtil.getLoginUser().getUsername());
    }
}
