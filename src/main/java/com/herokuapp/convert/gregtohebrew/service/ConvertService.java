package com.herokuapp.convert.gregtohebrew.service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONException;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
public class ConvertService {

    public String gregToHebrew(int gy, int gm, int gd) throws JSONException, UnirestException {

        UriTemplate template = new UriTemplate(
                "https://www.hebcal.com/converter?cfg=json&gy={gy}&gm={gm}&gd={gd}&g2h=1");

        /* Create and put PathVariables to the map to prepare it for the UriTemplate */
        Map<String, Integer> variables = new HashMap<>();
        variables.put("gy", gy);
        variables.put("gm", gm);
        variables.put("gd", gd);

        /* Set the values from the map to the template */
        URI expand = template.expand(variables);

        /* Get Json due Unirest - lightweight HTTP client library from Mashape*/
        HttpResponse<JsonNode> response = Unirest.get(expand.toString())
                .header("accept", "application/json").asJson();

        System.out.println(response.getBody().getObject().get("hebrew"));
        /* Result returning by match key-word "hebrew" */
        return response.getBody().getObject().get("hebrew").toString();
    }
}
