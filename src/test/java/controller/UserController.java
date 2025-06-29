package controller;

import config.ItemModel;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import config.UserModel;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.annotations.Test;
import utils.Utils;

import java.util.Properties;

import static io.restassured.RestAssured.given;

public class UserController {

    Properties prop;


    public UserController(Properties prop) {
        this.prop = prop;
    }

    public Response createUser(UserModel userModel) {
        RestAssured.baseURI = prop.getProperty("baseUrl");
        return given().contentType("application/json")
                .body(userModel)
//                .header("Authorization", "bearer " + prop.getProperty("token"))
//                .header("X-AUTH-SECRET-KEY", prop.getProperty("secretKey"))
                .when().post("/api/auth/register");
    }

    public Response userLogin(UserModel userModel) {
        RestAssured.baseURI = prop.getProperty("baseUrl");
        return given().contentType("application/json")
                .body(userModel)
                .when().post("/api/auth/login");
    }

    public Response getUserList() {
        return given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + prop.getProperty("userToken"))
                .when()
                .get("/api/user/users");
    }

    public Response searchUser(String userID) {
        RestAssured.baseURI = prop.getProperty("baseUrl");
        return given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + prop.getProperty("adminToken"))
                .when()
                .get("/api/user/" + userID);
    }

    public Response editUserInfo(String userModel) {
        RestAssured.baseURI = prop.getProperty("baseUrl");
        return given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + prop.getProperty("adminToken"))
                .body(userModel)
                .when()
                .put("/api/user/" + prop.getProperty("userID"));
    }

    public Response getItemList() {
        RestAssured.baseURI = prop.getProperty("baseUrl");
        return given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + prop.getProperty("userToken"))
                .when()
                .get("/api/costs/");
    }

    public Response addItem(ItemModel itemModel) {
        RestAssured.baseURI = prop.getProperty("baseUrl");
        return given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + prop.getProperty("userToken"))
                .body(itemModel)
                .when()
                .post("/api/costs/");
    }

    public Response editItem(String itemModel) {
        return given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + prop.getProperty("userToken"))
                .body(itemModel)
                .when()
                .put("api/costs/" + prop.getProperty("itemID"));
    }

    public Response deleteItem() {
        return given()
                .contentType("application/json")
                .header("Authorization", "Bearer " + prop.getProperty("userToken"))
                .when()
                .delete("api/costs/" + prop.getProperty("itemID"));
    }

}
