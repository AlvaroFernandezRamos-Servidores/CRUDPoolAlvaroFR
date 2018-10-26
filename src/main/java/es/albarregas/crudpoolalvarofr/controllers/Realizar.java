package es.albarregas.crudpoolalvarofr.controllers;

import es.albarregas.crudpoolalvarofr.beans.Ave;
import es.albarregas.crudpoolalvarofr.connections.Conexion;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import org.apache.commons.beanutils.BeanUtils;

@WebServlet(name = "Realizar", urlPatterns = {"/Realizar"})
public class Realizar extends HttpServlet {
    
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
	DataSource ds = null;
	Connection con = null;
	try {
	    ds = Conexion.conectar();
	    con = ds.getConnection();
	    if(request.getParameter("operacion")!=null){
		switch(request.getParameter("operacion")){
		    case "create":realizar_create(request,response,con);break;
		    case "read":realizar_read(request,response,con);break;
		    case "update":fetch_update(request,response,con);break;
		    case "cancel":request.getRequestDispatcher("Retornar").forward(request,response);break;
		}
	    }
	} catch (ClassNotFoundException ex) {
	    Logger.getLogger(Realizar.class.getName()).log(Level.SEVERE, null, ex);
	} catch (NamingException ex) {
	    Logger.getLogger(Realizar.class.getName()).log(Level.SEVERE, null, ex);
	} catch (SQLException ex) {
	    Logger.getLogger(Realizar.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
    protected void realizar_read(HttpServletRequest request,HttpServletResponse response, Connection con) throws SQLException{
	PreparedStatement sql = null;
	ResultSet resultado = null;
	ArrayList<Ave> aves = new ArrayList();
	try {
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
	    request.getRequestDispatcher("/JSP/read/leer.jsp").forward(request,response);
	} catch (SQLException ex) {
	    Logger.getLogger(Concluir.class.getName()).log(Level.SEVERE, null, ex);
	} catch (ServletException ex) {
	    Logger.getLogger(Realizar.class.getName()).log(Level.SEVERE, null, ex);
	} catch (IOException ex) {
	    Logger.getLogger(Realizar.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
    
    protected void realizar_create(HttpServletRequest request,HttpServletResponse response, Connection con) throws SQLException{
	Ave ave = new Ave();
	boolean errores = false;
	try {
	    //Poblamos el objeto ave
	    BeanUtils.populate(ave,request.getParameterMap());
	    //Comprobamos los errores campo a campo
	    if(ave.getAnilla().length()>3){
		request.setAttribute("error_anilla",request.getParameter("anilla"));
		errores = true;
	    }
	    if(ave.getEspecie().length()>20){
		request.setAttribute("error_especie",request.getParameter("especie"));
		errores = true;
	    }
	    if(ave.getLugar().length()>50){
		request.setAttribute("error_lugar",request.getParameter("lugar"));
		errores = true;
	    }
	} catch (IllegalAccessException ex) {
	    Logger.getLogger(Realizar.class.getName()).log(Level.SEVERE, null, ex);
	} catch (InvocationTargetException ex) {
	    Logger.getLogger(Realizar.class.getName()).log(Level.SEVERE, null, ex);
	}
	PreparedStatement sql = null;
	ResultSet resultado = null;
	sql = con.prepareStatement("SELECT * FROM aves WHERE anilla = ?;");
	sql.setString(1,request.getParameter("anilla"));
	resultado = sql.executeQuery();
	if(resultado.next()){
	    request.setAttribute("error_repetida",request.getParameter("anilla"));
		errores = true;
	}
	if(errores){
	    request.setAttribute("operacion","create");
	    try {
		request.getRequestDispatcher("Operacion").forward(request, response);
	    } catch (ServletException ex) {
		Logger.getLogger(Realizar.class.getName()).log(Level.SEVERE, null, ex);
	    } catch (IOException ex) {
		Logger.getLogger(Realizar.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}else{
	    try {
		sql = con.prepareStatement("INSERT INTO aves VALUES (?,?,?,?)");
		sql.setString(1,ave.getAnilla());
		sql.setString(2,ave.getEspecie());
		sql.setString(3,ave.getLugar());
		sql.setString(4,ave.getFecha());
		sql.executeUpdate();
		Conexion.CloseConnection(con);
		request.getRequestDispatcher("/JSP/create/finCreate.jsp").forward(request,response);
	    } catch (ServletException ex) {
		Logger.getLogger(Realizar.class.getName()).log(Level.SEVERE, null, ex);
	    } catch (IOException ex) {
		Logger.getLogger(Realizar.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
    }
    
    protected void fetch_update(HttpServletRequest request,HttpServletResponse response, Connection con){
	//Comprobamos que venga al menos una selección en los parámetros
	System.out.println(request.getParameterValues("aves_update") == null);
	if(request.getParameterValues("aves_update") == null || request.getParameterValues("aves_update").length<1){
	    try {
		request.getRequestDispatcher("Operacion").forward(request,response);
	    } catch (ServletException ex) {
		Logger.getLogger(Realizar.class.getName()).log(Level.SEVERE, null, ex);
	    } catch (IOException ex) {
		Logger.getLogger(Realizar.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
	Ave ave = new Ave();
	PreparedStatement sql = null;
	ResultSet resultado = null;
	try {
	    sql = con.prepareStatement("SELECT * FROM aves WHERE anilla = ?;");
	    sql.setString(1,request.getParameterValues("aves_update")[0]);
	    resultado = sql.executeQuery();
	    if(resultado.next()){
		ave.setAnilla(resultado.getString("anilla"));
		ave.setEspecie(resultado.getString("especie"));
		ave.setLugar(resultado.getString("lugar"));
		ave.setFecha(resultado.getString("fecha"));
		request.setAttribute("ave",ave);
	    }
	    Conexion.CloseConnection(con);
	    request.getRequestDispatcher("/JSP/update/update.jsp").forward(request,response);
	} catch (SQLException ex) {
	    Logger.getLogger(Realizar.class.getName()).log(Level.SEVERE, null, ex);
	} catch (ServletException ex) {
	    Logger.getLogger(Realizar.class.getName()).log(Level.SEVERE, null, ex);
	} catch (IOException ex) {
	    Logger.getLogger(Realizar.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
    
    @Override
    public String getServletInfo() {
	return "Short description";
    }// </editor-fold>

}
