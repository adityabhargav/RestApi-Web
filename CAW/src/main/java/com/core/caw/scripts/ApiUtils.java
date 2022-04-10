package com.core.caw.scripts;

import com.core.caw.commons.BaseApi;
import com.core.caw.commons.apiUtils.IRestResponse;
import com.core.caw.commons.apiUtils.ResponseUtil;
import com.core.caw.helper.FileHelper;
import com.core.caw.pages.api.UserPage;
import io.restassured.response.Response;

import java.io.IOException;

public class ApiUtils extends BaseApi {

    public IRestResponse<UserPage> getToken(String email, String password) throws IOException {
        String baseUrl = FileHelper.getPropFile("url.properties", "ApiBaseUrl");
        String credentials = "{\"email\":\"" + email + "\",\"password\":\"" + password + "\"}";
        Response resp = super.post("", credentials, baseUrl + "/users/login");
        return new ResponseUtil(UserPage.class, resp);
    }

    public IRestResponse<UserPage> getResp(String method, String token, String reqBody, String route) throws Exception {
        Response resp = null;
        String baseUrl = FileHelper.getPropFile("url.properties", "ApiBaseUrl");
        switch (method) {
            case "get":
                resp = super.get(token, baseUrl + route);
                break;
            case "post":
                resp = super.post(token, reqBody, baseUrl + route);
                break;
            case "put":
                resp = super.put(token, reqBody, baseUrl + route);
                break;
            case "delete":
                resp = super.delete(token, reqBody, baseUrl + route);
                break;
            case "retrieve":
                resp = super.retrieve(token, baseUrl + route);
                break;
            default:
                throw new Exception("Enter the correct method to validate this request");
        }
        return new ResponseUtil(UserPage.class, resp);
    }
}
