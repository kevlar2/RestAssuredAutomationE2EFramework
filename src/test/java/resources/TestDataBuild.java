package resources;

import pojo.AddPlace;
import pojo.Location;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {

    public AddPlace addPlacePayload(String name, String language, String address)
    {
        AddPlace addPlace = new AddPlace();
        addPlace.setAccuracy(50);
        addPlace.setAddress(address);
        addPlace.setLanguage(language);
        addPlace.setPhone_number("(+91) 983 893 3937");
        addPlace.setWebsite("http://google.com");
        addPlace.setName(name);

        // Creating an object for list class
        List<String> myTypesList = new ArrayList<String>();
        myTypesList.add("shoe park");
        myTypesList.add("shop");
        addPlace.setTypes(myTypesList);

        // Creating a location class object for add-place class to use
        Location location=new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);

        //Pass the value add-place object
        addPlace.setLocation(location);

        return addPlace;

    }

    public String deletPlacePayload(String placeId)
    {
        return "{\"place_id\":\""+placeId+"\"}";
    }
}
