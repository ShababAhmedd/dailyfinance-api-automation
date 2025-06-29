package testrunner;

import io.qameta.allure.*;
import com.github.javafaker.Faker;
import config.ItemModel;
import controller.UserController;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import jdk.jshell.execution.Util;
import org.apache.commons.configuration.ConfigurationException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import config.Setup;
import config.UserModel;
import utils.Utils;

import static io.restassured.RestAssured.given;

public class UserTestRunner extends Setup {

    private UserController userController;

    @BeforeMethod
    public void initUserController() {
        userController = new UserController(prop);
    }

    @Test(priority = 1, description = "Register a new user", enabled = true)
    public void userReg() throws ConfigurationException, javax.naming.ConfigurationException {
//        UserController userController = new UserController(prop);
        UserModel userModel = new UserModel();
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = "shabab.ahmed2000sa" + Utils.generateRandomNumber(1000, 9999) + "@gmail.com";
        String password = "1234";
        String phoneNumber = "01" + Utils.generateRandomNumber(100000000, 999999999);
        String address = faker.address().fullAddress();
        String gender = "male";
        boolean termsAccepted = true;

        userModel.setFirstName(firstName);
        userModel.setLastName(lastName);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhoneNumber(phoneNumber);
        userModel.setAddress(address);
        userModel.setGender(gender);
        userModel.setTermsAccepted(termsAccepted);

        Utils.setEnvVar("userEmail", email);
        Utils.setEnvVar("userPass", password);

        Response res = userController.createUser(userModel);
        System.out.println(res.asString());

        JsonPath jsonObj = res.jsonPath();
        String userID = jsonObj.get("_id");
        System.out.println("User ID: " + userID);
        Utils.setEnvVar("userID", userID);

        String userToken = jsonObj.get("token");
        System.out.println("User Token: " + userToken);
        Utils.setEnvVar("userToken", userToken);

        Assert.assertEquals(res.getStatusCode(), 201);
    }

    @Test(priority = 2, description = "Login by admin", enabled = true)
    public void adminLogin() throws ConfigurationException, javax.naming.ConfigurationException {
//        UserController userController = new UserController(prop);

        UserModel userModel = new UserModel();
        userModel.setEmail("admin@test.com");
        userModel.setPassword("admin123");

        Response res = userController.userLogin(userModel);
        System.out.println(res.asString());

        JsonPath jsonObj = res.jsonPath();
        String adminToken = jsonObj.get("token");
        System.out.println("Admin Token: " + adminToken);
        Utils.setEnvVar("adminToken", adminToken);

        Assert.assertEquals(res.getStatusCode(), 200);
    }

    @Test(priority = 3, description = "Get user list", enabled = true)
    public void getAllUser() {
        Response res = userController.getUserList();
        System.out.println(res.asString());
        Assert.assertEquals(res.getStatusCode(), 200);
    }

    @Test(priority = 4, description = "search the new user by user id", enabled = true)
    public void searchUser() {
        Response res = userController.searchUser(prop.getProperty("userID"));
        System.out.println(res.asString());
        Assert.assertEquals(res.getStatusCode(), 200);
    }

    @Test(priority = 5, description = "edit the user info (e.g. firstname, phonenumber)", enabled = true)
    public void editUserInfo() {
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String userModel = "{\n" +
                "  \"firstName\": \"" + firstName + "\"\n" +
                "}";
        Response res = userController.editUserInfo(userModel);
        System.out.println(res.asString());

        Assert.assertEquals(res.getStatusCode(), 200);
    }

    @Test(priority = 5, description = "Login by any user", enabled = true)
    public void userLogin() throws javax.naming.ConfigurationException, ConfigurationException {
        UserModel userModel = new UserModel();
        userModel.setEmail(prop.getProperty("userEmail"));
        userModel.setPassword(prop.getProperty("userPass"));
        Response res = userController.userLogin(userModel);
        System.out.println(res.asString());

        JsonPath jsonObj = res.jsonPath();
        String userToken = jsonObj.get("token");
        System.out.println("Token: " + userToken);
        Utils.setEnvVar("userToken", userToken);

        Assert.assertEquals(res.getStatusCode(), 200);
    }

    @Test(priority = 6, description = "Get item list", enabled = true)
    public void getItemList() {
        Response res = userController.getItemList();
        System.out.println(res.asString());
        Assert.assertEquals(res.getStatusCode(), 200);
    }

    @Test(priority = 7, description = "Add any item", enabled = true)
    public void addItem() throws javax.naming.ConfigurationException, ConfigurationException {
        ItemModel itemModel = new ItemModel();
        itemModel.setItemName("test");
        itemModel.setQuantity(1);
        itemModel.setAmount("100");
        itemModel.setPurchaseDate("2025-06-22");
        itemModel.setMonth("January");
        itemModel.setRemarks("n/a");

        Response res = userController.addItem(itemModel);
        System.out.println(res.asString());

        JsonPath jsonObj = res.jsonPath();
        String itemID = jsonObj.get("_id");
        Utils.setEnvVar("itemID", itemID);

        Assert.assertEquals(res.getStatusCode(), 201);
    }

    @Test(priority = 8, description = "Edit any item name", enabled = true)
    public void editItem() throws javax.naming.ConfigurationException, ConfigurationException {
        String itemModel = "{\n" +
                "  \"itemName\": \"edited name\"\n" +
                "}";
        Response res = userController.editItem(itemModel);
        System.out.println(res.asString());
        Assert.assertEquals(res.getStatusCode(), 200);
    }

    @Test(priority = 9, description = "Delete any item from the item list", enabled = true)
    public void deleteItem() throws javax.naming.ConfigurationException, ConfigurationException {
        Response res = userController.deleteItem();
        System.out.println(res.asString());

        JsonPath jsonObj = res.jsonPath();
        String messageActual = jsonObj.get("message");
        String messageExpected = "Cost deleted successfully";
        Assert.assertTrue(messageActual.contains(messageExpected));
        Assert.assertEquals(res.getStatusCode(), 200);
    }

}