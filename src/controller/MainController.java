// This is a normal class for testing in controller for reference
package controller;

import java.util.ArrayList;

import com.google.gson.Gson;

import model.Film;
import model.FilmDAO;

// This is to test code and return films into the console for testing and debugging!
public class MainController {

	
// Main Method
	public static void main(String[] args) {
		
// Decaring new object fdao to reference FILM DAO
		FilmDAO fdao = new FilmDAO();
		
// This runs the method get all films and stores the outcome in the  ArrayList variable films
		ArrayList<Film> films = fdao.getAllFilms();
		
		
// This is where you can test your code!
		
		
	}

}
