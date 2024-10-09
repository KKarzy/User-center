package org.karzy.usercenter.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author karzy
 */
@Data
public class Rsg<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 9168701895038585344L;
    private Integer code;
    private T data;
    private String msg;
    private String description;
    public Rsg(Integer code, T data, String msg, String description){
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.description = description;
    }
    public Rsg(Integer code, T data){
        this(code, data,"","");
    }
    public Rsg(Integer code, T data, String msg){
        this(code, data,msg,"");
    }

    public Rsg(ErrorCode errorCode) {
        this(errorCode.getCode(),null,errorCode.getMsg(),errorCode.getDescription());
    }
    public static Rsg error(ErrorCode errorCode){
        return new Rsg<>(errorCode);
    }
}
