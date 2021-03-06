package cn.wilton.framework.file.modules.service.impl;

import cn.wilton.framework.file.common.entity.FolderEntity;
import cn.wilton.framework.file.common.entity.User;
import cn.wilton.framework.file.common.util.SecurityUtil;
import cn.wilton.framework.file.modules.mapper.FolderMapper;
import cn.wilton.framework.file.modules.mapper.UserMapper;
import cn.wilton.framework.file.modules.service.IFolderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/3/27
 */
@Service
@RequiredArgsConstructor
public class FolderServiceImpl extends ServiceImpl<FolderMapper, FolderEntity> implements IFolderService {

    private final UserMapper userMapper;

    @Override
    public List<FolderEntity> list(Long parentId) {
        List<FolderEntity> list = this.list(new QueryWrapper<FolderEntity>()
                .eq(parentId != null, "parent_id", parentId)
        );
        list.forEach(folder ->{
            User user = userMapper.selectById(folder.getCreatedBy());
            folder.setCreatedByName(user.getNickName());
        });
        return list;
    }

    @Override
    public FolderEntity findByUserId(Long userId) {
        return this.getOne(new QueryWrapper<FolderEntity>()
                .eq("created_by", userId)
                .eq("parent_id", 0L)
        );
    }

    @Override
    public List<FolderEntity> findParentById(Long id) {
        return this.baseMapper.findParentById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<FolderEntity> deletedList() {
        return this.baseMapper.deletedList(SecurityUtil.getLoginUser().getId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean restoreById(long id) {
        return this.baseMapper.restoreById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deletePermanentlyById(long id) {
        return this.baseMapper.deletePermanentlyById(id);
    }
}
