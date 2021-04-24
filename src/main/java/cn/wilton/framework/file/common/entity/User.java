package cn.wilton.framework.file.common.entity;

import cn.wilton.framework.file.common.util.IdUtils;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.vihackerframework.common.entity.ViHackerEntity;
import lombok.Data;

/**
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/2
 */
@Data
@TableName("t_user")
public class User extends ViHackerEntity {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String username;

    private String password;

    @TableField("full_name")
    private String fullName;

    private String mobile;

    private String email;

    /**
     * 存储空间id
     */
    private String spaceCode;

    /**
     * 存储空间大小 单位 GB
     */
    private Long storageSize;

    public void created(){
        this.spaceCode = IdUtils.simpleUUID();
        this.storageSize = 10L;
    }
}

