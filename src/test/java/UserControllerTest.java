import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class UserControllerTest {

    private final String url = "http://localhost:8080";

    @Test
    public void testUserCreationWithValidEmail() {
        String body = """
                {
                    "email": "test@mail.com",
                    "login": "testlogin",
                    "name": "TestName",
                    "birthdate": "2024-06-05"
                }
                """;
        Response response = RestAssured
                .given()
                .body(body)
                .header("Content-Type","application/json" )
                .when()
                .post(url+"/users");
        response.then().assertThat().statusCode(201);
    }

    @Test
    public void testUserCreationWithInvalidEmail() {
        String body = """
                {
                    "email": "test$mail.com",
                    "login": "testlogin",
                    "name": "TestName",
                    "birthdate": "2024-06-05"
                }
                """;
        Response response = RestAssured
                .given()
                .body(body)
                .header("Content-Type","application/json" )
                .when()
                .post(url+"/users");
        response.then().assertThat().statusCode(400);
    }

    @Test
    public void testUserCreationWithEmptyEmail() {
        String body = """
                {
                    "login": "testlogin",
                    "name": "TestName",
                    "birthdate": "2024-06-05"
                }
                """;
        Response response = RestAssured
                .given()
                .body(body)
                .header("Content-Type","application/json" )
                .when()
                .post(url+"/users");
        response.then().assertThat().statusCode(400);
    }

    @Test
    public void testUserCreationWithValidLogin() {
        String body = """
                {
                    "email": "test@mail.com",
                    "login": "testlogin",
                    "name": "TestName",
                    "birthdate": "2024-06-05"
                }
                """;
        Response response = RestAssured
                .given()
                .body(body)
                .header("Content-Type","application/json" )
                .when()
                .post(url+"/users");
        response.then().assertThat().statusCode(201);
    }

    @Test
    public void testUserCreationWithInvalidLogin() {
        String body = """
                {
                    "email": "test@mail.com",
                    "login": "test login",
                    "name": "TestName",
                    "birthdate": "2024-06-05"
                }
                """;
        Response response = RestAssured
                .given()
                .body(body)
                .header("Content-Type","application/json" )
                .when()
                .post(url+"/users");
        response.then().assertThat().statusCode(400);
    }

    @Test
    public void testUserCreationWithEmptyLogin() {
        String body = """
                {
                    "email": "test@mail.com",
                    "name": "TestName",
                    "birthdate": "2024-06-05"
                }
                """;
        Response response = RestAssured
                .given()
                .body(body)
                .header("Content-Type","application/json" )
                .when()
                .post(url+"/users");
        response.then().assertThat().statusCode(400);
    }

    @Test
    public void testUserCreationWithValidName() {
        String body = """
                {
                    "email": "test@mail.com",
                    "login": "testlogin",
                    "name": "TestName",
                    "birthdate": "2024-06-05"
                }
                """;
        Response response = RestAssured
                .given()
                .body(body)
                .header("Content-Type","application/json" )
                .when()
                .post(url+"/users");
        response.then().assertThat().statusCode(201);
    }

    @Test
    public void testUserCreationWithEmptyName() {
        String body = """
                {
                    "email": "test@mail.com",
                    "login": "testlogin",
                    "birthdate": "2024-06-05"
                }
                """;
        Response response = RestAssured
                .given()
                .body(body)
                .header("Content-Type","application/json" )
                .when()
                .post(url+"/users");
        response.then().assertThat().statusCode(201);
    }

    @Test
    public void testUserCreationWithValidBirthdateInPast() {
        String body = """
                {
                    "email": "test@mail.com",
                    "login": "testlogin",
                    "name": "TestName",
                    "birthdate": "1998-01-15"
                }
                """;
        Response response = RestAssured
                .given()
                .body(body)
                .header("Content-Type","application/json" )
                .when()
                .post(url+"/users");
        response.then().assertThat().statusCode(201);
    }

    @Test
    public void testUserCreationWithInvalidBirthdateInPresent() {
        String body = "{\"email\": \"test@mail.com\",\"login\":\"testlogin\",\"name\":\"TestName\",\"birthdate\":\" " + LocalDate.now() + "\"}";
        Response response = RestAssured
                .given()
                .body(body)
                .header("Content-Type","application/json" )
                .when()
                .post(url+"/users");
        response.then().assertThat().statusCode(400);
    }

    @Test
    public void testUserCreationWithInvalidBirthdateInFuture() {
        String body = """
                {
                    "email": "test@mail.com",
                    "login": "testlogin",
                    "name": "TestName",
                    "birthdate": "2024-06-06"
                }
                """;
        Response response = RestAssured
                .given()
                .body(body)
                .header("Content-Type","application/json" )
                .when()
                .post(url+"/users");
        response.then().assertThat().statusCode(400);
    }
}
