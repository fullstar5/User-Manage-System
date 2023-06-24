package com.star.usercenter.model.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yifei
 * user register request
 * used to receive arguement passed by front end when user register
 */
@Data
public class UserRegisterRequest implements Serializable {


    private static final long serialVersionUID = 7110905951068246434L;

    private String userAccount;
    private String userPassword;
    private String checkPassword;
}
