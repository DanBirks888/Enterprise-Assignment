// This function passes two variables.
// One for the type of displaying table the client selected in the browser (json/xml/text)
// One for the different input fields the client has input in the browser
function deleteFilmById(inputField, resultsRegion) {

var servletName = "deleteFilm"; 
var inputFieldValue = getValue(inputField);

var data = "filmId=" + inputFieldValue + "&format=json";

ajaxPost(servletName, data, function(request) { 
	showJsonFilmInfo(request, resultsRegion); 
});

}

// This passes in the variables required to iterate through a JSON array list of films
function showJsonFilmInfo(request, resultRegion) {
  if ((request.readyState == 4) && (request.status == 200)) {
    var rawData = request.responseText;
    var films = eval("(" + rawData + ")");
    var rows = new Array();
    for(var i=0; i<films.length; i++) {
      var film = films[i];
      rows[i] = [film.id, film.title, film.year, film.director, film.stars, film.review];
    }
    var table = getFilmTable(rows);
    htmlInsert(resultRegion, table);
  }
}

// THis passes in the variables required to iterate through an XML array list of films
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

// This confirms what variables shall be displayed in each table row
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