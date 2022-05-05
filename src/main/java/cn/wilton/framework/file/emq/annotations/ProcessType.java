package cn.wilton.framework.file.emq.annotations;

import java.lang.annotation.*;

/**
 * 协议处理类型注解
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface ProcessType {
    String value();
}
