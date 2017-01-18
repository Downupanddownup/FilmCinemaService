package Data;

public class FinforFilm {
	private String filmId;
    private String name;
    private String type;
    private String scord;
    private String time;
    private String date;
    private String posterName;
    
    public String getPosterName() {
        return posterName;
    }

    public void setPosterName(String posterName) {
        this.posterName = posterName;
    }

    public String getFilmId() {
        return filmId;
    }

    public String getName() {
        return name;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }

    public String getType() {
        return type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScord() {
        return scord;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setScord(String scord) {
        this.scord = scord;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
