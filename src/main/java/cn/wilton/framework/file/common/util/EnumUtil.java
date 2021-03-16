package cn.wilton.framework.file.common.util;

import cn.wilton.framework.file.common.entity.enums.EnumMessage;

/**
 * 枚举工具类
 * @author Ranger
 * @since 2021/3/16
 * @email wilton.icp@gmail.com
 */
public class EnumUtil {

    public static <T extends EnumMessage> T getEnumByCode(Class<T> clazz, Long code) {
        for(T inner : clazz.getEnumConstants()){
            if(code.equals(inner.getCode())) {
                return inner;
            }
        }
        return null;
    }

}
