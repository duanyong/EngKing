package com.reaier.engking.domain.adatper;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.reaier.engking.domain.view.SourceWords;

import java.io.IOException;

public class SourceWordsAdatper extends TypeAdapter<SourceWords> {
    @Override
    public void write(JsonWriter jsonWriter, SourceWords word) throws IOException {
        jsonWriter.beginObject();

        jsonWriter.name("word").value(word.getWord());
        jsonWriter.name("en_mp3").value(word.getEnMp3());
        jsonWriter.name("en_phonetic").value(word.getEnPhonetic());
        jsonWriter.name("am_mp3").value(word.getAmMp3());
        jsonWriter.name("am_phonetic").value(word.getAmPhonetic());

        jsonWriter.name("means").value(new Gson().toJson(word.getMeans()));

        jsonWriter.endObject();
    }

    @Override
    public SourceWords read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
