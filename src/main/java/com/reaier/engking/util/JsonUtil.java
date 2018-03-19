package com.reaier.engking.util;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;

public class JsonUtil {
    private static final Gson JSON = new Gson();

    public static String json(Object obj) {
        try {
            return JSON.toJson(obj);
        } catch (JsonIOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static <T> T derialiser(String json, Class clz) {
        return (T) JSON.fromJson(json, clz);
    }

    public static void registerTypeAdapter(Class clz, Object object) {
        JSON.
    }
}
