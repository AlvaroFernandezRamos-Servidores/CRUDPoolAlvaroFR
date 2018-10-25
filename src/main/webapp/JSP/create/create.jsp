<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet">
    </head>
    <body>
        <span>El ave que se va a insertar es:</span><br>
	<span>Anilla:</span><span><%=request.getParameter("anilla")%></span><br>
	<span>Especie:</span><span><%=request.getParameter("especie")%></span><br>
	<span>Lugar:</span><span><%=request.getParameter("lugar")%></span><br>
	<span>Fecha:</span><span><%=request.getParameter("fecha")%></span><br>
	
	<form action="<%=request.getContextPath()%>/Realizar" method="post">
	    <input type="hidden" name="anilla" value="<%=request.getParameter("anilla")%>">
	    <input type="hidden" name="especie" value="<%=request.getParameter("especie")%>">
	    <input type="hidden" name="lugar" value="<%=request.getParameter("lugar")%>">
	    <input type="hidden" name="fecha" value="<%=request.getParameter("fecha")%>">
	    <input type="submit" name="operacion" value="create">
	    <input type="submit" name="operacion" value="cancel">
	</form>
    </body>
</html>
