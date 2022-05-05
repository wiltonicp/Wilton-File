package cn.wilton.framework.file.emq.service.impl;


import cn.wilton.framework.file.emq.msghandle.MsgHandler;
import cn.wilton.framework.file.emq.service.MqttService;
import cn.wilton.framework.file.emq.util.JsonUtil;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.io.IOException;
import java.util.Map;


@Slf4j
@RequiredArgsConstructor
public class MqttServiceImpl implements MqttService {

//   @Autowired
//    private final  MsgHandlerContextImp msgHandlerContext;

    private Map<String, MsgHandler> handlerMap = Maps.newHashMap();



    /**
     * mqtt消息处理
     * @param topic
     * @param message
     */
    @Override
    public void processMessage(String topic, MqttMessage message) {
        String msgContent = new String(message.getPayload());
        log.info("接收到消息:"+msgContent);
        try {
            String msgType = JsonUtil.getValueByNodeName("msgType",msgContent);
            if(Strings.isNullOrEmpty(msgType)) return;
            MsgHandler msgHandler = getMsgHandler(msgType);
            if(msgHandler == null)return;
            msgHandler.process(msgContent);
        } catch (IOException e) {
            log.error("process msg error,msg is: "+msgContent,e);
        }
    }


    public MsgHandler getMsgHandler(String msgType){
        return handlerMap.get(msgType);
    }
}
