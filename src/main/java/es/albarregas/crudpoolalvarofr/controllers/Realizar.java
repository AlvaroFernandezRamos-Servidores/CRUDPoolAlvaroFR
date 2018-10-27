package es.albarregas.crudpoolalvarofr.controllers;

import es.albarregas.crudpoolalvarofr.beans.Ave;
import es.albarregas.crudpoolalvarofr.connections.Conexion;
import es.albarregas.crudpoolalvarofr.utils.MyLogger;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
		    case "update":fetch_elements(request,response,con);request.getRequestDispatcher("/JSP/update/update.jsp").forward(request,response);break;
		    case "delete":fetch_elements(request,response,con);request.getRequestDispatcher("/JSP/delete/delete.jsp").forward(request,response);break;
		    case "cancel":request.getRequestDispatcher("Retornar").forward(request,response);break;
		}
	    }
	} catch (SQLException ex) {
	    MyLogger.doLog(ex,Conexion.class, "fatal");
	}
    }
    protected void realizar_read(HttpServletRequest request,HttpServletResponse response, Connection con){
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
	    MyLogger.doLog(ex,Conexion.class, "fatal");
	} catch (ServletException ex) {
	    MyLogger.doLog(ex,Conexion.class, "error");
	} catch (IOException ex) {
	    MyLogger.doLog(ex,Conexion.class, "error");
	}
    }
    
    protected void realizar_create(HttpServletRequest request,HttpServletResponse response, Connection con){
	Ave ave = new Ave();
	boolean errores = false;
	try {
	    //Si no existen todos los parametros necesarios, reconducimos al controller
	    if((request.getParameter("anilla") == null) ||
		request.getParameter("anilla").length() < 1 ||    
		request.getParameter("especie") == null || 
		request.getParameter("especie").length() < 1 ||   
		request.getParameter("lugar") == null || 
		request.getParameter("lugar").length() < 1 ||   
		request.getParameter("fecha") == null ||
		request.getParameter("fecha").length() < 1){
		request.getRequestDispatcher("Operacion").forward(request,response);
	    }
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
	    MyLogger.doLog(ex,Conexion.class, "fatal");
	} catch (InvocationTargetException ex) {
	    MyLogger.doLog(ex,Conexion.class, "warn");
	} catch (ServletException ex) {
	    MyLogger.doLog(ex,Conexion.class, "error");
	} catch (IOException ex) {
	    MyLogger.doLog(ex,Conexion.class, "error");
	}
	PreparedStatement sql = null;
	ResultSet resultado = null;
	try {
	    sql = con.prepareStatement("SELECT * FROM aves WHERE anilla = ?;");
	    sql.setString(1,request.getParameter("anilla"));
	    resultado = sql.executeQuery();
	    if(resultado.next()){
		request.setAttribute("error_repetida",request.getParameter("anilla"));
		    errores = true;
	    }
	} catch (SQLException ex) {
		MyLogger.doLog(ex,Conexion.class, "fatal");
	}
	if(errores){
	    request.setAttribute("operacion","create");
	    try {
		request.getRequestDispatcher("Operacion").forward(request, response);
	    } catch (ServletException ex) {
		MyLogger.doLog(ex,Conexion.class, "error");
	    } catch (IOException ex) {
		MyLogger.doLog(ex,Conexion.class, "error");
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
		MyLogger.doLog(ex,Conexion.class, "error");
	    } catch (IOException ex) {
		MyLogger.doLog(ex,Conexion.class, "error");
	    } catch (SQLException ex) {
		MyLogger.doLog(ex,Conexion.class, "fatal");
	    }
	}
    }
    
    protected void fetch_elements(HttpServletRequest request,HttpServletResponse response, Connection con){
	//Comprobamos que venga al menos una selección en los parámetros
	if(request.getParameterValues("aves_update") == null || request.getParameterValues("aves_update").length<1){
	    try {
		request.getRequestDispatcher("Operacion").forward(request,response);
	    } catch (ServletException ex) {
		MyLogger.doLog(ex,Conexion.class, "error");
	    } catch (IOException ex) {
		MyLogger.doLog(ex,Conexion.class, "error");
	    }
	}
	Ave ave = new Ave();
	PreparedStatement sql = null;
	ResultSet resultado = null;
	try {
	    if(request.getParameter("operacion").equals("delete")){
		String sentencia = "SELECT * FROM aves WHERE ";
		for(int i = 0; i < request.getParameterValues("aves_update").length;i++){//aves_delete ??
		    sentencia += " anilla = ? OR";
		}
		sentencia = sentencia.substring(0, sentencia.length() - 3);
		sentencia+=";";
		sql = con.prepareStatement(sentencia);
		for(int i = 0; i < request.getParameterValues("aves_update").length;i++){
		    sql.setString(i+1,request.getParameterValues("aves_update")[i]);
		}
	    }else{
		sql = con.prepareStatement("SELECT * FROM aves WHERE anilla = ?;");
		sql.setString(1,request.getParameterValues("aves_update")[0]);
	    }
	    resultado = sql.executeQuery();
	    if(request.getParameter("operacion").equals("delete")){
		//Vamos a emplear la query del select y a transformarla para
		//ahorrarnos todo el rollo de generarla.
		String sql_para_delete = sql.toString().split(":")[1];
		sql_para_delete = sql_para_delete.replace("SELECT *","DELETE");
		//lo pasamos por atributo por si el usuario confirma la operación
		request.setAttribute("delete_query",sql_para_delete);
		
		ArrayList<Ave> aves = new ArrayList();
		while(resultado.next()){
		    ave = new Ave();
		    ave.setAnilla(resultado.getString("anilla"));
		    ave.setEspecie(resultado.getString("especie"));
		    ave.setLugar(resultado.getString("lugar"));
		    ave.setFecha(resultado.getString("fecha"));
		    aves.add(ave);
		}
		request.setAttribute("aves_delete",aves);
	    }else{
		if(resultado.next()){
		    ave.setAnilla(resultado.getString("anilla"));
		    ave.setEspecie(resultado.getString("especie"));
		    ave.setLugar(resultado.getString("lugar"));
		    ave.setFecha(resultado.getString("fecha"));
		    request.setAttribute("ave",ave);
		}
	    }
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
