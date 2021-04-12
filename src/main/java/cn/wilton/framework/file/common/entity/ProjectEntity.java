package cn.wilton.framework.file.common.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * <p>我的项目
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/11
 */
@Data
@TableName("t_project")
public class ProjectEntity extends BaseEntity{

    private Long id;

    private String projectName;

    /**
     * 对应目录
     */
    private String folderId;

    @TableField(exist = false)
    private String folderName;

    /**
     * 已使用
     */
    private String used;
    
    private Long accessTokenId;

}
