package es.albarregas.crudpoolalvarofr.connections;

import es.albarregas.crudpoolalvarofr.utils.MyLogger;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Conexion {
    static DataSource datasource;
    
    public static DataSource conectar(){
	try {
	    Connection con = null;
	    Context  initialContext  =  new  InitialContext();
	    datasource  =(DataSource)initialContext.lookup("java:comp/env/jdbc/CRUDjndi");
	    Class.forName("com.mysql.jdbc.Driver");
	} catch (NamingException ex) {
	    MyLogger.doLog(ex,Conexion.class, "fatal");
	} catch (ClassNotFoundException ex) {
	    MyLogger.doLog(ex,Conexion.class, "fatal");
	}
	return datasource;
    }
    
    public static void CloseConnection(Connection con) throws SQLException{
	con.close();
    }
}
