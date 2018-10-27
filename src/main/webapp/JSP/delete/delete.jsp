<%@page import="java.util.Iterator"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="es.albarregas.crudpoolalvarofr.beans.Ave"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<Ave> aves = null;
    Iterator<Ave> iterador = null;
    if(request.getAttribute("aves_delete") != null){
	aves = (ArrayList) request.getAttribute("aves_delete");
	iterador = aves.iterator();
    }
    System.out.println((String) request.getAttribute("delete_query"));
    request.setAttribute("delete_query",(String) request.getAttribute("delete_query"));
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
	    <h1>Los elementos que se eliminarán son:</h1>
	    <%
		while (iterador.hasNext()) {
		    Ave ave = iterador.next();
	    %>
	    <ave>
		<span>Anilla:</span><span><%=ave.getAnilla()%></span><br>
		<span>Especie:</span><span><%=ave.getEspecie()%></span><br>
		<span>Lugar:</span><span><%=ave.getLugar()%></span><br>
		<span>Fecha:</span><span><%=ave.getFecha()%></span><br>
	    </ave>
	    <%}%>
	</section>
	<form class="botonera-derecha" action="Concluir" method="post">
	    <input class="delete-button" type="submit" name="operacion" value="delete">
	    <input class="sidebutton volver" type="submit" name="operacion" value="cancel">
	    <input type="hidden" name="delete_query" value="<%=(String) request.getAttribute("delete_query")%>">
	</form>
    </body>
</html>
