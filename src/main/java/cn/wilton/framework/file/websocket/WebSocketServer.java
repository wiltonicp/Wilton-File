package cn.wilton.framework.file.websocket;

import cn.wilton.framework.file.common.entity.User;
import cn.wilton.framework.file.common.entity.enums.MessageTypeEnum;
import cn.wilton.framework.file.modules.dto.ChatRoomDto;
import cn.wilton.framework.file.modules.dto.RoomMessage;
import cn.wilton.framework.file.modules.service.IUserService;
import cn.wilton.framework.file.modules.service.impl.UserServiceImpl;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vihackerframework.common.api.ViHackerResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * <p> webSocket服务端
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/25
 */
@Slf4j
@Component
@ServerEndpoint("/api/websocket/{roomId}/{userId}")
public class WebSocketServer {

    /**
     * 此处是解决无法注入的关键
     */
    private static ApplicationContext applicationContext;

    // TODO  你要注入的service或者dao

    private IUserService userService;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        WebSocketServer.applicationContext = applicationContext;
    }

    /**
     * 当前在线连接数
     */
    private static int onlineCount = 0;
    /**
     * 房间所有用户
     * Map<用户 id, 在线用户>
     */
    private static Map<String, User> onlineUserMap = new ConcurrentHashMap<String, User>();

    /**
     * 存放每个客户端对应的MyWebSocket对象
     */
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();


    private Session session;

    /**
     * 当前用户
     */
    private Long currentUserId;

    /**
     * 接收聊天室id
     */
    private String roomId = "";

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("roomId") String roomId,@PathParam("userId") String userId) {
        this.currentUserId = Long.valueOf(userId);
        this.session = session;
        // TODO 加入set中
        webSocketSet.add(this);
        this.roomId = roomId;
        // TODO 在线数加1
        addOnlineCount();
        try {
            userService = applicationContext.getBean(UserServiceImpl.class);
            User user = userService.getById(userId);
            onlineUserMap.put(user.getId().toString(),user);
            RoomMessage roomMessage = new RoomMessage();
            roomMessage.setMsg(user.getNickName() + " 上线了");
            roomMessage.setOnlineNumber(getOnlineCount());
            roomMessage.setMsgType(MessageTypeEnum.CONNECTION);
            sendInfo(JSON.toJSONString(roomMessage),roomId);
            log.info("有新窗口开始监听:" + roomId + ",当前在线人数为:" + getOnlineCount());
        } catch (IOException e) {
            log.error("websocket IO Exception");
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        // TODO 从set中删除
        webSocketSet.remove(this);
        // TODO 在线数减1
        subOnlineCount();
        try {
            User user = onlineUserMap.get(this.currentUserId);
            RoomMessage roomMessage = new RoomMessage();
            roomMessage.setMsg(user.getNickName() + " 离线了");
            roomMessage.setOnlineNumber(getOnlineCount());
            roomMessage.setMsgType(MessageTypeEnum.CONNECTION);
            sendInfo(JSON.toJSONString(roomMessage),roomId);
        } catch (IOException e) {
            log.error("websocket IO Exception");
        }
        log.info("释放的roomId为："+roomId);
        log.info("有一连接关闭！当前在线人数为" + getOnlineCount());
    }

    /**
     * 收到客户端消息后调用的方法
     * @ Param message 客户端发送过来的消息
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        log.info("收到来自窗口" + roomId + "的信息:" + message);
        JSONObject parse = JSONObject.parseObject(message);

        /**
         * 查询发送消息者的信息
         */
        User user = onlineUserMap.get(parse.getString("uid"));
        /**
         * 组装消息体
         */
        RoomMessage roomMessage = new RoomMessage();
        roomMessage.setMsgType(MessageTypeEnum.NEWS);
        roomMessage.setMsg(parse.getString("msg"));
        roomMessage.setNackName(user.getNickName());
        roomMessage.setUserId(user.getId());

        // TODO 群发消息
        for (WebSocketServer item : webSocketSet) {
            try { // TODO 自己不推送
                if(!item.currentUserId.equals(parse.getLongValue("uid"))){
                    item.sendMessage(JSON.toJSONString(roomMessage));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @ Param session
     * @ Param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        log.error("发生错误");
        error.printStackTrace();
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 群发自定义消息
     */
    public static void sendInfo(String message, @PathParam("roomId") String roomId) throws IOException {
        log.info("推送消息到窗口" + roomId + "，推送内容:" + message);

        for (WebSocketServer item : webSocketSet) {
            try {
                // TODO 为null则全部推送
                if (roomId == null) {
                } else if (item.roomId.equals(roomId)) {
                    item.sendMessage(message);
                }
            } catch (IOException e) {
                continue;
            }
        }
    }

    public static synchronized int getOnlineCount() {
        return onlineCount;
    }

    public static synchronized void addOnlineCount() {
        WebSocketServer.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        WebSocketServer.onlineCount--;
    }

    public static CopyOnWriteArraySet<WebSocketServer> getWebSocketSet() {
        return webSocketSet;
    }
}
