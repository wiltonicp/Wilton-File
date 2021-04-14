package cn.wilton.framework.file.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * <p>分享
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/12
 */
@Data
@TableName("f_share")
public class ShareEntity extends BaseEntity{

    private Long id;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件大小
     */
    private Long fileSize;

    @TableField(exist = false)
    private String fileSizeVal;

    private String ico;

    /**
     * 文件 id
     */
    private Long fileId;
    /**
     * 是否公开
     */
    private Boolean open;
    /**
     * 分享链接
     */
    private String sharePath;
    /**
     * 取件码
     */
    private String pickupCode;
    /**
     * 状态 0：已失效、1：永久有效、自定义天数
     */
    private String state;
    /**
     * 	浏览次数
     */
    private Long views;
}
