package es.albarregas.crudpoolalvarofr.controllers;

import es.albarregas.crudpoolalvarofr.beans.Ave;
import es.albarregas.crudpoolalvarofr.connections.Conexion;
import es.albarregas.crudpoolalvarofr.utils.MyLogger;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

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
	    //Dependiendo del parámetro que recibamos redirigimos a un sitio u otro
	    //Update y delete necesitan recoger aves de la BD antes de redirigir
	    switch(request.getParameter("operacion")){
		case "create":direccion += "create/inicioCreate.jsp";break;
		case "read":direccion = "Realizar";break;
		case "update":operacion_get_aves(request,response);direccion += "update/inicioUpdate.jsp";break;
		case "delete":operacion_get_aves(request,response);direccion += "delete/inicioDelete.jsp";break;
	    }
	    
	    request.getRequestDispatcher(direccion).forward(request, response);
	}
    }
    
    public void operacion_get_aves(HttpServletRequest request,HttpServletResponse response){
	//Este método recupera las aves de la BD y las inserta como atributo
	PreparedStatement sql = null;
	ResultSet resultado = null;
	ArrayList<Ave> aves = new ArrayList();
	try {
	    DataSource ds = Conexion.conectar();
	    Connection con = ds.getConnection();
	    sql = con.prepareStatement("SELECT * FROM aves;");
	    resultado = sql.executeQuery();
	    while(resultado.next()){
		Ave ave = new Ave();
		ave.setAnilla(resultado.getString("anilla"));
		ave.setEspecie(resultado.getString("especie"));
		ave.setLugar(resultado.getString("lugar"));
		ave.setFecha(resultado.getString("fecha"));
		aves.add(ave);
	    }
	    request.setAttribute("aves",aves);
	    Conexion.CloseConnection(con);
	} catch (SQLException ex) {
	    MyLogger.doLog(ex,Conexion.class, "fatal");
	}
    }
    
    @Override
    public String getServletInfo() {
	return "Short description";
    }// </editor-fold>

}
