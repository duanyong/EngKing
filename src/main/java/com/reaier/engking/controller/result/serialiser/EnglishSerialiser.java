package com.reaier.engking.controller.result.serialiser;

import com.google.gson.*;
import com.reaier.engking.domain.dictionary.English;

import java.lang.reflect.Type;

public class EnglishSerialiser implements JsonSerializer<English> {

    @Override
    public JsonElement serialize(English english, Type type, JsonSerializationContext jsonSerializationContext) {
        final JsonObject json = new JsonObject();

        json.addProperty("en_mp3", english.getEnMp3());
        json.addProperty("en_phonetic", english.getEnPhonetic());
        json.addProperty("am_mp3", english.getAmMp3());
        json.addProperty("am_phonetic", english.getAmPhonetic());

        final JsonArray means = new JsonArray();
        for (Object object : english.getMeans()) {
            means.add(new JsonPrimitive(new Gson().toJson(object)));
        }

        json.add("means", means);

        return json;
    }
}
