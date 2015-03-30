package com.holyboom.flyer.health.uitil;

import com.google.gson.Gson;

import java.lang.reflect.Type;

/**
 * Created by shyboooy on 15/3/18.
 */
public class JSONDecoder<T> {

    public JSONObject<T> decode(String json,Class cls) {
        Gson gson = new Gson();
        Type type = JSONObject.getType(cls);
        JSONObject<T> obj = gson.fromJson(json,type);
        return obj;
    }
}
