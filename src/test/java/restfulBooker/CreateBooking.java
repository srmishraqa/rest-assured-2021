package restfulBooker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class CreateBooking {

    @Test
    public void createAndValidateBooking(){

        // 1.Build the Request
        RequestSpecification requestSpec = RestAssured.given();

        // putting the logger to see all logs - to print all request logs
        requestSpec = requestSpec.log().all();

        // But as of now Request Specification is  empty, we need to build it further
        // 1st set base URI and base Path on request specification
        requestSpec.baseUri("https://restful-booker.herokuapp.com/");
        requestSpec.basePath("booking");

        // Pass the body or payload in JSON format
        requestSpec.body("{\n" +
                "    \"firstname\" : \"Soumya\",\n" +
                "    \"lastname\" : \"Mishra\",\n" +
                "    \"totalprice\" : 112,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2019-01-01\",\n" +
                "        \"checkout\" : \"2020-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Lunch\"\n" +
                "}");

        // need to specify explicitly content-type header - use enum provided by Rest assured
        requestSpec.contentType(ContentType.JSON);

        //2. Send the request and get the response - call the method through ref of Request Specification
        Response response = requestSpec.post();

        //3. Validate the response
        // response can't be validated directly , we need to convert that that to a validatable format
        // That we can achieve using then() on top of response - return type is ValidatableResponse
        System.out.println(response.getStatusCode());
        ValidatableResponse validatableResponse = response.then();
        // To Log all the response 
        validatableResponse = validatableResponse.log().all();
        validatableResponse.statusCode(200);
    }
}
