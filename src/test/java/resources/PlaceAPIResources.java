package resources;

public enum PlaceAPIResources {

    // enum is a special class in java which has a collection of constants or methods

    AddPlaceAPI("/maps/api/place/add/json"),
    GetPlaceAPI("/maps/api/place/get/json"),
    DeletePlaceAPI("/maps/api/place/delete/json");

    private final String resource;

    PlaceAPIResources(String resource)
    {
        this.resource = resource;
    }

    public String getResource()
    {
        return resource;
    }
}

