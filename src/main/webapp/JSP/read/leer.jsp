<%-- 
    Document   : leer
    Created on : Oct 23, 2018, 7:49:45 PM
    Author     : atomsk
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="es.albarregas.crudpoolalvarofr.beans.Ave"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ArrayList<Ave> aves = (ArrayList) request.getAttribute("aves");
    Iterator<Ave> iterador = aves.iterator();
    String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet">
        <title>VISUALIZACION DE VARIOS ELEMENTOS</title>
	<script>
	    document.addEventListener('DOMContentLoaded',function(){
		document.querySelector('section').addEventListener('click',function(e){
		    if(e.target.tagName == "ARTICLE"){
			e.target.classList.toggle('shown');
			if(document.querySelectorAll('article:not(.shown)').length<1){
			    document.querySelector('#showAll').innerHTML = 'Ocultar todas';
			    document.querySelector('#showAll').val = "hide";
			}
			if(document.querySelectorAll('article.shown').length<1){
			    document.querySelector('#showAll').val = "show";
			    document.querySelector('#showAll').innerHTML = 'Mostrar todas';
			}
		    }
		    
		});
		
		document.querySelector('#showAll').addEventListener('click',function(e){
		    if(typeof(e.target.val)=="undefined"){e.target.val = "show";}
		    if(e.target.val == "show"){
			document.querySelectorAll('section article').forEach((i)=>{
			    i.classList.add('shown');
			});
			e.target.innerHTML = 'Ocultar todas';
			e.target.val = "hide";
		    }else{
			document.querySelectorAll('section article').forEach((i)=>{
			    i.classList.remove('shown');
			});
			e.target.val = "show";
			e.target.innerHTML = 'Mostrar todas';
		    }
		});
	    });
	</script>
    </head>
    <body>
	<section>
	    <%
		if(!iterador.hasNext()){
	    %>
	    <span>No existen resultados con esa anilla</span>    
	    <%
		}else{
		    while (iterador.hasNext()) {
			Ave ave = iterador.next();
	    %>
	    <article>
	    <span>Anilla :<%=ave.getAnilla()%></span>
	    <span>Especie :<%=ave.getEspecie()%></span>
	    <span>Lugar :<%=ave.getLugar()%></span>
	    <span>Fecha :<%=ave.getFecha()%></span>
	    </article>
	    <%
		    }
		}
	    %>
	    <a class="sidebutton volver" href="<%=path%>/index.html">Volver</a>
	    <button class="sidebutton" id="showAll">Mostrar todas</button>
	</section>
    </body>
</html>
