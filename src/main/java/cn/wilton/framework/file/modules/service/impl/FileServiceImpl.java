package cn.wilton.framework.file.modules.service.impl;

import cn.wilton.framework.file.common.entity.FileEntity;
import cn.wilton.framework.file.modules.mapper.IFileMapper;
import cn.wilton.framework.file.modules.service.IFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/3/16
 */
@Service
public class FileServiceImpl extends ServiceImpl<IFileMapper, FileEntity> implements IFileService {

}
