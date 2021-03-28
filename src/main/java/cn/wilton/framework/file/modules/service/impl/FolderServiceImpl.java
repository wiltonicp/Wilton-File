package cn.wilton.framework.file.modules.service.impl;

import cn.wilton.framework.file.common.entity.FolderEntity;
import cn.wilton.framework.file.modules.mapper.IFolderMapper;
import cn.wilton.framework.file.modules.service.IFolderService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/3/27
 */
@Service
public class FolderServiceImpl extends ServiceImpl<IFolderMapper, FolderEntity> implements IFolderService {

    @Override
    public List<FolderEntity> list(Long parentId) {
        List<FolderEntity> list = this.list(new QueryWrapper<FolderEntity>()
                .eq(parentId != null, "parent_id", parentId)
        );
        return list;
    }

    @Override
    public List<FolderEntity> findParentById(Long id) {
        return this.baseMapper.findParentById(id);
    }
}
