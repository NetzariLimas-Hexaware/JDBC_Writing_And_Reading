/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.hexaware.javadatabaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 *
 * @author netza
 */
public class JavaDatabaseConnection {

    public static void main(String[] args) throws SQLException {
        Connection myConn = null;
        PreparedStatement myStmt = null;
        ResultSet myRs = null;
        
        myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "pruebas", "1234");
        System.out.println("Conexion a MYSQL correctamente!\n");
        
        try{
            myStmt = myConn.prepareStatement("CREATE DATABASE IF NOT EXISTS jdbctest");
            myStmt.execute();
            
            System.out.println("Se ha creado la base de datos!\n");
            
            myStmt = myConn.prepareStatement("USE jdbctest");
            myStmt.execute();
            
            System.out.println("Posicionamiento a base de datos correctamente!\n");
            
            myStmt = myConn.prepareStatement("DROP TABLE IF EXISTS datasheet");
            myStmt.execute();
            
            System.out.println("Eliminar tabla si existe..!\n");
            
            myStmt = myConn.prepareStatement("CREATE TABLE `datasheet` ("
                    + " `name` varchar(35) NOT NULL,"
                    + " `lastname` varchar(20) NOT NULL,"
                    + " `email` varchar(35) NOT NULL,"
                    + " `password` varchar(35) NOT NULL,"
                    + " `company` varchar(35) DEFAULT NULL,"
                    + " `address` text DEFAULT NULL,"
                    + " `city` varchar(35) DEFAULT NULL,"
                    + " `zip_code` varchar(5) NOT NULL,"
                    + " `mobile_phone` varchar(13) NOT NULL,"
                    + " PRIMARY KEY (`email`)"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=latin1;"
            );
            
            myStmt.execute();
            
            System.out.println("Se ha creado la tabla correctamente!\n");
            
            myStmt = myConn.prepareStatement("INSERT INTO `datasheet` "
                    + "(`name`,`lastname`,`email`,`password`, `company`, `address`,`city`,`zip_code`,`mobile_phone`) "
                    + "VALUES (?,?,?,?,?,?,?,?,?);");
			
            myStmt.setString(1, "Netzari");
            myStmt.setString(2, "Limas");
            myStmt.setString(3, "Netzarigilbertol@hexaware.com");
            myStmt.setString(4, "P4ssw0rd");
            myStmt.setString(5, "Hexaware");
            myStmt.setString(6, "HexawareAddress");
            myStmt.setString(7, "Iselin");
            myStmt.setString(8, "12345");
            myStmt.setString(9, "+528991234567");
            
            myStmt.execute();
            
            myStmt = myConn.prepareStatement("SELECT * FROM datasheet");
            myRs = myStmt.executeQuery();
            
            System.out.println("Inforamcion insertada correctamente.");
        }
        catch(Exception exc){
               exc.printStackTrace();
        }
        finally{
            if(myRs != null){
                myRs.close();
            }
            
            if(myStmt != null){
                myStmt.close();
            }
            
            if (myConn != null) {
                myConn.close();
            }
        }
    }
}
