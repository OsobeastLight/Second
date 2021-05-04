package ru.api.tests;


import com.jayway.restassured.RestAssured;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;



public class SecondTestJson {
    @Test

        public void getAPI(){

            String json = RestAssured.get("https://status.encoding.com/status.php?format=json").asString();
            System.out.println(json);
            JSONObject jsonResponse = new JSONObject(json);
            System.out.println(jsonResponse.getString("status"));
            String status = jsonResponse.getString("status");
            System.out.println(jsonResponse.getJSONObject("incident_count").getInt("lastYear"));
            int lastYear = jsonResponse.getJSONObject("incident_count").getInt("lastYear");
            System.out.println(jsonResponse.getInt("uptime"));
            int upTime = jsonResponse.getInt("uptime");


        Assert.assertEquals(status, "Ok");
        Assert.assertTrue(lastYear<10);
        Assert.assertTrue(upTime>86400);



        //System.out.println(Assert.assertTrue(lastYear<10));
        }
    }









