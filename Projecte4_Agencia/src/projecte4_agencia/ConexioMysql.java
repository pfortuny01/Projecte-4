
package projecte4_agencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexioMysql {
    
            // Atributs de la connexió
        private static Connection conn = null;
        private String driver;
        private String url;
        private String usuario;
        private String password;

        // Constructor de la connexió per accedir als mètodes
    public ConexioMysql(){

        String url = "jdbc:mysql://localhost:3306/agencia";
        String driver = "com.mysql.jdbc.Driver";
        String usuario = "root";
        String password = "";

        try{
            Class.forName(driver);
            conn = DriverManager.getConnection(url, usuario, password);
        }catch(ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        
    }
        // Mètode per realitzar la connexió
        public Connection getConnection(){

            if (conn == null){
                new ConexioMysql();
            }

            return conn;
        } // Fin getConn
    
}
