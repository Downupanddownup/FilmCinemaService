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
            tem.setName("ӰԺ"+i);
            tem.setAddress("����·������"+i+"��");
            tem.setDistance("���룺"+i+"����");
            tem.setlPrice("���Ʊ�ۣ���5"+i);
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
            tem.setName("��Ӱ22");
            tem.setScord("����11");
            tem.setDate("10��10��");
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
            filmList.setName("��Ӱ"+i);
            filmList.setScord(i*8%10+5+"");
            /*filmList.setPoster(getPoster(i));*/
            filmList.setType("���ͣ�"+i);
            filmList.setDate("2016��10��"+i+"��");
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
    	film.setName("����֮ǰ");
    	film.setScord("5.5");
    	film.setTime("120");
    	film.setType("����");
        return film;
    }
    
    
    public static Director getDirector(){
        Director director = new Director();
        director.setId("1");
        director.setName("�Ž���");
        return director;
    }
    
    public static ArrayList<Actors> getActors(){
        ArrayList<Actors> actorses = new ArrayList<Actors>();
        for (int i=0; i<5;++i) {
            Actors actors = new Actors();
            actors.setId("5"+i);
            actors.setName("���к�"+i);
            actors.setRole("�Σ�������"+i);

            actorses.add(actors);
        }
        return actorses;
    }
    public static String getPlot(){
        return "������ʵ���У��о���Ա�Ƚ�һ����缫���д�����ֲ����Ӵ��ԣ����������ڴ����������ֱ�Ӷ�ȡ�ֱۿ��������Ե�λ�źţ���ѵ������ͨ������������Ļ�Ͽ������ַ����о���Աͨ����Ƶ�ʶ���㷨��ʶ����Ӵ��ֵ��Ե�λ�źţ���ʵ��������������ƶ���ꡢѡ����ĸ�������գ�����ѵ���ĺ��ӿ��Խ�������������ͨ��������ա���д����������������ÿ����12�����ʵ��ٶ����롶ŦԼʱ���������»�ɯʿ������������ķ���ء��Ķ��䡣";
    }
    public static ArrayList<Critic> getCritics(int num){
        ArrayList<Critic> critics = new ArrayList<Critic>();
        for(int i=num;i<num*2;++i){
            Critic critic = new Critic();
            critic.setName("�û�"+i);
            critic.setContent("Ȩ�� view �� content ���������Ŀ�Ⱥ͸߶ȵ����롣����measure(int, int) ���� ����Ӧ�ñ����������ǣ��Ա��ṩ׼ȷ��Ч�Ĳ��ֲ�����\n" +
                    "�涨: ���������������ʱ���������� setMeasuredDimension(int, int)�Ա�洢��ȷ����ͼ�Ŀ�͸ߡ�������������Ļ�������llegalStateException�쳣��" +
                    "������ measure(int, int)�׳������ø��� onMeasure(int, int)�Ǻ���ġ�");
            critic.setDate("16��10��"+i+"��");
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
            collection.setName("��Ӱ��"+i);
            collection.setId(i+"");
            collection.setDirector("���ݣ�"+i);
            collection.setScord(i*7%6+5+"");
            collection.setTime(i+":20");
            collection.setType("����");

            collections.add(collection);
        }
        return collections;
    }
    public static ArrayList<MyCritic> getMyCritics(int num){
        ArrayList<MyCritic> critics = new ArrayList<MyCritic>();
        for(int i=num;i<num*2;++i){
        	MyCritic critic = new MyCritic();
            critic.setFilmName("��Ӱ"+i);
            critic.setFilmId(i+"");
            critic.setContent("Ȩ�� view �� content ���������Ŀ�Ⱥ͸߶ȵ����롣����measure(int, int) ���� ����Ӧ�ñ����������ǣ��Ա��ṩ׼ȷ��Ч�Ĳ��ֲ�����\n" +
                    "�涨: ���������������ʱ���������� setMeasuredDimension(int, int)�Ա�洢��ȷ����ͼ�Ŀ�͸ߡ�������������Ļ�������llegalStateException�쳣��" +
                    "������ measure(int, int)�׳������ø��� onMeasure(int, int)�Ǻ���ġ�");
            critic.setDate("16��10��"+i+"��");
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
            ticketRecord.setFilmName("��Ӱ"+i);
            ticketRecord.setCinemaName("ӰԺ"+i);
            ticketRecord.setDate("2016.10."+i);
            ticketRecord.setVideoHall(i+"����");
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
            film.setName("��Ӱ��"+i);

            film.setScord((float)(i/2.0+6)/2);
            films.add(film);
        }
        return films;
    }
    public static CinemaDetail getCinema(String cinemaId){
    	CinemaDetail cinema = new CinemaDetail();
        cinema.setId(cinemaId);
        cinema.setAddress("��ҫ·3��");
        cinema.setName("1234556ӰԺ");
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
