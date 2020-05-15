// This function builds the request object so send requests using ajax
function getRequestObject() {
  if (window.XMLHttpRequest) {
    return(new XMLHttpRequest());
  } else if (window.ActiveXObject) { 
    return(new ActiveXObject("Microsoft.XMLHTTP"));
  } else {
    return(null); 
  }
}

// This inserts the html data into the element that has the specified id.
function htmlInsert(id, htmlData) {
  document.getElementById(id).innerHTML = htmlData;
}

// This returns the escaped value of textfield that has given id.
// The builtin "escape" function url-encodes characters.
function getValue(id) {
  return(escape(document.getElementById(id).value));
}

// Generalized version of ajaxResultPost. In this
// version, you pass in a response handler function
// instead of just a result region.
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

// This when recieves an element, returns the body content.
function getBodyContent(element) {
  element.normalize();
  return(element.childNodes[0].nodeValue);
}

// Once recieved the name of an XML element, this returns an 
// array of the values of all elements with that name.
function getXmlValues(xmlDocument, xmlElementName) {
  var elementArray = 
     xmlDocument.getElementsByTagName(xmlElementName);
  var valueArray = new Array();
  for(var i=0; i<elementArray.length; i++) {
     valueArray[i] = getBodyContent(elementArray[i]);
  }
  return(valueArray);
}

// Given an element object and an array of sub-element names,
// returns an array of the values of the sub-elements.
function getElementValues(element, subElementNames) {
  var values = new Array(subElementNames.length);
  for(var i=0; i<subElementNames.length; i++) {
    var name = subElementNames[i];
    var subElement = element.getElementsByTagName(name)[0];
    values[i] = getBodyContent(subElement);
  }
  return(values);
}

// Takes as input an array of headings (to go into th elements)
// and an array-of-arrays of rows (to go into td
// elements). Builds an xhtml table from the data.
function getTable(headings, rows) {
  var table = "<table border='1' class='ajaxTable'>\n" +
              getTableHeadings(headings) +
              getTableBody(rows) +
              "</table>";
  return(table);
}

// This gets the amount of headings iterates through them
function getTableHeadings(headings) {
  var firstRow = "  <tr>";
  for(var i=0; i<headings.length; i++) {
    firstRow += "<th>" + headings[i] + "</th>";
  }
  firstRow += "</tr>\n";
  return(firstRow);
}

// This gets the amount of rows and iterates through them
function getTableBody(rows) {
  var body = "";
  for(var i=0; i<rows.length; i++) {
    body += "  <tr>";
    var row = rows[i];
    for(var j=0; j<row.length; j++) {
      body += "<td>" + row[j] + "</td>";
    }
    body += "</tr>\n";
  }
  return(body);
}