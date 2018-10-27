<%-- 
    Document   : inicioDelete
    Created on : Oct 26, 2018, 7:22:17 PM
    Author     : atomsk
--%>

<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="es.albarregas.crudpoolalvarofr.beans.Ave"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<Ave> aves = null;
    Iterator<Ave> iterador = null;
    if(request.getAttribute("aves") != null){
	aves = (ArrayList) request.getAttribute("aves");
	iterador = aves.iterator();
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet">
    </head>
    <body>
        <section>
	    <h1>Seleccione una o más aves para eliminar</h1>
	    <form action="Realizar" method="post">
		<%
		    if(!iterador.hasNext()){
		%>
		<span>No existen resultados con esa anilla</span>    
		<%
		    } else { while (iterador.hasNext()) {
			Ave ave = iterador.next();
		%>
		<input type="checkbox" name="aves_update" value="<%=ave.getAnilla()%>" id="<%=ave.getAnilla()%>"><label for="<%=ave.getAnilla()%>"><%=ave.getEspecie()%></label><br>
		<%
			}
		    }
		%>
		<input class="boton" type="submit" name="operacion" value="delete">
		<input class="sidebutton volver" type="submit" name="operacion" value="cancel">
	    </form>
	</section>
    </body>
</html>
