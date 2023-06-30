package com.wms.common.dto;

import lombok.Data;

@Data
public class PasswordForm {
    private Integer id;
    private String no;
    private String oldPassword;
    private String password;
    private String password2;
}
