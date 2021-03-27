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
}
