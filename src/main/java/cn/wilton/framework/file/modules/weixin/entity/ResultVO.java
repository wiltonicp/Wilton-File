package cn.wilton.framework.file.modules.weixin.entity;

import lombok.Data;

/**
 * <p>
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/4/9
 */
@Data
public class ResultVO<T> {

    private String code;
    private Boolean state;
    private T data;
}
