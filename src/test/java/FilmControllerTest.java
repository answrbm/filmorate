import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class FilmControllerTest {

    private static final String url = "http://localhost:8080";

    @Test
    public void testFilmCreationWithEmptyName() {
        String body = """
                {
                "name": "",
                "description": "TestDescr",
                "releaseDate": "2024-06-05",
                "duration": 60
                }
                """;
        Response response = RestAssured
                .given()
                .body(body)
                .header("Content-Type","application/json" )
                .when()
                .post(url+"/films");
        response.then().assertThat().statusCode(400);
    }

    @Test
    public void testFilmCreationWith201CharactersDescription() {
        String body = """
                {
                "name": "TestName",
                "description": "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.asffsfgsg1",
                "releaseDate": "2024-06-05",
                "duration": 60
                }
                """;
        Response response = RestAssured
                .given()
                .body(body)
                .header("Content-Type","application/json" )
                .when()
                .post(url+"/films");
        response.then().assertThat().statusCode(400);
    }

    @Test
    public void testFilmCreationWith200CharactersDescription() {
        String body = """
                {
                "name": "TestName",
                "description": "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.asffsfgsg",
                "releaseDate": "2024-06-05",
                "duration": 60
                }
                """;
        Response response = RestAssured
                .given()
                .body(body)
                .header("Content-Type","application/json" )
                .when()
                .post(url+"/films");
        response.then().assertThat().statusCode(201);
    }

    @Test
    public void testFilmCreationWith199CharactersDescription() {
        String body = """
                {
                "name": "TestName",
                "description": "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.asffsfgs",
                "releaseDate": "2024-06-05",
                "duration": 60
                }
                """;
        Response response = RestAssured
                .given()
                .body(body)
                .header("Content-Type","application/json" )
                .when()
                .post(url+"/films");
        response.then().assertThat().statusCode(201);
    }

    @Test
    public void testFilmCreationWithReleaseDateBeforeValid() {
        String body = """
                {
                "name": "TestName",
                "description": "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.asffsfgs",
                "releaseDate": "1895-12-27",
                "duration": 60
                }
                """;
        Response response = RestAssured
                .given()
                .body(body)
                .header("Content-Type","application/json" )
                .when()
                .post(url+"/films");
        response.then().assertThat().statusCode(400);
    }

    @Test
    public void testFilmCreationWithReleaseDateEqualToValid() {
        String body = """
                {
                "name": "TestName",
                "description": "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.asffsfgs",
                "releaseDate": "1895-12-28",
                "duration": 60
                }
                """;
        Response response = RestAssured
                .given()
                .body(body)
                .header("Content-Type","application/json" )
                .when()
                .post(url+"/films");
        response.then().assertThat().statusCode(201);
    }

    @Test
    public void testFilmCreationWithReleaseDateAfterValid() {
        String body = """
                {
                "name": "TestName",
                "description": "Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.asffsfgs",
                "releaseDate": "1895-12-29",
                "duration": 60
                }
                """;
        Response response = RestAssured
                .given()
                .body(body)
                .header("Content-Type","application/json" )
                .when()
                .post(url+"/films");
        response.then().assertThat().statusCode(201);
    }

    @Test
    public void testFilmCreationWithPositiveDuration() {
        String body = """
                {
                "name": "TestName",
                "description": "TestDescr",
                "releaseDate": "1895-12-29",
                "duration": 60
                }
                """;
        Response response = RestAssured
                .given()
                .body(body)
                .header("Content-Type","application/json" )
                .when()
                .post(url+"/films");
        response.then().assertThat().statusCode(201);
    }

    @Test
    public void testFilmCreationWithNegativeDuration() {
        String body = """
                {
                "name": "TestName",
                "description": "TestDescr",
                "releaseDate": "1895-12-29",
                "duration": -60
                }
                """;
        Response response = RestAssured
                .given()
                .body(body)
                .header("Content-Type","application/json" )
                .when()
                .post(url+"/films");
        response.then().assertThat().statusCode(400);
    }






}
