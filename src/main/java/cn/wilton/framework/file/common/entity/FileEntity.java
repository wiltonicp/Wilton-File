package cn.wilton.framework.file.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/3/16
 */
@Data
@TableName("f_file")
public class FileEntity extends BaseEntity{

    /**
     * 文件id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 目录 id
     */
    @TableField("folder_id")
    private Long folderId;

    /**
     * 文件名称(上传的名称)
     */
    @TableField("file_name")
    private String fileName;

    /**
     * 文件类型
     */
    @TableField("file_type")
    private String fileType;

    /**
     * 文件大小
     */
    @TableField("file_size")
    private Long fileSize;

    /**
     * 文件大小单位转换
     */
    @TableField(exist = false)
    private String fileSizeVal;

    /**
     * 文件唯一指纹
     */
    @TableField("file_md5")
    private String fileMd5;

    /**
     * 存储路径
     */
    @TableField("path")
    private String path;

    /**
     * 文件后缀
     */
    @TableField("ico")
    private String ico;

    /**
     * 预览地址
     */
    @TableField("thumb")
    private String thumb;

    /**
     * 是否公开
     */
    @TableField("open")
    private Boolean open;
}
