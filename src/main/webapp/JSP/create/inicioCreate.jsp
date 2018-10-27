<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String errorAnilla = "";
    boolean errores = false;
    if(request.getAttribute("error_anilla")!= null){
	errorAnilla = (String) request.getAttribute("error_anilla");
    }else if(request.getAttribute("error_repetida")!= null){
	errorAnilla = (String) request.getAttribute("error_repetida");
    }
    if(request.getAttribute("error_anilla")!= null || request.getAttribute("error_repetida")!= null || request.getAttribute("error_especie")!= null || request.getAttribute("error_lugar")!= null){
	errores = true;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insertar un nuevo Ave</title>
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet">
    </head>
    <body>
	<section>
	    <h1>Inserción de Ave</h1>
	    <%if(errores){
	    %>
		<h2 class="erroresHeader">Existen errores en el formulario</h2>
	    <%
	    }%>
	    <form action="Realizar" method="post">
		<label>Anilla</label><input type="text" name="anilla" <%if(errorAnilla.length()<1 && request.getParameter("anilla") != null){%>value="<%=request.getParameter("anilla")%>"<%}else if(errorAnilla.length()>0){%>error<%}%>><br>
		<label>Especie</label><input type="text" name="especie" <%if(request.getAttribute("error_especie") == null && request.getParameter("especie") != null){%>value="<%=request.getParameter("especie")%>"<%}else if(request.getAttribute("error_especie") != null){%>error<%}%>><br>
		<label>Lugar</label><input type="text" name="lugar" <%if(request.getAttribute("error_lugar") == null && request.getParameter("lugar") != null){%>value="<%=request.getParameter("lugar")%>"<%}else if(request.getAttribute("error_lugar") != null){%>error<%}%>><br>
		<label>Fecha</label><input type="text" name="fecha" placeholder="AAAA-DD-MM"<%if(request.getAttribute("error_fecha") == null && request.getParameter("fecha") != null){%>value="<%=request.getParameter("fecha")%>"<%}else if(request.getAttribute("error_fecha") != null){%>error<%}%>><br>
		<input class="boton" type="submit" name="operacion" value="create">
		<input class="sidebutton volver" type="submit" name="operacion" value="cancel">
	    </form>
	</section>
    </body>
</html>
