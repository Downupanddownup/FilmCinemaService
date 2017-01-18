package Data;

public class Aseat {
    public final static int STATE_EMPTY_SEAT = 4;
    public final static int STATE_CHOSED_SEAT = 5;
    public final static int STATE_CHOOSING_SEAT =6;
	public int id;
	public int seatCoulmns;
	public int seatRow;
	public int seatType;
	public int seatState;
    public int lovers_locat;
	public Aseat(){
		seatState=STATE_EMPTY_SEAT;
	}
}
