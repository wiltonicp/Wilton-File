package cn.wilton.framework.file.modules.service.impl;

import cn.wilton.framework.file.common.entity.FileEntity;
import cn.wilton.framework.file.common.entity.ShareEntity;
import cn.wilton.framework.file.common.util.IdUtils;
import cn.wilton.framework.file.modules.mapper.FileMapper;
import cn.wilton.framework.file.modules.mapper.ShareMapper;
import cn.wilton.framework.file.modules.service.IShareService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/14
 */
@Service
@RequiredArgsConstructor
public class ShareServiceImpl extends ServiceImpl<ShareMapper, ShareEntity> implements IShareService {

    private final FileMapper fileMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ShareEntity add(long fileId) {
        FileEntity file = fileMapper.getByFileId(fileId);
        ShareEntity shareEntity = new ShareEntity();
        shareEntity.created(file);
        shareEntity.setShareCode(IdUtils.createdOne(file.getId()));
        shareEntity.setOpen(true);
        shareEntity.setPickupCode(IdUtils.getCode());
        shareEntity.setState(-1);
        this.baseMapper.insert(shareEntity);
        return shareEntity;
    }
}
