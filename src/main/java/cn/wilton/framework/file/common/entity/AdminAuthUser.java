package cn.wilton.framework.file.common.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Date;

/**
 * @Description
 * @Author: Ranger
 * @Date: 2021/1/25 15:06
 * @Email: wilton.icp@gmail.com
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class AdminAuthUser extends User {

    private static final long serialVersionUID = -6411066541689297219L;

    private Long id;

    private String nickName;

    private String avatar;

    private String email;

    private String mobile;

    private String sex;

    private String roleId;

    private String roleName;

    private Date lastLoginTime;

    private String description;

    private String status;

    private String spaceCode;

    private Long storageSize;

    public AdminAuthUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public AdminAuthUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
