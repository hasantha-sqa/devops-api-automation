package org.devops;

import io.cucumber.java8.En;

public class Context {

    private String AuthorizationToken;
    private String AuthenticationToken;

    public String getAuthorizationToken() {
        return AuthorizationToken;
    }

    public void setAuthorizationToken(String AuthorizationToken) {
        AuthorizationToken = AuthorizationToken;
    }

    public String getAuthenticationToken() {
        return AuthenticationToken;
    }

    public void setAuthenticationToken(String authenticationToken) {
        AuthenticationToken = authenticationToken;
    }
}
