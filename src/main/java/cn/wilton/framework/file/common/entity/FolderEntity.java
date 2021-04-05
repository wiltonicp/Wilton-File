package cn.wilton.framework.file.common.entity;

import cn.wilton.framework.file.common.util.SecurityUtil;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
public class FolderEntity extends BaseEntity{

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
     * 创建者姓名
     */
    @TableField(exist = false)
    private String createdByName;

    public void created(){
        this.type = 0L;
    }

    public void update(){
        this.setModifyBy(SecurityUtil.getLoginUser().getId());
        this.setModifyTime(LocalDateTime.now());
    }

}
