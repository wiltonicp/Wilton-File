package cn.wilton.framework.file.modules.service.impl;

import cn.wilton.framework.file.common.entity.FileEntity;
import cn.wilton.framework.file.common.entity.ShareEntity;
import cn.wilton.framework.file.common.entity.User;
import cn.wilton.framework.file.common.util.FileUtil;
import cn.wilton.framework.file.common.util.IdUtils;
import cn.wilton.framework.file.common.util.MaskUtil;
import cn.wilton.framework.file.modules.dto.ShareQueryInput;
import cn.wilton.framework.file.modules.mapper.FileMapper;
import cn.wilton.framework.file.modules.mapper.ShareMapper;
import cn.wilton.framework.file.modules.mapper.UserMapper;
import cn.wilton.framework.file.modules.service.IShareService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.vihackerframework.common.constant.ViHackerConstant;
import com.vihackerframework.common.core.entity.PageInfo;
import com.vihackerframework.common.exception.ViHackerRuntimeException;
import com.vihackerframework.common.util.SortUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
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
    private final UserMapper userMapper;

    @Override
    public PageInfo<ShareEntity> queryPage(ShareQueryInput input) {
        Page<ShareEntity> page = new Page<>(input.getPageNum(), input.getPageSize());
        SortUtil.handlePageSort(input, page, "id", ViHackerConstant.ORDER_DESC, true);
        IPage<ShareEntity> shareList = this.baseMapper.selectPage(page, new QueryWrapper<ShareEntity>()
        .eq("open","1"));
        shareList.getRecords().forEach(share ->{
            User user = userMapper.selectById(share.getCreatedBy());
            share.setCreatedName(MaskUtil.getAnonymousRealName(user.getFullName()));
            share.setFileSizeVal(FileUtil.getSize(share.getFileSize()));
        });
        return PageInfo.of(shareList,ShareEntity.class);
    }

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

    @Override
    public ShareEntity verify(String shareCode, String pickupCode) {
        ShareEntity share = this.baseMapper.selectOne(new QueryWrapper<ShareEntity>()
                .eq("share_code", shareCode)
                .eq("pickup_code",pickupCode)
        );
        if(ObjectUtils.isEmpty(share)){
            throw new ViHackerRuntimeException("取件码错误");
        }
        return share;
    }
}
