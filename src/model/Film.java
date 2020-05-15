// This is in the package model as these are not server related
package model;

// The reusable object Film which will be referred throughout the code
public class Film {
	
	// Contrustor for the Film Object
   public Film(int id, String title, int year, String director, String stars,
			String review) {
		super();
		this.id = id;
		this.title = title;
		this.year = year;
		this.director = director;
		this.stars = stars;
		this.review = review;
	}

// Variables 
   int id;
   String title;
   int year;
   String director;
   String stars;
   String review;
   

// Getters and setters to provide a control over read/write of the properties
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public int getYear() {
	return year;
}
public void setYear(int year) {
	this.year = year;
}
public String getDirector() {
	return director;
}
public void setDirector(String director) {
	this.director = director;
}
public String getStars() {
	return stars;
}
public void setStars(String stars) {
	this.stars = stars;
}
public String getReview() {
	return review;
}
public void setReview(String review) {
	this.review = review;
}

// toString is for converting all variables into a string for the URL http request to recognise
@Override
public String toString() {
	return "Film [id=" + id + ", title=" + title + ", year=" + year
			+ ", director=" + director + ", stars=" + stars + ", review="
			+ review + "]";
}   
}
