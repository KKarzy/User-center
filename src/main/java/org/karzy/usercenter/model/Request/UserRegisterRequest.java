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
public class UserRegisterRequest implements Serializable{
    @Serial
    private static final long serialVersionUID = -1270938713167924786L;
    private String userPassword;
    private String checkPassword;
    private String userAccount;
}
