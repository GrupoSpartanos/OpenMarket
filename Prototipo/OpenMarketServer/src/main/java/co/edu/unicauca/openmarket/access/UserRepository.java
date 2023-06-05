/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.openmarket.access;

import co.edu.unicauca.openmarket.domain.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author santi
 */
public class UserRepository implements IUserRepository{
    
    private Connection conn;

    public UserRepository() {
        initDatabase();
    }
    
    public void connect() {
        // SQLite connection string
        //String url = "jdbc:sqlite:./myDatabase.db"; //Para Linux/Mac
        String url = "jdbc:sqlite:myDatabase.db"; //Para Windows
        //String url = "jdbc:sqlite::memory:";

        try {
            conn = DriverManager.getConnection(url);

        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void disconnect() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
    private void initDatabase() {
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS users (\n"
                + "	userId integer PRIMARY KEY AUTOINCREMENT,\n"
                + "     userName text NOT NULL,\n"
                + "     password text NOT NULL,\n"
                + "     name text NOT NULL,\n"
                + "     lastName text NOT NULL,\n"
                + "     email text NOT NULL,\n"
                + "	phone integer NOT NULL,\n"
                + "	role text NOT NULL,\n"
                + "	billingType text NOT NULL,\n"
                + "	birthDate text NOT NULL,\n"
                + "	punctuation integer NULL\n"
                + ");";

        try {
            this.connect();
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
            this.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(ProductRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean toRegister(User newUser) {
        try {
            //Validate product
            if (newUser == null || newUser.getUserName().isBlank()) {
                return false;
            }
            this.connect();

            String sql = "INSERT INTO users ( userName, password, name, lastName, email, phone, role, billingType, birthDate, punctuation) "
                    + "VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, newUser.getUserName());
            pstmt.setString(2, newUser.getPassword());
            pstmt.setString(3, newUser.getName());
            pstmt.setString(4, newUser.getLastName());
            pstmt.setString(5, newUser.getEmail());
            pstmt.setLong(6, newUser.getPhone());
            pstmt.setString(7, newUser.getRole());
            pstmt.setString(8, newUser.getBillingType());
            pstmt.setString(9, newUser.getBirthDate());
            pstmt.setInt(10, newUser.getPunctuation());
            pstmt.executeUpdate();
            this.disconnect();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }

    @Override
    public User login(String userName, String password) {
        try {
        this.connect();
        String sql = "SELECT * FROM users WHERE userName = ? AND password = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, userName);
        pstmt.setString(2, password);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()) {
            User user = new User();
            user.setUserId(rs.getLong("userId"));
            user.setName(rs.getString("name"));
            user.setLastName(rs.getString("lastName"));
            user.setEmail(rs.getString("email"));
            user.setPhone(rs.getLong("phone"));
            user.setRole(rs.getString("role"));
            user.setBillingType(rs.getString("billingType"));
            user.setBirthDate(rs.getString("birthDate"));
            user.setPunctuation(rs.getInt("punctuation"));
            this.disconnect();
            return user;
        }
        
        this.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(UserRepository.class.getName()).log(Level.SEVERE, null, ex);
        }    
        return null;       
    }
    
}
