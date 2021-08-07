package factoryRequest;

import util.ConfigEnvironment;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestGET implements IRequest{

    @Override
    public Response send(RequestInformation information) {

        Response response=given()
                    .auth()
                    .preemptive()
                .basic(ConfigEnvironment.user,ConfigEnvironment.password)

                .log()
                    .all()
                .when()
                    .get(information.getUrl());

        return response;
    }
}
