package servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Data.Actors;
import Data.Aseat;
import Data.Cinema;
import Data.CinemaDetail;
import Data.CinemaFilm;
import Data.CinemaSeat;
import Data.CinemaSession;
import Data.Collection;
import Data.Critic;
import Data.Director;
import Data.FilmList;
import Data.FinforFilm;
import Data.HomePageFilm;
import Data.MyCritic;
import Data.Result;
import Data.Seat;
import Data.TicketRecord;
import DataBase.ActorTable;
import DataBase.CinemaFilmShowTable;
import DataBase.CinemaTable;
import DataBase.CityTable;
import DataBase.CollectionTable;
import DataBase.CriticTable;
import DataBase.DirectorTable;
import DataBase.FilmActorTable;
import DataBase.FilmTable;
import DataBase.PraiseTable;
import DataBase.SeatTable;
import DataBase.TicketRecordTable;
import DataBase.TicketSeatTable;
import DataBase.UserTable;
import DataBase.VideoHallSeatTable;
/*
 * 4张热门电影海报
 * 10份今日热映电影
 * 10份即将上映电影
 * 20份正在上映的电影
 * 20份即将上映的电影
 * 20家影院
 * 20家按距离远近排序的影院
 * 40份电影详情，剧情简介，演员列表，导演和影评列表
 * 每家影院5部正在上映的电影，3部即将上映的电影排片，每天每厅7场，9点开始，11点结束
 * 每部电影自上映后五天内，在每家影院均有排片
 * 
 * 
 * 
 * 
 * */
public class ConnectToSql {
	
	/*
	 * 编写并导入电影数据
	 * */
	public static void importFilmData(){
		ArrayList<FilmTable> ut = new ArrayList<FilmTable>();
		for (int i = 1; i < 41; ++i) {
			FilmTable tem = new FilmTable();
			tem.id = i + "";
			tem.name = "电影名" + i;
			tem.posterPath = i+"";
			tem.scord = i+0.5f;
			tem.directorId = i+"";
			tem.type = "类型"+i;
			tem.language = "语言"+i;
			tem.country = "国家"+i;
			tem.time = i%2==0?"110":"120";
			tem.plot = "剧情"+i;
			tem.date = getDate(i);
		
			ut.add(tem);
		}

		Connection conn = getConnection();
		String sql = "insert into FilmTable (id,name,posterPath,scord,directorId,"
				+ "type,language,country,time,plot,date) values(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			for (FilmTable tem : ut) {
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				pstmt.setString(1, tem.id);
				pstmt.setString(2, tem.name);
				pstmt.setString(3, tem.posterPath);
				pstmt.setFloat(4, tem.scord);
				pstmt.setString(5, tem.directorId);
				pstmt.setString(6, tem.type);
				pstmt.setString(7, tem.language);
				pstmt.setString(8, tem.country);
				pstmt.setString(9, tem.time);
				pstmt.setString(10, tem.plot);
				pstmt.setString(11, tem.date);

				pstmt.executeUpdate();
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private static String getDate(int i){
		String date = "";
		if(i<5){
			date = "20161211";
		}else if(i < 9){
			date = "20161212";
		}else if(i<13){
			date = "20161213";
		}else if(i<17){
			date = "20161214";
		}else if(i<21){
			date = "20161215";
		}else if(i<25){
			date = "20161216";
		}else if(i<29){
			date = "20161217";
		}else if(i<33){
			date = "20161218";
		}else if(i<37){
			date = "20161219";
		}else{
			date = "20161220";
		}
		return date;
	}
	public static void importUserData() {
		ArrayList<UserTable> ut = new ArrayList<UserTable>();
		for (int i = 1; i < 21; ++i) {
			UserTable tem = new UserTable();
			tem.id = i + "";
			tem.name = "用户名" + i;
			tem.password = "123456";
			tem.phone = "10086";
			tem.sex = i % 2 == 0 ? "女" : "男";
			tem.portraitPath = i + "";
			ut.add(tem);
		}

		Connection conn = getConnection();
		String sql = "insert into UserTable (id,name,password,sex,phone,"
				+ "portraitPath) values(?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			for (UserTable tem : ut) {
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				pstmt.setString(1, tem.id);
				pstmt.setString(2, tem.name);
				pstmt.setString(3, tem.password);
				pstmt.setString(4, tem.sex);
				pstmt.setString(5, tem.phone);
				pstmt.setString(6, tem.portraitPath);
				pstmt.executeUpdate();
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void importTicketSeatData() {
		ArrayList<TicketSeatTable> ut = new ArrayList<TicketSeatTable>();
		ArrayList<TicketRecordTable> ticketNum = getTicketNum();
		for (TicketRecordTable t:ticketNum) {
			for (int i = 0; i < t.seatSum; ++i) {
				TicketSeatTable tem = new TicketSeatTable();
				tem.ticketNum = t.TicketNum;
				tem.seatId = i*t.seatSum*5%80+1+"";
				
				ut.add(tem);
			}
		}

		Connection conn = getConnection();
		String sql = "insert into TicketSeatTable (ticketNum,"
				+ "seatId) values(?,?)";
		PreparedStatement pstmt = null;
		try {
			for (TicketSeatTable tem : ut) {
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				pstmt.setString(1, tem.ticketNum);
				pstmt.setString(2, tem.seatId);

				pstmt.executeUpdate();
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static ArrayList<TicketRecordTable> getTicketNum(){
		ArrayList<TicketRecordTable> ticketNum = new ArrayList<TicketRecordTable>();
		
		Connection conn = getConnection();
	    String sql = "select distinct ticketNum,ticketSum from TicketRecordTable";
	    PreparedStatement pstmt = null;
	    try {
	    	
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					TicketRecordTable tem = new TicketRecordTable();
					tem.TicketNum = rs.getString("ticketNum");
					tem.seatSum = rs.getInt("ticketSum");
					ticketNum.add(tem);
				}
	    
	    	conn.close();
	    	pstmt.close();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
		return ticketNum;
	}
	
	
	public static void importTicketRecordData() {
		ArrayList<TicketRecordTable> ut = new ArrayList<TicketRecordTable>();
		ArrayList<CinemaFilmShowTable> cfst = new ArrayList<CinemaFilmShowTable>();
		ArrayList<CinemaFilmShowTable> showingt = new ArrayList<CinemaFilmShowTable>();
		for (int i = 1; i < 21; ++i) {
			for (int j = 1; j < 11; ++j) {
				String cinemaId = (i+20*(j-1))%200+1+"";
				cfst = getFilms(cinemaId);
				for(int t =1;t<3;++t){
					TicketRecordTable tem = new TicketRecordTable();
					tem.userId = i + "";
					tem.cinemaId = cinemaId;
				
					tem.filmId = cfst.get((i+4*(t-1))%8).filmId;
					showingt = getShowingId(cinemaId,tem.filmId);
					tem.showingId = showingt.get(t*2%showingt.size()).id;
					tem.buyTime = "购买时间"+i+j;
					tem.TicketNum = tem.userId+tem.cinemaId+tem.filmId+tem.buyTime;
					tem.ticketSum = (i + j)%3+1;
					ut.add(tem);	
				}
				
			}
		}

		Connection conn = getConnection();
		String sql = "insert into TicketRecordTable (userId,cinemaId,filmId,showingId,buyTime,"
				+ "TicketNum,ticketSum) values(?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			for (TicketRecordTable tem : ut) {
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				pstmt.setString(1, tem.userId);
				pstmt.setString(2, tem.cinemaId);
				pstmt.setString(3, tem.filmId);
				pstmt.setString(4, tem.showingId);
				pstmt.setString(5, tem.buyTime);
				pstmt.setString(6, tem.TicketNum);
				pstmt.setInt(7, tem.ticketSum);
				pstmt.executeUpdate();
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private static ArrayList<CinemaFilmShowTable> getFilms(String cinemaId){
		ArrayList<CinemaFilmShowTable> c = new ArrayList<CinemaFilmShowTable>();
		Connection conn = getConnection();
	    String sql = "select distinct filmId from CinemaFilmShowTable where cinemaId='"+cinemaId+"'";
	    PreparedStatement pstmt = null;
	    try {

				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					CinemaFilmShowTable tem = new CinemaFilmShowTable();
					tem.filmId = rs.getString("filmId");
					c.add(tem);
				}
	    	conn.close();
	    	pstmt.close();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return c;
	}
	
	private static ArrayList<CinemaFilmShowTable> getShowingId(String cinemaId,String filmId){
		ArrayList<CinemaFilmShowTable> c = new ArrayList<CinemaFilmShowTable>();
		Connection conn = getConnection();
	    String sql = "select distinct id from CinemaFilmShowTable where cinemaId='"
	    		+cinemaId+"' and filmId ='"+filmId+"'";
	    PreparedStatement pstmt = null;
	    try {

				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					CinemaFilmShowTable tem = new CinemaFilmShowTable();
					tem.id = rs.getString("id");
					c.add(tem);
				}
	    	conn.close();
	    	pstmt.close();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return c;
	}
	
	
	public static void importCollectionData() {
		ArrayList<CollectionTable> ut = new ArrayList<CollectionTable>();
		for (int i = 1; i < 21; ++i) {
			for (int j = 1; j < 11; ++j) {
				CollectionTable tem = new CollectionTable();
				tem.userId = i + "";
				tem.filmId =(i+4*(j-1))%40+1+"";
				ut.add(tem);
			}
		}

		Connection conn = getConnection();
		String sql = "insert into CollectionTable (userId,"
				+ "filmId) values(?,?)";
		PreparedStatement pstmt = null;
		try {
			for (CollectionTable tem : ut) {
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				pstmt.setString(1, tem.userId);
				pstmt.setString(2, tem.filmId);

				pstmt.executeUpdate();
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void importPraiseData() {
		
		ArrayList<CriticTable> c = getCriticTable();
		
		
		ArrayList<PraiseTable> ut = new ArrayList<PraiseTable>();
		for (int i = 1; i < 21; ++i) {
			for (int j = 1; j < 6; ++j) {
				PraiseTable tem = new PraiseTable();
				tem.userId = i + "";
				tem.criticId = c.get((i+80*(j-1))%400).id;
				ut.add(tem);
			}
		}

		Connection conn = getConnection();
		String sql = "insert into PraiseTable (userId,"
				+ "criticId) values(?,?)";
		PreparedStatement pstmt = null;
		try {
			for (PraiseTable tem : ut) {
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				pstmt.setString(1, tem.userId);
				pstmt.setString(2, tem.criticId);
				pstmt.executeUpdate();
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static ArrayList<CriticTable> getCriticTable(){
		ArrayList<CriticTable> c = new ArrayList<CriticTable>();
		Connection conn = getConnection();
	    String sql = "select * from CriticTable";
	    PreparedStatement pstmt = null;
	    try {

				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					CriticTable tem = new CriticTable();
					tem.id = rs.getString("id");
					c.add(tem);
				}
	    	conn.close();
	    	pstmt.close();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return c;
	}
	
	public static void importActorData() {
		ArrayList<ActorTable> ut = new ArrayList<ActorTable>();
		for (int i = 1; i < 81; ++i) {
			ActorTable tem = new ActorTable();
			tem.id = i + "";
			tem.name = "演员名" + i;
			tem.portraitPath = i + "";
			ut.add(tem);
		}

		Connection conn = getConnection();
		String sql = "insert into ActorTable (id,name,"
				+ "portraitPath) values(?,?,?)";
		PreparedStatement pstmt = null;
		try {
			for (ActorTable tem : ut) {
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				pstmt.setString(1, tem.id);
				pstmt.setString(2, tem.name);
				pstmt.setString(3, tem.portraitPath);
				pstmt.executeUpdate();
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void importDirectorData() {
		ArrayList<DirectorTable> ut = new ArrayList<DirectorTable>();
		for (int i = 1; i < 41; ++i) {
			DirectorTable tem = new DirectorTable();
			tem.id = i + "";
			tem.name = "导演名" + i;
			tem.portraitPath = i +40+ "";
			ut.add(tem);
		}

		Connection conn = getConnection();
		String sql = "insert into DirectorTable (id,name,"
				+ "portraitPath) values(?,?,?)";
		PreparedStatement pstmt = null;
		try {
			for (DirectorTable tem : ut) {
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				pstmt.setString(1, tem.id);
				pstmt.setString(2, tem.name);
				pstmt.setString(3, tem.portraitPath);
				pstmt.executeUpdate();
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void importCriticData() {
		ArrayList<CriticTable> ut = new ArrayList<CriticTable>();
		for (int i = 1; i < 41; ++i) {
			for (int j = 1; j < 11; ++j) {
				CriticTable tem = new CriticTable();
				tem.filmId = i + "";
				tem.userId = (i+2*(j-1))%20+1+"";
				tem.content = "评价"+i+j;
				tem.praiseSum = (i+j)%10+"";
				tem.time = "时间"+i+j;
				tem.scord = (i+j)%20/2f;
				tem.id = tem.userId+tem.filmId+tem.time;
				ut.add(tem);
			}
		}

		Connection conn = getConnection();
		String sql = "insert into CriticTable (filmId,userId,content,praiseSum,time,scord,"
				+ "id) values(?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			for (CriticTable tem : ut) {
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				pstmt.setString(1, tem.filmId);
				pstmt.setString(2, tem.userId);
				pstmt.setString(3, tem.content);
				pstmt.setString(4, tem.praiseSum);
				pstmt.setString(5, tem.time);
				pstmt.setFloat(6, tem.scord);
				pstmt.setString(7, tem.id);
				pstmt.executeUpdate();
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void importFilmActorData() {
		ArrayList<FilmActorTable> ut = new ArrayList<FilmActorTable>();
		for (int i = 1; i < 41; ++i) {
			for (int j = 1; j < 11; ++j) {
				FilmActorTable tem = new FilmActorTable();
				tem.filmId = i + "";
				tem.actorId = (i+8*(j-1))%80+1+"";
				tem.role = "角色" + i+j;
				ut.add(tem);
			}
		}

		Connection conn = getConnection();
		String sql = "insert into FilmActorTable (filmId,actorId,"
				+ "role) values(?,?,?)";
		PreparedStatement pstmt = null;
		try {
			for (FilmActorTable tem : ut) {
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				pstmt.setString(1, tem.filmId);
				pstmt.setString(2, tem.actorId);
				pstmt.setString(3, tem.role);
				pstmt.executeUpdate();
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void importCinemaDate() {
		ArrayList<CinemaTable> ut = new ArrayList<CinemaTable>();
		for (int i = 1; i < 201; ++i) {
			CinemaTable tem = new CinemaTable();
			tem.id = i + "";
			tem.name = "影院名" + i;
			tem.address = "弗雷德"+i+"号路";
			tem.phone = "10086"+i;
			tem.videoHallSum = 4;
			tem.jing = i + "公里";
			tem.wei = i + "";
			ut.add(tem);
		}

		Connection conn = getConnection();
		String sql = "insert into CinemaTable (id,name,address,phone,videoHallSum,"
				+ "jing,wei) values(?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			for (CinemaTable tem : ut) {
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				pstmt.setString(1, tem.id);
				pstmt.setString(2, tem.name);
				pstmt.setString(3, tem.address);
				pstmt.setString(4, tem.phone);
				pstmt.setInt(5, tem.videoHallSum);
				pstmt.setString(6, tem.jing);
				pstmt.setString(7, tem.wei);
				pstmt.executeUpdate();
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void importCinemaFilmShowData() {
		ArrayList<CinemaFilmShowTable> ut = new ArrayList<CinemaFilmShowTable>();
		for (int i = 1; i < 201; ++i) {
			ArrayList<Integer> films = new ArrayList<Integer>();
			for(int j= 1;j<7;++j){
				int f = (i+6*(j-1))%20+1;
				if(exist(films,f)) f++;
				films.add(f);
			}
			for(int j= 1;j<3;++j){
				int f = (i+8*(j-1))%20+21;
				if(exist(films,f)) f++;
				films.add(f);
			}
			
			for(int d = 1; d < 10;++d){
				ArrayList<Integer> showingFilms = new ArrayList<Integer>();
				showingFilms = getShowingFilms(films,d);
				if (showingFilms.size() != 0) {
					for (int vhn = 1; vhn < 5; ++vhn) {
						for (int t = 1; t < 8; ++t) {
							CinemaFilmShowTable tem = new CinemaFilmShowTable();
							tem.cinemaId = i + "";
							tem.filmId = showingFilms.get((vhn + t)
									% showingFilms.size())
									+ "";
							tem.date = 20161215 + d - 1 + "";
							tem.videoHallNum = vhn + "";
							tem.startTime = 9 + 2 * (t - 1) + ":00";
							tem.price = i%20+20+"";
							tem.id = tem.videoHallNum + tem.date
									+ tem.startTime;
							ut.add(tem);
						}
					}
				}
			}
			
		}

		Connection conn = getConnection();
		String sql = "insert into CinemaFilmShowTable (cinemaId,filmId,date,videoHallNum,startTime,"
				+ "price,id) values(?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			for (CinemaFilmShowTable tem : ut) {
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				pstmt.setString(1, tem.cinemaId);
				pstmt.setString(2, tem.filmId);
				pstmt.setString(3, tem.date);
				pstmt.setString(4, tem.videoHallNum);
				pstmt.setString(5, tem.startTime);
				pstmt.setString(6, tem.price);
				pstmt.setString(7, tem.id);
				pstmt.executeUpdate();
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static ArrayList<Integer> getShowingFilms(ArrayList<Integer> films,int date){
		ArrayList<Integer> sFilms = new ArrayList<Integer>();
		ArrayList<FilmTable> filmTable = new ArrayList<FilmTable>();
		
		
		Connection conn = getConnection();
	    String sql = "select * from FilmTable where id = '";
	    PreparedStatement pstmt = null;
	    try {
	    	
	    	for(int i=0;i<films.size();++i){
				pstmt = (PreparedStatement) conn.prepareStatement(sql+films.get(i)+"'");
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					FilmTable tem = new FilmTable();
					tem.id = rs.getString("id");
					tem.date = rs.getString("date");
					filmTable.add(tem);
				}
	    	}
	    	conn.close();
	    	pstmt.close();
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
		for(FilmTable f:filmTable){
			if(Integer.parseInt(f.date)+4>= 20161215+date-1 &&
					Integer.parseInt(f.date)<=20161215+date-1)
				sFilms.add(Integer.parseInt(f.id));
		}
		return sFilms;
	}
	
	private static boolean exist(ArrayList<Integer> films,int f){
		for(int tem:films){
			if(tem==f) return true;
		}
		return false;
	}
	
	public static void importRealSeatDate() {
		ArrayList<SeatTable> ut = new ArrayList<SeatTable>();
		for (int i = 1; i < 3; ++i) {
			if (i == 1) {
				for (int j = 1; j < 81; ++j) {
					SeatTable tem = new SeatTable();
					tem.id = i + "";
					tem.seatNum =j+"";
					tem.seatType = Seat.ORDINARY_SEAT+"";
					tem.seatCoulmns = j%10==0?"10":j%10+"";
					tem.seatRow = (j-1)/10+"";
					ut.add(tem);
				}
			} else {
				for (int j = 1; j < 121; ++j) {
					SeatTable tem = new SeatTable();
					tem.id = i + "";
					tem.seatNum =j+"";
					tem.seatType = Seat.ORDINARY_SEAT+"";
					tem.seatCoulmns = j%12==0?"12":j%12+"";
					tem.seatRow = (j-1)/12+"";
					ut.add(tem);
				}
			}
		}

		Connection conn = getConnection();
		String sql = "insert into RealSeatTable (id,seatNum,seatType,seatCoulmns,seatRow"
				+") values(?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			for (SeatTable tem : ut) {
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				pstmt.setString(1, tem.id);
				pstmt.setString(2, tem.seatNum);
				pstmt.setString(3, tem.seatType);
				pstmt.setString(4, tem.seatCoulmns);
				pstmt.setString(5, tem.seatRow);
			
				pstmt.executeUpdate();
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void importVideoHallSeatData() {
		ArrayList<VideoHallSeatTable> ut = new ArrayList<VideoHallSeatTable>();
		for (int i = 1; i < 201; ++i) {
		
			for (int j = 1; j < 5; ++j) {
				VideoHallSeatTable tem = new VideoHallSeatTable();
				tem.cinemaId = i + "";
				tem.videoHallNum = j+"";
				tem.seatId = (i+j)%2+1+"";
				ut.add(tem);
			}

		}

		Connection conn = getConnection();
		String sql = "insert into VideoHallSeatTable (cinemaId,videoHallNum,seatId"
				+ ") values(?,?,?)";
		PreparedStatement pstmt = null;
		try {
			for (VideoHallSeatTable tem : ut) {
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				pstmt.setString(1, tem.cinemaId);
				pstmt.setString(2, tem.videoHallNum);
				pstmt.setString(3, tem.seatId);
		
				pstmt.executeUpdate();
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/////////////////////////////////////////////////////////////////////
  	public static Connection getConnection(){
		Connection dbConn = null;
		String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=FilmCinemaServiceData";
		String userName="sa";
		String userPwd="123456789";
		try
		{
			Class.forName(driverName);
		    dbConn=DriverManager.getConnection(dbURL,userName,userPwd);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		     
		}
		return dbConn;
	}
	public static ArrayList<HomePageFilm> getNowShowingData_HomepageFilm(){
		Connection conn = getConnection();
	    String sql = "select date,id,posterPath,scord,name from FilmTable";///
	    PreparedStatement pstmt;
	    ArrayList<HomePageFilm> content = new ArrayList<HomePageFilm>();///
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        int sum =0;
			while (rs.next()) {
				int date = Integer.parseInt(rs.getString("date"));
				if (date<=20161224) {
					if(sum>9)break;
					HomePageFilm tem = new HomePageFilm();// /

					tem.setId(rs.getString("id"));
					tem.setName(rs.getString("name"));
					tem.setPosterPath(rs.getString("posterPath"));
					tem.setScord(rs.getFloat("scord") + "");
					content.add(tem);
					sum++;
				}
			} 
	    conn.close();
	    pstmt.close();     
	            
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
		return content;
	}
	
	public static ArrayList<HomePageFilm> getUpcomingData_HomepageFilm(){
		Connection conn = getConnection();
	    String sql = "select date,id,posterPath,scord,name from FilmTable";///
	    PreparedStatement pstmt;
	    ArrayList<HomePageFilm> content = new ArrayList<HomePageFilm>();///
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	       int sum =0 ;
	       while(rs.next()){
	    	   int date = Integer.parseInt(rs.getString("date"));
				if (date > 20161224) {
					if(sum>9)break;
					HomePageFilm tem = new HomePageFilm();// /

					tem.setId(rs.getString("id"));
					tem.setName(rs.getString("name"));
					tem.setScord(rs.getFloat("scord")+"");
					tem.setDate(rs.getString("date"));
					tem.setPosterPath(rs.getString("posterPath"));
					content.add(tem);
					++sum;
	    	    }
	    	}
	       	       
	    conn.close();
	    pstmt.close();     
	            
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
		return content;
	}
	public static ArrayList<FilmList> getBigPosters_HomePageFilm(){
		Connection conn = getConnection();
	    String sql = "select id,posterPath from FilmTable";///
	    PreparedStatement pstmt;
	    ArrayList<FilmList> content = new ArrayList<FilmList>();///
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	       int sum=0;
	       int i=0;
	       while(rs.next()){
	    	   if(sum>3)break;
	    	   i++;
	    	   if(i%4!=0)continue;
	    	   FilmList tem = new FilmList();///
	    	   	
	    	   	tem.setId(rs.getString("id"));
	    	   	tem.setposterPath(rs.getString("posterPath"));
	    	  
	    	   	content.add(tem);
	    	   	sum++;
	       }
	       	       
	    conn.close();
	    pstmt.close();     
	            
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
		return content;
	}
	public static ArrayList<FilmList> getNowShowingData_FilmList(String start){
		Connection conn = getConnection();
	    String sql = "select date,id,name,posterPath,scord from FilmTable";///
	    PreparedStatement pstmt;
	    ArrayList<FilmList> content = new ArrayList<FilmList>();///
	    int sum = 0;
	    int i = 0;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	       
			while (rs.next()) {
				int date = Integer.parseInt(rs.getString("date"));
				if(date >20161224) continue;
				if (i >= Integer.parseInt(start)) {
					if (sum > 9)
						break;
					FilmList tem = new FilmList();// /

					tem.setId(rs.getString("id"));
					tem.setposterPath(rs.getString("posterPath"));
					tem.setName(rs.getString("name"));
					tem.setScord(rs.getFloat("scord")+"");
					tem.setCinemaNum(getCinema(rs.getString("id")));
					tem.setShowingTimes(getShowingTimes(rs.getString("id")));
					content.add(tem);
					
					sum++;
				}i++;
			}
	       	       
	    conn.close();
	    pstmt.close();     
	            
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
		return content;
	}
	private static String getCinema(String filmId){
		Connection conn = getConnection();
		PreparedStatement p =null;
		int sum = 0 ;
		String sql = "select distinct cinemaId from CinemaFilmShowTable where filmId='"+filmId+"'";
		try{
			p = conn.prepareStatement(sql);
			ResultSet rs = p.executeQuery();
			
			while(rs.next())sum++;
			conn.close();
			p.close();
		}catch(Exception e){
			
		}
		return sum+"";
	}

	private static String getShowingTimes(String filmId) {
		Connection conn = getConnection();
		PreparedStatement p = null;
		int sum=0;
		String sql = "select distinct count(*) as 'sum' from CinemaFilmShowSessionTable,CinemaFilmShowTable where CinemaFilmShowSessionTable.showingId=CinemaFilmShowTable.showingId "
				+ "and filmId='"+filmId + "'";
		try {
			p = conn.prepareStatement(sql);
			ResultSet rs = p.executeQuery();

			while (rs.next()){
				sum=rs.getInt("sum");
			}
				
			conn.close();
			p.close();
		} catch (Exception e) {

		}
		return sum + "";
	}
	
	public static ArrayList<FilmList> getUpcomingData_FilmList(String start){
		Connection conn = getConnection();
	    String sql = "select id,name,posterPath,scord,date,type from FilmTable";///
	    PreparedStatement pstmt;
	    ArrayList<FilmList> content = new ArrayList<FilmList>();///
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	       int i = 0;
	       int sum = 0;
	       while(rs.next()){
	    	   int date= Integer.parseInt(rs.getString("date"));
	    	   if(date<=20161224)continue;
				if (i >= Integer.parseInt(start)) {
					if (sum > 9)
						break;
					FilmList tem = new FilmList();// /

					tem.setId(rs.getString("id"));
					tem.setposterPath(rs.getString("posterPath"));
					tem.setName(rs.getString("name"));
					tem.setScord(rs.getFloat("scord")+"");
					tem.setDate(rs.getString("date"));
					tem.setType(rs.getString("type"));
					content.add(tem);
					
					++sum;
	    	   }
				++i;
	       }
	       	       
	    conn.close();
	    pstmt.close();     
	            
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
		return content;
	}
	
	public static ArrayList<FilmList> getSearchFilm(String filmNameLike,String start){
		Connection conn = getConnection();
	    String sql = "select id,name,posterPath,scord,date,type from FilmTable where name like '%"+filmNameLike+"%'";///
	    PreparedStatement pstmt;
	    ArrayList<FilmList> content = new ArrayList<FilmList>();///
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	       int i = 0;
	       int sum = 0;
	       while(rs.next()){
	    	   int date= Integer.parseInt(rs.getString("date"));
	    	   if(date<=20161215)continue;
				if (i >= Integer.parseInt(start)) {
					if (sum > 9)
						break;
					FilmList tem = new FilmList();// /

					tem.setId(rs.getString("id"));
					tem.setposterPath(rs.getString("posterPath"));
					tem.setName(rs.getString("name"));
					tem.setScord(rs.getFloat("scord")+"");
					tem.setDate(rs.getString("date"));
					tem.setType(rs.getString("type"));
					content.add(tem);
					
					++sum;
	    	   }
				++i;
	       }
	       	       
	    conn.close();
	    pstmt.close();     
	            
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
		return content;
	}
	
	
	public static ArrayList<Cinema> getSearchCinema(String cinemaNameLike,String cityId,String start){
		Connection conn = getConnection();
	    String sql = "select id,name,address,jing from CinemaTable where name like '%"+cinemaNameLike+"%'";///
	    PreparedStatement pstmt;
	    ArrayList<Cinema> content = new ArrayList<Cinema>();///
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	       int i=0;
	       int sum=0;
	       while(rs.next()){
				if (i >= Integer.parseInt(start)) {
					if (sum > 9)
						break;
					Cinema tem = new Cinema();// /

					tem.setId(rs.getString("id"));
					tem.setName(rs.getString("name"));
					tem.setlPrice(getLprice(tem.getId()));
					tem.setAddress(rs.getString("address"));
					tem.setDistance(rs.getString("jing"));
					content.add(tem);
					sum++;
	    	   }
	    	   ++i;
	       }
	       	       
	    conn.close();
	    pstmt.close();     
	            
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
		return content;
	}
	
	
	
	public static ArrayList<Cinema> getAllCity_CinemaList_NoSpecial(String cityId,String start){
		Connection conn = getConnection();
	    String sql = "select id,name,address,jing from CinemaTable";///
	    PreparedStatement pstmt;
	    ArrayList<Cinema> content = new ArrayList<Cinema>();///
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	       int i=0;
	       int sum=0;
	       while(rs.next()){
				if (i > Integer.parseInt(start)) {
					if (sum > 9)
						break;
					Cinema tem = new Cinema();// /

					tem.setId(rs.getString("id"));
					tem.setName(rs.getString("name"));
					tem.setlPrice(getLprice(tem.getId()));
					tem.setAddress(rs.getString("address"));
					tem.setDistance(rs.getString("jing"));
					content.add(tem);
					sum++;
	    	   }
	    	   ++i;
	       }
	       	       
	    conn.close();
	    pstmt.close();     
	            
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
		return content;
	}
	
	private static String getLprice(String cinemaId) {
		Connection conn = getConnection();
		PreparedStatement p = null;
		int price = 0;
		String sql = "select Min(price) as'price' from CinemaFilmShowTable where cinemaId='"
				+ cinemaId + "'";
		try {
			p = conn.prepareStatement(sql);
			ResultSet rs = p.executeQuery();

			while (rs.next())
				price=rs.getInt("price");
			conn.close();
			p.close();
		} catch (Exception e) {

		}
		return price + "";
	}
	
	
	public static ArrayList<Cinema> getNearby_CinemaList_NoSpecial
				(String cityId,String jing,String wei,String start){
		Connection conn = getConnection();
	    String sql = "select id,name,address,jing from CinemaTable order by jing";///
	    PreparedStatement pstmt;
	    ArrayList<Cinema> content = new ArrayList<Cinema>();///
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	       int i=0;
	       int sum=0;
	       while(rs.next()){
				if (i >= Integer.parseInt(start)) {
					if (sum > 9)
						break;
					Cinema tem = new Cinema();// /

					tem.setId(rs.getString("id"));
					tem.setName(rs.getString("name"));
					tem.setlPrice(getLprice(tem.getId()));
					tem.setAddress(rs.getString("address"));
					tem.setDistance(rs.getString("jing"));
					content.add(tem);
					sum++;
	    	   }
	    	   ++i;
	       }
	    /*ArrayList<Cinema> result = new ArrayList<Cinema>();
	    while(content.size()!=0){
	    	int l = findMin(content);
	    	result.add(content.get(l));
	    	content.remove(l);
	    }*/
	   // content = result;
	    conn.close();
	    pstmt.close();     
	            
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
		return content;
	}
	
	private static int findMin(ArrayList<Cinema> result){
		int l=0;
		int min=Integer.parseInt(result.get(0).getlPrice());
		for(int i=1;i<result.size();++i){
			if(Integer.parseInt(result.get(i).getlPrice())<min){
				l=0;
				min=Integer.parseInt(result.get(i).getlPrice());
			}
		}
		return l;
	}
	
 	public static ArrayList<TicketRecord> getTicketRecorder(String userId,String start){
		Connection conn = getConnection();
	    String sql = "select filmId,cinemaId,buyTime,ticketNum,sessionId from TicketRecordTable,CinemaFilmShowTable " +
	    		"Where TicketRecordTable.showingId=CinemaFilmShowTable.showingId and userId='"+userId+"'";///
	    PreparedStatement pstmt;
	    ArrayList<TicketRecord> content = new ArrayList<TicketRecord>();///
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	       int i=0;
	       int sum=0;
	       while(rs.next()){
				if (i >= Integer.parseInt(start)) {
					if (sum > 9)
						break;
					TicketRecord tem = new TicketRecord();// /

					tem.setFilmId(rs.getString("filmId"));
					tem.setFilmName(getFilmName(tem.getFilmId()));
					tem.setCinemaName(getCinemaName(rs.getString("cinemaId")));
					tem.setDate(getDate(rs.getString("cinemaId"),
							tem.getFilmId(),rs.getString("sessionId")));
					tem.setVideoHall(getVideoHallNum(rs.getString("cinemaId"),
							tem.getFilmId(),rs.getString("sessionId")));
					tem.setTime(getStartTime(rs.getString("cinemaId"),
							tem.getFilmId(),rs.getString("sessionId")));
					tem.setBuyTime(rs.getString("buyTime"));
					tem.setTickets(getTickets(rs.getString("ticketNum")));

					content.add(tem);
					sum++;
	    	   }++i;
	       }
	       	       
	    conn.close();
	    pstmt.close();     
	            
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
		return content;
	}
 	private static String getFilmName(String filmId){
 		Connection conn = getConnection();
		PreparedStatement p = null;
		String  name="";
		String sql = "select name from FilmTable where id='"
				+ filmId + "'";
		try {
			p = conn.prepareStatement(sql);
			ResultSet rs = p.executeQuery();

			while (rs.next())
				name=rs.getString("name");
			conn.close();
			p.close();
		} catch (Exception e) {

		}
		return name;
 	}
 	
 	private static String getCinemaName(String cinemaId){
 		Connection conn = getConnection();
		PreparedStatement p = null;
		String  name="";
		String sql = "select name from CinemaTable where id='"
				+ cinemaId + "'";
		try {
			p = conn.prepareStatement(sql);
			ResultSet rs = p.executeQuery();

			while (rs.next())
				name=rs.getString("name");
			conn.close();
			p.close();
		} catch (Exception e) {

		}
		return name;
 	}
 	
 	private static String getDate(String cinemaId,String filmId,String sessionId){
 		Connection conn = getConnection();
		PreparedStatement p = null;
		String  date="";
		String sql = "select date,sessionId from CinemaFilmShowSessionTable,CinemaFilmShowTable where CinemaFilmShowSessionTable.showingId=CinemaFilmShowTable.showingId and "
				+"cinemaId='"+cinemaId+"' and filmId='"+filmId+"' and sessionId='"+sessionId+"'";
		try {
			p = conn.prepareStatement(sql);
			ResultSet rs = p.executeQuery();

			while (rs.next())
				date=rs.getString("date");
			conn.close();
			p.close();
		} catch (Exception e) {

		}
		return date;
 	}
 	
 	private static String getVideoHallNum(String cinemaId,String filmId,String sessionId){
 		Connection conn = getConnection();
		PreparedStatement p = null;
		String  tem="";
		String sql = "select videoHallNum from CinemaFilmShowSessionTable,CinemaFilmShowTable where CinemaFilmShowSessionTable.showingId=CinemaFilmShowTable.showingId and "
				+"cinemaId='"+cinemaId+"' and filmId='"+filmId+"' and sessionId='"+sessionId+"'";
		try {
			p = conn.prepareStatement(sql);
			ResultSet rs = p.executeQuery();

			while (rs.next())
				tem=rs.getString("videoHallNum");
			conn.close();
			p.close();
		} catch (Exception e) {

		}
		return tem;
 	}
 	
 	private static String getStartTime(String cinemaId,String filmId,String sessionId){
 		Connection conn = getConnection();
		PreparedStatement p = null;
		String  tem="";
		String sql = "select startTime from CinemaFilmShowSessionTable,CinemaFilmShowTable where CinemaFilmShowSessionTable.showingId=CinemaFilmShowTable.showingId and "
				+"cinemaId='"+cinemaId+"' and filmId='"+filmId+"' and sessionId='"+sessionId+"'";
		try {
			p = conn.prepareStatement(sql);
			ResultSet rs = p.executeQuery();

			while (rs.next())
				tem=rs.getString("startTime");
			conn.close();
			p.close();
		} catch (Exception e) {

		}
		return tem;
 	}
 	
 	private static ArrayList<Integer> getTickets(String ticketNum){
 		Connection conn = getConnection();
		PreparedStatement p = null;
		ArrayList<Integer> t = new ArrayList<Integer>();
		String sql = "select seatNum from TicketSeatTable where ticketNum='"
				+ ticketNum + "'";
		try {
			p = conn.prepareStatement(sql);
			ResultSet rs = p.executeQuery();

			while (rs.next())
				t.add(Integer.parseInt(rs.getString("seatNum")));
			conn.close();
			p.close();
		} catch (Exception e) {

		}
		return t;
 	}
 	
	public static ArrayList<MyCritic> getMycritic(String userId,String start){
		Connection conn = getConnection();
	    String sql = "select filmId,scord,id,content,time,praiseSum from CriticTable where " +
	    		"userId='"+userId+"'";///
	    PreparedStatement pstmt;
	    ArrayList<MyCritic> content = new ArrayList<MyCritic>();///
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	       int i=0;
	       int sum=0;
	       while(rs.next()){
				if (i >= Integer.parseInt(start)) {
					if(sum>9)break;
					MyCritic tem = new MyCritic();// /

					tem.setFilmId(rs.getString("filmId"));
					tem.setFilmName(getFilmName(tem.getFilmId()));
					tem.setScord(rs.getFloat("scord"));
					tem.setDate(rs.getString("time"));
					tem.setPraise(rs.getString("praiseSum"));
					tem.setContent(rs.getString("content"));
					tem.setPraise(isPraised(userId,rs.getString("id")));
					content.add(tem);
					sum++;
				}++i;
	       }
	       	       
	    conn.close();
	    pstmt.close();     
	            
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
		return content;
	}
	
	private static boolean isPraised(String userId,String criticId){
		Connection conn = getConnection();
		PreparedStatement p = null;
		boolean  tem= false;
		String sql = "select criticId from PraiseTable where userId='"
				+ userId + "'";
		try {
			p = conn.prepareStatement(sql);
			ResultSet rs = p.executeQuery();

			while (rs.next())
				if(criticId.equals(rs.getString("startTime"))) tem=true;
			conn.close();
			p.close();
		} catch (Exception e) {

		}
		return tem;
	}
	
 	public static ArrayList<Collection> getCollection(String userId,String start){
		Connection conn = getConnection();
	    String sql = "SELECT FilmTable.name,FilmTable.id,posterPath,scord,type,DirectorTable.name as 'directorName',time "+
	    		"FROM CollectionTable,FilmTable,DirectorTable "+
	    		"WHERE CollectionTable.filmId=FilmTable.id AND FilmTable.directorId=DirectorTable.id AND CollectionTable.userId=" +
	    		"'"+userId+"'";///
	    PreparedStatement pstmt;
	    ArrayList<Collection> content = new ArrayList<Collection>();///
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        int i=0;
	        int sum=0;
			while (rs.next()) {
				if (i >= Integer.parseInt(start)) {
					if (sum > 9)
						break;
					Collection tem = new Collection();// /

					tem.setId(rs.getString("id"));
					tem.setName(rs.getString("name"));
					tem.setScord(rs.getFloat("scord")+"");
					tem.setDirector(rs.getString("directorName"));
					tem.setType(rs.getString("type"));
					tem.setTime(rs.getString("time"));
					tem.setPosterId(rs.getString("posterPath"));

					content.add(tem);
					sum++;
				}
				++i;
			}
	       	       
	    conn.close();
	    pstmt.close();     
	            
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
		return content;
	}
	public static FinforFilm getFinfor_FilmDetail(String filmId){
		Connection conn = getConnection();
	    String sql = "SELECT name,type,posterPath,scord,time,date from FilmTable where id = '"+filmId+"'";///
	    PreparedStatement pstmt;
	    FinforFilm content = new FinforFilm();///
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	       
	       while(rs.next()){
	    	   FinforFilm tem = new FinforFilm();///
	    	   	
	    	   	tem.setName(rs.getString("name"));
	    	   	tem.setPosterName(rs.getString("posterPath"));
	    	   	tem.setType(rs.getString("type"));
	    	   	tem.setScord(rs.getFloat("scord")+"");
	    	   	tem.setDate(rs.getString("date"));
	    	   	tem.setTime(rs.getString("time"));
	    	   	content = tem;
	       }
	       	       
	    conn.close();
	    pstmt.close();     
	            
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
		return content;
	}
	public static ArrayList<Actors> getActors_FilmDeatail(String filmId,String start){
		Connection conn = getConnection();
	    String sql = "select actorId,name,portraitPath,role from FilmActorTable,ActorTable where FilmActorTable.actorId=ActorTable.id "+
	    		"and filmId='"+filmId+"'";///
	    PreparedStatement pstmt;
	    ArrayList<Actors> content = new ArrayList<Actors>();///
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
			int i = 0;
			int sum = 0;
			while (rs.next()) {
				if (i >= Integer.parseInt(start)) {
					if (sum > 4)
						break;

					Actors tem = new Actors();// /

					tem.setId(rs.getString("actorId"));
					tem.setName(rs.getString("name"));
					tem.setPortraitId(rs.getString("portraitPath"));
					tem.setRole(rs.getString("role"));
					content.add(tem);
				}
				++i;
			}
    
	    conn.close();
	    pstmt.close();     
	            
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
		return content;
	}
	public static Director getDirector_FilmDeatil(String filmId){
		Connection conn = getConnection();
	    String sql = "select directorId,DirectorTable.name as 'directorName',portraitPath from FilmTable,DirectorTable where FilmTable.directorId=DirectorTable.id "+
	    		"and FilmTable.id='"+filmId+"'";///
	    PreparedStatement pstmt;
	    Director content = new Director();///
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	       
	       while(rs.next()){
	    	   Director tem = new Director();///
	    	   	
	    	   	tem.setId(rs.getString("directorId"));
	    	   	tem.setName(rs.getString("directorName"));
	    	   	tem.setPortraitId(rs.getString("portraitPath"));
	    	   	content = tem;
	       }
	       	       
	    conn.close();
	    pstmt.close();     
	            
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
		return content;
	}
	public static ArrayList<Critic> getCritic_FilmDeatil(String filmId,String start){
		Connection conn = getConnection();
	    String sql = "select userId,UserTable.name as 'userName',portraitPath,scord,time,praiseSum,content from CriticTable,UserTable where CriticTable.userId=UserTable.id "+
	    		"and filmId='"+filmId+"'";///
	    PreparedStatement pstmt;
	    ArrayList<Critic> content = new ArrayList<Critic>();///
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	       int i=0;
	       int sum=0;
	      
	       while(rs.next()){
	    	   if(i>=Integer.parseInt(start)){
	    		   if(sum>4)break;
	    	   Critic tem = new Critic();///
	    	   	
	    	   	tem.setId(rs.getString("userId"));
	    	   	tem.setName(rs.getString("userName"));
	    	   	tem.setDate(rs.getString("time"));
	    	   	tem.setContent(rs.getString("content"));
	    	   	tem.setPortraitId(rs.getString("portraitPath"));
	    	   	tem.setPraise(rs.getString("praiseSum"));
	    	   	tem.setScord(rs.getFloat("scord"));
	    	   	content.add(tem);
	    	   	sum++;
	    	   }++i;
	       }
	       	       
	    conn.close();
	    pstmt.close();     
	            
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
		return content;
	}
	public static ArrayList<Cinema> getAllCity_CinemaList_Special(String filmId,String cityId,String start){
		Connection conn = getConnection();
	    String sql = "select distinct CinemaTable.id as 'id',name,address,jing from CinemaTable,CinemaFilmShowTable where CinemaTable.id=CinemaFilmShowTable.cinemaId "+
	    		"and filmId='"+filmId+"'";///
	    PreparedStatement pstmt;
	    ArrayList<Cinema> content = new ArrayList<Cinema>();///
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
			int i = 0;
			int sum = 0;
			while (rs.next()) {
				if (i >= Integer.parseInt(start)) {
					if (sum > 9)
						break;
					Cinema tem = new Cinema();// /

					tem.setId(rs.getString("id"));
					tem.setName(rs.getString("name"));
					tem.setAddress(rs.getString("address"));
					tem.setDistance(rs.getString("jing"));
					tem.setlPrice(getLprice(tem.getId()));
					content.add(tem);
					sum++;
				}
				++i;
			}
	       	       
	    conn.close();
	    pstmt.close();     
	            
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
		return content;
	}
	public static ArrayList<Cinema> getNearby_CinemaList_Special
			(String filmId,String cityId,String jing,String wei,String start){
		Connection conn = getConnection();
	    String sql = "select distinct CinemaTable.id as'id',name,address,jing from CinemaTable,CinemaFilmShowTable where CinemaTable.id=CinemaFilmShowTable.cinemaId "+
	    		"and filmId='"+filmId+"' order by jing";//////
	    PreparedStatement pstmt;
	    ArrayList<Cinema> content = new ArrayList<Cinema>();///
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        int i = 0;
			int sum = 0;
			while (rs.next()) {
				if (i >= Integer.parseInt(start)) {
					if (sum > 9)
						break;
					Cinema tem = new Cinema();// /

					tem.setId(rs.getString("id"));
					tem.setName(rs.getString("name"));
					tem.setAddress(rs.getString("address"));
					tem.setDistance(rs.getString("jing"));
					tem.setlPrice(getLprice(tem.getId()));
					content.add(tem);
					sum++;
				}
				++i;
	       }
	       	       
	    conn.close();
	    pstmt.close();     
	            
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
		return content;
	}
	public static ArrayList<CinemaFilm> getFilm_CinemaDetail(String cinemaId){
		Connection conn = getConnection();
	    String sql = "select filmId,name,scord,posterPath from FilmTable,CinemaFilmShowTable where FilmTable.id=CinemaFilmShowTable.filmId and CinemaFilmShowTable.cinemaId=" +
	    		"'"+cinemaId+"'";///
	    PreparedStatement pstmt;
	    ArrayList<CinemaFilm> content = new ArrayList<CinemaFilm>();///
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	       
	       while(rs.next()){
	    	   CinemaFilm tem = new CinemaFilm();///
	    	   	
	    	   	tem.setId(rs.getString("filmId"));
	    	   	tem.setName(rs.getString("name"));
	    	   	tem.setScord(rs.getFloat("scord"));
	    	   	tem.setPosterId(rs.getString("posterPath"));
	    	   	content.add(tem);
	       }
	       	       
	    conn.close();
	    pstmt.close();     
	            
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
		return content;
	}
	public static CinemaDetail getFinfor_CinemaDetail(String cinemaId){
		Connection conn = getConnection();
	    String sql = "select name,phone,address from CinemaTable where id='"+cinemaId+"'";///
	    PreparedStatement pstmt;
	    CinemaDetail content = new CinemaDetail();///
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	       
	       while(rs.next()){
	    	   CinemaDetail tem = new CinemaDetail();///
	    	   	
	    	   	tem.setAddress(rs.getString("address"));
	    	   	tem.setName(rs.getString("name"));
	    	   	tem.setPhone(rs.getString("phone"));
	    	   	content = tem;
	       }
	       	       
	    conn.close();
	    pstmt.close();     
	            
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
		return content;
	}
	public static ArrayList<CinemaSession> getSession_CinemaDetail(String cinemaId,String filmId){
		Connection conn = getConnection();
	    String sql = "select date,videoHallNum,startTime,sessionId,price from CinemaFilmShowSessionTable,CinemaFilmShowTable where  "+
	    		"CinemaFilmShowSessionTable.showingId=CinemaFilmShowTable.showingId and CinemaFilmShowTable.cinemaId='"+
	    		cinemaId+"' and CinemaFilmShowTable.filmId='"+filmId+"'";///
	    PreparedStatement pstmt;
	    ArrayList<CinemaSession> content = new ArrayList<CinemaSession>();///
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	       
	       while(rs.next()){
	    	   CinemaSession tem = new CinemaSession();///
	    	   	
	    	   	tem.setDate(rs.getString("date"));
	    	   	tem.setTime(rs.getString("startTime"));
	    	   	tem.setPrice(rs.getString("price"));
	    	   	tem.setSessionId(rs.getString("sessionId"));
	    	   	tem.setVideoHallNum(rs.getString("videoHallNum"));

	    	   	content.add(tem);
	       }
	       	       
	    conn.close();
	    pstmt.close();     
	            
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
		return content;
	}
	public static CinemaSeat getSeat(String cinemaId,String filmId,String sessionId){
		Connection conn = getConnection();
	    String sql = "select seatNum,seatRow,seatCoulmns,seatType,rightOrleft from SeatTable,VideoHallSeatTable,CinemaFilmShowSessionTable,CinemaFilmShowTable "
	    +"where CinemaFilmShowSessionTable.showingId=CinemaFilmShowTable.showingId and VideoHallSeatTable.cinemaId = CinemaFilmShowTable.cinemaId "
	    +"and VideoHallSeatTable.videoHallSeatId = SeatTable.videoHallSeatId and VideoHallSeatTable.videoHallNum=CinemaFilmShowSessionTable.videoHallNum  "
	    +"and CinemaFilmShowTable.cinemaId='"+cinemaId+"' and CinemaFilmShowTable.filmId='"+filmId+"'and CinemaFilmShowSessionTable.sessionId='"+sessionId+"'";
	    PreparedStatement pstmt;
	    CinemaSeat content = new CinemaSeat();///
	    ArrayList<Aseat> t=new ArrayList<Aseat>();
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	       
	       while(rs.next()){
	    	   Aseat tem = new Aseat();///
	    	   	
	    	   	tem.id=rs.getInt("seatNum");
	    	   	tem.seatCoulmns=rs.getInt("seatCoulmns");
	    	   	tem.seatRow=rs.getInt("seatRow");
	    	   	tem.seatType=rs.getInt("seatType");
	    	   	tem.lovers_locat=rs.getInt("rightOrleft");
	    	   	t.add(tem);
	       }
	  //  
	    content.setRow(finmaxRow(t));
	    content.setCoulmns(finmaxCoulmns(t));
	    
	    sql="select seatNum from TicketRecordTable,TicketSeatTable,CinemaFilmShowTable where TicketRecordTable.ticketNum=TicketSeatTable.ticketNum  "+
	    "and CinemaFilmShowTable.showingId=TicketRecordTable.showingId and sessionId='"+sessionId+"' and filmId='"+filmId+"' and cinemaId='"+cinemaId+"'";
	    pstmt = (PreparedStatement)conn.prepareStatement(sql);
        rs = pstmt.executeQuery();
        while(rs.next()){
        	for(int i=0;i<t.size();++i){
        		if(t.get(i).id==rs.getInt("seatNum")){
        			t.get(i).seatState=Aseat.STATE_CHOSED_SEAT;
        		}
        	}
        }
        content.setSeatList(t);
	    conn.close();
	    pstmt.close();     
	            
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
		return content;
	}
	private static int finmaxRow(ArrayList<Aseat> t){
		int max=0;
		for(Aseat a:t){
			if(a.seatRow>max)max=a.seatRow;
		}
		return max+1;
	}
	private static int finmaxCoulmns(ArrayList<Aseat> t){
		int max=0;
		for(Aseat a:t){
			if(a.seatCoulmns>max)max=a.seatCoulmns;
		}
		return max;
	}
	
	public static ArrayList<CityTable> getCity(){
		Connection conn = getConnection();
	    String sql = "select id,name,firstLettle from CityTable";///
	    PreparedStatement pstmt;
	    ArrayList<CityTable> content = new ArrayList<CityTable>();///
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				CityTable tem = new CityTable();// /

				tem.id = rs.getString("id");
				tem.name = rs.getString("name");
				tem.firstLettle = rs.getString("firstLettle");
	
				content.add(tem);

			}
	       	       
	    conn.close();
	    pstmt.close();     
	            
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
		return content;
	}
	
	public static String getFilmPictrue(Service s,String pictureName){
		final String PATH = pixiv/";
        String path = PATH+pictureName+".jpg";
        if(s.getServletContext().getResourceAsStream(path) == null){
        	path = PATH +pictureName + ".png";
        }
        
        return path;
	}
	public static String getPortrait(Service s,String pictureName){
		final String PATH = "/WEB-INF/pixiv/";
        String path = PATH+pictureName+".jpg";
        if(s.getServletContext().getResourceAsStream(path) == null){
        	path = PATH +pictureName + ".png";
        }
        
        return path;
	}
	public static String getPlot(String filmId){
		Connection conn = getConnection();
	    String sql = "select plot from FilmTable where id='"+filmId+"'";///
	    PreparedStatement pstmt;
	    String content = new String();///
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	       
	       while(rs.next()){
	    	   	content = rs.getString("plot");
	       }
	       	       
	    conn.close();
	    pstmt.close();     
	            
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
		return content;
	}

	public static String createNewUserId(){
		
		Connection conn = getConnection();
	    String sql = "select count(*) as 'sum' from FilmTable";///
	    PreparedStatement pstmt;
	   String id="";///
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				id = rs.getInt("sum")+1+"";
			}
	       	       
	    conn.close();
	    pstmt.close();     
	            
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
        switch (id.length()){
            case 1:id="00"+id;
                break;
            case 2:id="0"+id;
                break;
        }
		return id;
	}
	///////////////////////////

	public static String getCriticId(String userId){
		Connection conn = getConnection();
		String sql = "select count(*) as'sum' from CriticTable where userId='"+userId+"'";
		PreparedStatement pstmt = null;
		String id=userId;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()){
				id+=rs.getInt("sum")+"";
			}
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	public static void postCritic(CriticTable table){
		Connection conn = getConnection();
		String sql = "insert into CriticTable (id,userId,filmId,scord,content,time,"
				+ "praiseSum) values(?,?,?,?,?,?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, table.id);
			pstmt.setString(2, table.userId);
			pstmt.setString(3, table.filmId);
			pstmt.setFloat(4, table.scord);
			pstmt.setString(5, table.content);
			pstmt.setString(6, table.time);
			pstmt.setString(7, table.praiseSum);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void postCollection(String userId,String filmId){
		Connection conn = getConnection();
		String sql = "insert into CollectionTable(userId,filmId"
				+ ") values(?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, filmId);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void postPraise(String userId,String criticId){
		Connection conn = getConnection();
		String sql = "insert into PraiseTable(userId,criticId"
				+ ") values(?,?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, criticId);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static int checkAndPostPhone(String userId,String phone){
		Result r = new Result();
		Connection conn = getConnection();
		String sql = "select count(*) as 'sum' from UserTable where phone='"+phone+"'";
		PreparedStatement pstmt = null;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				if(rs.getInt("sum")>0){
					r.setCode(Result.RESULT_EXIT_PHONE);
					break;
				}
			}
			if(r.getCode()!=Result.RESULT_EXIT_PHONE){
				sql = "update UserTable set phone='"+phone+"' where id='"+userId+"'";
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				pstmt.executeUpdate();
				r.setCode(Result.RESULT_OK);
			}
			
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r.getCode();
	}
	public static void postPassword(String userId,String pwd){
		Connection conn = getConnection();
		String sql = "update UserTable set password='"+pwd+"' where id='"+userId+"'";
		PreparedStatement pstmt = null;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static int checkAndPostUserFinfor(String userId,String userName,String sex){
		Result r = new Result();
		Connection conn = getConnection();
		String sql = "select count(name) as 'sum' from UserTable where name='"+userName+"'";
		PreparedStatement pstmt = null;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				if(rs.getInt("sum")>0){
					r.setCode(Result.RESULT_EXIT_NAME);
					break;
				}
			}
			if(r.getCode()!=Result.RESULT_EXIT_NAME){
				sql = "update UserTable set name='"+userName+"',sex='"+sex+"' where id='"+userId+"'";
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				pstmt.executeUpdate();
				r.setCode(Result.RESULT_OK);
			}
			
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r.getCode();
	}
	public static int checkAndPostNewUser(UserTable table){
		Result r = new Result();
		Connection conn = getConnection();
		String sql = "select count(phone) as 'sum' from UserTable where phone='"+table.phone+"'";
		PreparedStatement pstmt = null;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				if(rs.getInt("sum")>0){
					r.setCode(Result.RESULT_EXIT_PHONE);
					break;
				}
			}
			if(r.getCode()!=Result.RESULT_EXIT_PHONE){
				sql = "insert into UserTable(id,name,password,sex,phone,portraitPath) values(?,?,?,?,?,?)";
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				pstmt.setString(1, table.id);
				pstmt.setString(2, table.name);
				pstmt.setString(3, table.password);
				pstmt.setString(4, table.sex);
				pstmt.setString(5, table.phone);
				pstmt.setString(6, table.portraitPath);
				pstmt.executeUpdate();
				r.setCode(Result.RESULT_OK);
			}
			
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r.getCode();
	}
	public static UserTable checkAndPostLogin(String phone,String pwd){
		Result r = new Result();
		UserTable u = null;
		Connection conn = getConnection();
		String sql = "select count(*) as 'sum' from UserTable where phone='"+phone+"' and password='"
				+pwd+"'";
		PreparedStatement pstmt = null;
		try {
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				if(rs.getInt("sum")>0){
					sql = "select id,name,portraitPath,sex from UserTable where phone = '"+phone+"'";
					pstmt = (PreparedStatement) conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					while(rs.next()){
						u = new UserTable();
						u.id = rs.getString("id");
						u.name = rs.getString("name");
						u.portraitPath=rs.getString("portraitPath");
						u.sex=rs.getString("sex");
					}
				}
			}
	
			
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}
	public static int checkAndPostBuyTicket(TicketRecordTable table,ArrayList<TicketSeatTable> list){
		Result r = new Result();
		Connection conn = getConnection();
		String sql = "";
		PreparedStatement pstmt = null;
		if(false){
			r.setCode(Result.RESULT_TICKET_OUTDATE);
		}
		
		for(TicketSeatTable t:list){
			if(hasBought(table.cinemaId,table.filmId,table.sessionId,t.seatId)){
				r.setCode(Result.RESULT_TICKET_BOUGHT);
				break;
			}
		}
		
		try {
			if (r.getCode()!=Result.RESULT_TICKET_BOUGHT) {
				sql="select showingId from CinemaFilmShowTable where cinemaId='" +
						table.cinemaId+"' and filmId='"+table.filmId+"'";
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				ResultSet rs=pstmt.executeQuery();
				String showingId="";
				while(rs.next())
				showingId=rs.getString("showingId");
				
				sql="insert into TicketRecordTable(userId,sessionId,showingId,ticketNum," +
						"buyTime,seatSum) values(?,?,?,?,?,?)";
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				pstmt.setString(1, table.userId);
				pstmt.setString(2, table.sessionId);
				pstmt.setString(3, showingId);
				pstmt.setString(4, table.TicketNum);
				pstmt.setString(5, table.buyTime);
				pstmt.setInt(6, table.seatSum);
				pstmt.executeUpdate();
				sql="insert into TicketSeatTable(ticketNum,seatNum) values(?,?)";
				for (TicketSeatTable t:list) {
					pstmt = (PreparedStatement) conn.prepareStatement(sql);
					pstmt.setString(1, t.ticketNum);
					pstmt.setString(2, t.seatId);
					pstmt.executeUpdate();
				}

				r.setCode(Result.RESULT_OK);

				pstmt.close();
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r.getCode();
	}
	private static boolean hasBought(String cinemaId,String filmId,String sessionId,String seatNum){
		boolean exist=false;
		Connection conn = getConnection();
		String sql = "select count(*) as'sum' from CinemaFilmShowSessionTable,CinemaFilmShowTable,TicketRecordTable,TicketSeatTable "+
				"where CinemaFilmShowSessionTable.showingId=CinemaFilmShowTable.showingId and CinemaFilmShowSessionTable.showingId=TicketRecordTable.showingId "+
				"and CinemaFilmShowSessionTable.sessionId=TicketRecordTable.sessionId and TicketRecordTable.ticketNum=TicketSeatTable.ticketNum  "+
				"and cinemaId='"+cinemaId+"' and filmId='"+filmId+"' and TicketRecordTable.sessionId='"+sessionId+"' and seatNum='"+seatNum+"'";
		PreparedStatement pstmt = null;
		try {
				pstmt = (PreparedStatement) conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				rs.next();
				if(rs.getInt("sum")>0){
					exist=true;
				pstmt.close();
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return exist;
	}
}
