package cn.wilton.framework.file.emq.msghandle;

/**
 * 消息处理上下文
 */
public interface MsgHandlerContext {
    MsgHandler getMsgHandler(String msgType);
}
