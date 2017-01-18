package Data;

import java.util.List;

public class CinemaSeat {
	 private List<Aseat> seatList;
     private int coulmns;
     private int row;

     public CinemaSeat() {
         super();
         coulmns = 0;
         row = 0;
     }

     public List<Aseat> getSeatList(){return seatList;}
     public void setSeatList(List<Aseat> list){seatList = list;}
     public int getCoulmns(){return coulmns;}
     public void setCoulmns(int coulmns){this.coulmns = coulmns;}
     public int getRow(){return row;}
     public void setRow(int row){this.row = row;}
}
