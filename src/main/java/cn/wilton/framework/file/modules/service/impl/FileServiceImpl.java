package cn.wilton.framework.file.modules.service.impl;

import cn.wilton.framework.file.common.entity.FileEntity;
import cn.wilton.framework.file.modules.mapper.IFileMapper;
import cn.wilton.framework.file.modules.service.IFileService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/3/16
 */
@Service
public class FileServiceImpl extends ServiceImpl<IFileMapper, FileEntity> implements IFileService {

    @Override
    public List<FileEntity> listPage(Long folderId) {
        return this.list(new QueryWrapper<FileEntity>()
                .eq(folderId != null, "folder_id", folderId)
                .eq("deleted",0)
        );
    }

    @Override
    public FileEntity getByFileMd5(String fileMd5) {
        return this.getOne(new QueryWrapper<FileEntity>()
                .eq(fileMd5 != null, "file_md5", fileMd5)
                .eq("deleted",0)
        );
    }
}
