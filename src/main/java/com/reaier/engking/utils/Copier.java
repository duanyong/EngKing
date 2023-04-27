package com.reaier.engking.utils;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class Copier {

    private final Object source;

    private Table<Class, Class, Converter> converterTable = HashBasedTable.create();

    private boolean ignoreNullValue = false;

    private String[] ignoreProperties = new String[] {};

    private Copier(Object source) {
        this.source = source;
    }

    public static Copier from(Object source) {
        return new Copier(source);
    }

//    /**
//     * 注册属性转换器,可以多次调用
//     * 不要传lambda表达式
//     * @param converter spring提供的转换器接口
//     */
//    public <S, T> Copier usePropertyConverter(Converter<S, T> converter) {
//        Assert.notNull(converter, "属性转换器不能为空");
//
//        Class<?>[] generics = TypeResolver.resolveRawArguments(Converter.class, converter.getClass());
//
//        Assert.isTrue(generics.length == 2, "converter的泛型数量必须为2");
//
//        converterTable.put(
//                Objects.requireNonNull(generics[0]),
//                Objects.requireNonNull(generics[1]),
//                converter
//        );
//
//        return this;
//    }


    /**
     * 忽略null值,source对象中的null值不会写入target
     */
    public Copier ignoreNullValue() {
        this.ignoreNullValue = true;

        return this;
    }

    /**
     * 注册要忽略的属性名称,这些属性不会被写入target
     */
    public Copier ignoreProperties(String... propertyNames) {
        this.ignoreProperties = propertyNames;

        return this;
    }

    public <T> T to(T target) {
        Assert.notNull(target, "target不能为null");

        if (ignoreNullValue) {
            // 获取源类中所有为null的字段
            List<String> props = getSourceNullProperties();
            if (null != ignoreProperties && ignoreProperties.length > 0) {
                props.addAll(Arrays.asList(ignoreProperties));
            }

            String[] nulls = props.toArray(new String[props.size()]);

            BeanUtils.copyProperties(source, target, nulls);
        } else if (null != ignoreProperties) {
            BeanUtils.copyProperties(source, target, ignoreProperties);
        } else {
            BeanUtils.copyProperties(source, target);
        }

        return target;
    }

    public <T> T to(Supplier<T> supplier) {
        Assert.notNull(supplier, "supplier不能为null");

        return to(supplier.get());
    }

    private List<String> getSourceNullProperties() {
        List<String> nulls = new ArrayList<>();
        if (null == source) {
            return nulls;
        }

        Field[] fields = source.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            if (!fields[i].isAccessible()) {
                fields[i].setAccessible(true);
            }

            try {
                if (null == fields[i].get(source)) {
                    nulls.add(fields[i].getName());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return nulls;
    }
}
