package tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UsersApi
{
    public static String URL = "https://reqres.in/api/users";
    RequestSpecification request = RestAssured.given();
    User user = new User("Francisco", "QA Automation");
    User userUpdate = new User("Nicolas", "Developer");

    public Response getUsers(){
        return request
                .when().get(URL).thenReturn();
    }

    public Response createUser() {
        return request
                .contentType(ContentType.JSON)
                .body(user)
                .when().post(URL).thenReturn();
    }

    public Response updateUser(int id){
        return request
                .contentType(ContentType.JSON)
                .body(userUpdate)
                .when()
                .put(URL+"/"+id)
                .thenReturn();
    }

    public Response deleteUser(){
        return request
                .when()
                .delete(URL)
                .thenReturn();
    }
}
