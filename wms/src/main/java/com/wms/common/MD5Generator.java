package com.wms.common;

import cn.hutool.crypto.SecureUtil;

public class MD5Generator {
    public static void main(String[] args) {
        System.out.println(SecureUtil.md5("123"));
    }
}
