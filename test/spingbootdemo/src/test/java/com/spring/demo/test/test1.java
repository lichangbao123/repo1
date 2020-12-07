package com.spring.demo.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: 17597
 * @Date: 2020/12/7
 * @Description: com.spring.demo.test
 * @version: 1.0
 */
public class test1 {
    public static void main(String[] args) {

        List list = new ArrayList();

        Map map=new HashMap();
        map.put("name","1");
        list.add(map);
        for (int i=0; i<list.size();i++ ) {
            System.out.println( list.get(i) );
        }
    }


}
