import java.util.ArrayList;
import java.util.List;

public class SearchModel {

    private List<Movie> Search;

    public SearchModel(List<Movie> search) {
        Search = search;
    }

    public void setSearch(List<Movie> search) {
        Search = search;
    }

    public List<Movie> getSearch() {
        return Search;
    }

}
