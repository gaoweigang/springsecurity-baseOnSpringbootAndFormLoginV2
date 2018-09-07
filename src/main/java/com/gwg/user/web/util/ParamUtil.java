package com.gwg.user.web.util;

import org.apache.commons.lang3.StringUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.stream.Stream;

public class ParamUtil {

    /*
     * 参数非空校验:
     * String类型
     * 非String
     */
    public static boolean isEmpty(Object... objs){
        //非空校验
        if(objs == null || objs.length == 0){
            return true;
        }
        return Stream.of(objs).filter(obj -> {return (obj instanceof String)? StringUtils.isEmpty((String)obj) : obj == null;}).count() > 0;
    }

    /**
     * 字符串去前后空格
     */
    public static void trim(Object obj){
        if(obj == null){
            return;
        }
        try {
            BeanInfo bi = Introspector.getBeanInfo(obj.getClass());
            PropertyDescriptor[] pds = bi.getPropertyDescriptors();
            for(PropertyDescriptor p : pds){
                Method getMethod = p.getReadMethod();
                Method setMethod = p.getWriteMethod();
                Object value = getMethod.invoke(obj, null);
                if(null != value && StringUtils.equals(p.getPropertyType().getTypeName(), String.class.getTypeName())){//同一个类加载器加载的class对象相同
                    setMethod.invoke(obj, ((String) value).trim());
                }
            }
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成员工编号 HB+5位数字
     * @throws Exception
     */
    public static String generateStaffCode(){
        return "HB00001";
    }



    public static void main(String[] args) throws Exception{
        System.out.println(String.class.getTypeName());

    }

}
