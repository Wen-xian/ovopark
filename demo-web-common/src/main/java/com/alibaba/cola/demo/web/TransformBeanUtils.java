package com.alibaba.cola.demo.web;

import java.util.List;
import java.util.stream.Collectors;

public class TransformBeanUtils {

    public static <T> List<T> transformList(Object source, Class<T> targetClass) {
        if(source instanceof List){
            return ((List<?>) source).stream().map(s -> transform(s, targetClass)).collect(Collectors.toList());
        }
        return null;
    }

    public static <T> T transform(Object source, Class<T> targetClass) {
        try {
            T target = targetClass.newInstance();
            copyProperties(source, target);
            return target;
        } catch (Exception e) {
            throw new RuntimeException("Failed to transform source to target", e);
        }
    }

    private static <T> void copyProperties(Object source, T target) {

    }
}
