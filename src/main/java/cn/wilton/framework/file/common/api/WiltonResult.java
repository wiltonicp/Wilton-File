package cn.wilton.framework.file.common.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * 通用api 返回对象
 * @author Ranger
 * @since 2021/3/15
 * @email wilton.icp@gmail.com
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WiltonResult<T> {
    /**
     * 状态码
     */
    private long code;
    /**
     * 提示信息
     */
    private String message;
    /**
     * 数据封装
     */
    private T data;

    protected WiltonResult() {
    }

    protected WiltonResult(long code, String message) {
        this.code = code;
        this.message = message;
    }

    protected WiltonResult(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功
     * @param <T>
     * @return
     */
    public static <T> WiltonResult<T> success() {
        return new WiltonResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage());
    }

    /**
     * 成功
     * @param <T>
     * @return
     */
    public static <T> WiltonResult<T> success(String message) {
        return new WiltonResult<T>(ResultCode.SUCCESS.getCode(), message);
    }

    /**
     * 成功返回结果
     * @param data 获取的数据
     */
    public static <T> WiltonResult<T> data(T data) {
        return new WiltonResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     * @param data 获取的数据
     * @param  message 提示信息
     */
    public static <T> WiltonResult<T> success(T data, String message) {
        return new WiltonResult<T>(ResultCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     */
    public static <T> WiltonResult<T> failed(IErrorCode errorCode) {
        return new WiltonResult<T>(errorCode.getCode(), errorCode.getMessage(), null);
    }

    /**
     * 失败返回结果
     * @param errorCode 错误码
     * @param message 错误信息
     */
    public static <T> WiltonResult<T> failed(IErrorCode errorCode,String message) {
        return new WiltonResult<T>(errorCode.getCode(), message);
    }

    /**
     * 失败返回结果
     * @param message 提示信息
     */
    public static <T> WiltonResult<T> failed(String message) {
        return new WiltonResult<T>(ResultCode.FAILED.getCode(), message);
    }

    /**
     * 失败返回结果
     */
    public static <T> WiltonResult<T> failed() {
        return failed(ResultCode.FAILED);
    }

    /**
     * 参数验证失败返回结果
     */
    public static <T> WiltonResult<T> validateFailed() {
        return failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     * 参数验证失败返回结果
     * @param message 提示信息
     */
    public static <T> WiltonResult<T> validateFailed(String message) {
        return new WiltonResult<T>(ResultCode.VALIDATE_FAILED.getCode(), message);
    }

    /**
     * 未登录返回结果
     */
    public static <T> WiltonResult<T> unauthorized(T data) {
        return new WiltonResult<T>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * 未授权返回结果
     */
    public static <T> WiltonResult<T> forbidden(T data) {
        return new WiltonResult<T>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
    }
}
