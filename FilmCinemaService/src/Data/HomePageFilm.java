package Data;

public class HomePageFilm {
    private String id;
    private String posterPath;
    private String name;
    private String scord;
    private String date;



    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String p) {
        this.posterPath = p;
    }
    /*public Bitmap getPoster() {
        return poster;
    }*/

    public String getName() {
        return name;
    }

    public String getScord() {
        return scord;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

/*    public void setPoster(Bitmap poster) {
        this.poster = poster;
    }*/

    public void setDate(String date) {
        this.date = date;
    }

    public void setScord(String scord) {
        this.scord = scord;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
