package servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

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
import Data.TicketRecord;
import DataBase.CityTable;
import DataBase.CriticTable;
import DataBase.TicketRecordTable;
import DataBase.TicketSeatTable;
import DataBase.UserTable;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Send {
	public static final int NETWORK_HOMEPAGE_GETBIGPOSTER = 1;
	public static final int NETWORK_HOMEPAGE_GETISSHOWING = 2;
	public static final int NETWORK_HOMEPAGE_GETUPCOMING = 3;
	public static final int NETWORK_FILMLIST_GETNOWSHOWING = 4;
	public static final int NETWORK_FILMLIST_GETUPCOMING = 5;
	public static final int NETWORK_CINEMALIST_ALLCITY_NOSPECIAL = 6;
	public static final int NETWORK_CINEMALIST_NEARBY_NOSPECIAL = 7;
	public static final int NETWORK_TICKETRECORD = 8;
	public static final int NETWORK_MYCRITIC = 9;
	public static final int NETWORK_COLLECTION = 10;
	public static final int NETWORK_FILMDETAIL_FINFOR = 11;
	public static final int NETWORK_FILMDETAIL_ACTORS = 12;
	public static final int NETWORK_FILMDETAIL_CRITIC = 13;
	public static final int NETWORK_CINEMALIST_ALLCITY_SPECIAL = 14;
	public static final int NETWORK_CINEMALIST_NEARBY_SPECIAL = 15;
	public static final int NETWORK_CINEMADETAIL_FINFOR = 16;
	public static final int NETWORK_CINEMADEATIL_FILM = 17;
	public static final int NETWORK_SEATCHOOSE = 18;
	public static final int NETWORK_FILMDETAIL_DIRECTOR = 19;
	public static final int NETWORK_CINEMADEATIL_SESSION = 20;
	public static final int NETWORK_FILM_PICTURE = 21;
	public static final int NETWORK_FILMDETAIL_PLOT = 22;
	public static final int NETWORK_PORTRAIT = 23;
	
	public static final int NETWORK_SEARCH_FILM = 33;
    public static final int NETWORK_POST_LOGIN = 24;
    public static final int NETWORK_POST_REGISTER = 25;
    public static final int NETWORK_POST_PCENTER_EDIT = 26;
    public static final int NETWORK_POST_PCENTER_PSD = 27;
    public static final int NETWORK_POST_PCENTER_PHONE = 28;
    public static final int NETWORK_POST_PRAISE = 29;
    public static final int NETWORK_POST_COLLECT = 30;
    public static final int NETWORK_POST_CRITIC = 31;
    public static final int NETWORK_POST_BUYTICKET = 32;
    public static final int NETWORK_CITY = 34;
    public static final int NETWORK_SEARCH_CINEMA = 35;
	
    
    public static void Critic(HttpServletResponse 
			response,String content)throws IOException{
    	JsonParser jp = new JsonParser();
		JsonObject jo = jp.parse(content).getAsJsonObject();
		CriticTable table = new CriticTable();

		table.userId = jo.get("userId").getAsString();
		table.filmId = jo.get("filmId").getAsString();
		table.scord = jo.get("scord").getAsFloat();
		table.time = jo.get("time").getAsString();
		table.content = jo.get("content").getAsString();
		table.praiseSum = 0+"";
		table.id = ConnectToSql.getCriticId(table.userId);

		ConnectToSql.postCritic(table);
		//int r= ConnectToSql.BuyTicket(table,list);
    }
    
    public static void Collect(HttpServletResponse 
			response,String content)throws IOException{
    	JsonParser jp = new JsonParser();
		JsonObject jo = jp.parse(content).getAsJsonObject();

		String filmId = jo.get("filmId").getAsString();
		String userId = jo.get("userId").getAsString();
		//int r= ConnectToSql.BuyTicket(table,list);
		ConnectToSql.postCollection(userId,filmId);
    }
    public static void Praise(HttpServletResponse 
			response,String content)throws IOException{
    	System.out.println("NowShowingData_HomepageFilm ");
		
		JsonParser jp = new JsonParser();
		JsonObject jo = jp.parse(content).getAsJsonObject();

		String criticId = jo.get("criticId").getAsString();
		String userId = jo.get("userId").getAsString();
		ConnectToSql.postPraise(userId,criticId);

    }
    
    public static void PcenterPhone(HttpServletResponse 
			response,String content)throws IOException{
    	System.out.println("NowShowingData_HomepageFilm ");
		
		JsonParser jp = new JsonParser();
		JsonObject jo = jp.parse(content).getAsJsonObject();

		String phone = jo.get("phone").getAsString();
		String id = jo.get("userId").getAsString();
		int r = ConnectToSql.checkAndPostPhone(id,phone);
		
		String result = URLEncoder.encode(r+"","UTF-8");
		jo.addProperty("result", result);
		PrintWriter out = response.getWriter();
		out.println(jo.toString());
		out.flush();
		out.close();
    }
    public static void PcenterPwd(HttpServletResponse 
			response,String content)throws IOException{
    	System.out.println("NowShowingData_HomepageFilm ");
		
		JsonParser jp = new JsonParser();
		JsonObject jo = jp.parse(content).getAsJsonObject();

		String pwd = jo.get("password").getAsString();
		String id = jo.get("userId").getAsString();
		ConnectToSql.postPassword(id,pwd);
		
    }
    public static void PcenterEdit(HttpServletResponse 
			response,String content)throws IOException{
    	System.out.println("NowShowingData_HomepageFilm ");
		
		JsonParser jp = new JsonParser();
		JsonObject jo = jp.parse(content).getAsJsonObject();

		String sex = jo.get("sex").getAsString();
		String name = jo.get("userName").getAsString();
		String id = jo.get("userId").getAsString();
		int r= ConnectToSql.checkAndPostUserFinfor(id,name,sex);
		
		String result = URLEncoder.encode(r+"","UTF-8");
		jo.addProperty("result", result);
		PrintWriter out = response.getWriter();
		out.println(jo.toString());
		out.flush();
		out.close();
    }
    public static void Register(HttpServletResponse 
			response,String content)throws IOException{
    	System.out.println("NowShowingData_HomepageFilm ");
		
		JsonParser jp = new JsonParser();
		JsonObject jo = jp.parse(content).getAsJsonObject();
		UserTable table = new UserTable();
		table.id = ConnectToSql.createNewUserId();
		table.name = jo.get("name").getAsString();
		table.password = jo.get("password").getAsString();
		table.phone = jo.get("phone").getAsString();
		table.portraitPath = "000.jpg";
		table.sex="男";
		int r = ConnectToSql.checkAndPostNewUser(table);
		
		String result = URLEncoder.encode(r+"","UTF-8");
		jo.addProperty("result", result);
		PrintWriter out = response.getWriter();
		out.println(jo.toString());
		out.flush();
		out.close();
    }
    public static void Login(HttpServletResponse 
			response,String content)throws IOException{
    	System.out.println("NowShowingData_HomepageFilm ");
		
		JsonParser jp = new JsonParser();
		JsonObject jo = jp.parse(content).getAsJsonObject();
		
		String userName = jo.get("phone").getAsString();
		String pwd = jo.get("password").getAsString();
		
		UserTable u= ConnectToSql.checkAndPostLogin(userName,pwd);
		
		jo = new JsonObject();
		if(u==null){
			jo.addProperty("userAccount", 1);
		}else{
			jo.addProperty("userAccount", 2);
			jo.addProperty("id", u.id);
			jo.addProperty("name", u.name);
			jo.addProperty("portraitName", u.portraitPath);
			jo.addProperty("sex", u.sex);
		}
		
		String result = URLEncoder.encode(jo.toString(),"UTF-8");
	//	jo.addProperty("result", result);
		PrintWriter out = response.getWriter();
		out.println(result);
		out.flush();
		out.close();
    }
    
    public static void BuyTicket(HttpServletResponse 
			response,String content)throws IOException{

		System.out.println("NowShowingData_HomepageFilm ");
		
		JsonParser jp = new JsonParser();
		JsonObject jo = jp.parse(content).getAsJsonObject();
		TicketRecordTable table = new TicketRecordTable();
		ArrayList<TicketSeatTable> list = new ArrayList<TicketSeatTable>();
		
		table.userId = jo.get("userId").getAsString();
		table.filmId = jo.get("filmId").getAsString();
		table.cinemaId = jo.get("cinemaId").getAsString();
		table.buyTime = jo.get("buyTime").getAsString();
		table.sessionId = jo.get("sessionId").getAsString();
		table.TicketNum = table.userId+table.cinemaId+table.filmId+table.buyTime;
		
		JsonArray ja = jo.get("tickets").getAsJsonArray();
		for(JsonElement je:ja){
			TicketSeatTable tem = new TicketSeatTable();
			tem.seatId = je.getAsInt()+"";
			tem.ticketNum = table.TicketNum;
			list.add(tem);
		}
		
		table.seatSum = list.size();
		
		int r= ConnectToSql.checkAndPostBuyTicket(table,list);
		
		String result = URLEncoder.encode(r+"","UTF-8");
		jo.addProperty("result", result);
		PrintWriter out = response.getWriter();
		out.println(jo.toString());
		out.flush();
		out.close();
    }
    
    public static void SearchFilm(HttpServletResponse 
			response,String filmNameLike,String start) throws IOException{
		
		System.out.println("UpcomingData_FilmList start:"+start);
		
		//ArrayList<FilmList> data = MakeData.getFilmList();///
		ArrayList<FilmList> data = ConnectToSql.getSearchFilm(filmNameLike,start);
		JsonObject jo = new JsonObject();
		JsonArray ja = new JsonArray();
		for(FilmList tem:data){
			JsonObject jo2 = new JsonObject();
			jo2.addProperty("id", tem.getId());///
			jo2.addProperty("posterName", tem.getposterPath());
			jo2.addProperty("name", tem.getName());
			jo2.addProperty("scord", tem.getScord());
			jo2.addProperty("type", tem.getType());
			jo2.addProperty("date", tem.getDate());
			ja.add(jo2);
		}
		jo.add("List", ja);
		String content = URLEncoder.encode(jo.toString(),"UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println(content);
		out.flush();
		out.close();
    }
 
    public static void SearchCinema(HttpServletResponse 
			response,String cinemaNameLike,String cityId,String start) throws IOException{
		
		System.out.println("AllCity_CinemaList_NoSpecial cityId:"
				+cityId+" start:"+start);
		
		//ArrayList<Cinema> data = MakeData.getCinemas(1,10);///
		ArrayList<Cinema> data = ConnectToSql.getSearchCinema(cinemaNameLike,cityId,start);
		JsonObject jo = new JsonObject();
		JsonArray ja = new JsonArray();
		for(Cinema tem:data){
			JsonObject jo2 = new JsonObject();
			jo2.addProperty("id", tem.getId());///
			jo2.addProperty("name", tem.getName());
			jo2.addProperty("lprice", tem.getlPrice());
			jo2.addProperty("address", tem.getAddress());
			jo2.addProperty("distance", tem.getDistance());
			ja.add(jo2);
		}
		jo.add("List", ja);
		String content = URLEncoder.encode(jo.toString(),"UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println(content);
		out.flush();
		out.close();
    }
    public static void City(HttpServletResponse 
			response,String cityId,String start) throws IOException{
		
		System.out.println("AllCity_CinemaList_NoSpecial cityId:"
				+cityId+" start:"+start);
		
		//ArrayList<Cinema> data = MakeData.getCinemas(1,10);///
		ArrayList<CityTable> data = ConnectToSql.getCity();
		JsonObject jo = new JsonObject();
		JsonArray ja = new JsonArray();
		for(CityTable tem:data){
			JsonObject jo2 = new JsonObject();
			jo2.addProperty("id", tem.id);///
			jo2.addProperty("name", tem.firstLettle);
			jo2.addProperty("lprice", tem.name);
			ja.add(jo2);
		}
		jo.add("List", ja);
		String content = URLEncoder.encode(jo.toString(),"UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println(content);
		out.flush();
		out.close();
    }    
	public static void NowShowingData_HomepageFilm(HttpServletResponse 
			response) throws IOException{
		/*
		 * 1，获取数据
		 * 2，将数据转化为JSON格式
		 * 3，发送数据
		 * 4，关闭连接*/
		
		//ArrayList<HomePageFilm> data = MakeData.getDatas();///
		
		System.out.println("NowShowingData_HomepageFilm ");
		
		ArrayList<HomePageFilm> data = ConnectToSql.
				getNowShowingData_HomepageFilm();
		JsonObject jo = new JsonObject();
		JsonArray ja = new JsonArray();
		for(HomePageFilm tem:data){
			JsonObject jo2 = new JsonObject();
			jo2.addProperty("id", tem.getId());///
			jo2.addProperty("name", tem.getName());
			jo2.addProperty("scord", tem.getScord());
			jo2.addProperty("posterName", tem.getPosterPath());
			ja.add(jo2);
		}
		jo.add("List", ja);
		String content = URLEncoder.encode(jo.toString(),"UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println(content);
		out.flush();
		out.close();
    }
	public static void UpcomingData_HomepageFilm(HttpServletResponse 
			response) throws IOException{
		//ArrayList<HomePageFilm> data = MakeData.getDatas();///
		System.out.println("UpcomingData_HomepageFilm ");
		
		ArrayList<HomePageFilm> data = 
				ConnectToSql.getUpcomingData_HomepageFilm();
		JsonObject jo = new JsonObject();
		JsonArray ja = new JsonArray();
		for(HomePageFilm tem:data){
			JsonObject jo2 = new JsonObject();
			jo2.addProperty("id", tem.getId());///
			jo2.addProperty("name", tem.getName());
			jo2.addProperty("scord", tem.getScord());
			jo2.addProperty("date", tem.getDate());
			jo2.addProperty("posterName", tem.getPosterPath());
			ja.add(jo2);
		}
		jo.add("List", ja);
		String content = URLEncoder.encode(jo.toString(),"UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println(content);
		out.flush();
		out.close();
    }
	public static void BigPosters_HomePageFilm(HttpServletResponse 
			response) throws IOException{
		//ArrayList<FilmList> data = MakeData.getBigPosters();///
		
		System.out.println("BigPosters_HomePageFilm ");
		
		ArrayList<FilmList> data = ConnectToSql.getBigPosters_HomePageFilm();
		JsonObject jo = new JsonObject();
		JsonArray ja = new JsonArray();
		for(FilmList tem:data){
			JsonObject jo2 = new JsonObject();
			jo2.addProperty("id", tem.getId());///
			jo2.addProperty("posterName", tem.getposterPath());
			ja.add(jo2);
		}
		jo.add("List", ja);
		String content = URLEncoder.encode(jo.toString(),"UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println(content);
		out.flush();
		out.close();
    }
	public static void NowShowingData_FilmList(HttpServletResponse 
			response,String start) throws IOException{
		
		System.out.println("NowShowingData_FilmList start:"+start);
		
		//ArrayList<FilmList> data = MakeData.getFilmList();///
		ArrayList<FilmList> data = ConnectToSql.getNowShowingData_FilmList(start);
		JsonObject jo = new JsonObject();
		JsonArray ja = new JsonArray();
		for(FilmList tem:data){
			JsonObject jo2 = new JsonObject();
			jo2.addProperty("id", tem.getId());///
			jo2.addProperty("posterName", tem.getposterPath());
			jo2.addProperty("name", tem.getName());
			jo2.addProperty("scord", tem.getScord());
			jo2.addProperty("showingTimes", tem.getShowingTimes());
			jo2.addProperty("cinemaNum", tem.getCinemaNum());
			ja.add(jo2);
		}
		jo.add("List", ja);
		String content = URLEncoder.encode(jo.toString(),"UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println(content);
		out.flush();
		out.close();
    }
	public static void UpcomingData_FilmList(HttpServletResponse 
			response,String start) throws IOException{
		
		System.out.println("UpcomingData_FilmList start:"+start);
		
		//ArrayList<FilmList> data = MakeData.getFilmList();///
		ArrayList<FilmList> data = ConnectToSql.getUpcomingData_FilmList(start);
		JsonObject jo = new JsonObject();
		JsonArray ja = new JsonArray();
		for(FilmList tem:data){
			JsonObject jo2 = new JsonObject();
			jo2.addProperty("id", tem.getId());///
			jo2.addProperty("posterName", tem.getposterPath());
			jo2.addProperty("name", tem.getName());
			jo2.addProperty("scord", tem.getScord());
			jo2.addProperty("type", tem.getType());
			jo2.addProperty("date", tem.getDate());
			ja.add(jo2);
		}
		jo.add("List", ja);
		String content = URLEncoder.encode(jo.toString(),"UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println(content);
		out.flush();
		out.close();
    }
	public static void AllCity_CinemaList_NoSpecial(HttpServletResponse 
			response,String cityId,String start) throws IOException{
		
		System.out.println("AllCity_CinemaList_NoSpecial cityId:"
				+cityId+" start:"+start);
		
		//ArrayList<Cinema> data = MakeData.getCinemas(1,10);///
		ArrayList<Cinema> data = ConnectToSql.getAllCity_CinemaList_NoSpecial(cityId,start);
		JsonObject jo = new JsonObject();
		JsonArray ja = new JsonArray();
		for(Cinema tem:data){
			JsonObject jo2 = new JsonObject();
			jo2.addProperty("id", tem.getId());///
			jo2.addProperty("name", tem.getName());
			jo2.addProperty("lprice", tem.getlPrice());
			jo2.addProperty("address", tem.getAddress());
			jo2.addProperty("distance", tem.getDistance());
			ja.add(jo2);
		}
		jo.add("List", ja);
		String content = URLEncoder.encode(jo.toString(),"UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println(content);
		out.flush();
		out.close();
    }
	public static void Nearby_CinemaList_NoSpecial(HttpServletResponse 
			response,String jing,String wei,String cityId,String start) throws IOException{
		
		System.out.println("Nearby_CinemaList_NoSpecial cityId:"
				+cityId+" start:"+start+" jing:"+jing+" wei:"+wei);
		
		//ArrayList<Cinema> data = MakeData.getCinemas(1,10);///
		ArrayList<Cinema> data = ConnectToSql.getNearby_CinemaList_NoSpecial(cityId,jing,wei,start);
		JsonObject jo = new JsonObject();
		JsonArray ja = new JsonArray();
		for(Cinema tem:data){
			JsonObject jo2 = new JsonObject();
			jo2.addProperty("id", tem.getId());///
			jo2.addProperty("name", tem.getName());
			jo2.addProperty("lprice", tem.getlPrice());
			jo2.addProperty("address", tem.getAddress());
			jo2.addProperty("distance", tem.getDistance());
			ja.add(jo2);
		}
		jo.add("List", ja);
		String content = URLEncoder.encode(jo.toString(),"UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println(content);
		out.flush();
		out.close();
    }
	public static void TicketRecorder(HttpServletResponse 
			response,String userId,String start) throws IOException{
		
		System.out.println("TicketRecorder userId:"
				+userId+" start:"+start);
		
		//ArrayList<TicketRecord> data = MakeData.getTicketRecords(10);///
		ArrayList<TicketRecord> data = ConnectToSql.getTicketRecorder(userId,start);
		JsonObject jo = new JsonObject();
		JsonArray ja = new JsonArray();
		for(TicketRecord tem:data){
			JsonObject jo2 = new JsonObject();
			jo2.addProperty("filmId", tem.getFilmId());///
			jo2.addProperty("filmName", tem.getFilmName());
			jo2.addProperty("cinemaName", tem.getCinemaName());
			jo2.addProperty("date", tem.getDate());///
			jo2.addProperty("buyTime", tem.getBuyTime());
			jo2.addProperty("time", tem.getTime());
			jo2.addProperty("videoHall", tem.getVideoHall());///
			JsonArray ja2 = new JsonArray();
			for(int i:tem.getTickets()){
				ja2.add(i);
			}
			jo2.add("tickets", ja2);
			
			ja.add(jo2);
		}
		jo.add("List", ja);
		String content = URLEncoder.encode(jo.toString(),"UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println(content);
		out.flush();
		out.close();
    }
	public static void Mycritic(HttpServletResponse 
			response,String userId,String start) throws IOException{
		
		System.out.println("Mycritic userId:"
				+userId+" start:"+start);
		
		//ArrayList<MyCritic> data = MakeData.getMyCritics(10);///
		ArrayList<MyCritic> data = ConnectToSql.getMycritic(userId,start);
		JsonObject jo = new JsonObject();
		JsonArray ja = new JsonArray();
		for(MyCritic tem:data){
			JsonObject jo2 = new JsonObject();
			jo2.addProperty("filmId", tem.getFilmId());///
			jo2.addProperty("filmName", tem.getFilmName());
			jo2.addProperty("content", tem.getContent());
			jo2.addProperty("date", tem.getDate());
			jo2.addProperty("scord", tem.getScord());
			jo2.addProperty("praise", tem.getPraise());
			jo2.addProperty("isPraise", tem.isPraise());
			ja.add(jo2);
		}
		jo.add("List", ja);
		String content = URLEncoder.encode(jo.toString(),"UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println(content);
		out.flush();
		out.close();
    }
	public static void Collection(HttpServletResponse 
			response,String userId,String start) throws IOException{
		
		System.out.println("Collection userId:"
				+userId+" start:"+start);
		
		//ArrayList<Collection> data = MakeData.getCollections(10);///
		ArrayList<Collection> data = ConnectToSql.getCollection(userId,start);
		JsonObject jo = new JsonObject();
		JsonArray ja = new JsonArray();
		for(Collection tem:data){
			JsonObject jo2 = new JsonObject();
			jo2.addProperty("id", tem.getId());///
			jo2.addProperty("posterName", tem.getPosterId());
			jo2.addProperty("name", tem.getName());
			jo2.addProperty("scord", tem.getScord());
			jo2.addProperty("director", tem.getDirector());
			jo2.addProperty("time", tem.getTime());
			jo2.addProperty("type", tem.getType());
			ja.add(jo2);
		}
		jo.add("List", ja);
		String content = URLEncoder.encode(jo.toString(),"UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println(content);
		out.flush();
		out.close();
    }
	public static void Finfor_FilmDetail(HttpServletResponse 
			response,String filmId) throws IOException{
		
		System.out.println("Finfor_FilmDetail filmId:"
				+filmId);
		
		//FinforFilm data = MakeData.getFinforFilm();///
		FinforFilm data = ConnectToSql.getFinfor_FilmDetail(filmId);
		JsonObject jo = new JsonObject();
		jo.addProperty("filmId", data.getFilmId());// /
		jo.addProperty("posterName", data.getPosterName());
		jo.addProperty("name", data.getName());
		jo.addProperty("scord", data.getScord());
		jo.addProperty("time", data.getTime());
		jo.addProperty("type", data.getType());
		jo.addProperty("date", data.getDate());
			
		String content = URLEncoder.encode(jo.toString(),"UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println(content);
		out.flush();
		out.close();
    }
	public static void Actors_FilmDeatail(HttpServletResponse 
			response,String filmId,String start) throws IOException{
		
		System.out.println("Actors_FilmDeatail filmId:"
				+filmId+" start:"+start);
		
		//ArrayList<Actors> data = MakeData.getActors();///
		ArrayList<Actors> data = ConnectToSql.getActors_FilmDeatail(filmId,start);
		JsonObject jo = new JsonObject();
		JsonArray ja = new JsonArray();
		for(Actors tem:data){
			JsonObject jo2 = new JsonObject();
			jo2.addProperty("id", tem.getId());///
			jo2.addProperty("name", tem.getName());
			jo2.addProperty("portraitName", tem.getPortraitId());
			jo2.addProperty("role", tem.getRole());
			ja.add(jo2);
		}
		jo.add("List", ja);
		String content = URLEncoder.encode(jo.toString(),"UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println(content);
		out.flush();
		out.close();
    }
	public static void Director_FilmDeatil(HttpServletResponse 
			response,String filmId) throws IOException{
		
		System.out.println("Director_FilmDeatil filmId:"
				+filmId);
		
		//Director data = MakeData.getDirector();///
		Director data = ConnectToSql.getDirector_FilmDeatil(filmId);
		JsonObject jo = new JsonObject();

		jo.addProperty("id", data.getId());// /
		jo.addProperty("name", data.getName());
		jo.addProperty("portraitName", data.getPortraitId());

		String content = URLEncoder.encode(jo.toString(),"UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println(content);
		out.flush();
		out.close();
    }
	public static void Critic_FilmDeatil(HttpServletResponse 
			response,String filmId,String start) throws IOException{
		
		System.out.println("Critic_FilmDeatil filmId:"
				+filmId+" start:"+start);
		
		//ArrayList<Critic> data = MakeData.getCritics(10);///
		ArrayList<Critic> data = ConnectToSql.getCritic_FilmDeatil(filmId,start);
		JsonObject jo = new JsonObject();
		JsonArray ja = new JsonArray();
		for(Critic tem:data){
			JsonObject jo2 = new JsonObject();
			jo2.addProperty("id", tem.getId());///
			jo2.addProperty("portraitName", tem.getPortraitId());
			jo2.addProperty("name", tem.getName());
			jo2.addProperty("scord", tem.getScord());
			jo2.addProperty("content", tem.getContent());///
			jo2.addProperty("date", tem.getDate());
			jo2.addProperty("praise", tem.getPraise());
			jo2.addProperty("isPraise", tem.isPraise());///
			ja.add(jo2);
		}
		jo.add("List", ja);
		String content = URLEncoder.encode(jo.toString(),"UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println(content);
		out.flush();
		out.close();
    }
	public static void AllCity_CinemaList_Special(HttpServletResponse 
			response,String cityId,String filmId,String start) throws IOException{
		
		System.out.println("AllCity_CinemaList_Special filmId:"
				+filmId+" cityId:"+cityId+" start:"+start);
		
		//ArrayList<Cinema> data = MakeData.getCinemas(1, 10);///
		ArrayList<Cinema> data = ConnectToSql.getAllCity_CinemaList_Special(filmId,cityId,start);
		JsonObject jo = new JsonObject();
		JsonArray ja = new JsonArray();
		for(Cinema tem:data){
			JsonObject jo2 = new JsonObject();
			jo2.addProperty("id", tem.getId());///
			jo2.addProperty("name", tem.getName());
			jo2.addProperty("address", tem.getAddress());
			jo2.addProperty("distance", tem.getDistance());
			jo2.addProperty("lprice", tem.getlPrice());
			ja.add(jo2);
		}
		jo.add("List", ja);
		String content = URLEncoder.encode(jo.toString(),"UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println(content);
		out.flush();
		out.close();
    }
	public static void Nearby_CinemaList_Special(HttpServletResponse 
			response,String jing,String wei,String filmId,String cityId,String start) throws IOException{
		
		System.out.println("Nearby_CinemaList_Special filmId:"
				+filmId+" cityId:"+cityId+" start:"+start+" jing:"+jing+" wei:"+wei);
		
		//ArrayList<Cinema> data = MakeData.getCinemas(1,10);///
		ArrayList<Cinema> data = ConnectToSql.getNearby_CinemaList_Special(filmId,cityId,jing,wei,start);
		JsonObject jo = new JsonObject();
		JsonArray ja = new JsonArray();
		for(Cinema tem:data){
			JsonObject jo2 = new JsonObject();
			jo2.addProperty("id", tem.getId());///
			jo2.addProperty("name", tem.getName());
			jo2.addProperty("address", tem.getAddress());
			jo2.addProperty("distance", tem.getDistance());
			jo2.addProperty("lprice", tem.getlPrice());
			ja.add(jo2);
		}
		jo.add("List", ja);
		String content = URLEncoder.encode(jo.toString(),"UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println(content);
		out.flush();
		out.close();
    }
	public static void Film_CinemaDetail(HttpServletResponse 
			response,String cinemaId) throws IOException{
		
		System.out.println("Film_CinemaDetail cinemaId:"
				+cinemaId);
		
		//ArrayList<CinemaFilm> data = MakeData.getFilms("2");///
		ArrayList<CinemaFilm> data = ConnectToSql.getFilm_CinemaDetail(cinemaId);
		JsonObject jo = new JsonObject();
		JsonArray ja = new JsonArray();
		for(CinemaFilm tem:data){
			JsonObject jo2 = new JsonObject();
			jo2.addProperty("id", tem.getId());///
			jo2.addProperty("name", tem.getName());
			jo2.addProperty("scord", tem.getScord());
			jo2.addProperty("posterName", tem.getPosterId());
			ja.add(jo2);
		}
		jo.add("List", ja);
		String content = URLEncoder.encode(jo.toString(),"UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println(content);
		out.flush();
		out.close();
    }
	public static void Finfor_CinemaDetail(HttpServletResponse 
			response,String cinemaId) throws IOException{
		
		System.out.println("Finfor_CinemaDetail cinemaId:"
				+cinemaId);
		
		//CinemaDetail data = MakeData.getCinema("2");///
		CinemaDetail data = ConnectToSql.getFinfor_CinemaDetail(cinemaId);
		JsonObject jo = new JsonObject();

		jo.addProperty("id", data.getId());// /
		jo.addProperty("name", data.getName());
		jo.addProperty("address", data.getAddress());
		jo.addProperty("phone", data.getPhone());
		
		String content = URLEncoder.encode(jo.toString(),"UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println(content);
		out.flush();
		out.close();
    }
	public static void Session_CinemaDetail(HttpServletResponse 
			response,String cinemaId,String filmId) throws IOException{
		
		System.out.println("Session_CinemaDetail cinemaId:"
				+cinemaId+" filmId:"+filmId);
		
		//ArrayList<CinemaSession> data = MakeData.getSessions("1", "2");///
		ArrayList<CinemaSession> data = ConnectToSql.getSession_CinemaDetail(cinemaId,filmId);
		JsonObject jo = new JsonObject();
		JsonArray ja = new JsonArray();
		for(CinemaSession tem:data){
			JsonObject jo2 = new JsonObject();
			jo2.addProperty("id", tem.getFilmId());///
			jo2.addProperty("sessionId", tem.getSessionId());
			jo2.addProperty("videoHallNum", tem.getVideoHallNum());
			jo2.addProperty("date", tem.getDate());///
			jo2.addProperty("price", tem.getPrice());
			jo2.addProperty("time", tem.getTime());
			ja.add(jo2);
		}
		jo.add("List", ja);
		String content = URLEncoder.encode(jo.toString(),"UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println(content);
		out.flush();
		out.close();
    }
	public static void Seat(HttpServletResponse 
			response,String cinemaId,String filmId,String sessionId) throws IOException{
		
		System.out.println("Seat cinemaId:"
				+cinemaId+" sessionId:"+sessionId);
		
		//CinemaSeat data = MakeData.getCinemaSeat("", "");///
		CinemaSeat data = ConnectToSql.getSeat(cinemaId,filmId,sessionId);
		JsonObject jo = new JsonObject();
		jo.addProperty("coulmns", data.getCoulmns());///
		jo.addProperty("row", data.getRow());
		JsonArray ja = new JsonArray();
		for(Aseat tem:data.getSeatList()){
			JsonObject jo2 = new JsonObject();
			jo2.addProperty("seatNum", tem.id);
			jo2.addProperty("type", tem.seatType);
			jo2.addProperty("state", tem.seatState);
			jo2.addProperty("lovers_locat",tem.lovers_locat);
			ja.add(jo2);
		}
		jo.add("seatList", ja);
		String content = URLEncoder.encode(jo.toString(),"UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println(content);
		out.flush();
		out.close();
    }
	public static void FilmPictrue(HttpServletResponse 
			response,Service s,String pictureName) throws IOException{
		
		System.out.println("FilmPictrue pictureName:"
				+pictureName);
		
		ServletOutputStream out = response.getOutputStream();
		//String path = MakeData.getPicturePath(s, 10);
		String path = "/WEB-INF/"+pictureName;
		InputStream in = s.getServletContext().getResourceAsStream(path);
		int b;
		while((b = in.read())!=-1){
			out.write(b);
		}
		
		out.flush();
		out.close();
	}
	public static void Plot(HttpServletResponse 
			response,String filmId)throws IOException{
		
		System.out.println("Plot filmId:"
				+filmId);
		
		//String data = MakeData.getPlot();///
		String data = ConnectToSql.getPlot(filmId);
		JsonObject jo = new JsonObject();
		jo.addProperty("plot", data);///

		String content = URLEncoder.encode(jo.toString(),"UTF-8");
		
		PrintWriter out = response.getWriter();
		out.println(content);
		out.flush();
		out.close();
	}
	public static void Portrait(HttpServletResponse 
			response,Service s,String portraitName) throws IOException{
		
		System.out.println("portrait portraitName:"
				+portraitName);
		
		ServletOutputStream out = response.getOutputStream();
		//String path = MakeData.getPortrait(s, 67);
		String path =  "/WEB-INF/"+portraitName;
		System.out.println("path="+path);
		InputStream in = s.getServletContext().getResourceAsStream(path);
		int b;
		while((b = in.read())!=-1){
			out.write(b);
		}
		
		out.flush();
		out.close();
	}
}
