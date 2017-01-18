package Data;

public class Seat {
	public final static int ORDINARY_SEAT = 1;
    public final static int LOVERS_SEAT = 2;
    public final static int NONE_SEAT = 3;

    public final static int STATE_EMPTY_SEAT = 4;
    public final static int STATE_CHOSED_SEAT = 5;
    public final static int STATE_CHOOSING_SEAT =6;

    private int seat_type;
    private int seat_state;
    private int seatNum;

    public Seat() {
        super();
        seat_type = NONE_SEAT;
        seat_state = STATE_EMPTY_SEAT;
    }

    public void setSeat_type(int type){seat_type = type;}
    public int getSeat_type(){return seat_type;}
    public void setSeat_state(int state){seat_state = state;}
    public int getSeat_state(){return seat_state;}
    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }
    public int getSeatNum() {
        return seatNum;
    }
}
