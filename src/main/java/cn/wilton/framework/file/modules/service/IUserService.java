package cn.wilton.framework.file.modules.service;

import cn.wilton.framework.file.common.entity.FreeStorage;
import cn.wilton.framework.file.common.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/3
 */
public interface IUserService extends IService<User> {

    /**
     * 根据用户名获取userId
     * @param username
     * @return
     */
    User getIdByUserName(String username);

    /**
     * 获取当前用户空闲存储空间
     * @return
     */
    FreeStorage getUserFreeStorageSize();

}
