package cn.wilton.framework.file.common.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * <p>
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/3/18
 */
public enum PermissionEnum implements EnumMessage{

    NO_LANDING (0,"禁止登陆"),
    SUPER_ADMIN (1,"超级管理员"),
    ADMIN (2,"管理员"),
    USER (3,"普通用户");

    /**
     * 标记数据库存的值是code
     */
    @EnumValue
    private final Integer code;
    @JsonValue
    private final String title;

    PermissionEnum (Integer code,String title){
        this.code = code;
        this.title = title;
    }

    @Override
    public String getCode() {
        return String.valueOf(this.code);
    }

    @Override
    public String getDesc() {
        return this.title;
    }
}
