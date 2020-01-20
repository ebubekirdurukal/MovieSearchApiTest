import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.apache.log4j.Logger;
import sun.jvm.hotspot.utilities.Assert;
import static io.restassured.RestAssured.given;


public class MovieSearch {

    private String apiKey = "4ac08a10";
    private String imdbID, title, year, released = null;
    final static Logger logger = Logger.getLogger(MovieSearch.class);
    private Response res = null;


    @Test
    public void movieSearch() {
        RestAssured.baseURI = "http://www.omdbapi.com/";
        res =
                given().param("i", getImdbId()).
                        then().
                        when().
                        get("?apikey=" + apiKey + "&").
                        then().
                        assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                        extract().response();
        title = res.jsonPath().getString("Title");
        year = res.jsonPath().getString("Year");
        released = res.jsonPath().getString("Released");
        Assert.that(title != null, "Title field is not set!");
        Assert.that(year != null, "Title field is not set!");
        Assert.that(released != null, "Title field is not set!");
        String responseString = res.asString();
        System.out.println("The movie's title is " + title + ", Year is " + year + " and release date is " + released);

    }


    private String getImdbId() {
        RestAssured.baseURI = "http://www.omdbapi.com/";
        res =
                given().param("s", "Harry Potter and the Sorcerer's Stone").
                        param("type", "movie").
                        when().
                        get("?apikey=" + apiKey + "&").
                        then().
                        assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                        extract().response();
        imdbID = res.jsonPath().getString("Search[0].imdbID");
        System.out.println(imdbID);
        return imdbID;
    }


}
