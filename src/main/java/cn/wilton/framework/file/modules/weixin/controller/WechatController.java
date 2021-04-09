package cn.wilton.framework.file.modules.weixin.controller;

import cn.hutool.core.lang.UUID;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.wilton.framework.file.common.util.DateUtil;
import cn.wilton.framework.file.modules.weixin.entity.AuthProcess;
import cn.wilton.framework.file.modules.weixin.entity.ResultVO;
import cn.wilton.framework.file.modules.weixin.entity.WeiXinUserVO;
import cn.wilton.framework.file.modules.weixin.service.WeChatService;
import cn.wilton.framework.file.modules.weixin.service.WxAccessTokenService;
import cn.wilton.framework.file.modules.weixin.util.CheckoutUtils;
import cn.wilton.framework.file.modules.weixin.util.HttpUtils;
import cn.wilton.framework.file.modules.weixin.util.WeiXinUtils;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.DateUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 *
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/9
 */
@Slf4j
@Controller
@RequestMapping("/wechat")
public class WechatController {

    private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    private static final String TICKET_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=%s";

    private static final String QR_CODE_URL = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=%s";

    private static final String USER_INFO_URL = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=%s&openid=%s&lang=zh_CN";

    @Value("${weixin.app.app_id}")
    private String app_id;

    @Value("${weixin.app.app_secret}")
    private String app_secret;

    @Autowired
    private WxAccessTokenService wxAccessTokenService;
    @Autowired
    private WeChatService weChatService;

    /**
     * 生成带参数的二维码，扫描关注微信公众号，自动登录网站
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/qrCodeUrl")
    @ResponseBody
    public ResultVO wechatMpLogin() throws Exception {


        long start = System.currentTimeMillis();
        System.out.println(start);
        long end = start + 1000 * 60;
        String ss = DateUtil.getDateFormat(new Date(end), "yyyy-MM-dd HH:mm:ss");
        ResultVO resultVO = new ResultVO();
        resultVO.setState(true);
        String access_token = this.wxAccessTokenService.findAccessTokenStr();
        String url = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=" + access_token;
        String scene_str = "file" + UUID.randomUUID().toString();
        String params = "{\"expire_seconds\":1200, \"action_name\":\"QR_STR_SCENE\", \"action_info\":{\"scene\":{\"scene_str\":\"" + scene_str + "\"}}}";
        System.out.println(params);

        String ticketUrl = String.format(TICKET_URL, access_token);
        String ticketJson = HttpUtil.post(ticketUrl, "{\"action_name\": \"QR_LIMIT_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \"test\"}}}");
        JSONObject ticket = JSONUtil.parseObj(ticketJson);
        log.info("【ticket】= {}", ticket);

        Map<String, Object> resultMap = HttpUtils.doPost(url, params, 60000);
        if (resultMap.get("ticket") != null) {
            String qrcodeUrl = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=" + resultMap.get("ticket");
            resultVO.setData(qrcodeUrl);
            resultVO.setCode(scene_str);

            //此处获得二维码之后，将scene_str放在map中，用户存放对应的用户微信id,方便后面查询使用
            //在微信回调函数推送信息后，将用户的微信id存放在此map中对应的value中
            WeiXinUtils.getUserOpenid().put(scene_str, "");
            WeiXinUtils.getUserNickname().put(scene_str,"");
        } else {
            resultVO.setState(false);
        }
        return resultVO;
    }

    // 轮询调用，检测登录状态
    @RequestMapping("/checkLogin")
    @ResponseBody
    public ResultVO wechatMpCheckLogin(HttpServletRequest request) {

        String scene_str = request.getParameter("scene_str");
        ResultVO resultVO = new ResultVO();
        resultVO.setState(true);
        String openId = WeiXinUtils.getUserOpenid().get(scene_str);
        String nickName = WeiXinUtils.getUserNickname().get(scene_str);
        String[] dataArray = {openId,nickName};
        if (StringUtils.isNotBlank(openId)) {
            resultVO.setCode("1");
            resultVO.setData(dataArray);
        } else {
            resultVO.setCode("0");
        }
        return resultVO;
    }

    /**
     * 微信公众号平台接口服务器回调token验证
     *
     * @param request
     * @return
     */
    @RequestMapping("/verify_wx_token")
    @ResponseBody
    public String verifyWXToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("call verify_wx_token success!!!!!!");
        // 微信加密签名
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");


        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (signature != null && echostr != null && CheckoutUtils.checkSignature(signature, timestamp, nonce)) {
            //服务器接口提交时认证，一般就调用一次
            return echostr;
            //WeiXinUtils.responsePrint(response, echostr);
        } else {
            //用户进行关注、取关等事件时的处理逻辑，根据事件类型，与自己的业务逻辑进行挂钩
            return callBackFromEventEncry(request);

        }

    }



    /**
     * 微信用户关注、进入、取消关注公众号时，微信服务器推送消息解读
     * 消息经过加密，处理时需要进行解密；即消息的传递采用密文方式
     * @param request
     * @return
     */
    public String callBackFromEventEncry (HttpServletRequest request) {
        //加密消息处理
        InputStream inputStream = null;
        String encrypt_type =request.getParameter("encrypt_type");//加密类型
        if (StringUtils.isBlank(encrypt_type)|| encrypt_type.equals("raw")) {//不用加密
            // 正常的微信处理流程
        } else {//需走加解密流程
            //解密请求消息体
            try {
                inputStream = request.getInputStream();
                // BufferedReader是包装设计模式，性能更高
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                StringBuffer xmlBuffer = new StringBuffer();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    xmlBuffer.append(line);
                }
                bufferedReader.close();
                inputStream.close();
                String nXmlString = AuthProcess.decryptMsg(request, xmlBuffer.toString());//对xml内容进行解密
                Map<String, String> callbackMap = WeiXinUtils.xmlToMap(nXmlString); //xml转map
                if (null != callbackMap) {
                    String fromUserName = callbackMap.get("FromUserName");//用户微信id

                    /**
                     *  1. 用户未关注时，进行关注后的事件推送的事件类型为 subscribe
                     *  2.用户已关注时的事件推送 事件类型为  SCAN
                     */
                    String event = callbackMap.get("Event");//事件类型
                    String eventKey = callbackMap.get("EventKey");
                    String scene_str = "";
                    if (StringUtils.isNotBlank(event)) {

                        Map<String, Object> wechatUserInfoMap = null;
                        String nickname = "";//用户的昵称
                        try {
                            WeiXinUserVO weiXinUserVO = this.weChatService.findWeiXinUserFromWeiXin(fromUserName, null);
                            if (null != weiXinUserVO) {
                                nickname = weiXinUserVO.getNickname();//获得微信用户昵称
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (event.equals("subscribe")) {
//                    事件KEY值，qrscene_为前缀，后面为二维码的参数值；如qrscene_123
                            scene_str = eventKey.split("qrscene_")[1];//关注时的eventKey是qrscene_为前缀，后面为二维码的参数值
//                    System.out.println("this is subscribe>>>>>>>> the scene_str is " + scene_str);
                            //用户关注公众号
                            if (StringUtils.isNotBlank(scene_str)) {
                                //场景值存在
                                WeiXinUtils.getUserOpenid().put(scene_str, fromUserName);
                                WeiXinUtils.getUserNickname().put(scene_str,nickname);
                            }
                        } else if (event.equals("SCAN")) {
                            //用户之前已经关注过公众号，扫码后直接进入了公众号
                            scene_str = eventKey;//已关注时的eventKey就是二维码参数
//                    System.out.println("this is SCAN>>>>>>>> the scene_str is " + scene_str);
                            WeiXinUtils.getUserOpenid().put(scene_str, fromUserName);
                            WeiXinUtils.getUserNickname().put(scene_str,nickname);

                        } else if (event.equals("unsubscribe")) {
                            //用户取消订阅，不做任何处理
                        }
                        if(StringUtils.isNotBlank(scene_str) && WeiXinUtils.getUserOpenid().containsKey(scene_str)){

                            //针对扫码登录的自动回复
                            String replayContent = "Hi~~\n欢迎您，您已成功登录。\n";//回复消息内容
                            String timestamp = String.valueOf(System.currentTimeMillis()/1000);
                            String replyMsg = "<xml>" +
                                    "<ToUserName><![CDATA["+fromUserName+"]]></ToUserName>" +     //这里是发送给对方的openId
                                    "<FromUserName><![CDATA[xxxxxx]]></FromUserName>" +   //注意这里是开发者的微信号，不是app_id
                                    "<CreateTime>"+Integer.valueOf(timestamp)+"</CreateTime>" +
                                    "<MsgType><![CDATA[text]]></MsgType>" +
                                    "<Content><![CDATA["+replayContent+"]]></Content>" +
                                    "</xml>";
//                            System.out.println("into replyMsg=============="+replyMsg);
                            return   AuthProcess.encryptMsg(request, replyMsg);//将消息加密后进行返回
                        }else{
                            return "success";
                        }
                    }

                }

            } catch (IOException ex) {
                ex.printStackTrace();
                return "";
            } catch (Exception exception) {
                exception.printStackTrace();
                return "";
            }
//            result = AuthProcess.encryptMsg(request, originalResult);
        }


        //通知微信平台处理成功
        return "success";
    }


}
