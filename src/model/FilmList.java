package model;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAccessType;


// For JAXB to discover the root element to ensure reference point is correct
@XmlRootElement (name = "filmlist")
@XmlAccessorType (XmlAccessType.FIELD)


// Main method used for reference in servlets and jsp files
public class FilmList {
	@XmlElement(name="film")
	private List<Film> films;
	public FilmList(List<Film> inFilms) {
	films=inFilms;
}

// Black FilmList method for reference
	public FilmList() {}
}
