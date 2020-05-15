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
@WebServlet("/deleteFilm")

//The main class of the controller which extends for Servlet functionality
public class DeleteFilmController extends HttpServlet {
	private static final long serialVersionUID = 1L;

// doGet Method handles the get request and is invoked by the web client
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
// Creating a Film DAO Object in this servlet
		FilmDAO fdao = new FilmDAO();
			
// Format string variable for use at the end of the url to determine where to server needs to look
	    String format = request.getParameter("format");
	    String outputPage = null;
	    	    
// This IF statement listens for the json string to be ?format=json
	    if("json".equals(format)) {
	    	String filmId = request.getParameter("filmId");
	    	Film films = fdao.deleteFilmByID(Integer.valueOf(filmId));
			request.setAttribute("films", films);

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
} // Main Method
