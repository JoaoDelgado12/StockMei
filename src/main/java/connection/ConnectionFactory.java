package connection;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

/**
 *
 * @author Master
 */
public class ConnectionFactory {
        private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
        private static final String URL = System.getenv("DB_URL");
        private static final String USER = System.getenv("DB_USER");
        private static final String PASSWORD = System.getenv("DB_PASS");
    //Métodos
        
        public static Connection getConnection() throws ClassNotFoundException, SQLException{
            Connection con = null;

            try {
                Class.forName(DRIVER);
                con = DriverManager.getConnection(URL, USER, PASSWORD);
                
            }catch (Exception e){
                System.err.println("SQLException: Não foi conectado o banco.");
            }
            
            return con;
             
        }
}