<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insertar un nuevo Ave</title>
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet">
    </head>
    <body>
	<section>
	    <form action="JSP/create/create.jsp" method="post">
		<label>Anilla</label><input type="text" name="anilla"><br>
		<label>Especie</label><input type="text" name="especie"><br>
		<label>Lugar</label><input type="text" name="lugar"><br>
		<label>Fecha</label><input type="text" name="fecha" placeholder="AAAA-DD-MM"><br>
		<input type="submit" value="Insertar">
	    </form>
	</section>
    </body>
</html>
