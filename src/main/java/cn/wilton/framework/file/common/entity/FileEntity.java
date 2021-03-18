package cn.wilton.framework.file.common.entity;

import cn.wilton.framework.file.common.entity.enums.FileTypeEnum;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/3/16
 */
@Data
public class FileEntity {

    /**
     * 文件id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 文件名称(上传的名称)
     */
    @TableField("file_name")
    private String fileName;

    /**
     * 文件类型
     */
    @TableField("file_type")
    private FileTypeEnum fileType;

    /**
     * 文件大小
     */
    @TableField("file_size")
    private long fileSize;

    /**
     * 存储路径
     */
    @TableField("path")
    private String path;

    /**
     * 是否公开
     */
    @TableField("open")
    private Boolean open;

    /**
     * 上传者
     */
    @TableField(exist = false)
    private Long userId;

    /**
     * 最后更新时间
     */
    @TableField("modify_time")
    private LocalDateTime modifyTime;

}
