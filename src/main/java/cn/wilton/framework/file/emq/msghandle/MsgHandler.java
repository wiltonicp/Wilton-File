package cn.wilton.framework.file.emq.msghandle;

import java.io.IOException;

/**
 * 消息处理接口
 */
public interface MsgHandler{
    void process(String jsonMsg) throws IOException;
}
