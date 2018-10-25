package es.albarregas.crudpoolalvarofr.controllers;

import es.albarregas.crudpoolalvarofr.connections.Conexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Concluir", urlPatterns = {"/Concluir"})
public class Concluir extends HttpServlet {
    
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
	
	PreparedStatement sql = null;
	Connection con = null;
	ResultSet resultado = null;
//	try {
//	    con = Conexion.conectar();
//	} catch (ClassNotFoundException ex) {
//	    Logger.getLogger(Concluir.class.getName()).log(Level.SEVERE, null, ex);
//	} catch (NamingException ex) {
//	    Logger.getLogger(Concluir.class.getName()).log(Level.SEVERE, null, ex);
//	}
//	if(request.getParameter("operacion") != null){
//	    switch(request.getParameter("operacion")){
//		case "update":break;
//		case "delete":break;
//	    }
//	}
	
    }
    
    @Override
    public String getServletInfo() {
	return "Short description";
    }// </editor-fold>

}
