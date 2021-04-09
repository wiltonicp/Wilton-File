package cn.wilton.framework.file.modules.weixin.service;

import cn.wilton.framework.file.modules.weixin.entity.WeiXinUserVO;

/**
 * <p>
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/9
 */
public interface WeChatService {

    WeiXinUserVO findWeiXinUserFromWeiXin(String openId, String accessToken) throws Exception;


}
