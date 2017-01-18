package servlet;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Service extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Service() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		int type = Integer.parseInt(request.getParameter("type"));
		String tem = "";
		String start = "";
		switch(type){
		case Send.NETWORK_CINEMADEATIL_FILM:
			tem = request.getParameter("cinemaId");
			Send.Film_CinemaDetail(response,tem);
			break;
		case Send.NETWORK_CINEMADEATIL_SESSION:
			tem = request.getParameter("cinemaId");
			String filmId = request.getParameter("filmId");
			Send.Session_CinemaDetail(response,tem,filmId);
			break;
		case Send.NETWORK_CINEMADETAIL_FINFOR:
			tem = request.getParameter("cinemaId");
			Send.Finfor_CinemaDetail(response,tem);
			break;
		case Send.NETWORK_CINEMALIST_ALLCITY_NOSPECIAL:
			tem = request.getParameter("cityId");
			start = request.getParameter("start");
			Send.AllCity_CinemaList_NoSpecial(response,tem,start);
			break;
		case Send.NETWORK_CINEMALIST_ALLCITY_SPECIAL:
			filmId = request.getParameter("filmId");
			tem = request.getParameter("cityId");
			start = request.getParameter("start");
			Send.AllCity_CinemaList_Special(response,tem,filmId,start);
			break;
		case Send.NETWORK_CINEMALIST_NEARBY_NOSPECIAL:
			String jing = request.getParameter("jing");
			String wei = request.getParameter("wei");
			start = request.getParameter("start");
			tem = request.getParameter("cityId");
			Send.Nearby_CinemaList_NoSpecial(response,jing,wei,tem,start);
			break;
		case Send.NETWORK_CINEMALIST_NEARBY_SPECIAL:
			jing = request.getParameter("jing");
			wei = request.getParameter("wei");
			start = request.getParameter("start");
			tem = request.getParameter("cityId");
			filmId = request.getParameter("filmId");
			Send.Nearby_CinemaList_Special(response,jing,wei,filmId,tem,start);
			break;
		case Send.NETWORK_COLLECTION:
			tem = request.getParameter("userId");
			start = request.getParameter("start");
			Send.Collection(response,tem,start);
			break;
		case Send.NETWORK_FILMDETAIL_ACTORS:
			tem = request.getParameter("filmId");
			start = request.getParameter("start");
			Send.Actors_FilmDeatail(response,tem,start);
			break;
		case Send.NETWORK_FILMDETAIL_CRITIC:
			tem = request.getParameter("filmId");
			start = request.getParameter("start");
			Send.Critic_FilmDeatil(response,tem,start);
			break;
		case Send.NETWORK_FILMDETAIL_DIRECTOR:
			tem = request.getParameter("filmId");
			Send.Director_FilmDeatil(response,tem);
			break;
		case Send.NETWORK_FILMDETAIL_FINFOR:
			tem = request.getParameter("filmId");
			Send.Finfor_FilmDetail(response,tem);
			break;
		case Send.NETWORK_FILMLIST_GETNOWSHOWING:
			start = request.getParameter("start");
			Send.NowShowingData_FilmList(response,start);
			break;
		case Send.NETWORK_FILMLIST_GETUPCOMING:
			start = request.getParameter("start");
			Send.UpcomingData_FilmList(response,start);
			break;
		case Send.NETWORK_MYCRITIC:
			tem = request.getParameter("userId");
			start = request.getParameter("start");
			Send.Mycritic(response,tem,start);
			break;
		case Send.NETWORK_SEATCHOOSE:
			tem = request.getParameter("cinemaId");
			String sessionId = request.getParameter("sessionId");
			filmId = request.getParameter("filmId");
			Send.Seat(response,tem,filmId,sessionId);
			break;
		case Send.NETWORK_TICKETRECORD:
			tem = request.getParameter("userId");
			start = request.getParameter("start");
			Send.TicketRecorder(response,tem,start);
			break;
		case Send.NETWORK_FILM_PICTURE:
			tem = request.getParameter("posterName");
			Send.FilmPictrue(response, this, tem);
			break;
		case Send.NETWORK_HOMEPAGE_GETBIGPOSTER:
			Send.BigPosters_HomePageFilm(response);
			break;
		case Send.NETWORK_HOMEPAGE_GETUPCOMING:
			Send.UpcomingData_HomepageFilm(response);
			break;
		case Send.NETWORK_HOMEPAGE_GETISSHOWING:
			Send.NowShowingData_HomepageFilm(response);
			break;
		case Send.NETWORK_PORTRAIT:
			tem = request.getParameter("portraitName");
			Send.Portrait(response,this,tem);
			break;
		case Send.NETWORK_FILMDETAIL_PLOT:
			tem = request.getParameter("filmId");
			Send.Plot(response,tem);
			break;
		case Send.NETWORK_SEARCH_FILM:
			tem = request.getParameter("filmNameLike");
			tem = URLDecoder.decode(tem,"UTF-8");
			start = request.getParameter("start");
			Send.SearchFilm(response, tem, start);
			break;
		case Send.NETWORK_CITY:
			tem = request.getParameter("cityId");
			start = request.getParameter("start");
			Send.City(response, tem, start);
			break;
		case Send.NETWORK_SEARCH_CINEMA:
			tem = request.getParameter("cityId");
			start = request.getParameter("start");
			String like = request.getParameter("cinemaNameLike");
			like=URLDecoder.decode(like,"UTF-8");
			Send.SearchCinema(response, like, tem, start);
			break;
		}
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int type = Integer.parseInt(request.getParameter("type"));
		String name = request.getParameter("postContent");
		System.out.println(new String(name.getBytes("iso-8859-1"),"UTF-8"));
		String c = request.getParameter("postContent");
		String content = URLDecoder.decode(c,"UTF-8");
		 
		String tem,start;
		switch(type){
		
		case Send.NETWORK_POST_LOGIN:
			Send.Login(response, content);
			break;
		case Send.NETWORK_POST_REGISTER:
			Send.Register(response, content);
			break;
		case Send.NETWORK_POST_PCENTER_EDIT:
			Send.PcenterEdit(response, content);
			break;
		case Send.NETWORK_POST_PCENTER_PSD:
			Send.PcenterPwd(response, content);
			break;
		case Send.NETWORK_POST_PCENTER_PHONE:
			Send.PcenterPhone(response, content);
			break;
		case Send.NETWORK_POST_PRAISE:
			Send.Praise(response, content);
			break;
		case Send.NETWORK_POST_COLLECT:
			Send.Collect(response, content);
			break;
		case Send.NETWORK_POST_CRITIC:
			Send.Critic(response, content);
			break;
		case Send.NETWORK_POST_BUYTICKET:
			Send.BuyTicket(response,content);
			break;
		
		}
		
		
		
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
