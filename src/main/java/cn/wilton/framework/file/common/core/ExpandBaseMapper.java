package cn.wilton.framework.file.common.core;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Collection;

/**
 * <p>
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/14
 */
public interface ExpandBaseMapper<T> extends BaseMapper<T> {

    /**
     * 批量插入 仅适用于mysql
     * @param entityList 实体列表
     * @return 影响行数
     */
    Integer insertBatchSomeColumn(Collection<T> entityList);
}
