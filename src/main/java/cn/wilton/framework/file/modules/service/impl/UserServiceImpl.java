package cn.wilton.framework.file.modules.service.impl;

import cn.wilton.framework.file.common.entity.FileEntity;
import cn.wilton.framework.file.common.entity.FreeStorage;
import cn.wilton.framework.file.common.entity.User;
import cn.wilton.framework.file.common.util.FileUtil;
import cn.wilton.framework.file.common.util.SecurityUtil;
import cn.wilton.framework.file.modules.mapper.FileMapper;
import cn.wilton.framework.file.modules.mapper.UserMapper;
import cn.wilton.framework.file.modules.service.IFileService;
import cn.wilton.framework.file.modules.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

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
