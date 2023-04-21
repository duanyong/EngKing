package com.reaier.engking.domain.convert;

import com.reaier.engking.utils.JsonUtils;
import org.springframework.lang.Nullable;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.List;


public class JSONConverter {
    public JSONConverter() {
    }

    @Converter(
            autoApply = true
    )
    public static class LongListConverter implements AttributeConverter<List, String > {
        @Nullable
        public String convertToDatabaseColumn(List list) {
            return null == list ? null : JsonUtils.obj2Json(list);
        }

        @Nullable
        public List<Long> convertToEntityAttribute(String list) {
            return null == list ? null : JsonUtils.json2Obj(list, List.class, Long.class);
        }
    }

    @Converter(
            autoApply = true
    )
    public static class IntegerListConverter implements AttributeConverter<List, String > {
        @Nullable
        public String convertToDatabaseColumn(List list) {
            return null == list ? null : JsonUtils.obj2Json(list);
        }

        @Nullable
        public List<Integer> convertToEntityAttribute(String list) {
            return null == list ? null : JsonUtils.json2Obj(list, List.class, Integer.class);
        }
    }

    @Converter(
            autoApply = true
    )
    public static class StringListConverter implements AttributeConverter<List, String > {
        @Nullable
        public String convertToDatabaseColumn(List list) {
            return null == list ? null : JsonUtils.obj2Json(list);
        }

        @Nullable
        public List<String> convertToEntityAttribute(String list) {
            return null == list ? null : JsonUtils.json2Obj(list, List.class, String.class);
        }
    }
}
