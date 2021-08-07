package basicTest;

import util.ConfigEnvironment;
import factoryRequest.RequestInformation;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class CRUDItemTest {

      @Test
        public void createItem(RequestInformation information){

          Response response=given()
                  .auth()
                  .preemptive()
                  .basic(ConfigEnvironment.user,ConfigEnvironment.password)
                  .body(information.getBody())
                  .log()
                  .all()
                  .when()
                  .post(information.getUrl());

          response.then()
                  .statusCode(200)
                  .body("Content", equalTo("Items homework"))
                  .log()
                  .all();

          int id = response.then().extract().path("Id");
          System.out.println("ID: " + id);

      }

    @Test
    public void updateItem(){
        JSONObject body= new JSONObject();
        body.put("Content","PrimerItem");

        Response response=given()
                .auth()
                .preemptive()
                .basic(ConfigEnvironment.user,ConfigEnvironment.password)
                .body(new File("C:\\Users\\Richard\\Documents\\Diplomado\\Modulos\\Modulo 3 mobile\\CRUDItems\\src\\test\\resources\\body.json"))
                .log()
                .all()
                .when()
                .post("http://todo.ly/api/items.json");

        response.then()
                .statusCode(200)
                .body("Content", equalTo("PrimerItem"))
                .log()
                .all();

        int id = response.then().extract().path("Id");
        System.out.println("ID: " + id);


        body.put("Content","PrimerItemUpdate");

         response = given()
                .auth()
                .preemptive()
                 .basic(ConfigEnvironment.user,ConfigEnvironment.password)
                .body(body.toString())
                .log()
                .all()
        .when()
                .put("http://todo.ly/api/items/"+id+".json");

        response.then()
                .statusCode(200)
                .body("Content", equalTo("PrimerItemUpdate"))
                .log()
                .all();

    }
    @Test
    public void deleteItem(){

        JSONObject body= new JSONObject();
        body.put("Content","PrimerItem");

        Response response=given()
                .auth()
                .preemptive()
                .basic(ConfigEnvironment.user,ConfigEnvironment.password)
                .body(body.toString())
                .log()
                .all()
                .when()
                .post("http://todo.ly/api/items.json");

        response.then()
                .statusCode(200)
                .body("Content", equalTo("PrimerItem"))
                .log()
                .all();

        int id = response.then().extract().path("Id");

        response=given()
                .auth()
                .preemptive()
                .basic(ConfigEnvironment.user,ConfigEnvironment.password)
                .log()
                .all()
                .when()
                .delete("http://todo.ly/api/items/"+id+".json");

        response.then()
                .statusCode(200)
                .body("Content", equalTo("PrimerItem"))
                .body("Deleted",equalTo(true))
                .log()
                .all();

    }
    @Test
    public void getItem() {
        JSONObject body = new JSONObject();
        body.put("Content", "PrimerItem");

        Response response = given()
                .auth()
                .preemptive()
                .basic(ConfigEnvironment.user,ConfigEnvironment.password)
                .body(body.toString())
                .log()
                .all()
                .when()
                .post("http://todo.ly/api/items.json");

        response.then()
                .statusCode(200)
                .body("Content", equalTo("PrimerItem"))
                .log()
                .all();

        int id = response.then().extract().path("Id");

        response = given()
                .auth()
                .preemptive()
                .basic(ConfigEnvironment.user,ConfigEnvironment.password)
                .log()
                .all()
                .when()
                .get("http://todo.ly/api/items/" + id + ".json");

        response.then()
                .statusCode(200)
                .body("Content", equalTo("PrimerItem"))
                .log()
                .all();
    }
}
