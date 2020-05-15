// This function passes seven variables.
// One for the type of displaying table the client selected in the browser (json/xml/text)
// Six for the different input fields the client has input in the browser
function updateFilmToJson(resultsRegion, id, title, year, director, stars, review) {
	
	var servletName = "updateAFilm"; 
	
	
	// Gets the parameteres passed into the function and stores them in variables
	var id1 = getValue(id);
	var title1 = getValue(title);
	var year1 = getValue(year);
	var director1 = getValue(director)
	var stars1 = getValue(stars);
	var review1 = getValue(review);
	
	
	// Passes All variables in one single variable = data
	var data = "id=" + id1 + "&title=" + title1 + "&year=" + year1 + "&director=" + director1 + "&stars=" + stars1 + "&review=" + review1 + "&format=json";
	
	// Takes the servlet name, all the data, and posts it to the servlet
	ajaxPost(servletName, data, function(request) { 
		showJsonFilmInfo(request, resultsRegion); 
    });
	
}

// This passes in the variables requred to iterate through a JSON array list of films
function showJsonFilmInfo(request, resultRegion) {
	  if ((request.readyState == 4) && (request.status == 200)) {
	    var rawData = request.responseText;
	    var films = eval("(" + rawData + ")");
	    
// Creates an Array List to store the films in
	    var rows = new Array();
	    
// Iterates through the array of films one at a time which each row containing all six values per object
	    for(var i=0; i<films.length; i++) {
	      var film = films[i];
	      rows[i] = [film.id, film.title, film.year, film.director, film.stars, film.review];
	    }
	    var table = getFilmTable(rows);
	    htmlInsert(resultRegion, table);
	  }
	}

// This passes in the variables required to iterate through an XML array list of films
	function showXmlFilmInfo(request, resultRegion) {
	  if ((request.readyState == 4) &&
	      (request.status == 200)) {
	    var xmlDocument = request.responseXML;
	    var films = 
	      xmlDocument.getElementsByTagName("film");
	    var rows = new Array();
	    for(var i=0; i<films.length; i++) {
	      var film = films[i];
	      var subElements = 
	        ["id", "title", "year", "director", "stars", "review"];
	      rows[i] = getElementValues(film, subElements);
	    }
	    var table = getFilmTable(rows);
	    htmlInsert(resultRegion, table);
	  }
	}

	function getFilmTable(rows) {
	  var headings = 
	    [ "ID", "Title", "Year", "Director", "Stars", "Review" ];
	  return(getTable(headings, rows));
	}


function ajaxPost(address, data, responseHandler) {
  var request = getRequestObject();
  request.onreadystatechange = 
    function() { responseHandler(request); };
  request.open("POST", address, true);
  request.setRequestHeader("Content-Type", 
                           "application/x-www-form-urlencoded");
  console.log("sending request");
  request.send(data);
}