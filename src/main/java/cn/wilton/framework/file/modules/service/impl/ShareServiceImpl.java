package cn.wilton.framework.file.modules.service.impl;

import cn.wilton.framework.file.common.entity.ShareEntity;
import cn.wilton.framework.file.modules.mapper.ShareMapper;
import cn.wilton.framework.file.modules.service.IShareService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/14
 */
@Service
public class ShareServiceImpl extends ServiceImpl<ShareMapper, ShareEntity> implements IShareService {
}
