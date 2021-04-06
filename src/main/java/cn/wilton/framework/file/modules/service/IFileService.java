package cn.wilton.framework.file.modules.service;

import cn.wilton.framework.file.common.entity.FileEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/3/16
 */
public interface IFileService extends IService<FileEntity> {

    /**
     * 分页查询
     * @param folderId
     * @return
     */
    List<FileEntity> listPage(Long folderId);

    /**
     * 根据文件加密查询
     * @param fileMd5
     * @return
     */
    FileEntity getByFileMd5(String fileMd5);

    /**
     * 根据 id 查询
     * @param id
     * @return
     */
    FileEntity getByFileId(long id);

    /**
     * 查询已经被删除的列表
     * @return
     */
    List<FileEntity> deletedList();

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
