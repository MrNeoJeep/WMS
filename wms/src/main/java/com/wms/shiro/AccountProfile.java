package com.wms.shiro;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class AccountProfile implements Serializable {
    private Integer id;

    private String no;

    private String name;

    private Integer age;

    private String phone;

    private Integer roleId;

    private String isValid;
}
