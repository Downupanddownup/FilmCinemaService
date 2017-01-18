package Data;

public class CinemaSession {
	private String filmId;
    private String Date;
    private String time;
    private String price;
    private String videoHallNum;
    private String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return Date;
    }

    public String getFilmId() {
        return filmId;
    }

    public void setDate(String date) {
        Date = date;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }

    public String getPrice() {
        return price;
    }

    public String getVideoHallNum() {
        return videoHallNum;
    }

    public String getTime() {
        return time;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setVideoHallNum(String videoHallNum) {
        this.videoHallNum = videoHallNum;
    }
}
