package com.android.redditreader.api;

import com.android.redditreader.dataaccess.entities.SubRedditResponse;
import com.android.redditreader.dataaccess.entities.Thread;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SubRedditDeserializer implements JsonDeserializer<SubRedditResponse> {

    @Override
    public SubRedditResponse deserialize(JsonElement json, Type typeOfT,
                                         JsonDeserializationContext context) throws JsonParseException {

        SubRedditResponse subRedditResponse = new SubRedditResponse();
        List<Thread> subRedditThreads = new ArrayList<>();
        Gson gson = new Gson();

        final JsonObject response = json.getAsJsonObject();
        final JsonObject data = response.get(SubRedditConstant.DATA).getAsJsonObject();

        if (data.has(SubRedditConstant.BEFORE) && !data.get(SubRedditConstant.BEFORE).isJsonNull()) {
            subRedditResponse.setBefore(data.get(SubRedditConstant.BEFORE).getAsString());
        }

        if (data.has(SubRedditConstant.AFTER) && !data.get(SubRedditConstant.AFTER).isJsonNull()) {
            subRedditResponse.setAfter(data.get(SubRedditConstant.AFTER).getAsString());
        }

        final JsonArray children = data.get(SubRedditConstant.CHILDREN).getAsJsonArray();

        for (JsonElement child : children) {
            final JsonObject childData = child.getAsJsonObject().get(SubRedditConstant.DATA).getAsJsonObject();
            Thread thread = gson.fromJson(childData, Thread.class);
            subRedditThreads.add(thread);
        }
        subRedditResponse.setThreads(subRedditThreads);
        return subRedditResponse;
    }
}
