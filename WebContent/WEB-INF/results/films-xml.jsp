<%-- Allows the jsp to use list functionality when printing --%>
<%@ page import="java.util.List"%>
<%-- Allows the jsp to use JAXB libararies when printing --%>
<%@ page import="javax.xml.bind.JAXBContext"%>
<%-- Allows the jsp to use exception handling when printing --%>
<%@ page import="javax.xml.bind.JAXBException"%>
<%-- Allows the jsp to use Marshaller which is used to serialise java content to XML --%>
<%@ page import="javax.xml.bind.Marshaller"%>
<%-- Allows the jsp to access the Film Class Object when printing --%>
<%@ page import="model.Film"%>
<%-- Allows the jsp to access the Film List Object when printing --%>
<%@ page import="model.FilmList"%>
<%-- Allows the jsp to remove the first 6 white spaces in the browser which are caused by these imports --%>
<%@ page trimDirectiveWhitespaces = "true" %>


<%-- Below gets attribute by films string and stores them into a list of films --%>
<%-- JAXB gets attributes from FilmList --%>
<%-- Creates a gson object to convert to JSON--%>
<%-- Prints the attribute films out into JSON --%>
<%
FilmList films = new FilmList((List<Film>) request.getAttribute("films"));
try {
JAXBContext jaxbContext = JAXBContext.newInstance(FilmList.class);
Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
jaxbMarshaller.marshal(films, out);
} catch (JAXBException e) {e.printStackTrace();
}
%>