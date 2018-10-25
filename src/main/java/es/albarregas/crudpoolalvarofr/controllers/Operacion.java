package es.albarregas.crudpoolalvarofr.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Operacion", urlPatterns = {"/Operacion"})
public class Operacion extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	processRequest(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	processRequest(request, response);
	String direccion = "JSP/";
	if(request.getParameter("operacion") != null){
	    switch(request.getParameter("operacion")){
		case "create":direccion += "create/inicioCreate.jsp";break;
		case "read":direccion = "Realizar";break;
		case "update":direccion += "";break;
		case "delete":direccion += "";break;
	    }
	    
	    request.getRequestDispatcher(direccion).forward(request, response);
	}
    }
    
    @Override
    public String getServletInfo() {
	return "Short description";
    }// </editor-fold>

}
