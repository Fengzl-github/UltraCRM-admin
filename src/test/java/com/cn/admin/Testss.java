package com.cn.admin;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 *@Author fengzhilong
 *@Date 2021/5/17 15:02
 *@Desc
 **/
public class Testss {

    public static void main(String[] args) throws UnsupportedEncodingException {

        String str = "%7B%22address%22%3A%22%E5%8C%97%E4%BA%AC%E5%B8%82%E9%80%9A%E5%B7%9E%E5%8C%BA%E9%A9%AC%E9%A9%B9%E6%A1%A5%E6%B6%9F%E6%B0%B4%E6%80%A1%E5%9B%AD%22%2C%22age%22%3A23%2C%22createTime%22%3A%222021-05-14%2011%3A05%3A34%22%2C%22ghid%22%3A%228600%22%2C%22id%22%3A1%2C%22mobile%22%3A%2218233521351%22%2C%22name%22%3A%22%E5%86%AF%E5%BF%97%E9%BE%99%22%2C%22nickName%22%3A%22%26%22%2C%22password%22%3A%221234%22%2C%22sex%22%3A0%2C%22userRole%22%3A0%7D";



        String decode = URLDecoder.decode(str, "utf-8");

        System.out.println(decode);
    }
}
