package org.karzy.usercenter.common;

/**
 * @author karzy
 */
public class RsgUtils {
    /**
     * 成功
     * @param data
     * @return
     * @param <T>
     */
    public static <T> Rsg<T> success(T data){
        return new Rsg<>(0,data,"ok");
    }
    public static <T> Rsg<T> success(){
        return new Rsg<>(0,null,"ok");
    }

    /**
     *失败
     * @param errorCode 错误码
     * @return
     */
    public static Rsg error(ErrorCode errorCode){
        return new Rsg<>(errorCode.getCode(), null, errorCode.getMsg(), errorCode.getDescription());
    }

    public static Rsg error(ErrorCode errorCode, String msg, String description){
        return new Rsg<>(errorCode.getCode(), null, msg, description);
    }
    public static Rsg error(ErrorCode errorCode, String description){
        return new Rsg<>(errorCode.getCode(), null, errorCode.getMsg(), description);
    }
    public static Rsg error(Integer code,String msg, String description){
        return new Rsg<>(code, null, msg, description);
    }
}
