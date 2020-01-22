public class Movie {

    public String Title;
    public int Year;
    public String imdbID;
    public String Type;
    public String Poster;

    public Movie(String title, int year, String imdbID, String type, String poster) {
        this.Title = title;
        this.Year = year;
        this.imdbID = imdbID;
        Type = type;
        Poster = poster;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        this.Year = year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "Title='" + Title + '\'' +
                ","+"\n"+ "Year=" + Year +
                ","+"\n"+ "imdbId=" + imdbID + '\''+
                ","+"\n"+ "Type="+ Type + '\'' +
                ","+"\n"+ "Poster="+ Poster + '\'' +
                '}';
    }
}