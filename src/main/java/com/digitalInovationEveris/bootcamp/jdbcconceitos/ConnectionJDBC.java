package com.digitalInovationEveris.bootcamp.jdbcconceitos;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionJDBC {
    public static void main(String[] args) {

        String urlConnection = "jdbc:mysql://localHost/digital_everis_bootCamp";
        //jdbc:nomeDrive://local/nomeBanco

        try (Connection conx = DriverManager.getConnection(urlConnection, "root", "Dress@97");) {
                                                         //urlDobanco,      User            ,Password
            System.out.println("OK");
        } catch (Exception e) {
            System.out.println("Fail");
        }

    }

}
