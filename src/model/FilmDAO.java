// This is in the package model as these are not server related
package model;

// Imports required for the Film DAO to talk to the MySQL Database
import java.util.ArrayList;
import java.sql.*;

// Main class
public class FilmDAO {
	
	
//Instance Fields which are declared here to minimise code bulk
	Film oneFilm = null;
	Connection conn = null;
    Statement stmt = null;
	String user = "birksd";
    String password = "Mel8quipt";
    String url = "jdbc:mysql://mudfoot.doc.stu.mmu.ac.uk:6306/"+user;

    
// Blank method for reference
	public FilmDAO() {}

// Open connection connects to the database using the JDBC driver
	private void openConnection(){
		// loading jdbc driver for mysql
		try{
		    Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch(Exception e) { System.out.println(e); }

		// connecting to database
		try{
			// connection string for demos database, username demos, password demos
 			conn = DriverManager.getConnection(url, user, password);
		    stmt = conn.createStatement();
		} catch(SQLException se) { System.out.println(se); }	   
    }
	private void closeConnection(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
// The method used to get the next film
	private Film getNextFilm(ResultSet rs){
    	Film thisFilm=null;
		try {
			thisFilm = new Film(
					rs.getInt("id"),
					rs.getString("title"),
					rs.getInt("year"),
					rs.getString("director"),
					rs.getString("stars"),
					rs.getString("review"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return thisFilm;		
	}
	
// This method returns all films from the MySQL database
	 public ArrayList<Film> getAllFilms(){
		   
			ArrayList<Film> allFilms = new ArrayList<Film>();
			openConnection();
			try{
			    String selectSQL = "SELECT * FROM films LIMIT 20";
			    ResultSet rs1 = stmt.executeQuery(selectSQL);
			    while(rs1.next()){
			    	oneFilm = getNextFilm(rs1);
			    	allFilms.add(oneFilm);
			   }
			    stmt.close();
			    closeConnection();
			} catch(SQLException se) { System.out.println(se); }

		   return allFilms;
	   }

	 
// This method returns a single film within the database which is identified by passing in a int value to read from the client
	   public Film getFilmByID(int id){
		   
			openConnection();
			oneFilm=null;
			try{
			    String selectSQL = "SELECT * FROM films WHERE id = " + id;
			    ResultSet rs1 = stmt.executeQuery(selectSQL);
			    while(rs1.next()){
			    	oneFilm = getNextFilm(rs1);
			    }
			    stmt.close();
			    closeConnection();
			} catch(SQLException se) { System.out.println(se); }

		   return oneFilm;
	   }

// The delete film function uses different code because Update and Delete statements require an executeUpdate() method
	   public Film deleteFilmByID(int id) {
		   
		   try{
			    Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch(Exception e) { System.out.println(e); }
			try{
				String selectSQL = "DELETE FROM films WHERE id = ?;";
	 			conn = DriverManager.getConnection(url, user, password);
			    PreparedStatement stmt = conn.prepareStatement(selectSQL);
			    stmt.setInt(1, id);
			    stmt.executeUpdate();
			} catch(SQLException se) { System.out.println(se); }	
	      
	      System.out.println("Database delete successful ");
		
		   return oneFilm;
	  
	   }
	   
// Update film takes all inputs from the Film.java using a setInt/String method
// Take note that the int id variable is not updateable! And is used as reference to which film is being altered.
	   public Film updateFilmToDatabase(int id, String title, int year, String director, String stars, String review) {
		   
		   try{
			    Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch(Exception e) { System.out.println(e); }

			try{
				String selectSQL = "UPDATE films SET title=?, year=?, director=?, stars=?, review=? WHERE id=? ;";
	 			conn = DriverManager.getConnection(url, user, password);
			    PreparedStatement stmt = conn.prepareStatement(selectSQL);
			    stmt.setString(1, title);
			    stmt.setInt(2, year);
			    stmt.setString(3,  director);
			    stmt.setString(4, stars);
			    stmt.setString(5,  review);
			    stmt.setInt(6, id);
			    stmt.executeUpdate();
			} catch(SQLException se) { System.out.println(se); }	
	      
	      System.out.println("Film updated successfully ");
		
		   return oneFilm;
	  
	   }

// This adds a film to the database. Unlike the above this int id variable does create a new id
	   public Film addFilmToDatabase(int id, String title, int year, String director, String stars, String review) {
		   
		   
		   try{
			    Class.forName("com.mysql.jdbc.Driver").newInstance();
			} catch(Exception e) { System.out.println(e); }

			try{
				String selectSQL = "INSERT INTO films (id, title, year, director, stars, review) VALUES (?, ?, ?, ?, ?, ?) ;";
	 			conn = DriverManager.getConnection(url, user, password);
			    PreparedStatement stmt = conn.prepareStatement(selectSQL);
			    stmt.setInt(1, id);
			    stmt.setString(2, title);
			    stmt.setInt(3,  year);
			    stmt.setString(4, director);
			    stmt.setString(5,  stars);
			    stmt.setString(6, review);
			    stmt.executeUpdate();
			} catch(SQLException se) { System.out.println(se); }	
	      
	      System.out.println("Film added successfully ");
		
		   return oneFilm;
	  
	   }

} // Main Method End
