package cn.wilton.framework.file.modules.service.impl;

import cn.wilton.framework.file.common.entity.*;
import cn.wilton.framework.file.common.service.RedisService;
import cn.wilton.framework.file.common.util.FileUtil;
import cn.wilton.framework.file.modules.mapper.PermissionMapper;
import cn.wilton.framework.file.modules.mapper.RolePermissionMapper;
import cn.wilton.framework.file.modules.mapper.UserMapper;
import cn.wilton.framework.file.modules.mapper.UserRoleMapper;
import cn.wilton.framework.file.properties.WiltonProperties;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/2
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class MyUserDetailsServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;
    private final RedisService redisService;
    private final WiltonProperties properties;
    private final UserRoleMapper userRoleMapper;
    private final PermissionMapper permissionMapper;
    private final RolePermissionMapper rolePermissionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<User> users = userMapper.selectByMap(new HashMap<String, Object>() {{
            put("username", username);
        }});
        if (CollectionUtils.isEmpty(users)) {
            throw new UsernameNotFoundException("账号输入错误!");
        }
        User user = users.get(0);
        List<UserRole> userRoles = userRoleMapper.selectByMap(new HashMap<String, Object>() {{
            put("user_id", user.getId());
        }});
        log.info("userRoles ={}",userRoles);
        if (CollectionUtils.isEmpty(userRoles)) {
            throw new UsernameNotFoundException("该用户尚未分配角色!");
        }
        List<Long> roleIds = userRoles.stream().map(UserRole::getRoleId).distinct().collect(Collectors.toList());
        log.info("roleIds ={}",roleIds);
        List<RolePermission> rolePermissions = rolePermissionMapper.selectList(new QueryWrapper<RolePermission>().in("role_id", roleIds));
        log.info("rolePermissions ={}",rolePermissions);
        if(CollectionUtils.isEmpty(rolePermissions)){
            throw new UsernameNotFoundException("所有均角色尚未绑定权限!");
        }
        List<Long> permissionIds = rolePermissions.stream().map(RolePermission::getPermissionId).distinct().collect(Collectors.toList());
        log.info("permissionIds ={}",permissionIds);
        List<Permission> permissions = permissionMapper.selectBatchIds(permissionIds);
        log.info("permissions ={}",permissions);
        if(CollectionUtils.isEmpty(permissions)){
            throw new UsernameNotFoundException("权限不存在!");
        }
        String permissionStr = permissions.stream().map(Permission::getCode).collect(Collectors.joining(","));
        boolean notLocked = true;
        AdminAuthUser authUser = new AdminAuthUser(user.getUsername(), user.getPassword(), true, true, true, notLocked,
                AuthorityUtils.commaSeparatedStringToAuthorityList(permissionStr));
        BeanUtils.copyProperties(user,authUser);
        /**
         * 初始化当前用户上传路径
         */
        FileUtil.initLoginUserFilePath(properties.path + File.separator + user.getSpaceCode());
        return authUser;
    }
}
