package servlet;

import java.util.ArrayList;

import Data.Actors;
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
import Data.Seat;
import Data.TicketRecord;



public class MakeData {
	
	

	
	
	
	
	
	
	
	public static String getPicturePath(Service s,int seed){
		final String PATH = "/WEB-INF/pixiv/";
        int index = seed%80+1;
        String path = PATH+index+".jpg";
        if(s.getServletContext().getResourceAsStream(path) == null){
        	path = PATH +index + ".png";
        }
        
        return path;
    }
	public static String getPortrait(Service s,int seed){
		final String PATH = "/WEB-INF/pixiv/";
        int index = seed%80+1;
        String path = PATH+index+".jpg";
        if(s.getServletContext().getResourceAsStream(path) == null){
        	path = PATH +index + ".png";
        }
        
        return path;
    }
    public static ArrayList<Cinema> getCinemas(int start,int num){
        ArrayList<Cinema> cinemas = new ArrayList<Cinema>();
        for(int i=start; i<start+num; ++i){
            Cinema tem = new Cinema();
            tem.setId(i+"");
            tem.setName("影院"+i);
            tem.setAddress("问责路，朝阳"+i+"号");
            tem.setDistance("距离："+i+"公里");
            tem.setlPrice("最低票价：￥5"+i);
            cinemas.add(tem);
        }
        return cinemas;
    }

    public static ArrayList<HomePageFilm> getDatas(){
        ArrayList<HomePageFilm> homePageFilms = new ArrayList<HomePageFilm>();
        for(int i=1;i<6;++i){
            HomePageFilm tem = new HomePageFilm();
            tem.setId("1");
            /*tem.setPoster(bitmap);*/
            tem.setPosterResourceId(i);
            tem.setName("电影22");
            tem.setScord("评分11");
            tem.setDate("10月10日");
            homePageFilms.add(tem);
        }
        return homePageFilms;
    }
    public static ArrayList<FilmList> getBigPosters(){
        ArrayList<FilmList> homePageFilms = new ArrayList<FilmList>();
        for(int i=1;i<5;++i){
        	FilmList tem = new FilmList();
            tem.setId(i+"");
            /*tem.setPoster(bitmap);*/
            homePageFilms.add(tem);
        }
        return homePageFilms;
    }
   
    public static ArrayList<FilmList> getFilmList(){
    	ArrayList<FilmList> filmLists = new ArrayList<FilmList>();
        for(int i=0; i<10;++i){
            FilmList filmList = new FilmList();
            filmList.setId(i+"");
            filmList.setName("电影"+i);
            filmList.setScord(i*8%10+5+"");
            /*filmList.setPoster(getPoster(i));*/
            filmList.setType("类型："+i);
            filmList.setDate("2016年10月"+i+"日");
            filmList.setCinemaNum(i*55+i*10+i+"");
            filmList.setShowingTimes(12*(66*i)+"");
            filmLists.add(filmList);
        }
        return filmLists;
    }
    
    public static FinforFilm getFinforFilm(){
    	FinforFilm film = new FinforFilm();
    	film.setDate("2016.12.11");
    	film.setFilmId("2");
    	film.setName("船货之前");
    	film.setScord("5.5");
    	film.setTime("120");
    	film.setType("剧情");
        return film;
    }
    
    
    public static Director getDirector(){
        Director director = new Director();
        director.setId("1");
        director.setName("张杰与");
        return director;
    }
    
    public static ArrayList<Actors> getActors(){
        ArrayList<Actors> actorses = new ArrayList<Actors>();
        for (int i=0; i<5;++i) {
            Actors actors = new Actors();
            actors.setId("5"+i);
            actors.setName("柳承浩"+i);
            actors.setRole("饰：吴三桂"+i);

            actorses.add(actors);
        }
        return actorses;
    }
    public static String getPlot(){
        return "在这项实验中，研究人员先将一个多电极阵列传感器植入猴子大脑，让它可以在大脑相关区域直接读取手臂控制鼠标的脑电位信号，并训练猴子通过键盘输入屏幕上看到的字符。研究人员通过设计的识别算法来识别猴子打字的脑电位信号，并实现在虚拟键盘上移动光标、选择字母键。最终，经过训练的猴子可以将所看到的文字通过意念“隔空”复写出来，而且最快可以每分钟12个单词的速度输入《纽约时报》的文章或莎士比亚名著《哈姆雷特》的段落。";
    }
    public static ArrayList<Critic> getCritics(int num){
        ArrayList<Critic> critics = new ArrayList<Critic>();
        for(int i=num;i<num*2;++i){
            Critic critic = new Critic();
            critic.setName("用户"+i);
            critic.setContent("权衡 view 和 content 来决定它的宽度和高度的整齐。它被measure(int, int) 调用 并且应该被子类所覆盖，以便提供准确高效的布局测量。\n" +
                    "规定: 当覆盖这个方法的时候，你必须调用 setMeasuredDimension(int, int)以便存储精确的视图的宽和高。如果不这样做的话将触发llegalStateException异常，" +
                    "被函数 measure(int, int)抛出。调用父类 onMeasure(int, int)是合理的。");
            critic.setDate("16年10月"+i+"日");
            critic.setId(i+"");

            critic.setPraise(i%2==0);
            critic.setPraise(i*45%30+"");
            critic.setScord(i*8%10);
            critics.add(critic);
        }
        return critics;
    }
    public static ArrayList<Collection> getCollections(int num){
        ArrayList<Collection> collections = new ArrayList<Collection>();
        for(int i=num;i<num+num;++i){
            Collection collection = new Collection();
            collection.setName("电影："+i);
            collection.setId(i+"");
            collection.setDirector("导演："+i);
            collection.setScord(i*7%6+5+"");
            collection.setTime(i+":20");
            collection.setType("动作");

            collections.add(collection);
        }
        return collections;
    }
    public static ArrayList<MyCritic> getMyCritics(int num){
        ArrayList<MyCritic> critics = new ArrayList<MyCritic>();
        for(int i=num;i<num*2;++i){
        	MyCritic critic = new MyCritic();
            critic.setFilmName("电影"+i);
            critic.setFilmId(i+"");
            critic.setContent("权衡 view 和 content 来决定它的宽度和高度的整齐。它被measure(int, int) 调用 并且应该被子类所覆盖，以便提供准确高效的布局测量。\n" +
                    "规定: 当覆盖这个方法的时候，你必须调用 setMeasuredDimension(int, int)以便存储精确的视图的宽和高。如果不这样做的话将触发llegalStateException异常，" +
                    "被函数 measure(int, int)抛出。调用父类 onMeasure(int, int)是合理的。");
            critic.setDate("16年10月"+i+"日");
            critic.setPraise(i%2==0);
            critic.setPraise(i*45%30+"");
            critic.setScord(i*8%10);
            critics.add(critic);
        }
        return critics;
    }
    public static ArrayList<TicketRecord> getTicketRecords(int num){
        ArrayList<TicketRecord> ticketRecords = new ArrayList<TicketRecord>();
        for(int i=num;i<num*2;++i){
            TicketRecord ticketRecord = new TicketRecord();
            ticketRecord.setFilmId(i+"");
            ticketRecord.setFilmName("电影"+i);
            ticketRecord.setCinemaName("影院"+i);
            ticketRecord.setDate("2016.10."+i);
            ticketRecord.setVideoHall(i+"号厅");
            ticketRecord.setTime("11:"+i);
            ArrayList<Integer> tem = new ArrayList<Integer>();
            for(int j=0;j<i%4;++j){
                tem.add((j+1)*i*345%100);
            }
            ticketRecord.setTickets(tem);
            ticketRecord.setBuyTime("6:"+i);
            ticketRecords.add(ticketRecord);
        }
        return ticketRecords;
    }
    public static ArrayList<CinemaSession> getSessions(String filmId,String cinemaId){
        ArrayList<CinemaSession> sessions = new ArrayList<CinemaSession>();
        for(int i=0; i<10;++i){
        	CinemaSession session = new CinemaSession();
            String date = "";
            switch (i%3){
                case 0:
                    date = "2016.10.27";
                    break;
                case 1:
                    date = "2016.10.28";
                    break;
                case 2:
                    date = "2016.10.29";
                    break;
            }
            session.setDate(date);
            session.setFilmId(filmId);
            session.setPrice(40+i+"");
            session.setSessionId(i*8+"");
            session.setTime(10+i+":20");
            session.setVideoHallNum(i%5+"");
            sessions.add(session);
        }
        return  sessions;
    }
    public static ArrayList<CinemaFilm> getFilms(String cinemaId){
        ArrayList<CinemaFilm> films = new ArrayList<CinemaFilm>();
        for(int i=0; i<6; ++i){
        	CinemaFilm film = new CinemaFilm();
            film.setId(i+"");
            film.setName("电影："+i);

            film.setScord((float)(i/2.0+6)/2);
            films.add(film);
        }
        return films;
    }
    public static CinemaDetail getCinema(String cinemaId){
    	CinemaDetail cinema = new CinemaDetail();
        cinema.setId(cinemaId);
        cinema.setAddress("昌耀路3号");
        cinema.setName("1234556影院");
        cinema.setPhone("9876543212");
        return cinema;
    }

    public static CinemaSeat getCinemaSeat(String cinemaId,String sessionId){
    	CinemaSeat cinema = new CinemaSeat();
        cinema.setCoulmns(40);
        cinema.setRow(10);
        ArrayList<Seat> tem = new ArrayList<Seat>();
        for(int i =0;i<400;++i)tem.add(getSeat());
        cinema.setSeatList(tem);
        return cinema;
    }
    
    private static Seat getSeat(){
        Seat s = new Seat();
        //  s.setSeat_type(random.nextInt()%3);
        //  s.setSeat_state(random.nextInt()%3+3);
        s.setSeat_type(1);
        s.setSeat_state(4);
        return s;
    }

}
