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
		case "delete":concluir_delete(request,response,con);break;
		case "cancel":request.getRequestDispatcher("Retornar").forward(request,response);break;
	    }
	}
	
    }
    protected void concluir_delete(HttpServletRequest request, HttpServletResponse response, Connection con){
	try {
	    PreparedStatement sql = null;
	    sql = con.prepareStatement(request.getParameter("delete_query"));
	    sql.executeUpdate();
	    request.getRequestDispatcher("/JSP/delete/finDelete.jsp").forward(request,response);
	} catch (SQLException ex) {
	    Logger.getLogger(Concluir.class.getName()).log(Level.SEVERE, null, ex);
	} catch (ServletException ex) {
	    Logger.getLogger(Concluir.class.getName()).log(Level.SEVERE, null, ex);
	} catch (IOException ex) {
	    Logger.getLogger(Concluir.class.getName()).log(Level.SEVERE, null, ex);
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
	    //definimos un array con las posibles coincidencias
	    String[] coincidencias = new String[] {"","",""};
	    //y un array con las claves de los campos
	    String[] nombresCampo = new String[] {"especie","lugar","fecha"};
	    //comprobamos qué campos son identicos a los que existen ya en la tupla a modificar
	    if(!ave.getEspecie().equals(nuevaAve.getEspecie())){
		coincidencias[0] = nuevaAve.getEspecie();
	    }
	    if(!ave.getLugar().equals(nuevaAve.getLugar())){
		coincidencias[1] = nuevaAve.getLugar();
	    }
	    if(!ave.getFecha().equals(nuevaAve.getFecha())){
		coincidencias[2] = nuevaAve.getFecha();
	    }
	    //si al menos uno de estos campos es distinto hacemos el update
	    if(coincidencias[0].length() > 0 ||coincidencias[1].length() > 0 ||coincidencias[2].length() > 0){
		//Vamosa construir la sentencia
		String sentencia = "UPDATE aves SET ";
		//Si hemos llegado hasta aqui sabes que, al menos, vamos a cambiar 1 campo
		int campos = 1;
		//con un bucle for recorremos las coincidencias para seguir construyendo la sentencia
		for(int i=0;i<coincidencias.length;i++){
		    if(coincidencias[i].length() > 0){
			sentencia += nombresCampo[i] + "= ?,";
		    }
		}
		//quitamos la ultima coma
		sentencia = sentencia.substring(0, sentencia.length() -1);
		//y añadimos el final que es siempre el mismo
		sentencia += " WHERE anilla = ?";
		sql = con.prepareStatement(sentencia);
		//ahora hay que reemplazar correctamente cada campo
		if(coincidencias[0].length() > 0){
		    sql.setString(campos,nuevaAve.getEspecie());
		    campos++;
		}
		if(coincidencias[1].length() > 0){
		    sql.setString(campos,nuevaAve.getLugar());
		    campos++;
		}
		if(coincidencias[2].length() > 0){
		    sql.setString(campos,nuevaAve.getFecha());
		    campos++;
		}
		//la anilla siempre es necesaria
		sql.setString(campos,nuevaAve.getAnilla());
		//ejecutamos la sentencia
		sql.executeUpdate();

		Conexion.CloseConnection(con);
	    }else{
		request.setAttribute("error_sin_cambios", "nothingChanged");
	    }
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
