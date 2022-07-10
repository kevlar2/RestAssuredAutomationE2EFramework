package StepDefinition;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {

    @Before("@DeletePlace")
    public void beforeScenario() throws IOException
    {
        StepDefinition sd = new StepDefinition();
        if(StepDefinition.place_id == null)
        {
            sd.add_place_payload("Riveria", "Spanish", "Spain");
            sd.user_calls_add_place_api_with_post_http_request("AddPlaceAPI", "POST");
            sd.verifyPlace_IdCreatedMapsToUsing("Riveria","GetPlaceAPI");
        }

    }

}
