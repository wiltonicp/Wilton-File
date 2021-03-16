package cn.wilton.framework.file.common.entity.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 文件类型
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/3/16
 */
public enum FileTypeEnum implements EnumMessage{
    IMAGE ("image","图片"),
    TXT ("txt","文档"),
    MUSIC ("music","音乐"),
    VIDEO ("video","视频"),
    OTHER ("other","其他");

    /**
     * 标记数据库存的值是code
     */
    @EnumValue
    private final String code;
    @JsonValue
    private final String title;

    FileTypeEnum (String code,String title){
        this.code = code;
        this.title = title;
    }

    @Override
    public String getCode() {
        return null;
    }

    @Override
    public String getDesc() {
        return null;
    }
}

