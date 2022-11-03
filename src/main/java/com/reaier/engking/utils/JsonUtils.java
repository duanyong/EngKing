package com.reaier.engking.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.OutputStream;

public class JsonUtils {
    public static final String JSON_DESERIALIZE_ERROR_MSG = "Json反序列化出错";

    public static final String JSON_SERIALIZE_ERROR_MSG = "Json序列化出错";

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .registerModule(new Jdk8Module())
            .registerModule(new JavaTimeModule())
            //.registerModule(new GuavaModule())
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

    public static Object json2Obj(String jsonStr) {
        return json2Obj(jsonStr, Object.class);
    }

    public static <T> T json2Obj(String content, Class<T> clazz) {
        if (content == null || content.isEmpty()) {
            return null;
        }

        try {
            return OBJECT_MAPPER.readValue(content, clazz);
        } catch (Exception e) {
            throw new RuntimeException(JSON_DESERIALIZE_ERROR_MSG, e);
        }
    }

    public static void outputStream(Object content, OutputStream output) {
        if (content == null) {
            return;
        }

        try {
            OBJECT_MAPPER.writeValue(output, content);
        } catch (Exception e) {
            throw new RuntimeException(JSON_SERIALIZE_ERROR_MSG, e);
        }
    }

    @SuppressWarnings({"rawtypes", "deprecation"})
    public static <T> T json2Obj(String content, Class<T> clazz, Class... classes) {
        if (null == content || content.isEmpty()) {
            return null;
        }

        JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(clazz, classes);
        try {
            return OBJECT_MAPPER.readValue(content, javaType);
        } catch (Exception e) {
            throw new RuntimeException(JSON_DESERIALIZE_ERROR_MSG, e);
        }
    }

//    public static TypeFactory getTypeFactory(){
//
//        return OBJECT_MAPPER.getTypeFactory();
//    }
//
//    public static <T> JsonResult<T> parseJsonResult(String content, Class<T> clazzItem, Class... classes) {
//
//        if (StringUtils.isBlank(content)) {
//            return null;
//        }
//        JavaType javaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(clazzItem, classes);
//        JavaType finalJavaType = OBJECT_MAPPER.getTypeFactory().constructParametricType(JsonResult.class, javaType);
//        try {
//            return OBJECT_MAPPER.readValue(content, finalJavaType);
//        } catch (Exception e) {
//            throw new RuntimeException(JSON_DESERIALIZE_ERROR_MSG, e);
//        }
//    }
//
//    public static <T> T json2Obj(String content, JavaType javaType) {
//        if (StringUtils.isBlank(content)) {
//            return null;
//        }
//        try {
//            return OBJECT_MAPPER.readValue(content, javaType);
//        } catch (Exception e) {
//            throw new RuntimeException(JSON_DESERIALIZE_ERROR_MSG, e);
//        }
//    }
//
//    @SuppressWarnings({"unchecked"})
//    public static <K, V> Map<K, V> json2Map(String content, Class<K> keyCls, Class<V> valueCls) {
//        Map<String, Object> jsonMap = json2Obj(content, Map.class);
//        if(jsonMap == null){
//            return null;
//        }
//        Map<K, V> result = Maps.newHashMap();
//        jsonMap.forEach((key, value) -> {
//            K keyObj = json2Obj(key, keyCls);
//            V valueObj = json2Obj(value.toString(), valueCls);
//            result.put(keyObj, valueObj);
//        });
//        return result;
//    }
//
    public static String obj2Json(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(JSON_SERIALIZE_ERROR_MSG, e);
        }
    }

}
