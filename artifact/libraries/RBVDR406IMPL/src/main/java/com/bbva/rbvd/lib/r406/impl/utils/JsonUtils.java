package com.bbva.rbvd.lib.r406.impl.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtils {

    private static final String DATE = "yyyy-MM-dd";
    private static final JsonUtils INSTANCE = new JsonUtils();

    private final Gson gson;

    private JsonUtils() {
        gson = new GsonBuilder()
                .setDateFormat(DATE)
                .create();
    }

    public static JsonUtils getInstance() { return INSTANCE; }

    public String serialization(Object o) { return this.gson.toJson(o); }


}
