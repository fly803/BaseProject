package com.cg.baseproject.request.data;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author
 * @version 1.0
 * @date 2018/8/4
 */
public class GenericType<T> {

    private final Type type;

    protected GenericType(){
        Type superClass = getClass().getGenericSuperclass();

        type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
    }

    public Type getType() {
        return type;
    }
}
