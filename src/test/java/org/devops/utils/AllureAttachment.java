package org.devops.utils;

import io.qameta.allure.Attachment;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.UnsupportedEncodingException;

public class AllureAttachment {

    @Attachment(value = "{endpointName} RQ/RS", type = "text/plain")
    public static String addSearchDetailsToReport(String endpointName, String requestType, RequestSpecification request, Response response) throws UnsupportedEncodingException {

        RequestResponseAttachment requestResponseAttachment = new RequestResponseAttachment(request, response);


        return "# # # # # # # # # # # # # # - - - R E Q U E S T - - - # # # # # # # # # # # # # #" + "\n\n" +
                "Request Type : " + requestType + "\n\n" +
                "URL : " + requestResponseAttachment.getUrl() + "\n\n" +
                "Request Headers : " + "\n" + requestResponseAttachment.getRequestHeaders() + "\n\n" +
                "Path Parameters : " + "\n" + requestResponseAttachment.getPathParameters() + "\n\n" +
                "Query Parameters : " + "\n" + requestResponseAttachment.getQueryParameters() + "\n\n" +
                "Request Parameters : " + "\n" + requestResponseAttachment.getRequestParameters() + "\n\n" +
                "Request Body : " + "\n" + requestResponseAttachment.getRequestBody() + "\n\n" +
                "# # # # # # # # # # # # # # - - - R E S P O N S E - - - # # # # # # # # # # # # # #" + "\n\n" +
                "Response Time : " + requestResponseAttachment.getResponseTime() + " ms\n\n" +
                "Response Headers : " + "\n" + requestResponseAttachment.getResponseHeaders() + "\n\n" +
                "Response : " + "\n" + requestResponseAttachment.getResponse() + "\n\n";

    }

}
