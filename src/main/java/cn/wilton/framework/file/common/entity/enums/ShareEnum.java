package cn.wilton.framework.file.common.entity.enums;

/**
 * <p>
 *
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/14
 */
public enum ShareEnum implements EnumMessage{
    EXPIRED (0L,"已失效"),
    PERMANENT (1L,"永久有效"),
    DAY (2L,"天");

    private Long code;
    private String desc;

    ShareEnum(long code, String desc) {
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
