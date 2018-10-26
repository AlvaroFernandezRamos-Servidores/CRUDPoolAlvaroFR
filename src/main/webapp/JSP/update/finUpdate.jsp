<%-- 
    Document   : finUpdate
    Created on : Oct 26, 2018, 1:42:19 PM
    Author     : atomsk
--%>

<%@page import="es.albarregas.crudpoolalvarofr.beans.Ave"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    Ave ave = null,nuevaAve = null;
    if(request.getAttribute("update_ave") != null){
	ave = (Ave) request.getAttribute("update_ave");
	System.out.println("tengo ave");
    }
    if(request.getAttribute("update_nuevaAve") != null){
	nuevaAve = (Ave) request.getAttribute("update_nuevaAve");
	System.out.println("tengo nueva ave");
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
	    <h1>La actualización se realizó con éxito</h1>
	    <ave>
		<h2>Los valores anteriores eran:</h2>
		<span>Anilla:</span><span><%=ave.getAnilla()%></span><br>
		<span>Especie:</span><span><%=ave.getEspecie()%></span><br>
		<span>Lugar:</span><span><%=ave.getLugar()%></span><br>
		<span>Fecha:</span><span><%=ave.getFecha()%></span><br>
	    </ave>
	    <ave>
		<h2>Los nuevos valores son:</h2>
		<span>Anilla:</span><span><%=nuevaAve.getAnilla()%></span><br>
		<span>Especie:</span><span><%=nuevaAve.getEspecie()%></span><br>
		<span>Lugar:</span><span><%=nuevaAve.getLugar()%></span><br>
		<span>Fecha:</span><span><%=nuevaAve.getFecha()%></span><br>
	    </ave>
	    <a class="sidebutton volver" href="Retornar">Volver</a>
	</section>
    </body>
</html>
