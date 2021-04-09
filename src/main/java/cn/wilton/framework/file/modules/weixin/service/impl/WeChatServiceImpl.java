package cn.wilton.framework.file.modules.weixin.service.impl;

import cn.wilton.framework.file.modules.weixin.entity.WeiXinUserVO;
import cn.wilton.framework.file.modules.weixin.service.WeChatService;
import cn.wilton.framework.file.modules.weixin.service.WxAccessTokenService;
import cn.wilton.framework.file.modules.weixin.util.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>微信接口Service实现层
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/9
 */
@Service
public class WeChatServiceImpl implements WeChatService {

    @Autowired
    private WxAccessTokenService wxAccessTokenService;

    @Override
    public WeiXinUserVO findWeiXinUserFromWeiXin(String openId, String accessToken) throws Exception {

        if (StringUtils.isBlank(accessToken)) {
            accessToken = this.wxAccessTokenService.findAccessTokenStr();
            if (StringUtils.isBlank(accessToken)) {
                return null;
            }
        }
        String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=" + accessToken + "&openid=" + openId;
        Map<String, Object> map = HttpUtils.doGet(url);
        //===========================================================================//

        if (null != map && map.size() > 0) {
            if (null != map.get("errcode")) {
                return null;
            }else{
                WeiXinUserVO weiXinUserVO = new WeiXinUserVO();
                weiXinUserVO.setOpenid(map.get("openid") == null? null:map.get("openid").toString());
                weiXinUserVO.setSubscribe(map.get("subscribe") == null? null:map.get("subscribe").toString());
                weiXinUserVO.setNickname(map.get("nickname") == null? null:map.get("nickname").toString());
                weiXinUserVO.setSex(map.get("sex") == null? null: map.get("sex").toString());
                weiXinUserVO.setLanguage(map.get("language") == null? null:map.get("language").toString());
                weiXinUserVO.setCity(map.get("city") == null? null:map.get("city").toString());
                weiXinUserVO.setProvince(map.get("province") == null? null:map.get("province").toString());
                weiXinUserVO.setCountry(map.get("country") == null? null:map.get("country").toString());
                weiXinUserVO.setSubscribeTime(map.get("subscribe_time") == null? null:map.get("subscribe_time").toString());
                weiXinUserVO.setRemark(map.get("remark") == null? null:map.get("remark").toString());
                weiXinUserVO.setSubscribeScene(map.get("subscribe_scene") == null? null:map.get("subscribe_scene").toString());
                weiXinUserVO.setQrScene(map.get("qr_scene") == null? null:map.get("qr_scene").toString());
                weiXinUserVO.setQrSceneStr(map.get("qr_scene_str") == null? null:map.get("qr_scene_str").toString());
                return weiXinUserVO;
            }
        }

        return null;
    }


}
