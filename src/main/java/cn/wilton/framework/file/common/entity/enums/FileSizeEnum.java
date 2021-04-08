package cn.wilton.framework.file.common.entity.enums;

import lombok.Getter;

/**
 * <p>
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/8
 */
@Getter
public enum FileSizeEnum implements EnumMessage {

    TYPE_B (1,"B"),
    TYPE_KB (2,"KB"),
    TYPE_MB (3,"MB"),
    TYPE_GB (4,"GB");

    private Integer code;
    private String desc;

    FileSizeEnum (Integer code,String desc){
        this.code = code;
        this.desc = desc;
    }

    @Override
    public Object getCode() {
        return this.code;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }
}
