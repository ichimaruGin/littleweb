package com.iwebirth.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by YY_410 on 2015/3/26.
 * 打印类型
 */
public class PrintTools {

    /**
     * 打印List
     * **/
    public static void printList(List list) {
        int len = list.size();
        if (len > 0) {
            for (int i = 0; i < len; i++) {
                printObject(list.get(i));
                System.out.println();
            }
        }
    }

    /**
     * 通过反射,打印单个对象
     * **/
    public static void printObject(Object o) {
        Class clazz = o.getClass();
        try{
            Field[] fields = clazz.getDeclaredFields();
            int len = fields.length;
            if(len == 0)
                return;
            PropertyDescriptor[] pd = new PropertyDescriptor[fields.length];
            for(int i=0;i<len;i++){
                pd[i] = new PropertyDescriptor(fields[i].getName(),clazz);
                Object value = pd[i].getReadMethod().invoke(o);
                System.out.print(fields[i].getName() + "---" + value + "  ");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
