package org.devops.objects;

import com.google.gson.JsonObject;

public class UserCredentials {

    private final String username;
    private final String password;
    public UserCredentials(String username, String password){

        this.username = username;
        this.password = password;

    }

    public JsonObject getRequestJson() {

        JsonObject request = new JsonObject();
        request.addProperty("username", this.username);
        request.addProperty("password", this.password);
        return request;

    }

    public String getUsername() {

        return this.username;

    }

    public String getPassword() {

        return this.password;

    }
}
