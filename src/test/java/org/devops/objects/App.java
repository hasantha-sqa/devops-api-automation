package org.devops.objects;

import com.google.gson.JsonObject;

public class App {

    private final String key;
    private final String email;

    public App(String key, String email) {

        this.key = key;
        this.email = email;

    }

    public JsonObject getRequestJson() {

        JsonObject request = new JsonObject();
        request.addProperty("key", this.key);
        request.addProperty("email", this.email);
        return request;

    }

    public String getKey() {

        return this.key;

    }

    public String getEmail() {

        return this.email;

    }
}
