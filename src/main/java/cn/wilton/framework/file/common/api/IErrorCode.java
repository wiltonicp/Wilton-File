package cn.wilton.framework.file.common.api;

/**
 * 封装API的错误码
 * @author Ranger
 * @since 2021/3/15
 * @email wilton.icp@gmail.com
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}
