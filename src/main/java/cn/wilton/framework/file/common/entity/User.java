package cn.wilton.framework.file.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/2
 */
@Data
@TableName("t_user")
public class User extends BaseEntity{

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String username;

    private String password;

    @TableField("full_name")
    private String fullName;

    private String mobile;
}

