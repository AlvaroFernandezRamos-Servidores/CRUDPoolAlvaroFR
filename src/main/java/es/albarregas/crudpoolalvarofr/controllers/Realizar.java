/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
	Connection con = null;
	try {
	    con = Conexion.conectar();
	    System.out.println(request.getParameter("anilla"));
	    if(request.getParameter("operacion")!=null){
		switch(request.getParameter("operacion")){
		    case "create":realizar_create(request,response,con);break;
		    case "read":realizar_read(request,response,con);break;
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
	PreparedStatement sql = null;
	ResultSet resultado = null;
	sql = con.prepareStatement("SELECT * FROM aves WHERE anilla = ?;");
	sql.setString(1,request.getParameter("anilla"));
	resultado = sql.executeQuery();
	Ave ave = new Ave();
	if(resultado.next()){
	    //TODO MANDAR ERROR PORQUE LA ANILLA YA EXISTE
		request.getRequestDispatcher(request.getContextPath()+"/JSP/error.jsp");
	}else{
	    try {
		BeanUtils.populate(ave,request.getParameterMap());
		sql = con.prepareStatement("INSERT INTO aves VALUES (?,?,?,?)");
		sql.setString(1,ave.getAnilla());
		sql.setString(2,ave.getEspecie());
		sql.setString(3,ave.getLugar());
		sql.setString(4,ave.getFecha());
		sql.executeUpdate();
		Conexion.CloseConnection(con);
		request.getRequestDispatcher("index.html").forward(request,response);
	    } catch (IllegalAccessException ex) {
		Logger.getLogger(Realizar.class.getName()).log(Level.SEVERE, null, ex);
	    } catch (InvocationTargetException ex) {
		Logger.getLogger(Realizar.class.getName()).log(Level.SEVERE, null, ex);
	    } catch (ServletException ex) {
		Logger.getLogger(Realizar.class.getName()).log(Level.SEVERE, null, ex);
	    } catch (IOException ex) {
		Logger.getLogger(Realizar.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
    }
    
    @Override
    public String getServletInfo() {
	return "Short description";
    }// </editor-fold>

}
