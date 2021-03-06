// The package controller stores all the servelets within it
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Film;
import model.FilmDAO;

//Allows the Servlet to be located by the JavaScript. Must extend with httpservlet
@WebServlet("/addAFilm")

//The main class of the controller which extends for Servlet functionality
public class AddAFilmController extends HttpServlet {
	private static final long serialVersionUID = 1L;

// doGet Method handles the get request and is invoked by the web client
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
// Allows to print formatted data
		PrintWriter out = response.getWriter();
		
// Creating a Film DAO Object in this servlet
		FilmDAO fdao = new FilmDAO();
				
// All variables below ties all parameters to the HTTP URL Request and setting them into variables to be used in the constructor below
	    String format = request.getParameter("format");
	    int id = Integer.valueOf(request.getParameter("id"));
	    String title = request.getParameter("title");
	    int year = Integer.valueOf(request.getParameter("year"));
	    String director = request.getParameter("director");
	    String stars = request.getParameter("stars");
	    String review = request.getParameter("review");
	    String outputPage = null;

// This IF statement listens for the json string to be ?format=json
	    if("json".equals(format)) {
	    	
// Passes all variables recieved from above through the method addFilmToDatabase from the Film DAO
	    	Film film = fdao.addFilmToDatabase(Integer.valueOf(id), title, Integer.valueOf(year), director, stars, review);
	 	   
// Creates a new variable to store the  returned film in a single list
	    	List<Film> films = new ArrayList<Film>();
	 	    films.add(film);			
	 	    
// Sets and passes the variable and sends it to the films-json to do all the printing
	 	    request.setAttribute("films", films);
	    	response.setContentType("application/json");
		    outputPage = "/WEB-INF/results/films-json.jsp";
		    
		    
// This IF statement listens for the xml string to be ?format=xml
	    } else if ("xml".equals(format)) {
	    	ArrayList<Film> films = fdao.getAllFilms();
	    	
// Sets and passes the variable and sends it to the films-xml to do all the printing
			request.setAttribute("films", films);
	    	response.setContentType("text/xml");
		    outputPage = "/WEB-INF/results/films-xml.jsp";
		 
// Sends through to results/ films-string.jsp as plain text if neither the above are selected
	    } else {
	      response.setContentType("text/plain");
	      outputPage = "/WEB-INF/results/films-string.jsp";
	    }
// Dispatcher passes the request block onto whichever outputpage is selected in the IF statement
	    RequestDispatcher dispatcher =
	      request.getRequestDispatcher(outputPage);
	    response.setStatus(200);
	    dispatcher.include(request, response);
	    
	}

// Do post runs the do get method above
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
} // Main method end
