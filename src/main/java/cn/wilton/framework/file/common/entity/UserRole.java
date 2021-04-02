package cn.wilton.framework.file.common.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * <p>
 *
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/2
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_user_role")
public class UserRole {

    private Long userId;

    private Long roleId;

    private LocalDateTime createdTime;

    private String creator;

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

