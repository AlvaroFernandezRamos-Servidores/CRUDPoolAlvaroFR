package es.albarregas.crudpoolalvarofr.connections;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Conexion {
    static DataSource datasource;
    
    public static DataSource conectar() throws ClassNotFoundException, NamingException{
	Connection con = null;
	Context  initialContext  =  new  InitialContext();
	datasource  =(DataSource)initialContext.lookup("java:comp/env/jdbc/CRUDjndi");
	Class.forName("com.mysql.jdbc.Driver");
	return datasource;
    }
    
    public static void CloseConnection(Connection con) throws SQLException{
	con.close();
    }
}
