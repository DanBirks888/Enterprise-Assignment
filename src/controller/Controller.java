// The package controller stores all the servelets within it
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Film;
import model.FilmDAO;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Controller() {
        super(); }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		PrintWriter out = response.getWriter();
		
// Create the variable to refer to the Data Access Object!
		FilmDAO fdao = new FilmDAO();
		
		
// Asks DAO to get the films here =
		ArrayList<Film> films = fdao.getAllFilms();
		
		
// This puts those films in the request block below! using request.setAttribute...
		request.setAttribute("films", films);
// This needs a parameter called format or whatever because 
	    String format = request.getParameter("format");
	    String outputPage;
	    
	    
// Which format will be passed? JSON, XML, HTML...
		 if ("xml".equals(format)) {
		      response.setContentType("text/xml");
		      outputPage = "/WEB-INF/results/films-xml.jsp";
		    } else if ("json".equals(format)) {
		      response.setContentType("application/json");
		      outputPage = "/WEB-INF/results/films-json.jsp";
		    } else {
		      response.setContentType("text/plain");
		      outputPage = "/WEB-INF/results/films-string.jsp";
		    }
		 
// Passes the request off to where ever in the for loop it sends off to do the printing!
		    RequestDispatcher dispatcher =
		      request.getRequestDispatcher(outputPage);
		    dispatcher.include(request, response);
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
