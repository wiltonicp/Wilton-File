package cn.wilton.framework.file.modules.mapper;

import cn.wilton.framework.file.common.entity.FileEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/3/16
 */
@Repository
public interface FileMapper extends BaseMapper<FileEntity>{


    /**
     * 根据 id 查询
     * @param id
     * @return
     */
    @Select("select * from f_file where id = #{id}")
    FileEntity getByFileId(@Param("id") long id);

    /**
     * 查询已经被删除的列表
     * @param createdBy
     * @return
     */
    List<FileEntity> deletedList(long createdBy);

    /**
     * 还原文件
     * @param id
     * @return
     */
    boolean restoreById(long id);

    /**
     * 永久删除
     * @param id
     * @return
     */
    boolean deletePermanentlyById(long id);
}
