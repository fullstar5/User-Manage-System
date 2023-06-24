package com.star.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = -8723817965432262146L;

    private String userAccount;
    private String userPassword;
}
