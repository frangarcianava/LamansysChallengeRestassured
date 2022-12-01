package tests;

import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

public class DogAPITest {
    DogAPI api = new DogAPI();
    @Test(priority = 1)
    public void checkCorrectSchema(){
        System.out.println("Getting all the characters...");
        String json = api.getData();

        System.out.println("Checking the correct schema...");
        MatcherAssert.assertThat(json, JsonSchemaValidator.matchesJsonSchema(new File("src/main/resources/dogApiSchema.json")));
    }
}
