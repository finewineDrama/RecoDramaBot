import java.util.ArrayList;

public class Drama {
    private String title;
    private String cast;
    private int eps;
    private String synopsis;
    private ArrayList<String> genre;
    private String country;
    private String year;

    public Drama(String title, String country, String cast, int eps, String synopsis, ArrayList<String> genre, String year) {
        this.country = country;
        this.title = title;
        this.cast = cast;
        this.eps = eps;
        this.synopsis = synopsis;
        this.genre = genre;
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public String getCountry() {
        return country;
    }

    public int getEps() {
        return eps;
    }

    public String getCast() {
        return cast;
    }

    public ArrayList<String> getGenre() {
        return genre;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public String getTitle() {
        return title;
    }
}

