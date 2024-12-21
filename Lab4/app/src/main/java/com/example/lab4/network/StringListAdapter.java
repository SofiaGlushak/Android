package com.example.lab4.network;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

public class StringListAdapter implements JsonDeserializer<String> {
    @Override
    public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json.isJsonArray() && json.getAsJsonArray().size() > 0) {
            return json.getAsJsonArray().get(0).getAsString();
        }
        return "";
    }
}
