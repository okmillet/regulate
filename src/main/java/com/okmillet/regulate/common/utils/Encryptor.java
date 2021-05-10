package com.okmillet.regulate.common.utils;

import org.springframework.util.DigestUtils;

public class Encryptor {

    public static String MD5(String str) {
    	if(str == null) {
    		return null;
    	}
        return DigestUtils.md5DigestAsHex(str.getBytes()).toUpperCase();
    }
}