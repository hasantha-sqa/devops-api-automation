package org.devops;

import io.cucumber.java8.En;

public class Context {

    private String authorizationToken;
    private String authenticationToken;

    public String getAuthorizationToken() {
        return this.authorizationToken;
    }

    public void setAuthorizationToken(String authorizationToken) {
        this.authorizationToken = authorizationToken;
    }

    public String getAuthenticationToken() {
        return this.authenticationToken;
    }

    public void setAuthenticationToken(String authenticationToken) {
        this.authenticationToken = authenticationToken;
    }
}
