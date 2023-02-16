package StepDefinition;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.runner.RunWith;
import resources.PlaceAPIResources;
import resources.TestDataBuild;
import resources.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

@RunWith(Cucumber.class)
public class StepDefinition extends Utils {

    // Global Declaration
    ResponseSpecification responseSpecification;
    RequestSpecification res;
    Response response;
    TestDataBuild addPlace = new TestDataBuild();
    static String place_id;


    @Given("^Add place payload (.+), (.+), (.+)$")
    public void add_place_payload(String name, String language, String address) throws IOException {
        res = given().spec(requestSpecificationForRequest()).body(addPlace.addPlacePayload(name, language, address));


    }

    @When("User calls {string} with {string} http request")
    public void user_calls_add_place_api_with_post_http_request(String resource, String httpMethod) throws IOException
    {
        PlaceAPIResources resourceAPI = PlaceAPIResources.valueOf(resource);
        System.out.println(resourceAPI.getResource());
        responseSpecification =new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();
        if(httpMethod.equalsIgnoreCase("POST"))
        {
            response = res.when().post(resourceAPI.getResource())
                    .then().spec(responseSpecification).extract().response();
        }
        else if(httpMethod.equalsIgnoreCase("GET"))
        {
            response = res.when().get(resourceAPI.getResource())
                    .then().spec(responseSpecification).extract().response();
        }
        else if(httpMethod.equalsIgnoreCase("DELETE"))
        {
            response = res.when().delete(resourceAPI.getResource())
                    .then().spec(responseSpecification).extract().response();
        }


    }

    @Then("The API call is successful with status code {int}")
    public void the_api_call_is_successful_with_status_code(Integer int1)
    {
        assertEquals(response.getStatusCode(), 200);

    }

    @Then("{string} in response body is {string}")
    public void something_in_response_body_is_something(String strArg1, String ExpectedValue) throws Throwable
    {
        assertEquals(getJsonPath(response,strArg1).toString(),ExpectedValue);
    }

    @And("verify place_Id created maps to {string} using {string}")
    public void verifyPlace_IdCreatedMapsToUsing(String ExpectedName, String resource) throws IOException {
        // Get API call
        place_id = (String) getJsonPath(response, "place_id");
        res = given().spec(requestSpecificationForRequest().queryParam("place_id", place_id));
        user_calls_add_place_api_with_post_http_request(resource, "GET");
        String actualGetResponseName = (String) getJsonPath(response, "name");
        assertEquals(actualGetResponseName,ExpectedName);
    }

    @Given("DeletePlace Payload")
    public void delete_place_payload() throws IOException {
       res= given().spec(requestSpecificationForRequest().body(addPlace.deletPlacePayload(place_id)));

    }
}
