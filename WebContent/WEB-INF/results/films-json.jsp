<%-- Allows the jsp to use list functionality when printing --%>
<%@ page import="java.util.List" %>
<%-- Allows the jsp to use Gson converter to convert to JSON when printing --%>
<%@ page import="com.google.gson.Gson" %>
<%-- Allows the jsp to access the Film Class Object when printing --%>
<%@ page import="model.Film"%>


<%-- Below gets attribute by films string and stores them into a list of films --%>
<%-- Creates a gson object to convert to JSON--%>
<%-- Prints the attribute films out into JSON --%>

<%
List<Film> films = (List<Film>) request.getAttribute("films");
Gson gson = new Gson();
String jsonInString = gson.toJson(films);
response.getWriter().println(jsonInString);
%>