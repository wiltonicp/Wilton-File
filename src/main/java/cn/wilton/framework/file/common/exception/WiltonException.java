package cn.wilton.framework.file.common.exception;

/**
 * 系统通用异常
 * @author Ranger
 * @email wilton.icp@gmail.com
 * @since 2021/3/15
 */
public class WiltonException extends Exception{

    private static final long serialVersionUID = -6916154462432027437L;

    public WiltonException(String message){
        super(message);
    }

}
