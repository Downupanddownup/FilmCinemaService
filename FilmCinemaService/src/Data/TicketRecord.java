package Data;

import java.util.ArrayList;

public class TicketRecord {
	private String filmName;
    private String filmId;
    private String cinemaName;
    private String date;
    private String videoHall;
    private String time;
    private ArrayList<Integer> tickets;
    private String buyTime;

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public void setFilmId(String filmId) {
        this.filmId = filmId;
    }

    public String getFilmName() {
        return filmName;
    }

    public String getFilmId() {
        return filmId;
    }

    public ArrayList<Integer> getTickets() {
        return tickets;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public String getDate() {
        return date;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getVideoHall() {
        return videoHall;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBuyTime() {
        return buyTime;
    }

    public String getTime() {
        return time;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }

    public void setTickets(ArrayList<Integer> tickets) {
        this.tickets = tickets;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setVideoHall(String videoHall) {
        this.videoHall = videoHall;
    }
}
