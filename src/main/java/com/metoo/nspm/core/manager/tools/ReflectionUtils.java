package com.metoo.nspm.core.manager.tools;

import java.lang.reflect.Field;

/**
 * @author HKK
 * @version 1.0
 * @date 2023-10-13 12:11
 */
public class ReflectionUtils {

    public static Object getPropertyValue(Object object, String propertyName) throws NoSuchFieldException, IllegalAccessException {
        Class<?> clazz = object.getClass();  // 获取对象的类
        Field field = clazz.getDeclaredField(propertyName);  // 获取属性
        field.setAccessible(true);  // 设置属性可访问
        return field.get(object);  // 获取属性值
    }

}
