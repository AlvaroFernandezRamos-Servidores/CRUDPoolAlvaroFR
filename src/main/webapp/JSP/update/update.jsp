<%@page import="es.albarregas.crudpoolalvarofr.beans.Ave"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    boolean errores = false;
    if(request.getAttribute("error_anilla")!= null || request.getAttribute("error_repetida")!= null || request.getAttribute("error_especie")!= null || request.getAttribute("error_lugar")!= null){
	errores = true;
    }
    Ave ave = null;
    if(request.getAttribute("ave") != null){
	ave = (Ave) request.getAttribute("ave");
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
	    <h1>Introduce valores para la modificación</h1>
	    <form action="Concluir" method="post">
		<label>Anilla</label><span><%=ave.getAnilla()%></span><br>
		<label>Especie</label><input type="text" name="especie" <%if(request.getAttribute("error_especie") == null){%>placeholder="<%=ave.getEspecie()%>"<%}else if(request.getAttribute("error_especie") != null){%>error<%}%>><br>
		<label>Lugar</label><input type="text" name="lugar" <%if(request.getAttribute("error_lugar") == null){%>placeholder="<%=ave.getLugar()%>"<%}else if(request.getAttribute("error_lugar") != null){%>error<%}%>><br>
		<label>Fecha</label><input type="text" name="fecha" <%if(request.getAttribute("error_fecha") == null){%>placeholder="<%=ave.getFecha()%>"<%}else if(request.getAttribute("error_fecha") != null){%>error placeholder="AAAA-DD-MM"<%}%>><br>
		<input class="boton" type="submit" name="operacion" value="update">
		<input class="sidebutton volver" type="submit" name="operacion" value="cancel">
	    </form>
	</section>
    </body>
</html>
