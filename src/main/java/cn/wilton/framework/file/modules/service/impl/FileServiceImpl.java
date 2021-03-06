package cn.wilton.framework.file.modules.service.impl;

import cn.wilton.framework.file.common.entity.FileEntity;
import cn.wilton.framework.file.common.entity.User;
import cn.wilton.framework.file.common.util.FileUtil;
import cn.wilton.framework.file.common.util.SecurityUtil;
import cn.wilton.framework.file.modules.mapper.FileMapper;
import cn.wilton.framework.file.modules.mapper.UserMapper;
import cn.wilton.framework.file.modules.service.IFileService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/3/16
 */
@Service
@RequiredArgsConstructor
public class FileServiceImpl extends ServiceImpl<FileMapper, FileEntity> implements IFileService {

    private final UserMapper userMapper;

    @Override
    public List<FileEntity> listPage(Long folderId) {
        List<FileEntity> list = this.list(new QueryWrapper<FileEntity>()
                .eq(folderId != null, "folder_id", folderId)
                .eq("deleted", 0)
        );
        list.forEach(file ->{
            User user = userMapper.selectById(file.getCreatedBy());
            file.setCreatedByName(user.getNickName());
            file.setFileSizeVal(FileUtil.getSize(file.getFileSize()));
        });
        return list;
    }

    @Override
    public FileEntity getByFileMd5(String fileMd5) {
        return this.getOne(new QueryWrapper<FileEntity>()
                .eq(fileMd5 != null, "file_md5", fileMd5)
                .eq("deleted",0)
        );
    }

    @Override
    public FileEntity getByFileId(long id) {
        return this.baseMapper.getByFileId(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<FileEntity> deletedList() {
        List<FileEntity> list = this.baseMapper.deletedList(SecurityUtil.getLoginUser().getId());
        list.forEach(file ->{
            file.setFileSizeVal(FileUtil.getSize(file.getFileSize()));
        });
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean restoreById(long id) {
        return this.baseMapper.restoreById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deletePermanentlyById(long id) {
        return this.baseMapper.deletePermanentlyById(id);
    }
}
