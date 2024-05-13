package karate.castlemock;

import karate.common.utils.Var;
import org.json.JSONObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;


import  com.fasterxml.jackson.databind.*;


public class CastleMockRestClient {

    private static ObjectMapper jsonMapper;

    private static final String URL = "https://myurl.com/oauth/token";


    public CastleMockRestClient() {
    }

    public static String Login(String username, String password) throws Exception {


        Map<String, String> map = Map.of("username", username, "password", password);


        var request = HttpRequest.newBuilder().uri(URI.create(URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonMapper.writeValueAsString(map))).build();

        var client = HttpClient.newHttpClient();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        JSONObject jsnobject = new JSONObject(response.body());
        String token = jsnobject.getString("access_token");

        return token;
    }





}
