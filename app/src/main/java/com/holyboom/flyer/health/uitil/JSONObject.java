package com.holyboom.flyer.health.uitil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by shyboooy on 15/3/18.
 */
public class JSONObject<T> {
    int code;
    List<T> list;
    T data;

    public int getCode() {
        return code;
    }

    public T getData() {
        return data;
    }
    public List<T> getList() {
        return list;
    }

    public static Type getType(Class tClass) {
        return type(JSONObject.class,tClass);
    }

    private static ParameterizedType type(final Class raw, final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return raw;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }
}
