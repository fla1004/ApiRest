package testClean;

import factoryRequest.FactoryRequest;
import factoryRequest.RequestInformation;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.ConfigAPI;
import util.GetProperties;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.equalTo;

public class CRUDProjectTest {

    @BeforeEach
    public void before() throws IOException {
        new GetProperties().readProperties();
    }

    @Test
    public void createItem(){
        JSONObject body = new JSONObject();
        body.put("Content","FlaItem");

        RequestInformation request = new RequestInformation(ConfigAPI.CREATE_PROJECT,body.toString());
        Response response = FactoryRequest.make(FactoryRequest.POST).send(request);
        response.then()
                .statusCode(200)
                .body("Content", equalTo("FlaItem"));
        String id = response.then().extract().path("Id")+"";
        System.out.println("ID: "+id);
    }

    @Test
    public void updteItem(){
        JSONObject body = new JSONObject();
        body.put("Content","FlaItem");

        RequestInformation request = new RequestInformation(ConfigAPI.CREATE_PROJECT,body.toString());
        Response response = FactoryRequest.make(FactoryRequest.POST).send(request);
        response.then()
                .statusCode(200)
                .body("Content", equalTo("FlaItem"));
        String id = response.then().extract().path("Id")+"";
        System.out.println("ID: "+id);


        request = new RequestInformation(ConfigAPI.UPDATE_PROJECT.replace("ID",id),
                "{\n" +
                "   \"Content\": \"Flavia update\"\n" +
                "}");
        response = FactoryRequest.make(FactoryRequest.PUT).send(request);

        response.then()
                .statusCode(200)
                .body("Content", equalTo("Flavia update"));

    }

    @Test
    public void getItem(){
        JSONObject body = new JSONObject();
        body.put("Content","FlaItem");

        RequestInformation request = new RequestInformation(ConfigAPI.CREATE_PROJECT,body.toString());
        Response response = FactoryRequest.make(FactoryRequest.POST).send(request);
        response.then()
                .statusCode(200)
                .body("Content", equalTo("FlaItem"));
        String id = response.then().extract().path("Id")+"";
        System.out.println("ID: "+id);


        request = new RequestInformation(ConfigAPI.READ_PROJECT.replace("ID",id),"");
        response = FactoryRequest.make(FactoryRequest.GET).send(request);

        response.then()
                .statusCode(200)
                .body("Content", equalTo("FlaItem"));
    }

    @Test
    public void deleteItem(){

        JSONObject body = new JSONObject();
        body.put("Content","FlaItem");

        RequestInformation request = new RequestInformation(ConfigAPI.CREATE_PROJECT,body.toString());
        Response response = FactoryRequest.make(FactoryRequest.POST).send(request);
        response.then()
                .statusCode(200)
                .body("Content", equalTo("FlaItem"));
        String id = response.then().extract().path("Id")+"";
        System.out.println("ID: "+id);


        request = new RequestInformation(ConfigAPI.DELETE_PROJECT.replace("ID",id),"");
        response = FactoryRequest.make(FactoryRequest.DELETE).send(request);

        response.then()
                .statusCode(200)
                .body("Deleted", equalTo(true));

    }

}