package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Utils {
    RequestSpecification requestSpecification;

    public RequestSpecification requestSpecificationForRequest()
    {
        // Add place to google API
        // Using Request and Response spec Builder
        requestSpecification =new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com/")
                .addQueryParam("key", "qaclick123")
                .setContentType(ContentType.JSON).build();
        return requestSpecification;
    }
}
