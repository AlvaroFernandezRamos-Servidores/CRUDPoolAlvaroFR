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
	    <h1>Registros eliminados con éxito</h1>
	<form action="Retornar" method="post">
	    <input class="sidebutton volver" type="submit" name="operacion" value="cancel">
	</form>
	</section>
    </body>
</html>
