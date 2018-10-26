package es.albarregas.crudpoolalvarofr.controllers;

import es.albarregas.crudpoolalvarofr.beans.Ave;
import es.albarregas.crudpoolalvarofr.connections.Conexion;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	DataSource ds = null;
	try {
	    ds = Conexion.conectar();
	    con = ds.getConnection();
	} catch (ClassNotFoundException ex) {
	    Logger.getLogger(Concluir.class.getName()).log(Level.SEVERE, null, ex);
	} catch (NamingException ex) {
	    Logger.getLogger(Concluir.class.getName()).log(Level.SEVERE, null, ex);
	} catch (SQLException ex) {
	    Logger.getLogger(Concluir.class.getName()).log(Level.SEVERE, null, ex);
	}
	if(request.getParameter("operacion") != null){
	    switch(request.getParameter("operacion")){
		case "update":concluir_update(request,response,con);break;
		case "delete":break;
		case "cancel":request.getRequestDispatcher("Retornar").forward(request,response);break;
	    }
	}
	
    }
    
    protected void concluir_update(HttpServletRequest request, HttpServletResponse response, Connection con){
	Ave ave = new Ave(),nuevaAve = new Ave();
	PreparedStatement sql = null;
	ResultSet resultado = null;
	try {	    
	    BeanUtils.populate(nuevaAve,request.getParameterMap());
	    sql = con.prepareStatement("SELECT * FROM aves WHERE anilla = ?;");
	    sql.setString(1,nuevaAve.getAnilla());
	    resultado = sql.executeQuery();
	    if(resultado.next()){
		ave.setAnilla(resultado.getString("anilla"));
		ave.setEspecie(resultado.getString("especie"));
		ave.setLugar(resultado.getString("lugar"));
		ave.setFecha(resultado.getString("fecha"));
		request.setAttribute("update_ave",ave);
	    }
	    request.setAttribute("update_nuevaAve",nuevaAve);
	    sql = con.prepareStatement("UPDATE aves SET especie = ?, lugar = ?, fecha = ? WHERE anilla = ?");
	    sql.setString(1,nuevaAve.getEspecie());
	    sql.setString(2,nuevaAve.getLugar());
	    sql.setString(3,nuevaAve.getFecha());
	    sql.setString(4,nuevaAve.getAnilla());
	    sql.executeUpdate();
	    
	    Conexion.CloseConnection(con);
	    request.getRequestDispatcher("/JSP/update/finUpdate.jsp").forward(request,response);
	} catch (IllegalAccessException ex) {
	    Logger.getLogger(Concluir.class.getName()).log(Level.SEVERE, null, ex);
	} catch (InvocationTargetException ex) {
	    Logger.getLogger(Concluir.class.getName()).log(Level.SEVERE, null, ex);
	} catch (SQLException ex) {
	    Logger.getLogger(Concluir.class.getName()).log(Level.SEVERE, null, ex);
	} catch (ServletException ex) {
	    Logger.getLogger(Concluir.class.getName()).log(Level.SEVERE, null, ex);
	} catch (IOException ex) {
	    Logger.getLogger(Concluir.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
    
    @Override
    public String getServletInfo() {
	return "Short description";
    }// </editor-fold>

}
