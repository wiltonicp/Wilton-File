package cn.wilton.framework.file.common.util;

/**
 * 打码加密工具
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/17
 */
public class MaskUtil {

    /**
     * 获取用户真实名字的隐藏字符串，只显示姓氏
     * @param realName 真实名字
     * @return
     */
    public static String getAnonymousRealName(String realName) {
        if (hasLength(realName)) {
            int len = realName.length();
            String replace = "";
            replace += realName.charAt(0);
            for (int i = 1; i < len; i++) {
                replace += "*";
            }
            return replace;
        }
        return "";
    }

    /**
     * 判断字符串是否有长度
     * @param realName
     * @return
     */
    private static boolean hasLength(String realName) {
        return realName != null && realName.length()>0;
    }

    /**
     * 获取用户身份号码的隐藏字符串
     *
     * @param idNumber
     * @return
     */
    public static String getAnonymousIdNumber(String idNumber) {
        if (hasLength(idNumber)) {
            int len = idNumber.length();
            String replace = "";
            for (int i = 0; i < len; i++) {
                if ((i > 5 && i < 10) || (i > len - 5)) {
                    replace += "*";
                } else {
                    replace += idNumber.charAt(i);
                }
            }
            return replace;
        }
        return "";
    }
}
