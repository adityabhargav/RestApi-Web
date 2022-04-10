package com.core.caw.helper;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DataHelper {

    public String getValFromObject(String key, String data) throws ParseException {
        String result = null;
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject object = (JSONObject) jsonParser.parse(data);
            result = object.get(key).toString();
        } catch (Exception e) {

        }
        return result;
    }
}
