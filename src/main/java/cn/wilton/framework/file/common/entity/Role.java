package cn.wilton.framework.file.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.vihackerframework.common.entity.ViHackerEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/2
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_role")
public class Role extends ViHackerEntity {

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String roleName;

    private String description;

    private Integer status;

}

