package Data;

public class MyCritic {
	private String filmId;
    private String filmName;
    private float scord;
    private String date;
    private String praise;
    private String content;
    private boolean isPraise;

    public void setPraise(boolean praise) {
        isPraise = praise;
    }

    public boolean isPraise() {
        return isPraise;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public float getScord() {
        return scord;
    }

    public void setScord(float scord) {
        this.scord = scord;
    }

    public String getDate() {
        return date;
    }

    public String getPraise() {
        return praise;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPraise(String praise) {
        this.praise = praise;
    }

    public String getFilmId() {
        return filmId;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }
}
