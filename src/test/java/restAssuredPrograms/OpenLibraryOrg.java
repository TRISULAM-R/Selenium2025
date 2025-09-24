package restAssuredPrograms;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.lessThan;

public class OpenLibraryOrg
{

    private final String BASE_URL = "https://openlibrary.org";

    @Test
    public void searchBookByIsbn() {

        String id = "0451526538";
        Response response = RestAssured
                .given().baseUri(BASE_URL)
                .when().get("/isbn/"+id+".json")
                .then().statusCode(200).statusLine("HTTP/1.1 200 OK")
                    .extract()
                    .response();
    }

    @Test
    public void searchByAuthorName() {

        String authorName = "tolkien";
        Response response = RestAssured
                .given().baseUri(BASE_URL)
                .when().get("search.json?author="+authorName)
                .then().statusCode(200).statusLine("HTTP/1.1 200 OK")
                .contentType("application/json")
//                .time(lessThan(1500L)) // response time in milliseconds
                .extract()
                .response();

        System.out.println(response.asString());
        String expectedAuthor = "J.R.R. Tolkien";
        String actualAuthor =response.jsonPath().get("docs[0].author_name[0]");
        System.out.println(actualAuthor);
        Assert.assertEquals(actualAuthor,expectedAuthor);
    }
}
