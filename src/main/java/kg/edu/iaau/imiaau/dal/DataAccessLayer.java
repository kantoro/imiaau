/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package kg.edu.iaau.imiaau.dal;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Kantoro
 */
public class DataAccessLayer {
    //private dbLink;
    private String dbHost;
    private String dbUsername;
    private String dbPassword;
    private String dbName;    
    public int queryCount;
    
    protected String query;
    protected Connection dbCon;
    protected DataSource pool;
    
    public DataAccessLayer() throws Exception {
        Context env = null;
        try {
            env = (Context) new InitialContext().lookup("java:comp/env");
            pool = (DataSource)env.lookup("jdbc:mysql://localhost:3306/imiaauDB");
            if( pool == null )
                throw new Exception("imiaauDB not found...");
        } catch (NamingException ne) {
            throw new Exception("...imiaauDB... " + ne.getMessage());
        }
    }
    
    public boolean connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        dbCon = pool.getConnection();
        return true;
    }
    
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        dbCon = pool.getConnection();
        return dbCon;
    }
    
    public void close() throws SQLException {
        dbCon.close();
    }
    
    public void execSQL() {
        
    }
}
