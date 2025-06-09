package tests;

import config.Config;
import model.UserRequest;
import io.restassured.response.Response;
import model.UserResponse;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static utils.Constants.*;

import static io.restassured.RestAssured.given;

public class Account extends Config {

    String password = "tesT123!@#";

    SoftAssert softAssert;

    @BeforeMethod(alwaysRun = true)
    public void setup(){
        softAssert = new SoftAssert();

    }
    @Test(priority = 1)
    public void postCreateUser(){

        UserRequest userRequest = UserRequest.createUser();

        Response response = given()
                .body(userRequest)
                .when().post(CREATE_USER);

        String userID = response.jsonPath().get("userID");
        System.out.println("UserID is: " + userID);
        String user = response.jsonPath().get("username");
        System.out.println("Username is: " + user);

        softAssert.assertEquals(response.getStatusCode(),201, "Expected 201 but got: " + response.getStatusCode());
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void postCreateUser_UsernameExists(){

        UserRequest userRequest = UserRequest.createUser();

        Response response = given()
                .body(userRequest)
                .when().post(CREATE_USER);

        String userName = response.jsonPath().get("username");

        UserRequest createUserAgain = userRequest
                .withUserName(userName);

        Response response1 = given()
                .body(createUserAgain)
                .when().post(CREATE_USER);

        String errorMessage = response1.jsonPath().get("message");
        softAssert.assertEquals(response1.getStatusCode(),406, "Expected 406 but got: " + response1.getStatusCode());
        softAssert.assertEquals(errorMessage, "User exists!");
        softAssert.assertAll();
    }

    @Test(priority = 3)
    public void postCreateUser_UsernameEmpty(){

        UserRequest userRequest = UserRequest.createEmptyUser();

        Response response = given()
                .body(userRequest)
                .when().post(CREATE_USER);

        String errorMessage = response.jsonPath().get("message");
        softAssert.assertEquals(response.getStatusCode(),400, "Expected 400 but got: " + response.getStatusCode());
        softAssert.assertEquals(errorMessage, "UserName and Password required.");
        softAssert.assertAll();
    }

    @Test(priority = 4)
    public void postCreateUser_PasswordEmpty(){

        UserRequest userRequest = UserRequest.createEmptyPassword();

        Response response = given()
                .body(userRequest)
                .when().post(CREATE_USER);

        String errorMessage = response.jsonPath().get("message");
        softAssert.assertEquals(response.getStatusCode(),400, "Expected 400 but got: " + response.getStatusCode());
        softAssert.assertEquals(errorMessage, "UserName and Password required.");
        softAssert.assertAll();
    }

    @Test(priority = 5)
    public void postCreateUser_PasswordNoAlphaNumeric(){

        UserRequest userRequest = UserRequest.createPasswordNoAlphanumeric();

        Response response = given()
                .body(userRequest)
                .when().post(CREATE_USER);

        String errorMessage = response.jsonPath().get("message");
        softAssert.assertEquals(response.getStatusCode(),400, "Expected 400 but got: " + response.getStatusCode());
        softAssert.assertEquals(errorMessage, "Passwords must have at least one non alphanumeric character, one digit ('0'-'9'), one uppercase ('A'-'Z'), one lowercase ('a'-'z'), one special character and Password must be eight characters or longer.");
        softAssert.assertAll();
    }

    @Test(priority = 6)
    public void postCreateUser_PasswordNoLowercase(){

        UserRequest userRequest = UserRequest.createPasswordNoLowercase();

        Response response = given()
                .body(userRequest)
                .when().post(CREATE_USER);

        String errorMessage = response.jsonPath().get("message");
        softAssert.assertEquals(response.getStatusCode(),400, "Expected 400 but got: " + response.getStatusCode());
        softAssert.assertEquals(errorMessage, "Passwords must have at least one non alphanumeric character, one digit ('0'-'9'), one uppercase ('A'-'Z'), one lowercase ('a'-'z'), one special character and Password must be eight characters or longer.");
        softAssert.assertAll();
    }

    @Test(priority = 7)
    public void postCreateUser_PasswordNoUppercase(){

        UserRequest userRequest = UserRequest.createPasswordNoUppercase();

        Response response = given()
                .body(userRequest)
                .when().post(CREATE_USER);

        String errorMessage = response.jsonPath().get("message");
        softAssert.assertEquals(response.getStatusCode(),400, "Expected 400 but got: " + response.getStatusCode());
        softAssert.assertEquals(errorMessage, "Passwords must have at least one non alphanumeric character, one digit ('0'-'9'), one uppercase ('A'-'Z'), one lowercase ('a'-'z'), one special character and Password must be eight characters or longer.");
        softAssert.assertAll();
    }

    @Test(priority = 8)
    public void postCreateUser_PasswordNoDigits(){

        UserRequest userRequest = UserRequest.createPasswordNoDigits();

        Response response = given()
                .body(userRequest)
                .when().post(CREATE_USER);

        String errorMessage = response.jsonPath().get("message");
        softAssert.assertEquals(response.getStatusCode(),400, "Expected 400 but got: " + response.getStatusCode());
        softAssert.assertEquals(errorMessage, "Passwords must have at least one non alphanumeric character, one digit ('0'-'9'), one uppercase ('A'-'Z'), one lowercase ('a'-'z'), one special character and Password must be eight characters or longer.");
        softAssert.assertAll();
    }

    @Test(priority = 9)
    public void postCreateUser_PasswordWith7characters(){

        UserRequest userRequest = UserRequest.createPassword7characters();

        Response response = given()
                .body(userRequest)
                .when().post(CREATE_USER);

        String errorMessage = response.jsonPath().get("message");
        softAssert.assertEquals(response.getStatusCode(),400, "Expected 400 but got: " + response.getStatusCode());
        softAssert.assertEquals(errorMessage, "Passwords must have at least one non alphanumeric character, one digit ('0'-'9'), one uppercase ('A'-'Z'), one lowercase ('a'-'z'), one special character and Password must be eight characters or longer.");
        softAssert.assertAll();
    }

    @Test(priority = 10)
    public void postAuthorization(){

        UserRequest userRequest = UserRequest.createUser();

        Response response = given()
                .body(userRequest)
                .when().post(CREATE_USER);

        String userID = response.jsonPath().get("userID");
        System.out.println("UserID is: " + userID);
        String user = response.jsonPath().get("username");
        System.out.println("Username is: " + user);

        Response response1 = given()
                .body(userRequest)
                .when().post(AUTHORIZED);

        softAssert.assertEquals(response1.getStatusCode(),200, "Expected 200 but got: " + response1.getStatusCode());
        softAssert.assertAll();
    }

    @Test(priority = 11)
    public void postAuthorizationNotExistingUser(){

        UserRequest userRequest = UserRequest.AuthorizationNotExistingUser();

        Response response = given()
                .body(userRequest)
                .when().post(AUTHORIZED);

        softAssert.assertEquals(response.getStatusCode(),404, "Expected 404 but got: " + response.getStatusCode());
        softAssert.assertAll();
    }

    @Test(priority = 12)
    public void postAuthorizationUsernameEmpty(){

        UserRequest userRequest = UserRequest.AuthorizationEmptyUser();

        Response response = given()
                .body(userRequest)
                .when().post(AUTHORIZED);

        softAssert.assertEquals(response.getStatusCode(),400, "Expected 400 but got: " + response.getStatusCode());
        softAssert.assertAll();
    }

    @Test(priority = 13)
    public void postAuthorizationPasswordEmpty(){

        UserRequest userRequest = UserRequest.AuthorizationEmptyPassword();

        Response response = given()
                .body(userRequest)
                .when().post(AUTHORIZED);

        softAssert.assertEquals(response.getStatusCode(),400, "Expected 400 but got: " + response.getStatusCode());
        softAssert.assertAll();
    }


    @Test(priority = 14)
    public void postGenerateToken(){

        UserRequest userRequest = UserRequest.createUser();

        Response response = given()
                .body(userRequest)
                .when().post(CREATE_USER);

        String userName = response.jsonPath().get("username");

        UserRequest createToken = userRequest
                .withUserName(userName)
                .withPassword(password);

        Response response1 = given()
                .body(createToken)
                .when().post(GENERATE_TOKEN);
        String token = response1.jsonPath().get("token");
        System.out.println("Token is: " + token);

        softAssert.assertEquals(response1.getStatusCode(),200, "Expected 200 but got: " + response1.getStatusCode());
        softAssert.assertAll();
    }

    @Test(priority = 15)
    public void getUserById(){

        UserRequest userRequest = UserRequest.createUser();

        Response response = given()
                .body(userRequest)
                .when().post(CREATE_USER);

        String userName = response.jsonPath().get("username");
        String userID = response.jsonPath().get("userID");
        System.out.println("UserID is: " + userID);

        UserRequest createToken = userRequest
                .withUserName(userName)
                .withPassword(password);

        Response response1 = given()
                .body(createToken)
                .when().post(GENERATE_TOKEN);
        String token = response1.jsonPath().get("token");
        System.out.println("Token is: " + token);

        UserRequest getUser = userRequest
                .withUserName(userName)
                .withPassword(password);

        Response response2 = given()
                .header("Authorization", "Bearer " + token)
                .body(getUser)
                .pathParam("UUID", userID)
                .when().get(GET_USER_ACCOUNT);

        softAssert.assertEquals(response2.getStatusCode(),201, "Expected 201 but got: " + response2.getStatusCode());
        softAssert.assertAll();
    }

    @Test(priority = 16)
    public void getUserWrongToken(){

        UserRequest userRequest = UserRequest.createUser();

        Response response = given()
                .body(userRequest)
                .when().post(CREATE_USER);

        String userID = response.jsonPath().get("userID");
        System.out.println("UserID is: " + userID);

        Response response1 = given()
                .header("Authorization", "Bearer " + "sjkhskjs8787")
                .body(userRequest)
                .pathParam("UUID", userID)
                .when().get(GET_USER_ACCOUNT);

        softAssert.assertEquals(response1.getStatusCode(),401, "Expected 401 but got: " + response1.getStatusCode());
        softAssert.assertAll();
    }

    @Test(priority = 17)
    public void deleteUser(){

        UserRequest userRequest = UserRequest.createUser();

        Response response = given()
                .body(userRequest)
                .when().post(CREATE_USER);

        String userName = response.jsonPath().get("username");
        String userID = response.jsonPath().get("userID");
        System.out.println("UserID is: " + userID);

        UserRequest createToken = userRequest
                .withUserName(userName)
                .withPassword(password);

        Response response1 = given()
                .body(createToken)
                .when().post(GENERATE_TOKEN);
        String token = response1.jsonPath().get("token");
        System.out.println("Token is: " + token);

        Response response2 = given()
                .header("Authorization", "Bearer " + token)
                .pathParam("UUID", userID)
                .when().delete(DELETE_USER);

        softAssert.assertEquals(response2.getStatusCode(),201, "Expected 201 but got: " + response2.getStatusCode());
        softAssert.assertAll();
    }

}
