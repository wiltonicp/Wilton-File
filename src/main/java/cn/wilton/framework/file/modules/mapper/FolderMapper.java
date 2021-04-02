package cn.wilton.framework.file.modules.mapper;

import cn.wilton.framework.file.common.entity.FolderEntity;
import com.baomidou.mybatisplus.annotation.SqlParser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/3/27
 */
@Repository
public interface FolderMapper extends BaseMapper<FolderEntity> {

    /**
     * 根据子id查询所有父节点
     * @param id
     * @return
     */
    @SqlParser(filter = true)
    List<FolderEntity> findParentById(Long id);
}
