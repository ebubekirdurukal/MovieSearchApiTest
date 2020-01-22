import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.apache.log4j.Logger;
import sun.jvm.hotspot.utilities.Assert;
import com.google.gson.Gson;

import java.util.List;

import static io.restassured.RestAssured.given;


public class MovieSearch {

    private String apiKey = "4ac08a10";  //OMDB api key
    private String imdbID, title, year, released = null;
    final static Logger logger = Logger.getLogger(MovieSearch.class); //logger object
    private Response res = null;
    Gson gson = new Gson(); // for deserialization of json objects

    @Test
    public void movieSearch() {
        RestAssured.baseURI = "http://www.omdbapi.com/"; //base url
        res =
                given().param("i", getImdbId()).
                        then().
                        when().
                        get("?apikey=" + apiKey + "&").
                        then().
                        assertThat().statusCode(200).and().
                        extract().response();
        title = res.jsonPath().getString("Title");
        year = res.jsonPath().getString("Year");
        released = res.jsonPath().getString("Released");
        Assert.that(title != null, "Title field is not set!");
        Assert.that(year != null, "Title field is not set!");
        Assert.that(released != null, "Title field is not set!");
        logger.info("\n************\n" + "Test is succesful\n" + "************");
        logger.info("The movie's title is " + title + ", Year is " + year + " and release date is " + released);

    }


    private String getImdbId() {
        RestAssured.baseURI = "http://www.omdbapi.com/";
        res =
                given().param("s", "Harry Potter").
                        param("type", "movie").
                        when().
                        get("?apikey=" + apiKey + "&").
                        then().
                        assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                        extract().response();
        logger.info("Movie search is succesful");

        String responseAsString = res.asString();
        SearchModel searchModel = gson.fromJson(responseAsString, SearchModel.class);
        List<Movie> movies = searchModel.getSearch();

        for (Movie movie : movies) {
            if (movie.Title.equals("Harry Potter and the Sorcerer's Stone")) {
                imdbID = movie.imdbID;
                logger.info(movie.toString());
                logger.info("imdbID is taken");
                break;
            }

        }
        return imdbID;
    }


}
