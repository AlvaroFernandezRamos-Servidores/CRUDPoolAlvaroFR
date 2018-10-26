<%-- 
    Document   : finCreate.jsp
    Created on : Oct 24, 2018, 7:20:34 PM
    Author     : atomsk
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet">
    </head>
    <body>
	<section>
	    <h1>La inserción se realizó con éxito</h1>
	    <ave>
		<h2>El ave insertada es:</h2>
		<span>Anilla:</span><span><%=request.getParameter("anilla")%></span><br>
		<span>Especie:</span><span><%=request.getParameter("especie")%></span><br>
		<span>Lugar:</span><span><%=request.getParameter("lugar")%></span><br>
		<span>Fecha:</span><span><%=request.getParameter("fecha")%></span><br>
	    </ave>
	    <a class="sidebutton volver" href="Retornar">Volver</a>
	</section>
    </body>
</html>