package com.core.caw.pages.api;

import com.core.caw.commons.apiUtils.IRestResponse;
import com.core.caw.helper.FileHelper;
import com.core.caw.scripts.ApiUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class UserPage {

    private ApiUtils apiReq = new ApiUtils();

    public String getToken(String email, String password) throws ParseException, IOException {
        IRestResponse<UserPage> resp = apiReq.getToken(email, password);
        System.out.println("response :: " + resp.getContent());
        if (resp.isSuccessful()) {
            JSONParser jsonParser = new JSONParser();
            JSONObject object = (JSONObject) jsonParser.parse(resp.getContent());
            return object.get("token").toString();
        } else {
            return null;
        }
    }

    public String[][] addUser(String reqBody) throws Exception {
        String[][] result = new String[1][2];
        String token = this.getToken(FileHelper.getPropFile("login.properties", "email"), FileHelper.getPropFile("login.properties", "password"));
        IRestResponse<UserPage> resp = this.apiReq.getResp("post", token, reqBody, "/users");
        result[0][0] = String.valueOf(resp.getStatusCode());
        result[0][1] = resp.getContent();
        return result;
    }

    public String[][] fetchUser() throws Exception {
        String[][] result = new String[1][2];
        String token = this.getToken(FileHelper.getPropFile("login.properties", "email"), FileHelper.getPropFile("login.properties", "password"));
        IRestResponse<UserPage> resp = this.apiReq.getResp("get", token, "", "/users/me");
        result[0][0] = String.valueOf(resp.getStatusCode());
        result[0][1] = resp.getContent();
        return result;
    }

    public String[][] fetchUserInvalidToken() throws Exception {
        String[][] result = new String[1][2];
        String token = "X0X0X0X0";
        IRestResponse<UserPage> resp = this.apiReq.getResp("get", token, "", "/users/me");
        result[0][0] = String.valueOf(resp.getStatusCode());
        result[0][1] = resp.getContent();
        return result;
    }


}