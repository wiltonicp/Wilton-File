package cn.wilton.framework.file.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * <p>文件层级
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/3/27
 */
@Data
@TableName("f_folder")
public class FolderEntity {

    /**
     * 文件id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 文件夹名称
     */
    @TableField("folder_name")
    @NotBlank(message = "文件夹名称不能为空!")
    private String folderName;

    /**
     * 父id
     */
    @TableField("parent_id")
    @NotNull(message = "上级目录id不能为空")
    private Long parentId;

    /**
     * 类型
     */
    @TableField("type")
    private Long type;

    /**
     * 创建者
     */
    @TableField("created_by")
    private Long createdBy;

    /**
     * 最后更新时间
     */
    @TableField("modify_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone="GMT+8")
    private LocalDateTime modifyTime;

    public void created(){
        this.createdBy = 1L;
        this.type = 0L;
        this.modifyTime = LocalDateTime.now();
    }

    public void update(){
        this.createdBy = 1L;
        this.modifyTime = LocalDateTime.now();
    }

}
