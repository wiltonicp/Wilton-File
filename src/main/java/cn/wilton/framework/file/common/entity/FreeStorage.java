package cn.wilton.framework.file.common.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>存储空间相关
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/8
 */
@Getter
@Setter
@Builder
public class FreeStorage {

    /**
     * 总计空间
     */
    private String total;
    /**
     * 已使用
     */
    private String used;
    /**
     * 已使用占比
     */
    private String usedBl;
    /**
     * 剩余可用
     */
    private String free;

    public FreeStorage(String total, String used, String usedBl, String free) {
        this.total = total;
        this.used = used;
        this.usedBl = usedBl;
        this.free = free;
    }
}
