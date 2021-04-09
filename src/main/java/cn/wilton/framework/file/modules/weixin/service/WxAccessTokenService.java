package cn.wilton.framework.file.modules.weixin.service;

import cn.wilton.framework.file.modules.weixin.entity.WxAccessToken;

/**
 * <p>
 *
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/9
 */
public interface WxAccessTokenService {

    WxAccessToken saveOrUpdate(WxAccessToken wxAccessToken) throws Exception;

    WxAccessToken findAccessToken() throws Exception;

    String findAccessTokenStr() throws Exception;

    String getAccessToken() throws Exception;
}
