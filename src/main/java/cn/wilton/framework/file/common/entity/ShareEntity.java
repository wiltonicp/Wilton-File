package cn.wilton.framework.file.common.entity;

import cn.wilton.framework.file.common.util.FileUtil;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.vihackerframework.common.entity.ViHackerEntity;
import lombok.Data;

/**
 * <p>分享
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/12
 */
@Data
@TableName("f_share")
public class ShareEntity extends ViHackerEntity {

    private Long id;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件大小
     */
    private Long fileSize;

    private String fileType;

    @TableField(exist = false)
    private String fileSizeVal;

    private String ico;

    /**
     * 文件 id
     */
    private Long fileId;
    /**
     * 是否公开
     */
    private Boolean open;
    /**
     * 分享码
     */
    private String shareCode;
    /**
     * 分享链接
     */
    @TableField(exist = false)
    private String sharePath;
    /**
     * 取件码
     */
    private String pickupCode;
    /**
     * 状态 0：已失效、-1：永久有效、自定义天数
     */
    private Integer state;

    @TableField(exist = false)
    private String stateVal;
    /**
     * 	浏览次数
     */
    private Long views;

    @TableField(exist = false)
    private String createdName;

    public void created(FileEntity file){
        this.fileId = file.getId();
        this.fileName = file.getFileName();
        this.fileSize = file.getFileSize();
        this.fileType = file.getFileType();
        this.ico = file.getIco();
    }

    public void created(String sharePath){
        this.fileSizeVal = FileUtil.getSize(this.fileSize);
        this.sharePath = sharePath + this.getShareCode() + "?pickupcode=" + this.pickupCode;
        switch (this.state){
            case -1: this.stateVal = "长期"; break;
            case 1: this.stateVal = "1天"; break;
            case 7: this.stateVal = "7天"; break;
        }
    }
}
