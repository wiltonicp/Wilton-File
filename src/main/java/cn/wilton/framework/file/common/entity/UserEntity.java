package cn.wilton.framework.file.common.entity;

import cn.wilton.framework.file.common.entity.enums.PermissionEnum;

import java.time.LocalDateTime;

/**
 * <p>
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/3/15
 */
public class UserEntity {

    private String username;
    private String password;
    private String realName;
    private String email;
    /**
     * 角色
     */
    private PermissionEnum permission;
    private LocalDateTime createTime;
    private LocalDateTime lastLoginTime;
    /**
     *（全局权限）用户是否可以下载
     */
    private Boolean isDownloadAble;
    /**
     * （全局权限）用户是否可以上传
     */
    private Boolean isUploadAble;
    /**
     * （全局权限）用户是否可以查看文件
     */
    private Boolean isVisible;
    /**
     * （全局权限）用户可以删除文件
     */
    private Boolean isDeletable;
    /**
     * （全局权限）:用户是否可以更新文件
     */
    private Boolean isUpdatable;
}
