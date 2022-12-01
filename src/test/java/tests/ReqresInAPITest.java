package tests;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ReqresInAPITest
{
    UsersApi api = new UsersApi();

    @Test(priority = 1)
    public void getFirstUser(){
        System.out.println("Getting all the users...");
        Response response = api.getUsers();
        String firstUser = "data[0]";

        System.out.println("Checking first user data...");
        System.out.println(response.getBody().jsonPath().get(firstUser));

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getBody().jsonPath().get(firstUser+".id"), 1);
        Assert.assertEquals(response.getBody().jsonPath().get(firstUser+".first_name"), "George");
        Assert.assertEquals(response.getBody().jsonPath().get(firstUser+".last_name"), "Bluth");
        Assert.assertNotNull(response.getBody().jsonPath().get(firstUser+".avatar"));
    }

    @Test(priority = 2)
    public void testPostUser(){
        System.out.println("Creating user...");
        Response response = api.createUser();

        System.out.println("Checking data of user created...");
        System.out.println(response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.getBody().jsonPath().get("name"), api.user.getName());
        Assert.assertEquals(response.getBody().jsonPath().get("job"), api.user.getJob());
        Assert.assertNotNull(response.getBody().jsonPath().get("id"));
        Assert.assertNotNull(response.getBody().jsonPath().get("createdAt"));

    }

    @Test(priority = 3)
    public void testUpdateUser(){
        System.out.println("Updating user...");
        Response response = api.updateUser(2);

        System.out.println("Checking data of user updated...");
        System.out.println(response.getBody().asString());

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getBody().jsonPath().get("name"), api.userUpdate.getName());
        Assert.assertEquals(response.getBody().jsonPath().get("job"), api.userUpdate.getJob());
        Assert.assertNotNull(response.getBody().jsonPath().get("updatedAt"));

    }

    @Test(priority = 4)
    public void testDeleteUser(){
        System.out.println("Deleting user...");
        Response response = api.deleteUser();

        System.out.println(response.getBody().asString());
        Assert.assertEquals(response.getStatusCode(), 204);
        Assert.assertTrue(response.getBody().asString().isEmpty());
    }

}
