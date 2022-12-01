package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class DogAPI {
    public static String URL = "https://dog.ceo/api/breeds/image/random";
    RequestSpecification request = RestAssured.given();

    public String getData() {
        Response res = request.get(URL);
        ResponseBody body = res.body();
        //Converting the response body to string
        return body.asString();
    }
}
