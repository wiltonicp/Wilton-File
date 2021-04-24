package cn.wilton.framework.file.modules.service;

import cn.wilton.framework.file.common.entity.FolderEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/3/27
 */
public interface IFolderService extends IService<FolderEntity> {

    List<FolderEntity> list(Long parentId);

    /**
     * 查询当前用户的主节点信息
     * @param userId
     * @return
     */
    FolderEntity findByUserId(Long userId);

    /**
     * 根据 子id 查询所有父节点
     * @param id
     * @return
     */
    List<FolderEntity> findParentById(Long id);

    /**
     * 查询已经被删除的列表
     * @return
     */
    List<FolderEntity> deletedList();

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
