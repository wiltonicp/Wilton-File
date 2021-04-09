package cn.wilton.framework.file.modules.weixin.service.impl;

import cn.hutool.core.lang.UUID;
import cn.wilton.framework.file.modules.weixin.entity.WxAccessToken;
import cn.wilton.framework.file.modules.weixin.service.WxAccessTokenService;
import cn.wilton.framework.file.modules.weixin.util.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * <p>
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/9
 */
@Service
public class WxAccessTokenServiceImpl implements WxAccessTokenService {

    @Value("${weixin.app.app_id}")
    private String app_id;

    @Value("${weixin.app.app_secret}")
    private String app_secret;

    //private WxAccessTokenRepository wxAccessTokenRepository;

    @Override
    public WxAccessToken saveOrUpdate(WxAccessToken wxAccessToken) throws Exception {
        if(StringUtils.isBlank(wxAccessToken.getId())){
            wxAccessToken.setId(UUID.randomUUID().toString());
        }
        if (null == wxAccessToken.getCreateTime()) {
            wxAccessToken.setCreateTime(LocalDateTime.now());
        }
        //return wxAccessTokenRepository.save(wxAccessToken);
        return wxAccessToken;
    }

    @Override
    public WxAccessToken findAccessToken() throws Exception{
//        List<WxAccessToken> wxAccessTokenList = wxAccessTokenRepository.findAll();
        //WxAccessToken currentToken = wxAccessTokenRepository.findByAppId(this.app_id);
        WxAccessToken currentToken = null;
        if (null == currentToken ) {
            //数据库中没有对应的凭据
            //调用微信接口获得access_token 并存储
            WxAccessToken newToken = new WxAccessToken();
            String accessToken = this.getAccessToken();
            if (StringUtils.isNotBlank(accessToken)) {
                newToken.setAppId(this.app_id);
                newToken.setAccessToken(accessToken);//更新token
                newToken.setCreateTime(LocalDateTime.now());//更新创建时间
                return newToken;
                //return this.saveOrUpdate(newToken);
            }
        }else{
            //数据库中存在凭据，则需要查看凭据的有效时间
//            if ((LocalDateTime.now() - currentToken.getCreateTime()) / (1000 * 60) > 90) {
//                //存储时间超过了90分钟，则需要重新从微信方获得新的凭据
//                String accessToken = this.getAccessToken();
//                if (StringUtils.isNotBlank(accessToken)) {
//                    currentToken.setAccessToken(accessToken);//更新token
//                    currentToken.setCreateTime(new Date().getTime());//更新创建时间
//                    return this.saveOrUpdate(currentToken);
//                }
//            }else{
//                return currentToken;
//            }
        }
        return null;
    }

    @Override
    public String findAccessTokenStr() throws Exception {
        String accessToken = "";
        WxAccessToken wxAccessToken = this.findAccessToken();
        if (null != wxAccessToken) {
            accessToken = wxAccessToken.getAccessToken();
        }
        return accessToken;
    }

    /**
     * 调用微信公众号接口获得公众号接口调用凭据
     * @return 返回凭据
     * @throws Exception
     */
    @Override
    public String getAccessToken() throws Exception {
        //调用微信接口获取access_token ：access_token 的有效期是2个小时，不要重复调用，否则会将之前的老的刷新
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + app_id + "&secret=" + app_secret;
        Map<String, Object> accessTokenMap = HttpUtils.doGet(url);
        if(null != accessTokenMap.get("access_token")){
            return accessTokenMap.get("access_token").toString();
        }else{
            return null;
        }
    }

}
