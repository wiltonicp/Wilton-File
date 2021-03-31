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

    List<FileEntity> listPage(Long folderId);

    FileEntity getByFileMd5(String fileMd5);

}
