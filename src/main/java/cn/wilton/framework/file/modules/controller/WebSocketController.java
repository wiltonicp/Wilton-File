package cn.wilton.framework.file.modules.controller;

import cn.wilton.framework.file.common.util.SecurityUtil;
import cn.wilton.framework.file.modules.dto.RoomMessage;
import cn.wilton.framework.file.websocket.WebSocketServer;
import com.alibaba.fastjson.JSONObject;
import cc.vihackerframework.core.api.ViHackerResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试 websocket
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/25
 */
@Controller
@RequestMapping("/chat")
public class WebSocketController {

    /**
     * 页面请求
     * @return
     */
    @GetMapping("/room")
    public ModelAndView socket() {
        ModelAndView mav = new ModelAndView("chat-room");
        mav.addObject("userId", SecurityUtil.getLoginUser().getId());
        mav.addObject("roomId",1L);
        return mav;
    }

    /**
     * 推送数据接口
     * @param message
     * @return
     */
    @ResponseBody
    @RequestMapping("/socket/push/{roomId}")
    public ViHackerResult pushToWeb(@PathVariable String roomId, String message) {
        try {
            RoomMessage roomMessage = new RoomMessage();
            roomMessage.setMsg(message);
            roomMessage.setNackName("系统管理员");
            roomMessage.setUserId(1111111L);
            roomMessage.setAvatarUrl("");
            WebSocketServer.sendInfo(JSONObject.toJSONString(roomMessage), roomId);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ViHackerResult.success();
    }
}
