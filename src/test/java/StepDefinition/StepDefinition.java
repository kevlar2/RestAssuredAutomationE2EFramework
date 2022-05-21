package StepDefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.Cucumber;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.junit.runner.RunWith;
import resources.TestDataBuild;
import resources.Utils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

@RunWith(Cucumber.class)
public class StepDefinition extends Utils {

    // Global Declaration
    ResponseSpecification responseSpecification;
    Response res;
    TestDataBuild addPlace = new TestDataBuild();

    @Given("Add place payload")
    public void add_place_payload()
    {

        responseSpecification =new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();
    }

    @When("User calls AddPlaceAPI with Post http request")
    public void user_calls_add_place_api_with_post_http_request()
    {
        res = given().spec(requestSpecificationForRequest()).body(addPlace.addPlacePayload())
                .when().post("/maps/api/place/add/json")
                .then().spec(responseSpecification).extract().response();
    }

    @Then("The API call is successful with status code {int}")
    public void the_api_call_is_successful_with_status_code(Integer int1)
    {
        assertEquals(res.getStatusCode(), 200);

    }

    @Then("{string} in response body is {string}")
    public void something_in_response_body_is_something(String strArg1, String strArg2) throws Throwable
    {
        String responseString = res.asString();
        JsonPath js= new JsonPath(responseString);
        assertEquals(js.get(strArg1).toString(),strArg2);
    }

}
