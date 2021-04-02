package cn.wilton.framework.file.common.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/2
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_permission")
public class Permission {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String code;

    private String description;

    private String url;

    /**
     * 版本信息
     */
    @Version
    private Long version;

    /**
     * 数据逻辑删除标识字段
     */
    @TableLogic
    private Integer deleted;
}


