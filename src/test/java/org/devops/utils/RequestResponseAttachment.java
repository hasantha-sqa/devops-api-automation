package org.devops.utils;

import io.restassured.http.Headers;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class RequestResponseAttachment {

    private final long responseTime;
    private final Headers requestHeaders;
    private final Headers responseHeaders;
    private final Map<String, String> pathParameters;
    private final Map<String, String> queryParameters;
    private final Map<String, String> requestParameters;
    private final String requestBody;
    private String response;
    private final String url;

    public RequestResponseAttachment(RequestSpecification request, Response response) {

        this.responseTime = response.getTimeIn(TimeUnit.MILLISECONDS);
        this.requestHeaders = ((RequestSpecificationImpl) request).getHeaders();
        this.pathParameters = ((RequestSpecificationImpl) request).getNamedPathParams();
        this.queryParameters = ((RequestSpecificationImpl) request).getQueryParams();
        this.requestParameters = ((RequestSpecificationImpl) request).getRequestParams();
        this.requestBody = ((RequestSpecificationImpl) request).getBody() != null ? ((RequestSpecificationImpl) request).getBody().toString(): "";
        this.responseHeaders = response.getHeaders();
        this.response = response.getBody() != null ? response.body().prettyPrint() : "";
        this.url =((RequestSpecificationImpl) request).getURI();
    }

    String getRequestBody() {
        return requestBody;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    long getResponseTime() {
        return responseTime;
    }

    Map<String, String> getPathParameters() {
        return pathParameters;
    }

    Map<String, String> getRequestParameters() {
        return requestParameters;
    }

    Map<String, String> getQueryParameters() {
        return queryParameters;
    }

    Headers getRequestHeaders() {
        return requestHeaders;
    }

    public String getUrl() {
        return url;
    }

    public Headers getResponseHeaders() {
        return responseHeaders;
    }

}
