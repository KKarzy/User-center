package org.karzy.usercenter.model.Request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 *
 * @author karzy
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequest implements Serializable{
    @Serial
    private static final long serialVersionUID = 5171712136957646362L;
    private String userPassword;
    private String userAccount;
}
