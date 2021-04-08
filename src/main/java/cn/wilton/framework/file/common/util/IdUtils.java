package cn.wilton.framework.file.common.util;

import cn.hutool.core.util.IdUtil;
import java.time.LocalDateTime;

/**
 * <p>
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/3/31
 */
public class IdUtils extends IdUtil{

    /**
     * 生成 id
     * @return
     */
    public static String getId(){
        String uuid = IdUtil.simpleUUID();
        String nowStr = "-" + DateUtil.formatFullTime(LocalDateTime.now(), DateUtil.FULLS_TIME_PATTERN);
        return uuid + nowStr + "-";
    }

    public static void main(String[] args) {
        System.out.println(fastUUID());
    }

}
