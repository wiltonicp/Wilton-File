package cn.wilton.framework.file.modules.service;

import cc.vihackerframework.core.entity.PageInfo;
import cn.wilton.framework.file.common.entity.ShareEntity;
import cn.wilton.framework.file.modules.dto.ShareQueryInput;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/14
 */
public interface IShareService extends IService<ShareEntity> {

    /**
     * 分页查询
     * @param input
     * @return
     */
    PageInfo<ShareEntity> queryPage(ShareQueryInput input);
    /**
     * 新增分享
     * @param fileId
     * @return
     */
    ShareEntity add(long fileId);

    /**
     * 校验取件码
     * @param shareCode
     * @param pickupCode
     * @return
     */
    ShareEntity verify(String shareCode,String pickupCode);

}
