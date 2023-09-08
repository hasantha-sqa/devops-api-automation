package org.devops.objects;

import com.google.gson.JsonObject;

public class LeaderboardUser {

    private final String username;
    private final int score;
    public LeaderboardUser(String username, int score){

        this.username = username;
        this.score = score;

    }

    public JsonObject getRequestJson() {

        JsonObject request = new JsonObject();
        request.addProperty("username", this.username);
        request.addProperty("score", this.score);
        return request;

    }

    public String getUsername() {

        return this.username;

    }

    public int getScore() {

        return this.score;

    }
}
