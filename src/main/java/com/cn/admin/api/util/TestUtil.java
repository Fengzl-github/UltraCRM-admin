package com.cn.admin.api.util;

import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import com.cn.admin.api.gg.dto.UserDTO;

import java.security.SecureRandom;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.aventrix.jnanoid.jnanoid.NanoIdUtils.DEFAULT_NUMBER_GENERATOR;

/**
 * @Author fengzl
 * @Date 2022/2/910:15
 * @Desc
 */
public class TestUtil {


    public static void main(String[] args) {

        /*List<UserDTO> list = new ArrayList<>();
        UserDTO userDTO = new UserDTO();
        userDTO.setId(1);
        userDTO.setName("张三");
        list.add(userDTO);
        userDTO = new UserDTO();
        userDTO.setId(2);
        userDTO.setName("李四");
        list.add(userDTO);
        list.forEach(user -> {
            if (user.getId() == 1) {
                System.out.println("id ==== true");
            } else {
                System.out.println("id ==== false");
            }
        });

        List<UserDTO> list1 = list.stream().filter(user -> "李四".equals(user.getName())).collect(Collectors.toList());
        list1.forEach(System.out::println);

        System.out.println(NanoIdUtils.randomNanoId());
        System.out.println(NanoIdUtils.randomNanoId(DEFAULT_NUMBER_GENERATOR, "123456789".toCharArray(), 2));
        System.out.println(UUID.randomUUID());*/

//        List<String> names1 = new ArrayList<String>();
//        names1.add("Google ");
//        names1.add("W3CSchool ");
//        names1.add("Taobao ");
//        names1.add("Baidu ");
//        names1.add("Sina ");
//
//        List<String> names2 = new ArrayList<String>();
//        names2.add("Google ");
//        names2.add("W3CSchool ");
//        names2.add("Taobao ");
//        names2.add("Baidu ");
//        names2.add("Sina ");
//
//
//        sortUsingJava8(names2);
//        names2.forEach(System.out::println);








    }
    public static void sortUsingJava8(List<String> list){

        list.sort(Comparator.naturalOrder());
    }



    /**
     * @Author Fengzl
     * @Desc
     * @Date 2022/6/23 15:43
     * @param: rand
     * @param: n
     * @return: java.lang.String
     **/
    public static String getRandomNumber(String rand, Integer n) {


        return rand + Math.abs(n);
    }
}
