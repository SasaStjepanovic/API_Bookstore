package tests;

import config.Config;
import io.restassured.response.Response;
import model.BookRequest;
import model.UserRequest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static utils.Constants.*;

import static io.restassured.RestAssured.given;

public class Bookstore extends Config {

    String isbn;
    String password = "tesT123!@#";
    SoftAssert softAssert;

    @BeforeMethod(alwaysRun = true)
    public void setup(){
        softAssert = new SoftAssert();

    }
    @Test(priority = 1)
    public void getAllBooks() {

        Response response = given()
                .when().get(GET_ALL_BOOKS);

        this.isbn = response.jsonPath().getString("books[0].isbn");
        System.out.println("ISBN is: " + isbn);
        softAssert.assertEquals(response.getStatusCode(), 200, "Expected 200 but got: " + response.getStatusCode());
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void getBook() {

        Response response = given()
                .queryParam("ISBN", this.isbn)
                .when().get(GET_BOOK);

        softAssert.assertEquals(response.getStatusCode(), 200, "Expected 200 but got: " + response.getStatusCode());
        softAssert.assertAll();
    }

    @Test(priority = 3)
    public void createBooks() {
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

        String body = "{\n" +
                "  \"userId\": \""+ userID +"\",\n" +
                "  \"collectionOfIsbns\": [\n" +
                "    {\n" +
                "      \"isbn\": \""+ this.isbn +"\"\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        Response response2 = given()
                .header("Authorization", "Bearer " + token)
                .body(body)
                .when().post(CREATE_BOOKS);

        softAssert.assertEquals(response2.getStatusCode(), 201, "Expected 201 but got: " + response2.getStatusCode());
        softAssert.assertAll();
    }

    @Test(priority = 4)
    public void updateBook() {

        Response response = given()
                .when().get(GET_ALL_BOOKS);

        String isbn = response.jsonPath().getString("books[0].isbn");
        System.out.println("ISBN is: " + isbn);

        UserRequest userRequest = UserRequest.createUser();

        Response response1 = given()
                .body(userRequest)
                .when().post(CREATE_USER);

        Assert.assertEquals(response1.getStatusCode(), 201, "Expected 201 but got: " + response1.getStatusCode());

        String userName = response1.jsonPath().get("username");
        String userID = response1.jsonPath().get("userID");
        System.out.println("UserID is: " + userID);

        UserRequest createToken = userRequest
                .withUserName(userName)
                .withPassword(password);

        Response response2 = given()
                .body(createToken)
                .when().post(GENERATE_TOKEN);

        Assert.assertEquals(response2.getStatusCode(), 200, "Expected 200 but got: " + response2.getStatusCode());

        String token = response2.jsonPath().get("token");
        System.out.println("Token is: " + token);

        BookRequest bookRequest = BookRequest.deleteBook();

        String updatedIsbn = "9781449325812";
        String updatedUserID = "71bef214-52b9-4609-8ffe-b0d3fa045d12";
        BookRequest updateBook = bookRequest
                .withIsbn(updatedIsbn)
                .withUserId(updatedUserID);

        Response response3 = given()
                .header("Authorization", "Bearer " + token)
                .pathParam("ISBN", this.isbn)
                .body(updateBook)
                .when().put(UPDATE_BOOK);

        softAssert.assertEquals(response3.getStatusCode(), 201, "Expected 201 but got: " + response3.getStatusCode());
        softAssert.assertAll();
    }

    @Test(priority = 5)
    public void deleteBook() {

        Response response = given()
                .when().get(GET_ALL_BOOKS);

        String isbn = response.jsonPath().getString("books[0].isbn");
        System.out.println("ISBN is: " + isbn);

        UserRequest userRequest = UserRequest.createUser();

        Response response1 = given()
                .body(userRequest)
                .when().post(CREATE_USER);

        Assert.assertEquals(response1.getStatusCode(), 201, "Expected 201 but got: " + response1.getStatusCode());

        String userName = response1.jsonPath().get("username");
        String userID = response1.jsonPath().get("userID");
        System.out.println("UserID is: " + userID);

        UserRequest createToken = userRequest
                .withUserName(userName)
                .withPassword(password);

        Response response2 = given()
                .body(createToken)
                .when().post(GENERATE_TOKEN);

        Assert.assertEquals(response2.getStatusCode(), 200, "Expected 200 but got: " + response2.getStatusCode());

        String token = response2.jsonPath().get("token");
        System.out.println("Token is: " + token);

        BookRequest bookRequest = BookRequest.deleteBook();

        BookRequest deleteBook = bookRequest
                .withIsbn(isbn)
                .withUserId(userID);

        Response response3 = given()
                .header("Authorization", "Bearer " + token)
                .body(deleteBook)
                .when().delete(DELETE_BOOK);

        softAssert.assertEquals(response3.getStatusCode(), 201, "Expected 201 but got: " + response3.getStatusCode());
        softAssert.assertAll();
    }

    @Test(priority = 6)
    public void deleteBooks() {

        Response response = given()
                .when().get(GET_ALL_BOOKS);

        String isbn = response.jsonPath().getString("books[0].isbn");
        System.out.println("ISBN is: " + isbn);

        UserRequest userRequest = UserRequest.createUser();

        Response response1 = given()
                .body(userRequest)
                .when().post(CREATE_USER);

        Assert.assertEquals(response1.getStatusCode(), 201, "Expected 201 but got: " + response1.getStatusCode());

        String userName = response1.jsonPath().get("username");
        String userID = response1.jsonPath().get("userID");
        System.out.println("UserID is: " + userID);

        UserRequest createToken = userRequest
                .withUserName(userName)
                .withPassword(password);

        Response response2 = given()
                .body(createToken)
                .when().post(GENERATE_TOKEN);

        Assert.assertEquals(response2.getStatusCode(), 200, "Expected 200 but got: " + response2.getStatusCode());

        String token = response2.jsonPath().get("token");
        System.out.println("Token is: " + token);


        Response response3 = given()
                .header("Authorization", "Bearer " + token)
                .queryParam("UserId", userID)
                .when().delete(DELETE_BOOKS);

        System.out.println(response3.prettyPeek());
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(response3.getStatusCode(), 204, "Expected 204 but got: " + response3.getStatusCode());
        softAssert.assertAll();
    }




}