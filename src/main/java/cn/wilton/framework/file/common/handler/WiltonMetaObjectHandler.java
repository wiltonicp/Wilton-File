package cn.wilton.framework.file.common.handler;

import cn.wilton.framework.file.common.entity.User;
import cn.wilton.framework.file.modules.service.IUserService;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/1
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class WiltonMetaObjectHandler implements MetaObjectHandler {

    private final IUserService userService;

    @Override
    public void insertFill(MetaObject metaObject) {
        User user = userService.getLoginUser();
        setFiledVal("version", 0L, metaObject);
        setFiledVal("deleted", 0, metaObject);
        setFiledVal("createdBy", user.getId(), metaObject);
        setFiledVal("modifyBy", user.getId(), metaObject);
        setFiledVal("createdTime", LocalDateTime.now(), metaObject);
        setFiledVal("modifyTime", LocalDateTime.now(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        User user = userService.getLoginUser();
        setFiledVal("modifyBy", user.getId(), metaObject);
        setFiledVal("modifyTime", LocalDateTime.now(), metaObject);
    }

    public void setFiledVal(String fileKey, Object fileVal, boolean ignoreExist, MetaObject metaObject) {
        boolean hasLastModifiedMeta = metaObject.hasGetter(fileKey);
        if (hasLastModifiedMeta) {
            Object oldVal = getFieldValByName(fileKey, metaObject);
            if (oldVal == null) {
                setFieldValByName(fileKey, fileVal, metaObject);
            } else {
                if (!ignoreExist) {
                    setFieldValByName(fileKey, fileVal, metaObject);
                }
            }
        }
    }

    /**
     * 设置数据值
     * @param fileKey
     * @param fileVal
     * @param metaObject
     */
    public void setFiledVal(String fileKey, Object fileVal, MetaObject metaObject) {
        this.setFiledVal(fileKey, fileVal, true, metaObject);
    }
}
