package main.java.ru.api.restassured;

import java.util.HashMap;
import java.util.Map;

public class APIControl {

    private String accessToken;

    // METHODS PUBLIC
    @Step("Получаем access_token")
    public void requestToken(String url) {
        RequestSpecification request = RestAssured
                .with()
                .header("X-REQUEST-ID", "jdautotest");

        Map<String, String> accessTokenData = new HashMap<>();
        accessTokenData.put("grant_type", "password");
        accessTokenData.put("client_id", "frontend");
        accessTokenData.put("username", "some_username");
        accessTokenData.put("password", "some_password");

        Response response = request
                .auth()
                .preemptive()
                .basic("frontend", "some")
                .and()
                .with()
                .params(accessTokenData)
                .when()
                .post(url + "/some/address");

        accessToken = getResponseAttr(response.asString(), Other.ACCESS_TOKEN.low());
    }

    @Step("Делаем запрос get")
    public Response requestGet(String requestStr) {
        return getRequest()
                .when()
                .get(requestStr);
    }

    private RequestSpecification getRequest() {
        return RestAssured
                .with()
                .header("X-REQUEST-ID", "autotest")
                .cookie(Other.ACCESS_TOKEN.low(), accessToken)
                .contentType("application/json");
    }

    @Step("Делаем запрос put")
    public Response requestPut(String jsonTemplate, String requestStr) {
        return getRequest()
                .body(jsonTemplate)
                .when()
                .put(requestStr);
    }

    @Step("Делаем запрос post")
    public Response requestPost(String jsonTemplate, String requestStr) {
        return getRequest()
                .body(jsonTemplate)
                .when()
                .post(requestStr);
    }

    @Step("Делаем запрос delete")
    public Response requestDelete(String jsonTemplate, String requestStr) {
        return getRequest()
                .body(jsonTemplate)
                .when()
                .delete(requestStr);
    }


}
