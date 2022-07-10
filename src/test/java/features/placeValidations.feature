Feature: Validating Place API's

    @AddPlace
  Scenario Outline: Verify if Place is being successfully added using AddPlaceAPI
    Given Add place payload <name>, <language>, <address>
    When User calls "AddPlaceAPI" with "Post" http request
    Then The API call is successful with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created maps to "<name>" using "GetPlaceAPI"

    Examples:
        | name            | language  | address           |
        | Nigeria House  | English    | World cross road  |
        | Kevin Academy | Spanish     | London Road       |
        | Jamie Academy | USA        | New York Road     |

    @DeletePlace
  Scenario: Verify is delete place functionality is working

    Given DeletePlace Payload
    When User calls "DeletePlaceAPI" with "Post" http request
    Then The API call is successful with status code 200
    And "status" in response body is "OK"