package cn.wilton.framework.file.emq.msghandle.msgHandler;


import cn.wilton.framework.file.emq.annotations.ProcessType;
import cn.wilton.framework.file.emq.msghandle.MsgHandler;
import cn.wilton.framework.file.emq.msghandle.MsgHandlerContext;
import com.google.common.collect.Maps;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

public class MsgHandlerContextImp implements ApplicationContextAware, MsgHandlerContext {
    private ApplicationContext ctx;
    private Map<String, MsgHandler> handlerMap = Maps.newHashMap();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ctx = applicationContext;
        Map<String, MsgHandler> map = ctx.getBeansOfType(MsgHandler.class);
        map.values().stream().forEach(v->{
            String msgType = v.getClass().getAnnotation(ProcessType.class).value();
            handlerMap.put(msgType,v);
        });
    }

    public MsgHandler getMsgHandler(String msgType){
        return handlerMap.get(msgType);
    }
}
