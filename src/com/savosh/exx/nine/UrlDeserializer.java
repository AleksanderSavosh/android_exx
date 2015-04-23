package com.savosh.exx.nine;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UrlDeserializer implements JsonDeserializer<List<String>> {
    @Override
    public List<String> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        ArrayList<String> urls = new ArrayList<String>();
        JsonObject base = json.getAsJsonObject();
        JsonArray data = base.get("data").getAsJsonArray();
        for(JsonElement gifInfo : data){
            JsonObject images = gifInfo.getAsJsonObject().get("images").getAsJsonObject();
            JsonObject original = images.get("original").getAsJsonObject();
            String url = original.get("url").getAsString();
            urls.add(url);
        }

        return urls;
    }
}